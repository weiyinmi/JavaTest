package com.accenture.web.dto;

//import net.sf.json.JSONObject;
import org.json.JSONObject;

public class RequestConverter implements Converter<JosephCircleRequest> {

	@Override
	public JosephCircleRequest fromJson(JSONObject jsonObject) {

		/*
		 * JosephCircleRequest request = new JosephCircleRequest(); JosephObject
		 * josephObject =new JosephObject();
		 * 
		 * JSONObject jsonObj = jsonObject.getJSONObject("circle");
		 * 
		 * josephObject.setStart(jsonObj.getString("start"));
		 * josephObject.setInterval(jsonObj.getString("interval"));
		 * josephObject.setPersons(jsonObj.getJSONArray("persons"));
		 * 
		 * request.setCircle(josephObject);
		 */

		JosephCircleRequest request = new JosephCircleRequest();
		JSONObject jsonObj = jsonObject.getJSONObject("circle");

		JosephObjectConverter josephObjectConverter = new JosephObjectConverter();
        
		if (jsonObj != null) {
			request.setCircle(josephObjectConverter.fromJson(jsonObj));
			
		} else {
			request.setCircle(null);
		}

		return request;
	}

	@Override
	public JSONObject toJson(JosephCircleRequest objJson) {

		return null;
	}

}
