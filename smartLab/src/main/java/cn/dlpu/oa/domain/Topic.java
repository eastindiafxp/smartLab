package cn.dlpu.oa.domain;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Topic entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Topic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private int id;
	private Reply lastReply;
	private User author;
	private Forum forum;
	private String content;
	private Date postTime;
	private String ip;
	private String title;
	private Date updateTime;
	private String topicType;
	private int replyCount;
	private Set<Reply> replies = new HashSet<Reply>(0);

	// Constructors

	/** default constructor */
//	public Topic() {
//	}

	/** minimal constructor */
	public Topic(Date postTime, Date updateTime) {
		this.postTime = postTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public Topic(Reply lastReply, User author, Forum forum, String content,
			Date postTime, String ip, String title, Date updateTime,
			String topicType, int replyCount, Set<Reply> replies) {
		this.lastReply = lastReply;
		this.author = author;
		this.forum = forum;
		this.content = content;
		this.postTime = postTime;
		this.ip = ip;
		this.title = title;
		this.updateTime = updateTime;
		this.topicType = topicType;
		this.replyCount = replyCount;
		this.replies = replies;
	}

	// Property accessors
//
//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Forum getForum() {
//		return this.forum;
//	}
//
//	public void setForum(Forum forum) {
//		this.forum = forum;
//	}
//
//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public Date getPostTime() {
//		return this.postTime;
//	}
//
//	public void setPostTime(Date postTime) {
//		this.postTime = postTime;
//	}
//
//	public String getIp() {
//		return this.ip;
//	}
//
//	public void setIp(String ip) {
//		this.ip = ip;
//	}
//
//	public String getTitle() {
//		return this.title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public Date getUpdateTime() {
//		return this.updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public String getTopicType() {
//		return this.topicType;
//	}
//
//	public void setTopicType(String topicType) {
//		this.topicType = topicType;
//	}
//
//	public int getReplyCount() {
//		return this.replyCount;
//	}
//
//	public void setReplyCount(int replyCount) {
//		this.replyCount = replyCount;
//	}
//
//	public Set<Reply> getReplies() {
//		return this.replies;
//	}
//
//	public void setReplies(Set<Reply> replies) {
//		this.replies = replies;
//	}
//
//	public User getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(User author) {
//		this.author = author;
//	}
//
//	public Reply getLastReply() {
//		return lastReply;
//	}
//
//	public void setLastReply(Reply lastReply) {
//		this.lastReply = lastReply;
//	}

}