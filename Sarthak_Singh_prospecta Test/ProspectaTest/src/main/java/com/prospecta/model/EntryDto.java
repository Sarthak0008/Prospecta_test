package com.prospecta.model;

public class EntryDto {
	
	public String title;
	public String description;
	public EntryDto() {
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EntryDto(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	
	

}
