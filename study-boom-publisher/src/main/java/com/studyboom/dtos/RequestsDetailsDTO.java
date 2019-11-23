package com.studyboom.dtos;

public class RequestsDetailsDTO {

	private Long userId;
	private Long requestId;
	private String requestText;
	private String processed;
	private String status;

	public RequestsDetailsDTO() {

	}

	public RequestsDetailsDTO(Long userId, String requestText, String processed, String status) {
		super();
		this.userId = userId;
		this.requestText = requestText;
		this.processed = processed;
		this.status = status;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "RequestsDTO [userId=" + userId + ", requestText=" + requestText + ", processed=" + processed
				+ ", status=" + status + "]";
	}

}
