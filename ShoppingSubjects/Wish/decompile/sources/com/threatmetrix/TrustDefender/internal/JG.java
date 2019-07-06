package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import com.threatmetrix.TrustDefender.internal.P.O;

class JG {

    /* renamed from: do reason: not valid java name */
    private static String f248do;

    /* renamed from: if reason: not valid java name */
    private static final String f249if = TL.m331if(JG.class);

    JG() {
    }

    /* renamed from: int reason: not valid java name */
    static String m109int(O o) {
        String str = Z.m198for(o.f487for.getContentResolver(), "android_id");
        if (str == null || str.equals("9774d56d682e549c") || str.length() < 15) {
            return null;
        }
        return str;
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: if reason: not valid java name */
    static String m108if(O o) {
        String str;
        boolean z = new R(o.f487for).m169if("android.permission.READ_PHONE_STATE", o.f487for.getPackageName());
        String str2 = null;
        if (z) {
            try {
                Object systemService = o.f487for.getSystemService("phone");
                if (systemService != null) {
                    if (systemService instanceof TelephonyManager) {
                        if (C0012I.f388for < 26) {
                            str = ((TelephonyManager) systemService).getDeviceId();
                        } else {
                            TelephonyManager telephonyManager = (TelephonyManager) systemService;
                            String imei = telephonyManager.getImei();
                            if (imei == null) {
                                str = telephonyManager.getMeid();
                            } else {
                                str = imei;
                            }
                        }
                        if (str == null || str.equals("000000000000000")) {
                            str = "";
                        }
                        str2 = str;
                        if (str2.isEmpty()) {
                            TL.m338new(f249if, "Failed to get useful imei");
                        }
                    }
                }
                return null;
            } catch (SecurityException unused) {
                str2 = "";
            } catch (Exception e) {
                TL.m338new(f249if, e.toString());
                str2 = "";
            }
        }
        return str2;
    }

    /* renamed from: new reason: not valid java name */
    static String m112new(O o) {
        String str = I.f371byte;
        if (NK.m203byte(str) && !str.equalsIgnoreCase("unknown") && !str.equals("1234567890ABCDEF")) {
            return str;
        }
        if (new R(o.f487for).m169if("android.permission.READ_PHONE_STATE", o.f487for.getPackageName())) {
            try {
                return I.m161for();
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f249if, e.toString());
            }
        }
        return null;
    }

    /* renamed from: for reason: not valid java name */
    static boolean m107for(O o) {
        if (NK.m215if(f248do)) {
            f248do = m112new(o);
        }
        return NK.m203byte(f248do) && (f248do.equalsIgnoreCase("unknown") || f248do.equals("1234567890ABCDEF"));
    }

    /* renamed from: do reason: not valid java name */
    private static String m105do(String str) {
        String str2;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.length() == 32) {
            return str;
        }
        if (str.length() < 32) {
            String str3 = NK.m208do(str);
            if (str3 == null) {
                return null;
            }
            int length = 32 - str.length();
            if (length > str3.length()) {
                length = str3.length();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str3.substring(0, length));
            str2 = sb.toString();
        } else {
            str2 = NK.m208do(str);
        }
        return str2;
    }

    /* renamed from: new reason: not valid java name */
    static String m113new(String str, boolean z) throws InterruptedException {
        if (z) {
            str = NK.m208do(str);
        }
        return m105do(str);
    }

    /* renamed from: int reason: not valid java name */
    static String m110int(String str, boolean z) {
        if (str != null && z) {
            str = NK.m208do(str);
        }
        return m105do(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m103do(com.threatmetrix.TrustDefender.internal.P.O r4) {
        /*
            boolean r0 = com.threatmetrix.TrustDefender.internal.N.A.f358for
            r1 = 0
            if (r0 == 0) goto L_0x000f
            boolean r0 = com.threatmetrix.TrustDefender.internal.N.A.f359if
            if (r0 == 0) goto L_0x000f
            r0 = 1
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            if (r0 != 0) goto L_0x0029
            java.util.UUID r4 = java.util.UUID.randomUUID()
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "-"
            java.lang.String r1 = ""
            java.lang.String r4 = r4.replace(r0, r1)
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r4 = r4.toLowerCase(r0)
            return r4
        L_0x0029:
            android.content.Context r4 = r4.f487for
            java.lang.String r0 = "ThreatMetrixMobileSDK"
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r0, r1)
            r0 = 0
            java.lang.String r1 = "ThreatMetrixMobileSDK"
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f358for     // Catch:{ ClassCastException -> 0x0045 }
            if (r2 == 0) goto L_0x0045
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f361new     // Catch:{ ClassCastException -> 0x0045 }
            if (r2 == 0) goto L_0x0045
            java.lang.String r1 = r4.getString(r1, r0)     // Catch:{ ClassCastException -> 0x0045 }
            goto L_0x0046
        L_0x0045:
            r1 = r0
        L_0x0046:
            if (r1 != 0) goto L_0x0084
            java.util.UUID r1 = java.util.UUID.randomUUID()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "-"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.replace(r2, r3)
            java.util.Locale r2 = java.util.Locale.US
            java.lang.String r1 = r1.toLowerCase(r2)
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f358for
            if (r2 == 0) goto L_0x0074
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f359if
            if (r2 == 0) goto L_0x0074
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f355char
            if (r2 == 0) goto L_0x0074
            android.content.SharedPreferences$Editor r0 = r4.edit()
        L_0x0074:
            if (r0 == 0) goto L_0x0084
            java.lang.String r4 = "ThreatMetrixMobileSDK"
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.A.f362try
            if (r2 == 0) goto L_0x0081
            r0.putString(r4, r1)
        L_0x0081:
            r0.apply()
        L_0x0084:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.JG.m103do(com.threatmetrix.TrustDefender.internal.P$O):java.lang.String");
    }

    /* renamed from: do reason: not valid java name */
    static String m106do(String str, String str2, String str3, boolean z, O o) throws InterruptedException {
        if (NK.m215if(f248do)) {
            f248do = m112new(o);
        }
        String str4 = f248do;
        if (f248do == null) {
            str4 = "";
        }
        if (NK.m203byte(str3)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            sb.append(str3);
            str4 = sb.toString();
        } else if (NK.m203byte(str)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str4);
            sb2.append(str);
            str4 = sb2.toString();
        } else if (NK.m203byte(str2)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str4);
            sb3.append(str2);
            str4 = sb3.toString();
        }
        if (z) {
            str4 = NK.m208do(str4);
        }
        return NK.m208do(str4);
    }

    /* renamed from: int reason: not valid java name */
    static void m111int(O o, String str, String str2, String str3) {
        if (A.f358for && A.f359if && A.f355char && A.f362try) {
            Editor edit = o.f487for.getSharedPreferences(str, 0).edit();
            if (edit != null) {
                edit.putString(str2, str3);
                edit.apply();
            }
        }
    }

    /* renamed from: do reason: not valid java name */
    static String m104do(O o, String str, String str2) {
        try {
            if (!A.f358for || !A.f361new) {
                return null;
            }
            return o.f487for.getSharedPreferences(str, 0).getString(str2, null);
        } catch (ClassCastException e) {
            TL.m328for(f249if, "Found preference of different type", (Throwable) e);
            return null;
        }
    }
}
