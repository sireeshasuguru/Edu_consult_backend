package com.educon.api_gateway.exception;

public class JwtExceptions extends RuntimeException {
    public JwtExceptions(String message) {
        super(message);
    }
}
