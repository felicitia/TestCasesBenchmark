package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import java.io.InputStream;

/* compiled from: ImageVideoBitmapDecoder */
public class k implements d<g, Bitmap> {
    private final d<InputStream, Bitmap> a;
    private final d<ParcelFileDescriptor, Bitmap> b;

    public String a() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    public k(d<InputStream, Bitmap> dVar, d<ParcelFileDescriptor, Bitmap> dVar2) {
        this.a = dVar;
        this.b = dVar2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.load.engine.i<android.graphics.Bitmap> a(com.bumptech.glide.load.b.g r4, int r5, int r6) throws java.io.IOException {
        /*
            r3 = this;
            java.io.InputStream r0 = r4.a()
            if (r0 == 0) goto L_0x001e
            com.bumptech.glide.load.d<java.io.InputStream, android.graphics.Bitmap> r1 = r3.a     // Catch:{ IOException -> 0x000d }
            com.bumptech.glide.load.engine.i r0 = r1.a(r0, r5, r6)     // Catch:{ IOException -> 0x000d }
            goto L_0x001f
        L_0x000d:
            r0 = move-exception
            java.lang.String r1 = "ImageVideoDecoder"
            r2 = 2
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = "ImageVideoDecoder"
            java.lang.String r2 = "Failed to load image from stream, trying FileDescriptor"
            android.util.Log.v(r1, r2, r0)
        L_0x001e:
            r0 = 0
        L_0x001f:
            if (r0 != 0) goto L_0x002d
            android.os.ParcelFileDescriptor r4 = r4.b()
            if (r4 == 0) goto L_0x002d
            com.bumptech.glide.load.d<android.os.ParcelFileDescriptor, android.graphics.Bitmap> r0 = r3.b
            com.bumptech.glide.load.engine.i r0 = r0.a(r4, r5, r6)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.k.a(com.bumptech.glide.load.b.g, int, int):com.bumptech.glide.load.engine.i");
    }
}
