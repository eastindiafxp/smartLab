package cn.dlpu.oa.domain;

import lombok.*;

/**
 * SysCodeId entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SysCodeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private String codeType;
	private String codeCode;

	// Constructors

	/** default constructor */
//	public SysCodeId() {
//	}

	/** full constructor */
//	public SysCodeId(String codeType, String codeCode) {
//		this.codeType = codeType;
//		this.codeCode = codeCode;
//	}

	// Property accessors

//	public String getCodeType() {
//		return this.codeType;
//	}
//
//	public void setCodeType(String codeType) {
//		this.codeType = codeType;
//	}
//
//	public String getCodeCode() {
//		return this.codeCode;
//	}
//
//	public void setCodeCode(String codeCode) {
//		this.codeCode = codeCode;
//	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysCodeId))
			return false;
		SysCodeId castOther = (SysCodeId) other;

		return ((this.getCodeType() == castOther.getCodeType()) || (this
				.getCodeType() != null && castOther.getCodeType() != null && this
				.getCodeType().equals(castOther.getCodeType())))
				&& ((this.getCodeCode() == castOther.getCodeCode()) || (this
						.getCodeCode() != null
						&& castOther.getCodeCode() != null && this
						.getCodeCode().equals(castOther.getCodeCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodeType() == null ? 0 : this.getCodeType().hashCode());
		result = 37 * result
				+ (getCodeCode() == null ? 0 : this.getCodeCode().hashCode());
		return result;
	}

}