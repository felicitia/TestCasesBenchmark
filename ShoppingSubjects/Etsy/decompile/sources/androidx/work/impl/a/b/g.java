package androidx.work.impl.a.b;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Trackers */
public class g {
    private static g a;
    private a b;
    private b c;
    private e d;
    private f e;

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g(context);
            }
            gVar = a;
        }
        return gVar;
    }

    private g(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = new a(applicationContext);
        this.c = new b(applicationContext);
        this.d = new e(applicationContext);
        this.e = new f(applicationContext);
    }

    public a a() {
        return this.b;
    }

    public b b() {
        return this.c;
    }

    public e c() {
        return this.d;
    }

    public f d() {
        return this.e;
    }
}
