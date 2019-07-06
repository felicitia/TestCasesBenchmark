package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gq;
import com.google.android.gms.internal.ads.hd;

@bu
final class f extends gq {
    final /* synthetic */ zzd a;

    private f(zzd zzd) {
        this.a = zzd;
    }

    /* synthetic */ f(zzd zzd, c cVar) {
        this(zzd);
    }

    public final void a() {
        Bitmap a2 = ao.y().a(Integer.valueOf(this.a.zzbxn.zzbyw.zzzj));
        if (a2 != null) {
            hd.a.post(new g(this, ao.g().a(this.a.mActivity, a2, this.a.zzbxn.zzbyw.zzzh, this.a.zzbxn.zzbyw.zzzi)));
        }
    }

    public final void c_() {
    }
}
