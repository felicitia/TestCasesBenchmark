package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.bitmap.f;
import com.bumptech.glide.load.resource.bitmap.h;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.request.target.i;
import java.io.InputStream;

/* compiled from: BitmapRequestBuilder */
public class a<ModelType, TranscodeType> extends e<ModelType, g, Bitmap, TranscodeType> {
    private final c g;
    private f h = f.a;
    private DecodeFormat i;
    private d<InputStream, Bitmap> j;
    private d<ParcelFileDescriptor, Bitmap> k;

    a(com.bumptech.glide.e.f<ModelType, g, Bitmap, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        super(fVar, cls, eVar);
        this.g = eVar.c.a();
        this.i = eVar.c.g();
        this.j = new n(this.g, this.i);
        this.k = new h(this.g, this.i);
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(d<g, Bitmap> dVar) {
        super.b(dVar);
        return this;
    }

    public a<ModelType, TranscodeType> a(com.bumptech.glide.load.resource.bitmap.d... dVarArr) {
        super.b((com.bumptech.glide.load.f<ResourceType>[]) dVarArr);
        return this;
    }

    public a<ModelType, TranscodeType> a() {
        return a(this.c.c());
    }

    public a<ModelType, TranscodeType> b() {
        return a(this.c.d());
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(com.bumptech.glide.load.f<Bitmap>... fVarArr) {
        super.b((com.bumptech.glide.load.f<ResourceType>[]) fVarArr);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(int i2) {
        super.b(i2);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(boolean z) {
        super.b(z);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(int i2, int i3) {
        super.b(i2, i3);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(com.bumptech.glide.load.a<g> aVar) {
        super.b(aVar);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(b bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(ModelType modeltype) {
        super.b(modeltype);
        return this;
    }

    /* renamed from: c */
    public a<ModelType, TranscodeType> f() {
        return (a) super.clone();
    }

    public i<TranscodeType> a(ImageView imageView) {
        return super.a(imageView);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        b();
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        a();
    }
}
