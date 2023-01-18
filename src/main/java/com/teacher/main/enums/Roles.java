package com.teacher.main.enums;

public enum Roles {

	PRINCIPAL("PRINCIPAL"), 
	HOD("HOD"), 
	TEACHER("TEACHER");

	private String roles;

	Roles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public static Roles getType(String type) {
		for (Roles billingType : Roles.values()) {
			if (billingType.getRoles().equals(type)) {
				return billingType;
			}
		}
		return Roles.TEACHER;
	}
}
