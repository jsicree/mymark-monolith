package com.mymark.app.data.enums;

public enum AddressType {
	BILLING (1L, "Billing"),
	BILLING_SHIPPING (2L, "Billing / Shipping"),
	SHIPPING (3L, "Shipping");
	
    private final Long id;  
    private final String longName; 

    AddressType(Long id, String longName) {
        this.id = id;
        this.longName = longName;
    }
    public Long id() { return id; }
    public String longName() { return longName; }	

}
