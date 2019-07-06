package com.lyft.android.scissors;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.MotionEvent;

/* compiled from: TouchManager */
class i {
    private final int a;
    private final b b;
    private final j[] c;
    private final j[] d;
    private float e;
    private float f;
    private Rect g;
    private float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private float o = -1.0f;
    private j p = new j();
    private Rect q = new Rect();

    private static boolean c(int i2) {
        return i2 == 6 || i2 == 1;
    }

    public i(int i2, b bVar) {
        this.a = i2;
        this.b = bVar;
        this.c = new j[i2];
        this.d = new j[i2];
        this.e = bVar.e();
        this.f = bVar.d();
    }

    @TargetApi(8)
    public void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (actionIndex < this.a) {
            if (c(motionEvent.getActionMasked())) {
                this.d[actionIndex] = null;
                this.c[actionIndex] = null;
            } else {
                b(motionEvent);
            }
            e();
            f();
            if (c(motionEvent.getActionMasked())) {
                g();
            }
        }
    }

    public void a(Matrix matrix) {
        matrix.postTranslate(((float) (-this.k)) / 2.0f, ((float) (-this.l)) / 2.0f);
        matrix.postScale(this.o, this.o);
        matrix.postTranslate(this.p.a(), this.p.b());
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.h = this.b.c();
        int i6 = i4 / 2;
        int i7 = i5 / 2;
        this.g = new Rect(0, 0, i6, i7);
        b(i2, i3, i4, i5);
        this.p.a((float) i6, (float) i7);
        this.k = i2;
        this.l = i3;
        if (i2 > 0 && i3 > 0) {
            i();
            h();
            g();
        }
    }

    public int a() {
        return this.i;
    }

    public int b() {
        return this.j;
    }

    public float c() {
        return this.h;
    }

    public void a(float f2) {
        this.h = f2;
        this.b.a(f2);
    }

    private void e() {
        if (k() == 1) {
            this.p.b(b(0));
        }
    }

    private void f() {
        if (k() == 2) {
            j();
            h();
        }
    }

    private void g() {
        if (this.g != null) {
            float b2 = this.p.b();
            int i2 = this.g.bottom;
            float f2 = (float) i2;
            if (f2 - b2 >= ((float) this.m)) {
                b2 = (float) (i2 - this.m);
            } else if (b2 - f2 >= ((float) this.m)) {
                b2 = (float) (i2 + this.m);
            }
            float a2 = this.p.a();
            int i3 = this.g.right;
            if (a2 <= ((float) (i3 - this.n))) {
                a2 = (float) (i3 - this.n);
            } else if (a2 > ((float) (this.n + i3))) {
                a2 = (float) (i3 + this.n);
            }
            this.p.a(a2, b2);
            float f3 = ((float) this.i) / this.o;
            float f4 = ((float) this.j) / this.o;
            float a3 = (((float) (this.k / 2)) + ((((float) this.g.right) / this.o) - (this.p.a() / this.o))) - (f3 / 2.0f);
            float b3 = (((float) (this.l / 2)) + ((((float) this.g.bottom) / this.o) - (this.p.b() / this.o))) - (f4 / 2.0f);
            this.q.set(Math.max(0, Math.round(a3)), Math.max(0, Math.round(b3)), Math.max(0, Math.round(a3 + f3)), Math.max(0, Math.round(b3 + f4)));
        }
    }

    private void b(MotionEvent motionEvent) {
        for (int i2 = 0; i2 < this.a; i2++) {
            if (i2 < motionEvent.getPointerCount()) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                if (this.c[i2] == null) {
                    this.c[i2] = new j(x, y);
                    this.d[i2] = null;
                } else {
                    if (this.d[i2] == null) {
                        this.d[i2] = new j();
                    }
                    this.d[i2].a(this.c[i2]);
                    this.c[i2].a(x, y);
                }
            } else {
                this.d[i2] = null;
                this.c[i2] = null;
            }
        }
    }

    private void b(int i2, int i3, int i4, int i5) {
        float f2 = ((float) i2) / ((float) i3);
        float f3 = ((float) i4) / ((float) i5);
        float c2 = this.b.c();
        if (Float.compare(0.0f, c2) != 0) {
            f2 = c2;
        }
        if (f2 > f3) {
            this.i = i4 - (this.b.b() * 2);
            this.j = (int) (((float) this.i) * (1.0f / f2));
            return;
        }
        this.j = i5 - (this.b.b() * 2);
        this.i = (int) (((float) this.j) * f2);
    }

    private void h() {
        this.n = b((int) (((float) this.k) * this.o), this.i);
        this.m = b((int) (((float) this.l) * this.o), this.j);
    }

    private void i() {
        this.e = Math.max(((float) this.i) / ((float) this.k), ((float) this.j) / ((float) this.l));
        this.o = Math.max(this.o, this.e);
    }

    private void j() {
        j a2 = a(this.c[0], this.c[1]);
        j a3 = a(0, 1);
        float c2 = a2.c();
        float c3 = a3.c();
        float f2 = this.o;
        if (c3 != 0.0f) {
            f2 *= c2 / c3;
        }
        if (f2 < this.e) {
            f2 = this.e;
        }
        if (f2 > this.f) {
            f2 = this.f;
        }
        this.o = f2;
    }

    private boolean a(int i2) {
        return this.c[i2] != null;
    }

    private int k() {
        int i2 = 0;
        for (j jVar : this.c) {
            if (jVar != null) {
                i2++;
            }
        }
        return i2;
    }

    private j b(int i2) {
        if (!a(i2)) {
            return new j();
        }
        return j.a(this.c[i2], this.d[i2] != null ? this.d[i2] : this.c[i2]);
    }

    private j a(int i2, int i3) {
        if (this.d[i2] == null || this.d[i3] == null) {
            return a(this.c[i2], this.c[i3]);
        }
        return a(this.d[i2], this.d[i3]);
    }

    private static int b(int i2, int i3) {
        return (i2 - i3) / 2;
    }

    private static j a(j jVar, j jVar2) {
        return j.a(jVar2, jVar);
    }

    public Rect d() {
        return this.q;
    }
}
