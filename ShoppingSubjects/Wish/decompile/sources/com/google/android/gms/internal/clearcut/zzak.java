package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;

final class zzak extends zzae<String> {
    zzak(zzao zzao, String str, String str2) {
        super(zzao, str, str2, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zza(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zzds, null);
        } catch (ClassCastException e) {
            String str = "PhenotypeFlag";
            String str2 = "Invalid string value in SharedPreferences for ";
            String valueOf = String.valueOf(this.zzds);
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzb(String str) {
        return str;
    }
}
