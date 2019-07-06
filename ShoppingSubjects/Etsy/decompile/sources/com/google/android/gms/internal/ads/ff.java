package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;

final class ff implements Runnable {
    private final /* synthetic */ Bitmap a;
    private final /* synthetic */ fc b;

    ff(fc fcVar, Bitmap bitmap) {
        this.b = fcVar;
        this.a = bitmap;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.b.l) {
            this.b.c.g = new abc();
            this.b.c.g.c = byteArrayOutputStream.toByteArray();
            this.b.c.g.b = "image/png";
            this.b.c.g.a = Integer.valueOf(1);
        }
    }
}
