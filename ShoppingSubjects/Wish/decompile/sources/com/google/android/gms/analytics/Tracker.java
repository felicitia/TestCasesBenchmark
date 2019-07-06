package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzar;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdd;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Tracker extends zzar {
    private boolean zzsl;
    private final Map<String, String> zzsm = new HashMap();
    private final Map<String, String> zzsn = new HashMap();
    /* access modifiers changed from: private */
    public final zzck zzso;
    /* access modifiers changed from: private */
    public final zza zzsp;

    class zza extends zzar {
        private long zztc = -1;
        private boolean zztd;

        protected zza(zzat zzat) {
            super(zzat);
        }

        /* access modifiers changed from: protected */
        public final void zzac() {
        }

        public final synchronized boolean zzad() {
            boolean z;
            z = this.zztd;
            this.zztd = false;
            return z;
        }
    }

    Tracker(zzat zzat, String str, zzck zzck) {
        super(zzat);
        if (str != null) {
            this.zzsm.put("&tid", str);
        }
        this.zzsm.put("useSecure", "1");
        this.zzsm.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzso = new zzck("tracking", zzbt());
        this.zzsp = new zza(zzat);
    }

    private static String zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        if (!(str.startsWith("&") && str.length() >= 2)) {
            return null;
        }
        return ((String) entry.getKey()).substring(1);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null) {
                    map2.put(zza2, (String) entry.getValue());
                }
            }
        }
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzbx().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzbx().isDryRunEnabled();
        HashMap hashMap = new HashMap();
        zza(this.zzsm, hashMap);
        zza(map, hashMap);
        boolean zzb = zzdd.zzb((String) this.zzsm.get("useSecure"), true);
        Map<String, String> map2 = this.zzsn;
        Preconditions.checkNotNull(hashMap);
        if (map2 != null) {
            for (Entry entry : map2.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null && !hashMap.containsKey(zza2)) {
                    hashMap.put(zza2, (String) entry.getValue());
                }
            }
        }
        this.zzsn.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzbu().zza((Map<String, String>) hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzbu().zza((Map<String, String>) hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzsl;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzsm.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzsm.put("&a", Integer.toString(parseInt));
            }
        }
        zzk zzbw = zzbw();
        zzp zzp = new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzb, str2);
        zzbw.zza((Runnable) zzp);
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzsm.put(str, str2);
        }
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        this.zzsp.zzm();
        String zzaf = zzca().zzaf();
        if (zzaf != null) {
            set("&an", zzaf);
        }
        String zzag = zzca().zzag();
        if (zzag != null) {
            set("&av", zzag);
        }
    }
}
