package com.mymark.app.data.reference;

public enum Language {
	ENG (1L, "English"),
	SPA (2L, "Spanish"),
	FRA (3L, "French"),
	UNK (4L, "Unknown");
	
    private final Long id;  
    private final String longName; 

    Language(Long id, String longName) {
        this.id = id;
        this.longName = longName;
    }
    public Long id() { return id; }
    public String longName() { return longName; }	

}
