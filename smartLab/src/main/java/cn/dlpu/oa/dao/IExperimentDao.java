package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Experiment;

public interface IExperimentDao extends IBaseDao<Experiment> {

	public List<Experiment> findByProjId(int projectId);

	public Experiment findByEptNoProjId(String experimentNo, int projectId);

}
