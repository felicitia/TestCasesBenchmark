package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.a;

@bu
public final class ek implements a {
    private final zzagu a;

    public ek(zzagu zzagu) {
        this.a = zzagu;
    }

    public final String a() {
        if (this.a == null) {
            return null;
        }
        try {
            return this.a.getType();
        } catch (RemoteException e) {
            ka.c("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    public final int b() {
        if (this.a == null) {
            return 0;
        }
        try {
            return this.a.getAmount();
        } catch (RemoteException e) {
            ka.c("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
