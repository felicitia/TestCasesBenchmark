package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import java.io.InputStream;

/* compiled from: StreamBitmapDecoder */
public class n implements d<InputStream, Bitmap> {
    private final f a;
    private c b;
    private DecodeFormat c;
    private String d;

    public n(c cVar, DecodeFormat decodeFormat) {
        this(f.a, cVar, decodeFormat);
    }

    public n(f fVar, c cVar, DecodeFormat decodeFormat) {
        this.a = fVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public i<Bitmap> a(InputStream inputStream, int i, int i2) {
        return c.a(this.a.a(inputStream, this.b, i, i2, this.c), this.b);
    }

    public String a() {
        if (this.d == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap");
            sb.append(this.a.a());
            sb.append(this.c.name());
            this.d = sb.toString();
        }
        return this.d;
    }
}
