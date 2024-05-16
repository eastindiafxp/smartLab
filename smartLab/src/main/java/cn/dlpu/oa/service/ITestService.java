package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Test;
import cn.dlpu.oa.utils.HQLHelper;

public interface ITestService {

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

	public Test findById(int testId);

	public void save(Test test) throws Exception;

	public void update(Test test) throws Exception;

	public void deleteById(int testId) throws Exception;

	public List<Test> findByIds(Integer[] testIdList);

}
