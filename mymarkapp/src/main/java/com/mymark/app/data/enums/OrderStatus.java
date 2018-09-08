package com.mymark.app.data.enums;

public enum OrderStatus {
	NEW (1L, "New"),
	SUBMITTED (2L, "Submitted"),
	COMPLETED (3L, "Completed"),
	CANCELLED (4L, "Cancelled");
	
    private final Long id;  
    private final String longName; 

    OrderStatus(Long id, String longName) {
        this.id = id;
        this.longName = longName;
    }
    public Long id() { return id; }
    public String longName() { return longName; }	

}
