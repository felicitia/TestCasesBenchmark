package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.connection.c;
import okhttp3.internal.connection.d;
import okhttp3.internal.connection.f;
import okhttp3.internal.connection.f.a;

/* compiled from: ConnectionPool */
public final class j {
    static final /* synthetic */ boolean c = true;
    private static final Executor d;
    final d a;
    boolean b;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque<c> h;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.internal.c.a("OkHttp ConnectionPool", true));
        d = threadPoolExecutor;
    }

    public j() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public j(int i, long j, TimeUnit timeUnit) {
        this.g = new Runnable() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x002b */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r8 = this;
                L_0x0000:
                    okhttp3.j r0 = okhttp3.j.this
                    long r1 = java.lang.System.nanoTime()
                    long r0 = r0.a(r1)
                    r2 = -1
                    int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r4 != 0) goto L_0x0011
                    return
                L_0x0011:
                    r2 = 0
                    int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r4 <= 0) goto L_0x0000
                    r2 = 1000000(0xf4240, double:4.940656E-318)
                    long r4 = r0 / r2
                    long r2 = r2 * r4
                    long r6 = r0 - r2
                    okhttp3.j r0 = okhttp3.j.this
                    monitor-enter(r0)
                    okhttp3.j r1 = okhttp3.j.this     // Catch:{ InterruptedException -> 0x002b }
                    int r2 = (int) r6     // Catch:{ InterruptedException -> 0x002b }
                    r1.wait(r4, r2)     // Catch:{ InterruptedException -> 0x002b }
                    goto L_0x002b
                L_0x0029:
                    r1 = move-exception
                    goto L_0x002d
                L_0x002b:
                    monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                    goto L_0x0000
                L_0x002d:
                    monitor-exit(r0)     // Catch:{ all -> 0x0029 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: okhttp3.j.AnonymousClass1.run():void");
            }
        };
        this.h = new ArrayDeque();
        this.a = new d();
        this.e = i;
        this.f = timeUnit.toNanos(j);
        if (j <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("keepAliveDuration <= 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public c a(a aVar, f fVar, ac acVar) {
        if (c || Thread.holdsLock(this)) {
            for (c cVar : this.h) {
                if (cVar.a(aVar, acVar)) {
                    fVar.a(cVar, true);
                    return cVar;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public Socket a(a aVar, f fVar) {
        if (c || Thread.holdsLock(this)) {
            for (c cVar : this.h) {
                if (cVar.a(aVar, null) && cVar.f() && cVar != fVar.c()) {
                    return fVar.a(cVar);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void a(c cVar) {
        if (c || Thread.holdsLock(this)) {
            if (!this.b) {
                this.b = true;
                d.execute(this.g);
            }
            this.h.add(cVar);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public boolean b(c cVar) {
        if (!c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (cVar.a || this.e == 0) {
            this.h.remove(cVar);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public long a(long j) {
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            int i = 0;
            c cVar = null;
            int i2 = 0;
            for (c cVar2 : this.h) {
                if (a(cVar2, j) > 0) {
                    i++;
                } else {
                    i2++;
                    long j3 = j - cVar2.e;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.f) {
                if (i2 <= this.e) {
                    if (i2 > 0) {
                        long j4 = this.f - j2;
                        return j4;
                    } else if (i > 0) {
                        long j5 = this.f;
                        return j5;
                    } else {
                        this.b = false;
                        return -1;
                    }
                }
            }
            this.h.remove(cVar);
            okhttp3.internal.c.a(cVar.d());
            return 0;
        }
    }

    private int a(c cVar, long j) {
        List<Reference<f>> list = cVar.d;
        int i = 0;
        while (i < list.size()) {
            Reference reference = (Reference) list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                a aVar = (a) reference;
                StringBuilder sb = new StringBuilder();
                sb.append("A connection to ");
                sb.append(cVar.b().a().a());
                sb.append(" was leaked. Did you forget to close a response body?");
                okhttp3.internal.e.f.c().a(sb.toString(), aVar.a);
                list.remove(i);
                cVar.a = true;
                if (list.isEmpty()) {
                    cVar.e = j - this.f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
