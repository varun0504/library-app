package com.needleinnovision.libraryapp.utils;

import java.beans.PropertyDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;

public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
	
	public static Object getProperty(Object obj, String fieldName) throws AppException{
		try {
			return new PropertyDescriptor(fieldName, obj.getClass()).getReadMethod().invoke(obj);
		}catch(Exception ex) {
			logger.error("Error occured while getting field value :"+fieldName, ex);
			throw new AppException(new ErrorDetails(5001, 2, "error", "Cannot read property value"));
		}
	}
}
