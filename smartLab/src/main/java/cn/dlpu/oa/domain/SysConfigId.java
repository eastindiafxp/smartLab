package cn.dlpu.oa.domain;

import lombok.*;

/**
 * SysConfigId entity. @author MyEclipse Persistence Tools
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SysConfigId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private String configName;
	private String configValue;

	// Constructors

	/** default constructor */
//	public SysConfigId() {
//	}

	/** full constructor */
//	public SysConfigId(String configName, String configValue) {
//		this.configName = configName;
//		this.configValue = configValue;
//	}

	// Property accessors
//
//	public String getConfigName() {
//		return this.configName;
//	}
//
//	public void setConfigName(String configName) {
//		this.configName = configName;
//	}
//
//	public String getConfigValue() {
//		return this.configValue;
//	}
//
//	public void setConfigValue(String configValue) {
//		this.configValue = configValue;
//	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysConfigId))
			return false;
		SysConfigId castOther = (SysConfigId) other;

		return ((this.getConfigName() == castOther.getConfigName()) || (this
				.getConfigName() != null && castOther.getConfigName() != null && this
				.getConfigName().equals(castOther.getConfigName())))
				&& ((this.getConfigValue() == castOther.getConfigValue()) || (this
						.getConfigValue() != null
						&& castOther.getConfigValue() != null && this
						.getConfigValue().equals(castOther.getConfigValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConfigName() == null ? 0 : this.getConfigName()
						.hashCode());
		result = 37
				* result
				+ (getConfigValue() == null ? 0 : this.getConfigValue()
						.hashCode());
		return result;
	}

}