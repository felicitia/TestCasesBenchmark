package com.bumptech.glide;

import android.content.Context;
import android.os.Build.VERSION;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.a.C0012a;
import com.bumptech.glide.load.engine.a.h;
import com.bumptech.glide.load.engine.a.i;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.bitmap_recycle.d;
import com.bumptech.glide.load.engine.bitmap_recycle.f;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

/* compiled from: GlideBuilder */
public class g {
    private final Context a;
    private b b;
    private c c;
    private h d;
    private ExecutorService e;
    private ExecutorService f;
    private DecodeFormat g;
    private C0012a h;

    public g(Context context) {
        this.a = context.getApplicationContext();
    }

    /* access modifiers changed from: 0000 */
    public Glide a() {
        if (this.e == null) {
            this.e = new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
        }
        if (this.f == null) {
            this.f = new FifoPriorityThreadPoolExecutor(1);
        }
        i iVar = new i(this.a);
        if (this.c == null) {
            if (VERSION.SDK_INT >= 11) {
                this.c = new f(iVar.b());
            } else {
                this.c = new d();
            }
        }
        if (this.d == null) {
            this.d = new com.bumptech.glide.load.engine.a.g(iVar.a());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.load.engine.a.f(this.a);
        }
        if (this.b == null) {
            this.b = new b(this.d, this.h, this.f, this.e);
        }
        if (this.g == null) {
            this.g = DecodeFormat.DEFAULT;
        }
        Glide glide = new Glide(this.b, this.d, this.c, this.a, this.g);
        return glide;
    }
}
