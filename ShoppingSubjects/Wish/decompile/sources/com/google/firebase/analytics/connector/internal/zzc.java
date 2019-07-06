package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.util.Arrays;
import java.util.List;

public final class zzc {
    private static final List<String> zzbqm = Arrays.asList(new String[]{"_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", "app_open"});
    private static final List<String> zzbqn = Arrays.asList(new String[]{"auto", "app", "am"});
    private static final List<String> zzbqo = Arrays.asList(new String[]{"_r", "_dbg"});
    private static final List<String> zzbqp = Arrays.asList((String[]) ArrayUtils.concat(UserProperty.zzada, UserProperty.zzadb));
    private static final List<String> zzbqq = Arrays.asList(new String[]{"^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$"});

    public static boolean zza(String str, Bundle bundle) {
        if (zzbqm.contains(str)) {
            return false;
        }
        if (bundle != null) {
            for (String containsKey : zzbqo) {
                if (bundle.containsKey(containsKey)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzb(java.lang.String r4, java.lang.String r5, android.os.Bundle r6) {
        /*
            java.lang.String r0 = "_cmp"
            boolean r5 = r0.equals(r5)
            r0 = 1
            if (r5 != 0) goto L_0x000a
            return r0
        L_0x000a:
            boolean r5 = zzff(r4)
            r1 = 0
            if (r5 != 0) goto L_0x0012
            return r1
        L_0x0012:
            if (r6 != 0) goto L_0x0015
            return r1
        L_0x0015:
            java.util.List<java.lang.String> r5 = zzbqo
            java.util.Iterator r5 = r5.iterator()
        L_0x001b:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = r6.containsKey(r2)
            if (r2 == 0) goto L_0x001b
            return r1
        L_0x002e:
            r5 = -1
            int r2 = r4.hashCode()
            r3 = 101200(0x18b50, float:1.41811E-40)
            if (r2 == r3) goto L_0x0057
            r3 = 101230(0x18b6e, float:1.41853E-40)
            if (r2 == r3) goto L_0x004d
            r3 = 3142703(0x2ff42f, float:4.403865E-39)
            if (r2 == r3) goto L_0x0043
            goto L_0x0061
        L_0x0043:
            java.lang.String r2 = "fiam"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x0061
            r4 = 2
            goto L_0x0062
        L_0x004d:
            java.lang.String r2 = "fdl"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x0061
            r4 = 1
            goto L_0x0062
        L_0x0057:
            java.lang.String r2 = "fcm"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x0061
            r4 = 0
            goto L_0x0062
        L_0x0061:
            r4 = -1
        L_0x0062:
            switch(r4) {
                case 0: goto L_0x0073;
                case 1: goto L_0x006e;
                case 2: goto L_0x0066;
                default: goto L_0x0065;
            }
        L_0x0065:
            return r1
        L_0x0066:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fiam_integration"
        L_0x006a:
            r6.putString(r4, r5)
            return r0
        L_0x006e:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fdl_integration"
            goto L_0x006a
        L_0x0073:
            java.lang.String r4 = "_cis"
            java.lang.String r5 = "fcm_integration"
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.internal.zzc.zzb(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }

    public static boolean zzff(String str) {
        return !zzbqn.contains(str);
    }

    public static boolean zzw(String str, String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc");
        }
        if ("_ln".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN);
        }
        if (zzbqp.contains(str2)) {
            return false;
        }
        for (String matches : zzbqq) {
            if (str2.matches(matches)) {
                return false;
            }
        }
        return true;
    }
}
