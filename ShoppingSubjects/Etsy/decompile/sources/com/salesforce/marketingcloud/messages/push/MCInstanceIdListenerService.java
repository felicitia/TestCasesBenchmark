package com.salesforce.marketingcloud.messages.push;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.salesforce.marketingcloud.j;

public class MCInstanceIdListenerService extends InstanceIDListenerService {
    private static final String a = j.a(MCInstanceIdListenerService.class);

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        if (r2.isHeld() != false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0071, code lost:
        if (r2.isHeld() != false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        r2.release();
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d  */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onTokenRefresh() {
        /*
            r6 = this;
            boolean r0 = com.salesforce.marketingcloud.c.c()
            r1 = 0
            if (r0 != 0) goto L_0x0017
            boolean r0 = com.salesforce.marketingcloud.c.b()
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = a
            java.lang.String r2 = "MarketingCloudSdk#init must be called in your application's onCreate"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.d(r0, r2, r1)
            return
        L_0x0017:
            r0 = 0
            java.lang.String r2 = "power"
            java.lang.Object r2 = r6.getSystemService(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005a }
            android.os.PowerManager r2 = (android.os.PowerManager) r2     // Catch:{ Exception -> 0x005e, all -> 0x005a }
            r3 = 1
            java.lang.String r4 = a     // Catch:{ Exception -> 0x005e, all -> 0x005a }
            android.os.PowerManager$WakeLock r2 = r2.newWakeLock(r3, r4)     // Catch:{ Exception -> 0x005e, all -> 0x005a }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0058 }
            r3 = 30
            long r3 = r0.toMillis(r3)     // Catch:{ Exception -> 0x0058 }
            r2.acquire(r3)     // Catch:{ Exception -> 0x0058 }
            com.salesforce.marketingcloud.c r0 = com.salesforce.marketingcloud.c.a()     // Catch:{ Exception -> 0x0058 }
            if (r0 == 0) goto L_0x004f
            com.salesforce.marketingcloud.b r0 = r0.d()     // Catch:{ Exception -> 0x0058 }
            java.lang.String r0 = r0.d()     // Catch:{ Exception -> 0x0058 }
            if (r0 == 0) goto L_0x0046
            com.salesforce.marketingcloud.k.b(r6, r0)     // Catch:{ Exception -> 0x0058 }
            goto L_0x004f
        L_0x0046:
            java.lang.String r0 = a     // Catch:{ Exception -> 0x0058 }
            java.lang.String r3 = "Received tokenRefresh intent but SenderId was not set."
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0058 }
            com.salesforce.marketingcloud.j.b(r0, r3, r4)     // Catch:{ Exception -> 0x0058 }
        L_0x004f:
            if (r2 == 0) goto L_0x0076
            boolean r0 = r2.isHeld()
            if (r0 == 0) goto L_0x0076
            goto L_0x0073
        L_0x0058:
            r0 = move-exception
            goto L_0x0062
        L_0x005a:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x0078
        L_0x005e:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L_0x0062:
            java.lang.String r3 = a     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = "Something went wrong while trying to refresh our push notification token."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0077 }
            com.salesforce.marketingcloud.j.c(r3, r0, r4, r1)     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0076
            boolean r0 = r2.isHeld()
            if (r0 == 0) goto L_0x0076
        L_0x0073:
            r2.release()
        L_0x0076:
            return
        L_0x0077:
            r0 = move-exception
        L_0x0078:
            if (r2 == 0) goto L_0x0083
            boolean r1 = r2.isHeld()
            if (r1 == 0) goto L_0x0083
            r2.release()
        L_0x0083:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.push.MCInstanceIdListenerService.onTokenRefresh():void");
    }
}
