package com.studyboom.domains;

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
@Table(name = "student_choosen_subject_sub_categories")
public class StudentChoosenSubjectSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "student_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Student studentIdToChoosenSubCategories;

	@JoinColumn(name = "subject_sub_category_id")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private SubjectSubCategory subjectSubCategoryIdToChoosenSubCategories;

	@Column(name = "priority_level", nullable = true)
	private Integer priorityLevel;

	public StudentChoosenSubjectSubCategory() {
		super();
	}

	public StudentChoosenSubjectSubCategory(Student studentIdToChoosenSubCategories,
			SubjectSubCategory subjectSubCategoryIdToChoosenSubCategories, Integer priorityLevel) {
		super();
		this.studentIdToChoosenSubCategories = studentIdToChoosenSubCategories;
		this.subjectSubCategoryIdToChoosenSubCategories = subjectSubCategoryIdToChoosenSubCategories;
		this.priorityLevel = priorityLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudentIdToChoosenSubCategories() {
		return studentIdToChoosenSubCategories;
	}

	public void setStudentIdToChoosenSubCategories(Student studentIdToChoosenSubCategories) {
		this.studentIdToChoosenSubCategories = studentIdToChoosenSubCategories;
	}

	public SubjectSubCategory getSubjectSubCategoryIdToChoosenSubCategories() {
		return subjectSubCategoryIdToChoosenSubCategories;
	}

	public void setSubjectSubCategoryIdToChoosenSubCategories(
			SubjectSubCategory subjectSubCategoryIdToChoosenSubCategories) {
		this.subjectSubCategoryIdToChoosenSubCategories = subjectSubCategoryIdToChoosenSubCategories;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	@Override
	public String toString() {
		return "StudentChoosenSubjectSubCategory [id=" + id + ", studentIdToChoosenSubCategories="
				+ studentIdToChoosenSubCategories + ", subjectSubCategoryIdToChoosenSubCategories="
				+ subjectSubCategoryIdToChoosenSubCategories + ", priorityLevel=" + priorityLevel + "]";
	}

}
