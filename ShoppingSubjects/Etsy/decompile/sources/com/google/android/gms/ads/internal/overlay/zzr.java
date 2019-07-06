package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;

@bu
public final class zzr extends zzd {
    public zzr(Activity activity) {
        super(activity);
    }

    public final void onCreate(Bundle bundle) {
        gv.a("AdOverlayParcel is null or does not contain valid overlay type.");
        this.zzbxx = 3;
        this.mActivity.finish();
    }
}
