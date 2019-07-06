package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fl;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.n;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.nt;
import com.google.android.gms.internal.ads.ot;
import com.google.android.gms.internal.ads.y;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzxn;

@bu
public abstract class zzi extends zzd implements f, n {
    private boolean zzwl;

    public zzi(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        super(context, zzjn, str, zzxn, zzang, bgVar);
    }

    /* access modifiers changed from: protected */
    public nn zza(gb gbVar, @Nullable bh bhVar, @Nullable fl flVar) throws zzarg {
        gb gbVar2 = gbVar;
        View nextView = this.zzvw.zzacs.getNextView();
        if (nextView instanceof nn) {
            ((nn) nextView).destroy();
        }
        if (nextView != null) {
            this.zzvw.zzacs.removeView(nextView);
        }
        ao.f();
        nn a = nt.a(this.zzvw.zzrt, ot.a(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, gbVar2.i);
        if (this.zzvw.zzacv.zzard == null) {
            zzg(a.getView());
        }
        a.zzuf().zza(this, this, this, this, this, false, null, bhVar, this, flVar);
        zza(a);
        a.zzdr(gbVar2.a.zzcdi);
        return a;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzbp();
    }

    /* access modifiers changed from: protected */
    public void zza(gb gbVar, aky aky) {
        if (gbVar.e != -2) {
            hd.a.post(new av(this, gbVar));
            return;
        }
        if (gbVar.d != null) {
            this.zzvw.zzacv = gbVar.d;
        }
        if (!gbVar.b.zzceq || gbVar.b.zzarg) {
            hd.a.post(new aw(this, gbVar, this.zzwc.c.a(this.zzvw.zzrt, this.zzvw.zzacr, gbVar.b), aky));
            return;
        }
        this.zzvw.zzadv = 0;
        zzbw zzbw = this.zzvw;
        ao.d();
        zzbw.zzacu = y.a(this.zzvw.zzrt, this, gbVar, this.zzvw.zzacq, null, this.zzwh, this, aky);
    }

    /* access modifiers changed from: protected */
    public final void zza(nn nnVar) {
        nnVar.zza("/trackActiveViewUnit", (ae<? super nn>) new au<Object>(this));
    }

    public final void zza(zzod zzod) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzvw.zzado = zzod;
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable ga gaVar, ga gaVar2) {
        if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().c(gaVar2.A);
        }
        try {
            if (gaVar2.b != null && !gaVar2.n && gaVar2.M) {
                if (((Boolean) ajh.f().a(akl.dl)).booleanValue() && !gaVar2.a.extras.containsKey("sdk_less_server_data")) {
                    try {
                        gaVar2.b.zzus();
                    } catch (Throwable unused) {
                        gv.a("Could not render test Ad label.");
                    }
                }
            }
        } catch (RuntimeException unused2) {
            gv.a("Could not render test AdLabel.");
        }
        return super.zza(gaVar, gaVar2);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzb(nn nnVar) {
        if (this.zzvw.zzacw != null) {
            this.zzvy.a(this.zzvw.zzacv, this.zzvw.zzacw, nnVar.getView(), nnVar);
            this.zzwl = false;
            return;
        }
        this.zzwl = true;
        gv.e("Request to enable ActiveView before adState is available.");
    }

    /* access modifiers changed from: protected */
    public void zzbq() {
        super.zzbq();
        if (this.zzwl) {
            if (((Boolean) ajh.f().a(akl.cg)).booleanValue()) {
                zzb(this.zzvw.zzacw.b);
            }
        }
    }

    public final void zzcn() {
        onAdClicked();
    }

    public final void zzco() {
        recordImpression();
        zzbm();
    }

    /* access modifiers changed from: protected */
    public final boolean zzcp() {
        return (this.zzvw.zzacx == null || this.zzvw.zzacx.b == null || !this.zzvw.zzacx.b.zzcfp) ? false : true;
    }

    public final void zzcq() {
        zzbn();
    }

    public final void zzh(View view) {
        this.zzvw.zzadu = view;
        ga gaVar = new ga(this.zzvw.zzacx, null, null, null, null, null, null, null);
        zzb(gaVar);
    }
}
