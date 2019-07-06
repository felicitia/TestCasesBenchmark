package com.google.android.gms.internal.ads;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class vr extends WeakReference<Throwable> {
    private final int a;

    public vr(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, null);
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
        vr vrVar = (vr) obj;
        return this.a == vrVar.a && get() == vrVar.get();
    }

    public final int hashCode() {
        return this.a;
    }
}
