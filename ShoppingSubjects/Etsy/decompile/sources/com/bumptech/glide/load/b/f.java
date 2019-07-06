package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a.c;
import java.io.InputStream;

/* compiled from: ImageVideoModelLoader */
public class f<A> implements l<A, g> {
    private final l<A, InputStream> a;
    private final l<A, ParcelFileDescriptor> b;

    /* compiled from: ImageVideoModelLoader */
    static class a implements c<g> {
        private final c<InputStream> a;
        private final c<ParcelFileDescriptor> b;

        public a(c<InputStream> cVar, c<ParcelFileDescriptor> cVar2) {
            this.a = cVar;
            this.b = cVar2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[SYNTHETIC, Splitter:B:14:0x0029] */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.load.b.g a(com.bumptech.glide.Priority r6) throws java.lang.Exception {
            /*
                r5 = this;
                com.bumptech.glide.load.a.c<java.io.InputStream> r0 = r5.a
                r1 = 2
                r2 = 0
                if (r0 == 0) goto L_0x0024
                com.bumptech.glide.load.a.c<java.io.InputStream> r0 = r5.a     // Catch:{ Exception -> 0x000f }
                java.lang.Object r0 = r0.a(r6)     // Catch:{ Exception -> 0x000f }
                java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Exception -> 0x000f }
                goto L_0x0025
            L_0x000f:
                r0 = move-exception
                java.lang.String r3 = "IVML"
                boolean r3 = android.util.Log.isLoggable(r3, r1)
                if (r3 == 0) goto L_0x001f
                java.lang.String r3 = "IVML"
                java.lang.String r4 = "Exception fetching input stream, trying ParcelFileDescriptor"
                android.util.Log.v(r3, r4, r0)
            L_0x001f:
                com.bumptech.glide.load.a.c<android.os.ParcelFileDescriptor> r3 = r5.b
                if (r3 != 0) goto L_0x0024
                throw r0
            L_0x0024:
                r0 = r2
            L_0x0025:
                com.bumptech.glide.load.a.c<android.os.ParcelFileDescriptor> r3 = r5.b
                if (r3 == 0) goto L_0x0045
                com.bumptech.glide.load.a.c<android.os.ParcelFileDescriptor> r3 = r5.b     // Catch:{ Exception -> 0x0032 }
                java.lang.Object r6 = r3.a(r6)     // Catch:{ Exception -> 0x0032 }
                android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6     // Catch:{ Exception -> 0x0032 }
                goto L_0x0046
            L_0x0032:
                r6 = move-exception
                java.lang.String r3 = "IVML"
                boolean r1 = android.util.Log.isLoggable(r3, r1)
                if (r1 == 0) goto L_0x0042
                java.lang.String r1 = "IVML"
                java.lang.String r3 = "Exception fetching ParcelFileDescriptor"
                android.util.Log.v(r1, r3, r6)
            L_0x0042:
                if (r0 != 0) goto L_0x0045
                throw r6
            L_0x0045:
                r6 = r2
            L_0x0046:
                com.bumptech.glide.load.b.g r1 = new com.bumptech.glide.load.b.g
                r1.<init>(r0, r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.b.f.a.a(com.bumptech.glide.Priority):com.bumptech.glide.load.b.g");
        }

        public void a() {
            if (this.a != null) {
                this.a.a();
            }
            if (this.b != null) {
                this.b.a();
            }
        }

        public String b() {
            if (this.a != null) {
                return this.a.b();
            }
            return this.b.b();
        }

        public void c() {
            if (this.a != null) {
                this.a.c();
            }
            if (this.b != null) {
                this.b.c();
            }
        }
    }

    public f(l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2) {
        if (lVar == null && lVar2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.a = lVar;
        this.b = lVar2;
    }

    public c<g> a(A a2, int i, int i2) {
        c a3 = this.a != null ? this.a.a(a2, i, i2) : null;
        c a4 = this.b != null ? this.b.a(a2, i, i2) : null;
        if (a3 == null && a4 == null) {
            return null;
        }
        return new a(a3, a4);
    }
}
