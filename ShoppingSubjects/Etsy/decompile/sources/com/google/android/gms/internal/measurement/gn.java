package com.google.android.gms.internal.measurement;

import android.util.Log;

final class gn extends gi<Integer> {
    gn(gs gsVar, String str, Integer num) {
        super(gsVar, str, num, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final Integer a(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            String str2 = this.a;
            StringBuilder sb = new StringBuilder(28 + String.valueOf(str2).length() + String.valueOf(str).length());
            sb.append("Invalid integer value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
