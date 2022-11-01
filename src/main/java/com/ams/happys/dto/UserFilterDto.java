package com.ams.happys.dto;

public class UserFilterDto {

	private String keyword;

	private Boolean active;

	private RoleDto[] roles;

	private UserGroupDto[] groups;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public RoleDto[] getRoles() {
		return roles;
	}

	public void setRoles(RoleDto[] roles) {
		this.roles = roles;
	}

	public UserGroupDto[] getGroups() {
		return groups;
	}

	public void setGroups(UserGroupDto[] groups) {
		this.groups = groups;
	}

}
