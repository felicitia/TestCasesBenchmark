package com.google.android.gms.internal.gcm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class e extends WeakReference<Throwable> {
    private final int a;

    public e(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
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
        e eVar = (e) obj;
        return this.a == eVar.a && get() == eVar.get();
    }

    public final int hashCode() {
        return this.a;
    }
}
