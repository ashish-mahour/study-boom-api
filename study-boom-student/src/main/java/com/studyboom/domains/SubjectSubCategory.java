package com.studyboom.domains;

import java.util.List;

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
@Table(name = "subject_sub_category")
public class SubjectSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "subject_category_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private SubjectCategory subjectCategoryIdToSubCategory;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeries.class, mappedBy = "subjectSubCategoryIdToTestSeries")
	private List<TestSeries> subjectSubCategoryIdToTestSeries;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeriesData.class, mappedBy = "subjectSubCategoryIdToTestSeriesData")
	private List<TestSeriesData> subjectSubCategoryIdToTestSeriesData;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = StudentChoosenSubjectSubCategory.class, mappedBy = "subjectSubCategoryIdToChoosenSubCategories")
	private List<StudentChoosenSubjectSubCategory> subjectSubCategoryIdToChoosenSubCategories;

	public SubjectSubCategory() {
		super();
	}

	public SubjectSubCategory(SubjectCategory subjectCategoryIdToSubCategory, String name) {
		super();
		this.subjectCategoryIdToSubCategory = subjectCategoryIdToSubCategory;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubjectCategory getSubjectCategoryIdToSubCategory() {
		return subjectCategoryIdToSubCategory;
	}

	public void setSubjectCategoryIdToSubCategory(SubjectCategory subjectCategoryIdToSubCategory) {
		this.subjectCategoryIdToSubCategory = subjectCategoryIdToSubCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestSeries> getSubjectSubCategoryIdToTestSeries() {
		return subjectSubCategoryIdToTestSeries;
	}

	public void setSubjectSubCategoryIdToTestSeries(List<TestSeries> subjectSubCategoryIdToTestSeries) {
		this.subjectSubCategoryIdToTestSeries = subjectSubCategoryIdToTestSeries;
	}

	public List<TestSeriesData> getSubjectSubCategoryIdToTestSeriesData() {
		return subjectSubCategoryIdToTestSeriesData;
	}

	public void setSubjectSubCategoryIdToTestSeriesData(List<TestSeriesData> subjectSubCategoryIdToTestSeriesData) {
		this.subjectSubCategoryIdToTestSeriesData = subjectSubCategoryIdToTestSeriesData;
	}

	public List<StudentChoosenSubjectSubCategory> getSubjectSubCategoryIdToChoosenSubCategories() {
		return subjectSubCategoryIdToChoosenSubCategories;
	}

	public void setSubjectSubCategoryIdToChoosenSubCategories(
			List<StudentChoosenSubjectSubCategory> subjectSubCategoryIdToChoosenSubCategories) {
		this.subjectSubCategoryIdToChoosenSubCategories = subjectSubCategoryIdToChoosenSubCategories;
	}

	@Override
	public String toString() {
		return "SubjectSubCategory [id=" + id + ", subjectCategoryIdToSubCategory=" + subjectCategoryIdToSubCategory
				+ ", name=" + name + "]";
	}

}
