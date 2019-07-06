package com.bumptech.glide.load.resource.a;

import com.bumptech.glide.load.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* compiled from: FileToStreamDecoder */
public class c<T> implements d<File, T> {
    private static final a a = new a();
    private d<InputStream, T> b;
    private final a c;

    /* compiled from: FileToStreamDecoder */
    static class a {
        a() {
        }

        public InputStream a(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }

    public String a() {
        return "";
    }

    public c(d<InputStream, T> dVar) {
        this(dVar, a);
    }

    c(d<InputStream, T> dVar, a aVar) {
        this.b = dVar;
        this.c = aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0019 A[SYNTHETIC, Splitter:B:14:0x0019] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.load.engine.i<T> a(java.io.File r3, int r4, int r5) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            com.bumptech.glide.load.resource.a.c$a r1 = r2.c     // Catch:{ all -> 0x0015 }
            java.io.InputStream r3 = r1.a(r3)     // Catch:{ all -> 0x0015 }
            com.bumptech.glide.load.d<java.io.InputStream, T> r0 = r2.b     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.load.engine.i r4 = r0.a(r3, r4, r5)     // Catch:{ all -> 0x0013 }
            if (r3 == 0) goto L_0x0012
            r3.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r4
        L_0x0013:
            r4 = move-exception
            goto L_0x0017
        L_0x0015:
            r4 = move-exception
            r3 = r0
        L_0x0017:
            if (r3 == 0) goto L_0x001c
            r3.close()     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.a.c.a(java.io.File, int, int):com.bumptech.glide.load.engine.i");
    }
}
