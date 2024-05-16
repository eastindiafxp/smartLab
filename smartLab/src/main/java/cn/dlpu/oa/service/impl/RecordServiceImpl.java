package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IRecordDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Record;
import cn.dlpu.oa.service.IRecordService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements IRecordService {

	private IRecordDao recordDao;
	
	@Override
	public List<Record> findRecordList(int experimentId) {
		List<Record> recordList = recordDao.findByEptId(experimentId);
		return recordList;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public Record findById(int recordId) {
		Record record = recordDao.findById(recordId);
		return record;
	}

	@Override
	public void save(Record record) throws Exception {
		recordDao.save(record);
	}

	@Override
	public void update(Record record2) throws Exception {
		recordDao.update(record2);
	}

	@Override
	public Record findByRecNoEptId(String recordNo, int experimentId) {
		Record r = recordDao.findByRecNoEptId(recordNo, experimentId);
		return r;
	}

	@Override
	public void deleteById(int recordId) throws Exception {
		recordDao.deleteById(recordId);
	}

	@Override
	public List<Record> findByCondition(String condition) {
		List<Record> list = recordDao.findByCondition(condition);
		return list;
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = recordDao.getPage(hh, pageNo, isNew);
		return page;
	}

	@Override
	public List<Record> findByIds(Integer[] recordIdList) {
		List<Record> list = recordDao.findByIds(recordIdList);
		return list;
	}

}
