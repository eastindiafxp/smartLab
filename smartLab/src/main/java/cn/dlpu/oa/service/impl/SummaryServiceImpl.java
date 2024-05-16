package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ISummaryDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.StageSummary;
import cn.dlpu.oa.service.ISummaryService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements ISummaryService {

	private ISummaryDao summaryDao;
	
	@Override
	public List<StageSummary> findSummaryList() {
		List<StageSummary> list = summaryDao.findAll();
		return list;
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = summaryDao.getPage(hh, pageNo, isNew);
		return page;
	}
	
	@Override
	public StageSummary findById(int summaryId) {
		StageSummary ss = summaryDao.findById(summaryId);
		return ss;
	}
	
	
	public ISummaryDao getSummaryDao() {
		return summaryDao;
	}
	
	public void setSummaryDao(ISummaryDao summaryDao) {
		this.summaryDao = summaryDao;
	}

	@Override
	public void update(StageSummary summary) throws Exception {
		summaryDao.update(summary);
	}

	@Override
	public void save(StageSummary summary) throws Exception {
		summaryDao.save(summary);
	}

	@Override
	public List<StageSummary> findByIds(Integer[] summaryIdList) {
		List<StageSummary> list = summaryDao.findByIds(summaryIdList);
		return list;
	}

	@Override
	public void deleteById(int summaryId) throws Exception {
		summaryDao.deleteById(summaryId);
	}
	
}
