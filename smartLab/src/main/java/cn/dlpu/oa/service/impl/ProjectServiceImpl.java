package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IProjectDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Project;
import cn.dlpu.oa.service.IProjectService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements IProjectService {

	private IProjectDao projectDao;
	
	@Override
	public List<Project> findProjectList() {
		List<Project> projectList = projectDao.findAll();
		return projectList;
	}
	
	@Override
	public Project findById(int projectId) {
		Project project = projectDao.findById(projectId);
		return project;
	}

	public IProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public void deleteById(int projectId) throws Exception {
		projectDao.deleteById(projectId);
	}

	@Override
	public Project findByProjNo(String projNo) {
		Project proj = projectDao.findByProjNo(projNo);
		return proj;
	}

	@Override
	public void save(Project project3) throws Exception {
		projectDao.save(project3);
	}

	@Override
	public void update(Project proj) throws Exception {
		projectDao.update(proj);
	}

	@Override
	public List<Project> findMyProject(int id) {
		List<Project> projects = projectDao.findMyProject(id);
		return projects;
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		Page page = projectDao.getPage(hh, pageNo, isNew);
		return page;
	}

	
}
