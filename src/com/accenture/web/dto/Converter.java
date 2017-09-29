package com.accenture.web.dto;

import org.json.JSONObject;

public interface Converter<T extends DataTransferObject> {
	public T fromJson(JSONObject jsonObject);
	public JSONObject toJson(T objJson);	
}
