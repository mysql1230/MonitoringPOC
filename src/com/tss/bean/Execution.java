package com.tss.bean;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Execution {
	int exeId;
	Date timeWhen;
	String status;
	String description;
	String url;
	Execution(){}
	public Execution(int exeId, Date timeWhen, String status, String description, String url){
		this.exeId = exeId;
		this.timeWhen = timeWhen;
		this.status = status;
		this.description = description;
		this.url = url;
	}
	public int getExeId() {
		return exeId;
	}
	@XmlElement
	public void setExeId(int exeId) {
		this.exeId = exeId;
	}
	public Date getTimeWhen() {
		return timeWhen;
	}
	@XmlElement
	public void setTimeWhen(Date timeWhen) {
		this.timeWhen = timeWhen;
	}
	public String getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}	
	public String getUrl() {
		return url;
	}
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}
}