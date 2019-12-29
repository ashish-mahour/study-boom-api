package com.studyboom.dtos;

import java.util.List;

public class StudentPerformedTestDTO {

	private Long studentId;
	private Long testSeriesId;
	private Integer attempted;
	private Integer unattemped;
	private Integer timeTaken;
	private Integer totalQuestions;
	private List<StudentChoosedAnswersDTO> studentChoosedAnswers;

	public StudentPerformedTestDTO() {
		super();
	}

	public StudentPerformedTestDTO(Long studentId, Long testSeriesId, Integer attempted, Integer unattemped,
			Integer timeTaken, Integer totalQuestions, List<StudentChoosedAnswersDTO> studentChoosedAnswers) {
		super();
		this.studentId = studentId;
		this.testSeriesId = testSeriesId;
		this.attempted = attempted;
		this.unattemped = unattemped;
		this.timeTaken = timeTaken;
		this.totalQuestions = totalQuestions;
		this.studentChoosedAnswers = studentChoosedAnswers;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getTestSeriesId() {
		return testSeriesId;
	}

	public void setTestSeriesId(Long testSeriesId) {
		this.testSeriesId = testSeriesId;
	}

	public Integer getAttempted() {
		return attempted;
	}

	public void setAttempted(Integer attempted) {
		this.attempted = attempted;
	}

	public Integer getUnattemped() {
		return unattemped;
	}

	public void setUnattemped(Integer unattemped) {
		this.unattemped = unattemped;
	}

	public Integer getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public List<StudentChoosedAnswersDTO> getStudentChoosedAnswers() {
		return studentChoosedAnswers;
	}

	public void setStudentChoosedAnswers(List<StudentChoosedAnswersDTO> studentChoosedAnswers) {
		this.studentChoosedAnswers = studentChoosedAnswers;
	}

	@Override
	public String toString() {
		return "StudentPerformedTestDTO [studentId=" + studentId + ", testSeriesId=" + testSeriesId + ", attempted="
				+ attempted + ", unattemped=" + unattemped + ", timeTaken=" + timeTaken + ", totalQuestions="
				+ totalQuestions + ", studentChoosedAnswers=" + studentChoosedAnswers + "]";
	}

}
