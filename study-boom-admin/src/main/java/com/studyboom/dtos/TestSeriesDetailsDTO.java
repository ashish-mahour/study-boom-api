package com.studyboom.dtos;

import java.math.BigDecimal;
import java.util.List;

public class TestSeriesDetailsDTO {

	private Long testSeriesId;
	private String testSeriesName;
	private Long categoryId;
	private Long subCategoryId;
	private Integer totalQuestions;
	private Integer totalMarks;
	private Integer durationMax;
	private Integer passingMarks;
	private BigDecimal price;
	private Long adminId;
	private Boolean isVisible;
	private List<TestSeriesQuestionDTO> testSeriesQuestions;

	public TestSeriesDetailsDTO(Long testSeriesId, String testSeriesName, Long categoryId, Long subCategoryId,
			Integer totalQuestions, Integer totalMarks, Integer durationMax, Integer passingMarks, BigDecimal price,
			Long adminId, Boolean isVisible, List<TestSeriesQuestionDTO> testSeriesQuestions) {
		super();
		this.testSeriesId = testSeriesId;
		this.testSeriesName = testSeriesName;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.totalQuestions = totalQuestions;
		this.totalMarks = totalMarks;
		this.durationMax = durationMax;
		this.passingMarks = passingMarks;
		this.price = price;
		this.adminId = adminId;
		this.isVisible = isVisible;
		this.testSeriesQuestions = testSeriesQuestions;
	}

	public TestSeriesDetailsDTO() {

	}

	public Long getTestSeriesId() {
		return testSeriesId;
	}

	public void setTestSeriesId(Long testSeriesId) {
		this.testSeriesId = testSeriesId;
	}

	public String getTestSeriesName() {
		return testSeriesName;
	}

	public void setTestSeriesName(String testSeriesName) {
		this.testSeriesName = testSeriesName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Integer getDurationMax() {
		return durationMax;
	}

	public void setDurationMax(Integer durationMax) {
		this.durationMax = durationMax;
	}

	public Integer getPassingMarks() {
		return passingMarks;
	}

	public void setPassingMarks(Integer passingMarks) {
		this.passingMarks = passingMarks;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<TestSeriesQuestionDTO> getTestSeriesQuestions() {
		return testSeriesQuestions;
	}

	public void setTestSeriesQuestions(List<TestSeriesQuestionDTO> testSeriesQuestions) {
		this.testSeriesQuestions = testSeriesQuestions;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "TestSeriesDetailsDTO [testSeriesId=" + testSeriesId + ", testSeriesName=" + testSeriesName
				+ ", categoryId=" + categoryId + ", subCategoryId=" + subCategoryId + ", totalQuestions="
				+ totalQuestions + ", totalMarks=" + totalMarks + ", durationMax=" + durationMax + ", passingMarks="
				+ passingMarks + ", price=" + price + ", adminId=" + adminId + ", isVisible=" + isVisible
				+ ", testSeriesQuestions=" + testSeriesQuestions + "]";
	}

}
