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

@Entity
@Table(name = "test_series_data")
public class TestSeriesData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "test_series_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private TestSeries testSeriesIdToTestSeriesData;

	@JoinColumn(name = "subject_sub_category_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private SubjectSubCategory subjectSubCategoryIdToTestSeriesData;

	@Column(name = "question_text", nullable = false)
	private String questionText;

	@Column(name = "answer_text", nullable = false)
	private String answerText;

	@Column(name = "choice1", nullable = false)
	private String choice1;

	@Column(name = "choice2", nullable = false)
	private String choice2;

	@Column(name = "choice3", nullable = false)
	private String choice3;

	@Column(name = "choice4", nullable = false)
	private String choice4;

	public TestSeriesData() {
		super();
	}

	public TestSeriesData(TestSeries testSeriesIdToTestSeriesData,
			SubjectSubCategory subjectSubCategoryIdToTestSeriesData, String questionText, String answerText,
			String choice1, String choice2, String choice3, String choice4) {
		super();
		this.testSeriesIdToTestSeriesData = testSeriesIdToTestSeriesData;
		this.subjectSubCategoryIdToTestSeriesData = subjectSubCategoryIdToTestSeriesData;
		this.questionText = questionText;
		this.answerText = answerText;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TestSeries getTestSeriesIdToTestSeriesData() {
		return testSeriesIdToTestSeriesData;
	}

	public void setTestSeriesIdToTestSeriesData(TestSeries testSeriesIdToTestSeriesData) {
		this.testSeriesIdToTestSeriesData = testSeriesIdToTestSeriesData;
	}

	public SubjectSubCategory getSubjectSubCategoryIdToTestSeriesData() {
		return subjectSubCategoryIdToTestSeriesData;
	}

	public void setSubjectSubCategoryIdToTestSeriesData(SubjectSubCategory subjectSubCategoryIdToTestSeriesData) {
		this.subjectSubCategoryIdToTestSeriesData = subjectSubCategoryIdToTestSeriesData;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	@Override
	public String toString() {
		return "TestSeiesData [id=" + id + ", testSeriesIdToTestSeriesData=" + testSeriesIdToTestSeriesData
				+ ", subjectSubCategoryIdToTestSeriesData=" + subjectSubCategoryIdToTestSeriesData + ", questionText="
				+ questionText + ", answerText=" + answerText + ", choice1=" + choice1 + ", choice2=" + choice2
				+ ", choice3=" + choice3 + ", choice4=" + choice4 + "]";
	}

}
