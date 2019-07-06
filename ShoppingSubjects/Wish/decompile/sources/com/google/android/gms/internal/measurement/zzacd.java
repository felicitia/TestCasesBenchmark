package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzacd;
import java.io.IOException;

public abstract class zzacd<M extends zzacd<M>> extends zzacj {
    protected zzacf zzbzd;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzacd zzacd = (zzacd) super.clone();
        zzach.zza(this, zzacd);
        return zzacd;
    }

    /* access modifiers changed from: protected */
    public int zza() {
        if (this.zzbzd == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzbzd.size(); i2++) {
            i += this.zzbzd.zzau(i2).zza();
        }
        return i;
    }

    public void zza(zzacb zzacb) throws IOException {
        if (this.zzbzd != null) {
            for (int i = 0; i < this.zzbzd.size(); i++) {
                this.zzbzd.zzau(i).zza(zzacb);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzaca zzaca, int i) throws IOException {
        int position = zzaca.getPosition();
        if (!zzaca.zzak(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzacl zzacl = new zzacl(i, zzaca.zzc(position, zzaca.getPosition() - position));
        zzacg zzacg = null;
        if (this.zzbzd == null) {
            this.zzbzd = new zzacf();
        } else {
            zzacg = this.zzbzd.zzat(i2);
        }
        if (zzacg == null) {
            zzacg = new zzacg();
            this.zzbzd.zza(i2, zzacg);
        }
        zzacg.zza(zzacl);
        return true;
    }

    public final /* synthetic */ zzacj zzvu() throws CloneNotSupportedException {
        return (zzacd) clone();
    }
}
