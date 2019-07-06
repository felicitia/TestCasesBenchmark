package io.reactivex.a;

import android.os.Looper;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: MainThreadDisposable */
public abstract class a implements Disposable {
    private final AtomicBoolean a = new AtomicBoolean();

    /* access modifiers changed from: protected */
    public abstract void a();

    public final boolean isDisposed() {
        return this.a.get();
    }

    public final void dispose() {
        if (!this.a.compareAndSet(false, true)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a();
        } else {
            io.reactivex.a.b.a.a().a((Runnable) new Runnable() {
                public void run() {
                    a.this.a();
                }
            });
        }
    }
}
