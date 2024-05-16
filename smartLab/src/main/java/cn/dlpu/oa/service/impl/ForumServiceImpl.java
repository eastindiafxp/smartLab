package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IForumDao;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.service.IForumService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl implements IForumService {
	
	private IForumDao forumDao;

	public IForumDao getForumDao() {
		return forumDao;
	}

	public void setForumDao(IForumDao forumDao) {
		this.forumDao = forumDao;
	}

	@Override
	public List<Forum> findForumList() {
		return forumDao.findAll();
	}

	@Override
	public Forum findById(int id) {
		return forumDao.findById(id);
	}

	@Override
	public void updateForum(Forum forum) throws Exception {
		forumDao.update(forum);
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		return forumDao.getPage(hh, pageNo, isNew);
	}

}
