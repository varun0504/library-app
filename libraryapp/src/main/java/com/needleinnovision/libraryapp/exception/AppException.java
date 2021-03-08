package com.needleinnovision.libraryapp.exception;

public class AppException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private ErrorDetails errorDetails;

    public AppException(ErrorDetails errorDetails){
    	this.errorDetails = errorDetails;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}