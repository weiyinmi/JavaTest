package com.forxml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="fibonacciSequenceRequest")
public class FibonacciSequenceRequest {
	
    private String length;
    
    @XmlElement(name="length")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
