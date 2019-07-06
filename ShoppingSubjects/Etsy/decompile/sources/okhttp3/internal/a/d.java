package okhttp3.internal.a;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache */
public final class d implements Closeable, Flushable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static final /* synthetic */ boolean j = true;
    final okhttp3.internal.d.a b;
    final int c;
    okio.d d;
    final LinkedHashMap<String, b> e;
    int f;
    boolean g;
    boolean h;
    boolean i;
    private long k;
    private long l;
    private long m;
    private final Executor n;
    private final Runnable o;

    /* compiled from: DiskLruCache */
    public final class a {
        final b a;
        final boolean[] b;
        final /* synthetic */ d c;
        private boolean d;

        /* access modifiers changed from: 0000 */
        public void a() {
            if (this.a.f == this) {
                for (int i = 0; i < this.c.c; i++) {
                    try {
                        this.c.b.a(this.a.d[i]);
                    } catch (IOException unused) {
                    }
                }
                this.a.f = null;
            }
        }

        public void b() throws IOException {
            synchronized (this.c) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.a.f == this) {
                    this.c.a(this, false);
                }
                this.d = true;
            }
        }
    }

    /* compiled from: DiskLruCache */
    private final class b {
        final String a;
        final long[] b;
        final File[] c;
        final File[] d;
        boolean e;
        a f;
        long g;

        /* access modifiers changed from: 0000 */
        public void a(okio.d dVar) throws IOException {
            for (long m : this.b) {
                dVar.k(32).m(m);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fd, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(okhttp3.internal.a.d.a r12, boolean r13) throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            okhttp3.internal.a.d$b r0 = r12.a     // Catch:{ all -> 0x00fe }
            okhttp3.internal.a.d$a r1 = r0.f     // Catch:{ all -> 0x00fe }
            if (r1 == r12) goto L_0x000d
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fe }
            r12.<init>()     // Catch:{ all -> 0x00fe }
            throw r12     // Catch:{ all -> 0x00fe }
        L_0x000d:
            r1 = 0
            if (r13 == 0) goto L_0x004d
            boolean r2 = r0.e     // Catch:{ all -> 0x00fe }
            if (r2 != 0) goto L_0x004d
            r2 = r1
        L_0x0015:
            int r3 = r11.c     // Catch:{ all -> 0x00fe }
            if (r2 >= r3) goto L_0x004d
            boolean[] r3 = r12.b     // Catch:{ all -> 0x00fe }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x00fe }
            if (r3 != 0) goto L_0x0039
            r12.b()     // Catch:{ all -> 0x00fe }
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fe }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            r13.<init>()     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r13.append(r0)     // Catch:{ all -> 0x00fe }
            r13.append(r2)     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x00fe }
            r12.<init>(r13)     // Catch:{ all -> 0x00fe }
            throw r12     // Catch:{ all -> 0x00fe }
        L_0x0039:
            okhttp3.internal.d.a r3 = r11.b     // Catch:{ all -> 0x00fe }
            java.io.File[] r4 = r0.d     // Catch:{ all -> 0x00fe }
            r4 = r4[r2]     // Catch:{ all -> 0x00fe }
            boolean r3 = r3.b(r4)     // Catch:{ all -> 0x00fe }
            if (r3 != 0) goto L_0x004a
            r12.b()     // Catch:{ all -> 0x00fe }
            monitor-exit(r11)
            return
        L_0x004a:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x004d:
            int r12 = r11.c     // Catch:{ all -> 0x00fe }
            if (r1 >= r12) goto L_0x0087
            java.io.File[] r12 = r0.d     // Catch:{ all -> 0x00fe }
            r12 = r12[r1]     // Catch:{ all -> 0x00fe }
            if (r13 == 0) goto L_0x007f
            okhttp3.internal.d.a r2 = r11.b     // Catch:{ all -> 0x00fe }
            boolean r2 = r2.b(r12)     // Catch:{ all -> 0x00fe }
            if (r2 == 0) goto L_0x0084
            java.io.File[] r2 = r0.c     // Catch:{ all -> 0x00fe }
            r2 = r2[r1]     // Catch:{ all -> 0x00fe }
            okhttp3.internal.d.a r3 = r11.b     // Catch:{ all -> 0x00fe }
            r3.a(r12, r2)     // Catch:{ all -> 0x00fe }
            long[] r12 = r0.b     // Catch:{ all -> 0x00fe }
            r3 = r12[r1]     // Catch:{ all -> 0x00fe }
            okhttp3.internal.d.a r12 = r11.b     // Catch:{ all -> 0x00fe }
            long r5 = r12.c(r2)     // Catch:{ all -> 0x00fe }
            long[] r12 = r0.b     // Catch:{ all -> 0x00fe }
            r12[r1] = r5     // Catch:{ all -> 0x00fe }
            long r7 = r11.l     // Catch:{ all -> 0x00fe }
            long r9 = r7 - r3
            long r2 = r9 + r5
            r11.l = r2     // Catch:{ all -> 0x00fe }
            goto L_0x0084
        L_0x007f:
            okhttp3.internal.d.a r2 = r11.b     // Catch:{ all -> 0x00fe }
            r2.a(r12)     // Catch:{ all -> 0x00fe }
        L_0x0084:
            int r1 = r1 + 1
            goto L_0x004d
        L_0x0087:
            int r12 = r11.f     // Catch:{ all -> 0x00fe }
            r1 = 1
            int r12 = r12 + r1
            r11.f = r12     // Catch:{ all -> 0x00fe }
            r12 = 0
            r0.f = r12     // Catch:{ all -> 0x00fe }
            boolean r12 = r0.e     // Catch:{ all -> 0x00fe }
            r12 = r12 | r13
            r2 = 10
            r3 = 32
            if (r12 == 0) goto L_0x00c4
            r0.e = r1     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            java.lang.String r1 = "CLEAN"
            okio.d r12 = r12.b(r1)     // Catch:{ all -> 0x00fe }
            r12.k(r3)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            java.lang.String r1 = r0.a     // Catch:{ all -> 0x00fe }
            r12.b(r1)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            r0.a(r12)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            r12.k(r2)     // Catch:{ all -> 0x00fe }
            if (r13 == 0) goto L_0x00e2
            long r12 = r11.m     // Catch:{ all -> 0x00fe }
            r1 = 1
            long r3 = r12 + r1
            r11.m = r3     // Catch:{ all -> 0x00fe }
            r0.g = r12     // Catch:{ all -> 0x00fe }
            goto L_0x00e2
        L_0x00c4:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.a.d$b> r12 = r11.e     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = r0.a     // Catch:{ all -> 0x00fe }
            r12.remove(r13)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = "REMOVE"
            okio.d r12 = r12.b(r13)     // Catch:{ all -> 0x00fe }
            r12.k(r3)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = r0.a     // Catch:{ all -> 0x00fe }
            r12.b(r13)     // Catch:{ all -> 0x00fe }
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            r12.k(r2)     // Catch:{ all -> 0x00fe }
        L_0x00e2:
            okio.d r12 = r11.d     // Catch:{ all -> 0x00fe }
            r12.flush()     // Catch:{ all -> 0x00fe }
            long r12 = r11.l     // Catch:{ all -> 0x00fe }
            long r0 = r11.k     // Catch:{ all -> 0x00fe }
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x00f5
            boolean r12 = r11.a()     // Catch:{ all -> 0x00fe }
            if (r12 == 0) goto L_0x00fc
        L_0x00f5:
            java.util.concurrent.Executor r12 = r11.n     // Catch:{ all -> 0x00fe }
            java.lang.Runnable r13 = r11.o     // Catch:{ all -> 0x00fe }
            r12.execute(r13)     // Catch:{ all -> 0x00fe }
        L_0x00fc:
            monitor-exit(r11)
            return
        L_0x00fe:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.a.d.a(okhttp3.internal.a.d$a, boolean):void");
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.f >= 2000 && this.f >= this.e.size();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(b bVar) throws IOException {
        if (bVar.f != null) {
            bVar.f.a();
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            this.b.a(bVar.c[i2]);
            this.l -= bVar.b[i2];
            bVar.b[i2] = 0;
        }
        this.f++;
        this.d.b("REMOVE").k(32).b(bVar.a).k(10);
        this.e.remove(bVar.a);
        if (a()) {
            this.n.execute(this.o);
        }
        return true;
    }

    public synchronized boolean b() {
        return this.h;
    }

    private synchronized void d() {
        if (b()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.g) {
            d();
            c();
            this.d.flush();
        }
    }

    public synchronized void close() throws IOException {
        b[] bVarArr;
        if (this.g) {
            if (!this.h) {
                for (b bVar : (b[]) this.e.values().toArray(new b[this.e.size()])) {
                    if (bVar.f != null) {
                        bVar.f.b();
                    }
                }
                c();
                this.d.close();
                this.d = null;
                this.h = true;
                return;
            }
        }
        this.h = true;
    }

    /* access modifiers changed from: 0000 */
    public void c() throws IOException {
        while (this.l > this.k) {
            a((b) this.e.values().iterator().next());
        }
        this.i = false;
    }
}
