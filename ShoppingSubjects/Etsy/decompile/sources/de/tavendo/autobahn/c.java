package de.tavendo.autobahn;

import java.io.ByteArrayOutputStream;

/* compiled from: NoCopyByteArrayOutputStream */
public class c extends ByteArrayOutputStream {
    public c() {
    }

    public c(int i) {
        super(i);
    }

    public byte[] a() {
        return this.buf;
    }
}
