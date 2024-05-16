package cn.dlpu.oa.domain;

import lombok.*;

/**
 * 岗位-用户关系表
 * @author 樊晓璞 v1.0 2015--7-31
 *
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoleUser  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private int roleId;
    private int userId;
//	private Role role;
//	private User user;

    // Constructors

    /** default constructor */
//    public RoleUser() {
//    }

    
    /** full constructor */
    public RoleUser(int roleId, int userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
   /* public RoleUser(Role role, User user) {
    	this.role = role;
    	this.user = user;
    }*/

   
    // Property accessors
//
//    public Integer getId() {
//        return this.id;
//    }
//
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//	public int getRoleId() {
//		return roleId;
//	}
//
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
//
//
//	public int getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}


	/*public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}*/

}