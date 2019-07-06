package com.salesforce.marketingcloud;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;

@RestrictTo({Scope.LIBRARY})
public class MCService extends IntentService {
    @VisibleForTesting
    public static final String a = "com.salesforce.marketingcloud.NOTIFICATION_CLICKED";
    private static final String b = j.a(MCService.class);

    public MCService() {
        super(b);
    }

    public static final Intent a(@NonNull Context context, @NonNull Bundle bundle) {
        return new Intent(context, MCService.class).setAction(a).putExtras(bundle);
    }

    static final Intent a(@NonNull Context context, String str, Bundle bundle) {
        return new Intent(context, MCService.class).setAction(str).putExtras(bundle);
    }

    private static void b(Context context, Bundle bundle) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.salesforce.marketingcloud.notifications.OPENED").putExtras(bundle));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onHandleIntent(android.content.Intent r10) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x00d9
            java.lang.String r0 = r10.getAction()
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            boolean r0 = com.salesforce.marketingcloud.c.c()
            r1 = 0
            if (r0 != 0) goto L_0x0020
            boolean r0 = com.salesforce.marketingcloud.c.b()
            if (r0 != 0) goto L_0x0020
            java.lang.String r10 = b
            java.lang.String r0 = "MarketingCloudSdk#init must be called in your application's onCreate"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.d(r10, r0, r1)
            return
        L_0x0020:
            r0 = 0
            r2 = 1
            java.lang.String r3 = "power"
            java.lang.Object r3 = r9.getSystemService(r3)     // Catch:{ Exception -> 0x008b, all -> 0x0086 }
            android.os.PowerManager r3 = (android.os.PowerManager) r3     // Catch:{ Exception -> 0x008b, all -> 0x0086 }
            java.lang.String r4 = b     // Catch:{ Exception -> 0x008b, all -> 0x0086 }
            android.os.PowerManager$WakeLock r3 = r3.newWakeLock(r2, r4)     // Catch:{ Exception -> 0x008b, all -> 0x0086 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0084 }
            r4 = 30
            long r4 = r0.toMillis(r4)     // Catch:{ Exception -> 0x0084 }
            r3.acquire(r4)     // Catch:{ Exception -> 0x0084 }
            com.salesforce.marketingcloud.c r0 = com.salesforce.marketingcloud.c.a()     // Catch:{ Exception -> 0x0084 }
            if (r0 == 0) goto L_0x006a
            java.lang.String r0 = r10.getAction()     // Catch:{ Exception -> 0x0084 }
            r4 = -1
            int r5 = r0.hashCode()     // Catch:{ Exception -> 0x0084 }
            r6 = -556369808(0xffffffffded67870, float:-7.7271126E18)
            if (r5 == r6) goto L_0x0050
            goto L_0x0059
        L_0x0050:
            java.lang.String r5 = "com.salesforce.marketingcloud.NOTIFICATION_CLICKED"
            boolean r0 = r0.equals(r5)     // Catch:{ Exception -> 0x0084 }
            if (r0 == 0) goto L_0x0059
            r4 = r1
        L_0x0059:
            if (r4 == 0) goto L_0x005f
            com.salesforce.marketingcloud.k.a(r9, r10)     // Catch:{ Exception -> 0x0084 }
            goto L_0x006a
        L_0x005f:
            android.content.Context r0 = r9.getApplicationContext()     // Catch:{ Exception -> 0x0084 }
            android.os.Bundle r4 = r10.getExtras()     // Catch:{ Exception -> 0x0084 }
            b(r0, r4)     // Catch:{ Exception -> 0x0084 }
        L_0x006a:
            if (r3 == 0) goto L_0x0075
            boolean r0 = r3.isHeld()
            if (r0 == 0) goto L_0x0075
            r3.release()
        L_0x0075:
            boolean r0 = android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r10)
            if (r0 == 0) goto L_0x00ba
            java.lang.String r0 = b
            java.lang.String r3 = "Completing wakeful intent: %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r10
            goto L_0x00b7
        L_0x0084:
            r0 = move-exception
            goto L_0x008f
        L_0x0086:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x00bc
        L_0x008b:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
        L_0x008f:
            java.lang.String r4 = b     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = "Encountered exception while handling action: %s"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = r10.getAction()     // Catch:{ all -> 0x00bb }
            r6[r1] = r7     // Catch:{ all -> 0x00bb }
            com.salesforce.marketingcloud.j.c(r4, r0, r5, r6)     // Catch:{ all -> 0x00bb }
            if (r3 == 0) goto L_0x00a9
            boolean r0 = r3.isHeld()
            if (r0 == 0) goto L_0x00a9
            r3.release()
        L_0x00a9:
            boolean r0 = android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r10)
            if (r0 == 0) goto L_0x00ba
            java.lang.String r0 = b
            java.lang.String r3 = "Completing wakeful intent: %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r10
        L_0x00b7:
            com.salesforce.marketingcloud.j.a(r0, r3, r2)
        L_0x00ba:
            return
        L_0x00bb:
            r0 = move-exception
        L_0x00bc:
            if (r3 == 0) goto L_0x00c7
            boolean r4 = r3.isHeld()
            if (r4 == 0) goto L_0x00c7
            r3.release()
        L_0x00c7:
            boolean r3 = android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r10)
            if (r3 == 0) goto L_0x00d8
            java.lang.String r3 = b
            java.lang.String r4 = "Completing wakeful intent: %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r10
            com.salesforce.marketingcloud.j.a(r3, r4, r2)
        L_0x00d8:
            throw r0
        L_0x00d9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MCService.onHandleIntent(android.content.Intent):void");
    }
}
