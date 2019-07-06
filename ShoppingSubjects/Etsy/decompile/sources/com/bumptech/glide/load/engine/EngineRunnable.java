package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.d;

class EngineRunnable implements com.bumptech.glide.load.engine.executor.a, Runnable {
    private final Priority a;
    private final a b;
    private final a<?, ?, ?> c;
    private Stage d = Stage.CACHE;
    private volatile boolean e;

    private enum Stage {
        CACHE,
        SOURCE
    }

    interface a extends d {
        void b(EngineRunnable engineRunnable);
    }

    public EngineRunnable(a aVar, a<?, ?, ?> aVar2, Priority priority) {
        this.b = aVar;
        this.c = aVar2;
        this.a = priority;
    }

    public void a() {
        this.e = true;
        this.c.d();
    }

    public void run() {
        if (!this.e) {
            i iVar = null;
            try {
                e = null;
                iVar = d();
            } catch (Exception e2) {
                e = e2;
                if (Log.isLoggable("EngineRunnable", 2)) {
                    Log.v("EngineRunnable", "Exception decoding", e);
                }
            }
            if (this.e) {
                if (iVar != null) {
                    iVar.d();
                }
                return;
            }
            if (iVar == null) {
                a(e);
            } else {
                a(iVar);
            }
        }
    }

    private boolean c() {
        return this.d == Stage.CACHE;
    }

    private void a(i iVar) {
        this.b.a(iVar);
    }

    private void a(Exception exc) {
        if (c()) {
            this.d = Stage.SOURCE;
            this.b.b(this);
            return;
        }
        this.b.a(exc);
    }

    private i<?> d() throws Exception {
        if (c()) {
            return e();
        }
        return f();
    }

    private i<?> e() throws Exception {
        i<?> iVar;
        try {
            iVar = this.c.a();
        } catch (Exception e2) {
            if (Log.isLoggable("EngineRunnable", 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Exception decoding result from cache: ");
                sb.append(e2);
                Log.d("EngineRunnable", sb.toString());
            }
            iVar = null;
        }
        return iVar == null ? this.c.b() : iVar;
    }

    private i<?> f() throws Exception {
        return this.c.c();
    }

    public int b() {
        return this.a.ordinal();
    }
}
