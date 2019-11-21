package com.studyboom.domains;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "is_activated", nullable = false)
	private Boolean isActivated;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Admin.class, mappedBy = "userIdToAdmin")
	private Admin userIdToAdmin;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Student.class, mappedBy = "userIdToStudent")
	private Student userIdToStudent;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Publisher.class, mappedBy = "userIdToPublisher")
	private Publisher userIdToPublisher;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Requests.class, mappedBy = "userIdToRequests")
	private Set<Requests> userIdToRequests = new TreeSet<Requests>();

	public Users() {
	}

	public Users(String fullName, String username, String email, String password, String type, Boolean isActivated) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.email = email;
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

	public Admin getUserIdToAdmin() {
		return userIdToAdmin;
	}

	public void setUserIdToAdmin(Admin userIdToAdmin) {
		this.userIdToAdmin = userIdToAdmin;
	}

	public Student getUserIdToStudent() {
		return userIdToStudent;
	}

	public void setUserIdToStudent(Student userIdToStudent) {
		this.userIdToStudent = userIdToStudent;
	}

	public Publisher getUserIdToPublisher() {
		return userIdToPublisher;
	}

	public void setUserIdToPublisher(Publisher userIdToPublisher) {
		this.userIdToPublisher = userIdToPublisher;
	}

	public Set<Requests> getUserIdToRequests() {
		return userIdToRequests;
	}

	public void setUserIdToRequests(Set<Requests> userIdToRequests) {
		this.userIdToRequests = userIdToRequests;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fullName=" + fullName + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", type=" + type + ", isActivated=" + isActivated + "]";
	}

}
