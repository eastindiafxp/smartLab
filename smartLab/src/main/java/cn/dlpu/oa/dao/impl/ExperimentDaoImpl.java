package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IExperimentDao;
import cn.dlpu.oa.domain.Experiment;

public class ExperimentDaoImpl extends BaseDaoImpl<Experiment> implements IExperimentDao {

	
	@Override
	public List<Experiment> findByProjId(int projectId) {
		String hql = "select e from Experiment e where e.project.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, projectId);
		List<Experiment> experimentList = query.list();
		return experimentList;
	}

	@Override
	public Experiment findByEptNoProjId(String experimentNo, int projectId) {
		String hql = "select e from Experiment e where e.project.id = ? and e.eptNo = ?";
		Query query = getSession().createQuery(hql).setParameter(0, projectId).setParameter(1, experimentNo);
		Experiment ept = (Experiment) query.uniqueResult();
		return ept;
	}

}
