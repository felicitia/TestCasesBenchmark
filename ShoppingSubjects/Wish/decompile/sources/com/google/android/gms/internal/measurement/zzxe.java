package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzxe extends zzwx<Double> {
    zzxe(zzxh zzxh, String str, Double d) {
        super(zzxh, str, d, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzfd */
    public final Double zzfa(String str) {
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            String str2 = this.zzbpk;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 27 + String.valueOf(str).length());
            sb.append("Invalid double value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
