package com.wish.android.shakedelegate;

public class FileHelper {
    private static final String TAG = "FileHelper";

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[SYNTHETIC, Splitter:B:20:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064 A[SYNTHETIC, Splitter:B:26:0x0064] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File writeStringToFile(java.io.File r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0037 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0037 }
            byte[] r5 = r5.getBytes()     // Catch:{ IOException -> 0x0032, all -> 0x002f }
            r1.write(r5)     // Catch:{ IOException -> 0x0032, all -> 0x002f }
            if (r1 == 0) goto L_0x0061
            r1.close()     // Catch:{ IOException -> 0x0013 }
            goto L_0x0061
        L_0x0013:
            r5 = move-exception
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x001b:
            java.lang.String r2 = "Failed to close stream. "
            r1.append(r2)
            java.lang.Throwable r5 = r5.getCause()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            android.util.Log.e(r0, r5)
            goto L_0x0061
        L_0x002f:
            r4 = move-exception
            r0 = r1
            goto L_0x0062
        L_0x0032:
            r5 = move-exception
            r0 = r1
            goto L_0x0038
        L_0x0035:
            r4 = move-exception
            goto L_0x0062
        L_0x0037:
            r5 = move-exception
        L_0x0038:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0035 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r2.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = "Failed to open FileOutputStream. "
            r2.append(r3)     // Catch:{ all -> 0x0035 }
            java.lang.Throwable r5 = r5.getCause()     // Catch:{ all -> 0x0035 }
            r2.append(r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0035 }
            android.util.Log.e(r1, r5)     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0061
            r0.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x0061
        L_0x0058:
            r5 = move-exception
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x001b
        L_0x0061:
            return r4
        L_0x0062:
            if (r0 == 0) goto L_0x0083
            r0.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x0083
        L_0x0068:
            r5 = move-exception
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to close stream. "
            r1.append(r2)
            java.lang.Throwable r5 = r5.getCause()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            android.util.Log.e(r0, r5)
        L_0x0083:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wish.android.shakedelegate.FileHelper.writeStringToFile(java.io.File, java.lang.String):java.io.File");
    }
}
