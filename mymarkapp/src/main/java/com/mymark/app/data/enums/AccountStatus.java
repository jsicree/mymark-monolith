package com.mymark.app.data.enums;

public enum AccountStatus {
	NEW (1L, "New"),
	CURRENT (2L, "Current"),
	SUSPENDED (3L, "Suspended"),
	TERMINATED (4L, "Terminated");
	
    private final Long id;  
    private final String longName; 

    AccountStatus(Long id, String longName) {
        this.id = id;
        this.longName = longName;
    }
    public Long id() { return id; }
    public String longName() { return longName; }	

}
