package com.studyboom.domains;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_sub_category")
public class SubjectSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "subject_category_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private SubjectCategory subjectCategoryIdToSubCategory;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeries.class, mappedBy = "subjectSubCategoryIdToTestSeries")
	private Set<TestSeries> subjectSubCategoryIdToTestSeries = new TreeSet<TestSeries>();

	@OneToMany(fetch = FetchType.LAZY, targetEntity = TestSeriesData.class, mappedBy = "subjectSubCategoryIdToTestSeriesData")
	private Set<TestSeriesData> subjectSubCategoryIdToTestSeriesData = new TreeSet<TestSeriesData>();

	@OneToOne(fetch = FetchType.LAZY, targetEntity = TestSeriesData.class, mappedBy = "subjectSubCategoryIdToChoosenSubCategories")
	private StudentChoosenSubjectSubCategory subjectSubCategoryIdToChoosenSubCategories;

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

	public Set<TestSeries> getSubjectSubCategoryIdToTestSeries() {
		return subjectSubCategoryIdToTestSeries;
	}

	public void setSubjectSubCategoryIdToTestSeries(Set<TestSeries> subjectSubCategoryIdToTestSeries) {
		this.subjectSubCategoryIdToTestSeries = subjectSubCategoryIdToTestSeries;
	}

	public Set<TestSeriesData> getSubjectSubCategoryIdToTestSeriesData() {
		return subjectSubCategoryIdToTestSeriesData;
	}

	public void setSubjectSubCategoryIdToTestSeriesData(Set<TestSeriesData> subjectSubCategoryIdToTestSeriesData) {
		this.subjectSubCategoryIdToTestSeriesData = subjectSubCategoryIdToTestSeriesData;
	}

	public StudentChoosenSubjectSubCategory getSubjectSubCategoryIdToChoosenSubCategories() {
		return subjectSubCategoryIdToChoosenSubCategories;
	}

	public void setSubjectSubCategoryIdToChoosenSubCategories(
			StudentChoosenSubjectSubCategory subjectSubCategoryIdToChoosenSubCategories) {
		this.subjectSubCategoryIdToChoosenSubCategories = subjectSubCategoryIdToChoosenSubCategories;
	}

	@Override
	public String toString() {
		return "SubjectSubCategory [id=" + id + ", subjectCategoryIdToSubCategory=" + subjectCategoryIdToSubCategory
				+ ", name=" + name + "]";
	}

}
