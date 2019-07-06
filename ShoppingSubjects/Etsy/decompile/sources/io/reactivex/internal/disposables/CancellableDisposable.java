package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.functions.f;
import java.util.concurrent.atomic.AtomicReference;

public final class CancellableDisposable extends AtomicReference<f> implements Disposable {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(f fVar) {
        super(fVar);
    }

    public boolean isDisposed() {
        return get() == null;
    }

    public void dispose() {
        if (get() != null) {
            f fVar = (f) getAndSet(null);
            if (fVar != null) {
                try {
                    fVar.a();
                } catch (Exception e) {
                    a.b(e);
                    io.reactivex.d.a.a((Throwable) e);
                }
            }
        }
    }
}
