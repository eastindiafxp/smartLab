package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.ISolutionDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Solution;
import cn.dlpu.oa.service.ISolutionService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class SolutionServiceImpl implements ISolutionService {

	private ISolutionDao solutionDao;
	
	@Override
	public List<Solution> findSolutionList() {
		List<Solution> soluList = solutionDao.findAll();
		return soluList;
	}

	@Override
	public List<Solution> findByIds(Integer[] solutionIdList) {
		List<Solution> solutions = solutionDao.findByIds(solutionIdList);
		return solutions;
	}
	
	
	public ISolutionDao getSolutionDao() {
		return solutionDao;
	}

	public void setSolutionDao(ISolutionDao solutionDao) {
		this.solutionDao = solutionDao;
	}

	@Override
	public Solution findById(int solutionId) {
		Solution solu = solutionDao.findById(solutionId);
		return solu;
	}

	@Override
	public void save(Solution solution) throws Exception {
		solutionDao.save(solution);
	}

	@Override
	public void update(Solution solution2) throws Exception {
		solutionDao.update(solution2);
	}

	@Override
	public void deleteById(int solutionId) throws Exception {
		solutionDao.deleteById(solutionId);
	}

	@Override
	public List<Solution> findMySolutionList(int ownerId) {
		List<Solution> list = solutionDao.findMySolutionList(ownerId);
		return list;
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = solutionDao.getPage(hh, pageNo, isNew);
		return page;
	}

}
