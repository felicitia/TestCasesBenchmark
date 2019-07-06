package com.google.android.gms.internal.stable;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class j extends WeakReference<Throwable> {
    private final int a;

    public j(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        j jVar = (j) obj;
        return this.a == jVar.a && get() == jVar.get();
    }

    public final int hashCode() {
        return this.a;
    }
}
