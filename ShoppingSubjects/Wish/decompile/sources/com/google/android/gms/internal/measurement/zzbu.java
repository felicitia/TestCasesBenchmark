package com.google.android.gms.internal.measurement;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.HashSet;
import java.util.Set;

public final class zzbu {
    private final zzat zzqm;
    private volatile Boolean zzxy;
    private String zzxz;
    private Set<Integer> zzya;

    protected zzbu(zzat zzat) {
        Preconditions.checkNotNull(zzat);
        this.zzqm = zzat;
    }

    public static boolean zzdt() {
        return ((Boolean) zzcc.zzyk.get()).booleanValue();
    }

    public static int zzdu() {
        return ((Integer) zzcc.zzzh.get()).intValue();
    }

    public static long zzdv() {
        return ((Long) zzcc.zzys.get()).longValue();
    }

    public static long zzdw() {
        return ((Long) zzcc.zzyv.get()).longValue();
    }

    public static int zzdx() {
        return ((Integer) zzcc.zzyx.get()).intValue();
    }

    public static int zzdy() {
        return ((Integer) zzcc.zzyy.get()).intValue();
    }

    public static String zzdz() {
        return (String) zzcc.zzza.get();
    }

    public static String zzea() {
        return (String) zzcc.zzyz.get();
    }

    public static String zzeb() {
        return (String) zzcc.zzzb.get();
    }

    public static long zzed() {
        return ((Long) zzcc.zzzp.get()).longValue();
    }

    public final boolean zzds() {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    ApplicationInfo applicationInfo = this.zzqm.getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzxy = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if ((this.zzxy == null || !this.zzxy.booleanValue()) && "com.google.android.gms.analytics".equals(myProcessName)) {
                        this.zzxy = Boolean.TRUE;
                    }
                    if (this.zzxy == null) {
                        this.zzxy = Boolean.TRUE;
                        this.zzqm.zzbu().zzu("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzxy.booleanValue();
    }

    public final Set<Integer> zzec() {
        String str = (String) zzcc.zzzk.get();
        if (this.zzya == null || this.zzxz == null || !this.zzxz.equals(str)) {
            String[] split = TextUtils.split(str, ",");
            HashSet hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException unused) {
                }
            }
            this.zzxz = str;
            this.zzya = hashSet;
        }
        return this.zzya;
    }
}
