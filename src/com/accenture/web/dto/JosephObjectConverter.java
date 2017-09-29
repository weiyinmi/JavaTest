package com.accenture.web.dto;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class JosephObjectConverter implements Converter<JosephObject> {

	Logger logger = Logger.getLogger(JosephObjectConverter.class);
    
	/**
	 * Converter JSONObject to java object for Joseph problem
	 * @param jsonObject  input values
	 * @return josephObject  java object for Joseph problem
	 */
	@Override
	public JosephObject fromJson(JSONObject jsonObject) {

		JosephObject josephObject = new JosephObject();
        
		//varify start
		if (jsonObject.has("start")) {

			if (jsonObject.getString("start") != null) {

				josephObject.setStart(jsonObject.getString("start"));

			} else {
				josephObject.setStart(null);
			}

		} else {

			logger.error("Start isn`t exists!");
		}
		
		//varify interval
		if (jsonObject.has("interval")) {
			if (jsonObject.getString("interval") != null) {
				josephObject.setInterval((String) jsonObject.getString("interval"));

			} else {
				josephObject.setInterval(null);
			}

		} else {

			logger.error("Interval isn`t exists!");
		}
		
		//varify persons
		if (jsonObject.has("persons")) {
			if (jsonObject.getJSONArray("persons") != null) {
				JSONArray jsonArray= jsonObject.getJSONArray("persons");
				int len =jsonArray.length();
				String[] array = new String[len] ;
				for(int i=0;i<len;i++){
					array[i]=jsonArray.getString(i);
				}
					
				josephObject.setPersons(array);

			} else {
				josephObject.setPersons(null);
			}

		} else {

			logger.error("Persons isn`t exists!");
		}
		return josephObject;
	}

	@Override
	public JSONObject toJson(JosephObject objJson) {
		
		return null;
	}

}
