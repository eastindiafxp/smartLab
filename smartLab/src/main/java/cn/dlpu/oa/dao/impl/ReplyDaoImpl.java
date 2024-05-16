package cn.dlpu.oa.dao.impl;

import java.util.List;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.IReplyDao;
import cn.dlpu.oa.domain.Reply;
import cn.dlpu.oa.domain.Topic;


public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements IReplyDao {

	@Override
	public List<Reply> findReplyList(Topic topic) {
		String hql = "from Reply r where r.topic = ? order by r.postTime";
		return this.getSession().createQuery(hql).setParameter(0, topic).list();
	}

	/*@Override
	public Page getPage(int pageNo, Topic topic) {
		String hql = "from Reply r where r.topic = ? order by r.postTime";
		Query query = this.getSession().createQuery(hql).setParameter(0, topic);
		query.setFirstResult(PubFun.PAGE_SIZE * (pageNo - 1));
		query.setMaxResults(PubFun.PAGE_SIZE);
		List<Reply> resultList = query.list();
		
		String hql2 = "select count(id) from Reply r where r.topic = ?";
		query = this.getSession().createQuery(hql2).setParameter(0, topic);
		Long resultCount = (Long) query.uniqueResult();
		Page page = new Page(pageNo, PubFun.PAGE_SIZE, resultCount.intValue(), resultList);
		return page;
	}*/

}
