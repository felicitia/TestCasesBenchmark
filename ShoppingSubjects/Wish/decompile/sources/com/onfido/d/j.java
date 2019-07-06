package com.onfido.d;

public abstract class j extends Exception {
    protected static final boolean a = (System.getProperty("surefire.test.class.path") != null);
    protected static final StackTraceElement[] b = new StackTraceElement[0];

    j() {
    }

    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
