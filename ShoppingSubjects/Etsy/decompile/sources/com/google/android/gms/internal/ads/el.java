package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.dynamic.RemoteCreator;

@bu
public final class el extends RemoteCreator<zzahc> {
    public el() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        return queryLocalInterface instanceof zzahc ? (zzahc) queryLocalInterface : new zzahd(iBinder);
    }
}
