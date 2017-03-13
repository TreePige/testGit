package com.rongdu.eloan.modules.workflow.controller;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.utils.parser.impl.DefaultClassTypeParser;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.workflow.service.RDTaskService;
import com.rongdu.eloan.modules.workflow.utils.observation.TaskAssignerCenter;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/modules/workflow/controller/ProcessTaskController")
public class ProcessTaskController extends WorkflowBaseController {

    @Autowired
    private RDTaskService taskService;
    @Autowired
    private TaskService task;

    private DefaultClassTypeParser defaultClassTypeParser = new DefaultClassTypeParser();

    /**
     * 执行指定任务
     *
     * @param taskId
     * @param serviceVariables 业务相关参数
     * @param processVariables 流程相关参数
     * @param request
     * @param response
     * @throws ServiceException
     */
    @RequestMapping(value = "completeTask")
    public void completeTask(
            @RequestParam(value = "taskId", required = false) String taskId,
            @RequestParam(value = "serviceVariables", required = false) String serviceVariables,
            @RequestParam(value = "processVariables", required = false) String processVariables,
            HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        TaskAssignerCenter.taskAssignee.remove();
        Map<String, Object> processVarMap = defaultClassTypeParser.parse(
                processVariables, Map.class);

        Map<String, Object> bpmVariables = new HashMap<String, Object>();

        // TODO FHJ,如果什么东西都是需要前台往后台传，那就可以进行作弊，这个方法需要在后台进行调整，影响流程的参数传递需要慎重考虑！
        // 这里采用统一规范，优先去borrow表查amount,如果没有，查borrow_requrement，把这个amount做为流程变量使用，前台则不用传值，防止作弊
        SysUser loginUser = getLoginUser(request);

        SysRole role = getRoleForLoginUser(request);
        // 准备bpm变量集合
        prepareServiceParams(serviceVariables, bpmVariables);
        prepareProcessParams(processVarMap, bpmVariables);
        prepareLoginInfoParams(loginUser, bpmVariables);
        prepareRoleInfoParams(role, bpmVariables);

        taskService.completeTask(taskId, loginUser.getUserName(), bpmVariables);

        Map<String, Object> res = new HashMap<String, Object>();

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);

        // TODO FHJ 用HashMap改造，不会静态变量，想想有没有并且问题

        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

        // if(TaskAssignerCenter.isNew&&TaskAssignerCenter.taskAssignee.get()!=null)
        // {
        // res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS +
        // ",任务分配给了：" + TaskAssignerCenter.taskAssignee.get());
        // } else {
        // res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        // }

        TaskAssignerCenter.isNew = false;
        TaskAssignerCenter.taskAssignee.remove();
        ServletUtils.writeToResponse(response, res);
    }

    /**
     * 批量执行任务
     */
    @RequestMapping(value = "completeBatchTasks")
    public void completeBatchTasks(
            @RequestParam(value = "tasksInfo", required = false) String tasksInfo,
            HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {

        List<JSONObject> taskList = defaultClassTypeParser.parse(tasksInfo,
                List.class);

        SysUser loginUser = getLoginUser(request);

        Map<String, Object> res = new HashMap<String, Object>();

        StringBuilder errorMsg = new StringBuilder();

        String taskId = null;

        // 如果单个任务失败了不会影响剩下的任务被执行，所有失败的任务会被记录下来

        for (Map taskInfo : taskList) {
            Map processVariables = (Map) taskInfo.get("processVariables");
            taskId = (String) taskInfo.get("taskId");
            String serviceVariables = taskInfo.get("serviceVariables")
                    .toString();

            // Map<String, Object> processVarMap =
            // defaultClassTypeParser.parse(processVariables, Map.class);

            Map<String, Object> bpmVariables = new HashMap<String, Object>();

            // 准备bpm变量集合
            prepareServiceParams(serviceVariables, bpmVariables);
            prepareProcessParams(processVariables, bpmVariables);
            prepareLoginInfoParams(loginUser, bpmVariables);
            try {
                taskService.completeTask(taskId, loginUser.getUserName(),
                        bpmVariables);
            } catch (ServiceException e) {
                errorMsg.append("task:" + taskId + "失败：" + e.getMessage());
            }
        }
        // 把所有的异常汇总后统一抛出给前台
        if (!StringUtils.isEmpty(errorMsg.toString())) {
            throw new ServiceException(errorMsg.toString());
        }

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

        ServletUtils.writeToResponse(response, res);
    }

    @RequestMapping(value = "claimTask")
    public void claimTask(
            @RequestParam(value = "taskId", required = false) String taskId,
            HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        Map<String, Object> res = new HashMap<String, Object>();
        SysUser loginUser = getLoginUser(request);
        SysRole role = getRoleForLoginUser(request);
        //TaskService taskServices = (new TaskServiceWrapper(taskService)).getTaskService();
        TaskQuery createTaskQuery = task.createTaskQuery();
        Task task = createTaskQuery.taskId(taskId).singleResult();
        String roleCode = task.getDescription();
        if (role.getNid().equals(roleCode)) {
            taskService.claimTask(taskId, loginUser.getUserName());
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        } else {
            res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "无法处理");
        }
        ServletUtils.writeToResponse(response, res);
    }


    @RequestMapping(value = "claimBatchTask")
    public void claimBatchTask(
            @RequestParam(value = "taskIdList") String taskIdList,
            HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        Map<String, Object> res = new HashMap<String, Object>();
        SysUser loginUser = getLoginUser(request);
        SysRole role = getRoleForLoginUser(request);
        //TaskService taskServices = (new TaskServiceWrapper(taskService)).getTaskService();
        TaskQuery createTaskQuery = task.createTaskQuery();
        String[] taskIdArray = taskIdList.split(",");
        StringBuilder errMsg = new StringBuilder();
        for (String taskId : taskIdArray) {
            Task task = createTaskQuery.taskId(taskId).singleResult();
            String roleCode = task.getDescription();
            if (role.getNid().equals(roleCode)) {
                try {
                    taskService.claimTask(taskId, loginUser.getUserName());
                } catch (Exception e) {
                    errMsg.append("task:" + taskId + "失败：" + e.getMessage());
                }
            } else {
                errMsg.append("task:" + taskId + "未授权给当前角色\n" );
            }
        }
        // 把所有的异常汇总后统一抛出给前台
        if (!StringUtils.isEmpty(errMsg.toString())) {
            throw new ServiceException(errMsg.toString());
        }

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        ServletUtils.writeToResponse(response, res);
    }
}
