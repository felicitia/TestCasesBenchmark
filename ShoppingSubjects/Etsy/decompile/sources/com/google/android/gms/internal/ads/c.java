package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Set;

@bu
public final class c extends m {
    private static final Set<String> a = CollectionUtils.setOf((T[]) new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"});
    private String b = "top-right";
    private boolean c = true;
    private int d = 0;
    private int e = 0;
    private int f = -1;
    private int g = 0;
    private int h = 0;
    private int i = -1;
    private final Object j = new Object();
    private final nn k;
    private final Activity l;
    private ot m;
    private ImageView n;
    private LinearLayout o;
    private n p;
    private PopupWindow q;
    private RelativeLayout r;
    private ViewGroup s;

    public c(nn nnVar, n nVar) {
        super(nnVar, "resize");
        this.k = nnVar;
        this.l = nnVar.zzto();
        this.p = nVar;
    }

    private final void b(int i2, int i3) {
        a(i2, i3 - ao.e().c(this.l)[0], this.i, this.f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        r5 = r9.e + r9.h;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b6, code lost:
        r5 = ((r9.e + r9.h) + r9.f) - 50;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e9, code lost:
        if (r0 < 0) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ec, code lost:
        if ((r0 + 50) > r3) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f0, code lost:
        if (r5 < r1[0]) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f5, code lost:
        if ((r5 + 50) <= r1[1]) goto L_0x00f8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0107  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int[] b() {
        /*
            r9 = this;
            com.google.android.gms.internal.ads.hd r0 = com.google.android.gms.ads.internal.ao.e()
            android.app.Activity r1 = r9.l
            int[] r0 = r0.b(r1)
            com.google.android.gms.internal.ads.hd r1 = com.google.android.gms.ads.internal.ao.e()
            android.app.Activity r2 = r9.l
            int[] r1 = r1.c(r2)
            r2 = 0
            r3 = r0[r2]
            r4 = 1
            r0 = r0[r4]
            int r5 = r9.i
            r6 = 2
            r7 = 50
            if (r5 < r7) goto L_0x00fd
            int r5 = r9.i
            if (r5 <= r3) goto L_0x0027
            goto L_0x00fd
        L_0x0027:
            int r5 = r9.f
            if (r5 < r7) goto L_0x00fa
            int r5 = r9.f
            if (r5 <= r0) goto L_0x0031
            goto L_0x00fa
        L_0x0031:
            int r5 = r9.f
            if (r5 != r0) goto L_0x003d
            int r0 = r9.i
            if (r0 != r3) goto L_0x003d
            java.lang.String r0 = "Cannot resize to a full-screen ad."
            goto L_0x00ff
        L_0x003d:
            boolean r0 = r9.c
            if (r0 == 0) goto L_0x00f8
            java.lang.String r0 = r9.b
            r5 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1364013995: goto L_0x007e;
                case -1012429441: goto L_0x0074;
                case -655373719: goto L_0x006a;
                case 1163912186: goto L_0x0060;
                case 1288627767: goto L_0x0056;
                case 1755462605: goto L_0x004c;
                default: goto L_0x004b;
            }
        L_0x004b:
            goto L_0x0088
        L_0x004c:
            java.lang.String r8 = "top-center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = r4
            goto L_0x0089
        L_0x0056:
            java.lang.String r8 = "bottom-center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = 4
            goto L_0x0089
        L_0x0060:
            java.lang.String r8 = "bottom-right"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = 5
            goto L_0x0089
        L_0x006a:
            java.lang.String r8 = "bottom-left"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = 3
            goto L_0x0089
        L_0x0074:
            java.lang.String r8 = "top-left"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = r2
            goto L_0x0089
        L_0x007e:
            java.lang.String r8 = "center"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0088
            r0 = r6
            goto L_0x0089
        L_0x0088:
            r0 = r5
        L_0x0089:
            switch(r0) {
                case 0: goto L_0x00e3;
                case 1: goto L_0x00d7;
                case 2: goto L_0x00c0;
                case 3: goto L_0x00b1;
                case 4: goto L_0x00a5;
                case 5: goto L_0x009b;
                default: goto L_0x008c;
            }
        L_0x008c:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            int r5 = r9.i
            int r0 = r0 + r5
            int r0 = r0 - r7
        L_0x0095:
            int r5 = r9.e
            int r8 = r9.h
            int r5 = r5 + r8
            goto L_0x00e9
        L_0x009b:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            int r5 = r9.i
            int r0 = r0 + r5
            int r0 = r0 - r7
            goto L_0x00b6
        L_0x00a5:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            int r5 = r9.i
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x00b6
        L_0x00b1:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
        L_0x00b6:
            int r5 = r9.e
            int r8 = r9.h
            int r5 = r5 + r8
            int r8 = r9.f
            int r5 = r5 + r8
            int r5 = r5 - r7
            goto L_0x00e9
        L_0x00c0:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            int r5 = r9.i
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.e
            int r8 = r9.h
            int r5 = r5 + r8
            int r8 = r9.f
            int r8 = r8 / r6
            int r5 = r5 + r8
            int r5 = r5 + -25
            goto L_0x00e9
        L_0x00d7:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            int r5 = r9.i
            int r5 = r5 / r6
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x0095
        L_0x00e3:
            int r0 = r9.d
            int r5 = r9.g
            int r0 = r0 + r5
            goto L_0x0095
        L_0x00e9:
            if (r0 < 0) goto L_0x0102
            int r0 = r0 + r7
            if (r0 > r3) goto L_0x0102
            r0 = r1[r2]
            if (r5 < r0) goto L_0x0102
            int r5 = r5 + r7
            r0 = r1[r4]
            if (r5 <= r0) goto L_0x00f8
            goto L_0x0102
        L_0x00f8:
            r0 = r4
            goto L_0x0103
        L_0x00fa:
            java.lang.String r0 = "Height is too small or too large."
            goto L_0x00ff
        L_0x00fd:
            java.lang.String r0 = "Width is too small or too large."
        L_0x00ff:
            com.google.android.gms.internal.ads.gv.e(r0)
        L_0x0102:
            r0 = r2
        L_0x0103:
            if (r0 != 0) goto L_0x0107
            r0 = 0
            return r0
        L_0x0107:
            boolean r0 = r9.c
            if (r0 == 0) goto L_0x011c
            int[] r0 = new int[r6]
            int r1 = r9.d
            int r3 = r9.g
            int r1 = r1 + r3
            r0[r2] = r1
            int r1 = r9.e
            int r2 = r9.h
            int r1 = r1 + r2
            r0[r4] = r1
            return r0
        L_0x011c:
            com.google.android.gms.internal.ads.hd r0 = com.google.android.gms.ads.internal.ao.e()
            android.app.Activity r1 = r9.l
            int[] r0 = r0.b(r1)
            com.google.android.gms.internal.ads.hd r1 = com.google.android.gms.ads.internal.ao.e()
            android.app.Activity r3 = r9.l
            int[] r1 = r1.c(r3)
            r0 = r0[r2]
            int r3 = r9.d
            int r5 = r9.g
            int r3 = r3 + r5
            int r5 = r9.e
            int r7 = r9.h
            int r5 = r5 + r7
            if (r3 >= 0) goto L_0x0140
            r0 = r2
            goto L_0x014a
        L_0x0140:
            int r7 = r9.i
            int r7 = r7 + r3
            if (r7 <= r0) goto L_0x0149
            int r3 = r9.i
            int r0 = r0 - r3
            goto L_0x014a
        L_0x0149:
            r0 = r3
        L_0x014a:
            r3 = r1[r2]
            if (r5 >= r3) goto L_0x0151
            r5 = r1[r2]
            goto L_0x015e
        L_0x0151:
            int r3 = r9.f
            int r3 = r3 + r5
            r7 = r1[r4]
            if (r3 <= r7) goto L_0x015e
            r1 = r1[r4]
            int r3 = r9.f
            int r5 = r1 - r3
        L_0x015e:
            int[] r1 = new int[r6]
            r1[r2] = r0
            r1[r4] = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.c.b():int[]");
    }

    public final void a(int i2, int i3) {
        this.d = i2;
        this.e = i3;
    }

    public final void a(int i2, int i3, boolean z) {
        synchronized (this.j) {
            this.d = i2;
            this.e = i3;
            if (this.q != null && z) {
                int[] b2 = b();
                if (b2 != null) {
                    PopupWindow popupWindow = this.q;
                    ajh.a();
                    int a2 = jp.a((Context) this.l, b2[0]);
                    ajh.a();
                    popupWindow.update(a2, jp.a((Context) this.l, b2[1]), this.q.getWidth(), this.q.getHeight());
                    b(b2[0], b2[1]);
                } else {
                    a(true);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0262, code lost:
        r13.o.setOnClickListener(new com.google.android.gms.internal.ads.d(r13));
        r13.o.setContentDescription("Close button");
        r13.r.addView(r13.o, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r6 = r13.q;
        r14 = r14.getDecorView();
        com.google.android.gms.internal.ads.ajh.a();
        r7 = com.google.android.gms.internal.ads.jp.a((android.content.Context) r13.l, r3[0]);
        com.google.android.gms.internal.ads.ajh.a();
        r6.showAtLocation(r14, 0, r7, com.google.android.gms.internal.ads.jp.a((android.content.Context) r13.l, r3[1]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r14 = r3[0];
        r6 = r3[1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x029f, code lost:
        if (r13.p == null) goto L_0x02aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02a1, code lost:
        r13.p.zza(r14, r6, r13.i, r13.f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02aa, code lost:
        r13.k.zza(com.google.android.gms.internal.ads.ot.a(r4, r5));
        b(r3[0], r3[1]);
        c("resized");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02c0, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02c1, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02c2, code lost:
        r1 = "Cannot show popup window: ";
        r14 = java.lang.String.valueOf(r14.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02d0, code lost:
        if (r14.length() != 0) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02d2, code lost:
        r14 = r1.concat(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02d7, code lost:
        r14 = new java.lang.String(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02dc, code lost:
        a(r14);
        r13.r.removeView(r13.k.getView());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02ec, code lost:
        if (r13.s != null) goto L_0x02ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02ee, code lost:
        r13.s.removeView(r13.n);
        r13.s.addView(r13.k.getView());
        r13.k.zza(r13.m);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0308, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0242, code lost:
        r6.addRule(11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0249, code lost:
        r6.addRule(14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0250, code lost:
        r6.addRule(9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.j
            monitor-enter(r0)
            android.app.Activity r1 = r13.l     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x000e
            java.lang.String r14 = "Not an activity context. Cannot resize."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x000e:
            com.google.android.gms.internal.ads.nn r1 = r13.k     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ot r1 = r1.zzud()     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x001d
            java.lang.String r14 = "Webview is not yet available, size is not set."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x001d:
            com.google.android.gms.internal.ads.nn r1 = r13.k     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ot r1 = r1.zzud()     // Catch:{ all -> 0x0317 }
            boolean r1 = r1.d()     // Catch:{ all -> 0x0317 }
            if (r1 == 0) goto L_0x0030
            java.lang.String r14 = "Is interstitial. Cannot resize an interstitial."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x0030:
            com.google.android.gms.internal.ads.nn r1 = r13.k     // Catch:{ all -> 0x0317 }
            boolean r1 = r1.zzuj()     // Catch:{ all -> 0x0317 }
            if (r1 == 0) goto L_0x003f
            java.lang.String r14 = "Cannot resize an expanded banner."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x003f:
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x005e
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = "width"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0317 }
            int r1 = com.google.android.gms.internal.ads.hd.b(r1)     // Catch:{ all -> 0x0317 }
            r13.i = r1     // Catch:{ all -> 0x0317 }
        L_0x005e:
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x007d
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = "height"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0317 }
            int r1 = com.google.android.gms.internal.ads.hd.b(r1)     // Catch:{ all -> 0x0317 }
            r13.f = r1     // Catch:{ all -> 0x0317 }
        L_0x007d:
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x009c
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = "offsetX"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0317 }
            int r1 = com.google.android.gms.internal.ads.hd.b(r1)     // Catch:{ all -> 0x0317 }
            r13.g = r1     // Catch:{ all -> 0x0317 }
        L_0x009c:
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x00bb
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = "offsetY"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0317 }
            int r1 = com.google.android.gms.internal.ads.hd.b(r1)     // Catch:{ all -> 0x0317 }
            r13.h = r1     // Catch:{ all -> 0x0317 }
        L_0x00bb:
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x00d7
            java.lang.String r1 = "allowOffscreen"
            java.lang.Object r1 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0317 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ all -> 0x0317 }
            r13.c = r1     // Catch:{ all -> 0x0317 }
        L_0x00d7:
            java.lang.String r1 = "customClosePosition"
            java.lang.Object r14 = r14.get(r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x0317 }
            boolean r1 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x0317 }
            if (r1 != 0) goto L_0x00e7
            r13.b = r14     // Catch:{ all -> 0x0317 }
        L_0x00e7:
            int r14 = r13.i     // Catch:{ all -> 0x0317 }
            r1 = 0
            r2 = 1
            if (r14 < 0) goto L_0x00f3
            int r14 = r13.f     // Catch:{ all -> 0x0317 }
            if (r14 < 0) goto L_0x00f3
            r14 = r2
            goto L_0x00f4
        L_0x00f3:
            r14 = r1
        L_0x00f4:
            if (r14 != 0) goto L_0x00fd
            java.lang.String r14 = "Invalid width and height options. Cannot resize."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x00fd:
            android.app.Activity r14 = r13.l     // Catch:{ all -> 0x0317 }
            android.view.Window r14 = r14.getWindow()     // Catch:{ all -> 0x0317 }
            if (r14 == 0) goto L_0x0310
            android.view.View r3 = r14.getDecorView()     // Catch:{ all -> 0x0317 }
            if (r3 != 0) goto L_0x010d
            goto L_0x0310
        L_0x010d:
            int[] r3 = r13.b()     // Catch:{ all -> 0x0317 }
            if (r3 != 0) goto L_0x011a
            java.lang.String r14 = "Resize location out of screen or close button is not visible."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x011a:
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ all -> 0x0317 }
            android.app.Activity r4 = r13.l     // Catch:{ all -> 0x0317 }
            int r5 = r13.i     // Catch:{ all -> 0x0317 }
            int r4 = com.google.android.gms.internal.ads.jp.a(r4, r5)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ all -> 0x0317 }
            android.app.Activity r5 = r13.l     // Catch:{ all -> 0x0317 }
            int r6 = r13.f     // Catch:{ all -> 0x0317 }
            int r5 = com.google.android.gms.internal.ads.jp.a(r5, r6)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r6 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r6 = r6.getView()     // Catch:{ all -> 0x0317 }
            android.view.ViewParent r6 = r6.getParent()     // Catch:{ all -> 0x0317 }
            if (r6 == 0) goto L_0x0309
            boolean r7 = r6 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x0309
            r7 = r6
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r8 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r8 = r8.getView()     // Catch:{ all -> 0x0317 }
            r7.removeView(r8)     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r7 = r13.q     // Catch:{ all -> 0x0317 }
            if (r7 != 0) goto L_0x017f
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6     // Catch:{ all -> 0x0317 }
            r13.s = r6     // Catch:{ all -> 0x0317 }
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r6 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r6 = r6.getView()     // Catch:{ all -> 0x0317 }
            android.graphics.Bitmap r6 = com.google.android.gms.internal.ads.hd.a(r6)     // Catch:{ all -> 0x0317 }
            android.widget.ImageView r7 = new android.widget.ImageView     // Catch:{ all -> 0x0317 }
            android.app.Activity r8 = r13.l     // Catch:{ all -> 0x0317 }
            r7.<init>(r8)     // Catch:{ all -> 0x0317 }
            r13.n = r7     // Catch:{ all -> 0x0317 }
            android.widget.ImageView r7 = r13.n     // Catch:{ all -> 0x0317 }
            r7.setImageBitmap(r6)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r6 = r13.k     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ot r6 = r6.zzud()     // Catch:{ all -> 0x0317 }
            r13.m = r6     // Catch:{ all -> 0x0317 }
            android.view.ViewGroup r6 = r13.s     // Catch:{ all -> 0x0317 }
            android.widget.ImageView r7 = r13.n     // Catch:{ all -> 0x0317 }
            r6.addView(r7)     // Catch:{ all -> 0x0317 }
            goto L_0x0184
        L_0x017f:
            android.widget.PopupWindow r6 = r13.q     // Catch:{ all -> 0x0317 }
            r6.dismiss()     // Catch:{ all -> 0x0317 }
        L_0x0184:
            android.widget.RelativeLayout r6 = new android.widget.RelativeLayout     // Catch:{ all -> 0x0317 }
            android.app.Activity r7 = r13.l     // Catch:{ all -> 0x0317 }
            r6.<init>(r7)     // Catch:{ all -> 0x0317 }
            r13.r = r6     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r6 = r13.r     // Catch:{ all -> 0x0317 }
            r6.setBackgroundColor(r1)     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r6 = r13.r     // Catch:{ all -> 0x0317 }
            android.view.ViewGroup$LayoutParams r7 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x0317 }
            r7.<init>(r4, r5)     // Catch:{ all -> 0x0317 }
            r6.setLayoutParams(r7)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r6 = r13.r     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r6 = com.google.android.gms.internal.ads.hd.a(r6, r4, r5, r1)     // Catch:{ all -> 0x0317 }
            r13.q = r6     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r6 = r13.q     // Catch:{ all -> 0x0317 }
            r6.setOutsideTouchable(r2)     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r6 = r13.q     // Catch:{ all -> 0x0317 }
            r6.setTouchable(r2)     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r6 = r13.q     // Catch:{ all -> 0x0317 }
            boolean r7 = r13.c     // Catch:{ all -> 0x0317 }
            r7 = r7 ^ r2
            r6.setClippingEnabled(r7)     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r6 = r13.r     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r7 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r7 = r7.getView()     // Catch:{ all -> 0x0317 }
            r8 = -1
            r6.addView(r7, r8, r8)     // Catch:{ all -> 0x0317 }
            android.widget.LinearLayout r6 = new android.widget.LinearLayout     // Catch:{ all -> 0x0317 }
            android.app.Activity r7 = r13.l     // Catch:{ all -> 0x0317 }
            r6.<init>(r7)     // Catch:{ all -> 0x0317 }
            r13.o = r6     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout$LayoutParams r6 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ all -> 0x0317 }
            android.app.Activity r7 = r13.l     // Catch:{ all -> 0x0317 }
            r9 = 50
            int r7 = com.google.android.gms.internal.ads.jp.a(r7, r9)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ all -> 0x0317 }
            android.app.Activity r10 = r13.l     // Catch:{ all -> 0x0317 }
            int r9 = com.google.android.gms.internal.ads.jp.a(r10, r9)     // Catch:{ all -> 0x0317 }
            r6.<init>(r7, r9)     // Catch:{ all -> 0x0317 }
            java.lang.String r7 = r13.b     // Catch:{ all -> 0x0317 }
            int r9 = r7.hashCode()     // Catch:{ all -> 0x0317 }
            switch(r9) {
                case -1364013995: goto L_0x0223;
                case -1012429441: goto L_0x0219;
                case -655373719: goto L_0x020f;
                case 1163912186: goto L_0x0205;
                case 1288627767: goto L_0x01fb;
                case 1755462605: goto L_0x01f1;
                default: goto L_0x01f0;
            }     // Catch:{ all -> 0x0317 }
        L_0x01f0:
            goto L_0x022d
        L_0x01f1:
            java.lang.String r9 = "top-center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = r2
            goto L_0x022e
        L_0x01fb:
            java.lang.String r9 = "bottom-center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = 4
            goto L_0x022e
        L_0x0205:
            java.lang.String r9 = "bottom-right"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = 5
            goto L_0x022e
        L_0x020f:
            java.lang.String r9 = "bottom-left"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = 3
            goto L_0x022e
        L_0x0219:
            java.lang.String r9 = "top-left"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = r1
            goto L_0x022e
        L_0x0223:
            java.lang.String r9 = "center"
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x022d
            r7 = 2
            goto L_0x022e
        L_0x022d:
            r7 = r8
        L_0x022e:
            r8 = 9
            r9 = 14
            r10 = 11
            r11 = 12
            r12 = 10
            switch(r7) {
                case 0: goto L_0x025e;
                case 1: goto L_0x025a;
                case 2: goto L_0x0254;
                case 3: goto L_0x024d;
                case 4: goto L_0x0246;
                case 5: goto L_0x023f;
                default: goto L_0x023b;
            }     // Catch:{ all -> 0x0317 }
        L_0x023b:
            r6.addRule(r12)     // Catch:{ all -> 0x0317 }
            goto L_0x0242
        L_0x023f:
            r6.addRule(r11)     // Catch:{ all -> 0x0317 }
        L_0x0242:
            r6.addRule(r10)     // Catch:{ all -> 0x0317 }
            goto L_0x0262
        L_0x0246:
            r6.addRule(r11)     // Catch:{ all -> 0x0317 }
        L_0x0249:
            r6.addRule(r9)     // Catch:{ all -> 0x0317 }
            goto L_0x0262
        L_0x024d:
            r6.addRule(r11)     // Catch:{ all -> 0x0317 }
        L_0x0250:
            r6.addRule(r8)     // Catch:{ all -> 0x0317 }
            goto L_0x0262
        L_0x0254:
            r7 = 13
            r6.addRule(r7)     // Catch:{ all -> 0x0317 }
            goto L_0x0262
        L_0x025a:
            r6.addRule(r12)     // Catch:{ all -> 0x0317 }
            goto L_0x0249
        L_0x025e:
            r6.addRule(r12)     // Catch:{ all -> 0x0317 }
            goto L_0x0250
        L_0x0262:
            android.widget.LinearLayout r7 = r13.o     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.d r8 = new com.google.android.gms.internal.ads.d     // Catch:{ all -> 0x0317 }
            r8.<init>(r13)     // Catch:{ all -> 0x0317 }
            r7.setOnClickListener(r8)     // Catch:{ all -> 0x0317 }
            android.widget.LinearLayout r7 = r13.o     // Catch:{ all -> 0x0317 }
            java.lang.String r8 = "Close button"
            r7.setContentDescription(r8)     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r7 = r13.r     // Catch:{ all -> 0x0317 }
            android.widget.LinearLayout r8 = r13.o     // Catch:{ all -> 0x0317 }
            r7.addView(r8, r6)     // Catch:{ all -> 0x0317 }
            android.widget.PopupWindow r6 = r13.q     // Catch:{ RuntimeException -> 0x02c1 }
            android.view.View r14 = r14.getDecorView()     // Catch:{ RuntimeException -> 0x02c1 }
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ RuntimeException -> 0x02c1 }
            android.app.Activity r7 = r13.l     // Catch:{ RuntimeException -> 0x02c1 }
            r8 = r3[r1]     // Catch:{ RuntimeException -> 0x02c1 }
            int r7 = com.google.android.gms.internal.ads.jp.a(r7, r8)     // Catch:{ RuntimeException -> 0x02c1 }
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ RuntimeException -> 0x02c1 }
            android.app.Activity r8 = r13.l     // Catch:{ RuntimeException -> 0x02c1 }
            r9 = r3[r2]     // Catch:{ RuntimeException -> 0x02c1 }
            int r8 = com.google.android.gms.internal.ads.jp.a(r8, r9)     // Catch:{ RuntimeException -> 0x02c1 }
            r6.showAtLocation(r14, r1, r7, r8)     // Catch:{ RuntimeException -> 0x02c1 }
            r14 = r3[r1]     // Catch:{ all -> 0x0317 }
            r6 = r3[r2]     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.n r7 = r13.p     // Catch:{ all -> 0x0317 }
            if (r7 == 0) goto L_0x02aa
            com.google.android.gms.internal.ads.n r7 = r13.p     // Catch:{ all -> 0x0317 }
            int r8 = r13.i     // Catch:{ all -> 0x0317 }
            int r9 = r13.f     // Catch:{ all -> 0x0317 }
            r7.zza(r14, r6, r8, r9)     // Catch:{ all -> 0x0317 }
        L_0x02aa:
            com.google.android.gms.internal.ads.nn r14 = r13.k     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ot r4 = com.google.android.gms.internal.ads.ot.a(r4, r5)     // Catch:{ all -> 0x0317 }
            r14.zza(r4)     // Catch:{ all -> 0x0317 }
            r14 = r3[r1]     // Catch:{ all -> 0x0317 }
            r1 = r3[r2]     // Catch:{ all -> 0x0317 }
            r13.b(r14, r1)     // Catch:{ all -> 0x0317 }
            java.lang.String r14 = "resized"
            r13.c(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x02c1:
            r14 = move-exception
            java.lang.String r1 = "Cannot show popup window: "
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0317 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x0317 }
            int r2 = r14.length()     // Catch:{ all -> 0x0317 }
            if (r2 == 0) goto L_0x02d7
            java.lang.String r14 = r1.concat(r14)     // Catch:{ all -> 0x0317 }
            goto L_0x02dc
        L_0x02d7:
            java.lang.String r14 = new java.lang.String     // Catch:{ all -> 0x0317 }
            r14.<init>(r1)     // Catch:{ all -> 0x0317 }
        L_0x02dc:
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            android.widget.RelativeLayout r14 = r13.r     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r1 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0317 }
            r14.removeView(r1)     // Catch:{ all -> 0x0317 }
            android.view.ViewGroup r14 = r13.s     // Catch:{ all -> 0x0317 }
            if (r14 == 0) goto L_0x0307
            android.view.ViewGroup r14 = r13.s     // Catch:{ all -> 0x0317 }
            android.widget.ImageView r1 = r13.n     // Catch:{ all -> 0x0317 }
            r14.removeView(r1)     // Catch:{ all -> 0x0317 }
            android.view.ViewGroup r14 = r13.s     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r1 = r13.k     // Catch:{ all -> 0x0317 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0317 }
            r14.addView(r1)     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.nn r14 = r13.k     // Catch:{ all -> 0x0317 }
            com.google.android.gms.internal.ads.ot r1 = r13.m     // Catch:{ all -> 0x0317 }
            r14.zza(r1)     // Catch:{ all -> 0x0317 }
        L_0x0307:
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x0309:
            java.lang.String r14 = "Webview is detached, probably in the middle of a resize or expand."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x0310:
            java.lang.String r14 = "Activity context is not ready, cannot get window or decor view."
            r13.a(r14)     // Catch:{ all -> 0x0317 }
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            return
        L_0x0317:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0317 }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.c.a(java.util.Map):void");
    }

    public final void a(boolean z) {
        synchronized (this.j) {
            if (this.q != null) {
                this.q.dismiss();
                this.r.removeView(this.k.getView());
                if (this.s != null) {
                    this.s.removeView(this.n);
                    this.s.addView(this.k.getView());
                    this.k.zza(this.m);
                }
                if (z) {
                    c("default");
                    if (this.p != null) {
                        this.p.zzcq();
                    }
                }
                this.q = null;
                this.r = null;
                this.s = null;
                this.o = null;
            }
        }
    }

    public final boolean a() {
        boolean z;
        synchronized (this.j) {
            z = this.q != null;
        }
        return z;
    }
}
