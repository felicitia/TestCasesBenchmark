package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;

@bu
public final class mh extends mb {
    @Nullable
    public final zzapg a(Context context, mo moVar, int i, boolean z, aky aky, mn mnVar) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (!(PlatformVersion.isAtLeastIceCreamSandwich() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11))) {
            return null;
        }
        boolean d = moVar.zzud().d();
        mp mpVar = new mp(context, moVar.zztq(), moVar.zzol(), aky, moVar.zztn());
        zzaov zzaov = new zzaov(context, z, d, mnVar, mpVar);
        return zzaov;
    }
}
