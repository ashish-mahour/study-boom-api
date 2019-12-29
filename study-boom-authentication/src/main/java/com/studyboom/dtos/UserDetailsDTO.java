package com.studyboom.dtos;

import java.util.List;

public class UserDetailsDTO {

	private Long id;
	private String fullName;
	private String username;
	private String email;
	private String password;
	private String type;
	private String profilePic;
	private String mobileNo;
	private String bankName;
	private String branchName;
	private String accountNo;
	private String ifscCode;
	private Boolean isActivated;
	private List<Long> choosedCategories;
	private List<Long> choosedSubCategories;

	public UserDetailsDTO() {
	}

	public UserDetailsDTO(Long id, String fullName, String username, String email, String password, String type,
			String profilePic, String mobileNo, String bankName, String branchName, String accountNo, String ifscCode,
			Boolean isActivated, List<Long> choosedCategories, List<Long> choosedSubCategories) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.profilePic = profilePic;
		this.mobileNo = mobileNo;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.isActivated = isActivated;
		this.choosedCategories = choosedCategories;
		this.choosedSubCategories = choosedSubCategories;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public List<Long> getChoosedCategories() {
		return choosedCategories;
	}

	public void setChoosedCategories(List<Long> choosedCategories) {
		this.choosedCategories = choosedCategories;
	}

	public List<Long> getChoosedSubCategories() {
		return choosedSubCategories;
	}

	public void setChoosedSubCategories(List<Long> choosedSubCategories) {
		this.choosedSubCategories = choosedSubCategories;
	}

	@Override
	public String toString() {
		return "UserDetailsDTO [id=" + id + ", fullName=" + fullName + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", type=" + type + ", profilePic=" + profilePic + ", mobileNo=" + mobileNo
				+ ", bankName=" + bankName + ", branchName=" + branchName + ", accountNo=" + accountNo + ", ifscCode="
				+ ifscCode + ", isActivated=" + isActivated + ", choosedCategories=" + choosedCategories
				+ ", choosedSubCategories=" + choosedSubCategories + "]";
	}

}
