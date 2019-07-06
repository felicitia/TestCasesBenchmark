package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.DeviceProperties;

final class ci implements ck {
    private final /* synthetic */ Context a;

    ci(Context context) {
        this.a = context;
    }

    public final boolean a(zzang zzang) {
        ajh.a();
        boolean c = jp.c(this.a);
        boolean z = ((Boolean) ajh.f().a(akl.dd)).booleanValue() && zzang.zzcvg;
        if (!ch.b(this.a, zzang.zzcvg) || !c || z) {
            return true;
        }
        if (DeviceProperties.isSidewinder(this.a)) {
            if (!((Boolean) ajh.f().a(akl.H)).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
