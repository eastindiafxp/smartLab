package cn.dlpu.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IForumManageDao;
import cn.dlpu.oa.domain.Forum;


public class ForumManageDaoImpl extends BaseDaoImpl<Forum> implements IForumManageDao {
	
	/**
	 * 重写保存方法，使得保存论坛模块时可以将排序用行号（showColNo）设置为id值
	 */
	@Override
	public void save(Forum t) throws Exception {
		super.save(t);//由瞬时对象变为持久对象，id已经有值
		t.setShowColNo(t.getId());
		super.update(t);
	}
	
	/**
	 * 重写查询方法，使其按照showColNo排列
	 */
	@Override
	public List<Forum> findAll() {
		String hql = "FROM Forum f ORDER BY f.showColNo";
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 往上移动论坛模块
	 */
	@Override
	public void moveUp(int id) throws Exception {
		Forum forum1 = this.findById(id);
		int no1 = forum1.getShowColNo();
		
		Session session = this.getSession();
		String hql = "from Forum f where f.showColNo < ? order by f.showColNo desc";
		Query query = session.createQuery(hql).setParameter(0, no1);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Forum forum2 = (Forum) query.uniqueResult();
		
		forum1.setShowColNo(forum2.getShowColNo());
		forum2.setShowColNo(no1);
		super.update(forum1);
		super.update(forum2);
	}

	/**
	 * 往下移动论坛模块
	 */
	@Override
	public void moveDown(int id) throws Exception {
		Forum forum1 = this.findById(id);
		int no1 = forum1.getShowColNo();
		
		Session session = this.getSession();
		String hql = "from Forum f where f.showColNo > ? order by f.showColNo";
		Query query = session.createQuery(hql).setParameter(0, no1);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Forum forum2 = (Forum) query.uniqueResult();
		
		forum1.setShowColNo(forum2.getShowColNo());
		forum2.setShowColNo(no1);
		super.update(forum1);
		super.update(forum2);
	}
	
}
