package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.g.h;
import com.etsy.android.ui.dialog.EtsyDialogFragment;

/* compiled from: AttributeStrategy */
class a implements g {
    private final b a = new b();
    private final e<C0014a, Bitmap> b = new e<>();

    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.a$a reason: collision with other inner class name */
    /* compiled from: AttributeStrategy */
    static class C0014a implements h {
        private final b a;
        private int b;
        private int c;
        private Config d;

        public C0014a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, int i2, Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof C0014a)) {
                return false;
            }
            C0014a aVar = (C0014a) obj;
            if (this.b == aVar.b && this.c == aVar.c && this.d == aVar.d) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * ((this.b * 31) + this.c)) + (this.d != null ? this.d.hashCode() : 0);
        }

        public String toString() {
            return a.d(this.b, this.c, this.d);
        }

        public void a() {
            this.a.a(this);
        }
    }

    /* compiled from: AttributeStrategy */
    static class b extends b<C0014a> {
        b() {
        }

        public C0014a a(int i, int i2, Config config) {
            C0014a aVar = (C0014a) c();
            aVar.a(i, i2, config);
            return aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0014a b() {
            return new C0014a(this);
        }
    }

    a() {
    }

    public void a(Bitmap bitmap) {
        this.b.a(this.a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap a(int i, int i2, Config config) {
        return (Bitmap) this.b.a(this.a.a(i, i2, config));
    }

    public Bitmap a() {
        return (Bitmap) this.b.a();
    }

    public String b(Bitmap bitmap) {
        return d(bitmap);
    }

    public String b(int i, int i2, Config config) {
        return d(i, i2, config);
    }

    public int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AttributeStrategy:\n  ");
        sb.append(this.b);
        return sb.toString();
    }

    private static String d(Bitmap bitmap) {
        return d(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    /* access modifiers changed from: private */
    public static String d(int i, int i2, Config config) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(i);
        sb.append(EtsyDialogFragment.OPT_X_BUTTON);
        sb.append(i2);
        sb.append("], ");
        sb.append(config);
        return sb.toString();
    }
}
