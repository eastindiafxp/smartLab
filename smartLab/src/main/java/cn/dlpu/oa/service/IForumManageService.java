package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;

public interface IForumManageService {

	public List<Forum> findAllForum();

	public void deleteById(Integer id) throws Exception;

	public void saveForum(Forum forum) throws Exception;

	public Forum findById(Integer id);

	public void updateForum(Forum forum2) throws Exception;

	public void moveUp(int id) throws Exception;

	public void moveDown(int id) throws Exception;

	public Page getPage(HQLHelper hh, int pageNo, int isNew);
	
}
