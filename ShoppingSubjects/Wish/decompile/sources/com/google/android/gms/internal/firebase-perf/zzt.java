package com.google.android.gms.internal.firebase-perf;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzt {
    private static boolean zzbz = true;
    private static boolean zzca = false;
    private final int zzcb;
    private final int zzcc;
    private final boolean zzcd;
    private boolean zzce;
    private zzv zzcf;
    private zzv zzcg;

    public zzt(Context context, String str, long j, long j2) {
        this(100, 500, new zzx(), zzf(Secure.getString(context.getContentResolver(), "android_id")), zza(context, str));
        this.zzce = zzae.zzf(context);
    }

    private zzt(long j, long j2, zzx zzx, int i, List<String> list) {
        this.zzce = false;
        this.zzcf = null;
        this.zzcg = null;
        this.zzcb = i;
        Map zza = zza(list);
        if (zza.containsKey("sampling")) {
            this.zzcc = ((Long) zza.get("sampling")).intValue();
        } else {
            this.zzcc = 100;
        }
        if (this.zzcc != 100) {
            int i2 = this.zzcc;
            int i3 = this.zzcb;
            StringBuilder sb = new StringBuilder(59);
            sb.append("RateLimiter sampling rate:");
            sb.append(i2);
            sb.append(" bucketId: ");
            sb.append(i3);
            Log.d("FirebasePerformance", sb.toString());
        }
        boolean z = true;
        this.zzcd = this.zzcb <= this.zzcc;
        if (!this.zzcd) {
            Log.d("FirebasePerformance", "logging is disabled because device sampling");
        } else {
            zzx zzx2 = zzx;
            Map map = zza;
            zzv zzv = new zzv(100, 500, zzx2, map, zzu.TRACE, this.zzce);
            this.zzcf = zzv;
            zzv zzv2 = new zzv(100, 500, zzx2, map, zzu.NETWORK, this.zzce);
            this.zzcg = zzv2;
        }
        if (zza.containsKey("enable_screen_trace")) {
            zzbz = ((Long) zza.get("enable_screen_trace")).intValue() != 0;
            if (this.zzce) {
                boolean z2 = zzbz;
                StringBuilder sb2 = new StringBuilder(25);
                sb2.append("enable_screen_trace:");
                sb2.append(z2);
                Log.d("FirebasePerformance", sb2.toString());
            }
        }
        if (zza.containsKey("sessions_feature_enabled")) {
            if (((Long) zza.get("sessions_feature_enabled")).intValue() == 0) {
                z = false;
            }
            zzca = z;
            if (this.zzce) {
                boolean z3 = zzca;
                StringBuilder sb3 = new StringBuilder(30);
                sb3.append("sessions_feature_enabled:");
                sb3.append(z3);
                Log.d("FirebasePerformance", sb3.toString());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb(zzar zzar) {
        if (!this.zzcd) {
            return false;
        }
        boolean z = true;
        if (!(zzar.zzgr == null || zzar.zzgr.name == null || (!zzar.zzgr.name.equals(zzz.FOREGROUND_TRACE_NAME.toString()) && !zzar.zzgr.name.equals(zzz.BACKGROUND_TRACE_NAME.toString())) || zzar.zzgr.zzha == null || zzar.zzgr.zzha.length <= 0)) {
            return true;
        }
        if (zzar.zzgr == null || zzar.zzgr.name == null || !zzar.zzgr.name.startsWith("_st_") || zzbz) {
            z = false;
        }
        if (z) {
            if (this.zzce) {
                Log.d("FirebasePerformance", "screen trace is off");
            }
            return false;
        } else if (zzar.zzgs != null) {
            return this.zzcg.zzb(zzar);
        } else {
            if (zzar.zzgr != null) {
                return this.zzcf.zzb(zzar);
            }
            return false;
        }
    }

    public static boolean zzu() {
        return zzca;
    }

    private static int zzf(String str) {
        int i;
        try {
            i = zzae.zza(MessageDigest.getInstance("SHA-1").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            i = zzae.zza(str.getBytes());
        }
        return (((i % 100) + 100) % 100) + 1;
    }

    private static String zzg(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte valueOf : digest) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static int zze(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException unused) {
            return 0;
        }
    }

    private static List<String> zza(Context context, String str) {
        Object obj;
        ArrayList arrayList = new ArrayList();
        int zze = zze(context);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(str);
        sb.append(":");
        sb.append(zze);
        String[] strArr = {sb.toString(), str, "1.0.0.206222422"};
        for (int i = 0; i < 3; i++) {
            String str2 = strArr[i];
            String valueOf = String.valueOf("_fireperf1:");
            String valueOf2 = String.valueOf(str2);
            String zzg = zzg(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzg).length() + 16);
            sb2.append("fireperf:");
            sb2.append(zzg);
            sb2.append("_limits");
            try {
                obj = zza.zza(context.getContentResolver(), sb2.toString(), (String) null);
            } catch (SecurityException e) {
                String str3 = "FirebasePerformance";
                String str4 = "Failed to fetch Gservices flag. SecurityException: ";
                String valueOf3 = String.valueOf(e.getMessage());
                Log.e(str3, valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4));
                obj = null;
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private static Map<String, Long> zza(List<String> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (String split : list) {
            for (String split2 : split.split(",")) {
                String[] split3 = split2.split(":");
                if (split3.length >= 2) {
                    String trim = split3[0].trim();
                    if (!trim.isEmpty() && !hashMap.containsKey(trim)) {
                        try {
                            long parseLong = Long.parseLong(split3[1].trim());
                            if (parseLong >= 0) {
                                hashMap.put(trim, Long.valueOf(parseLong));
                            }
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(boolean z) {
        if (this.zzcd) {
            this.zzcf.zzb(z);
            this.zzcg.zzb(z);
        }
    }

    static boolean zzv() {
        return zzbz;
    }
}
