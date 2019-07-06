package com.google.android.gms.internal.ads;

import com.android.volley.DefaultRetryPolicy;

public final class agu implements s {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public agu() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    private agu(int i, int i2, float f) {
        this.a = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.c = 1;
        this.d = 1.0f;
    }

    public final int a() {
        return this.a;
    }

    public final void a(zzae zzae) throws zzae {
        boolean z = true;
        this.b++;
        this.a = (int) (((float) this.a) + (((float) this.a) * this.d));
        if (this.b > this.c) {
            z = false;
        }
        if (!z) {
            throw zzae;
        }
    }

    public final int b() {
        return this.b;
    }
}
