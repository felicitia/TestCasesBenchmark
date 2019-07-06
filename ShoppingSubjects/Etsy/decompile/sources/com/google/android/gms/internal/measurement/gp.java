package com.google.android.gms.internal.measurement;

import android.util.Log;

final class gp extends gi<Double> {
    gp(gs gsVar, String str, Double d) {
        super(gsVar, str, d, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final Double a(String str) {
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            String str2 = this.a;
            StringBuilder sb = new StringBuilder(27 + String.valueOf(str2).length() + String.valueOf(str).length());
            sb.append("Invalid double value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
