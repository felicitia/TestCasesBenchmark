package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.a.a.C0009a;
import com.bumptech.glide.load.b.b.a.C0010a;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.engine.a.h;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.b.a;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.e;
import com.bumptech.glide.load.resource.bitmap.g;
import com.bumptech.glide.load.resource.bitmap.i;
import com.bumptech.glide.load.resource.bitmap.l;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.c.d;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.request.target.f;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Glide {
    private static volatile Glide a;
    private final c b;
    private final b c;
    private final com.bumptech.glide.load.engine.bitmap_recycle.c d;
    private final h e;
    private final DecodeFormat f;
    private final f g = new f();
    private final d h = new d();
    private final com.bumptech.glide.e.c i;
    private final e j;
    private final com.bumptech.glide.load.resource.b.f k;
    private final i l;
    private final com.bumptech.glide.load.resource.b.f m;
    private final Handler n;
    private final a o;

    public static Glide a(Context context) {
        if (a == null) {
            synchronized (Glide.class) {
                if (a == null) {
                    Context applicationContext = context.getApplicationContext();
                    List<com.bumptech.glide.d.a> a2 = new com.bumptech.glide.d.b(applicationContext).a();
                    g gVar = new g(applicationContext);
                    for (com.bumptech.glide.d.a a3 : a2) {
                        a3.a(applicationContext, gVar);
                    }
                    a = gVar.a();
                    for (com.bumptech.glide.d.a a4 : a2) {
                        a4.a(applicationContext, a);
                    }
                }
            }
        }
        return a;
    }

    Glide(b bVar, h hVar, com.bumptech.glide.load.engine.bitmap_recycle.c cVar, Context context, DecodeFormat decodeFormat) {
        this.c = bVar;
        this.d = cVar;
        this.e = hVar;
        this.f = decodeFormat;
        this.b = new c(context);
        this.n = new Handler(Looper.getMainLooper());
        this.o = new a(hVar, cVar, decodeFormat);
        this.i = new com.bumptech.glide.e.c();
        m mVar = new m(cVar, decodeFormat);
        this.i.a(InputStream.class, Bitmap.class, mVar);
        g gVar = new g(cVar, decodeFormat);
        this.i.a(ParcelFileDescriptor.class, Bitmap.class, gVar);
        l lVar = new l(mVar, gVar);
        this.i.a(com.bumptech.glide.load.b.g.class, Bitmap.class, lVar);
        com.bumptech.glide.load.resource.gif.b bVar2 = new com.bumptech.glide.load.resource.gif.b(context, cVar);
        this.i.a(InputStream.class, GifDrawable.class, bVar2);
        this.i.a(com.bumptech.glide.load.b.g.class, com.bumptech.glide.load.resource.b.a.class, new com.bumptech.glide.load.resource.b.g(lVar, bVar2, cVar));
        this.i.a(InputStream.class, File.class, new com.bumptech.glide.load.resource.a.d());
        a(File.class, ParcelFileDescriptor.class, (com.bumptech.glide.load.b.m<T, Y>) new C0009a<T,Y>());
        a(File.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.c.a<T,Y>());
        a(Integer.TYPE, ParcelFileDescriptor.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.a.c.a<T,Y>());
        a(Integer.TYPE, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.e.a<T,Y>());
        a(Integer.class, ParcelFileDescriptor.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.a.c.a<T,Y>());
        a(Integer.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.e.a<T,Y>());
        a(String.class, ParcelFileDescriptor.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.a.d.a<T,Y>());
        a(String.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.f.a<T,Y>());
        a(Uri.class, ParcelFileDescriptor.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.a.e.a<T,Y>());
        a(Uri.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.g.a<T,Y>());
        a(URL.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.h.a<T,Y>());
        a(com.bumptech.glide.load.b.d.class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new C0010a<T,Y>());
        a(byte[].class, InputStream.class, (com.bumptech.glide.load.b.m<T, Y>) new com.bumptech.glide.load.b.b.b.a<T,Y>());
        this.h.a(Bitmap.class, GlideBitmapDrawable.class, new com.bumptech.glide.load.resource.c.b(context.getResources(), cVar));
        this.h.a(com.bumptech.glide.load.resource.b.a.class, GlideDrawable.class, new com.bumptech.glide.load.resource.c.a(new com.bumptech.glide.load.resource.c.b(context.getResources(), cVar)));
        this.j = new e(cVar);
        this.k = new com.bumptech.glide.load.resource.b.f(cVar, (com.bumptech.glide.load.f<Bitmap>) this.j);
        this.l = new i(cVar);
        this.m = new com.bumptech.glide.load.resource.b.f(cVar, (com.bumptech.glide.load.f<Bitmap>) this.l);
    }

    public com.bumptech.glide.load.engine.bitmap_recycle.c a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public <Z, R> com.bumptech.glide.load.resource.c.c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        return this.h.a(cls, cls2);
    }

    /* access modifiers changed from: 0000 */
    public <T, Z> com.bumptech.glide.e.b<T, Z> b(Class<T> cls, Class<Z> cls2) {
        return this.i.a(cls, cls2);
    }

    /* access modifiers changed from: 0000 */
    public <R> com.bumptech.glide.request.target.i<R> a(ImageView imageView, Class<R> cls) {
        return this.g.a(imageView, cls);
    }

    /* access modifiers changed from: 0000 */
    public b b() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public e c() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public i d() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.load.resource.b.f e() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.load.resource.b.f f() {
        return this.m;
    }

    /* access modifiers changed from: 0000 */
    public DecodeFormat g() {
        return this.f;
    }

    private c i() {
        return this.b;
    }

    public void h() {
        com.bumptech.glide.g.h.a();
        this.e.a();
        this.d.a();
    }

    public void a(int i2) {
        com.bumptech.glide.g.h.a();
        this.e.a(i2);
        this.d.a(i2);
    }

    public static void a(com.bumptech.glide.request.target.i<?> iVar) {
        com.bumptech.glide.g.h.a();
        com.bumptech.glide.request.a c2 = iVar.c();
        if (c2 != null) {
            c2.d();
            iVar.a((com.bumptech.glide.request.a) null);
        }
    }

    public <T, Y> void a(Class<T> cls, Class<Y> cls2, com.bumptech.glide.load.b.m<T, Y> mVar) {
        com.bumptech.glide.load.b.m a2 = this.b.a(cls, cls2, mVar);
        if (a2 != null) {
            a2.a();
        }
    }

    public static <T, Y> com.bumptech.glide.load.b.l<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        if (cls != null) {
            return a(context).i().a(cls, cls2);
        }
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Unable to load null model, setting placeholder only");
        }
        return null;
    }

    public static <T> com.bumptech.glide.load.b.l<T, InputStream> a(Class<T> cls, Context context) {
        return a(cls, InputStream.class, context);
    }

    public static <T> com.bumptech.glide.load.b.l<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a(cls, ParcelFileDescriptor.class, context);
    }

    public static h b(Context context) {
        return RequestManagerRetriever.get().get(context);
    }
}
