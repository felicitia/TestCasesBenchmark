package com.threatmetrix.TrustDefender.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.threatmetrix.TrustDefender.internal.P.O;

class OE {

    /* renamed from: int reason: not valid java name */
    private static final String f473int = TL.m331if(OE.class);

    OE() {
    }

    /* renamed from: int reason: not valid java name */
    static DS m231int(O o, String str) {
        long j;
        int i = 0;
        if (!(A.f358for && A.f359if)) {
            return null;
        }
        SharedPreferences sharedPreferences = o.f487for.getSharedPreferences(str, 0);
        try {
            DS ds = new DS();
            String str2 = "enableOptions";
            long j2 = 0;
            if (!A.f358for || !A.f360int) {
                j = 0;
            } else {
                j = sharedPreferences.getLong(str2, 0);
            }
            ds.f145new = j;
            String str3 = "disableOptions";
            if (A.f358for && A.f360int) {
                j2 = sharedPreferences.getLong(str3, 0);
            }
            ds.f144if = j2;
            String str4 = "sdkVersion";
            String str5 = "";
            if (A.f358for && A.f361new) {
                str5 = sharedPreferences.getString(str4, str5);
            }
            ds.f142do = str5;
            String str6 = "quietPeriod";
            if (A.f358for && A.f356do) {
                i = sharedPreferences.getInt(str6, 0);
            }
            ds.f143for = i;
            return ds;
        } catch (ClassCastException e) {
            TL.m328for(f473int, "Found preference of different type", (Throwable) e);
            return null;
        }
    }

    /* renamed from: int reason: not valid java name */
    static void m232int(O o, String str, DS ds) throws InterruptedException {
        Editor editor;
        if (A.f358for && A.f359if) {
            SharedPreferences sharedPreferences = o.f487for.getSharedPreferences(str, 0);
            if (!A.f358for || !A.f359if || !A.f355char) {
                editor = null;
            } else {
                editor = sharedPreferences.edit();
            }
            if (editor != null) {
                String str2 = "enableOptions";
                long j = ds.f145new;
                if (A.f354case) {
                    editor.putLong(str2, j);
                }
                String str3 = "disableOptions";
                long j2 = ds.f144if;
                if (A.f354case) {
                    editor.putLong(str3, j2);
                }
                String str4 = "sdkVersion";
                String str5 = "5.2-34";
                if (A.f362try) {
                    editor.putString(str4, str5);
                }
                String str6 = "quietPeriod";
                int i = ds.f143for;
                if (A.f357else) {
                    editor.putInt(str6, i);
                }
                editor.apply();
            }
        }
    }
}
