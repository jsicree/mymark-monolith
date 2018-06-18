package com.mymark.app.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.mymark.app.data.enums.AccountStatus;
import com.mymark.app.data.reference.State;

@Entity(name="ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)	
	private AccountStatus status;

	@OneToOne
	@JoinColumn(name = "BILLING_ADDRESS_ID")
	private Address billingAddress;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(AccountStatus status, Address billingAddress) {
		super();
		this.status = status;
		this.billingAddress = billingAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", billingAddress=");
		builder.append(billingAddress);
		builder.append("]");
		return builder.toString();
	}
	

}
