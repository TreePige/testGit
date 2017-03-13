package com.rongdu.eloan.modules.common.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ListUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.domain.PubProfile;
import com.rongdu.eloan.modules.common.exception.PersistentDataException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.common.service.PubProfileService;

@Controller
@RequestMapping("/modules/common/action/PubProfileAction")
public class PubProfileAction extends BaseAction {

    @Autowired
    private PubProfileService profileService;
//    @Autowired
//    private LoanApplicationService loanApplicationService;

    /**
     * 
     * @description 查找树状附件, 根据taskId || projectId || consultId
     * @param taskId
     * @param projectId
     * @param consultId
     * @param response
     * @param request
     * @throws Exception
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @RequestMapping("/queryProFiles")
    public void queryProFiles( 
    		@RequestParam(value="taskId") Long taskId,
    		@RequestParam(value="projectId") Long projectId,
    		@RequestParam(value="consultId") Long consultId,
    		HttpServletResponse response, HttpServletRequest request
    		) throws Exception {
        List<Map<String, Object>> list = null;
        if(taskId != null){
            list = profileService.queryAttachTreeByTaskId(taskId);
        }else if(projectId != null){
        	list = profileService.queryAttachTreeByProjectId(taskId);
        }else if(consultId != null){
        	list = profileService.queryAttachTreeByConsultId(taskId);
        }else{
            throw new RuntimeException("processInstanceId taskId必须有一个不能为空");
        }
        list = ListUtil.list2Tree(list, "id", "parentId");
        ListUtil.attachCnt(list, "remark", "attachCnt");
        Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
    }
    
    
    /**
     * 
     * @description 查询所有类型,返回树状结构
     * @param request
     * @param response
     * @throws Exception
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @RequestMapping("/fetchAllAttach")
	public void fetchAllAttach(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Map<String, Object> res = new HashMap<String, Object>();
		List<Map<String, Object>> list= profileService.fetchAllAttach();
		list=ListUtil.list2Tree(list,"value","parentId");
		list=ListUtil.treeForExt(list,null,null,true);
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
	}

//    
//    @RequestMapping("/queryAllProFiles")
//    public void queryAllProFiles(final String taskId, final String processInstanceId,HttpServletResponse response){
//        new AbsActionWrapper(response) {
//            @Override
//            public Object doAction() throws ServiceException {
//
//                Map<String,Object> params=new HashMap<String, Object>();
//                if(!isEmpty(processInstanceId)){
//                    params.put("processInstanceId",processInstanceId);
//                }else if(!isEmpty(taskId)){
//                    params.put("taskId",taskId);
//                }
//                List<Map> list = profileService.queryAllProFiles(params);
//                return list;
//            }
//        };
//    }
//    @RequestMapping("/isBTypeExists")
//    public void isBTypeExists(final String id, final String btype,HttpServletRequest request,HttpServletResponse response){
//        new AbsActionWrapper(response) {
//            @Override
//            public Object doAction() throws ServiceException {
//                boolean exists= profileService.isBTypeExists(id,btype);
//                JSONObject obj=new JSONObject();
//                obj.put("success",true);
//                obj.put("exists",exists);
//                return obj;
//            }
//        };
//    }
//    @RequestMapping("/queryProfileWholeTree")
//    public void queryProfileWholeTree(HttpServletRequest request,HttpServletResponse response){
//        new AbsActionWrapper(response) {
//            @Override
//            public Object doAction() throws ServiceException {
//                List<Map> tree=profileService.queryProfileWholeTree();
//                return tree;
//            }
//        };
//    }
//    @RequestMapping("/queryProfileWholeTreeSingle")
//    public void queryProfileWholeTreeSingle(HttpServletRequest request,HttpServletResponse response){
//        new AbsActionWrapper(response) {
//            @Override
//            public Object doAction() throws ServiceException {
//                List<Map> tree=profileService.queryProfileWholeTreeSingle();
//                return tree;
//            }
//        };
//    }
//
//
//
//
//    /**以下暂时由lkf维护**/
//    @SuppressWarnings("unchecked")
//	@RequestMapping("/getPubProfileList")
//    public void getPubProfileList(HttpServletRequest request,HttpServletResponse response) throws ServiceException{
//        try {
//        	Map map = new HashMap();
//        	map.put("limit", 100);
//            List<PubProfile> list=profileService.getPubProfileList(map);
//            ServletUtils.writeToResponse(response,list);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    @RequestMapping("/getPubProfileTreeList")
//    public void getPubProfileTreeList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "node", required = false) String node) throws ServiceException{
//    	try {
////			Map<String,Object> map = new HashMap<String, Object>();
//			//map.put("type", 1);
//			Map<String,Object> map = new HashMap<String, Object>();
//			//map.put("type", 1);
//			map.put("parentId", "NaN".equals(node)?0:node);
////			map.put("parentId", 13);
//			map.put("limit", 100);
//			List<Map> profileList=profileService.getPubProfileTreeList(map);
////			List<SysOffice> officeList = sysOfficeService.getOfficeList(map);
//			ServletUtils.writeToResponselist(response, profileList);//officeList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	
//    }
//
//
//    /**
//     * 原生，不加处理
//     * @param json
//     * @param request
//     * @param response
//     */
////    @RequestMapping("/saveOrUpdateInterface")
////    public void saveOrUpdateInterface(final String json,HttpServletRequest request,HttpServletResponse response){
////        new AbsActionWrapper(request,response) {
////            @Override
////            public Object doAction() throws ServiceException {
////                Map tbs=parseJson(json);
////                loanApplicationService.saveOrUpdateLoanApplication(tbs);
////                return null;
////            }
////        };
////    }
//
//    /**
//     * 通过parentId查找指定级别的数据
//     * @param parentId
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/queryProfileTypeListByParentId")
//    public void queryProfileTypeListByParentId(final String parentId,HttpServletRequest request,HttpServletResponse response){
//        new AbsActionWrapper(request,response) {
//            @Override
//            public Object doAction() throws ServiceException {
//                List<String> where=getWhere();
//                where.add("disable!=1");
//                getSort().add("rd_sort");
//
//                List<Map> list=profileService.queryProFilesByParentId(parentId,this.params);
//                return list;
//            }
//        };
//    }
    
    @RequestMapping("/getProfileById")
    public void getProfileById(
    		@RequestParam(value="id", required=true) Long id,
    		HttpServletRequest request,HttpServletResponse response
    		) throws Exception {
    	Map<String, Object> res = new HashMap<String, Object>();
    	PubProfile profile = profileService.getProfileById(id); 
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, profile);
		ServletUtils.writeToResponse(response, res);
    }


	/**
	 * 
	 * @description 新增或修改,根据id判断
	 * @param json
	 * @param request
	 * @param response
	 * @author liyc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping("/saveOrUpdateProFile")
    public void saveOrUpdateProFile(
    		@RequestParam(value="json", required=true) String json,
    		HttpServletRequest request,HttpServletResponse response
    		) throws Exception {
        PubProfile rec= JsonUtil.parse(json, PubProfile.class);
        rec.setDisable(0);
        if(StringUtils.isEmpty(rec.getId())){
            String parentId= String.valueOf(rec.getParentId());
            Map<String,Object> params_=new HashMap<String, Object>();
            params_.put("sortStr","sort desc");
            params_.put("parentId", parentId);
            PageBounds pageBounds = new PageBounds();
            pageBounds.setPage(1);
            pageBounds.setLimit(1);
            List<PubProfile> o = profileService.getPageListByMap(params_, pageBounds);
            int sort= (o==null && o.size()<1)?1:(Integer)o.get(0).getSort()+1;
            rec.setSort(sort);;
        }
        profileService.saveOrUpdate(rec);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("success",true);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        result.put("rec",rec);
        ServletUtils.writeToResponse(response, result);
    }
	
    /**
     * 
     * @description 逻辑删除
     * @param id
     * @param request
     * @param response
     * @throws Exception
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @RequestMapping("/deleteProFile")
    public void deleteProFile(
    		@RequestParam(value="id", required=true) Long id,
    		HttpServletRequest request,HttpServletResponse response
    		) throws Exception {
        Map<String,Object> res = new HashMap<String, Object>();
        PubProfile profile = new PubProfile();
        profile.setId(id);
        profile.setDisable(1);
        profileService.saveOrUpdate(profile);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put("success",true);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        ServletUtils.writeToResponse(response, res);
    }
	
}
