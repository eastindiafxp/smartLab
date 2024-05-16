package cn.dlpu.oa.domain;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限类
 * @author 樊晓璞 v1.0 2015--8-07
 *
 */
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id ;
	
	private String name;
	
	private String url;
	
	private Privilege parent;
	
	private Set<Privilege> children = new HashSet<Privilege>();
	
	private Set<Role> roles = new HashSet<Role>();

	/** default constructor */
//	public Privilege(){}
	
	/** minimal constructor */
	public Privilege(String name, Privilege parent) {
		this.name = name;
		this.parent = parent;
	}
	
//	/** full constructor */
//	public Privilege(String name, Privilege parent, String url, Set<Privilege> children, Set<Role> roles) {
//		this.name = name;
//		this.parent = parent;
//		this.url = url;
//		this.children = children;
//		this.roles = roles;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public Privilege getParent() {
//		return parent;
//	}
//
//	public void setParent(Privilege parent) {
//		this.parent = parent;
//	}
//
//	public Set<Privilege> getChildren() {
//		return children;
//	}
//
//	public void setChildren(Set<Privilege> children) {
//		this.children = children;
//	}
//
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
	
}
