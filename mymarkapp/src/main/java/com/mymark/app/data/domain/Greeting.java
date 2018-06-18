package com.mymark.app.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mymark.app.data.enums.Language;

@Entity(name="GREETING")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="LANGUAGE")
	@Enumerated(EnumType.STRING)	
	private Language language;

	@Column(name="SIMPLE_MESSAGE")
	private String simpleMessage;

	@Column(name="NAMED_MESSAGE")
	private String namedMessage;
	
	public Greeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Greeting(Long id, Language language, String simpleMessage, String namedMessage) {
		super();
		this.id = id;
		this.language = language;
		this.simpleMessage = simpleMessage;
		this.namedMessage = namedMessage;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getSimpleMessage() {
		return simpleMessage;
	}

	public void setSimpleMessage(String simpleMessage) {
		this.simpleMessage = simpleMessage;
	}

	public String getNamedMessage() {
		return namedMessage;
	}

	public void setNamedMessage(String namedMessage) {
		this.namedMessage = namedMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Greeting [id=");
		builder.append(id);
		builder.append(", language=");
		builder.append(language);
		builder.append(", simpleMessage=");
		builder.append(simpleMessage);
		builder.append(", namedMessage=");
		builder.append(namedMessage);
		builder.append("]");
		return builder.toString();
	}
	
}
