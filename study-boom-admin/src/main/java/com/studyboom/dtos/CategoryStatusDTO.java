package com.studyboom.dtos;

public class CategoryStatusDTO {

	private Integer statusCode;
	private String message;
	private Long categoryId;

	public CategoryStatusDTO() {
	}

	public CategoryStatusDTO(Integer statusCode, String message, Long categoryId) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.categoryId = categoryId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "CategoryStatusDTO [statusCode=" + statusCode + ", message=" + message + ", categoryId=" + categoryId
				+ "]";
	}

}
