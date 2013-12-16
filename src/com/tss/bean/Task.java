package com.tss.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
	Task(){}
	Task(int taskID, String taskName, String status, String description){
		this.taskId = taskID;
		this.taskName = taskName;
		this.status = status;
		this.description= description;
	}
	int taskId;
	String taskName;
	String status;
	String description;
	List<Execution> exeList;
	
	public int getTaskId() {
		return taskId;
	}
	@XmlElement
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	@XmlElement
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public List<Execution> getExeList() {
		return exeList;
	}
	@XmlElement(name="Execution")
	public void setExeList(List<Execution> exeList) {
		this.exeList = exeList;
	}
}