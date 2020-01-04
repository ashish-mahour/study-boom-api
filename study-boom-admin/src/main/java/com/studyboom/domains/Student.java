package com.studyboom.domains;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users userIdToStudent;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "mobile", nullable = true)
	private String mobile;

	@Column(name = "registration_date", nullable = false)
	private LocalDateTime registrationDate;

	@Column(name = "modified_date", nullable = false)
	private LocalDateTime modifiedDate;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeriesRatings.class, mappedBy = "ratedByStudent")
	private List<TestSeriesRatings> ratedByStudent;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = StudentChoosenSubjectSubCategory.class, mappedBy = "studentIdToChoosenSubCategories")
	private List<StudentChoosenSubjectSubCategory> studentIdToChoosenSubCategories;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = StudentPerfromedTest.class, mappedBy = "performendByStudent")
	@JsonIgnore
	private List<StudentPerfromedTest> testSeriesPerformendByStudent;

	public Student() {
		super();
	}

	public Student(Users userIdToStudent, String fullName, String username, String email, String mobile,
			LocalDateTime registrationDate, LocalDateTime modifiedDate) {
		super();
		this.userIdToStudent = userIdToStudent;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.registrationDate = registrationDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUserIdToStudent() {
		return userIdToStudent;
	}

	public void setUserIdToStudent(Users userIdToStudent) {
		this.userIdToStudent = userIdToStudent;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<TestSeriesRatings> getRatedByStudent() {
		return ratedByStudent;
	}

	public void setRatedByStudent(List<TestSeriesRatings> ratedByStudent) {
		this.ratedByStudent = ratedByStudent;
	}

	public List<StudentChoosenSubjectSubCategory> getStudentIdToChoosenSubCategories() {
		return studentIdToChoosenSubCategories;
	}

	public void setStudentIdToChoosenSubCategories(
			List<StudentChoosenSubjectSubCategory> studentIdToChoosenSubCategories) {
		this.studentIdToChoosenSubCategories = studentIdToChoosenSubCategories;
	}

	public List<StudentPerfromedTest> getTestSeriesPerformendByStudent() {
		return testSeriesPerformendByStudent;
	}

	public void setTestSeriesPerformendByStudent(List<StudentPerfromedTest> testSeriesPerformendByStudent) {
		this.testSeriesPerformendByStudent = testSeriesPerformendByStudent;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", userIdToStudent=" + userIdToStudent + ", fullName=" + fullName + ", username="
				+ username + ", email=" + email + ", mobile=" + mobile + ", registrationDate=" + registrationDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
