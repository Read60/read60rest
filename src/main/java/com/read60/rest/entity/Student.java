package com.read60.rest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Student {

	private Long id;
	
	private Date insertDate;
	
	private String firstName;
	private String lastName;
	private String birthDate;
	private String gender;
	private int age;
	
	private Teacher teacher;
	private School school;
	private Address address;
	private int entries;
	private Library library;
	
	private Set<Parent> parents = new HashSet<Parent>();
	private List<ReadLog> logs = new ArrayList<ReadLog>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="teacher_id")
	@JsonIgnore
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="school_id")
	@JsonIgnore
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="address_id")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Formula(value = "(SELECT count(*) FROM ReadLog logs WHERE logs.student_id = id)")
	public int getEntries() {
		return entries;
	}
	
	public void setEntries(int entries) {
		this.entries = entries;
	}
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="student")
	@JsonIgnore
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
			name = "parent_student",
			joinColumns = @JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="parent_id")
			)
	@JsonIgnore
	public Set<Parent> getParents() {
		return parents;
	}
	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy="student", fetch=FetchType.LAZY)
	@JsonIgnore
	public List<ReadLog> getLogs() {
		return logs;
	}
	public void setLogs(List<ReadLog> logs) {
		this.logs = logs;
	}
	
}
