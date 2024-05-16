package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IExperimentDao;
import cn.dlpu.oa.domain.Experiment;
import cn.dlpu.oa.service.IExperimentService;
import org.springframework.stereotype.Service;

@Service
public class ExperimentServiceImpl implements IExperimentService {
	
	private IExperimentDao experimentDao;
	
	@Override
	public List<Experiment> findByProjId(int projectId) {
		List<Experiment> experimentList = experimentDao.findByProjId(projectId);
		return experimentList;
	}
	
	@Override
	public Experiment findById(int experimentId) {
		Experiment ept = experimentDao.findById(experimentId);
		return ept;
	}

	
	/* set和get方法 */
	public IExperimentDao getExperimentDao() {
		return experimentDao;
	}

	public void setExperimentDao(IExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}

	@Override
	public Experiment findByEptNoProjId(String experimentNo, int projectId) {
		Experiment ept = experimentDao.findByEptNoProjId(experimentNo, projectId);
		return ept;
	}

	@Override
	public void deleteById(int experimentId) throws Exception {
		experimentDao.deleteById(experimentId);
	}

	@Override
	public void save(Experiment experiment) throws Exception {
		experimentDao.save(experiment);
	}

	@Override
	public void update(Experiment ept) throws Exception {
		experimentDao.update(ept);
	}

}
