package cn.dlpu.oa.service;

import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.utils.HQLHelper;

public interface ITopicService {

//	public List<Topic> findTopicByForumId(int id);

	public void save(Topic topic) throws Exception;

	public Topic findById(int id);

	public void updateTopic(Topic topic) throws Exception;

	public Page getPage(HQLHelper hh, int pageNo, int isNew);

}
