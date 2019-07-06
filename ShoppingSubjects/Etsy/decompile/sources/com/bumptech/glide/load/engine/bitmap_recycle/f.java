package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: LruBitmapPool */
public class f implements c {
    private static final Config a = Config.ARGB_8888;
    private final g b;
    private final Set<Config> c;
    private final int d;
    private final a e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    /* compiled from: LruBitmapPool */
    private interface a {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    /* compiled from: LruBitmapPool */
    private static class b implements a {
        public void a(Bitmap bitmap) {
        }

        public void b(Bitmap bitmap) {
        }

        private b() {
        }
    }

    f(int i2, g gVar, Set<Config> set) {
        this.d = i2;
        this.f = i2;
        this.b = gVar;
        this.c = set;
        this.e = new b();
    }

    public f(int i2) {
        this(i2, e(), f());
    }

    public synchronized boolean a(Bitmap bitmap) {
        if (bitmap == null) {
            try {
                throw new NullPointerException("Bitmap must not be null");
            } catch (Throwable th) {
                throw th;
            }
        } else {
            if (bitmap.isMutable() && this.b.c(bitmap) <= this.f) {
                if (this.c.contains(bitmap.getConfig())) {
                    int c2 = this.b.c(bitmap);
                    this.b.a(bitmap);
                    this.e.a(bitmap);
                    this.j++;
                    this.g += c2;
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Put bitmap in pool=");
                        sb.append(this.b.b(bitmap));
                        Log.v("LruBitmapPool", sb.toString());
                    }
                    c();
                    b();
                    return true;
                }
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Reject bitmap from pool, bitmap: ");
                sb2.append(this.b.b(bitmap));
                sb2.append(", is mutable: ");
                sb2.append(bitmap.isMutable());
                sb2.append(", is allowed config: ");
                sb2.append(this.c.contains(bitmap.getConfig()));
                Log.v("LruBitmapPool", sb2.toString());
            }
            return false;
        }
    }

    private void b() {
        b(this.f);
    }

    public synchronized Bitmap a(int i2, int i3, Config config) {
        Bitmap b2;
        b2 = b(i2, i3, config);
        if (b2 != null) {
            b2.eraseColor(0);
        }
        return b2;
    }

    @TargetApi(12)
    public synchronized Bitmap b(int i2, int i3, Config config) {
        Bitmap a2;
        a2 = this.b.a(i2, i3, config != null ? config : a);
        if (a2 == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Missing bitmap=");
                sb.append(this.b.b(i2, i3, config));
                Log.d("LruBitmapPool", sb.toString());
            }
            this.i++;
        } else {
            this.h++;
            this.g -= this.b.c(a2);
            this.e.b(a2);
            if (VERSION.SDK_INT >= 12) {
                a2.setHasAlpha(true);
            }
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Get bitmap=");
            sb2.append(this.b.b(i2, i3, config));
            Log.v("LruBitmapPool", sb2.toString());
        }
        c();
        return a2;
    }

    public void a() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        b(0);
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i2) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("trimMemory, level=");
            sb.append(i2);
            Log.d("LruBitmapPool", sb.toString());
        }
        if (i2 >= 60) {
            a();
        } else if (i2 >= 40) {
            b(this.f / 2);
        }
    }

    private synchronized void b(int i2) {
        while (this.g > i2) {
            Bitmap a2 = this.b.a();
            if (a2 == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    d();
                }
                this.g = 0;
                return;
            }
            this.e.b(a2);
            this.g -= this.b.c(a2);
            a2.recycle();
            this.k++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Evicting bitmap=");
                sb.append(this.b.b(a2));
                Log.d("LruBitmapPool", sb.toString());
            }
            c();
        }
    }

    private void c() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            d();
        }
    }

    private void d() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hits=");
        sb.append(this.h);
        sb.append(", misses=");
        sb.append(this.i);
        sb.append(", puts=");
        sb.append(this.j);
        sb.append(", evictions=");
        sb.append(this.k);
        sb.append(", currentSize=");
        sb.append(this.g);
        sb.append(", maxSize=");
        sb.append(this.f);
        sb.append("\nStrategy=");
        sb.append(this.b);
        Log.v("LruBitmapPool", sb.toString());
    }

    private static g e() {
        if (VERSION.SDK_INT >= 19) {
            return new i();
        }
        return new a();
    }

    private static Set<Config> f() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(Config.values()));
        if (VERSION.SDK_INT >= 19) {
            hashSet.add(null);
        }
        return Collections.unmodifiableSet(hashSet);
    }
}
