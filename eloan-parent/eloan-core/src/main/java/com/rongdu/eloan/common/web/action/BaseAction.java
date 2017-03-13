package com.rongdu.eloan.common.web.action;


import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.exception.ParamValideException;
import com.rongdu.eloan.common.spring.security.authentication.handler.SaveLoginInfoAuthenticationSuccessHandler;
import com.rongdu.eloan.common.spring.security.userdetails.UserFunction;
import com.rongdu.eloan.common.spring.security.userdetails.UserRole;
import com.rongdu.eloan.common.utils.BaseController;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.NumberUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.StringUtil;
import com.rongdu.eloan.common.web.model.URLConfig;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.service.SysRoleService;
import com.rongdu.eloan.modules.system.service.SysUserService;

/**
 * 
 * 小贷基类action
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午1:48:28
 */
@Controller
public abstract class BaseAction extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseAction.class);

	// URL匹配器
	//private static UrlMatcher URL_MATCHER = new AntUrlPathMatcher();
	@Autowired
	protected URLConfig mlmsAppServerConfig;
	@Autowired
	protected SysUserService sysUserService;
   
//    @Autowired
////    protected BaseServiceImpl baseService;

    @Autowired
    protected SysRoleService roleService;
//    @Autowired
//    private PubProjectDao projectDao;
//    @Autowired
//    private PubProjectProcessDao projectProcessDao;





	/**
	 * 初始化绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	protected final void initBinderInternal(WebDataBinder binder) {
		registerDefaultCustomDateEditor(binder);
		registerDefaultCustomNumberEditor(binder);
		initBinder(binder);
	}

	private void registerDefaultCustomNumberEditor(WebDataBinder binder) {
		// 注册双精度数字格式化类型: #0.00
		NumberFormat numberFormat = new DecimalFormat("#0.00");
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, numberFormat, true));
	}

	protected void registerDefaultCustomDateEditor(WebDataBinder binder) {
		// 注册默认的日期格式化类型: yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	
//	protected void setProjectCode(String processInstanceId,Map<String,Object> data)throws ServiceException{
//	    ParamChecker.checkEmpty(processInstanceId, "processInstanceId");
//	    PubProjectProcess p;
//	    try {
//            p=projectProcessDao.getItem(processInstanceId, "PubProjectProcess");
//            setProjectCode(p.getProjectId(), data);
//        } catch (PersistentDataException e) {
//            e.printStackTrace();
//            throw new ServiceException(400, e.getMessage());
//        }
//	}
//	protected void setProjectCode(Long projectId,Map<String,Object> data)throws ServiceException{
//	    if(data==null){
//	        data=new HashMap<String,Object>();
//	    }
//	    ParamChecker.checkEmpty(projectId, "projectId");
//	    Project p;
//	    try {
//            p=projectDao.getItem(projectId,"Project");
//            data.put("projectCode", p.getCode());
//        } catch (PersistentDataException e) {
//            e.printStackTrace();
//            throw new ServiceException(400, e.getMessage());
//        }
//    }
	/**
	 * 提供子类初始化表单, 子类如果要调用请重写该方法
	 * 
	 * @param binder
	 */
	protected void initBinder(WebDataBinder binder) {
	}

	/**
	 * 获得当前登录用户名
	 * 
	 * @return String
	 */
	/*protected String getLoginName() {
		// TODO; 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return user.getUsername();
	}*/

	/**
	 * 获取当前登录用户的用户
	 * 
	 * @param request
	 * @return
	 * @see SaveLoginInfoAuthenticationSuccessHandler
	 */
	protected SysUser getLoginUser(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("SysUser");
		if(sysUser == null) {
			throw new IllegalArgumentException("程序出错，Session中没有用户！");
		}
		return sysUser;
	}

	/**
	 * 为BasicVO注入当前登录用户
	 * 
	 * @param basicDataBean
	 * @param request
//	 */
//	protected void fetchLoginUser(BasicDataBean basicDataBean,
//			HttpServletRequest request) {
//		SysUser loginUser = getLoginUser(request);
//		basicDataBean.setLoginUserId(loginUser.getId());
//		basicDataBean.setOfficeId(loginUser.getOfficeId());
//		basicDataBean.setUserName(loginUser.getUserName());
//	}

	/**
	 * 获得当前登录用户信息
	 * 
	 * @return SystemUser
	 * @throws ServiceException
	 */
	protected SysUser getSysUser() throws ServiceException {
		// 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		SysUser sysUser = sysUserService.getUserByUserName(user.getUsername());
		return sysUser;
	}

	/**
	 * 设置一个Token 。 放置重复提交<br/>
	 * 转向页面的时候根据指定的tokenName名字 在session中存储一个值字符的"true",提交页面时在处理接受的方法中调用
	 * isTonten方法判断session中的值
	 * 
	 * @param tokenName
	 *            指定的Token名称
	 * @param request
	 */
	protected void setToken(String tokenName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		session.setAttribute(tokenName, "true");
	}

	/**
	 * 判断session中Token的值<br/>
	 * 根据指定的tokenName获取session中对应的值，如果该值符合条件则改变session中的值并返会"" <br/>
	 * 如果不符合条件则返回 提示信息
	 * 
	 * @param tokenName
	 *            request
	 * @param request
	 * @return 符合条件返回true， 否则返回false
	 */
	protected String isToken(String tokenName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		String tokenValue = (String) session.getAttribute(tokenName);
		String paramValue = (String) request.getParameter(tokenName);

		// 参数、session中都没用token值提示错误
		if (StringUtil.isBlank(paramValue) && StringUtil.isBlank(tokenValue)) {
			return "会话Token未设定！";
		} else if (StringUtil.isBlank(paramValue)
				&& !StringUtil.isBlank(tokenValue)) {
			return "表单Token未设定！";
		} else if (paramValue.equals(tokenValue)
				&& !StringUtil.isBlank(tokenValue) && "true".equals(tokenValue)) { // session中有token,防止重复提交检查
			session.setAttribute(tokenName, "false");
			return "";
		} else {
			return "请勿重复提交！";
		}
	}

	protected void message(HttpServletResponse response) throws IOException {
		this.message(response, "", true);
	}

	/**
	 * 消息处理方法
	 * 
	 * @param msg
	 *            消息
	 */
	protected void message(HttpServletResponse response, String msg)
			throws IOException {
		this.message(response, msg, true);
	}

	/**
	 * 消息处理方法重载
	 * 
	 * @param msg
	 *            消息
	 */
	protected void message(HttpServletResponse response, String msg,
			boolean result) throws IOException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", result);
		data.put("msg", msg);
		this.jsonResponse(response, data);
	}

	protected Integer paramInt(HttpServletRequest request, String str) {
		return NumberUtil.getInt(request.getParameter(str));
	}

	protected Long paramLong(HttpServletRequest request, String str) {
		return NumberUtil.getLong(request.getParameter(str));
	}

	protected String paramString(HttpServletRequest request, String str) {
		return StringUtil.isNull(request.getParameter(str));
	}

	protected Byte paramByte(HttpServletRequest request, String str) {
		return NumberUtil.getByte(request.getParameter(str));
	}

	protected String redirect(String url) {
		return "redirect:" + mlmsAppServerConfig + url;
	}

	protected String redirectLogin() {
		return redirect("/modules/login.htm");
	}

	protected String success() {
		return redirect("/success.htm");
	}

	protected String error() {
		return redirect("/error.htm");
	}

	protected String success(ModelMap model) {
		return "success";
	}

	protected String error(ModelMap model) {
		return "error";
	}

	/**
	 * 根据URL地址判断是否有访问的权限
	 * 
	 * @param url
	 *            访问的URL
	 * @return 是否通过
	 */
	protected boolean isAllowAccess(String url) {
		Map<String, UserFunction> functionMap = ((UserRole) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal())
				.getFunctionMap();
		if (functionMap.containsKey(url)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前操作的功能Id。
	 * <p>
	 * 通过当前操作功能的URL，获取对应功能的Id。如果没有匹配的功能，则返回空。
	 * 
	 * @return Long 功能Id
	 */
	private Long getSystemFunctionId() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		
		String requestUri = request.getRequestURI();
		  RequestMatcher requestMatcher =null;
		String contextPath = request.getContextPath();
		String queryStr = request.getQueryString();

		// 获得带请求参数，但不带协议、主机名、端口和应用上下文的请求路径
		StringBuffer pathSb = new StringBuffer();
		String path = null;
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			pathSb = pathSb.append(requestUri.substring(contextPath.length()));
		} else {
			pathSb = pathSb.append(requestUri);
		}
		if (StringUtils.isNotBlank(queryStr)) {
			pathSb = pathSb.append(queryStr);
		}
		path = pathSb.toString();

		// 根据请求路径匹配对应的功能
		Map<String, UserFunction> functionMap = ((UserRole) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal())
				.getFunctionMap();
		Long funcId = null;
		if (functionMap.containsKey(path)) {
			funcId = functionMap.get(path).getId();
		} else {			
			for (Map.Entry<String, UserFunction> func : functionMap.entrySet()) {
			 requestMatcher = new AntPathRequestMatcher(func.getKey() + "*");
				if (requestMatcher.matches(request)) {
					funcId = func.getValue().getId();
					break;
				}
			}
		}
		return funcId;
	}

	@ExceptionHandler({ ServiceException.class })
	public void excptionDispose(ServiceException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, e.getCode());
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

		logger.error("ServiceException:", e);

		ServletUtils.writeToResponse(response, res);
	}

    @ExceptionHandler({RuntimeException.class})
    public void runtimeExcptionDispose(RuntimeException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "数据保存失败，请稍后重试");

		logger.error("RuntimeException", e);

        ServletUtils.writeToResponse(response, res);
    }
    
    @ExceptionHandler({BindException.class})
    public void runtimeExcptionDispose(BindException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "数据保存失败，请稍后重试");
    
		logger.error("参数校验失败"+ e.getFieldError().getDefaultMessage(), e);

        ServletUtils.writeToResponse(response, res);
    }
    
    /**
     * 绑定参数校验异常
     * @param ex
     * @param response
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void validException(MethodArgumentNotValidException ex,HttpServletResponse response) {
    	
    	Field[] fields = ex.getBindingResult().getTarget().getClass().getDeclaredFields();
        StringBuffer errorAppend = new StringBuffer();
    	for (Field field : fields) {
    		FieldError fieldError = ex.getBindingResult().getFieldError(field.getName());
    		if(fieldError != null){
    			errorAppend.append(fieldError.getDefaultMessage());
    			break;
    		}
    	}
    	
    	Map<String, Object> res = new HashMap<String, Object>();
    	res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_PARAM_INSUFFICIENT);
        res.put(Constant.RESPONSE_CODE_MSG, errorAppend.toString());
         
 		logger.error("参数校验失败", ex);
 		
        ServletUtils.writeToResponse(response, res);
    }
    
    /**
     * 业务参数校验异常
     * @param ex
     * @param response
     */
    @ExceptionHandler(value = ParamValideException.class)
    public void paramValideException(ParamValideException ex,HttpServletResponse response) {
    	Map<String, Object> res = new HashMap<String, Object>();
   	    res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_PARAM_INSUFFICIENT);
        res.put(Constant.RESPONSE_CODE_MSG, ex.getMessage());
        
        logger.error("参数校验失败", ex);
        
        ServletUtils.writeToResponse(response, res);
    }
    
    @ExceptionHandler({Exception.class})
    public void runtimeExcptionDispose(Exception e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

		logger.error("Exception:", e);

        ServletUtils.writeToResponse(response, res);
    }
    
	/**
	 * 从自定义session 中获取角色
	 * 
	 * @param request
	 * @return
	 */
	public List<Long> getRole(HttpServletRequest request) {

		List<Long> roles = new ArrayList<Long>();
		HttpSession session = request.getSession();
		Long role = (Long) session.getAttribute(Constant.ROLEID);
		roles.add(role);

		return roles;

	}

    public SysRole getRoleForLoginUser(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Long roleId = (Long) session.getAttribute(Constant.ROLEID);
        SysRole role = roleService.getRoleById(roleId);

        return role;

    }
    
    public String getLoginUserName(HttpServletRequest request) throws ServiceException {
    	SysUser loginUser = getLoginUser(request);

        return loginUser.getUserName();

    }
    public List<String> getCoverdOffices(SysUser loginUser) {
        ArrayList<String> list = new ArrayList<String>();
        String coverdOfficeStr = loginUser.getOfficeOver();
        if(!org.springframework.util.StringUtils.hasLength(coverdOfficeStr)){
            coverdOfficeStr="null";
        }
        StringTokenizer stringTokenizer = new StringTokenizer(coverdOfficeStr, ",");
        while(stringTokenizer.hasMoreElements()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }
    /**
	 * String集合 转换为实体类型  日期格式为yyyy-MM-dd HH:mm:ss
	 * @param s1  String集合
	 * @param clasz  实体类
	 * @param list  封装的集合
	 */
    public void jsonArrayToTime(String s1, List list, Class clasz) {
        if (!StringUtils.isEmpty(s1)) {
            JSONArray jsonArray = JSONArray.parseArray(s1);
            for (int i = 0; i < jsonArray.size(); i++) {
                String obj = jsonArray.getString(i);
                if (!StringUtils.isEmpty(obj)) {
                	 list.add(JsonUtil.parse(obj, clasz));
                }
            }
        }
    }
    /**
  	 * String集合 转换为实体类型  日期格式为yyyy-MM-dd
  	 * @param s1  String集合
  	 * @param clasz  实体类
  	 * @param list  封装的集合
  	 */
    public void jsonArrayToDate(String s1, List list, Class clasz) {
        if (!StringUtils.isEmpty(s1)) {
            JSONArray jsonArray = JSONArray.parseArray(s1);
            for (int i = 0; i < jsonArray.size(); i++) {
                String obj = jsonArray.getString(i);
                if (!StringUtils.isEmpty(obj)) {
                	 list.add(JsonUtil.parseWithOnlyYMDDate(obj, clasz));
                }
            }
        }
    }
}
