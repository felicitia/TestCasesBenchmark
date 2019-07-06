package okhttp3;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.internal.b;
import okhttp3.internal.b.g;
import okhttp3.internal.b.j;
import okhttp3.internal.e.f;

/* compiled from: RealCall */
final class x implements e {
    final w a;
    final j b;
    final y c;
    final boolean d;
    /* access modifiers changed from: private */
    public p e;
    private boolean f;

    /* compiled from: RealCall */
    final class a extends b {
        private final f c;

        a(f fVar) {
            super("OkHttp %s", x.this.f());
            this.c = fVar;
        }

        /* access modifiers changed from: 0000 */
        public String a() {
            return x.this.c.a().f();
        }

        /* access modifiers changed from: 0000 */
        public x b() {
            return x.this;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x003e A[SYNTHETIC, Splitter:B:14:0x003e] */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x005e A[Catch:{ all -> 0x0036 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void c() {
            /*
                r6 = this;
                r0 = 1
                r1 = 0
                okhttp3.x r2 = okhttp3.x.this     // Catch:{ IOException -> 0x0038 }
                okhttp3.aa r2 = r2.g()     // Catch:{ IOException -> 0x0038 }
                okhttp3.x r3 = okhttp3.x.this     // Catch:{ IOException -> 0x0038 }
                okhttp3.internal.b.j r3 = r3.b     // Catch:{ IOException -> 0x0038 }
                boolean r3 = r3.b()     // Catch:{ IOException -> 0x0038 }
                if (r3 == 0) goto L_0x0023
                okhttp3.f r1 = r6.c     // Catch:{ IOException -> 0x0021 }
                okhttp3.x r2 = okhttp3.x.this     // Catch:{ IOException -> 0x0021 }
                java.io.IOException r3 = new java.io.IOException     // Catch:{ IOException -> 0x0021 }
                java.lang.String r4 = "Canceled"
                r3.<init>(r4)     // Catch:{ IOException -> 0x0021 }
                r1.a(r2, r3)     // Catch:{ IOException -> 0x0021 }
                goto L_0x002a
            L_0x0021:
                r1 = move-exception
                goto L_0x003c
            L_0x0023:
                okhttp3.f r1 = r6.c     // Catch:{ IOException -> 0x0021 }
                okhttp3.x r3 = okhttp3.x.this     // Catch:{ IOException -> 0x0021 }
                r1.a(r3, r2)     // Catch:{ IOException -> 0x0021 }
            L_0x002a:
                okhttp3.x r0 = okhttp3.x.this
                okhttp3.w r0 = r0.a
                okhttp3.n r0 = r0.t()
                r0.b(r6)
                goto L_0x0071
            L_0x0036:
                r0 = move-exception
                goto L_0x0072
            L_0x0038:
                r0 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
            L_0x003c:
                if (r0 == 0) goto L_0x005e
                okhttp3.internal.e.f r0 = okhttp3.internal.e.f.c()     // Catch:{ all -> 0x0036 }
                r2 = 4
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0036 }
                r3.<init>()     // Catch:{ all -> 0x0036 }
                java.lang.String r4 = "Callback failure for "
                r3.append(r4)     // Catch:{ all -> 0x0036 }
                okhttp3.x r4 = okhttp3.x.this     // Catch:{ all -> 0x0036 }
                java.lang.String r4 = r4.e()     // Catch:{ all -> 0x0036 }
                r3.append(r4)     // Catch:{ all -> 0x0036 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0036 }
                r0.a(r2, r3, r1)     // Catch:{ all -> 0x0036 }
                goto L_0x002a
            L_0x005e:
                okhttp3.x r0 = okhttp3.x.this     // Catch:{ all -> 0x0036 }
                okhttp3.p r0 = r0.e     // Catch:{ all -> 0x0036 }
                okhttp3.x r2 = okhttp3.x.this     // Catch:{ all -> 0x0036 }
                r0.a(r2, r1)     // Catch:{ all -> 0x0036 }
                okhttp3.f r0 = r6.c     // Catch:{ all -> 0x0036 }
                okhttp3.x r2 = okhttp3.x.this     // Catch:{ all -> 0x0036 }
                r0.a(r2, r1)     // Catch:{ all -> 0x0036 }
                goto L_0x002a
            L_0x0071:
                return
            L_0x0072:
                okhttp3.x r1 = okhttp3.x.this
                okhttp3.w r1 = r1.a
                okhttp3.n r1 = r1.t()
                r1.b(r6)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.x.a.c():void");
        }
    }

    private x(w wVar, y yVar, boolean z) {
        this.a = wVar;
        this.c = yVar;
        this.d = z;
        this.b = new j(wVar, z);
    }

    static x a(w wVar, y yVar, boolean z) {
        x xVar = new x(wVar, yVar, z);
        xVar.e = wVar.y().a(xVar);
        return xVar;
    }

    public aa a() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        h();
        this.e.a((e) this);
        try {
            this.a.t().a(this);
            aa g = g();
            if (g == null) {
                throw new IOException("Canceled");
            }
            this.a.t().b(this);
            return g;
        } catch (IOException e2) {
            this.e.a((e) this, e2);
            throw e2;
        } catch (Throwable th) {
            this.a.t().b(this);
            throw th;
        }
    }

    private void h() {
        this.b.a(f.c().a("response.body().close()"));
    }

    public void a(f fVar) {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        h();
        this.e.a((e) this);
        this.a.t().a(new a(fVar));
    }

    public void b() {
        this.b.a();
    }

    public boolean c() {
        return this.b.b();
    }

    /* renamed from: d */
    public x clone() {
        return a(this.a, this.c, this.d);
    }

    /* access modifiers changed from: 0000 */
    public String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(c() ? "canceled " : "");
        sb.append(this.d ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(f());
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public String f() {
        return this.c.a().n();
    }

    /* access modifiers changed from: 0000 */
    public aa g() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.a.w());
        arrayList.add(this.b);
        arrayList.add(new okhttp3.internal.b.a(this.a.g()));
        arrayList.add(new okhttp3.internal.a.a(this.a.h()));
        arrayList.add(new okhttp3.internal.connection.a(this.a));
        if (!this.d) {
            arrayList.addAll(this.a.x());
        }
        arrayList.add(new okhttp3.internal.b.b(this.d));
        g gVar = new g(arrayList, null, null, null, 0, this.c, this, this.e, this.a.a(), this.a.b(), this.a.c());
        return gVar.a(this.c);
    }
}
