package okhttp3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.c;

/* compiled from: Dispatcher */
public final class n {
    private int a = 64;
    private int b = 5;
    private Runnable c;
    private ExecutorService d;
    private final Deque<a> e = new ArrayDeque();
    private final Deque<a> f = new ArrayDeque();
    private final Deque<x> g = new ArrayDeque();

    public synchronized ExecutorService a() {
        if (this.d == null) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), c.a("OkHttp Dispatcher", false));
            this.d = threadPoolExecutor;
        }
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(a aVar) {
        if (this.f.size() >= this.a || c(aVar) >= this.b) {
            this.e.add(aVar);
        } else {
            this.f.add(aVar);
            a().execute(aVar);
        }
    }

    private void c() {
        if (this.f.size() < this.a && !this.e.isEmpty()) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (c(aVar) < this.b) {
                    it.remove();
                    this.f.add(aVar);
                    a().execute(aVar);
                }
                if (this.f.size() >= this.a) {
                    return;
                }
            }
        }
    }

    private int c(a aVar) {
        int i = 0;
        for (a aVar2 : this.f) {
            if (!aVar2.b().d && aVar2.a().equals(aVar.a())) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(x xVar) {
        this.g.add(xVar);
    }

    /* access modifiers changed from: 0000 */
    public void b(a aVar) {
        a(this.f, aVar, true);
    }

    /* access modifiers changed from: 0000 */
    public void b(x xVar) {
        a(this.g, xVar, false);
    }

    private <T> void a(Deque<T> deque, T t, boolean z) {
        int b2;
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            if (z) {
                c();
            }
            b2 = b();
            runnable = this.c;
        }
        if (b2 == 0 && runnable != null) {
            runnable.run();
        }
    }

    public synchronized int b() {
        return this.f.size() + this.g.size();
    }
}
