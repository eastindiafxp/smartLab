package cn.dlpu.oa.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Forum entity. @author MyEclipse Persistence Tools
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Forum implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private int id;
	private String name;
	private String description;
	private int showColNo;
	private int topicCount;
	private int articleCount;
	private Topic lastTopic;
	private Set<Topic> topics = new HashSet<Topic>(0);

	// Constructors

	/** default constructor */
//	public Forum() {
//	}

	/** minimal constructor */
	public Forum(String name) {
		this.name = name;
	}

	/** full constructor */
	public Forum(Topic lastTopic, String name, String description,
			int showColNo, int topicCount, int articleCount,
			Set<Topic> topics) {
		this.lastTopic = lastTopic;
		this.name = name;
		this.description = description;
		this.showColNo = showColNo;
		this.topicCount = topicCount;
		this.articleCount = articleCount;
		this.topics = topics;
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
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return this.description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public int getShowColNo() {
//		return this.showColNo;
//	}
//
//	public void setShowColNo(int showColNo) {
//		this.showColNo = showColNo;
//	}
//
//	public int getTopicCount() {
//		return this.topicCount;
//	}
//
//	public void setTopicCount(int topicCount) {
//		this.topicCount = topicCount;
//	}
//
//	public int getArticleCount() {
//		return this.articleCount;
//	}
//
//	public void setArticleCount(int articleCount) {
//		this.articleCount = articleCount;
//	}
//
//	public Set<Topic> getTopics() {
//		return this.topics;
//	}
//
//	public void setTopics(Set<Topic> topics) {
//		this.topics = topics;
//	}
//
//	public Topic getLastTopic() {
//		return lastTopic;
//	}
//
//	public void setLastTopic(Topic lastTopic) {
//		this.lastTopic = lastTopic;
//	}

}