package com.studyboom.dtos;

import java.util.Set;

public class CategoryDetailsDTO {

	private Long categoryId;
	private String categoryName;
	private Set<SubCategoryDetails> subCategories;

	public CategoryDetailsDTO() {
		super();
	}

	public CategoryDetailsDTO(Long categoryId, String categoryName, Set<SubCategoryDetails> subCategories) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.subCategories = subCategories;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<SubCategoryDetails> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategoryDetails> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public String toString() {
		return "CategoryDetailsDTO [categoryName=" + categoryName + ", subCategories=" + subCategories + "]";
	}

}
