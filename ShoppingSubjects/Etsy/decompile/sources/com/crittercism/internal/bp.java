package com.crittercism.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class bp extends bq {
    private static final long serialVersionUID = -5983887553268578751L;
    private String[] b = null;
    private boolean c;

    public bp(String str, String str2, String str3, boolean z) {
        super(str, str2, str3);
        this.b = str3.split("\\r\\n");
        this.c = z;
    }

    public final void printStackTrace(PrintStream printStream) {
        for (String append : this.b) {
            printStream.append(append);
            printStream.append("\n");
        }
    }

    public final void printStackTrace(PrintWriter printWriter) {
        for (String append : this.b) {
            printWriter.append(append);
            printWriter.append("\n");
        }
    }
}
