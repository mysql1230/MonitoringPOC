package com.tss.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class City {
	int cityId;
	String cityName;
	List<Group> groupList;
	City(){}
	public City(int cityId, String cityName){
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public int getCityId() {
		return cityId;
	}
	@XmlElement
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	@XmlElement
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	@XmlElement(name = "Group")
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
}
