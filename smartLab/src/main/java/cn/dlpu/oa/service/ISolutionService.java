package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Solution;
import cn.dlpu.oa.utils.HQLHelper;

public interface ISolutionService {

	public List<Solution> findSolutionList();

	public List<Solution> findByIds(Integer[] solutionIdList);

	public Solution findById(int solutionId);

	public void save(Solution solution) throws Exception;

	public void update(Solution solution2) throws Exception;

	public void deleteById(int solutionId) throws Exception;

	public List<Solution> findMySolutionList(int i);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

}
