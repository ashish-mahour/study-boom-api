package com.studyboom.domains;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject_category")
public class SubjectCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = SubjectSubCategory.class, mappedBy = "subjectCategoryIdToSubCategory")
	private Set<SubjectSubCategory> subjectCategoryIdToSubCategory = new TreeSet<SubjectSubCategory>();

	public SubjectCategory() {
		super();
	}

	public SubjectCategory(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SubjectSubCategory> getSubjectCategoryIdToSubCategory() {
		return subjectCategoryIdToSubCategory;
	}

	public void setSubjectCategoryIdToSubCategory(Set<SubjectSubCategory> subjectCategoryIdToSubCategory) {
		this.subjectCategoryIdToSubCategory = subjectCategoryIdToSubCategory;
	}

	@Override
	public String toString() {
		return "SubjectCategory [id=" + id + ", name=" + name + "]";
	}

}