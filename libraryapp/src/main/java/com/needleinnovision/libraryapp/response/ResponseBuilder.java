package com.needleinnovision.libraryapp.response;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;

public class ResponseBuilder {
	
	private static Map<Integer, Integer> errorCodesmap = new HashMap<>();
	
	static {
		errorCodesmap.put(1, HttpServletResponse.SC_OK);
		errorCodesmap.put(2, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		errorCodesmap.put(3, HttpServletResponse.SC_UNAUTHORIZED);
		errorCodesmap.put(4, HttpServletResponse.SC_BAD_REQUEST);
	}

	public static AppResponse getErrorResponse(ErrorDetails errorDetails, HttpServletResponse response) {
		response.setStatus(getHttpResponseCode(errorDetails.getErrorTypeCode()));
		return new AppResponse(new ErrorResponse(errorDetails.getErrorCode(), errorDetails.getMessage()));
	}

	private static int getHttpResponseCode(int code) {
		return errorCodesmap.get(code) == null ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : errorCodesmap.get(code);
	}

	public static AppResponse getSuccessResponse() throws AppException {
		return getSuccessResponse(null);
	}

	public static AppResponse getSuccessResponse(Object responseData) throws AppException {
		return new AppResponse(responseData);
	}
}
