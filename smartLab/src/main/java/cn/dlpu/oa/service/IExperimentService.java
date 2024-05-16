package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Experiment;

public interface IExperimentService {

	public List<Experiment> findByProjId(int projectId);

	public Experiment findById(int experimentId);

	public void deleteById(int experimentId) throws Exception;

	public void save(Experiment experiment) throws Exception;

	public void update(Experiment ept) throws Exception;

	public Experiment findByEptNoProjId(String experimentNo, int projectId);

}
