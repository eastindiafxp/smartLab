package cn.dlpu.oa.action;

import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Department;
import cn.dlpu.oa.utils.PubFun;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 部门管理
 * @author 樊晓璞 v1.0 2015-08-01
 *
 */

@Namespace("/department")
@ParentPackage("struts-default")
public class DepartmentAction extends BaseAction<Department> {
	
	private static final long serialVersionUID = 1L;
	
	/** 传给修改/新建页面中上级部门下拉列表框的值或者传给部门列表页面要循环显示的列表 */
	private List<Department> departmentList;
	/** 传给修改/新建页面的对象或者从该页面表单回传的对象 */
	private Department department;
	/** 传给页面或者从页面回传的上级列表的id值 */
	private int parentId;
	
	/*
	 * 以下findTopList()和findChildrenList()这两个方法最好不要合并，
	 * 否则在打开系统后二次以上进入此菜单时，显示的可能不是最上层的部门列表。
	 * 暂时还没有发现别的解决此问题的办法，只能将其分开成为两个方法。
	 */
	/**
	 * 查询最上一级的部门列表
	 * @return
	 */
	@Action(value = "findTopDepartList",results = {
			@Result(name = SUCCESS,location = "/page/department/departmentList.jsp")
	})
	public String findTopList() {
		departmentList = departmentService.findTopList();
		return SUCCESS;
	}
	
	/**
	 * 查询下级部门列表
	 */
	@Action(value = "findChildrenDepartList",results = {
			@Result(name = SUCCESS,location = "/page/department/departmentList.jsp")
	})
	public String findChildrenList() {
		
		if (parentId == 0) {
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildrenList(parentId);
		}
		return SUCCESS;
	}
	
	/**
	 * 返回上级部门列表
	 */
	@Action(value = "lastLevel",results = {
			@Result(name = SUCCESS,location = "/page/department/departmentList.jsp")
	})
	public String toLastLevel() {
		if (model.getId() == 0) {//已经处于最上级列表时点击返回上一层按钮
			departmentList = departmentService.findTopList();
		} else {//处于非最上层列表时点击返回上一层按钮
			Department dept = departmentService.findById(model.getId());
			if (null == dept.getParent() ) {//处于次级列表（二级列表）时点击返回上一层按钮
				departmentList = departmentService.findTopList();
				parentId = 0;//若没有这一行，则处于一级列表时点击新建按钮，新建页面上的默认上级部门将不会是0（即“请选择部门”）
			} else {//处于二级以下级别列表时点击返回上一层按钮
				departmentList = departmentService.findChildrenList(dept.getParent().getId());
				parentId = dept.getParent().getId();//及时更新parentId的值
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除部门
	 * @return
	 */
	@Action(value = "deleteById",results = {
			@Result(name = SUCCESS,location = "/page/department/departmentList.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String deleteById() {
		try {
			departmentService.delete(model);
			if (null == model.getParent()) {
				departmentList = departmentService.findTopList();
			} else {
				departmentList = departmentService.findChildrenList(model.getParent().getId());
			}
//			System.out.println(departmentList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("删除部门信息出现异常！");
			return ERROR;
		}
	}
	
	/**
	 * 跳转至新建部门页面
	 * @return
	 */
	@Action(value = "createNewDepart",results = {
			@Result(name = SUCCESS,location = "/page/department/editPage.jsp")
	})
	public String createNewDepart() {
		departmentList = PubFun.buildTreeList(departmentService.findTopList(), 0);
		department = new Department();
		return SUCCESS;
	}
	
	/**
	 * 跳转至修改部门信息页面
	 */
	@Action(value = "editDepartmentPage",results = {
			@Result(name = SUCCESS,location = "/page/department/editPage.jsp")
	})
	public String editDepartmentPage() {
		
		departmentList = PubFun.buildTreeList(departmentService.findTopList(), model.getId());
		department = departmentService.findById(model.getId());
		
		if (null == department.getParent()) {
			parentId = 0;
		} else {
			parentId = department.getParent().getId();
		}
		return SUCCESS;
	}
	
	/**
	 * 保存新建或修改的部门信息
	 * @return
	 * @throws Exception 
	 */
	@Action(value = "save",results = {
			@Result(name = SUCCESS,location = "/page/department/editSuccess.jsp"),
			@Result(name = ERROR,location = "/page/publicPage/Failure.jsp")
	})
	public String saveDepart() {
		Department department2;
		Department parent;
		//新建部门
		if (department.getId() == 0) {
			try {
				parent = new Department();
				if (0 == parentId) {
					parent = null;
				} else {
					parent = departmentService.findById(parentId);
				}
				department.setParent(parent);
				departmentService.save(department);
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("新建部门信息出现异常！");
				return ERROR;
			}
			//修改部门信息
		} else {
			try {
				department2 = departmentService.findById(department.getId());
				if (department.getName() != department2.getName()) {
					department2.setName(department.getName());
				}
				if (department.getDescription() != department2.getDescription()) {
					department2.setDescription(department.getDescription());
				}
				if ((null == department2.getParent() && 0 != parentId) || //修改前属于最上层部门，要将其修改为非最上层部门的情况
						(null != department2.getParent() && department2.getParent().getId() != parentId)) {//修改前并非最上层部门的情况
					parent = departmentService.findById(parentId);
					department2.setParent(parent);
				}
				departmentService.updateDepartment(department2);
				if (department2.getParent() != null) {
					departmentList = departmentService.findChildrenList(department2.getParent().getId());
				} else {
					departmentList = departmentService.findTopList();
				}
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				exceptionMessage.setClassName(this.getClass().getName());
				exceptionMessage.setError(e.toString());
				exceptionMessage.setMessage("修改部门信息出现异常！");
				return ERROR;
			}
		}
		
	}
	
	/* set and get method */
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
