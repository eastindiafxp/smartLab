package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Project;
import cn.dlpu.oa.utils.HQLHelper;

public interface IProjectService {
	
	public List<Project> findProjectList();

	public Project findById(int projectId);

	public void deleteById(int projectId) throws Exception;

	public Project findByProjNo(String projNo);

	public void save(Project project3) throws Exception;

	public void update(Project proj) throws Exception;

	public List<Project> findMyProject(int id);

	public Page getPage(HQLHelper hh, int pageNo, int isNew);
	
}
