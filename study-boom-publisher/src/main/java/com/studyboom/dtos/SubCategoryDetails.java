package com.studyboom.dtos;

public class SubCategoryDetails {

	private Long subCategoryId;
	private String subCategoryName;

	public SubCategoryDetails() {
		super();
	}

	public SubCategoryDetails(Long subCategoryId, String subCategoryName) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	@Override
	public String toString() {
		return "SubCategoryDetails [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName + "]";
	}

}
