package cn.dlpu.oa.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门类
 * @author 樊晓璞 v1.0 2015--7-31
 *
 */
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Department implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
	private int id;
//    @Column(name = "name")
	private String name;
	private String description;
	private Department parent;
	private Set<Department> children = new HashSet<Department>();
	private Set<User> users = new HashSet<User>();
	
	/** default constructor */
//    public Department() {
//    }

	/** minimal constructor */
    public Department(Department parent, String name) {
        this.parent = parent;
        this.name = name;
    }
    
    /** full constructor */
    public Department(Department department, String name, String description, Set<User> users, Set<Department> children) {
        this.parent = department;
        this.name = name;
        this.description = description;
        this.users = users;
        this.children = children;
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
//	public Department getParent() {
//		return parent;
//	}
//	public void setParent(Department parent) {
//		this.parent = parent;
//	}
//	public Set<Department> getChildren() {
//		return children;
//	}
//	public void setChildren(Set<Department> children) {
//		this.children = children;
//	}
//	public Set<User> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
	
}
