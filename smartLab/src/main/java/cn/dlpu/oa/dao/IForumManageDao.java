package cn.dlpu.oa.dao;

import cn.dlpu.oa.base.IBaseDao;
import cn.dlpu.oa.domain.Forum;

public interface IForumManageDao extends IBaseDao<Forum> {

	public void moveUp(int id) throws Exception;

	public void moveDown(int id) throws Exception;

}
