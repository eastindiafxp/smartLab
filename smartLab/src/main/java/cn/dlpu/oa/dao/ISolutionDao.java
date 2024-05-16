package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Solution;

public interface ISolutionDao extends IBaseDao<Solution> {

	public List<Solution> findMySolutionList(int ownerId);

}
