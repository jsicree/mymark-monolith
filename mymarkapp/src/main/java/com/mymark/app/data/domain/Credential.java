package com.mymark.app.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CREDENTIAL")
public class Credential {

	@Id
	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name="PASSWORD")
	private String password;
	
	public Credential() {
		// TODO Auto-generated constructor stub
	}

	public Credential(Long customerId, String password) {
		super();
		this.customerId = customerId;
		this.password = password;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Credential [customerId=");
		builder.append(customerId);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

	
}
