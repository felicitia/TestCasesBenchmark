package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.aln;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import java.util.List;

final class z implements Runnable {
    private final /* synthetic */ aln a;
    private final /* synthetic */ int b;
    private final /* synthetic */ List c;
    private final /* synthetic */ zzbc d;

    z(zzbc zzbc, aln aln, int i, List list) {
        this.d = zzbc;
        this.a = aln;
        this.b = i;
        this.c = list;
    }

    public final void run() {
        try {
            boolean z = false;
            if ((this.a instanceof zzoq) && this.d.zzvw.zzadg != null) {
                zzbc zzbc = this.d;
                if (this.b != this.c.size() - 1) {
                    z = true;
                }
                zzbc.zzvu = z;
                zzov zzb = zzbc.zza(this.a);
                this.d.zzvw.zzadg.zza(zzb);
                this.d.zzb(zzb.zzka());
            } else if ((this.a instanceof zzoq) && this.d.zzvw.zzadf != null) {
                zzbc zzbc2 = this.d;
                if (this.b != this.c.size() - 1) {
                    z = true;
                }
                zzbc2.zzvu = z;
                zzoq zzoq = (zzoq) this.a;
                this.d.zzvw.zzadf.zza(zzoq);
                this.d.zzb(zzoq.zzka());
            } else if ((this.a instanceof zzoo) && this.d.zzvw.zzadg != null) {
                zzbc zzbc3 = this.d;
                if (this.b != this.c.size() - 1) {
                    z = true;
                }
                zzbc3.zzvu = z;
                zzov zzb2 = zzbc.zza(this.a);
                this.d.zzvw.zzadg.zza(zzb2);
                this.d.zzb(zzb2.zzka());
            } else if (!(this.a instanceof zzoo) || this.d.zzvw.zzade == null) {
                zzbc zzbc4 = this.d;
                if (this.b != this.c.size() - 1) {
                    z = true;
                }
                zzbc4.zzc(3, z);
            } else {
                zzbc zzbc5 = this.d;
                if (this.b != this.c.size() - 1) {
                    z = true;
                }
                zzbc5.zzvu = z;
                zzoo zzoo = (zzoo) this.a;
                this.d.zzvw.zzade.zza(zzoo);
                this.d.zzb(zzoo.zzka());
            }
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
