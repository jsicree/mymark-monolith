package com.mymark.app.data.enums;

public enum InventoryStatus {
	AVAILABLE (1L, "Available"),
	SOLD (2L, "Sold"),
	IN_PROCESS (3L, "In Process"),
	RETURNED (4L, "Returned");
	
    private final Long id;  
    private final String longName; 

    InventoryStatus(Long id, String longName) {
        this.id = id;
        this.longName = longName;
    }
    public Long id() { return id; }
    public String longName() { return longName; }	

}
