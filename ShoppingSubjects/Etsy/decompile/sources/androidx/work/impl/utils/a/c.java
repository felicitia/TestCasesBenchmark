package androidx.work.impl.utils.a;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkManagerTaskExecutor */
public class c implements b {
    private static c a;
    private final b b = new a();
    private b c = this.b;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    private c() {
    }

    public void a(Runnable runnable) {
        this.c.a(runnable);
    }

    public void b(Runnable runnable) {
        this.c.b(runnable);
    }
}
