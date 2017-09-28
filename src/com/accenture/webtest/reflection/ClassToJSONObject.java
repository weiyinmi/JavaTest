package com.accenture.webtest.reflection;

import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassToJSONObject {
	/**
	 * Converter a object to JSONObject.
	 * 
	 * @param object
	 *            a java object,it also could be a nested object
	 * @return jsonObject a JSONObject
	 * @throws JSONException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public JSONObject objectToJSONObject(Object object)
			throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(object);
			return jsonObject;
		} catch (JSONException e) {

			jsonObject = new JSONObject();
		}
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			jsonObject.put(field.getName(), objectToJSONObject(field.get(object)));
		}

		return jsonObject;
	}
}
