package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Department;

public interface IDepartmentService {

	public List<Department> findAllList();

	public void delete(Department model) throws Exception;

	public Department findById(int id);

	public void save(Department department) throws Exception;

	public void updateDepartment(Department department) throws Exception;

	public List<Department> findTopList();

	public List<Department> findChildrenList(int parentId);

}
