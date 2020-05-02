package com.studyboom.dtos;

public class TestSeriesRatingDTO {

	private Long studentId;
	private Long testSeriesId;
	private Integer difficulty;
	private Integer questionsQuality;
	private Integer answersQuality;
	private Integer priceRating;
	private Integer overallRatings;

	public TestSeriesRatingDTO() {
		super();
	}

	public TestSeriesRatingDTO(Long studentId, Long testSeriesId, Integer difficulty, Integer questionsQuality,
			Integer answersQuality, Integer priceRating, Integer overallRatings) {
		super();
		this.testSeriesId = testSeriesId;
		this.studentId = studentId;
		this.difficulty = difficulty;
		this.questionsQuality = questionsQuality;
		this.answersQuality = answersQuality;
		this.priceRating = priceRating;
		this.overallRatings = overallRatings;
	}

	public Long getTestSeriesId() {
		return testSeriesId;
	}

	public void setTestSeriesId(Long testSeriesId) {
		this.testSeriesId = testSeriesId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getQuestionsQuality() {
		return questionsQuality;
	}

	public void setQuestionsQuality(Integer questionsQuality) {
		this.questionsQuality = questionsQuality;
	}

	public Integer getAnswersQuality() {
		return answersQuality;
	}

	public void setAnswersQuality(Integer answersQuality) {
		this.answersQuality = answersQuality;
	}

	public Integer getPriceRating() {
		return priceRating;
	}

	public void setPriceRating(Integer priceRating) {
		this.priceRating = priceRating;
	}

	public Integer getOverallRatings() {
		return overallRatings;
	}

	public void setOverallRatings(Integer overallRatings) {
		this.overallRatings = overallRatings;
	}

	@Override
	public String toString() {
		return "TestSeriesRatingDTO [studentId=" + studentId + ", testSeriesId=" + testSeriesId + ", difficulty="
				+ difficulty + ", questionsQuality=" + questionsQuality + ", answersQuality=" + answersQuality
				+ ", priceRating=" + priceRating + ", overallRatings=" + overallRatings + "]";
	}

}
