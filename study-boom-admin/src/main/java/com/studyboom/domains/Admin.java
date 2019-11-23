package com.studyboom.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.EAGER)
	private Users userIdToAdmin;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "mobile", nullable = true)
	private Long mobile;

	@Column(name = "registration_date", nullable = false)
	private LocalDateTime registrationDate;

	@Column(name = "modified_date", nullable = false)
	private LocalDateTime modifiedDate;

	public Admin() {
		super();
	}

	public Admin( Users userIdToAdmin, String fullName, String username, String email, Long mobile,
			LocalDateTime registrationDate, LocalDateTime modifiedDate) {
		super();
		this.userIdToAdmin = userIdToAdmin;
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

	public Users getUserIdToAdmin() {
		return userIdToAdmin;
	}

	public void setUserIdToAdmin(Users userIdToAdmin) {
		this.userIdToAdmin = userIdToAdmin;
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

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
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

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userIdToAdmin=" + userIdToAdmin + ", fullName=" + fullName + ", username="
				+ username + ", email=" + email + ", mobile=" + mobile + ", registrationDate=" + registrationDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
