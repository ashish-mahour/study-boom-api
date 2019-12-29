package com.studyboom.dtos;

public class TestSeriesReportDTO {

	private String testSeriesName;
	private Integer overAllRating;

	public TestSeriesReportDTO(String testSeriesName, Integer overAllRating) {
		super();
		this.testSeriesName = testSeriesName;
		this.overAllRating = overAllRating;
	}

	public TestSeriesReportDTO() {
		super();
	}

	public String getTestSeriesName() {
		return testSeriesName;
	}

	public void setTestSeriesName(String testSeriesName) {
		this.testSeriesName = testSeriesName;
	}

	public Integer getOverAllRating() {
		return overAllRating;
	}

	public void setOverAllRating(Integer overAllRating) {
		this.overAllRating = overAllRating;
	}

	@Override
	public String toString() {
		return "TestSeriesReportDTO [testSeriesName=" + testSeriesName + ", overAllRating=" + overAllRating + "]";
	}

}
