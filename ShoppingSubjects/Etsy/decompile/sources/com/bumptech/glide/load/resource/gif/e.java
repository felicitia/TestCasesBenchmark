package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.request.target.g;
import com.bumptech.glide.request.target.i;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

/* compiled from: GifFrameLoader */
class e {
    private final b a;
    private final com.bumptech.glide.b.a b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private com.bumptech.glide.e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> f;
    private a g;
    private boolean h;

    /* compiled from: GifFrameLoader */
    static class a extends g<Bitmap> {
        private final Handler a;
        /* access modifiers changed from: private */
        public final int b;
        private final long c;
        private Bitmap d;

        public a(Handler handler, int i, long j) {
            this.a = handler;
            this.b = i;
            this.c = j;
        }

        public Bitmap a() {
            return this.d;
        }

        public void a(Bitmap bitmap, com.bumptech.glide.request.a.c<? super Bitmap> cVar) {
            this.d = bitmap;
            this.a.sendMessageAtTime(this.a.obtainMessage(1, this), this.c);
        }
    }

    /* compiled from: GifFrameLoader */
    public interface b {
        void onFrameReady(int i);
    }

    /* compiled from: GifFrameLoader */
    private class c implements Callback {
        private c() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                e.this.a((a) message.obj);
                return true;
            }
            if (message.what == 2) {
                Glide.a((i<?>) (a) message.obj);
            }
            return false;
        }
    }

    /* compiled from: GifFrameLoader */
    static class d implements com.bumptech.glide.load.b {
        private final UUID a;

        public d() {
            this(UUID.randomUUID());
        }

        d(UUID uuid) {
            this.a = uuid;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return ((d) obj).a.equals(this.a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public e(Context context, b bVar, com.bumptech.glide.b.a aVar, int i, int i2) {
        this(bVar, aVar, null, a(context, aVar, i, i2, Glide.a(context).a()));
    }

    e(b bVar, com.bumptech.glide.b.a aVar, Handler handler, com.bumptech.glide.e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> eVar) {
        this.d = false;
        this.e = false;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper(), new c());
        }
        this.a = bVar;
        this.b = aVar;
        this.c = handler;
        this.f = eVar;
    }

    public void a(f<Bitmap> fVar) {
        if (fVar == null) {
            throw new NullPointerException("Transformation must not be null");
        }
        this.f = this.f.b((f<ResourceType>[]) new f[]{fVar});
    }

    public void a() {
        if (!this.d) {
            this.d = true;
            this.h = false;
            e();
        }
    }

    public void b() {
        this.d = false;
    }

    public void c() {
        b();
        if (this.g != null) {
            Glide.a((i<?>) this.g);
            this.g = null;
        }
        this.h = true;
    }

    public Bitmap d() {
        if (this.g != null) {
            return this.g.a();
        }
        return null;
    }

    private void e() {
        if (this.d && !this.e) {
            this.e = true;
            this.b.a();
            this.f.b((com.bumptech.glide.load.b) new d()).a(new a(this.c, this.b.d(), SystemClock.uptimeMillis() + ((long) this.b.b())));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        if (this.h) {
            this.c.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        a aVar2 = this.g;
        this.g = aVar;
        this.a.onFrameReady(aVar.b);
        if (aVar2 != null) {
            this.c.obtainMessage(2, aVar2).sendToTarget();
        }
        this.e = false;
        e();
    }

    private static com.bumptech.glide.e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> a(Context context, com.bumptech.glide.b.a aVar, int i, int i2, com.bumptech.glide.load.engine.bitmap_recycle.c cVar) {
        g gVar = new g(cVar);
        f fVar = new f();
        return Glide.b(context).a(fVar, com.bumptech.glide.b.a.class).a(aVar).a(Bitmap.class).b(com.bumptech.glide.load.resource.a.b()).b((com.bumptech.glide.load.d<DataType, ResourceType>) gVar).b(true).b(DiskCacheStrategy.NONE).b(i, i2);
    }
}
