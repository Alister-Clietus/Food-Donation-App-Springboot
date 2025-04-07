package com.fooddonation.app.dto;

import org.json.simple.JSONObject;

public class ServiceResponse 
{

	public ServiceResponse(String code, String message, JSONObject details) 
	{
		this.message = message;
		this.details = details;
		this.code = code;
	}

	private String message;
	private String code;
	private JSONObject details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getDetails() {
		return details;
	}

	public void setDetails(JSONObject details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
