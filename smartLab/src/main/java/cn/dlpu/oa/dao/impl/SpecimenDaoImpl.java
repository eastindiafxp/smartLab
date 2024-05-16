package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.ISpecimenDao;
import cn.dlpu.oa.domain.Specimen;

public class SpecimenDaoImpl extends BaseDaoImpl<Specimen> implements ISpecimenDao {

	
	@Override
	public List<Specimen> findAvaiSpecimenList() {
		String hql = "select s from Specimen s where s.record = ?";
		Query query = getSession().createQuery(hql).setParameter(0, null);
		List<Specimen> list = query.list();
		return list;
	}

	@Override
	public Specimen findByLabel(String label2) {
		String hql = "select s from Specimen s where s.label = ?";
		Query query = getSession().createQuery(hql).setParameter(0, label2);
		Specimen spec = (Specimen) query.uniqueResult();
		return spec;
	}

	
	@Override
	public List<Specimen> findMySpecimenList(int id) {
		String hql = "select s from Specimen s where s.owner.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, id);
		return query.list();
	}

}
