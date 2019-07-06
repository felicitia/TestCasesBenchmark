package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ba;
import java.util.Arrays;

@bu
final class any {
    private final Object[] a;

    any(zzjj zzjj, String str, int i) {
        this.a = ba.a((String) ajh.f().a(akl.aV), zzjj, str, i, null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof any)) {
            return false;
        }
        return Arrays.equals(this.a, ((any) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public final String toString() {
        String arrays = Arrays.toString(this.a);
        StringBuilder sb = new StringBuilder(24 + String.valueOf(arrays).length());
        sb.append("[InterstitialAdPoolKey ");
        sb.append(arrays);
        sb.append("]");
        return sb.toString();
    }
}
