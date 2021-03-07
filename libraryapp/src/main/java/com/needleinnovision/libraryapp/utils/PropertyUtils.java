package com.needleinnovision.libraryapp.utils;

import java.beans.PropertyDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;

public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
	
	public static Object getPropertyValue(String field, Object obj) throws AppException{
		try {
			return new PropertyDescriptor(field, obj.getClass()).getReadMethod().invoke(obj);
		}catch(Exception ex) {
			logger.error("Error occured while getting field value :"+field, ex);
			throw new AppException(new ErrorDetails(5001, 2, "error", "Cannot read property value"),ex);
		}
	}
}
