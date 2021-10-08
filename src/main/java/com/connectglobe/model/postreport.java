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
@Table(name =  "postreport")
public class postreport {
	@Id
	private int post_id;
	private String state;
	private String taluka;
	private String your_report;
	private String your_status;
	public postreport()
	{
		
	}
	public postreport(int post_id, String state, String taluka, String your_report, String your_status) {
		super();
		this.post_id = post_id;
		this.state = state;
		this.taluka = taluka;
		this.your_report = your_report;
		this.your_status = your_status;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTaluka() {
		return taluka;
	}
	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}
	public String getYour_report() {
		return your_report;
	}
	public void setYour_report(String your_report) {
		this.your_report = your_report;
	}
	public String getYour_status() {
		return your_status;
	}
	public void setYour_status(String your_status) {
		this.your_status = your_status;
	}
	
}

