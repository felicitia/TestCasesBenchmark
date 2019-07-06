package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;

public abstract class ach implements acg {
    protected static volatile acy a;
    protected MotionEvent b;
    protected LinkedList<MotionEvent> c = new LinkedList<>();
    protected long d = 0;
    protected long e = 0;
    protected long f = 0;
    protected long g = 0;
    protected long h = 0;
    protected long i = 0;
    protected long j = 0;
    protected double k;
    protected float l;
    protected float m;
    protected float n;
    protected float o;
    protected boolean p = false;
    protected DisplayMetrics q;
    private double r;
    private double s;
    private boolean t = false;

    protected ach(Context context) {
        try {
            if (((Boolean) ajh.f().a(akl.bL)).booleanValue()) {
                abl.a();
            } else {
                ade.a(a);
            }
            this.q = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    private final String a(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        vy vyVar;
        int i2;
        if (z) {
            try {
                vyVar = a(context, view, activity);
                this.t = true;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                i2 = 7;
                return Integer.toString(i2);
            } catch (Throwable unused2) {
                i2 = 3;
                return Integer.toString(i2);
            }
        } else {
            vyVar = a(context, null);
        }
        if (vyVar != null) {
            if (vyVar.d() != 0) {
                return abl.a(vyVar, str);
            }
        }
        return Integer.toString(5);
    }

    /* access modifiers changed from: protected */
    public abstract long a(StackTraceElement[] stackTraceElementArr) throws zzcw;

    /* access modifiers changed from: protected */
    public abstract vy a(Context context, View view, Activity activity);

    /* access modifiers changed from: protected */
    public abstract vy a(Context context, ta taVar);

    public final String a(Context context) {
        if (adg.a()) {
            if (((Boolean) ajh.f().a(akl.bN)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return a(context, null, false, null, null, null);
    }

    public final String a(Context context, String str, View view) {
        return a(context, str, view, null);
    }

    public final String a(Context context, String str, View view, Activity activity) {
        return a(context, str, true, view, activity, null);
    }

    public final void a(int i2, int i3, int i4) {
        MotionEvent motionEvent;
        if (this.b != null) {
            this.b.recycle();
        }
        if (this.q != null) {
            motionEvent = MotionEvent.obtain(0, (long) i4, 1, this.q.density * ((float) i2), this.q.density * ((float) i3), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            motionEvent = null;
        }
        this.b = motionEvent;
        this.p = false;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.view.MotionEvent r13) {
        /*
            r12 = this;
            boolean r0 = r12.t
            r1 = 0
            if (r0 == 0) goto L_0x0035
            r2 = 0
            r12.g = r2
            r12.f = r2
            r12.e = r2
            r12.d = r2
            r12.h = r2
            r12.j = r2
            r12.i = r2
            java.util.LinkedList<android.view.MotionEvent> r0 = r12.c
            java.util.Iterator r0 = r0.iterator()
        L_0x001b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002b
            java.lang.Object r2 = r0.next()
            android.view.MotionEvent r2 = (android.view.MotionEvent) r2
            r2.recycle()
            goto L_0x001b
        L_0x002b:
            java.util.LinkedList<android.view.MotionEvent> r0 = r12.c
            r0.clear()
            r0 = 0
            r12.b = r0
            r12.t = r1
        L_0x0035:
            int r0 = r13.getAction()
            switch(r0) {
                case 0: goto L_0x0060;
                case 1: goto L_0x003d;
                case 2: goto L_0x003d;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x0072
        L_0x003d:
            float r0 = r13.getRawX()
            double r2 = (double) r0
            float r0 = r13.getRawY()
            double r4 = (double) r0
            double r6 = r12.r
            double r6 = r2 - r6
            double r8 = r12.s
            double r8 = r4 - r8
            double r10 = r12.k
            double r6 = r6 * r6
            double r8 = r8 * r8
            double r6 = r6 + r8
            double r6 = java.lang.Math.sqrt(r6)
            double r10 = r10 + r6
            r12.k = r10
            r12.r = r2
            r12.s = r4
            goto L_0x0072
        L_0x0060:
            r2 = 0
            r12.k = r2
            float r0 = r13.getRawX()
            double r2 = (double) r0
            r12.r = r2
            float r0 = r13.getRawY()
            double r2 = (double) r0
            r12.s = r2
        L_0x0072:
            int r0 = r13.getAction()
            r2 = 1
            r4 = 1
            switch(r0) {
                case 0: goto L_0x0116;
                case 1: goto L_0x00df;
                case 2: goto L_0x0086;
                case 3: goto L_0x007e;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x0134
        L_0x007e:
            long r0 = r12.g
            long r5 = r0 + r2
            r12.g = r5
            goto L_0x0134
        L_0x0086:
            long r2 = r12.e
            int r0 = r13.getHistorySize()
            int r0 = r0 + r4
            long r5 = (long) r0
            long r7 = r2 + r5
            r12.e = r7
            com.google.android.gms.internal.ads.adf r13 = r12.b(r13)     // Catch:{ zzcw -> 0x0134 }
            if (r13 == 0) goto L_0x00a2
            java.lang.Long r0 = r13.d     // Catch:{ zzcw -> 0x0134 }
            if (r0 == 0) goto L_0x00a2
            java.lang.Long r0 = r13.g     // Catch:{ zzcw -> 0x0134 }
            if (r0 == 0) goto L_0x00a2
            r0 = r4
            goto L_0x00a3
        L_0x00a2:
            r0 = r1
        L_0x00a3:
            if (r0 == 0) goto L_0x00b9
            long r2 = r12.i     // Catch:{ zzcw -> 0x0134 }
            java.lang.Long r0 = r13.d     // Catch:{ zzcw -> 0x0134 }
            long r5 = r0.longValue()     // Catch:{ zzcw -> 0x0134 }
            java.lang.Long r0 = r13.g     // Catch:{ zzcw -> 0x0134 }
            long r7 = r0.longValue()     // Catch:{ zzcw -> 0x0134 }
            long r9 = r5 + r7
            long r5 = r2 + r9
            r12.i = r5     // Catch:{ zzcw -> 0x0134 }
        L_0x00b9:
            android.util.DisplayMetrics r0 = r12.q     // Catch:{ zzcw -> 0x0134 }
            if (r0 == 0) goto L_0x00c8
            if (r13 == 0) goto L_0x00c8
            java.lang.Long r0 = r13.e     // Catch:{ zzcw -> 0x0134 }
            if (r0 == 0) goto L_0x00c8
            java.lang.Long r0 = r13.h     // Catch:{ zzcw -> 0x0134 }
            if (r0 == 0) goto L_0x00c8
            r1 = r4
        L_0x00c8:
            if (r1 == 0) goto L_0x0134
            long r0 = r12.j     // Catch:{ zzcw -> 0x0134 }
            java.lang.Long r2 = r13.e     // Catch:{ zzcw -> 0x0134 }
            long r2 = r2.longValue()     // Catch:{ zzcw -> 0x0134 }
            java.lang.Long r13 = r13.h     // Catch:{ zzcw -> 0x0134 }
            long r5 = r13.longValue()     // Catch:{ zzcw -> 0x0134 }
            long r7 = r2 + r5
            long r2 = r0 + r7
            r12.j = r2     // Catch:{ zzcw -> 0x0134 }
            goto L_0x0134
        L_0x00df:
            android.view.MotionEvent r13 = android.view.MotionEvent.obtain(r13)
            r12.b = r13
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.c
            android.view.MotionEvent r0 = r12.b
            r13.add(r0)
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.c
            int r13 = r13.size()
            r0 = 6
            if (r13 <= r0) goto L_0x0100
            java.util.LinkedList<android.view.MotionEvent> r13 = r12.c
            java.lang.Object r13 = r13.remove()
            android.view.MotionEvent r13 = (android.view.MotionEvent) r13
            r13.recycle()
        L_0x0100:
            long r0 = r12.f
            long r5 = r0 + r2
            r12.f = r5
            java.lang.Throwable r13 = new java.lang.Throwable     // Catch:{ zzcw -> 0x0134 }
            r13.<init>()     // Catch:{ zzcw -> 0x0134 }
            java.lang.StackTraceElement[] r13 = r13.getStackTrace()     // Catch:{ zzcw -> 0x0134 }
            long r0 = r12.a(r13)     // Catch:{ zzcw -> 0x0134 }
            r12.h = r0     // Catch:{ zzcw -> 0x0134 }
            goto L_0x0134
        L_0x0116:
            float r0 = r13.getX()
            r12.l = r0
            float r0 = r13.getY()
            r12.m = r0
            float r0 = r13.getRawX()
            r12.n = r0
            float r13 = r13.getRawY()
            r12.o = r13
            long r0 = r12.d
            long r5 = r0 + r2
            r12.d = r5
        L_0x0134:
            r12.p = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ach.a(android.view.MotionEvent):void");
    }

    public void a(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract adf b(MotionEvent motionEvent) throws zzcw;
}
