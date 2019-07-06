package com.bumptech.glide.load.resource.b;

import android.graphics.Bitmap;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.OutputStream;

/* compiled from: GifBitmapWrapperResourceEncoder */
public class d implements e<a> {
    private final e<Bitmap> a;
    private final e<GifDrawable> b;
    private String c;

    public d(e<Bitmap> eVar, e<GifDrawable> eVar2) {
        this.a = eVar;
        this.b = eVar2;
    }

    public boolean a(i<a> iVar, OutputStream outputStream) {
        a aVar = (a) iVar.b();
        i b2 = aVar.b();
        if (b2 != null) {
            return this.a.a(b2, outputStream);
        }
        return this.b.a(aVar.c(), outputStream);
    }

    public String a() {
        if (this.c == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.a());
            sb.append(this.b.a());
            this.c = sb.toString();
        }
        return this.c;
    }
}
