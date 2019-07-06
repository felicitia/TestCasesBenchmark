package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.fl;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.y;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzny;

final class aw implements Runnable {
    final /* synthetic */ gb a;
    final /* synthetic */ fl b;
    final /* synthetic */ zzi c;
    private final /* synthetic */ aky d;

    aw(zzi zzi, gb gbVar, fl flVar, aky aky) {
        this.c = zzi;
        this.a = gbVar;
        this.b = flVar;
        this.d = aky;
    }

    public final void run() {
        if (this.a.b.zzcez && this.c.zzvw.zzado != null) {
            String str = null;
            if (this.a.b.zzbyq != null) {
                ao.e();
                str = hd.a(this.a.b.zzbyq);
            }
            zzny zzny = new zzny(this.c, str, this.a.b.zzceo);
            this.c.zzvw.zzadv = 1;
            try {
                this.c.zzvu = false;
                this.c.zzvw.zzado.zza(zzny);
                return;
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
                this.c.zzvu = true;
            }
        }
        bh bhVar = new bh(this.c.zzvw.zzrt, this.b, this.a.b.zzcfi);
        try {
            nn zza = this.c.zza(this.a, bhVar, this.b);
            zza.setOnTouchListener(new ay(this, bhVar));
            zza.setOnClickListener(new az(this, bhVar));
            this.c.zzvw.zzadv = 0;
            zzbw zzbw = this.c.zzvw;
            ao.d();
            zzbw.zzacu = y.a(this.c.zzvw.zzrt, this.c, this.a, this.c.zzvw.zzacq, zza, this.c.zzwh, this.c, this.d);
        } catch (zzarg e2) {
            gv.b("Could not obtain webview.", e2);
            hd.a.post(new ax(this));
        }
    }
}
