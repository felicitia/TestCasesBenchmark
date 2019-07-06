package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class alc implements ae<Object> {
    private final /* synthetic */ zzok a;

    alc(zzok zzok) {
        this.a = zzok;
    }

    public final void zza(Object obj, Map<String, String> map) {
        try {
            this.a.zzbhp = Long.valueOf(Long.parseLong((String) map.get("timestamp")));
        } catch (NumberFormatException unused) {
            gv.c("Failed to call parse unconfirmedClickTimestamp.");
        }
        this.a.zzbho = (String) map.get("id");
        String str = (String) map.get("asset_id");
        if (this.a.zzbhm == null) {
            gv.b("Received unconfirmed click but UnconfirmedClickListener is null.");
            return;
        }
        try {
            this.a.zzbhm.onUnconfirmedClickReceived(str);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }
}
