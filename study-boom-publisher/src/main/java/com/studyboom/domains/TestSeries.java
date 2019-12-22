package com.studyboom.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_series")
public class TestSeries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "uploaded_by")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Publisher uploadedByPublisher;

	@Column(name = "name", nullable = false)
	private String name;

	@JoinColumn(name = "subject_sub_category_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private SubjectSubCategory subjectSubCategoryIdToTestSeries;

	@Column(name = "total_questions", nullable = false)
	private Integer totalQuestions;

	@Column(name = "duration_min", nullable = false)
	private Integer durationMin;

	@Column(name = "total_marks", nullable = false)
	private Integer totalMarks;

	@Column(name = "passing_marks", nullable = false)
	private Integer passingMarks;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@Column(name = "modified_date", nullable = false)
	private LocalDateTime modifiedDate;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = TestSeriesData.class, mappedBy = "testSeriesIdToTestSeriesData")
	private Set<TestSeriesData> testSeriesIdToTestSeriesData = new TreeSet<TestSeriesData>();

	@OneToMany(fetch = FetchType.EAGER, targetEntity = TestSeriesRatings.class, mappedBy = "testSeriesIdToRatings")
	private Set<TestSeriesRatings> testSeriesIdToRatings = new TreeSet<TestSeriesRatings>();

	public TestSeries() {
		super();
	}

	public TestSeries(Publisher uploadedByPublisher, String name, SubjectSubCategory subjectSubCategoryIdToTestSeries,
			Integer totalQuestions, Integer durationMin, Integer totalMarks, Integer passingMarks, BigDecimal price,
			LocalDateTime createdDate, LocalDateTime modifiedDate) {
		super();
		this.uploadedByPublisher = uploadedByPublisher;
		this.name = name;
		this.subjectSubCategoryIdToTestSeries = subjectSubCategoryIdToTestSeries;
		this.totalQuestions = totalQuestions;
		this.durationMin = durationMin;
		this.totalMarks = totalMarks;
		this.passingMarks = passingMarks;
		this.price = price;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Publisher getUploadedByPublisher() {
		return uploadedByPublisher;
	}

	public void setUploadedByPublisher(Publisher uploadedByPublisher) {
		this.uploadedByPublisher = uploadedByPublisher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubjectSubCategory getSubjectSubCategoryIdToTestSeries() {
		return subjectSubCategoryIdToTestSeries;
	}

	public void setSubjectSubCategoryIdToTestSeries(SubjectSubCategory subjectSubCategoryIdToTestSeries) {
		this.subjectSubCategoryIdToTestSeries = subjectSubCategoryIdToTestSeries;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public Integer getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(Integer durationMin) {
		this.durationMin = durationMin;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
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

	public Set<TestSeriesData> getTestSeriesIdToTestSeriesData() {
		return testSeriesIdToTestSeriesData;
	}

	public void setTestSeriesIdToTestSeriesData(Set<TestSeriesData> testSeriesIdToTestSeriesData) {
		this.testSeriesIdToTestSeriesData = testSeriesIdToTestSeriesData;
	}

	public Set<TestSeriesRatings> getTestSeriesIdToRatings() {
		return testSeriesIdToRatings;
	}

	public void setTestSeriesIdToRatings(Set<TestSeriesRatings> testSeriesIdToRatings) {
		this.testSeriesIdToRatings = testSeriesIdToRatings;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "TestSeries [id=" + id + ", uploadedByPublisher=" + uploadedByPublisher + ", name=" + name
				+ ", subjectSubCategoryIdToTestSeries=" + subjectSubCategoryIdToTestSeries + ", totalQuestions="
				+ totalQuestions + ", durationMin=" + durationMin + ", totalMarks=" + totalMarks + ", passingMarks="
				+ passingMarks + ", price=" + price + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", testSeriesIdToTestSeriesData=" + testSeriesIdToTestSeriesData + ", testSeriesIdToRatings="
				+ testSeriesIdToRatings + "]";
	}

}
