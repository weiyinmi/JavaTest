package com.accenture.webtest.dto;

import net.sf.json.JSONObject;

public interface Converter<T extends DataTransferObject> {
	public T fromJson(JSONObject jsonObject);
	public JSONObject toJson(T objJson);
}
