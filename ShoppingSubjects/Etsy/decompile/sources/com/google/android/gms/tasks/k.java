package com.google.android.gms.tasks;

final class k implements Runnable {
    private final /* synthetic */ f a;
    private final /* synthetic */ j b;

    k(j jVar, f fVar) {
        this.b = jVar;
        this.a = fVar;
    }

    public final void run() {
        if (this.a.c()) {
            this.b.c.f();
            return;
        }
        try {
            this.b.c.a(this.b.b.then(this.a));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.b.c.a((Exception) e.getCause());
            } else {
                this.b.c.a((Exception) e);
            }
        } catch (Exception e2) {
            this.b.c.a(e2);
        }
    }
}
