package com.studyboom.dtos;

public class TestSeriesRatingStatusDTO {

	private String message;
	private Long ratingId;

	public TestSeriesRatingStatusDTO() {
		super();
	}

	public TestSeriesRatingStatusDTO(String message, Long ratingId) {
		super();
		this.message = message;
		this.ratingId = ratingId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getRatingId() {
		return ratingId;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	@Override
	public String toString() {
		return "TestSeriesRatingStatus [message=" + message + ", ratingId=" + ratingId + "]";
	}

}
