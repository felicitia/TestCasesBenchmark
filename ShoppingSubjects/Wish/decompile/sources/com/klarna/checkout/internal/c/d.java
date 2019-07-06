package com.klarna.checkout.internal.c;

import java.io.PrintWriter;
import java.io.StringWriter;

final class d extends PrintWriter {
    public d() {
        super(new StringWriter());
    }

    public d(int i) {
        super(new StringWriter(i));
    }

    public final String a() {
        flush();
        return ((StringWriter) this.out).toString();
    }
}
