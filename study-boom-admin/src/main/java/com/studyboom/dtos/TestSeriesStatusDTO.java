package com.studyboom.dtos;

public class TestSeriesStatusDTO {

	private Long testSeriesId;
	private String message;
	private Integer status;

	public TestSeriesStatusDTO() {
	}

	public TestSeriesStatusDTO(Long testSeriesId, String message, Integer status) {
		super();
		this.testSeriesId = testSeriesId;
		this.message = message;
		this.status = status;
	}

	public Long getTestSeriesId() {
		return testSeriesId;
	}

	public void setTestSeriesId(Long testSeriesId) {
		this.testSeriesId = testSeriesId;
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
		return "TestSeriesStatusDTO [testSeriesId=" + testSeriesId + ", message=" + message + ", status=" + status
				+ "]";
	}

}
