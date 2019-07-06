package com.onfido.android.sdk.capture.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView.ScaleType;

public class ZoomImageView extends AppCompatImageView {
    /* access modifiers changed from: private */
    public float a;
    /* access modifiers changed from: private */
    public Matrix b;
    private Matrix c;
    /* access modifiers changed from: private */
    public e d;
    /* access modifiers changed from: private */
    public float e;
    /* access modifiers changed from: private */
    public float f;
    private float g;
    private float h;
    private float[] i;
    private ScaleType j;
    private boolean k;
    private boolean l;
    private f m;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public int o;
    private int p;
    private int q;
    private float r;
    private float s;
    private float t;
    private float u;
    /* access modifiers changed from: private */
    public ScaleGestureDetector v;
    /* access modifiers changed from: private */
    public GestureDetector w;
    /* access modifiers changed from: private */
    public OnTouchListener x = null;
    /* access modifiers changed from: private */
    public OnTouchImageViewListener y = null;

    /* renamed from: com.onfido.android.sdk.capture.ui.ZoomImageView$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.ZoomImageView.AnonymousClass1.<clinit>():void");
        }
    }

    public interface OnTouchImageViewListener {
        void onMove();
    }

    private class a implements Runnable {
        private long b;
        private float c;
        private float d;
        private float e;
        private float f;
        private boolean g;
        private AccelerateDecelerateInterpolator h = new AccelerateDecelerateInterpolator();
        private PointF i;
        private PointF j;

        a(float f2, float f3, float f4, boolean z) {
            ZoomImageView.this.setState(e.ANIMATE_ZOOM);
            this.b = System.currentTimeMillis();
            this.c = ZoomImageView.this.a;
            this.d = f2;
            this.g = z;
            PointF a2 = ZoomImageView.this.a(f3, f4, false);
            this.e = a2.x;
            this.f = a2.y;
            this.i = ZoomImageView.this.a(this.e, this.f);
            this.j = new PointF((float) (ZoomImageView.this.n / 2), (float) (ZoomImageView.this.o / 2));
        }

        private float a() {
            return this.h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.b)) / 200.0f));
        }

        private void a(float f2) {
            float f3 = this.i.x + ((this.j.x - this.i.x) * f2);
            float f4 = this.i.y + (f2 * (this.j.y - this.i.y));
            PointF a2 = ZoomImageView.this.a(this.e, this.f);
            ZoomImageView.this.b.postTranslate(f3 - a2.x, f4 - a2.y);
        }

        private double b(float f2) {
            return ((double) (this.c + (f2 * (this.d - this.c)))) / ((double) ZoomImageView.this.a);
        }

        public void run() {
            float a2 = a();
            ZoomImageView.this.a(b(a2), this.e, this.f, this.g);
            a(a2);
            ZoomImageView.this.c();
            ZoomImageView.this.setImageMatrix(ZoomImageView.this.b);
            if (ZoomImageView.this.y != null) {
                ZoomImageView.this.y.onMove();
            }
            if (a2 < 1.0f) {
                ZoomImageView.this.a((Runnable) this);
                return;
            }
            ZoomImageView.this.d();
            ZoomImageView.this.setState(e.NONE);
        }
    }

    private class b extends SimpleOnGestureListener {
        private b() {
        }

        /* synthetic */ b(ZoomImageView zoomImageView, AnonymousClass1 r2) {
            this();
        }
    }

    private class c implements OnTouchListener {
        private PointF b;

        private c() {
            this.b = new PointF();
        }

        /* synthetic */ c(ZoomImageView zoomImageView, AnonymousClass1 r2) {
            this();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            ZoomImageView zoomImageView;
            e eVar;
            ZoomImageView.this.v.onTouchEvent(motionEvent);
            ZoomImageView.this.w.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (ZoomImageView.this.d == e.NONE || ZoomImageView.this.d == e.DRAG || ZoomImageView.this.d == e.ZOOM) {
                int action = motionEvent.getAction();
                if (action != 6) {
                    switch (action) {
                        case 0:
                            this.b.set(pointF);
                            zoomImageView = ZoomImageView.this;
                            eVar = e.DRAG;
                            break;
                        case 1:
                            if (ZoomImageView.this.a > ZoomImageView.this.e) {
                                a aVar = new a(ZoomImageView.this.e, motionEvent.getX(), motionEvent.getY(), false);
                                ZoomImageView.this.a((Runnable) aVar);
                                break;
                            }
                            break;
                        case 2:
                            if ((ZoomImageView.this.d == e.DRAG || ZoomImageView.this.d == e.ZOOM) && ZoomImageView.this.a > ZoomImageView.this.e) {
                                float f = pointF.y - this.b.y;
                                ZoomImageView.this.b.postTranslate(ZoomImageView.this.b(pointF.x - this.b.x, (float) ZoomImageView.this.n, ZoomImageView.this.getImageWidth()), ZoomImageView.this.b(f, (float) ZoomImageView.this.o, ZoomImageView.this.getImageHeight()));
                                ZoomImageView.this.b();
                                this.b.set(pointF.x, pointF.y);
                                break;
                            }
                    }
                } else {
                    zoomImageView = ZoomImageView.this;
                    eVar = e.NONE;
                }
                zoomImageView.setState(eVar);
            }
            ZoomImageView.this.setImageMatrix(ZoomImageView.this.b);
            if (ZoomImageView.this.x != null) {
                ZoomImageView.this.x.onTouch(view, motionEvent);
            }
            if (ZoomImageView.this.y != null) {
                ZoomImageView.this.y.onMove();
            }
            return true;
        }
    }

    private class d extends SimpleOnScaleGestureListener {
        private d() {
        }

        /* synthetic */ d(ZoomImageView zoomImageView, AnonymousClass1 r2) {
            this();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomImageView.this.a((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), false);
            if (ZoomImageView.this.y != null) {
                ZoomImageView.this.y.onMove();
            }
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ZoomImageView.this.setState(e.ZOOM);
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            ZoomImageView.this.setState(e.NONE);
            float d = ZoomImageView.this.a;
            boolean z = true;
            if (ZoomImageView.this.a > ZoomImageView.this.f) {
                d = ZoomImageView.this.f;
            } else if (ZoomImageView.this.a < ZoomImageView.this.e) {
                d = ZoomImageView.this.e;
            } else {
                z = false;
            }
            float f = d;
            if (z) {
                a aVar = new a(f, (float) (ZoomImageView.this.n / 2), (float) (ZoomImageView.this.o / 2), false);
                ZoomImageView.this.a((Runnable) aVar);
            }
        }
    }

    private enum e {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    private class f {
        public float a;
        public float b;
        public float c;
        public ScaleType d;

        public f(float f, float f2, float f3, ScaleType scaleType) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = scaleType;
        }
    }

    public ZoomImageView(Context context) {
        super(context);
        a(context);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    private float a(float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 <= f3) {
            f5 = f3 - f4;
            f6 = 0.0f;
        } else {
            f6 = f3 - f4;
            f5 = 0.0f;
        }
        if (f2 < f6) {
            return (-f2) + f6;
        }
        if (f2 > f5) {
            return (-f2) + f5;
        }
        return 0.0f;
    }

    private int a(int i2, int i3, int i4) {
        return i2 != Integer.MIN_VALUE ? i2 != 0 ? i3 : i4 : Math.min(i4, i3);
    }

    /* access modifiers changed from: private */
    public PointF a(float f2, float f3) {
        this.b.getValues(this.i);
        return new PointF(this.i[2] + (getImageWidth() * (f2 / ((float) getDrawable().getIntrinsicWidth()))), this.i[5] + (getImageHeight() * (f3 / ((float) getDrawable().getIntrinsicHeight()))));
    }

    /* access modifiers changed from: private */
    public PointF a(float f2, float f3, boolean z) {
        this.b.getValues(this.i);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f4 = this.i[2];
        float imageWidth = ((f2 - f4) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f3 - this.i[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    private void a() {
        if (this.b != null && this.o != 0 && this.n != 0) {
            this.b.getValues(this.i);
            this.c.setValues(this.i);
            this.u = this.s;
            this.t = this.r;
            this.q = this.o;
            this.p = this.n;
        }
    }

    /* access modifiers changed from: private */
    public void a(double d2, float f2, float f3, boolean z) {
        float f4;
        float f5;
        if (z) {
            f4 = this.g;
            f5 = this.h;
        } else {
            f4 = this.e;
            f5 = this.f;
        }
        float f6 = this.a;
        this.a = (float) (((double) this.a) * d2);
        if (this.a > f5) {
            this.a = f5;
            d2 = (double) (f5 / f6);
        } else if (this.a < f4) {
            this.a = f4;
            d2 = (double) (f4 / f6);
        }
        float f7 = (float) d2;
        this.b.postScale(f7, f7, f2, f3);
        c();
    }

    private void a(int i2, float f2, float f3, float f4, int i3, int i4, int i5) {
        float f5 = (float) i4;
        if (f4 < f5) {
            this.i[i2] = (f5 - (((float) i5) * this.i[0])) * 0.5f;
        } else if (f2 > 0.0f) {
            this.i[i2] = -((f4 - f5) * 0.5f);
        } else {
            this.i[i2] = -((((Math.abs(f2) + (((float) i3) * 0.5f)) / f3) * f4) - (f5 * 0.5f));
        }
    }

    private void a(Context context) {
        super.setClickable(true);
        this.v = new ScaleGestureDetector(context, new d(this, null));
        this.w = new GestureDetector(context, new b(this, null));
        this.b = new Matrix();
        this.c = new Matrix();
        this.i = new float[9];
        this.a = 1.0f;
        if (this.j == null) {
            this.j = ScaleType.FIT_CENTER;
        }
        this.e = 1.0f;
        this.f = 3.0f;
        this.g = this.e * 0.75f;
        this.h = this.f * 1.25f;
        setImageMatrix(this.b);
        setScaleType(ScaleType.MATRIX);
        setState(e.NONE);
        this.l = false;
        super.setOnTouchListener(new c(this, null));
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void a(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }

    /* access modifiers changed from: private */
    public float b(float f2, float f3, float f4) {
        if (f4 <= f3) {
            return 0.0f;
        }
        return f2;
    }

    /* access modifiers changed from: private */
    public void b() {
        this.b.getValues(this.i);
        float f2 = this.i[2];
        float f3 = this.i[5];
        float a2 = a(f2, (float) this.n, getImageWidth());
        float a3 = a(f3, (float) this.o, getImageHeight());
        if (a2 != 0.0f || a3 != 0.0f) {
            this.b.postTranslate(a2, a3);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        b();
        this.b.getValues(this.i);
        if (getImageWidth() < ((float) this.n)) {
            this.i[2] = (((float) this.n) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.o)) {
            this.i[5] = (((float) this.o) - getImageHeight()) / 2.0f;
        }
        this.b.setValues(this.i);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        r1 = java.lang.Math.min(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        r5 = ((float) r11.n) - (r1 * r2);
        r7 = ((float) r11.o) - (r3 * r4);
        r11.r = ((float) r11.n) - r5;
        r11.s = ((float) r11.o) - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        if (isZoomed() != false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007b, code lost:
        r11.b.setScale(r1, r3);
        r11.b.postTranslate(r5 / 2.0f, r7 / 2.0f);
        r11.a = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0091, code lost:
        if (r11.t == 0.0f) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0097, code lost:
        if (r11.u != 0.0f) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009c, code lost:
        r11.c.getValues(r11.i);
        r11.i[0] = (r11.r / r2) * r11.a;
        r11.i[4] = (r11.s / r4) * r11.a;
        r4 = r11.i[2];
        r10 = r11.i[5];
        a(2, r4, r11.t * r11.a, getImageWidth(), r11.p, r11.n, r9);
        a(5, r10, r11.u * r11.a, getImageHeight(), r11.q, r11.o, r0);
        r11.b.setValues(r11.i);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f4, code lost:
        b();
        setImageMatrix(r11.b);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r11 = this;
            android.graphics.drawable.Drawable r0 = r11.getDrawable()
            if (r0 == 0) goto L_0x00fc
            int r1 = r0.getIntrinsicWidth()
            if (r1 == 0) goto L_0x00fc
            int r1 = r0.getIntrinsicHeight()
            if (r1 != 0) goto L_0x0013
            return
        L_0x0013:
            android.graphics.Matrix r1 = r11.b
            if (r1 == 0) goto L_0x00fc
            android.graphics.Matrix r1 = r11.c
            if (r1 != 0) goto L_0x001c
            return
        L_0x001c:
            int r9 = r0.getIntrinsicWidth()
            int r0 = r0.getIntrinsicHeight()
            int r1 = r11.n
            float r1 = (float) r1
            float r2 = (float) r9
            float r1 = r1 / r2
            int r3 = r11.o
            float r3 = (float) r3
            float r4 = (float) r0
            float r3 = r3 / r4
            int[] r5 = com.onfido.android.sdk.capture.ui.ZoomImageView.AnonymousClass1.a
            android.widget.ImageView$ScaleType r6 = r11.j
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 1065353216(0x3f800000, float:1.0)
            switch(r5) {
                case 1: goto L_0x0059;
                case 2: goto L_0x0053;
                case 3: goto L_0x0045;
                case 4: goto L_0x004e;
                case 5: goto L_0x005d;
                default: goto L_0x003d;
            }
        L_0x003d:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "TouchImageView does not support FIT_START or FIT_END"
            r0.<init>(r1)
            throw r0
        L_0x0045:
            float r1 = java.lang.Math.min(r1, r3)
            float r1 = java.lang.Math.min(r6, r1)
            r3 = r1
        L_0x004e:
            float r1 = java.lang.Math.min(r1, r3)
            goto L_0x0057
        L_0x0053:
            float r1 = java.lang.Math.max(r1, r3)
        L_0x0057:
            r3 = r1
            goto L_0x005d
        L_0x0059:
            r1 = 1065353216(0x3f800000, float:1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
        L_0x005d:
            int r5 = r11.n
            float r5 = (float) r5
            float r7 = r1 * r2
            float r5 = r5 - r7
            int r7 = r11.o
            float r7 = (float) r7
            float r8 = r3 * r4
            float r7 = r7 - r8
            int r8 = r11.n
            float r8 = (float) r8
            float r8 = r8 - r5
            r11.r = r8
            int r8 = r11.o
            float r8 = (float) r8
            float r8 = r8 - r7
            r11.s = r8
            boolean r8 = r11.isZoomed()
            if (r8 != 0) goto L_0x008c
            android.graphics.Matrix r0 = r11.b
            r0.setScale(r1, r3)
            android.graphics.Matrix r0 = r11.b
            r1 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r1
            float r7 = r7 / r1
            r0.postTranslate(r5, r7)
            r11.a = r6
            goto L_0x00f4
        L_0x008c:
            float r1 = r11.t
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x0099
            float r1 = r11.u
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x009c
        L_0x0099:
            r11.a()
        L_0x009c:
            android.graphics.Matrix r1 = r11.c
            float[] r3 = r11.i
            r1.getValues(r3)
            float[] r1 = r11.i
            r3 = 0
            float r5 = r11.r
            float r5 = r5 / r2
            float r2 = r11.a
            float r5 = r5 * r2
            r1[r3] = r5
            float[] r1 = r11.i
            r2 = 4
            float r3 = r11.s
            float r3 = r3 / r4
            float r4 = r11.a
            float r3 = r3 * r4
            r1[r2] = r3
            float[] r1 = r11.i
            r2 = 2
            r4 = r1[r2]
            float[] r1 = r11.i
            r2 = 5
            r10 = r1[r2]
            float r1 = r11.t
            float r2 = r11.a
            float r5 = r1 * r2
            float r6 = r11.getImageWidth()
            r3 = 2
            int r7 = r11.p
            int r8 = r11.n
            r2 = r11
            r2.a(r3, r4, r5, r6, r7, r8, r9)
            float r1 = r11.u
            float r2 = r11.a
            float r4 = r1 * r2
            float r5 = r11.getImageHeight()
            r2 = 5
            int r6 = r11.q
            int r7 = r11.o
            r1 = r11
            r3 = r10
            r8 = r0
            r1.a(r2, r3, r4, r5, r6, r7, r8)
            android.graphics.Matrix r0 = r11.b
            float[] r1 = r11.i
            r0.setValues(r1)
        L_0x00f4:
            r11.b()
            android.graphics.Matrix r0 = r11.b
            r11.setImageMatrix(r0)
        L_0x00fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.ZoomImageView.d():void");
    }

    /* access modifiers changed from: private */
    public float getImageHeight() {
        return this.s * this.a;
    }

    /* access modifiers changed from: private */
    public float getImageWidth() {
        return this.r * this.a;
    }

    /* access modifiers changed from: private */
    public void setState(e eVar) {
        this.d = eVar;
    }

    public boolean canScrollHorizontally(int i2) {
        this.b.getValues(this.i);
        float f2 = this.i[2];
        if (getImageWidth() < ((float) this.n)) {
            return false;
        }
        if (f2 < -1.0f || i2 >= 0) {
            return (Math.abs(f2) + ((float) this.n)) + 1.0f < getImageWidth() || i2 <= 0;
        }
        return false;
    }

    public float getCurrentZoom() {
        return this.a;
    }

    public float getMaxZoom() {
        return this.f;
    }

    public float getMinZoom() {
        return this.e;
    }

    public ScaleType getScaleType() {
        return this.j;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF a2 = a((float) (this.n / 2), (float) (this.o / 2), true);
        a2.x /= (float) intrinsicWidth;
        a2.y /= (float) intrinsicHeight;
        return a2;
    }

    public RectF getZoomedRect() {
        if (this.j == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF a2 = a(0.0f, 0.0f, true);
        PointF a3 = a((float) this.n, (float) this.o, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(a2.x / intrinsicWidth, a2.y / intrinsicHeight, a3.x / intrinsicWidth, a3.y / intrinsicHeight);
    }

    public boolean isZoomed() {
        return this.a != 1.0f;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.l = true;
        this.k = true;
        if (this.m != null) {
            setZoom(this.m.a, this.m.b, this.m.c, this.m.d);
            this.m = null;
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(MeasureSpec.getSize(i2), MeasureSpec.getSize(i3));
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i3);
        int mode2 = MeasureSpec.getMode(i3);
        this.n = a(mode, size, intrinsicWidth);
        this.o = a(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.n, this.o);
        d();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.a = bundle.getFloat("saveScale");
            this.i = bundle.getFloatArray("matrix");
            this.c.setValues(this.i);
            this.u = bundle.getFloat("matchViewHeight");
            this.t = bundle.getFloat("matchViewWidth");
            this.q = bundle.getInt("viewHeight");
            this.p = bundle.getInt("viewWidth");
            this.k = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.a);
        bundle.putFloat("matchViewHeight", this.s);
        bundle.putFloat("matchViewWidth", this.r);
        bundle.putInt("viewWidth", this.n);
        bundle.putInt("viewHeight", this.o);
        this.b.getValues(this.i);
        bundle.putFloatArray("matrix", this.i);
        bundle.putBoolean("imageRendered", this.k);
        return bundle;
    }

    public void resetZoom() {
        this.a = 1.0f;
        d();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        a();
        d();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        a();
        d();
    }

    public void setImageResource(int i2) {
        super.setImageResource(i2);
        a();
        d();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        a();
        d();
    }

    public void setMaxZoom(float f2) {
        this.f = f2;
        this.h = this.f * 1.25f;
    }

    public void setMinZoom(float f2) {
        this.e = f2;
        this.g = this.e * 0.75f;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.x = onTouchListener;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.j = scaleType;
            if (this.l) {
                setZoom(this);
            }
        }
    }

    public void setZoom(float f2) {
        setZoom(f2, 0.5f, 0.5f);
    }

    public void setZoom(float f2, float f3, float f4) {
        setZoom(f2, f3, f4, this.j);
    }

    public void setZoom(float f2, float f3, float f4, ScaleType scaleType) {
        if (!this.l) {
            f fVar = new f(f2, f3, f4, scaleType);
            this.m = fVar;
            return;
        }
        if (scaleType != this.j) {
            setScaleType(scaleType);
        }
        resetZoom();
        a((double) f2, (float) (this.n / 2), (float) (this.o / 2), false);
        this.b.getValues(this.i);
        this.i[2] = -((f3 * getImageWidth()) - (((float) this.n) * 0.5f));
        this.i[5] = -((f4 * getImageHeight()) - (((float) this.o) * 0.5f));
        this.b.setValues(this.i);
        b();
        setImageMatrix(this.b);
    }

    public void setZoom(ZoomImageView zoomImageView) {
        PointF scrollPosition = zoomImageView.getScrollPosition();
        setZoom(zoomImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, zoomImageView.getScaleType());
    }
}
