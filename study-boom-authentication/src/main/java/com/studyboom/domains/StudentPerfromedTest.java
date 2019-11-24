package com.studyboom.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student_performed_test")
public class StudentPerfromedTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "performed_by")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Student performendByStudent;

	@JoinColumn(name = "test_performed")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private TestSeries testSeriesPerformed;

	@Column(name = "attemped", nullable = false)
	private Integer attemped;

	@Column(name = "unattemped", nullable = false)
	private Integer unattemped;

	@Column(name = "total_score", nullable = false)
	private Integer totalScore;

	@Column(name = "time_taken", nullable = false)
	private Integer timeTaken;

	@Column(name = "performed_at", nullable = false)
	private LocalDateTime performedAt;

	public StudentPerfromedTest() {
		super();
	}

	public StudentPerfromedTest(Student performendByStudent, TestSeries testSeriesPerformed, Integer attemped,
			Integer unattemped, Integer totalScore, Integer timeTaken, LocalDateTime performedAt) {
		super();
		this.performendByStudent = performendByStudent;
		this.testSeriesPerformed = testSeriesPerformed;
		this.attemped = attemped;
		this.unattemped = unattemped;
		this.totalScore = totalScore;
		this.timeTaken = timeTaken;
		this.performedAt = performedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getPerformendByStudent() {
		return performendByStudent;
	}

	public void setPerformendByStudent(Student performendByStudent) {
		this.performendByStudent = performendByStudent;
	}

	public TestSeries getTestSeriesPerformed() {
		return testSeriesPerformed;
	}

	public void setTestSeriesPerformed(TestSeries testSeriesPerformed) {
		this.testSeriesPerformed = testSeriesPerformed;
	}

	public Integer getAttemped() {
		return attemped;
	}

	public void setAttemped(Integer attemped) {
		this.attemped = attemped;
	}

	public Integer getUnattemped() {
		return unattemped;
	}

	public void setUnattemped(Integer unattemped) {
		this.unattemped = unattemped;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	public LocalDateTime getPerformedAt() {
		return performedAt;
	}

	public void setPerformedAt(LocalDateTime performedAt) {
		this.performedAt = performedAt;
	}

	@Override
	public String toString() {
		return "StudentPerfromedTest [id=" + id + ", performendByStudent=" + performendByStudent
				+ ", testSeriesPerformed=" + testSeriesPerformed + ", attemped=" + attemped + ", unattemped="
				+ unattemped + ", totalScore=" + totalScore + ", timeTaken=" + timeTaken + ", performedAt="
				+ performedAt + "]";
	}

}
