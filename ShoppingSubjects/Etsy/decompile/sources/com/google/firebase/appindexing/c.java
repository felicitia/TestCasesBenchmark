package com.google.firebase.appindexing;

import com.google.android.gms.tasks.f;
import com.google.firebase.a;
import com.google.firebase.appindexing.internal.k;
import java.lang.ref.WeakReference;

public abstract class c {
    private static WeakReference<c> a;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            cVar = a == null ? null : (c) a.get();
            if (cVar == null) {
                k kVar = new k(a.d().a());
                a = new WeakReference<>(kVar);
                cVar = kVar;
            }
        }
        return cVar;
    }

    public abstract f<Void> a(a aVar);
}
