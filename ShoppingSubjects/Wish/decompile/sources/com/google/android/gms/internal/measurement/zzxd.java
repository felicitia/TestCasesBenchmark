package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzxd extends zzwx<Boolean> {
    zzxd(zzxh zzxh, String str, Boolean bool) {
        super(zzxh, str, bool, null);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzfa(String str) {
        if (zzws.zzbom.matcher(str).matches()) {
            return Boolean.valueOf(true);
        }
        if (zzws.zzbon.matcher(str).matches()) {
            return Boolean.valueOf(false);
        }
        String str2 = this.zzbpk;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 28 + String.valueOf(str).length());
        sb.append("Invalid boolean value for ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
