package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.g.d;
import com.bumptech.glide.load.f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: DecodeJob */
class a<A, T, Z> {
    private static final b a = new b();
    private final e b;
    private final int c;
    private final int d;
    private final com.bumptech.glide.load.a.c<A> e;
    private final com.bumptech.glide.e.b<A, T> f;
    private final f<T> g;
    private final com.bumptech.glide.load.resource.c.c<T, Z> h;
    private final C0011a i;
    private final DiskCacheStrategy j;
    private final Priority k;
    /* access modifiers changed from: private */
    public final b l;
    private volatile boolean m;

    /* renamed from: com.bumptech.glide.load.engine.a$a reason: collision with other inner class name */
    /* compiled from: DecodeJob */
    interface C0011a {
        com.bumptech.glide.load.engine.a.a a();
    }

    /* compiled from: DecodeJob */
    static class b {
        b() {
        }

        public OutputStream a(File file) throws FileNotFoundException {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }

    /* compiled from: DecodeJob */
    class c<DataType> implements com.bumptech.glide.load.engine.a.a.b {
        private final com.bumptech.glide.load.a<DataType> b;
        private final DataType c;

        public c(com.bumptech.glide.load.a<DataType> aVar, DataType datatype) {
            this.b = aVar;
            this.c = datatype;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x002f A[Catch:{ all -> 0x0023 }] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0038 A[SYNTHETIC, Splitter:B:20:0x0038] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x003f A[SYNTHETIC, Splitter:B:26:0x003f] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(java.io.File r5) {
            /*
                r4 = this;
                r0 = 0
                com.bumptech.glide.load.engine.a r1 = com.bumptech.glide.load.engine.a.this     // Catch:{ FileNotFoundException -> 0x0025 }
                com.bumptech.glide.load.engine.a$b r1 = r1.l     // Catch:{ FileNotFoundException -> 0x0025 }
                java.io.OutputStream r5 = r1.a(r5)     // Catch:{ FileNotFoundException -> 0x0025 }
                com.bumptech.glide.load.a<DataType> r0 = r4.b     // Catch:{ FileNotFoundException -> 0x001e, all -> 0x0019 }
                DataType r1 = r4.c     // Catch:{ FileNotFoundException -> 0x001e, all -> 0x0019 }
                boolean r0 = r0.a(r1, r5)     // Catch:{ FileNotFoundException -> 0x001e, all -> 0x0019 }
                if (r5 == 0) goto L_0x003c
                r5.close()     // Catch:{ IOException -> 0x003c }
                goto L_0x003c
            L_0x0019:
                r0 = move-exception
                r3 = r0
                r0 = r5
                r5 = r3
                goto L_0x003d
            L_0x001e:
                r0 = move-exception
                r3 = r0
                r0 = r5
                r5 = r3
                goto L_0x0026
            L_0x0023:
                r5 = move-exception
                goto L_0x003d
            L_0x0025:
                r5 = move-exception
            L_0x0026:
                java.lang.String r1 = "DecodeJob"
                r2 = 3
                boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch:{ all -> 0x0023 }
                if (r1 == 0) goto L_0x0036
                java.lang.String r1 = "DecodeJob"
                java.lang.String r2 = "Failed to find file to write to disk cache"
                android.util.Log.d(r1, r2, r5)     // Catch:{ all -> 0x0023 }
            L_0x0036:
                if (r0 == 0) goto L_0x003b
                r0.close()     // Catch:{ IOException -> 0x003b }
            L_0x003b:
                r0 = 0
            L_0x003c:
                return r0
            L_0x003d:
                if (r0 == 0) goto L_0x0042
                r0.close()     // Catch:{ IOException -> 0x0042 }
            L_0x0042:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.a.c.a(java.io.File):boolean");
        }
    }

    public a(e eVar, int i2, int i3, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.c.c<T, Z> cVar2, C0011a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority) {
        this(eVar, i2, i3, cVar, bVar, fVar, cVar2, aVar, diskCacheStrategy, priority, a);
    }

    a(e eVar, int i2, int i3, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.c.c<T, Z> cVar2, C0011a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority, b bVar2) {
        this.b = eVar;
        this.c = i2;
        this.d = i3;
        this.e = cVar;
        this.f = bVar;
        this.g = fVar;
        this.h = cVar2;
        this.i = aVar;
        this.j = diskCacheStrategy;
        this.k = priority;
        this.l = bVar2;
    }

    public i<Z> a() throws Exception {
        if (!this.j.cacheResult()) {
            return null;
        }
        long a2 = d.a();
        i a3 = a((com.bumptech.glide.load.b) this.b);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded transformed from cache", a2);
        }
        long a4 = d.a();
        i<Z> d2 = d(a3);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transcoded transformed from cache", a4);
        }
        return d2;
    }

    public i<Z> b() throws Exception {
        if (!this.j.cacheSource()) {
            return null;
        }
        long a2 = d.a();
        i a3 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded source from cache", a2);
        }
        return a(a3);
    }

    public i<Z> c() throws Exception {
        return a(e());
    }

    public void d() {
        this.m = true;
        this.e.c();
    }

    private i<Z> a(i<T> iVar) {
        long a2 = d.a();
        i c2 = c(iVar);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transformed resource from source", a2);
        }
        b(c2);
        long a3 = d.a();
        i<Z> d2 = d(c2);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transcoded transformed from source", a3);
        }
        return d2;
    }

    private void b(i<T> iVar) {
        if (iVar != null && this.j.cacheResult()) {
            long a2 = d.a();
            this.i.a().a(this.b, new c(this.f.d(), iVar));
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Wrote transformed from source to cache", a2);
            }
        }
    }

    private i<T> e() throws Exception {
        try {
            long a2 = d.a();
            Object a3 = this.e.a(this.k);
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Fetched data", a2);
            }
            if (this.m) {
                return null;
            }
            i<T> a4 = a((A) a3);
            this.e.a();
            return a4;
        } finally {
            this.e.a();
        }
    }

    private i<T> a(A a2) throws IOException {
        if (this.j.cacheSource()) {
            return b(a2);
        }
        long a3 = d.a();
        i<T> a4 = this.f.b().a(a2, this.c, this.d);
        if (!Log.isLoggable("DecodeJob", 2)) {
            return a4;
        }
        a("Decoded from source", a3);
        return a4;
    }

    private i<T> b(A a2) throws IOException {
        long a3 = d.a();
        this.i.a().a(this.b.a(), new c(this.f.c(), a2));
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Wrote source to cache", a3);
        }
        long a4 = d.a();
        i<T> a5 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2) && a5 != null) {
            a("Decoded source from cache", a4);
        }
        return a5;
    }

    private i<T> a(com.bumptech.glide.load.b bVar) throws IOException {
        File a2 = this.i.a().a(bVar);
        if (a2 == null) {
            return null;
        }
        try {
            i<T> a3 = this.f.a().a(a2, this.c, this.d);
            if (a3 == null) {
            }
            return a3;
        } finally {
            this.i.a().b(bVar);
        }
    }

    private i<T> c(i<T> iVar) {
        if (iVar == null) {
            return null;
        }
        i<T> a2 = this.g.a(iVar, this.c, this.d);
        if (!iVar.equals(a2)) {
            iVar.d();
        }
        return a2;
    }

    private i<Z> d(i<T> iVar) {
        if (iVar == null) {
            return null;
        }
        return this.h.a(iVar);
    }

    private void a(String str, long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(d.a(j2));
        sb.append(", key: ");
        sb.append(this.b);
        Log.v("DecodeJob", sb.toString());
    }
}
