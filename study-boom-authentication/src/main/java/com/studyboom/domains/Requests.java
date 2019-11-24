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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "requests")
public class Requests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users userIdToRequests;

	@Column(name = "request_text", nullable = false)
	private String requestText;

	@Column(name = "processed", nullable = false)
	private String processed;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "last_modified", nullable = false)
	private LocalDateTime lastModified;

	@Column(name = "date_created", nullable = false)
	private LocalDateTime dateCreated;

	public Requests() {
		super();
	}

	public Requests(Users userIdToRequests, String requestText, String processed, String status,
			LocalDateTime lastModified, LocalDateTime dateCreated) {
		super();
		this.id = id;
		this.userIdToRequests = userIdToRequests;
		this.requestText = requestText;
		this.processed = processed;
		this.status = status;
		this.lastModified = lastModified;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUserIdToRequests() {
		return userIdToRequests;
	}

	public void setUserIdToRequests(Users userIdToRequests) {
		this.userIdToRequests = userIdToRequests;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Requests [id=" + id + ", userIdToRequests=" + userIdToRequests + ", requestText=" + requestText
				+ ", processed=" + processed + ", status=" + status + ", lastModified=" + lastModified
				+ ", dateCreated=" + dateCreated + "]";
	}

}
