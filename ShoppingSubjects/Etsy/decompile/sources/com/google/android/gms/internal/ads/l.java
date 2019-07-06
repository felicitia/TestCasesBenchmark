package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

@bu
public final class l extends m implements ae<nn> {
    private final nn a;
    private final Context b;
    private final WindowManager c;
    private final ajw d;
    private DisplayMetrics e;
    private float f;
    private int g = -1;
    private int h = -1;
    private int i;
    private int j = -1;
    private int k = -1;
    private int l = -1;
    private int m = -1;

    public l(nn nnVar, Context context, ajw ajw) {
        super(nnVar);
        this.a = nnVar;
        this.b = context;
        this.d = ajw;
        this.c = (WindowManager) context.getSystemService("window");
    }

    public final void a(int i2, int i3) {
        int i4 = 0;
        if (this.b instanceof Activity) {
            i4 = ao.e().c((Activity) this.b)[0];
        }
        if (this.a.zzud() == null || !this.a.zzud().d()) {
            ajh.a();
            this.l = jp.b(this.b, this.a.getWidth());
            ajh.a();
            this.m = jp.b(this.b, this.a.getHeight());
        }
        b(i2, i3 - i4, this.l, this.m);
        this.a.zzuf().zzb(i2, i3);
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i2;
        this.e = new DisplayMetrics();
        Display defaultDisplay = this.c.getDefaultDisplay();
        defaultDisplay.getMetrics(this.e);
        this.f = this.e.density;
        this.i = defaultDisplay.getRotation();
        ajh.a();
        this.g = jp.b(this.e, this.e.widthPixels);
        ajh.a();
        this.h = jp.b(this.e, this.e.heightPixels);
        Activity zzto = this.a.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            this.j = this.g;
            i2 = this.h;
        } else {
            ao.e();
            int[] a2 = hd.a(zzto);
            ajh.a();
            this.j = jp.b(this.e, a2[0]);
            ajh.a();
            i2 = jp.b(this.e, a2[1]);
        }
        this.k = i2;
        if (this.a.zzud().d()) {
            this.l = this.g;
            this.m = this.h;
        } else {
            this.a.measure(0, 0);
        }
        a(this.g, this.h, this.j, this.k, this.f, this.i);
        this.a.zza("onDeviceFeaturesReceived", new i(new k().b(this.d.a()).a(this.d.b()).c(this.d.d()).d(this.d.c()).e(true)).a());
        int[] iArr = new int[2];
        this.a.getLocationOnScreen(iArr);
        ajh.a();
        int b2 = jp.b(this.b, iArr[0]);
        ajh.a();
        a(b2, jp.b(this.b, iArr[1]));
        if (gv.a(2)) {
            gv.d("Dispatching Ready Event.");
        }
        b(this.a.zztq().zzcw);
    }
}
