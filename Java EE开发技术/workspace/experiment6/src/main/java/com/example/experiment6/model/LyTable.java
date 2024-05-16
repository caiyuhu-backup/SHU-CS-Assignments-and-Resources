package com.example.experiment6.model;
import java.io.Serializable;
import java.util.Date;

public class LyTable implements Serializable {
	private int id;
	private int userId;
	private Date date;
	private String title;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date=date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content=content;
	}

}
