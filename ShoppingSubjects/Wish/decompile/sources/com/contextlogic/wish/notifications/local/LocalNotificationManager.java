package com.contextlogic.wish.notifications.local;

import androidx.work.WorkManager;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.crashlytics.android.Crashlytics;

public final class LocalNotificationManager {
    private static final String WORKER_NOTIFICATION_TAG = "com.contextlogic.wish.notifications.local.LocalNotificationManager";
    private static LocalNotificationManager sInstance = new LocalNotificationManager();

    private LocalNotificationManager() {
    }

    public static LocalNotificationManager getInstance() {
        return sInstance;
    }

    public void initialize() {
        ForegroundWatcher.getInstance().addListener(new ForegroundListener() {
            public void onLoggedInForeground() {
            }

            public void onForeground() {
                LocalNotificationManager.this.cancelAlarm();
            }

            public void onBackground() {
                LocalNotificationManager.this.scheduleAlarm();
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007f, code lost:
        if (com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance().getCartCount() > 0) goto L_0x0081;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scheduleAlarm() {
        /*
            r11 = this;
            com.contextlogic.wish.api.datacenter.ConfigDataCenter r0 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
            com.contextlogic.wish.api.model.WishLocalNotification r0 = r0.getLocalNotification()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0083
            com.contextlogic.wish.api.datacenter.AuthenticationDataCenter r3 = com.contextlogic.wish.api.datacenter.AuthenticationDataCenter.getInstance()
            boolean r3 = r3.isLoggedIn()
            if (r3 == 0) goto L_0x0083
            java.lang.String r3 = "lastLocalNotificationSent"
            r4 = 0
            long r3 = com.contextlogic.wish.util.PreferenceUtil.getLong(r3, r4)
            long r5 = java.lang.System.currentTimeMillis()
            long r7 = r5 - r3
            long r3 = r0.getMinimumInterval()
            long r5 = r0.getDelay()
            long r9 = r3 - r5
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0083
            java.lang.String r3 = r0.getType()
            java.lang.String r4 = "forced"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x006b
            java.lang.String r3 = "local_notification_id"
            java.util.ArrayList r3 = com.contextlogic.wish.util.PreferenceUtil.getStringArray(r3)
            java.lang.String r4 = r0.getId()
            boolean r4 = r3.contains(r4)
            if (r4 != 0) goto L_0x0083
            java.lang.String r4 = r0.getId()
            r3.add(r1, r4)
        L_0x0055:
            int r4 = r3.size()
            r5 = 5
            if (r4 <= r5) goto L_0x0065
            int r4 = r3.size()
            int r4 = r4 - r2
            r3.remove(r4)
            goto L_0x0055
        L_0x0065:
            java.lang.String r4 = "local_notification_id"
            com.contextlogic.wish.util.PreferenceUtil.setStringArray(r4, r3)
            goto L_0x0081
        L_0x006b:
            java.lang.String r3 = r0.getType()
            java.lang.String r4 = "cart"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0083
            com.contextlogic.wish.api.datacenter.StatusDataCenter r3 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            int r3 = r3.getCartCount()
            if (r3 <= 0) goto L_0x0083
        L_0x0081:
            r3 = 1
            goto L_0x0084
        L_0x0083:
            r3 = 0
        L_0x0084:
            if (r3 == 0) goto L_0x00f1
            java.lang.String r3 = r0.getId()
            com.contextlogic.wish.analytics.LocalNotificationLogger$ActionType r4 = com.contextlogic.wish.analytics.LocalNotificationLogger.ActionType.SCHEDULED
            com.contextlogic.wish.analytics.LocalNotificationLogger.logAction(r3, r4)
            androidx.work.WorkManager r3 = androidx.work.WorkManager.getInstance()
            if (r3 != 0) goto L_0x00a0
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r1 = "WorkManager cannot by null"
            r0.<init>(r1)
            com.crashlytics.android.Crashlytics.logException(r0)
            return
        L_0x00a0:
            androidx.work.Data$Builder r4 = new androidx.work.Data$Builder
            r4.<init>()
            java.lang.String r5 = "notification_id"
            java.lang.String r6 = r0.getId()
            androidx.work.Data$Builder r4 = r4.putString(r5, r6)
            java.lang.String r5 = "notification_text"
            java.lang.String r6 = r0.getText()
            androidx.work.Data$Builder r4 = r4.putString(r5, r6)
            java.lang.String r5 = "notification_deep_link"
            java.lang.String r6 = r0.getTarget()
            androidx.work.Data$Builder r4 = r4.putString(r5, r6)
            androidx.work.Data r4 = r4.build()
            androidx.work.OneTimeWorkRequest$Builder r5 = new androidx.work.OneTimeWorkRequest$Builder
            java.lang.Class<com.contextlogic.wish.notifications.local.LocalNotificationWorker> r6 = com.contextlogic.wish.notifications.local.LocalNotificationWorker.class
            r5.<init>(r6)
            long r6 = r0.getDelay()
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            androidx.work.OneTimeWorkRequest$Builder r0 = r5.setInitialDelay(r6, r0)
            androidx.work.WorkRequest$Builder r0 = r0.setInputData(r4)
            androidx.work.OneTimeWorkRequest$Builder r0 = (androidx.work.OneTimeWorkRequest.Builder) r0
            java.lang.String r4 = WORKER_NOTIFICATION_TAG
            androidx.work.WorkRequest$Builder r0 = r0.addTag(r4)
            androidx.work.OneTimeWorkRequest$Builder r0 = (androidx.work.OneTimeWorkRequest.Builder) r0
            androidx.work.OneTimeWorkRequest r0 = r0.build()
            androidx.work.WorkRequest[] r2 = new androidx.work.WorkRequest[r2]
            r2[r1] = r0
            r3.enqueue(r2)
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.notifications.local.LocalNotificationManager.scheduleAlarm():void");
    }

    /* access modifiers changed from: private */
    public void cancelAlarm() {
        WorkManager instance = WorkManager.getInstance();
        if (instance == null) {
            Crashlytics.logException(new Exception("WorkManager cannot by null"));
        } else {
            instance.cancelAllWorkByTag(WORKER_NOTIFICATION_TAG);
        }
    }
}
