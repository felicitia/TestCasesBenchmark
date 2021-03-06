package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzah;
import com.google.android.gms.internal.measurement.zzat;
import java.util.ListIterator;

public class zza extends zzj<zza> {
    private final zzat zzqm;
    private boolean zzqn;

    public zza(zzat zzat) {
        super(zzat.zzbw(), zzat.zzbt());
        this.zzqm = zzat;
    }

    public final void enableAdvertisingIdCollection(boolean z) {
        this.zzqn = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzg zzg) {
        zzad zzad = (zzad) zzg.zzb(zzad.class);
        if (TextUtils.isEmpty(zzad.zzaz())) {
            zzad.setClientId(this.zzqm.zzcn().zzdn());
        }
        if (this.zzqn && TextUtils.isEmpty(zzad.zzbb())) {
            zzah zzcm = this.zzqm.zzcm();
            zzad.zzm(zzcm.zzbj());
            zzad.zza(zzcm.zzbc());
        }
    }

    public final void zza(String str) {
        Preconditions.checkNotEmpty(str);
        Uri zzb = zzb.zzb(str);
        ListIterator listIterator = this.zzry.zzq().listIterator();
        while (listIterator.hasNext()) {
            if (zzb.equals(((zzo) listIterator.next()).zzk())) {
                listIterator.remove();
            }
        }
        this.zzry.zzq().add(new zzb(this.zzqm, str));
    }

    /* access modifiers changed from: 0000 */
    public final zzat zzh() {
        return this.zzqm;
    }

    public final zzg zzi() {
        zzg zzo = this.zzry.zzo();
        zzo.zza((zzi) this.zzqm.zzce().zzdb());
        zzo.zza((zzi) this.zzqm.zzcf().zzeg());
        zzd(zzo);
        return zzo;
    }
}
