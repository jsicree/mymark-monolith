package com.mymark.app.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mymark.app.data.enums.AddressType;
import com.mymark.app.data.reference.State;


@Entity(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "PRIMARY_LINE")
	private String primaryAddressLine;

	@Column(name = "SECONDARY_LINE")
	private String secondaryAddressLine;

	@Column(name = "CITY")
	private String city;

	@ManyToOne
	@JoinColumn(name = "STATE_ID")
	private State state;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String primaryAddressLine, String secondaryAddressLine, String city, State state, String zipCode,
			AddressType addressType) {
		super();
		this.primaryAddressLine = primaryAddressLine;
		this.secondaryAddressLine = secondaryAddressLine;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.addressType = addressType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimaryAddressLine() {
		return primaryAddressLine;
	}

	public void setPrimaryAddressLine(String primaryAddressLine) {
		this.primaryAddressLine = primaryAddressLine;
	}

	public String getSecondaryAddressLine() {
		return secondaryAddressLine;
	}

	public void setSecondaryAddressLine(String secondaryAddressLine) {
		this.secondaryAddressLine = secondaryAddressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [id=");
		builder.append(id);
		builder.append(", primaryAddressLine=");
		builder.append(primaryAddressLine);
		builder.append(", secondaryAddressLine=");
		builder.append(secondaryAddressLine);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", addressType=");
		builder.append(addressType);
		builder.append("]");
		return builder.toString();
	}


}