package com.google.zxing;

public final class FormatException extends ReaderException {
    private static final FormatException a;

    static {
        FormatException formatException = new FormatException();
        a = formatException;
        formatException.setStackTrace(NO_TRACE);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : a;
    }

    public static FormatException getFormatInstance(Throwable th) {
        return isStackTrace ? new FormatException(th) : a;
    }
}
