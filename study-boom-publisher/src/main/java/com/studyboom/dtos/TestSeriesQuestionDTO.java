package com.studyboom.dtos;

public class TestSeriesQuestionDTO {

	private Long questionId;
	private String questionText;
	private String answerText;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;

	public TestSeriesQuestionDTO(Long questionId, String questionText, String answerText, String choice1,
			String choice2, String choice3, String choice4) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.answerText = answerText;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
	}

	public TestSeriesQuestionDTO() {

	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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
		return "TestSeriesQuestionDTO [questionId=" + questionId + ", questionText=" + questionText + ", answerText="
				+ answerText + ", choice1=" + choice1 + ", choice2=" + choice2 + ", choice3=" + choice3 + ", choice4="
				+ choice4 + "]";
	}

}
