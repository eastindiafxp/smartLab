package cn.dlpu.oa.domain;

import lombok.*;

/**
 * Template entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Template implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;
	private String name;
	private String prcDefKey;
	private String filePath;

	// Constructors

	/** default constructor */
//	public Template() {
//	}

	/** full constructor */
	public Template(String name, String prcDefKey, String filePath) {
		this.name = name;
		this.prcDefKey = prcDefKey;
		this.filePath = filePath;
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
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPrcDefKey() {
//		return this.prcDefKey;
//	}
//
//	public void setPrcDefKey(String prcDefKey) {
//		this.prcDefKey = prcDefKey;
//	}
//
//	public String getFilePath() {
//		return this.filePath;
//	}
//
//	public void setFilePath(String filePath) {
//		this.filePath = filePath;
//	}

}