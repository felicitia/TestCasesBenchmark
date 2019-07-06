package com.lyft.android.scissors;

import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.io.OutputStream;
import java.util.concurrent.Future;

/* compiled from: CropViewExtensions */
class c {
    static final boolean a = a("com.squareup.picasso.Picasso");
    static final boolean b = a("com.bumptech.glide.Glide");
    static final boolean c = a("com.nostra13.universalimageloader.core.ImageLoader");

    /* compiled from: CropViewExtensions */
    public static class a {
        private final CropView a;
        private CompressFormat b = CompressFormat.JPEG;
        private int c = 100;

        a(@NonNull CropView cropView) {
            m.a((Object) cropView, "cropView == null");
            this.a = cropView;
        }

        public a a(@NonNull CompressFormat compressFormat) {
            m.a((Object) compressFormat, "format == null");
            this.b = compressFormat;
            return this;
        }

        public a a(int i) {
            m.a(i >= 0 && i <= 100, "quality must be 0..100");
            this.c = i;
            return this;
        }

        public Future<Void> a(@NonNull OutputStream outputStream, boolean z) {
            return m.a(this.a.crop(), this.b, this.c, outputStream, z);
        }
    }

    /* compiled from: CropViewExtensions */
    public static class b {
        /* access modifiers changed from: private */
        public final CropView a;
        private a b;

        b(CropView cropView) {
            m.a((Object) cropView, "cropView == null");
            this.a = cropView;
        }

        public void a(@Nullable Object obj) {
            if (this.a.getWidth() == 0 && this.a.getHeight() == 0) {
                c(obj);
            } else {
                b(obj);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(Object obj) {
            if (this.b == null) {
                this.b = c.a(this.a);
            }
            this.b.a(obj, this.a);
        }

        /* access modifiers changed from: 0000 */
        public void c(final Object obj) {
            if (this.a.getViewTreeObserver().isAlive()) {
                this.a.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if (b.this.a.getViewTreeObserver().isAlive()) {
                            b.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        b.this.b(obj);
                    }
                });
            }
        }
    }

    static a a(CropView cropView) {
        if (a) {
            return f.a(cropView);
        }
        if (b) {
            return d.a(cropView);
        }
        if (c) {
            return k.a(cropView);
        }
        throw new IllegalStateException("You must provide a BitmapLoader.");
    }

    static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    static Rect a(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 == i4) {
            return new Rect(0, 0, i3, i4);
        }
        float f = i * i4 > i3 * i2 ? ((float) i4) / ((float) i2) : ((float) i3) / ((float) i);
        return new Rect(0, 0, (int) ((((float) i) * f) + 0.5f), (int) ((((float) i2) * f) + 0.5f));
    }
}
