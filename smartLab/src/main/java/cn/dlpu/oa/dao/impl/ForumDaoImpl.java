package cn.dlpu.oa.dao.impl;

import java.util.List;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IForumDao;
import cn.dlpu.oa.domain.Forum;

public class ForumDaoImpl extends BaseDaoImpl<Forum> implements IForumDao {
	
	/**
	 * 重写查询版块列表的方法
	 */
	
	@Override
	public List<Forum> findAll() {
		String hql = "from Forum f order by f.showColNo";
		return this.getSession().createQuery(hql).list();
	}
	
}
