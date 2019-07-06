package com.bumptech.glide.load.a;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: ByteArrayFetcher */
public class b implements c<InputStream> {
    private final byte[] a;
    private final String b;

    public void a() {
    }

    public void c() {
    }

    public b(byte[] bArr, String str) {
        this.a = bArr;
        this.b = str;
    }

    /* renamed from: b */
    public InputStream a(Priority priority) {
        return new ByteArrayInputStream(this.a);
    }

    public String b() {
        return this.b;
    }
}
