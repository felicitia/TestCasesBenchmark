package com.google.android.gms.internal.ads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@bu
public class lk<T> implements lg<T> {
    private final Object a = new Object();
    private int b = 0;
    private final BlockingQueue<ll> c = new LinkedBlockingQueue();
    private T d;

    public final void a() {
        synchronized (this.a) {
            if (this.b != 0) {
                throw new UnsupportedOperationException();
            }
            this.b = -1;
            for (ll llVar : this.c) {
                llVar.b.a();
            }
            this.c.clear();
        }
    }

    public final void a(lj<T> ljVar, lh lhVar) {
        synchronized (this.a) {
            if (this.b == 1) {
                ljVar.a(this.d);
            } else if (this.b == -1) {
                lhVar.a();
            } else if (this.b == 0) {
                this.c.add(new ll(this, ljVar, lhVar));
            }
        }
    }

    public final void a(T t) {
        synchronized (this.a) {
            if (this.b != 0) {
                throw new UnsupportedOperationException();
            }
            this.d = t;
            this.b = 1;
            for (ll llVar : this.c) {
                llVar.a.a(t);
            }
            this.c.clear();
        }
    }

    public final int b() {
        return this.b;
    }
}
