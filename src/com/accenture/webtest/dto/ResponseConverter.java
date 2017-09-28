package com.accenture.webtest.dto;

import org.json.JSONObject;

public class ResponseConverter implements Converter<JosephCircleResponse> {

	@Override
	public JosephCircleResponse fromJson(JSONObject jsonObject) {

		return null;
	}
    
	/**
	 * Converter the response object to a JSONObject
	 * @param response  result of Joseph problem
	 * @return lastOne  JSONObject
	 */
	@Override
	public JSONObject toJson(JosephCircleResponse response) {			
		JSONObject lastOne = new JSONObject();		
	    lastOne.put("lastPeople", response.getLastPeople());
		return lastOne;
	}

}
