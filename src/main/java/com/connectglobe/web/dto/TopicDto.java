package com.connectglobe.web.dto;

import java.util.Date;

public class TopicDto {
	private String topicname;
	private String email;
	private String discuss;
	private Date date;
	
	public TopicDto()
	{
		
	}

	public TopicDto(String topicname, String email, String discuss, Date date) {
		super();
		this.topicname = topicname;
		this.email = email;
		this.discuss = discuss;
		this.date = date;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
