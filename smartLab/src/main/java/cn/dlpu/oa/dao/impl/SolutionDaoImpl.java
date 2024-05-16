package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.ISolutionDao;
import cn.dlpu.oa.domain.Solution;

public class SolutionDaoImpl extends BaseDaoImpl<Solution> implements ISolutionDao {

	
	@Override
	public List<Solution> findMySolutionList(int ownerId) {
		String hql = "select s from Solution s where s.owner.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, ownerId);
		return query.list();
	}

}
