package com.google.firebase.appindexing;

import com.google.android.gms.tasks.f;
import com.google.firebase.a;
import com.google.firebase.appindexing.internal.d;
import java.lang.ref.WeakReference;

public abstract class b {
    private static WeakReference<b> a;

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            bVar = a == null ? null : (b) a.get();
            if (bVar == null) {
                d dVar = new d(a.d().a());
                a = new WeakReference<>(dVar);
                bVar = dVar;
            }
        }
        return bVar;
    }

    public abstract f<Void> a(d... dVarArr);

    public abstract f<Void> a(String... strArr);
}
