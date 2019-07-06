package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzga;
import java.io.IOException;

public abstract class zzga<M extends zzga<M>> extends zzgg {
    protected zzgc zzss;

    /* access modifiers changed from: protected */
    public int zzax() {
        if (this.zzss == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzss.size(); i2++) {
            i += this.zzss.zzbb(i2).zzax();
        }
        return i;
    }

    public void zza(zzfy zzfy) throws IOException {
        if (this.zzss != null) {
            for (int i = 0; i < this.zzss.size(); i++) {
                this.zzss.zzbb(i).zza(zzfy);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzfx zzfx, int i) throws IOException {
        int position = zzfx.getPosition();
        if (!zzfx.zzm(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzgi zzgi = new zzgi(i, zzfx.zzr(position, zzfx.getPosition() - position));
        zzgd zzgd = null;
        if (this.zzss == null) {
            this.zzss = new zzgc();
        } else {
            zzgd = this.zzss.zzba(i2);
        }
        if (zzgd == null) {
            zzgd = new zzgd();
            this.zzss.zza(i2, zzgd);
        }
        zzgd.zza(zzgi);
        return true;
    }

    public final /* synthetic */ zzgg zzgg() throws CloneNotSupportedException {
        return (zzga) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzga zzga = (zzga) super.clone();
        zzge.zza(this, zzga);
        return zzga;
    }
}
