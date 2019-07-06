package com.threatmetrix.TrustDefender.internal;

class ZR implements Runnable {

    /* renamed from: else reason: not valid java name */
    private static final String f746else = TL.m331if(ZR.class);

    /* renamed from: do reason: not valid java name */
    private final String f747do;

    /* renamed from: for reason: not valid java name */
    private final String f748for;

    /* renamed from: if reason: not valid java name */
    private final String f749if;

    /* renamed from: int reason: not valid java name */
    private final String f750int;

    /* renamed from: new reason: not valid java name */
    private final int f751new;

    public ZR(String str, String str2, String str3, String str4, int i) {
        this.f748for = str;
        this.f750int = str2;
        this.f749if = str3;
        this.f747do = str4;
        this.f751new = i;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:7|8|(4:10|(2:13|11)|90|14)|15|16|17|18|19|20|21|22) */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|(4:10|(2:13|11)|90|14)|15|16|17|18|19|20|21|22) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x007e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0081 */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a9 A[SYNTHETIC, Splitter:B:45:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ae A[SYNTHETIC, Splitter:B:49:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b3 A[SYNTHETIC, Splitter:B:53:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c2 A[SYNTHETIC, Splitter:B:61:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c7 A[SYNTHETIC, Splitter:B:65:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00cc A[SYNTHETIC, Splitter:B:69:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00db A[SYNTHETIC, Splitter:B:77:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e0 A[SYNTHETIC, Splitter:B:81:0x00e0] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00e5 A[SYNTHETIC, Splitter:B:85:0x00e5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            r0 = 0
            java.net.Socket r1 = new java.net.Socket     // Catch:{ UnknownHostException -> 0x00d0, IOException -> 0x00b7, all -> 0x009b }
            java.lang.String r2 = r7.f748for     // Catch:{ UnknownHostException -> 0x00d0, IOException -> 0x00b7, all -> 0x009b }
            r3 = 8080(0x1f90, float:1.1322E-41)
            r1.<init>(r2, r3)     // Catch:{ UnknownHostException -> 0x00d0, IOException -> 0x00b7, all -> 0x009b }
            int r2 = r7.f751new     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            r1.setSoTimeout(r2)     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            java.io.PrintWriter r4 = new java.io.PrintWriter     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            r4.<init>(r2)     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            r3.<init>(r4)     // Catch:{ UnknownHostException -> 0x0099, IOException -> 0x0097, all -> 0x0092 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ UnknownHostException -> 0x008f, IOException -> 0x008c, all -> 0x0087 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ UnknownHostException -> 0x008f, IOException -> 0x008c, all -> 0x0087 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ UnknownHostException -> 0x008f, IOException -> 0x008c, all -> 0x0087 }
            r4.<init>(r5)     // Catch:{ UnknownHostException -> 0x008f, IOException -> 0x008c, all -> 0x0087 }
            r2.<init>(r4)     // Catch:{ UnknownHostException -> 0x008f, IOException -> 0x008c, all -> 0x0087 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = "<handle sig=FF44EE55 session_id="
            r0.<init>(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = r7.f749if     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = " org_id="
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = r7.f750int     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = " w="
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = r7.f747do     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r4 = " />"
            r0.append(r4)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            java.lang.String r0 = r0.toString()     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r3.write(r0)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r3.flush()     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            int r0 = r2.read()     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r4 = -1
            if (r0 == r4) goto L_0x0074
            r0 = 1
            char[] r0 = new char[r0]     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            r4 = 0
            r0[r4] = r4     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
        L_0x0067:
            r5 = 15
            if (r4 >= r5) goto L_0x0071
            r3.write(r0)     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
            int r4 = r4 + 1
            goto L_0x0067
        L_0x0071:
            r2.read()     // Catch:{ UnknownHostException -> 0x0090, IOException -> 0x008d, all -> 0x0085 }
        L_0x0074:
            java.lang.String r0 = f746else
            java.lang.String r4 = "Tidying up"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r4)
            r1.close()     // Catch:{ IOException -> 0x007e }
        L_0x007e:
            r3.close()     // Catch:{ IOException -> 0x0081 }
        L_0x0081:
            r2.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0084:
            return
        L_0x0085:
            r0 = move-exception
            goto L_0x00a0
        L_0x0087:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x00a0
        L_0x008c:
            r2 = r0
        L_0x008d:
            r0 = r3
            goto L_0x00b9
        L_0x008f:
            r2 = r0
        L_0x0090:
            r0 = r3
            goto L_0x00d2
        L_0x0092:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
            goto L_0x00a0
        L_0x0097:
            r2 = r0
            goto L_0x00b9
        L_0x0099:
            r2 = r0
            goto L_0x00d2
        L_0x009b:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r0 = r1
            r1 = r3
        L_0x00a0:
            java.lang.String r4 = f746else
            java.lang.String r5 = "Tidying up"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r5)
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ IOException -> 0x00ac }
        L_0x00ac:
            if (r3 == 0) goto L_0x00b1
            r3.close()     // Catch:{ IOException -> 0x00b1 }
        L_0x00b1:
            if (r2 == 0) goto L_0x00b6
            r2.close()     // Catch:{ IOException -> 0x00b6 }
        L_0x00b6:
            throw r0
        L_0x00b7:
            r1 = r0
            r2 = r1
        L_0x00b9:
            java.lang.String r3 = f746else
            java.lang.String r4 = "Tidying up"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r4)
            if (r1 == 0) goto L_0x00c5
            r1.close()     // Catch:{ IOException -> 0x00c5 }
        L_0x00c5:
            if (r0 == 0) goto L_0x00ca
            r0.close()     // Catch:{ IOException -> 0x00ca }
        L_0x00ca:
            if (r2 == 0) goto L_0x00e9
            r2.close()     // Catch:{ IOException -> 0x00cf }
        L_0x00cf:
            return
        L_0x00d0:
            r1 = r0
            r2 = r1
        L_0x00d2:
            java.lang.String r3 = f746else
            java.lang.String r4 = "Tidying up"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r4)
            if (r1 == 0) goto L_0x00de
            r1.close()     // Catch:{ IOException -> 0x00de }
        L_0x00de:
            if (r0 == 0) goto L_0x00e3
            r0.close()     // Catch:{ IOException -> 0x00e3 }
        L_0x00e3:
            if (r2 == 0) goto L_0x00e9
            r2.close()     // Catch:{ IOException -> 0x00e8 }
        L_0x00e8:
            return
        L_0x00e9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.ZR.run():void");
    }
}
