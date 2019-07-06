package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* compiled from: AsyncTimeout */
public class a extends u {
    private static final long a = TimeUnit.SECONDS.toMillis(60);
    static a b;
    private static final long d = TimeUnit.MILLISECONDS.toNanos(a);
    private boolean e;
    private a f;
    private long g;

    /* renamed from: okio.a$a reason: collision with other inner class name */
    /* compiled from: AsyncTimeout */
    private static final class C0197a extends Thread {
        C0197a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r1.a();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0000 }
                okio.a r1 = okio.a.e()     // Catch:{ all -> 0x0019 }
                if (r1 != 0) goto L_0x000b
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                goto L_0x0000
            L_0x000b:
                okio.a r2 = okio.a.b     // Catch:{ all -> 0x0019 }
                if (r1 != r2) goto L_0x0014
                r1 = 0
                okio.a.b = r1     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                return
            L_0x0014:
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                r1.a()     // Catch:{ InterruptedException -> 0x0000 }
                goto L_0x0000
            L_0x0019:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                throw r1     // Catch:{ InterruptedException -> 0x0000 }
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.a.C0197a.run():void");
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    public final void c() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long j_ = j_();
        boolean k_ = k_();
        if (j_ != 0 || k_) {
            this.e = true;
            a(this, j_, k_);
        }
    }

    private static synchronized void a(a aVar, long j, boolean z) {
        synchronized (a.class) {
            if (b == null) {
                b = new a();
                new C0197a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                aVar.g = nanoTime + Math.min(j, aVar.d() - nanoTime);
            } else if (j != 0) {
                aVar.g = nanoTime + j;
            } else if (z) {
                aVar.g = aVar.d();
            } else {
                throw new AssertionError();
            }
            long b2 = aVar.b(nanoTime);
            a aVar2 = b;
            while (true) {
                if (aVar2.f == null) {
                    break;
                } else if (b2 < aVar2.f.b(nanoTime)) {
                    break;
                } else {
                    aVar2 = aVar2.f;
                }
            }
            aVar.f = aVar2.f;
            aVar2.f = aVar;
            if (aVar2 == b) {
                a.class.notify();
            }
        }
    }

    public final boolean i_() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return a(this);
    }

    private static synchronized boolean a(a aVar) {
        synchronized (a.class) {
            for (a aVar2 = b; aVar2 != null; aVar2 = aVar2.f) {
                if (aVar2.f == aVar) {
                    aVar2.f = aVar.f;
                    aVar.f = null;
                    return false;
                }
            }
            return true;
        }
    }

    private long b(long j) {
        return this.g - j;
    }

    public final s a(final s sVar) {
        return new s() {
            public void a_(c cVar, long j) throws IOException {
                v.a(cVar.b, 0, j);
                while (true) {
                    long j2 = 0;
                    if (j > 0) {
                        q qVar = cVar.a;
                        while (true) {
                            if (j2 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                                break;
                            }
                            long j3 = j2 + ((long) (qVar.c - qVar.b));
                            if (j3 >= j) {
                                j2 = j;
                                break;
                            } else {
                                qVar = qVar.f;
                                j2 = j3;
                            }
                        }
                        a.this.c();
                        try {
                            sVar.a_(cVar, j2);
                            long j4 = j - j2;
                            a.this.a(true);
                            j = j4;
                        } catch (IOException e) {
                            throw a.this.b(e);
                        } catch (Throwable th) {
                            a.this.a(false);
                            throw th;
                        }
                    } else {
                        return;
                    }
                }
            }

            public void flush() throws IOException {
                a.this.c();
                try {
                    sVar.flush();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.b(e);
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            public void close() throws IOException {
                a.this.c();
                try {
                    sVar.close();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.b(e);
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            public u a() {
                return a.this;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("AsyncTimeout.sink(");
                sb.append(sVar);
                sb.append(")");
                return sb.toString();
            }
        };
    }

    public final t a(final t tVar) {
        return new t() {
            public long a(c cVar, long j) throws IOException {
                a.this.c();
                try {
                    long a2 = tVar.a(cVar, j);
                    a.this.a(true);
                    return a2;
                } catch (IOException e) {
                    throw a.this.b(e);
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            public void close() throws IOException {
                try {
                    tVar.close();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.b(e);
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            public u a() {
                return a.this;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("AsyncTimeout.source(");
                sb.append(tVar);
                sb.append(")");
                return sb.toString();
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z) throws IOException {
        if (i_() && z) {
            throw a((IOException) null);
        }
    }

    /* access modifiers changed from: 0000 */
    public final IOException b(IOException iOException) throws IOException {
        if (!i_()) {
            return iOException;
        }
        return a(iOException);
    }

    /* access modifiers changed from: protected */
    public IOException a(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    static a e() throws InterruptedException {
        a aVar = b.f;
        a aVar2 = null;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            a.class.wait(a);
            if (b.f == null && System.nanoTime() - nanoTime >= d) {
                aVar2 = b;
            }
            return aVar2;
        }
        long b2 = aVar.b(System.nanoTime());
        if (b2 > 0) {
            long j = b2 / 1000000;
            a.class.wait(j, (int) (b2 - (1000000 * j)));
            return null;
        }
        b.f = aVar.f;
        aVar.f = null;
        return aVar;
    }
}
