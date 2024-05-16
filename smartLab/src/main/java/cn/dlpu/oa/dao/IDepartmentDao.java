package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Department;

public interface IDepartmentDao extends IBaseDao<Department> {

	public List<Department> findTopList();

	public List<Department> findChildrenList(int parentId);

}
