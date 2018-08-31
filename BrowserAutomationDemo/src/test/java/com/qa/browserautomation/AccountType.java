package com.qa.browserautomation;

public enum AccountType {
	All_ACCOUNTS("All Accounts"), SELECTED_ACCOUNTS("Selected Accounts");

	private String type;

	AccountType(String type) {
		this.type = type;
	}

	public String type() {
		return this.type;
	}
}
