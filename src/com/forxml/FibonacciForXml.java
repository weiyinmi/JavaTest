package com.forxml;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="fibonacciSequenceResponse")
public class FibonacciForXml {
    private List<BigInteger> sequence;
 
	public List<BigInteger> getSequence() {
		return sequence;
	}
	
    @XmlElementWrapper(name="sequence")
	@XmlElement(name="value")
	public void setSequence(List<BigInteger> value) {
		this.sequence = value;
	}





    

}
