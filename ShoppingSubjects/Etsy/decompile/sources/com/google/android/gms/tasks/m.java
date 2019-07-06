package com.google.android.gms.tasks;

final class m implements Runnable {
    private final /* synthetic */ f a;
    private final /* synthetic */ l b;

    m(l lVar, f fVar) {
        this.b = lVar;
        this.a = fVar;
    }

    public final void run() {
        try {
            f fVar = (f) this.b.b.then(this.a);
            if (fVar == null) {
                this.b.a((Exception) new NullPointerException("Continuation returned null"));
                return;
            }
            fVar.a(h.b, (e<? super TResult>) this.b);
            fVar.a(h.b, (d) this.b);
            fVar.a(h.b, (b) this.b);
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
