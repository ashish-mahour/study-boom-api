package com.studyboom.dtos;

public class StudentChoosedAnswersDTO {

	private Long questionId;
	private String choosedAnswer;

	public StudentChoosedAnswersDTO() {
		super();
	}

	public StudentChoosedAnswersDTO(Long questionId, String choosedAnswer) {
		super();
		this.questionId = questionId;
		this.choosedAnswer = choosedAnswer;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getChoosedAnswer() {
		return choosedAnswer;
	}

	public void setChoosedAnswer(String choosedAnswer) {
		this.choosedAnswer = choosedAnswer;
	}

	@Override
	public String toString() {
		return "StudentChoosedAnswersDTO [questionId=" + questionId + ", choosedAnswer=" + choosedAnswer + "]";
	}

}
