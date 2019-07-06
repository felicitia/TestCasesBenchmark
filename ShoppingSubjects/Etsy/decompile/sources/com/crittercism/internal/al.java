package com.crittercism.internal;

import java.lang.Thread.UncaughtExceptionHandler;

public abstract class al implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler a;

    public abstract void a(Throwable th);

    public al(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r2.a == null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        if (r2.a != null) goto L_0x0007;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0007, code lost:
        r2.a.uncaughtException(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void uncaughtException(java.lang.Thread r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            r2.a(r4)     // Catch:{ ThreadDeath -> 0x001b, Throwable -> 0x000f }
            java.lang.Thread$UncaughtExceptionHandler r0 = r2.a
            if (r0 == 0) goto L_0x001a
        L_0x0007:
            java.lang.Thread$UncaughtExceptionHandler r0 = r2.a
            r0.uncaughtException(r3, r4)
            return
        L_0x000d:
            r0 = move-exception
            goto L_0x001d
        L_0x000f:
            r0 = move-exception
            java.lang.String r1 = "Unable to send crash"
            com.crittercism.internal.cm.a(r1, r0)     // Catch:{ all -> 0x000d }
            java.lang.Thread$UncaughtExceptionHandler r0 = r2.a
            if (r0 == 0) goto L_0x001a
            goto L_0x0007
        L_0x001a:
            return
        L_0x001b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x000d }
        L_0x001d:
            java.lang.Thread$UncaughtExceptionHandler r1 = r2.a
            if (r1 == 0) goto L_0x0026
            java.lang.Thread$UncaughtExceptionHandler r1 = r2.a
            r1.uncaughtException(r3, r4)
        L_0x0026:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.al.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
