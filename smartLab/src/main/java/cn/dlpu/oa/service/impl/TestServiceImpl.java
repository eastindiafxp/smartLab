package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ITestDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Test;
import cn.dlpu.oa.service.ITestService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {

	private ITestDao testDao;
	
	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = testDao.getPage(hh, pageNo, isNew);
		return page;
	}

	
	public ITestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}


	@Override
	public Test findById(int testId) {
		Test test = testDao.findById(testId);
		return test;
	}


	@Override
	public void save(Test test) throws Exception {
		testDao.save(test);
	}


	@Override
	public void update(Test test) throws Exception {
		testDao.update(test);
	}


	@Override
	public void deleteById(int testId) throws Exception {
		testDao.deleteById(testId);
	}


	@Override
	public List<Test> findByIds(Integer[] testIdList) {
		List<Test> list = testDao.findByIds(testIdList);
		return list;
	}

}
