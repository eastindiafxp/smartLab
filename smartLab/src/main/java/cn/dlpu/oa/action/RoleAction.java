package cn.dlpu.oa.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Privilege;
import cn.dlpu.oa.domain.Role;
import cn.dlpu.oa.service.IUserService;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 岗位管理
 * @author 樊晓璞 v1.0 2015-7-31
 *
 */
@Namespace("/role")
@ParentPackage("struts-default")
@Getter
@Setter
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;

//	IUserService userService;

	/** 岗位列表 */
	private List<Role> roleList = null;
	/** 根据id查询到的岗位详细信息或修改页面传递过来的岗位信息 */
	private Role role = null;
	private Integer roleId = null;
	/** 传给页面的权限列表 */
	List<Privilege> privilegeList = new ArrayList<Privilege>();
	/** 接收页面传回的权限id列表或者传给页面回显的权限id列表 */
	private Integer[] privilegeIds = null;
	/** 用于存储分页信息的类 */
	private Page page;

	private Integer pageNo2;
	private Integer pageNo3;

	/**
	 * 查询岗位信息列表
	 * @return
	 */
//	
	@Action(value = "findRoleList",results = {
			@Result(name = SUCCESS,location = "/page/role/roleList.jsp")
	})
	public String findRoleList(){
//		roleList = roleService.findRoleList();

		HQLHelper hh = new HQLHelper(Role.class);
		page = roleService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		roleList = page.getResultList();
		return SUCCESS;
	}
	
	/**
	 * 根据id删除岗位列表中的岗位信息
	 * @return
	 */
	@Action(value = "deleteById",results = {
			@Result(name = SUCCESS,location = "/page/role/roleList.jsp")
	})
	public String deleteById(){
		
		try {
//			roleService.deleteById(id);
			roleService.delete(model);

			if (null != pageNo2 && pageNo2 != 0) {
				pageNo = pageNo2;
			}
			HQLHelper hh = new HQLHelper(Role.class);
			page = roleService.getPage(hh, pageNo, isNew);
			roleList = page.getResultList();
//			pageNo = PubFun.DEFAULT_PAGE_NO;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除岗位信息出现异常！");
			return ERROR;
		}
	}

	/**
	 * 跳转至新建岗位的页面
	 * @return
	 */
	@Action(value = "createNewRole",results = {
			@Result(name = SUCCESS,location = "/page/role/editPage.jsp")
	})
	public String createNewRole() {
		role = new Role();
		return SUCCESS;
	}

	/**
	 * 根据id跳转至修改岗位信息的详细页面
	 * @return
	 */
	@Action(value = "editRolePage",results = {
			@Result(name = SUCCESS,location = "/page/role/editPage.jsp")
	})
	public String editRolePage() {
		role = roleService.findRole(model);
//		pageNo = page.getPageNo();
		return SUCCESS;
	}
	
	/**
	 * 保存更改的岗位信息
	 * @return
	 * @throws Exception 
	 */
	@Action(value = "saveRole",results = {
			@Result(name = SUCCESS,location = "/page/role/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveRole() {

//		HQLHelper hh = new HQLHelper(Role.class);
//		page = roleService.getPage(hh, pageNo, isNew);
//		pageNo = PubFun.DEFAULT_PAGE_NO;
//		roleList = page.getResultList();

		if (0 != role.getId()) {
			try {
				Role role2 = roleService.findById(role.getId());
				if (!role2.getName().equals(role.getName())) {
					role2.setName(role.getName());
				}
				if (!role2.getDescription().equals(role.getDescription())) {
					role2.setDescription(role.getDescription());
				}
				roleService.updateRole(role2);
				isNew = PubFun.NOT_NEW;
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改岗位信息出现异常！");
				return ERROR;
			}
		} else {
			try {
				roleService.saveNewRole(role);
				isNew = PubFun.IS_NEW;
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("新建岗位信息出现异常！");
				return ERROR;
			}
		}
	}

	
	/**
	 * 跳转至设置权限页面
	 * @return
	 */
	@Action(value = "setPrivilegePage",results = {
			@Result(name = SUCCESS,location = "/page/role/privilegePage.jsp")
	})
	public String setPrivilegePage() {
		
		//传给页面的岗位信息
		role = roleService.findRole(model);
		//需要在页面显示的权限列表
//		privilegeList = privilegeService.findAllPri();
		privilegeList = privilegeService.findTopPri();
		//查询当前岗位对应的权限，用于页面回显
		privilegeIds = new Integer[role.getPrivileges().size()];
		if (role.getPrivileges() != null && role.getPrivileges().size() != 0) {
			List<Privilege> pList = new ArrayList<Privilege>(role.getPrivileges());
			for (int i = 0; i < pList.size(); i++) {
				privilegeIds[i] = pList.get(i).getId();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 保存更改的权限信息
	 * @return
	 */
	@Action(value = "setPrivilege",results = {
			@Result(name = SUCCESS,location = "/page/role/privilegePage.jsp"),
//			@Result(name = SUCCESS,type = "chain", location = "medicineManagePage"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String setPrivilege() {
		
//		Role role2 = roleService.findById(role.getId());
		Role role2 = roleService.findById(roleId);
		try {
			if (privilegeIds != null && privilegeIds.length != 0) {
				List<Privilege> privilegeList2 = privilegeService.findByIds(privilegeIds);
//				privilegeList = privilegeService.findByIds(privilegeIds);
				role2.setPrivileges(new HashSet<Privilege>(privilegeList2));
			} else {
				role2.setPrivileges(null);
			}
			roleService.update(role2);
			role = role2;
			privilegeList = privilegeService.findTopPri();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("更新权限信息时出现异常！");
			return ERROR;
		}
		
	}

	/* setter and getter method */
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
