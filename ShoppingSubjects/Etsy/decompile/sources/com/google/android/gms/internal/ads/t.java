package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@bu
public abstract class t implements hw<Void>, op {
    protected final Context a;
    protected final nn b;
    protected zzaej c;
    private final z d;
    private final gb e;
    private Runnable f;
    private final Object g = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean h = new AtomicBoolean(true);

    protected t(Context context, gb gbVar, nn nnVar, z zVar) {
        this.a = context;
        this.e = gbVar;
        this.c = this.e.b;
        this.b = nnVar;
        this.d = zVar;
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public void a(int i) {
        ga gaVar;
        int i2 = i;
        if (i2 != -2) {
            this.c = new zzaej(i2, this.c.zzbsu);
        }
        this.b.zztz();
        z zVar = this.d;
        zzaef zzaef = this.e.a;
        zzjj zzjj = zzaef.zzccv;
        nn nnVar = this.b;
        List<String> list = this.c.zzbsn;
        List<String> list2 = this.c.zzbso;
        List<String> list3 = this.c.zzces;
        int i3 = this.c.orientation;
        long j = this.c.zzbsu;
        String str = zzaef.zzccy;
        boolean z = this.c.zzceq;
        long j2 = this.c.zzcer;
        zzjn zzjn = this.e.d;
        long j3 = j2;
        z zVar2 = zVar;
        long j4 = this.c.zzcep;
        long j5 = this.e.f;
        long j6 = this.c.zzceu;
        String str2 = this.c.zzcev;
        JSONObject jSONObject = this.e.h;
        zzaig zzaig = this.c.zzcfe;
        List<String> list4 = this.c.zzcff;
        List<String> list5 = this.c.zzcfg;
        boolean z2 = this.c.zzcfh;
        zzael zzael = this.c.zzcfi;
        List<String> list6 = this.c.zzbsr;
        String str3 = this.c.zzcfl;
        ahh ahh = this.e.i;
        boolean z3 = this.e.b.zzzl;
        boolean z4 = this.e.j;
        boolean z5 = this.e.b.zzcfp;
        List<String> list7 = this.c.zzbsp;
        String str4 = str2;
        ga gaVar2 = gaVar;
        z zVar3 = zVar2;
        long j7 = j3;
        long j8 = j4;
        long j9 = j5;
        long j10 = j6;
        ga gaVar3 = new ga(zzjj, nnVar, list, i2, list2, list3, i3, j, str, z, null, null, null, null, null, j7, zzjn, j8, j9, j10, str4, jSONObject, null, zzaig, list4, list5, z2, zzael, null, list6, str3, ahh, z3, z4, z5, list7, this.e.b.zzzm, this.e.b.zzcfq);
        zVar3.zzb(gaVar2);
    }

    public final void a(boolean z) {
        gv.b("WebView finished loading.");
        int i = 0;
        if (this.h.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            a(i);
            hd.a.removeCallbacks(this.f);
        }
    }

    public void b() {
        if (this.h.getAndSet(false)) {
            this.b.stopLoading();
            ao.g();
            hj.a(this.b);
            a(-1);
            hd.a.removeCallbacks(this.f);
        }
    }

    public final /* synthetic */ Object c() {
        Preconditions.checkMainThread("Webview render task needs to be called on UI thread.");
        this.f = new u(this);
        hd.a.postDelayed(this.f, ((Long) ajh.f().a(akl.bB)).longValue());
        a();
        return null;
    }
}
