package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.e.f;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.b.a;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.manager.k;
import com.bumptech.glide.request.target.i;

/* compiled from: DrawableRequestBuilder */
public class c<ModelType> extends e<ModelType, g, a, GlideDrawable> {
    c(Context context, Class<ModelType> cls, f<ModelType, g, a, GlideDrawable> fVar, Glide glide, k kVar, com.bumptech.glide.manager.g gVar) {
        super(context, cls, fVar, GlideDrawable.class, glide, kVar, gVar);
        c();
    }

    /* renamed from: a */
    public c<ModelType> b(d<g, a> dVar) {
        super.b(dVar);
        return this;
    }

    public c<ModelType> a() {
        return b(this.c.e());
    }

    public c<ModelType> b() {
        return b(this.c.f());
    }

    public c<ModelType> a(com.bumptech.glide.load.f<Bitmap>... fVarArr) {
        com.bumptech.glide.load.resource.b.f[] fVarArr2 = new com.bumptech.glide.load.resource.b.f[fVarArr.length];
        for (int i = 0; i < fVarArr.length; i++) {
            fVarArr2[i] = new com.bumptech.glide.load.resource.b.f(this.c.a(), fVarArr[i]);
        }
        return b(fVarArr2);
    }

    /* renamed from: c */
    public c<ModelType> b(com.bumptech.glide.load.f<a>... fVarArr) {
        super.b((com.bumptech.glide.load.f<ResourceType>[]) fVarArr);
        return this;
    }

    public final c<ModelType> c() {
        super.a((com.bumptech.glide.request.a.d<TranscodeType>) new com.bumptech.glide.request.a.a<TranscodeType>());
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(int i) {
        super.b(i);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(boolean z) {
        super.b(z);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(com.bumptech.glide.load.a<g> aVar) {
        super.b(aVar);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(b bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(ModelType modeltype) {
        super.b(modeltype);
        return this;
    }

    /* renamed from: g */
    public c<ModelType> f() {
        return (c) super.clone();
    }

    public i<GlideDrawable> a(ImageView imageView) {
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
