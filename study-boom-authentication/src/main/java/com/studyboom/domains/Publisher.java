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
@Table(name = "publisher")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users userIdToPublisher;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "mobile", nullable = true)
	private String mobile;

	@Column(name = "bank_name", nullable = true)
	private String bankName;

	@Column(name = "branch", nullable = true)
	private String branch;

	@Column(name = "account_no", nullable = true)
	private String accountNo;

	@Column(name = "ifsc_code", nullable = true)
	private String ifscCode;

	@Column(name = "registration_date", nullable = false)
	private LocalDateTime registrationDate;

	@Column(name = "modified_date", nullable = false)
	private LocalDateTime modifiedDate;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeries.class, mappedBy = "uploadedByPublisher")
	@JsonIgnore
	private List<TestSeries> uploadedByPublisherTestSeries;

	public Publisher() {
		super();
	}

	public Publisher(Users userIdToPublisher, String fullName, String username, String email, String mobile,
			String bankName, String branch, String accountNo, String ifscCode, LocalDateTime registrationDate,
			LocalDateTime modifiedDate) {
		super();
		this.userIdToPublisher = userIdToPublisher;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.bankName = bankName;
		this.branch = branch;
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.registrationDate = registrationDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUserIdToPublisher() {
		return userIdToPublisher;
	}

	public void setUserIdToPublisher(Users userIdToPublisher) {
		this.userIdToPublisher = userIdToPublisher;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
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

	public List<TestSeries> getUploadedByPublisherTestSeries() {
		return uploadedByPublisherTestSeries;
	}

	public void setUploadedByPublisherTestSeries(List<TestSeries> uploadedByPublisherTestSeries) {
		this.uploadedByPublisherTestSeries = uploadedByPublisherTestSeries;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", userIdToPublisher=" + userIdToPublisher + ", fullName=" + fullName
				+ ", username=" + username + ", email=" + email + ", mobile=" + mobile + ", bankName=" + bankName
				+ ", branch=" + branch + ", accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", registrationDate="
				+ registrationDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
