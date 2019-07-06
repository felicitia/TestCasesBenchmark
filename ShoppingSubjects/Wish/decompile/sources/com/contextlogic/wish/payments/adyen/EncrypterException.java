package com.contextlogic.wish.payments.adyen;

public class EncrypterException extends Exception {
    public EncrypterException(String str, Throwable th) {
        super(str, th);
    }
}
