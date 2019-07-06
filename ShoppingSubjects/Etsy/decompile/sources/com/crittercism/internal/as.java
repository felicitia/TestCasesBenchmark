package com.crittercism.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class as {
    private static final Map<String, String> i;
    public URL a;
    public URL b;
    public URL c;
    public URL d;
    URL e;
    URL f;
    public URL g = null;
    public URL h = null;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put("00555300", "crittercism.com");
        i.put("00555304", "crit-ci.com");
        i.put("00555305", "crit-staging.com");
        i.put("00444503", "eu.crittercism.com");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:15|(1:22)(2:19|(1:21))|23|24|25|26|27|28|29|30|31|32) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r7.h = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0179, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x017a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0182, code lost:
        throw new java.lang.IllegalArgumentException("Crittercism failed to initialize", r8);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0164 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0177 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public as(java.lang.String r8, com.crittercism.internal.av r9, com.crittercism.internal.ap r10) {
        /*
            r7 = this;
            r7.<init>()
            r0 = 0
            r7.g = r0
            r7.h = r0
            boolean r1 = a(r8)
            if (r1 != 0) goto L_0x0016
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "invalid app ID"
            r8.<init>(r9)
            throw r8
        L_0x0016:
            int r1 = r8.length()
            r2 = 24
            if (r1 != r2) goto L_0x0021
            java.lang.String r8 = "00555300"
            goto L_0x0035
        L_0x0021:
            int r1 = r8.length()
            r2 = 40
            if (r1 != r2) goto L_0x0034
            int r1 = r8.length()
            int r1 = r1 + -8
            java.lang.String r8 = r8.substring(r1)
            goto L_0x0035
        L_0x0034:
            r8 = r0
        L_0x0035:
            java.util.Map<java.lang.String, java.lang.String> r1 = i
            java.lang.Object r8 = r1.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 != 0) goto L_0x0047
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Invalid character sequence"
            r8.<init>(r9)
            throw r8
        L_0x0047:
            java.lang.String r9 = r9.h
            if (r9 == 0) goto L_0x005d
            int r1 = r9.length()
            if (r1 != 0) goto L_0x0052
            goto L_0x005d
        L_0x0052:
            java.lang.String r1 = "unity"
            boolean r1 = r9.equals(r1)
            if (r1 == 0) goto L_0x0066
            java.lang.String r9 = "unity-android"
            goto L_0x0066
        L_0x005d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r1 = "empty or null dev platform"
            r9.<init>(r1)
            java.lang.String r9 = "android"
        L_0x0066:
            java.lang.String r1 = "5.8.10"
            r2 = 46
            r3 = 45
            java.lang.String r1 = r1.replace(r2, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = "-"
            r2.append(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "version-based dns prefix is: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            com.crittercism.internal.cm.d(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "https://"
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r2 = ".network.ingest."
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "https://"
            r2.<init>(r3)
            r2.append(r9)
            java.lang.String r3 = ".error.ingest."
            r2.append(r3)
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "https://"
            r3.<init>(r4)
            r3.append(r9)
            java.lang.String r4 = ".userflows.ingest."
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "https://"
            r4.<init>(r5)
            r4.append(r9)
            java.lang.String r5 = ".appload.ingest."
            r4.append(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "https://"
            r5.<init>(r6)
            r5.append(r9)
            java.lang.String r9 = ".event.ingest."
            r5.append(r9)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            java.lang.String r9 = "https://api.na1.region.vmwservices.com"
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r6 = "com.crittercism.apmUrl"
            java.lang.String r1 = java.lang.System.getProperty(r6, r1)     // Catch:{ MalformedURLException -> 0x017a }
            r5.<init>(r1)     // Catch:{ MalformedURLException -> 0x017a }
            r7.a = r5     // Catch:{ MalformedURLException -> 0x017a }
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r5 = "com.crittercism.apiUrl"
            java.lang.String r2 = java.lang.System.getProperty(r5, r2)     // Catch:{ MalformedURLException -> 0x017a }
            r1.<init>(r2)     // Catch:{ MalformedURLException -> 0x017a }
            r7.b = r1     // Catch:{ MalformedURLException -> 0x017a }
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r2 = "com.crittercism.txnUrl"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)     // Catch:{ MalformedURLException -> 0x017a }
            r1.<init>(r2)     // Catch:{ MalformedURLException -> 0x017a }
            r7.c = r1     // Catch:{ MalformedURLException -> 0x017a }
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r2 = "com.crittercism.appLoadUrl"
            java.lang.String r2 = java.lang.System.getProperty(r2, r4)     // Catch:{ MalformedURLException -> 0x017a }
            r1.<init>(r2)     // Catch:{ MalformedURLException -> 0x017a }
            r7.d = r1     // Catch:{ MalformedURLException -> 0x017a }
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r2 = "com.crittercism.eventUrl"
            java.lang.String r8 = java.lang.System.getProperty(r2, r8)     // Catch:{ MalformedURLException -> 0x017a }
            r1.<init>(r8)     // Catch:{ MalformedURLException -> 0x017a }
            r7.e = r1     // Catch:{ MalformedURLException -> 0x017a }
            java.net.URL r8 = new java.net.URL     // Catch:{ MalformedURLException -> 0x017a }
            java.lang.String r1 = "com.crittercism.dhubRegionUrl"
            java.lang.String r9 = java.lang.System.getProperty(r1, r9)     // Catch:{ MalformedURLException -> 0x017a }
            r8.<init>(r9)     // Catch:{ MalformedURLException -> 0x017a }
            r7.f = r8     // Catch:{ MalformedURLException -> 0x017a }
            com.crittercism.internal.ap$f r8 = com.crittercism.internal.ap.S     // Catch:{ a -> 0x0164 }
            java.lang.Object r8 = r10.a(r8)     // Catch:{ a -> 0x0164 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ a -> 0x0164 }
            java.lang.String r9 = "com.crittercism.dhubConfigUrl"
            java.net.URL r8 = a(r9, r8)     // Catch:{ a -> 0x0164 }
            r7.g = r8     // Catch:{ a -> 0x0164 }
            goto L_0x0166
        L_0x0164:
            r7.g = r0     // Catch:{ MalformedURLException -> 0x017a }
        L_0x0166:
            com.crittercism.internal.ap$f r8 = com.crittercism.internal.ap.T     // Catch:{ a -> 0x0177 }
            java.lang.Object r8 = r10.a(r8)     // Catch:{ a -> 0x0177 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ a -> 0x0177 }
            java.lang.String r9 = "com.crittercism.dhubEventsUrl"
            java.net.URL r8 = a(r9, r8)     // Catch:{ a -> 0x0177 }
            r7.h = r8     // Catch:{ a -> 0x0177 }
            return
        L_0x0177:
            r7.h = r0     // Catch:{ MalformedURLException -> 0x017a }
            return
        L_0x017a:
            r8 = move-exception
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Crittercism failed to initialize"
            r9.<init>(r10, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.as.<init>(java.lang.String, com.crittercism.internal.av, com.crittercism.internal.ap):void");
    }

    public static boolean a(String str) {
        if (str == null) {
            cm.d("null App ID");
            return false;
        } else if (!str.matches("[0-9a-fA-F]+")) {
            cm.d("App ID must be hexadecimal characters");
            return false;
        } else if (str.length() == 24 || str.length() == 40) {
            return true;
        } else {
            cm.d("App ID must be either 24 or 40 characters");
            return false;
        }
    }

    public static URL a(String str, String str2) {
        String property = System.getProperty(str, str2);
        boolean z = true;
        if (property != null && property.length() != 0) {
            int i2 = 0;
            while (true) {
                if (i2 >= property.length()) {
                    break;
                }
                int codePointAt = property.codePointAt(i2);
                if (!Character.isWhitespace(codePointAt)) {
                    z = false;
                    break;
                }
                i2 += Character.charCount(codePointAt);
            }
        }
        if (z) {
            throw new a("blank endpoint");
        }
        if (!property.startsWith("http")) {
            StringBuilder sb = new StringBuilder("https://");
            sb.append(property);
            property = sb.toString();
        }
        try {
            return new URL(property);
        } catch (MalformedURLException e2) {
            StringBuilder sb2 = new StringBuilder("malformed endpoint url: ");
            sb2.append(property);
            a aVar = new a(sb2.toString());
            aVar.initCause(e2);
            throw aVar;
        }
    }
}
