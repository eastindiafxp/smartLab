package cn.dlpu.oa.dao.impl;

import cn.dlpu.oa.base.BaseDaoImpl;
import cn.dlpu.oa.dao.ITopicDao;
import cn.dlpu.oa.domain.Topic;

public class TopicDaoImpl extends BaseDaoImpl<Topic> implements ITopicDao {

	/**
	 * 根据版块id查询相应的主题列表
	 */
	/*@Override
	public List<Topic> findTopicListByForumId(int id) {
		String hql = "from Topic t where t.forum.id = ? order by (case t.topicType when '04' then 1 else 5 end), t.postTime desc";
		return this.getSession().createQuery(hql).setParameter(0, id).list();
	}*/

}
