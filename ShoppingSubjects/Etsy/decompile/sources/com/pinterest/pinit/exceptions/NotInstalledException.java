package com.pinterest.pinit.exceptions;

public class NotInstalledException extends RuntimeException {
    public static final String MESSAGE = "Pinterest for Android is not installed!";

    public NotInstalledException() {
        super(MESSAGE);
    }
}
