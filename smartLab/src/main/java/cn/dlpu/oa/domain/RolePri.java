package cn.dlpu.oa.domain;

import lombok.*;

/**
 * 岗位—权限关系类
 * @author 樊晓璞 v1.0 2015--8-07
 *
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RolePri {
	
	private int id;
	
	private int roleId;
	
	private int priId;
	
//	public RolePri(){}
	
	public RolePri(int roleId, int priId) {
		this.roleId = roleId;
		this.priId = priId;
	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
//
//	public int getPriId() {
//		return priId;
//	}
//
//	public void setPriId(int priId) {
//		this.priId = priId;
//	}
	
}
