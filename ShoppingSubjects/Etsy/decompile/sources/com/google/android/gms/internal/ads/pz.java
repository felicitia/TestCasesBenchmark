package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class pz extends ByteArrayOutputStream {
    private final gt a;

    public pz(gt gtVar, int i) {
        this.a = gtVar;
        this.buf = this.a.a(Math.max(i, 256));
    }

    private final void a(int i) {
        if (this.count + i > this.buf.length) {
            byte[] a2 = this.a.a((this.count + i) << 1);
            System.arraycopy(this.buf, 0, a2, 0, this.count);
            this.a.a(this.buf);
            this.buf = a2;
        }
    }

    public final void close() throws IOException {
        this.a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.a.a(this.buf);
    }

    public final synchronized void write(int i) {
        a(1);
        super.write(i);
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        a(i2);
        super.write(bArr, i, i2);
    }
}
