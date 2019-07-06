package defpackage;

import android.content.Context;
import java.util.Locale;

/* renamed from: aj reason: default package */
/* compiled from: GA */
public final class aj extends q {
    private final Context b;
    private final int c;
    private Class<?> d;

    public aj(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public final void c() {
        try {
            this.d = this.b.getClassLoader().loadClass(c.A[0]);
        } catch (Exception unused) {
            super.a(c.m);
        }
        switch (this.c) {
            case 0:
                for (int i = 0; i < c.B.length; i++) {
                    String b2 = b(c.B[i]);
                    if (!b2.isEmpty()) {
                        super.a(String.format(Locale.US, "%s, %s", new Object[]{Integer.toString(i), b2}));
                    }
                }
                return;
            case 1:
                for (int i2 = 0; i2 < c.C.length; i2++) {
                    String b3 = b(c.C[i2]);
                    if (!b3.isEmpty()) {
                        super.a(String.format(Locale.US, "%s, %s", new Object[]{Integer.toString(i2), b3}));
                    }
                }
                break;
        }
    }

    private String b(String str) {
        String str2 = "";
        if (this.d != null) {
            try {
                return (String) this.d.getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{str});
            } catch (Exception unused) {
            }
        }
        return str2;
    }

    public final boolean b() {
        return this.a != null && this.a.size() > 0;
    }
}
