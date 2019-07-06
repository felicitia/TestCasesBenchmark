package com.facebook.internal;

import android.graphics.Bitmap;

/* compiled from: ImageResponse */
public class p {
    private o a;
    private Exception b;
    private boolean c;
    private Bitmap d;

    p(o oVar, Exception exc, boolean z, Bitmap bitmap) {
        this.a = oVar;
        this.b = exc;
        this.d = bitmap;
        this.c = z;
    }

    public o a() {
        return this.a;
    }

    public Exception b() {
        return this.b;
    }

    public Bitmap c() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }
}
