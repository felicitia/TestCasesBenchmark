package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class ku extends FilterInputStream {
    private final long a;
    private long b;

    ku(InputStream inputStream, long j) {
        super(inputStream);
        this.a = j;
    }

    /* access modifiers changed from: 0000 */
    public final long a() {
        return this.a - this.b;
    }

    public final int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.b++;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.b += (long) read;
        }
        return read;
    }
}
