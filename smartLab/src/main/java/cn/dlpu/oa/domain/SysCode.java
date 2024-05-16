package cn.dlpu.oa.domain;

import lombok.*;

/**
 * SysCode entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SysCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private SysCodeId id;
	private String codeName;
	private String validStatus;
	private String reverse1;
	private String reverse2;
	private String reverse3;

	// Constructors

	/** default constructor */
//	public SysCode() {
//	}

	/** minimal constructor */
	public SysCode(SysCodeId id, String codeName) {
		this.id = id;
		this.codeName = codeName;
	}

	/** full constructor */
//	public SysCode(SysCodeId id, String codeName, String validStatus,
//			String reverse1, String reverse2, String reverse3) {
//		this.id = id;
//		this.codeName = codeName;
//		this.validStatus = validStatus;
//		this.reverse1 = reverse1;
//		this.reverse2 = reverse2;
//		this.reverse3 = reverse3;
//	}

	// Property accessors

//	public SysCodeId getId() {
//		return this.id;
//	}
//
//	public void setId(SysCodeId id) {
//		this.id = id;
//	}
//
//	public String getCodeName() {
//		return this.codeName;
//	}
//
//	public void setCodeName(String codeName) {
//		this.codeName = codeName;
//	}
//
//	public String getValidStatus() {
//		return this.validStatus;
//	}
//
//	public void setValidStatus(String validStatus) {
//		this.validStatus = validStatus;
//	}
//
//	public String getReverse1() {
//		return this.reverse1;
//	}
//
//	public void setReverse1(String reverse1) {
//		this.reverse1 = reverse1;
//	}
//
//	public String getReverse2() {
//		return this.reverse2;
//	}
//
//	public void setReverse2(String reverse2) {
//		this.reverse2 = reverse2;
//	}
//
//	public String getReverse3() {
//		return this.reverse3;
//	}
//
//	public void setReverse3(String reverse3) {
//		this.reverse3 = reverse3;
//	}

}