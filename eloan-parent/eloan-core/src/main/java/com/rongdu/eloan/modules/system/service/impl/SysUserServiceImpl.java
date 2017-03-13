package com.rongdu.eloan.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.dao.BaseDao;
import com.rongdu.eloan.common.service.impl.BaseServiceImpl;
import com.rongdu.eloan.common.spring.security.authentication.encoding.PasswordEncoder;
import com.rongdu.eloan.modules.common.exception.RDRuntimeException;
import com.rongdu.eloan.modules.common.exception.ServiceException;
import com.rongdu.eloan.modules.system.dao.SysRoleDao;
import com.rongdu.eloan.modules.system.dao.SysUserDao;
import com.rongdu.eloan.modules.system.dao.SysUserRoleDao;
import com.rongdu.eloan.modules.system.domain.SysOffice;
import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;
import com.rongdu.eloan.modules.system.domain.SysUserRole;
import com.rongdu.eloan.modules.system.service.SysOfficeService;
import com.rongdu.eloan.modules.system.service.SysUserService;

@SuppressWarnings("rawtypes")
@Service(value = "sysUserServiceImpl")
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService {
    /**
	 * 日志信息
	 */
	private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private static final String NORMAL = "0";
    private static final String NOT_DELETED = "0";
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysOfficeService sysOfficeService;
	private IdentityService identityService;
	@Autowired
	private PasswordEncoder passwordEncoder;// 密码加密

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public SysUserRoleDao getSysUserRoleDao() {
		return sysUserRoleDao;
	}

	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	@Override
	public Boolean editUserLoginInfo(SysUser sysUser) throws ServiceException{
		try {
			return sysUserDao.editUserLoginInfo(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Boolean editUserPassWord(SysUser sysUser) throws ServiceException {
		try {
			return sysUserDao.editUserPassWord(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserById(long id) throws ServiceException {
		try {
			return sysUserDao.getByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public SysRole getRoleById(long id) throws ServiceException {
		try {
			return sysRoleDao.getByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int userUpdate(SysUser sysUser) throws ServiceException {
		try {
			return this.sysUserDao.update(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getUserPageList(Map<String, Object> mapdata) throws ServiceException {
		try {
			return sysUserDao.getUserInfoOracle(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int getUserSum(Map<String, Object> map) throws ServiceException {
		try {
			return sysUserDao.getPageCountOracle(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Override
	public BaseDao getMapper() {
		return sysUserDao;
	}

	@Override
	public void addUser(SysUser sysUser, String roleIdArr) throws ServiceException {
		try {
			sysUser.setPassword(passwordEncoder.encodePassword(sysUser.getPassword()));
			// 增加用户
			sysUserDao.insert(sysUser);
			String temp = roleIdArr.replaceAll("\\[", "").replaceAll("\\]", "");
			String[] roles = temp.split(","); 
			for(int i=0;i<roles.length;i++){
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(sysUser.getId());
				sysUserRoleDao.insert(sysUserRole);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean updateSysUserById(Map<String, Object> map) throws ServiceException {
		try {
			Boolean result = false;
			//首先删除角色关系
			Long userId = Long.valueOf(String.valueOf(map.get("id")));
			sysUserRoleDao.deleteByUserId(userId);
			String temp = String.valueOf(map.get("roleId")).replaceAll("\\[", "").replaceAll("\\]", "");			
			String[] roles = temp.split(","); 
			for(int i=0;i<roles.length;i++){
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(userId);
				sysUserRoleDao.insert(sysUserRole);
			}
			int isU = sysUserDao.updateSysUserById(map);
			if(isU > 0){
				result = true;
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getCustomerServiceStaffList(Map<String, Object> paramMap) throws ServiceException {
		try {
			return sysUserDao.getCustomerServiceStaffList(paramMap);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserByUserName(String userName) throws ServiceException {
		try {
			return sysUserDao.getUserByUserName(userName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int queryRoleUserIsUse(Map<String, Object> data) throws ServiceException {
		try {
			return sysUserDao.queryRoleUserIsUse(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e,Constant.FAIL_CODE_VALUE);
		}
	}

	/**************************** 以下是工作流相关代码 start ***********************************/
	/**
	 * 同步数据到Activiti Identify模块
	 * @param u
	 *            用户信息
	 * @param roleList
	 *            角色信息
	 */
	public void activitiData(SysUser u, List<SysRole> roleList) {
		UserQuery userQuery = identityService.createUserQuery();
		List<User> activitiUsers = userQuery.userId(u.getUserName()).list();
		if (activitiUsers.size() == 1) {
			updateActivitiData(u, roleList, activitiUsers.get(0));
		} else if (activitiUsers.size() > 1) {
			String errorMsg = "发现重复用户：id=" + u.getUserName();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		} else {
			newActivitiUser(u, roleList);
		}
	}

	/**
	 * 更新工作流用户以及角色
	 * @param user
	 *            用户对象
	 * @param groupList
	 *            用户拥有的角色集合
	 */
	private void updateActivitiData(SysUser user, List<SysRole> roleList,
			User activitiUser) {
		String userId = user.getUserName();
		// 更新用户主体信息
		cloneAndSaveActivitiUser(user, activitiUser);
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery()
				.groupMember(userId).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: {}",
					ToStringBuilder.reflectionToString(group));
			identityService.deleteMembership(userId, group.getId());
		}
		// 添加membership
		addMembershipToIdentify(roleList, userId);
	}

	/**
	 * 添加Activiti Identify的用户于组关系
	 * @param roleIds
	 *            角色ID集合
	 * @param userId
	 *            用户ID
	 */
	private void addMembershipToIdentify(List<SysRole> roleIds, String userId) {
		for (SysRole role : roleIds) {
			if (role != null && role.getNid() != null
					&& role.getNid().length() > 0) {
				identityService.createMembership(userId, role.getNid());
			}
		}
	}

	/**
	 * 添加工作流用户以及角色
	 * @param user
	 *            用户对象
	 * @param groupList
	 *            用户拥有的角色集合
	 */
	private void newActivitiUser(SysUser u, List<SysRole> roleList) {
		String userId = u.getUserName();
		// 添加用户
		saveActivitiUser(u);
		// 添加membership
		addMembershipToIdentify(roleList, userId);
	}

	/**
	 * 添加一个用户到Activiti
	 * @param user
	 *            用户对象, {@link User}
	 */
	private void saveActivitiUser(SysUser u) {
		String userId = u.getUserName();
		org.activiti.engine.identity.User activitiUser = identityService
				.newUser(userId);
		cloneAndSaveActivitiUser(u, activitiUser);
		logger.debug("add activiti user: {}",
				ToStringBuilder.reflectionToString(activitiUser));
	}

	/**
	 * 使用系统用户对象属性设置到Activiti User对象中
	 * @param op
	 *            系统用户对象
	 * @param activitiUser
	 *            Activiti User
	 */
	private void cloneAndSaveActivitiUser(SysUser op, User activitiUser) {
		activitiUser.setFirstName(op.getUserName());
		activitiUser.setLastName("");
		activitiUser.setPassword("");
		activitiUser.setEmail(op.getEmail());
		identityService.saveUser(activitiUser);
	}

	@Override
	public SysUser queryTheLeastBusyUserByRoleName(String roleName,
			String officeId, String processInstanceId, String taskDefinition) throws ServiceException {
		//传入的officeID所归属的营业厅
		//SysOffice sysOffice = sysOfficeService.getBusinessHallByOrganizationId(officeId); 
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", roleName);
		params.put("processInstanceId", processInstanceId);
		params.put("taskDefinition", taskDefinition);
		//params.put("userOfficeId", officeId);
        params.put("status", NORMAL);
        params.put("isDelete", NOT_DELETED);
       /* if(sysOffice!=null){
        	params.put("officeId", sysOffice.getId());
            params.put("length", sysOffice.getId().length());
        }*/
		// TODO FHJ
		Map<String, Object> queryTheLeastBusyUserByMap = sysUserDao.queryTheLeastBusyUserByMap(params);
		if(queryTheLeastBusyUserByMap==null){
		    throw new RDRuntimeException("分配任务失败，请检查人员配置");
		}

		SysUser sysUser = new SysUser();
		sysUser.setUserName((String) queryTheLeastBusyUserByMap.get("userName"));
		return sysUser;
	}

	@Override
	public SysUser queryTheLeastBusyUserByHeadRoleName(String roleName,
			String officeId, String processInstanceId, String taskDefinition)
			throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", roleName);
		params.put("processInstanceId", processInstanceId);
		params.put("taskDefinition", taskDefinition);
        params.put("status", NORMAL);
        params.put("isDelete", NOT_DELETED);
        
       /* params.put("userOfficeId", 1102);//总部风控部
        params.put("userHeadOfficeId", 1101);//总部财务部
        if(!StringUtils.isEmpty(officeId)){
        	params.put("userOverOfficeId", officeId);//启动流程的officeId
        }*/
		// TODO FHJ
		Map<String, Object> queryTheLeastBusyUserByMap = sysUserDao
				.queryTheLeastBusyUserByMap(params);

		SysUser sysUser = new SysUser();
		sysUser.setUserName((String) queryTheLeastBusyUserByMap.get("userName"));
		return sysUser;
	}

	@Override
	public Map<String, Object> queryTheUserWhoDidThisTask(String processInstanceId,
			String taskDef) throws ServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processInstanceId", processInstanceId);
		paramMap.put("taskDef", taskDef);
		Map user;
		try {
			user = (Map<String, Object>) sysUserDao.queryTheUserWhoDidThisTask(paramMap);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	@Override
	public SysUser getUserByRoleAndOfficeId(String roleName, Long officeId)
			throws ServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleName", roleName);
        paramMap.put("officeId", officeId);
        SysUser user = null;
        try {
        	user = sysUserDao.getUserByMap(paramMap);
        	
        	//如果查询不到直接领导,则去查询 当前营业厅 的客服经理
        	if(user == null){
        		SysOffice sysOffice = sysOfficeService.getBusinessHallByOrganizationId(officeId.toString()); 
        		if(sysOffice!=null){
        			paramMap.put("officeId", sysOffice.getId());
            		user = sysUserDao.getUserByMap(paramMap);
        		}
        	}
        	return user;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
	}

	@Override
	public List<Map<String, Object>> getUserInfo(Map<String, Object> params) throws ServiceException{
		List<Map<String, Object>> usersInfo;
		try{
			usersInfo = sysUserDao.getUserInfo(params);
		} catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
		return usersInfo;
	}

	/**************************** 工作流相关代码 end ***********************************/
	
	
}
