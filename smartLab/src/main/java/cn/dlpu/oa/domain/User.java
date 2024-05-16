package cn.dlpu.oa.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.dlpu.oa.utils.PubFun;
import lombok.*;
import org.hibernate.Hibernate;

/**
 * 用户类
 * @author 樊晓璞 v1.0 2015-7-31
 *
 */
//@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String loginName;
	private String realName;
	private Integer gender;
	private String phone;
	private String email;
	private String description;
	private String password;
	private Department department;
	private User supervisor;
	private Set<Role> roles = new HashSet<Role>();
	private Set<Record> records = new HashSet<Record>();
	private Set<StageSummary> stageSummarys = new HashSet<StageSummary>();
	private Set<Experiment> experiments = new HashSet<Experiment>();
	private Set<Project> projects = new HashSet<Project>();
	private Set<User> students = new HashSet<User>();
	private Set<Medicine> medicines = new HashSet<Medicine>();
	private Set<Solution> solutions = new HashSet<Solution>();
	private Set<Specimen> specimens = new HashSet<Specimen>();
	private Set<Test> tests = new HashSet<Test>();
	
	/** default constructor */
//    public User() {}

	/** minimal constructor */
    public User(String loginName, String realName, String password) {
        this.loginName = loginName;
        this.realName = realName;
        this.password = password;
    }
    
    /** full constructor */
//    public User(Department department, String loginName, String realName, Integer gender, String phone, String email, String description, String password,
//    		Set<Role> roles, Set<Record> records, Set<StageSummary> stageSummarys, Set<Experiment> experiments, Set<Project> projects, User supervisor,
//    		Set<User> students, Set<Medicine> medicines, Set<Solution> solutions, Set<Specimen> specimens, Set<Test> tests) {
//        this.department = department;
//        this.loginName = loginName;
//        this.realName = realName;
//        this.gender = gender;
//        this.phone = phone;
//        this.email = email;
//        this.description = description;
//        this.password = password;
//        this.roles = roles;
//        this.records = records;
//        this.stageSummarys = stageSummarys;
//        this.experiments = experiments;
//        this.projects = projects;
//        this.supervisor = supervisor;
//        this.students = students;
//        this.medicines = medicines;
//        this.solutions = solutions;
//        this.specimens = specimens;
//        this.tests = tests;
//    }
//
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public Set<Record> getRecords() {
//		return records;
//	}
//	public void setRecords(Set<Record> records) {
//		this.records = records;
//	}
//	public String getLoginName() {
//		return loginName;
//	}
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//	public String getRealName() {
//		return realName;
//	}
//	public void setRealName(String realName) {
//		this.realName = realName;
//	}
//	public Integer getGender() {
//		return gender;
//	}
//	public void setGender(Integer gender) {
//		this.gender = gender;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public Department getDepartment() {
//		return department;
//	}
//	public Set<StageSummary> getStageSummarys() {
//		return stageSummarys;
//	}
//
//	public void setStageSummarys(Set<StageSummary> stageSummarys) {
//		this.stageSummarys = stageSummarys;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
//	public Set<Role> getRoles() {
//		return roles;
//	}
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//
//	public Set<Experiment> getExperiments() {
//		return experiments;
//	}
//
//	public void setExperiments(Set<Experiment> experiments) {
//		this.experiments = experiments;
//	}
//
//	public Set<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(Set<Project> projects) {
//		this.projects = projects;
//	}
//
//	public User getSupervisor() {
//		return supervisor;
//	}
//
//	public void setSupervisor(User supervisor) {
//		this.supervisor = supervisor;
//	}
//
//	public Set<User> getStudents() {
//		return students;
//	}
//
//	public void setStudents(Set<User> students) {
//		this.students = students;
//	}
//
//	public Set<Medicine> getMedicines() {
//		return medicines;
//	}
//
//	public void setMedicines(Set<Medicine> medicines) {
//		this.medicines = medicines;
//	}
//
//	public Set<Solution> getSolutions() {
//		return solutions;
//	}
//
//	public void setSolutions(Set<Solution> solutions) {
//		this.solutions = solutions;
//	}
//
//	public Set<Specimen> getSpecimens() {
//		return specimens;
//	}
//
//	public void setSpecimens(Set<Specimen> specimens) {
//		this.specimens = specimens;
//	}
//
//	public Set<Test> getTests() {
//		return tests;
//	}
//
//	public void setTests(Set<Test> tests) {
//		this.tests = tests;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	/**
	 * 根据权限名称判断当前用户是否有该权限
	 * @param priName:要判断的权限名称
	 * @return
	 */
	public boolean hasThisPri(String priName) {
		//如果用户是超级管理员，则拥有所有权限
		if (PubFun.isAdmin(this)) {
			return true;
		}
		//当用户不是超级管理员时
		for (Role role : roles) {
			List<Privilege> priList = new ArrayList<Privilege>(role.getPrivileges());
			for (Privilege p : priList) {
				if (p.getName().equals(priName)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasThisPriByUrl(String url) {
		if (PubFun.isAdmin(this)) {
			return true;
		}
		for (Role role : roles) {
			List<Privilege> priList = new ArrayList<Privilege>(role.getPrivileges());
			for (Privilege p : priList) {
				if (url.equals(p.getUrl())) {
					return true;
				}
			}
		}
		return false;
	}
	
}
