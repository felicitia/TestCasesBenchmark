package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager.BadTokenException;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@bu
public final class hx {
    private final Context a;
    private String b;
    private String c;
    private String d;
    @Nullable
    private String e;
    private final float f;
    private float g;
    private float h;
    private float i;
    private int j;
    private int k;
    private float l;
    private float m;
    private float n;
    private float o;
    private Handler p;
    private Runnable q;

    public hx(Context context) {
        this.j = 0;
        this.q = new hy(this);
        this.a = context;
        this.f = context.getResources().getDisplayMetrics().density;
        this.k = ViewConfiguration.get(this.a).getScaledTouchSlop();
        ao.t().a();
        this.p = ao.t().b();
    }

    public hx(Context context, String str) {
        this(context);
        this.b = str;
    }

    private static int a(List<String> list, String str, boolean z) {
        if (!z) {
            return -1;
        }
        list.add(str);
        return list.size() - 1;
    }

    @VisibleForTesting
    private final void a(int i2, float f2, float f3) {
        if (i2 == 0) {
            this.j = 0;
            this.g = f2;
            this.h = f3;
            this.i = f3;
        } else if (this.j != -1) {
            if (i2 == 2) {
                if (f3 > this.h) {
                    this.h = f3;
                } else if (f3 < this.i) {
                    this.i = f3;
                }
                if (this.h - this.i > 30.0f * this.f) {
                    this.j = -1;
                    return;
                }
                if (this.j == 0 || this.j == 2 ? f2 - this.g >= 50.0f * this.f : !(!(this.j == 1 || this.j == 3) || f2 - this.g > -50.0f * this.f)) {
                    this.g = f2;
                    this.j++;
                }
                if (this.j == 1 || this.j == 3) {
                    if (f2 > this.g) {
                        this.g = f2;
                    }
                } else if (this.j == 2 && f2 < this.g) {
                    this.g = f2;
                }
            } else if (i2 == 1 && this.j == 4) {
                a();
            }
        }
    }

    private final boolean a(float f2, float f3, float f4, float f5) {
        return Math.abs(this.l - f2) < ((float) this.k) && Math.abs(this.m - f3) < ((float) this.k) && Math.abs(this.n - f4) < ((float) this.k) && Math.abs(this.o - f5) < ((float) this.k);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006c, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0071;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void e() {
        /*
            r5 = this;
            android.content.Context r0 = r5.a
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = "Can not create dialog without Activity Context"
            com.google.android.gms.internal.ads.gv.d(r0)
            return
        L_0x000c:
            java.lang.String r0 = r5.b
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x006f
            java.lang.String r1 = "\\+"
            java.lang.String r2 = "%20"
            java.lang.String r0 = r0.replaceAll(r1, r2)
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            android.net.Uri$Builder r0 = r1.encodedQuery(r0)
            android.net.Uri r0 = r0.build()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.google.android.gms.ads.internal.ao.e()
            java.util.Map r0 = com.google.android.gms.internal.ads.hd.a(r0)
            java.util.Set r2 = r0.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x003d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0060
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r1.append(r3)
            java.lang.String r4 = " = "
            r1.append(r4)
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            r1.append(r3)
            java.lang.String r3 = "\n\n"
            r1.append(r3)
            goto L_0x003d
        L_0x0060:
            java.lang.String r0 = r1.toString()
            java.lang.String r0 = r0.trim()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            java.lang.String r0 = "No debug information"
        L_0x0071:
            android.app.AlertDialog$Builder r1 = new android.app.AlertDialog$Builder
            android.content.Context r2 = r5.a
            r1.<init>(r2)
            r1.setMessage(r0)
            java.lang.String r2 = "Ad Information"
            r1.setTitle(r2)
            java.lang.String r2 = "Share"
            com.google.android.gms.internal.ads.ia r3 = new com.google.android.gms.internal.ads.ia
            r3.<init>(r5, r0)
            r1.setPositiveButton(r2, r3)
            java.lang.String r0 = "Close"
            android.content.DialogInterface$OnClickListener r2 = com.google.android.gms.internal.ads.ib.a
            r1.setNegativeButton(r0, r2)
            android.app.AlertDialog r0 = r1.create()
            r0.show()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.hx.e():void");
    }

    public final void a() {
        try {
            if (!((Boolean) ajh.f().a(akl.cP)).booleanValue()) {
                if (!((Boolean) ajh.f().a(akl.cO)).booleanValue()) {
                    e();
                    return;
                }
            }
            if (!(this.a instanceof Activity)) {
                gv.d("Can not create dialog without Activity Context");
                return;
            }
            String str = !TextUtils.isEmpty(ao.o().a()) ? "Creative Preview (Enabled)" : "Creative Preview";
            String str2 = ao.o().b() ? "Troubleshooting (Enabled)" : "Troubleshooting";
            ArrayList arrayList = new ArrayList();
            int a2 = a((List<String>) arrayList, "Ad Information", true);
            int a3 = a((List<String>) arrayList, str, ((Boolean) ajh.f().a(akl.cO)).booleanValue());
            int a4 = a((List<String>) arrayList, str2, ((Boolean) ajh.f().a(akl.cP)).booleanValue());
            Builder builder = new Builder(this.a, ao.g().f());
            builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new hz(this, a2, a3, a4));
            builder.create().show();
        } catch (BadTokenException e2) {
            String str3 = "";
            if (gv.a()) {
                Log.v("Ads", str3, e2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(int i2, int i3, int i4, DialogInterface dialogInterface, int i5) {
        if (i5 == i2) {
            e();
            return;
        }
        if (i5 == i3) {
            if (((Boolean) ajh.f().a(akl.cO)).booleanValue()) {
                gv.b("Debug mode [Creative Preview] selected.");
                hb.a((Runnable) new ic(this));
                return;
            }
        }
        if (i5 == i4) {
            if (((Boolean) ajh.f().a(akl.cP)).booleanValue()) {
                gv.b("Debug mode [Troubleshooting] selected.");
                hb.a((Runnable) new id(this));
            }
        }
    }

    public final void a(MotionEvent motionEvent) {
        if (((Boolean) ajh.f().a(akl.cQ)).booleanValue()) {
            int actionMasked = motionEvent.getActionMasked();
            int historySize = motionEvent.getHistorySize();
            int pointerCount = motionEvent.getPointerCount();
            if (actionMasked == 0) {
                this.j = 0;
                this.l = motionEvent.getX();
                this.m = motionEvent.getY();
                return;
            }
            if (this.j != -1) {
                boolean z = true;
                if (this.j == 0 && actionMasked == 5) {
                    this.j = 5;
                    this.n = motionEvent.getX(1);
                    this.o = motionEvent.getY(1);
                    this.p.postDelayed(this.q, ((Long) ajh.f().a(akl.cR)).longValue());
                    return;
                } else if (this.j == 5) {
                    if (pointerCount == 2) {
                        if (actionMasked == 2) {
                            boolean z2 = false;
                            for (int i2 = 0; i2 < historySize; i2++) {
                                if (!a(motionEvent.getHistoricalX(0, i2), motionEvent.getHistoricalY(0, i2), motionEvent.getHistoricalX(1, i2), motionEvent.getHistoricalY(1, i2))) {
                                    z2 = true;
                                }
                            }
                            if (a(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                                z = z2;
                            }
                        } else {
                            z = false;
                        }
                    }
                    if (z) {
                        this.j = -1;
                        this.p.removeCallbacks(this.q);
                    }
                }
            }
            return;
        }
        int historySize2 = motionEvent.getHistorySize();
        for (int i3 = 0; i3 < historySize2; i3++) {
            a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i3), motionEvent.getHistoricalY(0, i3));
        }
        a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    public final void a(String str) {
        this.c = str;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(String str, DialogInterface dialogInterface, int i2) {
        ao.e();
        hd.a(this.a, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b() {
        ao.o().a(this.a, this.c, this.d, this.e);
    }

    public final void b(String str) {
        this.d = str;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c() {
        ao.o().a(this.a, this.c, this.d);
    }

    public final void c(String str) {
        this.b = str;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d() {
        this.j = 4;
        a();
    }

    public final void d(String str) {
        this.e = str;
    }
}
