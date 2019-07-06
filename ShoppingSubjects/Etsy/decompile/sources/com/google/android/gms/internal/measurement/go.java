package com.google.android.gms.internal.measurement;

import android.util.Log;

final class go extends gi<Boolean> {
    go(gs gsVar, String str, Boolean bool) {
        super(gsVar, str, bool, null);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object a(String str) {
        if (gd.a.matcher(str).matches()) {
            return Boolean.valueOf(true);
        }
        if (gd.b.matcher(str).matches()) {
            return Boolean.valueOf(false);
        }
        String str2 = this.a;
        StringBuilder sb = new StringBuilder(28 + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append("Invalid boolean value for ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
