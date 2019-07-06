package com.bumptech.glide.load.resource.b;

import android.graphics.Bitmap;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: GifBitmapWrapperResourceDecoder */
public class c implements d<g, a> {
    private static final b a = new b();
    private static final a b = new a();
    private final d<g, Bitmap> c;
    private final d<InputStream, GifDrawable> d;
    private final com.bumptech.glide.load.engine.bitmap_recycle.c e;
    private final b f;
    private final a g;
    private String h;

    /* compiled from: GifBitmapWrapperResourceDecoder */
    static class a {
        a() {
        }

        public InputStream a(InputStream inputStream, byte[] bArr) {
            return new RecyclableBufferedInputStream(inputStream, bArr);
        }
    }

    /* compiled from: GifBitmapWrapperResourceDecoder */
    static class b {
        b() {
        }

        public ImageType a(InputStream inputStream) throws IOException {
            return new ImageHeaderParser(inputStream).b();
        }
    }

    public c(d<g, Bitmap> dVar, d<InputStream, GifDrawable> dVar2, com.bumptech.glide.load.engine.bitmap_recycle.c cVar) {
        this(dVar, dVar2, cVar, a, b);
    }

    c(d<g, Bitmap> dVar, d<InputStream, GifDrawable> dVar2, com.bumptech.glide.load.engine.bitmap_recycle.c cVar, b bVar, a aVar) {
        this.c = dVar;
        this.d = dVar2;
        this.e = cVar;
        this.f = bVar;
        this.g = aVar;
    }

    public i<a> a(g gVar, int i, int i2) throws IOException {
        com.bumptech.glide.g.a a2 = com.bumptech.glide.g.a.a();
        byte[] b2 = a2.b();
        try {
            a a3 = a(gVar, i, i2, b2);
            if (a3 != null) {
                return new b(a3);
            }
            return null;
        } finally {
            a2.a(b2);
        }
    }

    private a a(g gVar, int i, int i2, byte[] bArr) throws IOException {
        if (gVar.a() != null) {
            return b(gVar, i, i2, bArr);
        }
        return b(gVar, i, i2);
    }

    private a b(g gVar, int i, int i2, byte[] bArr) throws IOException {
        InputStream a2 = this.g.a(gVar.a(), bArr);
        a2.mark(2048);
        ImageType a3 = this.f.a(a2);
        a2.reset();
        a a4 = a3 == ImageType.GIF ? a(a2, i, i2) : null;
        return a4 == null ? b(new g(a2, gVar.b()), i, i2) : a4;
    }

    private a a(InputStream inputStream, int i, int i2) throws IOException {
        i a2 = this.d.a(inputStream, i, i2);
        if (a2 == null) {
            return null;
        }
        GifDrawable gifDrawable = (GifDrawable) a2.b();
        if (gifDrawable.getFrameCount() > 1) {
            return new a(null, a2);
        }
        return new a(new com.bumptech.glide.load.resource.bitmap.c(gifDrawable.getFirstFrame(), this.e), null);
    }

    private a b(g gVar, int i, int i2) throws IOException {
        i a2 = this.c.a(gVar, i, i2);
        if (a2 != null) {
            return new a(a2, null);
        }
        return null;
    }

    public String a() {
        if (this.h == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.d.a());
            sb.append(this.c.a());
            this.h = sb.toString();
        }
        return this.h;
    }
}
