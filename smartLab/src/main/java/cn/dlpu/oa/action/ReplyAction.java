package cn.dlpu.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dlpu.oa.base.BaseAction;
import cn.dlpu.oa.domain.Forum;
import cn.dlpu.oa.domain.Page;
import cn.dlpu.oa.domain.Reply;
import cn.dlpu.oa.domain.Topic;
import cn.dlpu.oa.utils.HQLHelper;
import cn.dlpu.oa.utils.PubFun;
import org.apache.struts2.convention.annotation.Action;

/**
 * 回复操作
 * @author 樊晓璞 v1.0 2015-08-15
 *
 */

public class ReplyAction extends BaseAction<Reply> {
	
	private static final long serialVersionUID = 1L;
	
	private Topic topic;
	
	private Reply reply;
	
	private int topicId;
	
	private Page page;
	
	private List<Reply> replyList = new ArrayList<Reply>();
	
	/**
	 * 保存回复信息
	 * @return
	 */
	public String saveReply() {
		Topic topic = topicService.findById(topicId);
		reply.setTopic(topic);
		reply.setStatus("01");
		reply.setIp(PubFun.getIP());
		reply.setPostTime(new Date());//回复时间为当前时间
		reply.setAuthor(PubFun.getLoginUser());
		try {
			replyService.saveReply(reply);
			Forum forum = topic.getForum();
			forum.setArticleCount(forum.getArticleCount() + 1);
			forumService.updateForum(forum);
			topic.setUpdateTime(reply.getPostTime());
			topic.setLastReply(reply);
			topic.setReplyCount(topic.getReplyCount() + 1);
			topicService.updateTopic(topic);
			isNew = PubFun.IS_NEW;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMessage.setClassName(this.getClass().getName());
			exceptionMessage.setError(e.toString());
			exceptionMessage.setMessage("保存新回复时出现异常！");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到回复页面
	 * @return
	 */
	public String replyPage() {
		topic = topicService.findById(topicId);
		getValueStack().push(topic);
		return SUCCESS;
	}
	
	/**
	 * 查询回复列表
	 * @return
	 */
	
	public String findReplyList() {
		
		topic = topicService.findById(topicId);
		
		//分页查询
		HQLHelper hh = new HQLHelper(Reply.class);
		hh.buildWhere("t.topic", PubFun.AND, topic);
		hh.buildOrderBy("t.postTime", true);
		page = replyService.getPage(hh, pageNo, isNew);
		pageNo = PubFun.DEFAULT_PAGE_NO;
		
		replyList = page.getResultList();
		isNew = PubFun.NOT_NEW;
		getValueStack().push(topic);
		
		return SUCCESS;
	}
	
	/* set and get method */
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

}
