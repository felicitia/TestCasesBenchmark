package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.f;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@bu
public final class zzny extends zzob {
    private final f zzbgs;
    @Nullable
    private final String zzbgt;
    private final String zzbgu;

    public zzny(f fVar, @Nullable String str, String str2) {
        this.zzbgs = fVar;
        this.zzbgt = str;
        this.zzbgu = str2;
    }

    public final String getContent() {
        return this.zzbgu;
    }

    public final void recordClick() {
        this.zzbgs.zzcn();
    }

    public final void recordImpression() {
        this.zzbgs.zzco();
    }

    public final void zzg(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.zzbgs.zzh((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public final String zzjn() {
        return this.zzbgt;
    }
}
