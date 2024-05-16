package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IProjectDao;
import cn.dlpu.oa.domain.Project;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements IProjectDao {

	@Override
	public Project findByProjNo(String projNo) {
		String hql = "select p from Project p where p.projNo = ?";
		Query query = getSession().createQuery(hql).setParameter(0, projNo);
		Project proj = (Project) query.uniqueResult();
		return proj;
	}

	
	@Override
	public List<Project> findMyProject(int id) {
		String hql = "select p from Project p where p.claimer.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, id);
		return query.list();
	}
	
}
