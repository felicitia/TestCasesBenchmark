package com.facebook;

public class FacebookException extends RuntimeException {
    public FacebookException() {
    }

    public FacebookException(String str) {
        super(str);
    }

    public FacebookException(String str, Throwable th) {
        super(str, th);
    }

    public FacebookException(Throwable th) {
        super(th);
    }

    public String toString() {
        return getMessage();
    }
}
