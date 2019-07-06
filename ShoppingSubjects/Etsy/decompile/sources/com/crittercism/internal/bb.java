package com.crittercism.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;

public final class bb implements ba {
    public static final String a = String.format("%s %d (%s)", new Object[]{"Logcat data is not collected for Android APIs before", Integer.valueOf(16), "Jellybean"});

    private static JSONArray a(InputStream inputStream) {
        JSONArray jSONArray = new JSONArray();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int i = 0;
        do {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    jSONArray.put(readLine);
                    i++;
                }
                break;
            } catch (IOException e) {
                cm.c("LogcatProfiler encountered an IOException when attempting to read stream.", e);
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    cm.c("LogcatProfiler unable to close input stream", e2);
                }
            }
        } while (i <= 200);
        try {
            break;
        } catch (IOException e3) {
            cm.c("LogcatProfiler unable to close input stream", e3);
        }
        return jSONArray;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x009e A[Catch:{ all -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONArray a() {
        /*
            r8 = this;
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r1 >= r2) goto L_0x0033
            java.lang.String r1 = a
            r0.put(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "API level is "
            r1.<init>(r2)
            int r2 = android.os.Build.VERSION.SDK_INT
            r1.append(r2)
            java.lang.String r2 = "("
            r1.append(r2)
            java.lang.String r2 = android.os.Build.VERSION.CODENAME
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.put(r1)
            return r0
        L_0x0033:
            r1 = 100
            r2 = 0
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ Exception -> 0x0079 }
            java.lang.ProcessBuilder r3 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x0079 }
            r4 = 5
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x0079 }
            r5 = 0
            java.lang.String r6 = "logcat"
            r4[r5] = r6     // Catch:{ Exception -> 0x0079 }
            java.lang.String r5 = "-t"
            r6 = 1
            r4[r6] = r5     // Catch:{ Exception -> 0x0079 }
            r5 = 2
            r4[r5] = r1     // Catch:{ Exception -> 0x0079 }
            r1 = 3
            java.lang.String r5 = "-v"
            r4[r1] = r5     // Catch:{ Exception -> 0x0079 }
            r1 = 4
            java.lang.String r5 = "time"
            r4[r1] = r5     // Catch:{ Exception -> 0x0079 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0079 }
            java.lang.ProcessBuilder r1 = r3.redirectErrorStream(r6)     // Catch:{ Exception -> 0x0079 }
            java.lang.Process r1 = r1.start()     // Catch:{ Exception -> 0x0079 }
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            org.json.JSONArray r2 = a(r2)     // Catch:{ Exception -> 0x0072, all -> 0x006f }
            if (r1 == 0) goto L_0x006e
            r1.destroy()
        L_0x006e:
            return r2
        L_0x006f:
            r0 = move-exception
            r2 = r1
            goto L_0x00a7
        L_0x0072:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x007a
        L_0x0077:
            r0 = move-exception
            goto L_0x00a7
        L_0x0079:
            r1 = move-exception
        L_0x007a:
            java.lang.String r3 = "Unable to collect logcat data"
            com.crittercism.internal.cm.b(r3, r1)     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = "Unable to collect logcat data due to a(n) "
            r3.<init>(r4)     // Catch:{ all -> 0x0077 }
            java.lang.Class r4 = r1.getClass()     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0077 }
            r3.append(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0077 }
            r0.put(r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x00a1
            r0.put(r1)     // Catch:{ all -> 0x0077 }
        L_0x00a1:
            if (r2 == 0) goto L_0x00a6
            r2.destroy()
        L_0x00a6:
            return r0
        L_0x00a7:
            if (r2 == 0) goto L_0x00ac
            r2.destroy()
        L_0x00ac:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.bb.a():org.json.JSONArray");
    }
}
