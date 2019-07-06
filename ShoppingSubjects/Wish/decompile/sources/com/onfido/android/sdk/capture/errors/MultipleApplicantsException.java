package com.onfido.android.sdk.capture.errors;

public final class MultipleApplicantsException extends RuntimeException {
    public MultipleApplicantsException() {
        super("You are not allowed to define multiple applicants.");
    }
}
