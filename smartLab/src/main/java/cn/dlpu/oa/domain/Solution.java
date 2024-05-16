package cn.dlpu.oa.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

// Generated 2016-9-22 21:15:50 by Hibernate Tools 3.4.0.CR1

/**
 * Solution generated by hbm2java
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Solution implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String solvent;
	private String dosage;
	private String reverse;
	private User owner;
	private Set<Record> records = new HashSet<Record>();
	private Set<Medicine> medicines = new HashSet<Medicine>();

//	public Solution() {
//	}

	public Solution(String name, String solvent, String dosage) {
		this.name = name;
		this.solvent = solvent;
		this.dosage = dosage;
	}

	public Solution(String name, String solvent, String dosage, String reverse, User owner, Set<Record> records, Set<Medicine> medicines) {
		this.name = name;
		this.solvent = solvent;
		this.dosage = dosage;
		this.reverse = reverse;
		this.owner = owner;
		this.records = records;
		this.medicines = medicines;
	}
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
//	public Set<Medicine> getMedicines() {
//		return medicines;
//	}
//
//	public void setMedicines(Set<Medicine> medicines) {
//		this.medicines = medicines;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSolvent() {
//		return this.solvent;
//	}
//
//	public void setSolvent(String solvent) {
//		this.solvent = solvent;
//	}
//
//	public String getDosage() {
//		return this.dosage;
//	}
//
//	public void setDosage(String dosage) {
//		this.dosage = dosage;
//	}
//
//	public String getReverse() {
//		return this.reverse;
//	}
//
//	public void setReverse(String reverse) {
//		this.reverse = reverse;
//	}
//
//	public Set<Record> getRecords() {
//		return records;
//	}
//
//	public void setRecords(Set<Record> records) {
//		this.records = records;
//	}
//
//	public User getOwner() {
//		return owner;
//	}
//
//	public void setOwner(User owner) {
//		this.owner = owner;
//	}

}
