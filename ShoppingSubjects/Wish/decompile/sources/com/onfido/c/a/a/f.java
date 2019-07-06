package com.onfido.c.a.a;

import android.util.Log;
import com.onfido.c.a.a.b;

public final class f {
    public final b a;
    private final String b;

    public f(String str, b bVar) {
        this.b = str;
        this.a = bVar;
    }

    public static f a(b bVar) {
        return new f("Analytics", bVar);
    }

    private boolean b(b bVar) {
        return this.a.ordinal() >= bVar.ordinal();
    }

    public void a(String str, Object... objArr) {
        if (b(b.VERBOSE)) {
            Log.v(this.b, String.format(str, objArr));
        }
    }

    public void a(Throwable th, String str, Object... objArr) {
        if (b(b.INFO)) {
            Log.e(this.b, String.format(str, objArr), th);
        }
    }

    public void b(String str, Object... objArr) {
        if (b(b.INFO)) {
            Log.i(this.b, String.format(str, objArr));
        }
    }

    public void c(String str, Object... objArr) {
        if (b(b.DEBUG)) {
            Log.d(this.b, String.format(str, objArr));
        }
    }
}
