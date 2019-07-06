package com.crittercism.internal;

public final class bk extends Exception {
    private static final long serialVersionUID = 4511293437269420307L;

    public bk(String str, Throwable th) {
        super(str, th);
    }

    public bk(String str) {
        this(str, null);
    }

    public bk(Throwable th) {
        super(th);
    }
}
