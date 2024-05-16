package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Record;
import cn.dlpu.oa.utils.HQLHelper;

public interface IRecordService {

	public List<Record> findRecordList(int experimentId);

	public Record findById(int recordId);

	public void save(Record record) throws Exception;

	public void update(Record record2) throws Exception;

	public Record findByRecNoEptId(String recordNo, int experimentId);

	public void deleteById(int recordId) throws Exception;

	public List<Record> findByCondition(String condition);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

	public List<Record> findByIds(Integer[] recordIdList);

}
