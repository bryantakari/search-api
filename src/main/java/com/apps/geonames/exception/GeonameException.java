package com.apps.geonames.exception;

public class GeonameException extends RuntimeException{
    public GeonameException(String message){
        super(message);
    }
    public GeonameException(String message, Throwable cause) {
        super(message, cause);
    }
}
