package cn.dlpu.oa.dao;

import java.util.List;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Project;

public interface IProjectDao extends IBaseDao<Project> {

	public Project findByProjNo(String projNo);

	public List<Project> findMyProject(int id);
	
}
