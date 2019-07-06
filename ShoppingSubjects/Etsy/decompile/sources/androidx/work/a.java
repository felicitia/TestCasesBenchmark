package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: Configuration */
public final class a {
    @NonNull
    private final Executor a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    /* renamed from: androidx.work.a$a reason: collision with other inner class name */
    /* compiled from: Configuration */
    public static final class C0005a {
        int a = 0;
        int b = Integer.MAX_VALUE;
        int c = 20;
        int d = 4;
        Executor e;

        @NonNull
        public a a() {
            return new a(this);
        }
    }

    a(@NonNull C0005a aVar) {
        if (aVar.e == null) {
            this.a = f();
        } else {
            this.a = aVar.e;
        }
        this.b = aVar.d;
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
    }

    @NonNull
    public Executor a() {
        return this.a;
    }

    @RestrictTo({Scope.LIBRARY})
    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int e() {
        if (VERSION.SDK_INT == 23) {
            return this.e / 2;
        }
        return this.e;
    }

    @NonNull
    private Executor f() {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)));
    }
}
