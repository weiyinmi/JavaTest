package com.accenture.webtest.dto;

public class JosephObject {
   private int start;
   private int interval;
   private String[] persons;
   
   public JosephObject(int start,int interval,String[] persons){
	   this.start = start;
	   this.interval = interval;
	   this.persons = persons;
   }
   
public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public int getInterval() {
	return interval;
}
public void setInterval(int interval) {
	this.interval = interval;
}
public String[] getPersons() {
	return persons;
}
public void setPersons(String[] persons) {
	this.persons = persons;
} 
   
}
