package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.f;
import java.util.Collections;
import java.util.Map;

final class zzy implements c<Map<zzh<?>, String>> {
    private final /* synthetic */ zzw zzgu;

    private zzy(zzw zzw) {
        this.zzgu = zzw;
    }

    public final void onComplete(@NonNull f<Map<zzh<?>, String>> fVar) {
        zzw zzw;
        ConnectionResult connectionResult;
        Map zzd;
        this.zzgu.zzga.lock();
        try {
            if (this.zzgu.zzgp) {
                if (fVar.b()) {
                    this.zzgu.zzgq = new ArrayMap(this.zzgu.zzgg.size());
                    for (zzv zzm : this.zzgu.zzgg.values()) {
                        this.zzgu.zzgq.put(zzm.zzm(), ConnectionResult.RESULT_SUCCESS);
                    }
                } else {
                    if (fVar.e() instanceof AvailabilityException) {
                        AvailabilityException availabilityException = (AvailabilityException) fVar.e();
                        if (this.zzgu.zzgn) {
                            this.zzgu.zzgq = new ArrayMap(this.zzgu.zzgg.size());
                            for (zzv zzv : this.zzgu.zzgg.values()) {
                                zzh zzm2 = zzv.zzm();
                                ConnectionResult connectionResult2 = availabilityException.getConnectionResult(zzv);
                                if (this.zzgu.zza(zzv, connectionResult2)) {
                                    zzd = this.zzgu.zzgq;
                                    connectionResult2 = new ConnectionResult(16);
                                } else {
                                    zzd = this.zzgu.zzgq;
                                }
                                zzd.put(zzm2, connectionResult2);
                            }
                        } else {
                            this.zzgu.zzgq = availabilityException.zzl();
                        }
                        zzw = this.zzgu;
                        connectionResult = this.zzgu.zzai();
                    } else {
                        Log.e("ConnectionlessGAC", "Unexpected availability exception", fVar.e());
                        this.zzgu.zzgq = Collections.emptyMap();
                        zzw = this.zzgu;
                        connectionResult = new ConnectionResult(8);
                    }
                    zzw.zzgt = connectionResult;
                }
                if (this.zzgu.zzgr != null) {
                    this.zzgu.zzgq.putAll(this.zzgu.zzgr);
                    this.zzgu.zzgt = this.zzgu.zzai();
                }
                if (this.zzgu.zzgt == null) {
                    this.zzgu.zzag();
                    this.zzgu.zzah();
                } else {
                    this.zzgu.zzgp = false;
                    this.zzgu.zzgj.zzc(this.zzgu.zzgt);
                }
                this.zzgu.zzgl.signalAll();
            }
        } finally {
            this.zzgu.zzga.unlock();
        }
    }
}
