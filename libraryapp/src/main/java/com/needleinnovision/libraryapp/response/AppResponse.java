package com.needleinnovision.libraryapp.response;

public class AppResponse {
	private Object responseData;
	
	public AppResponse(Object responseData) {
		super();
		this.responseData = responseData;
	}
	public Object getResponseData() {
		return responseData;
	}
	public AppResponse setResponseData(Object responseData) {
		this.responseData = responseData;
		return this;
	}
	@Override
	public String toString() {
		if (responseData == null) {
			return "AppResponse [responseData=" + responseData + "]";
		} else {
			return "AppResponse [responseData=" + responseData.toString() + "]";
		}
	}
}

