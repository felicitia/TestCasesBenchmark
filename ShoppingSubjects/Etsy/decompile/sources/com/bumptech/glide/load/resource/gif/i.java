package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.a.C0008a;
import com.bumptech.glide.b.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.f;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: GifResourceEncoder */
public class i implements e<GifDrawable> {
    private static final a a = new a();
    private final C0008a b;
    private final c c;
    private final a d;

    /* compiled from: GifResourceEncoder */
    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(C0008a aVar) {
            return new com.bumptech.glide.b.a(aVar);
        }

        public d a() {
            return new d();
        }

        public com.bumptech.glide.c.a b() {
            return new com.bumptech.glide.c.a();
        }

        public com.bumptech.glide.load.engine.i<Bitmap> a(Bitmap bitmap, c cVar) {
            return new com.bumptech.glide.load.resource.bitmap.c(bitmap, cVar);
        }
    }

    public String a() {
        return "";
    }

    public i(c cVar) {
        this(cVar, a);
    }

    i(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    /* JADX INFO: finally extract failed */
    public boolean a(com.bumptech.glide.load.engine.i<GifDrawable> iVar, OutputStream outputStream) {
        long a2 = com.bumptech.glide.g.d.a();
        GifDrawable gifDrawable = (GifDrawable) iVar.b();
        f frameTransformation = gifDrawable.getFrameTransformation();
        if (frameTransformation instanceof com.bumptech.glide.load.resource.d) {
            return a(gifDrawable.getData(), outputStream);
        }
        com.bumptech.glide.b.a a3 = a(gifDrawable.getData());
        com.bumptech.glide.c.a b2 = this.d.b();
        if (!b2.a(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < a3.c()) {
            com.bumptech.glide.load.engine.i a4 = a(a3.f(), frameTransformation, gifDrawable);
            try {
                if (!b2.a((Bitmap) a4.b())) {
                    a4.d();
                    return false;
                }
                b2.a(a3.a(a3.d()));
                a3.a();
                a4.d();
                i++;
            } catch (Throwable th) {
                a4.d();
                throw th;
            }
        }
        boolean a5 = b2.a();
        if (Log.isLoggable("GifEncoder", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Encoded gif with ");
            sb.append(a3.c());
            sb.append(" frames and ");
            sb.append(gifDrawable.getData().length);
            sb.append(" bytes in ");
            sb.append(com.bumptech.glide.g.d.a(a2));
            sb.append(" ms");
            Log.v("GifEncoder", sb.toString());
        }
        return a5;
    }

    private boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 3)) {
                Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private com.bumptech.glide.b.a a(byte[] bArr) {
        d a2 = this.d.a();
        a2.a(bArr);
        com.bumptech.glide.b.c b2 = a2.b();
        com.bumptech.glide.b.a a3 = this.d.a(this.b);
        a3.a(b2, bArr);
        a3.a();
        return a3;
    }

    private com.bumptech.glide.load.engine.i<Bitmap> a(Bitmap bitmap, f<Bitmap> fVar, GifDrawable gifDrawable) {
        com.bumptech.glide.load.engine.i a2 = this.d.a(bitmap, this.c);
        com.bumptech.glide.load.engine.i<Bitmap> a3 = fVar.a(a2, gifDrawable.getIntrinsicWidth(), gifDrawable.getIntrinsicHeight());
        if (!a2.equals(a3)) {
            a2.d();
        }
        return a3;
    }
}
