package cn.dlpu.oa.action;

import java.util.ArrayList;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.*;
import cn.dlpu.oa.utils.MD5Utils;
import cn.dlpu.oa.utils.PubFun;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;

/**
 * 系统配置管理
 * @author 樊晓璞 v1.0 2015-09-06
 *
 */
@Namespace("/set")
@ParentPackage("struts-default")
@Getter
@Setter
public class SetAction extends BaseAction<SysConfig> {

	private static final long serialVersionUID = 1L;
	
	private List<SysConfig> pageSetList = new ArrayList<SysConfig>();
	
	private List<SysConfig> sessionSetList = new ArrayList<SysConfig>();
	
	private SysConfig sysConfig;
	
	private SysConfigId sysConfigId;
	
	private String configName;
	
	private String configValue;
	
	private int num;
	
	private String des;
	
	private User user;
	
	private int departmentId;
	
	private List<Department> departmentList = new ArrayList<Department>();
	
	private List<Role> roleList = new ArrayList<Role>();
	
	private Integer[] roleIdList = null;
	
	private String oldPwd;
	
	private String newPwd;
	
	private String newPwd2;
	
	private boolean ok = false;
	/** 从页面的请求命令中接收的参数，用来判断是否需要更新登录密码 */
	private boolean isModifyPwd;
	/** 用于页面回显的导师id */
	private int supervisorId;
	/** 用于页面导师列表框的下拉列表选项 */
	private List<User> supervisorList = new ArrayList<User>();
	
	/**
	 * 查询页面信息设置列表
	 * @return
	 */
	@Action(value = "basicSet",results = {
			@Result(name = SUCCESS,location = "/page/set/basSet.jsp")
	})
	public String findBasicSetList() {
		
//		查询页面配置信息列表
		pageSetList = sysConfigService.findPageSetList();
//		查询session配置信息列表
		sessionSetList =sysConfigService.findSessionSetList();
		
		return SUCCESS;
	}
	
	/**
	 * 跳转至个人信息修改页面
	 * @return
	 */
	@Action(value = "personSet",results = {
			@Result(name = SUCCESS,location = "/page/set/personInfoPage.jsp")
	})
	public String findPersonInfoPage() {

		user = PubFun.getLoginUser();
		
		User user2 = userService.findById(user.getId());
		user = user2;
		if (user2.getDepartment() == null) {
			departmentId = 0;
		} else {
			departmentId = user2.getDepartment().getId();
		}
		
		if (user.getSupervisor() == null) {
			supervisorId = 0;
		} else {
			supervisorId = user.getSupervisor().getId();
		}
		supervisorList = userService.findSupervisorList();
		
		List<Department> topList = departmentService.findTopList();
		departmentList = PubFun.buildTreeList(topList, 0);
		
		roleList = roleService.findRoleList();
		
		List<Role> roles = new ArrayList<Role>(user2.getRoles());
		roleIdList = new Integer[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			roleIdList[i] = roles.get(i).getId();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 修改密码时验证旧密码是否正确
	 * @return
	 */
//	@Action(value = "validOldPwd",results = {
//			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
//	})
	public String validOldPwd() {
		
		try {
			String oldPwd$ = MD5Utils.md5(oldPwd);
			if (oldPwd$ == user.getPassword() || oldPwd$.equals(user.getPassword())) {
				ok = true;
			} else {
				ok = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("验证旧密码时出现异常！");
		}
		model.setReverse1(ok + "");
		return "ajaxResult";
	}


	/**
	 * 用户修改个人信息后点击保存按钮
	 * @return
	 */
	@Action(value = "savePInfo",results = {
			@Result(name = SUCCESS,location = "/page/set/editSuccess.jsp")
	})
//	@Action(value = "savePInfo",results = {
//			@Result(name = SUCCESS,type = "chain", location = "personSet")
//	})
	public String updatePersonInfo() {
		try {
			User user2 = userService.findById(user.getId());
			user2.setRealName(user.getRealName());
			user2.setEmail(user.getEmail());
			user2.setGender(user.getGender());
			user2.setPhone(user.getPhone());
			user2.setDescription(user.getDescription());
			//判断是否需要更新密码，若isModifyPwd为true，则更新，否则不更新
			if (isModifyPwd == true) {
				user2.setPassword(MD5Utils.md5(newPwd));
			}
			userService.update(user2);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("保存用户信息出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 恢复默认设置
	 * @return
	 */
	@Action(value = "resetBasicSet",results = {
			@Result(name = SUCCESS,type = "chain", location = "basicSet")
	})
	public String resetBasicSet() {
		
		sysConfig = sysConfigService.findByConfigName(configName);
		sysConfigId = sysConfig.getId();
		if ("PageSize1" == configName || "PageSize1".equals(configName)) {
			sysConfigId.setConfigValue(PubFun.DEFAULT_PAGE_SIZE);
		} else if ("PageSize2" == configName || "PageSize2".equals(configName)) {
			sysConfigId.setConfigValue(PubFun.DEFAULT_PAGE_SIZE);
		} else if ("SessionTimeOut" == configName || "SessionTimeOut".equals(configName)) {
			sysConfigId.setConfigValue(PubFun.DEFAULT_SESSION_TIMEOUT);
		}
		sysConfig.setId(sysConfigId);
		try {
			sysConfigService.updatePageSet(sysConfig);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("初始化信息时出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 保存更改的配置值
	 * @return
	 */
	@Action(value = "savePageSet",results = {
//			@Result(name = SUCCESS,type = "redirectAction", action = "anotherAction"),
			@Result(name = SUCCESS,type = "chain", location = "basicSet")
	})
	public String saveBasicSet() {
		
		try {
			sysConfig = sysConfigService.findByConfigName(configName);
			sysConfigId = sysConfig.getId();
			sysConfigId.setConfigValue(Integer.toString(num));
			sysConfig.setId(sysConfigId);
			sysConfigService.updatePageSet(sysConfig);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("更改信息时出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}

	/* getter and setter method */
//	public List<SysConfig> getPageSetList() {
//		return pageSetList;
//	}
//
//	public void setPageSetList(List<SysConfig> pageSetList) {
//		this.pageSetList = pageSetList;
//	}
//
//	public SysConfig getSysConfig() {
//		return sysConfig;
//	}
//
//	public void setSysConfig(SysConfig sysConfig) {
//		this.sysConfig = sysConfig;
//	}
//
//	public String getConfigName() {
//		return configName;
//	}
//
//	public void setConfigName(String configName) {
//		this.configName = configName;
//	}
//
//	public SysConfigId getSysConfigId() {
//		return sysConfigId;
//	}
//
//	public void setSysConfigId(SysConfigId sysConfigId) {
//		this.sysConfigId = sysConfigId;
//	}
//
//	public String getConfigValue() {
//		return configValue;
//	}
//
//	public void setConfigValue(String configValue) {
//		this.configValue = configValue;
//	}
//
//	public int getNum() {
//		return num;
//	}
//
//	public void setNum(int num) {
//		this.num = num;
//	}
//
//	public String getDes() {
//		return des;
//	}
//
//	public void setDes(String des) {
//		this.des = des;
//	}
//
//	public List<SysConfig> getSessionSetList() {
//		return sessionSetList;
//	}
//
//	public void setSessionSetList(List<SysConfig> sessionSetList) {
//		this.sessionSetList = sessionSetList;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public int getDepartmentId() {
//		return departmentId;
//	}
//
//	public void setDepartmentId(int departmentId) {
//		this.departmentId = departmentId;
//	}
//
//	public List<Department> getDepartmentList() {
//		return departmentList;
//	}
//
//	public void setDepartmentList(List<Department> departmentList) {
//		this.departmentList = departmentList;
//	}
//
//	public List<Role> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<Role> roleList) {
//		this.roleList = roleList;
//	}
//
//	public Integer[] getRoleIdList() {
//		return roleIdList;
//	}
//
//	public void setRoleIdList(Integer[] roleIdList) {
//		this.roleIdList = roleIdList;
//	}
//
//	public String getOldPwd() {
//		return oldPwd;
//	}
//
//	public void setOldPwd(String oldPwd) {
//		this.oldPwd = oldPwd;
//	}
//
//	public String getNewPwd() {
//		return newPwd;
//	}
//
//	public void setNewPwd(String newPwd) {
//		this.newPwd = newPwd;
//	}
//
//	public String getNewPwd2() {
//		return newPwd2;
//	}
//
//	public void setNewPwd2(String newPwd2) {
//		this.newPwd2 = newPwd2;
//	}
//
//	public boolean isOk() {
//		return ok;
//	}
//
//	public void setOk(boolean ok) {
//		this.ok = ok;
//	}
//
//	public boolean isIsModifyPwd() {
//		return isModifyPwd;
//	}
//
//	public void setIsModifyPwd(boolean isModifyPwd) {
//		this.isModifyPwd = isModifyPwd;
//	}
//
//	public boolean isModifyPwd() {
//		return isModifyPwd;
//	}
//
//	public void setModifyPwd(boolean isModifyPwd) {
//		this.isModifyPwd = isModifyPwd;
//	}
//
//	public int getSupervisorId() {
//		return supervisorId;
//	}
//
//	public void setSupervisorId(int supervisorId) {
//		this.supervisorId = supervisorId;
//	}
//
//	public List<User> getSupervisorList() {
//		return supervisorList;
//	}
//
//	public void setSupervisorList(List<User> supervisorList) {
//		this.supervisorList = supervisorList;
//	}

}
