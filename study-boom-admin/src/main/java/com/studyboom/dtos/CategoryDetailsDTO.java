package com.studyboom.dtos;

import java.util.List;

public class CategoryDetailsDTO {

	private Long categoryId;
	private String categoryName;
	private List<SubCategoryDetails> subCategories;

	public CategoryDetailsDTO() {
		super();
	}

	public CategoryDetailsDTO(Long categoryId, String categoryName, List<SubCategoryDetails> subCategories) {
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

	public List<SubCategoryDetails> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryDetails> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public String toString() {
		return "CategoryDetailsDTO [categoryName=" + categoryName + ", subCategories=" + subCategories + "]";
	}

}
