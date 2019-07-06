package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import java.util.Map;
import java.util.TreeMap;

final class an {
    private final String a;
    private final Map<String, String> b = new TreeMap();
    private String c;
    private String d;

    public an(String str) {
        this.a = str;
    }

    public final String a() {
        return this.d;
    }

    public final void a(zzjj zzjj, zzang zzang) {
        this.c = zzjj.zzaqd.zzatn;
        Bundle bundle = zzjj.zzaqg != null ? zzjj.zzaqg.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle != null) {
            String str = (String) ajh.f().a(akl.cy);
            for (String str2 : bundle.keySet()) {
                if (str.equals(str2)) {
                    this.d = bundle.getString(str2);
                } else if (str2.startsWith("csa_")) {
                    this.b.put(str2.substring(4), bundle.getString(str2));
                }
            }
            this.b.put("SDKVersion", zzang.zzcw);
        }
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.a;
    }

    public final Map<String, String> d() {
        return this.b;
    }
}
