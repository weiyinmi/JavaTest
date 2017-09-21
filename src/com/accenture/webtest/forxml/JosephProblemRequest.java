package com.accenture.webtest.forxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="josephProblemRequest")
public class JosephProblemRequest {
     	 
	 private Circle circle;
   
	@XmlElement(name="circle")
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
}
