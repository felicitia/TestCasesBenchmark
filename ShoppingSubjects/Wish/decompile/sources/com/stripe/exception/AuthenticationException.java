package com.stripe.exception;

public class AuthenticationException extends StripeException {
    public AuthenticationException(String str, String str2, Integer num) {
        super(str, str2, num);
    }
}
