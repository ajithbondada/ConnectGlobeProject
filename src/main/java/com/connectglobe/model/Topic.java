package com.connectglobe.model;

import java.util.*;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name =  "topic")
public class Topic {
	@Id
	private String topicname;
	private String email;
	private String discuss;
	private Date date;
	     
	
	public Topic()
	{
		
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


	public Topic(String topicname, String email, String discuss, Date date) {
		super();
		this.topicname = topicname;
		this.email = email;
		this.discuss = discuss;
		this.date = date;
	}

}

