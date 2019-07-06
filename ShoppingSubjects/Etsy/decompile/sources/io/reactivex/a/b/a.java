package io.reactivex.a.b;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: AndroidSchedulers */
public final class a {
    private static final u a = io.reactivex.a.a.a.a((Callable<u>) new Callable<u>() {
        /* renamed from: a */
        public u call() throws Exception {
            return C0181a.a;
        }
    });

    /* renamed from: io.reactivex.a.b.a$a reason: collision with other inner class name */
    /* compiled from: AndroidSchedulers */
    private static final class C0181a {
        static final u a = new b(new Handler(Looper.getMainLooper()));
    }

    public static u a() {
        return io.reactivex.a.a.a.a(a);
    }
}
