package com.tss.bean;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Group {
	int groupId;
	String groupName;
	Date lastTimeChanged;
	String statusColor;
	List<Task> taskList;
	Group(){}
	Group(int groupId, String groupName, Date lastTimeChanged){
		this.groupId = groupId;
		this.groupName = groupName;
		this.lastTimeChanged = lastTimeChanged;
	}
	public int getGroupId() {
		return groupId;
	}
	@XmlElement
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	@XmlElement
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Date getLastTimeChanged() {
		return lastTimeChanged;
	}
	@XmlElement
	public void setLastTimeChanged(Date lastTimeChanged) {
		this.lastTimeChanged = lastTimeChanged;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	@XmlElement(name="Task")
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}