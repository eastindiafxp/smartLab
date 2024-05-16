package cn.dlpu.oa.domain;

import lombok.*;

import java.util.Date;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Reply implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Fields
	private Integer id;
	private Topic topic;
	private User author;
	private String content;
	private Date postTime;
	private String ip;
	private String status;

	// Constructors

	/** default constructor */
//	public Reply() {}

	/** minimal constructor */
	public Reply(Date postTime) {
		this.postTime = postTime;
	}

	/** full constructor */
	public Reply(Topic topic, User author, String content, Date postTime,
			String ip, String status) {
		this.topic = topic;
		this.setAuthor(author);
		this.content = content;
		this.postTime = postTime;
		this.ip = ip;
		this.status = status;
	}

	// Property accessors
//
//	public Integer getId() {
//		return this.id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Topic getTopic() {
//		return this.topic;
//	}
//
//	public void setTopic(Topic topic) {
//		this.topic = topic;
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
//	public String getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public User getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(User author) {
//		this.author = author;
//	}

}