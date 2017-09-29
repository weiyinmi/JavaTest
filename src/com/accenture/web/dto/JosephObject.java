package com.accenture.web.dto;

public class JosephObject extends DataTransferObject{
	private String start;
	private String interval;
	private String[] persons;
	
/*	public JosephObject(String start, String interval, JSONArray persons) {
		super();
		this.start = start;
		this.interval = interval;
		this.persons = persons;
	}*/

	public String[] getPersons() {
		return persons;
	}

	public void setPersons(String[] persons) {
		this.persons = persons;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

}
