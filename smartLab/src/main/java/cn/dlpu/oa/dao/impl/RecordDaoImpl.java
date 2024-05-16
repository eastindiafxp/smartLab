package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IRecordDao;
import cn.dlpu.oa.domain.Record;

public class RecordDaoImpl extends BaseDaoImpl<Record> implements IRecordDao {

	
	@Override
	public List<Record> findByEptId(int experimentId) {
		String hql = "select r from Record r where r.experiment.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, experimentId);
		List<Record> recordList = query.list();
		return recordList;
	}

	@Override
	public Record findByRecNoEptId(String recordNo, int experimentId) {
		String hql = "select r from Record r where r.experiment.id = ? and r.recordNo = ?";
		Query query = getSession().createQuery(hql).setParameter(0, experimentId).setParameter(1, recordNo);
		Record r = (Record) query.uniqueResult();
		return r;
	}

	
	@Override
	public List<Record> findByCondition(String condition) {
		Query query = getSession().createQuery(condition);
		return query.list();
	}

}
