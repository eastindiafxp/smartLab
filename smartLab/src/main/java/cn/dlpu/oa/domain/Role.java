package cn.dlpu.oa.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 岗位类
 * @author 樊晓璞 v1.0 2015--7-31
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>();
	private Set<Privilege> privileges = new HashSet<Privilege>();

//    private Integer pageNo;
	/** default constructor */
//    public Role() {}

	/** minimal constructor */
    public Role(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Role(String name, String description, Set<User> users, Set<Privilege> privileges) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.privileges = privileges;
    }
//
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public Set<User> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//
//	public Set<Privilege> getPrivileges() {
//		return privileges;
//	}
//
//	public void setPrivileges(Set<Privilege> privileges) {
//		this.privileges = privileges;
//	}
	
}
