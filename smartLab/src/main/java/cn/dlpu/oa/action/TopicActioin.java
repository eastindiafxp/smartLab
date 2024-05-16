package cn.dlpu.oa.action;

import java.util.Date;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.utils.PubFun;
import org.apache.struts2.convention.annotation.Action;

/**
 * 帖子管理
 * @author 樊晓璞 v1.0 2015-08-15
 *
 */

public class TopicActioin extends BaseAction<Topic> {
	
	private static final long serialVersionUID = 1L;
	
	private int forumId;
	
	private Forum forum;
	
	private Topic topic;
	
	/**
	 * 跳转到发新帖页面
	 * @return
	 */
	public String topicPage() {
		forum = forumService.findById(forumId);
		getValueStack().push(forum);
		return SUCCESS;
	}
	
	/**
	 * 保存新帖
	 * @return
	 */
	public String saveTopic() {
		Forum forum = forumService.findById(forumId);
		topic.setForum(forum);
		topic.setIp(PubFun.getIP());
		topic.setPostTime(new Date());
		topic.setUpdateTime(topic.getPostTime());//设置最后更新时间为发表主题的时间
		topic.setReplyCount(0);
		topic.setAuthor(PubFun.getLoginUser());
		topic.setTopicType("03");
		try {
			topicService.save(topic);
			
			forum.setTopicCount(forum.getTopicCount() + 1);
			forum.setArticleCount(forum.getArticleCount() + 1);
			forum.setLastTopic(topic);
			forumService.updateForum(forum);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("保存新帖时出现异常！");
			return ERROR;
		}
		isNew = PubFun.IS_NEW;
		return SUCCESS;
	}
	
	/**
	 * 查询回复列表(此方法转移到了ReplyAction中)
	 * @return
	 */
	/*
	public String findReplyList() {
		
		topic = topicService.findById(topicId);
		//分页查询
//		page = replyService.getPage(pageNo, topic);
		HQLHelper hh = new HQLHelper(Reply.class);
		hh.buildWhere("t.topic = ?", topic);
		hh.buildOrderBy("t.postTime", true);
		page = replyService.getPage(hh, pageNo, isNew);
		
		forum = topic.getForum();
		id = forum.getId();
		name = forum.getName();
		model.setId(id);
		replyList = page.getResultList();
		isNew = PubFun.NOT_NEW;
		return SUCCESS;
	}*/
	
	/* set and get method */
	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
