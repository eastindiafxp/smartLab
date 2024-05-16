package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IDepartmentDao;
import cn.dlpu.oa.domain.Department;
import cn.dlpu.oa.service.IDepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	private IDepartmentDao departmentDao;
	
	@Override
	public List<Department> findAllList() {
		return departmentDao.findAll();
	}
	
	public IDepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public void delete(Department model) throws Exception {
		departmentDao.deleteById(model.getId());
	}

	@Override
	public Department findById(int id) {
		return departmentDao.findById(id);
	}

	@Override
	public void save(Department department) throws Exception {
		departmentDao.save(department);
	}

	@Override
	public void updateDepartment(Department department) throws Exception {
		departmentDao.update(department);
	}

	@Override
	public List<Department> findTopList() {
		return departmentDao.findTopList();
	}

	@Override
	public List<Department> findChildrenList(int parentId) {
		return departmentDao.findChildrenList(parentId);
	}

}
