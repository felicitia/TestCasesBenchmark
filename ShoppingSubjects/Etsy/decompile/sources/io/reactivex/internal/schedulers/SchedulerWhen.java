package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SchedulerWhen extends u implements Disposable {
    static final Disposable b = new d();
    static final Disposable c = io.reactivex.disposables.b.a();
    private final u d;
    private final io.reactivex.processors.a<g<io.reactivex.a>> e;
    private Disposable f;

    static class DelayedAction extends ScheduledAction {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        DelayedAction(Runnable runnable, long j, TimeUnit timeUnit) {
            this.action = runnable;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        /* access modifiers changed from: protected */
        public Disposable callActual(io.reactivex.u.c cVar, io.reactivex.c cVar2) {
            return cVar.a(new b(this.action, cVar2), this.delayTime, this.unit);
        }
    }

    static class ImmediateAction extends ScheduledAction {
        private final Runnable action;

        ImmediateAction(Runnable runnable) {
            this.action = runnable;
        }

        /* access modifiers changed from: protected */
        public Disposable callActual(io.reactivex.u.c cVar, io.reactivex.c cVar2) {
            return cVar.a((Runnable) new b(this.action, cVar2));
        }
    }

    static abstract class ScheduledAction extends AtomicReference<Disposable> implements Disposable {
        /* access modifiers changed from: protected */
        public abstract Disposable callActual(io.reactivex.u.c cVar, io.reactivex.c cVar2);

        ScheduledAction() {
            super(SchedulerWhen.b);
        }

        /* access modifiers changed from: 0000 */
        public void call(io.reactivex.u.c cVar, io.reactivex.c cVar2) {
            Disposable disposable = (Disposable) get();
            if (disposable != SchedulerWhen.c && disposable == SchedulerWhen.b) {
                Disposable callActual = callActual(cVar, cVar2);
                if (!compareAndSet(SchedulerWhen.b, callActual)) {
                    callActual.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return ((Disposable) get()).isDisposed();
        }

        public void dispose() {
            Disposable disposable;
            Disposable disposable2 = SchedulerWhen.c;
            do {
                disposable = (Disposable) get();
                if (disposable == SchedulerWhen.c) {
                    return;
                }
            } while (!compareAndSet(disposable, disposable2));
            if (disposable != SchedulerWhen.b) {
                disposable.dispose();
            }
        }
    }

    static final class a implements io.reactivex.functions.g<ScheduledAction, io.reactivex.a> {
        final io.reactivex.u.c a;

        /* renamed from: io.reactivex.internal.schedulers.SchedulerWhen$a$a reason: collision with other inner class name */
        final class C0187a extends io.reactivex.a {
            final ScheduledAction a;

            C0187a(ScheduledAction scheduledAction) {
                this.a = scheduledAction;
            }

            /* access modifiers changed from: protected */
            public void b(io.reactivex.c cVar) {
                cVar.onSubscribe(this.a);
                this.a.call(a.this.a, cVar);
            }
        }

        a(io.reactivex.u.c cVar) {
            this.a = cVar;
        }

        /* renamed from: a */
        public io.reactivex.a apply(ScheduledAction scheduledAction) {
            return new C0187a(scheduledAction);
        }
    }

    static class b implements Runnable {
        final io.reactivex.c a;
        final Runnable b;

        b(Runnable runnable, io.reactivex.c cVar) {
            this.b = runnable;
            this.a = cVar;
        }

        public void run() {
            try {
                this.b.run();
            } finally {
                this.a.onComplete();
            }
        }
    }

    static final class c extends io.reactivex.u.c {
        private final AtomicBoolean a = new AtomicBoolean();
        private final io.reactivex.processors.a<ScheduledAction> b;
        private final io.reactivex.u.c c;

        c(io.reactivex.processors.a<ScheduledAction> aVar, io.reactivex.u.c cVar) {
            this.b = aVar;
            this.c = cVar;
        }

        public void dispose() {
            if (this.a.compareAndSet(false, true)) {
                this.b.onComplete();
                this.c.dispose();
            }
        }

        public boolean isDisposed() {
            return this.a.get();
        }

        public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
            DelayedAction delayedAction = new DelayedAction(runnable, j, timeUnit);
            this.b.onNext(delayedAction);
            return delayedAction;
        }

        public Disposable a(Runnable runnable) {
            ImmediateAction immediateAction = new ImmediateAction(runnable);
            this.b.onNext(immediateAction);
            return immediateAction;
        }
    }

    static final class d implements Disposable {
        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }

        d() {
        }
    }

    public void dispose() {
        this.f.dispose();
    }

    public boolean isDisposed() {
        return this.f.isDisposed();
    }

    public io.reactivex.u.c a() {
        io.reactivex.u.c a2 = this.d.a();
        io.reactivex.processors.a g = UnicastProcessor.f().g();
        g b2 = g.b((io.reactivex.functions.g<? super T, ? extends R>) new a<Object,Object>(a2));
        c cVar = new c(g, a2);
        this.e.onNext(b2);
        return cVar;
    }
}
