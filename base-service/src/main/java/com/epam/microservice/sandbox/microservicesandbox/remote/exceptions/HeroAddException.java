package com.epam.microservice.sandbox.microservicesandbox.remote.exceptions;

public class HeroAddException extends RuntimeException {

    public HeroAddException() {
    }

    public HeroAddException(String s) {
        super(s);
    }
}
