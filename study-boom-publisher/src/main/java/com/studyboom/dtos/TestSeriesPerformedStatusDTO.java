package com.studyboom.dtos;

public class TestSeriesPerformedStatusDTO {

	private Long performedId;
	private String message;
	private Integer status;

	public TestSeriesPerformedStatusDTO() {
		super();
	}

	public TestSeriesPerformedStatusDTO(Long performedId, String message, Integer status) {
		super();
		this.performedId = performedId;
		this.message = message;
		this.status = status;
	}

	public Long getPerformedId() {
		return performedId;
	}

	public void setPerformedId(Long performedId) {
		this.performedId = performedId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TestSeriesPerformedStatusDTO [performedId=" + performedId + ", message=" + message + ", status="
				+ status + "]";
	}

}
