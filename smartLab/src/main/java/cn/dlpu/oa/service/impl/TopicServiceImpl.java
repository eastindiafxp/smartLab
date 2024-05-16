package cn.dlpu.oa.service.impl;

import cn.dlpu.oa.dao.ITopicDao;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.service.ITopicService;
import cn.dlpu.oa.utils.HQLHelper;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements ITopicService {
	
	private ITopicDao topicDao;
	
	/*@Override
	public List<Topic> findTopicByForumId(int id) {
		return topicDao.findTopicListByForumId(id);
	}*/

	public ITopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(ITopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public void save(Topic topic) throws Exception {
		topicDao.save(topic);
	}

	@Override
	public Topic findById(int id) {
		return topicDao.findById(id);
	}

	@Override
	public void updateTopic(Topic topic) throws Exception {
		topicDao.update(topic);
	}

	@Override
	public Page getPage(HQLHelper hh, int pageNo, int isNew) {
		return topicDao.getPage(hh, pageNo, isNew);
	}

}
