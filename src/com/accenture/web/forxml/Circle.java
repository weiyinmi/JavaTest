package com.accenture.web.forxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="circle")
public class Circle {
		
    private int start;		
	private int interval;
    private List<String> peoples;
    
	@XmlElementWrapper(name="peoples")
	@XmlElement(name="people")
	public List<String> getPeoples() {
		return peoples;
	}
	public void setPeoples(List<String> peoples) {
		this.peoples = peoples;
	}
	
	@XmlAttribute(name="start")
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	@XmlAttribute(name="interval")
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
}
