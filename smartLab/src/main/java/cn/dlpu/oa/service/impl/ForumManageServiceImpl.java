package cn.dlpu.oa.service.impl;

import java.util.List;

import cn.dlpu.oa.dao.IForumManageDao;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.service.IForumManageService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class ForumManageServiceImpl implements IForumManageService {
	
	private IForumManageDao forumManageDao;

	public IForumManageDao getForumManageDao() {
		return forumManageDao;
	}

	public void setForumManageDao(IForumManageDao forumManageDao) {
		this.forumManageDao = forumManageDao;
	}

	@Override
	public List<Forum> findAllForum() {
		return forumManageDao.findAll();
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		forumManageDao.deleteById(id);
	}

	@Override
	public void saveForum(Forum forum) throws Exception {
		forumManageDao.save(forum);
	}

	@Override
	public Forum findById(Integer id) {
		return forumManageDao.findById(id);
	}

	@Override
	public void updateForum(Forum forum2) throws Exception {
		forumManageDao.update(forum2);
	}

	@Override
	public void moveUp(int id) throws Exception {
		forumManageDao.moveUp(id);
	}

	@Override
	public void moveDown(int id) throws Exception {
		forumManageDao.moveDown(id);
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		return forumManageDao.getPage(hh, pageNo, isNew);
	}
	
}
