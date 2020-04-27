package com.studyboom.dtos;

public class TestSeriesReportDTO {

	private String testSeriesName;
	private Double attemped;
	private Integer totalMarks;
	private Double marksObtained;

	public TestSeriesReportDTO() {
	}

	public TestSeriesReportDTO(String testSeriesName, Double attemped, Integer totalMarks, Double marksObtained) {
		super();
		this.testSeriesName = testSeriesName;
		this.attemped = attemped;
		this.totalMarks = totalMarks;
		this.marksObtained = marksObtained;
	}

	public String getTestSeriesName() {
		return testSeriesName;
	}

	public void setTestSeriesName(String testSeriesName) {
		this.testSeriesName = testSeriesName;
	}

	public Double getAttemped() {
		return attemped;
	}

	public void setAttemped(Double attemped) {
		this.attemped = attemped;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Double marksObtained) {
		this.marksObtained = marksObtained;
	}

	@Override
	public String toString() {
		return "TestSeriesReportDTO [testSeriesName=" + testSeriesName + ", attemped=" + attemped + ", totalMarks="
				+ totalMarks + ", marksObtained=" + marksObtained + "]";
	}

}
