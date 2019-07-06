package com.android.riskifiedbeacon;

import android.content.Context;
import android.provider.Settings.Secure;
import java.util.Arrays;

public class UniqueIdBuilder {
    public static String getDeviceId(Context context) {
        String uniqueID = getUniqueID(context);
        return uniqueID == null ? Secure.getString(context.getContentResolver(), "android_id") : uniqueID;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getUniqueID(android.content.Context r3) {
        /*
            java.lang.String r0 = "NoTelephonyId"
            java.lang.String r1 = "NoAndroidId"
            java.lang.String r2 = "phone"
            java.lang.Object r2 = r3.getSystemService(r2)     // Catch:{ Exception -> 0x0016 }
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ Exception -> 0x0016 }
            java.lang.String r2 = r2.getDeviceId()     // Catch:{ Exception -> 0x0016 }
            if (r2 != 0) goto L_0x0015
            java.lang.String r0 = "NoTelephonyId"
            goto L_0x0016
        L_0x0015:
            r0 = r2
        L_0x0016:
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r2 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r3, r2)     // Catch:{ Exception -> 0x0024 }
            if (r3 != 0) goto L_0x0025
            java.lang.String r1 = "NoAndroidId"
        L_0x0024:
            r3 = r1
        L_0x0025:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004a }
            r1.<init>()     // Catch:{ Exception -> 0x004a }
            int r3 = r3.hashCode()     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = getStringIntegerHexBlocks(r3)     // Catch:{ Exception -> 0x004a }
            r1.append(r3)     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = "-"
            r1.append(r3)     // Catch:{ Exception -> 0x004a }
            int r3 = r0.hashCode()     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = getStringIntegerHexBlocks(r3)     // Catch:{ Exception -> 0x004a }
            r1.append(r3)     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x004a }
            return r3
        L_0x004a:
            java.lang.String r3 = "0000-0000-1111-1111"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.riskifiedbeacon.UniqueIdBuilder.getUniqueID(android.content.Context):java.lang.String");
    }

    private static String getStringIntegerHexBlocks(int i) {
        String hexString = Integer.toHexString(i);
        char[] cArr = new char[(8 - hexString.length())];
        Arrays.fill(cArr, '0');
        StringBuilder sb = new StringBuilder();
        sb.append(new String(cArr));
        sb.append(hexString);
        String sb2 = sb.toString();
        String str = "";
        int i2 = 0;
        for (int length = sb2.length() - 1; length >= 0; length--) {
            i2++;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2.substring(length, length + 1));
            sb3.append(str);
            str = sb3.toString();
            if (i2 == 4) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("-");
                sb4.append(str);
                str = sb4.toString();
                i2 = 0;
            }
        }
        return str.startsWith("-") ? str.substring(1, str.length()) : str;
    }
}
