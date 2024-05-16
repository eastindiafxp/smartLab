package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IDepartmentDao;
import cn.dlpu.oa.domain.Department;


public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements IDepartmentDao {

	@Override
	public List<Department> findTopList() {
		String hql = "FROM Department D WHERE D.parent IS NULL";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Department> findChildrenList(int parentId) {
		String hql = "from Department d where d.parent.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, parentId);
		return query.list();
	}
	
}
