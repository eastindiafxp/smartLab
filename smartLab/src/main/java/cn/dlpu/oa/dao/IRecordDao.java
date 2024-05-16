package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Record;

public interface IRecordDao extends IBaseDao<Record> {

	public List<Record> findByEptId(int experimentId);

	public Record findByRecNoEptId(String recordNo, int experimentId);

	public List<Record> findByCondition(String condition);

}
