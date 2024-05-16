package cn.dlpu.oa.service;

import java.util.List;

import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.utils.HQLHelper;

public interface IForumService {

	public List<Forum> findForumList();

	public Forum findById(int id);

	public void updateForum(Forum forum) throws Exception;

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

}
