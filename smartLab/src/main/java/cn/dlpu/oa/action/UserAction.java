package cn.dlpu.oa.action;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import lombok.Data;
import org.apache.struts2.ServletActionContext;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Department;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Role;
import cn.dlpu.oa.domain.User;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.MD5Utils;
import cn.dlpu.oa.utils.PubFun;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletResponse;

@Data
@Namespace("/user")
@ParentPackage("json-default")
//@ParentPackage("struts-default")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	
	private List<User> userList = new ArrayList<User>();
	
	private List<Department> departmentList = new ArrayList<Department>();
	
	private List<Role> roleList = new ArrayList<Role>();
	
	private User user = new User();
	
	private int departmentId = 0;
	
	private Integer[] roleIdList = null;
	
	private String loginName = null;

	private boolean ok;
	
	private Page page;
	
	private int uid;
	/** 用于页面回显的导师id */
	private int supervisorId;
	/** 用于页面导师列表框的下拉列表选项 */
	private List<User> supervisorList = new ArrayList<User>();
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@Action(value = "findUserList",results = {
			@Result(name = SUCCESS,location = "/page/user/userList.jsp")
	})
	public String findUserList() {
		
		HQLHelper hh = new HQLHelper(User.class);
		User loginUser = PubFun.getLoginUser();
		if (!PubFun.isAdmin(loginUser)) {
			if (loginUser.getSupervisor().getId() == 1) {
				hh.buildWhere("id", PubFun.OR, loginUser.getId());
				hh.buildWhere("supervisor.id", PubFun.OR, loginUser.getId());
			} else {
				hh.buildWhere("id", PubFun.OR, loginUser.getSupervisor().getId());
				hh.buildWhere("supervisor.id", PubFun.OR, loginUser.getSupervisor().getId());
			}
		}
		hh.buildWhereNot("id", PubFun.AND, 1);
		hh.buildOrderBy("departmentId", true);
		hh.buildOrderBy("id", true);
		
		page = userService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		userList = page.getResultList();
		isNew = PubFun.NOT_NEW;
		return SUCCESS;
	}
	
	/**
	 * 根据id删除用户
	 * @return
	 */
	@Action(value = "delete",results = {
			@Result(name = SUCCESS,location = "/page/user/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String deleteById() {
		try {
			userService.deleteById(uid);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除用户信息出现异常！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 点击登录按钮
	 * @return
	 */
	@Action(value = "login", results = {
			@Result(name = SUCCESS,location = "/page/main/main.jsp",params = {}),
			@Result(name = ERROR,location = "/page/user/loginPage.jsp")
	})
	public String login() {
		
		session = ServletActionContext.getRequest().getSession();

		User user1 = PubFun.getLoginUser();
		if (user1 != null && user1.getLoginName().equals(this.user.getLoginName())) {
			Map session2 = ActionContext.getContext().getSession();
			session2.clear();
		}

		User user2 = new User();
		try {
			user2 = userService.login(this.user.getLoginName(), this.user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "加密算法出现异常,没有md5这个方法！");
			return ERROR;
		} catch (Exception e2) {
			e2.printStackTrace();
			session.setAttribute("errorMsg", "请输入用户名和密码！");
			return ERROR;
		}
		
		if (user2 == null) {
			session.setAttribute("errorMsg", "用户名或密码错误，请重新输入！");
			return ERROR;
		}
		
//		管理员用户（admin）单点登录
//		User user1 = PubFun.getLoginUser();
//		if (user1 != null && user1.getLoginName().equals(user2.getLoginName())) {
//			session.setAttribute("errorMsg", "用户已登录！");
//			return ERROR;
//		}

		session.setAttribute(PubFun.LOGIN_USER, user2);
		return SUCCESS;
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@Action(value = "logout", results = {
			@Result(name = SUCCESS,location = "/page/user/loginPage.jsp",params = {})
	})
	public String logout() {
		Map session2 = ActionContext.getContext().getSession();
		if (session2 != null) {
			session2.clear();
//			session.invalidate();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至登录界面
	 * @return
	 */
	@Action(value = "loginPage", results = {
			@Result(name = SUCCESS,location = "/page/user/loginPage.jsp",params = {})
	})
	public String loginPage() {
		if (session != null) {
			session.invalidate();
			session.removeAttribute("errorMsg");
			session.removeAttribute(PubFun.LOGIN_USER);
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至新建用户页面
	 * @return
	 */
	@Action(value = "registerPage",results = {
			@Result(name = SUCCESS,location = "/page/user/registerPage.jsp")
	})
	public String registerPage() {
		//准备部门树形列表数据
		List<Department> list = departmentService.findTopList();
		departmentList = PubFun.buildTreeList(list, 0);
		//准备岗位列表数据
		roleList = roleService.findRoleList();
		//导师信息列表
//		supervisorList = userService.findSupervisorList();
		supervisorList = userService.findUserList();
		User loginUser = PubFun.getLoginUser();//得到登录用户，为下一步判断做准备
		if ("admin".equals(loginUser.getLoginName()) || "admin" == loginUser.getLoginName()) {
			departmentId = 0;//如果是超级管理员登录，部门列表无默认选中项
			supervisorId = 0;//如果是超级管理员登录，导师列表无默认选中项
		} else {
			departmentId = loginUser.getDepartment().getId();//如果是实验室管理员登录，则部门默认选中该实验室管理员所属部门
			supervisorId = loginUser.getSupervisor().getId();//如果是实验室管理员登录，则部门默认选中该实验室管理员的导师
		}
		roleIdList = null;
		user = new User();
		return SUCCESS;
	}
	
	/**
	 * 点击保存按钮（保存用户信息）
	 * @return
	 */
	@Action(value = "register",results = {
			@Result(name = SUCCESS,location = "/page/user/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String register() {
		if (supervisorId == 0) {
			user.setSupervisor(userService.findById(1));
		} else {
			user.setSupervisor(userService.findById(supervisorId));
		}
		if (user.getId() != 0) {//修改用户信息
			try {
				User user2 = userService.findById(user.getId());
				user2.setLoginName(user.getLoginName());
				user2.setRealName(user.getRealName());
				user2.setEmail(user.getEmail());
				user2.setGender(user.getGender());
				user2.setPhone(user.getPhone());
				user2.setDescription(user.getDescription());
				user2.setSupervisor(user.getSupervisor());
				
				if (roleIdList != null && roleIdList.length != 0) {
					List<Role> roles = roleService.findByIds(roleIdList);
					user2.setRoles(new HashSet<Role>(roles));
				} else {
					user2.setRoles(null);
				}
				
				if (departmentId != 0) {
					user2.setDepartment(departmentService.findById(departmentId));
				} else {
					user2.setDepartment(null);
				}
				userService.update(user2);
				if (null == page) {
					pageNo = 1;
				} else {
					pageNo = page.getPageNo();
				}
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改用户信息出现异常！");
				return ERROR;
			}
		} else {//新建用户
			try {
				if (departmentId != 0) {
					user.setDepartment(departmentService.findById(departmentId));
				}
				if (roleIdList != null && roleIdList.length != 0) {
					List<Role> roles = roleService.findByIds(roleIdList);
					user.setRoles(new HashSet<Role>(roles));
				}
				user.setPassword(MD5Utils.md5("1234"));
				userService.register(user);
				isNew = PubFun.IS_NEW;
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setError(e.toString());
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setMessage("新建用户信息出现异常！");
				return ERROR;
			}
		}
		
	}
	
	/**
	 * 管理员跳转至用户信息修改页面
	 * @return
	 */
	@Action(value = "editUserPage",results = {
			@Result(name = SUCCESS,location = "/page/user/registerPage.jsp")
	})
	public String editUserPage() {
		user = userService.findById(uid);
		if (user.getDepartment() == null) {
			departmentId = 0;
		} else {
			departmentId = user.getDepartment().getId();
		}
		departmentList = PubFun.buildTreeList(departmentService.findTopList(), 0);
		
		if (user.getSupervisor() == null) {
			supervisorId = 0;
		} else {
			supervisorId = user.getSupervisor().getId();
		}
		supervisorList = userService.findSupervisorList();
		
		roleList = roleService.findRoleList();
		List<Role> roles = new ArrayList<Role>(user.getRoles());
		roleIdList = new Integer[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			roleIdList[i] = roles.get(i).getId();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化密码
	 * @return
	 */
	@Action(value = "initPwd",results = {
			@Result(name = SUCCESS,location = "/page/user/userList.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String initPwd() {
		User user = userService.findById(uid);
		try {
			user.setPassword(MD5Utils.md5(PubFun.INIT_PWD));
			userService.update(user);
			return SUCCESS;
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e1.toString());
			exceptionMessage.setMessage("初始化密码时MD5加密出现异常！");
		} catch (Exception e2) {
			e2.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e2.toString());
			exceptionMessage.setMessage("初始化密码出现异常！");
		}
		return ERROR;
	}


	/**
	 * 登录名唯一性验证
	 * @return
	 */
//	@ParentPackage(value = "json-default")
	@Action(value = "validLoginName",results = {
			@Result(name = "ajaxResult",type = "json",params = {"data", "data"})
	})
	public String validLoginName() {
		User user = userService.findByLName(model.getLoginName());
		HttpServletResponse response = ServletActionContext.getResponse();

		if (user == null || user.equals(null)) {
			ok = true;
		} else {
			ok = false;
		}
		model.setDescription(ok + "");
		return "ajaxResult";
	}





	
	/* get and set method */
//	public List<User> getUserList() {
//		return userList;
//	}
//
//	public void setUserList(List<User> userList) {
//		this.userList = userList;
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
//	public Integer[] getRoleIdList() {
//		return roleIdList;
//	}
//
//	public void setRoleIdList(Integer[] roleIdList) {
//		this.roleIdList = roleIdList;
//	}
//
//	@JSON
//	public boolean isOk() {
//		return ok;
//	}
//
//	public void setOk(boolean ok) {
//		this.ok = ok;
//	}
//
//	public String getLoginName() {
//		return loginName;
//	}
//
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//
//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}
//
//	public int getUid() {
//		return uid;
//	}
//
//	public void setUid(int uid) {
//		this.uid = uid;
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

