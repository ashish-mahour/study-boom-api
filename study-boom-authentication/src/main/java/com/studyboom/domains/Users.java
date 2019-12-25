package com.studyboom.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "profilePic", nullable = true)
	private String profilePic;

	@Column(name = "is_activated", nullable = false)
	private Boolean isActivated;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Admin.class, mappedBy = "userIdToAdmin")
	private Admin userIdFromAdmin;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Student.class, mappedBy = "userIdToStudent")
	private Student userIdFromStudent;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Publisher.class, mappedBy = "userIdToPublisher")
	private Publisher userIdFromPublisher;

	@OneToMany(targetEntity = Requests.class, mappedBy = "userIdToRequests")
	@JsonIgnore
	private List<Requests> userIdsFromRequests;

	public Users() {
	}

	public Users(String fullName, String username, String email, String password, String type, String profilePic,
			Boolean isActivated) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.profilePic = profilePic;
		this.password = password;
		this.type = type;
		this.isActivated = isActivated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public Admin getUserIdFromAdmin() {
		return userIdFromAdmin;
	}

	public void setUserIdFromAdmin(Admin userIdFromAdmin) {
		this.userIdFromAdmin = userIdFromAdmin;
	}

	public Student getUserIdFromStudent() {
		return userIdFromStudent;
	}

	public void setUserIdFromStudent(Student userIdFromStudent) {
		this.userIdFromStudent = userIdFromStudent;
	}

	public Publisher getUserIdFromPublisher() {
		return userIdFromPublisher;
	}

	public void setUserIdFromPublisher(Publisher userIdFromPublisher) {
		this.userIdFromPublisher = userIdFromPublisher;
	}

	public List<Requests> getUserIdsFromRequests() {
		return userIdsFromRequests;
	}

	public void setUserIdsFromRequests(List<Requests> userIdsFromRequests) {
		this.userIdsFromRequests = userIdsFromRequests;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fullName=" + fullName + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", type=" + type + ", isActivated=" + isActivated + "]";
	}

}
