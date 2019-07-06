package com.threatmetrix.TrustDefender.internal;

class HM {

    /* renamed from: new reason: not valid java name */
    private static final String f177new = TL.m331if(HM.class);

    HM() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* renamed from: new reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m71new() throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x003a }
            java.lang.String r2 = "/proc/mounts"
            r1.<init>(r2)     // Catch:{ all -> 0x003a }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0038 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0038 }
            r3.<init>(r1)     // Catch:{ all -> 0x0038 }
            r2.<init>(r3)     // Catch:{ all -> 0x0038 }
        L_0x0012:
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x0038 }
            if (r3 == 0) goto L_0x0034
            java.lang.String r4 = " "
            java.lang.String[] r3 = r3.split(r4)     // Catch:{ all -> 0x0038 }
            int r4 = r3.length     // Catch:{ all -> 0x0038 }
            r5 = 3
            if (r4 < r5) goto L_0x0012
            java.lang.String r4 = "selinuxfs"
            r5 = 2
            r5 = r3[r5]     // Catch:{ all -> 0x0038 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0012
            r0 = 1
            r0 = r3[r0]     // Catch:{ all -> 0x0038 }
            r1.close()
            return r0
        L_0x0034:
            r1.close()
            return r0
        L_0x0038:
            r0 = move-exception
            goto L_0x003e
        L_0x003a:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.close()
        L_0x0043:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HM.m71new():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032  */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m70for() throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "/proc/filesystems"
            r1.<init>(r2)     // Catch:{ all -> 0x002c }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x002a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x002a }
            r2.<init>(r1)     // Catch:{ all -> 0x002a }
            r0.<init>(r2)     // Catch:{ all -> 0x002a }
        L_0x0012:
            java.lang.String r2 = r0.readLine()     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0025
            java.lang.String r3 = "selinuxfs"
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0012
            r1.close()
            r0 = 1
            return r0
        L_0x0025:
            r1.close()
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            goto L_0x0030
        L_0x002c:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()
        L_0x0035:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HM.m70for():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0063, code lost:
        if (r0 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0069, code lost:
        if (r0 != null) goto L_0x0065;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005f A[SYNTHETIC, Splitter:B:42:0x005f] */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.threatmetrix.TrustDefender.internal.K7.W m69do() {
        /*
            r0 = 0
            java.lang.String r1 = m71new()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            if (r1 != 0) goto L_0x0013
            boolean r1 = m70for()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            if (r1 == 0) goto L_0x0010
            com.threatmetrix.TrustDefender.internal.K7$W r1 = com.threatmetrix.TrustDefender.internal.K7.W.UNKNOWN     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            return r1
        L_0x0010:
            com.threatmetrix.TrustDefender.internal.K7$W r1 = com.threatmetrix.TrustDefender.internal.K7.W.NONE     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            return r1
        L_0x0013:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            r3.append(r1)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            java.lang.String r1 = "/enforce"
            r3.append(r1)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            java.lang.String r1 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0063, all -> 0x005c }
            r0 = 10
            byte[] r1 = new byte[r0]     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            int r3 = r2.read(r1)     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            if (r3 != r0) goto L_0x0039
            com.threatmetrix.TrustDefender.internal.K7$W r0 = com.threatmetrix.TrustDefender.internal.K7.W.UNKNOWN     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            r2.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r0
        L_0x0039:
            java.lang.String r0 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            byte[] r1 = java.util.Arrays.copyOf(r1, r3)     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            r1 = 1
            if (r0 != r1) goto L_0x004f
            com.threatmetrix.TrustDefender.internal.K7$W r0 = com.threatmetrix.TrustDefender.internal.K7.W.ENFORCING     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            r2.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            return r0
        L_0x004f:
            com.threatmetrix.TrustDefender.internal.K7$W r0 = com.threatmetrix.TrustDefender.internal.K7.W.PERMISSIVE     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0058, all -> 0x0055 }
            r2.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            return r0
        L_0x0055:
            r1 = move-exception
            r0 = r2
            goto L_0x005d
        L_0x0058:
            r0 = r2
            goto L_0x0063
        L_0x005a:
            r0 = r2
            goto L_0x0069
        L_0x005c:
            r1 = move-exception
        L_0x005d:
            if (r0 == 0) goto L_0x0062
            r0.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            throw r1
        L_0x0063:
            if (r0 == 0) goto L_0x006c
        L_0x0065:
            r0.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x006c
        L_0x0069:
            if (r0 == 0) goto L_0x006c
            goto L_0x0065
        L_0x006c:
            com.threatmetrix.TrustDefender.internal.K7$W r0 = com.threatmetrix.TrustDefender.internal.K7.W.UNKNOWN
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HM.m69do():com.threatmetrix.TrustDefender.internal.K7$W");
    }
}
