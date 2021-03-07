package com.needleinnovision.libraryapp.exception;

public class AppException extends Throwable{

    private static final long serialVersionUID = 1L;
    private ErrorDetails errorDetails;
    private Throwable throwable;

    public AppException(ErrorDetails errorDetails,Throwable throwable){
    	this.errorDetails = errorDetails;
        this.throwable = throwable;
    }
    public AppException(ErrorDetails errorDetails) {
        super();
        this.errorDetails = errorDetails;
        this.throwable = null;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public Throwable getThrowable() {
        return throwable;
    }
    
}