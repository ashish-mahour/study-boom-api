package com.studyboom.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_series_ratings")
public class TestSeriesRatings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "rated_by")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Student ratedByStudent;

	@JoinColumn(name = "test_series_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private TestSeries testSeriesIdToRatings;

	@Column(name = "difficulty", nullable = true)
	private Integer difficulty;

	@Column(name = "questions_quality", nullable = true)
	private Integer questionsQuality;

	@Column(name = "answers_quality", nullable = true)
	private Integer answersQuality;

	@Column(name = "price_rating", nullable = true)
	private Integer price_rating;

	@Column(name = "overall_ratings", nullable = false)
	private Integer overallRatings;

	public TestSeriesRatings() {
		super();
	}

	public TestSeriesRatings(Student ratedByStudent, TestSeries testSeriesIdToRatings, Integer difficulty,
			Integer questionsQuality, Integer answersQuality, Integer price_rating, Integer overallRatings) {
		super();
		this.ratedByStudent = ratedByStudent;
		this.testSeriesIdToRatings = testSeriesIdToRatings;
		this.difficulty = difficulty;
		this.questionsQuality = questionsQuality;
		this.answersQuality = answersQuality;
		this.price_rating = price_rating;
		this.overallRatings = overallRatings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getRatedByStudent() {
		return ratedByStudent;
	}

	public void setRatedByStudent(Student ratedByStudent) {
		this.ratedByStudent = ratedByStudent;
	}

	public TestSeries getTestSeriesIdToRatings() {
		return testSeriesIdToRatings;
	}

	public void setTestSeriesIdToRatings(TestSeries testSeriesIdToRatings) {
		this.testSeriesIdToRatings = testSeriesIdToRatings;
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

	public Integer getPrice_rating() {
		return price_rating;
	}

	public void setPrice_rating(Integer price_rating) {
		this.price_rating = price_rating;
	}

	public Integer getOverallRatings() {
		return overallRatings;
	}

	public void setOverallRatings(Integer overallRatings) {
		this.overallRatings = overallRatings;
	}

	@Override
	public String toString() {
		return "TestSeriesRatings [id=" + id + ", ratedByStudent=" + ratedByStudent + ", testSeriesIdToRatings="
				+ testSeriesIdToRatings + ", difficulty=" + difficulty + ", questionsQuality=" + questionsQuality
				+ ", answersQuality=" + answersQuality + ", price_rating=" + price_rating + ", overallRatings="
				+ overallRatings + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
