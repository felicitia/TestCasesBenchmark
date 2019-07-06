package com.bumptech.glide.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: AnimatedGifEncoder */
public class a {
    private int a;
    private int b;
    private Integer c = null;
    private int d;
    private int e = -1;
    private int f = 0;
    private boolean g = false;
    private OutputStream h;
    private Bitmap i;
    private byte[] j;
    private byte[] k;
    private int l;
    private byte[] m;
    private boolean[] n = new boolean[256];
    private int o = 7;
    private int p = -1;
    private boolean q = false;
    private boolean r = true;
    private boolean s = false;
    private int t = 10;
    private boolean u;

    public void a(int i2) {
        this.f = Math.round(((float) i2) / 10.0f);
    }

    public boolean a(Bitmap bitmap) {
        boolean z = false;
        if (bitmap == null || !this.g) {
            return false;
        }
        try {
            if (!this.s) {
                a(bitmap.getWidth(), bitmap.getHeight());
            }
            this.i = bitmap;
            c();
            b();
            if (this.r) {
                f();
                h();
                if (this.e >= 0) {
                    g();
                }
            }
            d();
            e();
            if (!this.r) {
                h();
            }
            i();
            this.r = false;
            z = true;
        } catch (IOException unused) {
        }
        return z;
    }

    public boolean a() {
        boolean z;
        if (!this.g) {
            return false;
        }
        this.g = false;
        try {
            this.h.write(59);
            this.h.flush();
            if (this.q) {
                this.h.close();
            }
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        this.d = 0;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.q = false;
        this.r = true;
        return z;
    }

    public void a(int i2, int i3) {
        if (!this.g || this.r) {
            this.a = i2;
            this.b = i3;
            if (this.a < 1) {
                this.a = 320;
            }
            if (this.b < 1) {
                this.b = 240;
            }
            this.s = true;
        }
    }

    public boolean a(OutputStream outputStream) {
        boolean z = false;
        if (outputStream == null) {
            return false;
        }
        this.q = false;
        this.h = outputStream;
        try {
            a("GIF89a");
            z = true;
        } catch (IOException unused) {
        }
        this.g = z;
        return z;
    }

    private void b() {
        int length = this.j.length;
        int i2 = length / 3;
        this.k = new byte[i2];
        c cVar = new c(this.j, length, this.t);
        this.m = cVar.d();
        for (int i3 = 0; i3 < this.m.length; i3 += 3) {
            byte b2 = this.m[i3];
            int i4 = i3 + 2;
            this.m[i3] = this.m[i4];
            this.m[i4] = b2;
            this.n[i3 / 3] = false;
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2) {
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            int a2 = cVar.a(this.j[i6] & 255, this.j[i7] & 255, this.j[i8] & 255);
            this.n[a2] = true;
            this.k[i5] = (byte) a2;
            i5++;
            i6 = i9;
        }
        this.j = null;
        this.l = 8;
        this.o = 7;
        if (this.c != null) {
            this.d = b(this.c.intValue());
        } else if (this.u) {
            this.d = b(0);
        }
    }

    private int b(int i2) {
        if (this.m == null) {
            return -1;
        }
        int red = Color.red(i2);
        int green = Color.green(i2);
        int blue = Color.blue(i2);
        int i3 = 0;
        int length = this.m.length;
        int i4 = 16777216;
        int i5 = 0;
        while (i3 < length) {
            int i6 = i3 + 1;
            int i7 = red - (this.m[i3] & 255);
            int i8 = i6 + 1;
            int i9 = green - (this.m[i6] & 255);
            int i10 = blue - (this.m[i8] & 255);
            int i11 = (i7 * i7) + (i9 * i9) + (i10 * i10);
            int i12 = i8 / 3;
            if (this.n[i12] && i11 < i4) {
                i4 = i11;
                i5 = i12;
            }
            i3 = i8 + 1;
        }
        return i5;
    }

    private void c() {
        int width = this.i.getWidth();
        int height = this.i.getHeight();
        if (!(width == this.a && height == this.b)) {
            Bitmap createBitmap = Bitmap.createBitmap(this.a, this.b, Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(createBitmap, 0.0f, 0.0f, null);
            this.i = createBitmap;
        }
        int[] iArr = new int[(width * height)];
        this.i.getPixels(iArr, 0, width, 0, 0, width, height);
        boolean z = false;
        this.j = new byte[(iArr.length * 3)];
        this.u = false;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = iArr[i2];
            if (i5 == 0) {
                i3++;
            }
            int i6 = i4 + 1;
            this.j[i4] = (byte) (i5 & 255);
            int i7 = i6 + 1;
            this.j[i6] = (byte) ((i5 >> 8) & 255);
            int i8 = i7 + 1;
            this.j[i7] = (byte) ((i5 >> 16) & 255);
            i2++;
            i4 = i8;
        }
        double length2 = ((double) (100 * i3)) / ((double) iArr.length);
        if (length2 > 4.0d) {
            z = true;
        }
        this.u = z;
        if (Log.isLoggable("AnimatedGifEncoder", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("got pixels for frame with ");
            sb.append(length2);
            sb.append("% transparent pixels");
            Log.d("AnimatedGifEncoder", sb.toString());
        }
    }

    private void d() throws IOException {
        int i2;
        int i3;
        this.h.write(33);
        this.h.write(249);
        this.h.write(4);
        if (this.c != null || this.u) {
            i2 = 1;
            i3 = 2;
        } else {
            i3 = 0;
            i2 = 0;
        }
        if (this.p >= 0) {
            i3 = this.p & 7;
        }
        this.h.write((i3 << 2) | 0 | 0 | i2);
        c(this.f);
        this.h.write(this.d);
        this.h.write(0);
    }

    private void e() throws IOException {
        this.h.write(44);
        c(0);
        c(0);
        c(this.a);
        c(this.b);
        if (this.r) {
            this.h.write(0);
        } else {
            this.h.write(128 | this.o);
        }
    }

    private void f() throws IOException {
        c(this.a);
        c(this.b);
        this.h.write(this.o | 240);
        this.h.write(0);
        this.h.write(0);
    }

    private void g() throws IOException {
        this.h.write(33);
        this.h.write(255);
        this.h.write(11);
        a("NETSCAPE2.0");
        this.h.write(3);
        this.h.write(1);
        c(this.e);
        this.h.write(0);
    }

    private void h() throws IOException {
        this.h.write(this.m, 0, this.m.length);
        int length = 768 - this.m.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.h.write(0);
        }
    }

    private void i() throws IOException {
        new b(this.a, this.b, this.k, this.l).b(this.h);
    }

    private void c(int i2) throws IOException {
        this.h.write(i2 & 255);
        this.h.write((i2 >> 8) & 255);
    }

    private void a(String str) throws IOException {
        for (int i2 = 0; i2 < str.length(); i2++) {
            this.h.write((byte) str.charAt(i2));
        }
    }
}
