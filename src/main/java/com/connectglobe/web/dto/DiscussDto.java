package com.connectglobe.web.dto;

public class DiscussDto {
	private String description;
	private int id;
	private String title;
	
	public DiscussDto()
	{
		
	}

	public DiscussDto(String description, int id, String title) {
		super();
		this.description = description;
		this.id = id;
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
