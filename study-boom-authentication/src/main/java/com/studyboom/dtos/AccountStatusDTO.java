package com.studyboom.dtos;

public class AccountStatusDTO {

	private Integer statusCode;
	private String message;
	private Long userId;

	public AccountStatusDTO() {
		super();
	}

	public AccountStatusDTO(Integer statusCode, String message, Long userId) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.userId = userId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AccountStatusDTO [statusCode=" + statusCode + ", message=" + message + ", userId=" + userId + "]";
	}

}
