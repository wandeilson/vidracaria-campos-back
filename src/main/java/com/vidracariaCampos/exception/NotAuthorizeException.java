package com.vidracariaCampos.exception;

public class NotAuthorizeException extends RuntimeException{
    public NotAuthorizeException(String message) {
        super(message);
    }
}