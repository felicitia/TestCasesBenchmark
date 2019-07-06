package com.usebutton.merchant.exception;

public class ButtonNetworkException extends Exception {
    public ButtonNetworkException(Exception exc) {
        super(exc);
    }

    public ButtonNetworkException(String str) {
        super(str);
    }
}
