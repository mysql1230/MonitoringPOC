package com.tss.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RootNode {

	List<City> cityList;
	public List<City> getCityList() {
		return cityList;
	}
	@XmlElement(name = "City")
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
}
