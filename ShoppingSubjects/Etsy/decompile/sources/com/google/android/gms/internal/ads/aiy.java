package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.ads.d;
import com.google.android.gms.ads.h.a;

@bu
public final class aiy {
    private final d[] a;
    private final String b;

    public aiy(Context context, AttributeSet attributeSet) {
        d[] a2;
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, a.AdsAttrs);
        String string = obtainAttributes.getString(a.AdsAttrs_adSize);
        String string2 = obtainAttributes.getString(a.AdsAttrs_adSizes);
        boolean z = !TextUtils.isEmpty(string);
        boolean z2 = !TextUtils.isEmpty(string2);
        if (z && !z2) {
            a2 = a(string);
        } else if (!z && z2) {
            a2 = a(string2);
        } else if (z) {
            throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
        } else {
            throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
        }
        this.a = a2;
        this.b = obtainAttributes.getString(a.AdsAttrs_adUnitId);
        if (TextUtils.isEmpty(this.b)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }

    private static d[] a(String str) {
        String[] split = str.split("\\s*,\\s*");
        d[] dVarArr = new d[split.length];
        for (int i = 0; i < split.length; i++) {
            String trim = split[i].trim();
            if (trim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                String[] split2 = trim.split("[xX]");
                split2[0] = split2[0].trim();
                split2[1] = split2[1].trim();
                try {
                    dVarArr[i] = new d("FULL_WIDTH".equals(split2[0]) ? -1 : Integer.parseInt(split2[0]), "AUTO_HEIGHT".equals(split2[1]) ? -2 : Integer.parseInt(split2[1]));
                } catch (NumberFormatException unused) {
                    String str2 = "Could not parse XML attribute \"adSize\": ";
                    String valueOf = String.valueOf(trim);
                    throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            } else if ("BANNER".equals(trim)) {
                dVarArr[i] = d.a;
            } else if ("LARGE_BANNER".equals(trim)) {
                dVarArr[i] = d.c;
            } else if ("FULL_BANNER".equals(trim)) {
                dVarArr[i] = d.b;
            } else if ("LEADERBOARD".equals(trim)) {
                dVarArr[i] = d.d;
            } else if ("MEDIUM_RECTANGLE".equals(trim)) {
                dVarArr[i] = d.e;
            } else if ("SMART_BANNER".equals(trim)) {
                dVarArr[i] = d.g;
            } else if ("WIDE_SKYSCRAPER".equals(trim)) {
                dVarArr[i] = d.f;
            } else if ("FLUID".equals(trim)) {
                dVarArr[i] = d.h;
            } else if ("ICON".equals(trim)) {
                dVarArr[i] = d.i;
            } else {
                String str3 = "Could not parse XML attribute \"adSize\": ";
                String valueOf2 = String.valueOf(trim);
                throw new IllegalArgumentException(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            }
        }
        if (dVarArr.length != 0) {
            return dVarArr;
        }
        String str4 = "Could not parse XML attribute \"adSize\": ";
        String valueOf3 = String.valueOf(str);
        throw new IllegalArgumentException(valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4));
    }

    public final String a() {
        return this.b;
    }

    public final d[] a(boolean z) {
        if (z || this.a.length == 1) {
            return this.a;
        }
        throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
    }
}
