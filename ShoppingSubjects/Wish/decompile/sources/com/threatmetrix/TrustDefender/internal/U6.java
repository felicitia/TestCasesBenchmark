package com.threatmetrix.TrustDefender.internal;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class U6 implements LocationListener {

    /* renamed from: int reason: not valid java name */
    private static final String f549int = TL.m331if(U6.class);

    /* renamed from: if reason: not valid java name */
    Location f550if = null;

    U6() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a3, code lost:
        if (r4 == false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        if (r0 != false) goto L_0x00ae;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLocationChanged(android.location.Location r11) {
        /*
            r10 = this;
            java.lang.String r0 = f549int
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onLocationChanged() : "
            r1.<init>(r2)
            java.lang.String r2 = r11.getProvider()
            r1.append(r2)
            java.lang.String r2 = ":"
            r1.append(r2)
            double r2 = r11.getLatitude()
            r1.append(r2)
            java.lang.String r2 = ":"
            r1.append(r2)
            double r2 = r11.getLongitude()
            r1.append(r2)
            java.lang.String r2 = ":"
            r1.append(r2)
            float r2 = r11.getAccuracy()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)
            android.location.Location r0 = r10.f550if
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0043
            goto L_0x00ae
        L_0x0043:
            long r3 = r11.getTime()
            long r5 = r0.getTime()
            long r7 = r3 - r5
            r3 = 120000(0x1d4c0, double:5.9288E-319)
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0056
            r3 = 1
            goto L_0x0057
        L_0x0056:
            r3 = 0
        L_0x0057:
            r4 = -120000(0xfffffffffffe2b40, double:NaN)
            int r6 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0060
            r4 = 1
            goto L_0x0061
        L_0x0060:
            r4 = 0
        L_0x0061:
            r5 = 0
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x0069
            r5 = 1
            goto L_0x006a
        L_0x0069:
            r5 = 0
        L_0x006a:
            if (r3 == 0) goto L_0x006d
            goto L_0x00ae
        L_0x006d:
            if (r4 != 0) goto L_0x00ad
            float r3 = r11.getAccuracy()
            float r4 = r0.getAccuracy()
            float r3 = r3 - r4
            int r3 = (int) r3
            if (r3 <= 0) goto L_0x007d
            r4 = 1
            goto L_0x007e
        L_0x007d:
            r4 = 0
        L_0x007e:
            if (r3 >= 0) goto L_0x0082
            r6 = 1
            goto L_0x0083
        L_0x0082:
            r6 = 0
        L_0x0083:
            r7 = 200(0xc8, float:2.8E-43)
            if (r3 <= r7) goto L_0x0089
            r3 = 1
            goto L_0x008a
        L_0x0089:
            r3 = 0
        L_0x008a:
            java.lang.String r7 = r11.getProvider()
            java.lang.String r0 = r0.getProvider()
            if (r7 != 0) goto L_0x009a
            if (r0 != 0) goto L_0x0098
            r0 = 1
            goto L_0x009e
        L_0x0098:
            r0 = 0
            goto L_0x009e
        L_0x009a:
            boolean r0 = r7.equals(r0)
        L_0x009e:
            if (r6 == 0) goto L_0x00a1
            goto L_0x00ae
        L_0x00a1:
            if (r5 == 0) goto L_0x00a6
            if (r4 != 0) goto L_0x00a6
            goto L_0x00ae
        L_0x00a6:
            if (r5 == 0) goto L_0x00ad
            if (r3 != 0) goto L_0x00ad
            if (r0 == 0) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r2 = 0
        L_0x00ae:
            if (r2 == 0) goto L_0x00b2
            r10.f550if = r11
        L_0x00b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.U6.onLocationChanged(android.location.Location):void");
    }

    public void onProviderDisabled(String str) {
        TL.m338new(f549int, "onProviderDisabled: ".concat(String.valueOf(str)));
    }

    public void onProviderEnabled(String str) {
        TL.m338new(f549int, "onProviderEnabled: ".concat(String.valueOf(str)));
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        String str2 = f549int;
        StringBuilder sb = new StringBuilder("onStatusChanged: ");
        sb.append(str);
        sb.append(" status: ");
        String str3 = i == 2 ? "available " : i == 1 ? "temporarily unavailable" : i == 0 ? "Out of Service" : "unknown";
        sb.append(str3);
        TL.m338new(str2, sb.toString());
    }
}
