package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IMedicineDao;
import cn.dlpu.oa.domain.Medicine;

public class MedicineDaoImpl extends BaseDaoImpl<Medicine> implements IMedicineDao {

	
	@Override
	public List<Medicine> findMyMediList(int id) {
		String hql = "select m from Medicine m where m.owner.id = ?";
		Query query  = getSession().createQuery(hql).setParameter(0, id);
		return query.list();
	}

}
