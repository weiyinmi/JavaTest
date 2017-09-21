package com.accenture.webtest.forxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="josephProblemResponse")
public class JosephForXml {
   private String lastPeople;

@XmlElement(name="people")  
public String getLastPeople() {
	return lastPeople;
}

public void setLastPeople(String people) {
	this.lastPeople = people;
}

}
