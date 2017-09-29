package com.accenture.web.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.accenture.web.servlet.ReflectionServlet;

public class ReflectionConverter {

	Logger logger = Logger.getLogger(ReflectionServlet.class);

	//		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
	//		propertyDescriptor.getWriteMethod();
    
	/**
	 * Converter JSONObject to java object
	 * @param clazz
	 * 			a arbitrary class
	 * @param jsonObject
	 * 			a JSONObject 
	 * @return object  a java object
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws JSONException
	 */
	public Object jsonToObject(Class<?> clazz, JSONObject jsonObject) throws ClassNotFoundException, SecurityException,
			IllegalAccessException, InvocationTargetException, InstantiationException, JSONException {

		Object object = clazz.newInstance();

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			Class<?> typeClazz = field.getType();

			if (typeClazz.isPrimitive()) {
				/*
				 * field.set(object,jsonObject.get(field.getName()));
				 * 
				 */
			} else {

				if (String.class.isAssignableFrom(field.getType())) {
					
					field.set(object, jsonObject.get(field.getName()));

				} else if (field.getType().isArray()
						&& (String.class.isAssignableFrom(field.getType().getComponentType()))) {

					JSONArray jArray = jsonObject.getJSONArray(field.getName());
					int len = jArray.length();
					String[] array = new String[len];
					for (int i = 0; i < len; i++) {

						array[i] = jArray.getString(i);
					}
					field.set(object, array);

				} else {

					field.set(object, jsonToObject(typeClazz, jsonObject.getJSONObject(field.getName())));
				}
			}
		}
		return object;
	}
}
