package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.UUID;

public final class zzae extends zzi<zzae> {
    private String zzuk;
    private int zzul;
    private int zzum;
    private String zzun;
    private String zzuo;
    private boolean zzup;
    private boolean zzuq;

    public zzae() {
        this(false);
    }

    private zzae(boolean z) {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits == 0) {
            leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
            if (leastSignificantBits == 0) {
                Log.e("GAv4", "UUID.randomUUID() returned 0.");
                leastSignificantBits = Integer.MAX_VALUE;
            }
        }
        this(false, leastSignificantBits);
    }

    private zzae(boolean z, int i) {
        Preconditions.checkNotZero(i);
        this.zzul = i;
        this.zzuq = false;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzuk);
        hashMap.put("interstitial", Boolean.valueOf(this.zzup));
        hashMap.put("automatic", Boolean.valueOf(this.zzuq));
        hashMap.put("screenId", Integer.valueOf(this.zzul));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzum));
        hashMap.put("referrerScreenName", this.zzun);
        hashMap.put("referrerUri", this.zzuo);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzae zzae = (zzae) zzi;
        if (!TextUtils.isEmpty(this.zzuk)) {
            zzae.zzuk = this.zzuk;
        }
        if (this.zzul != 0) {
            zzae.zzul = this.zzul;
        }
        if (this.zzum != 0) {
            zzae.zzum = this.zzum;
        }
        if (!TextUtils.isEmpty(this.zzun)) {
            zzae.zzun = this.zzun;
        }
        if (!TextUtils.isEmpty(this.zzuo)) {
            String str = this.zzuo;
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            zzae.zzuo = str;
        }
        if (this.zzup) {
            zzae.zzup = this.zzup;
        }
        if (this.zzuq) {
            zzae.zzuq = this.zzuq;
        }
    }

    public final String zzbg() {
        return this.zzuk;
    }

    public final int zzbh() {
        return this.zzul;
    }

    public final String zzbi() {
        return this.zzuo;
    }
}
