package com.contextlogic.wish.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationManagerCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.social.google.GoogleManager;
import java.util.Iterator;

public class NotificationUtil {
    public static void sendNotification(Notification notification, int i) {
        NotificationManagerCompat.from(WishApplication.getInstance()).notify(i, notification);
    }

    public static void clearAllPushNotifications() {
        clearNotificationsFromPreference("push-notification-ids");
    }

    public static void clearAllLocalNotifications() {
        clearNotificationsFromPreference("LocalNotificationIds");
    }

    private static void clearNotificationsFromPreference(String str) {
        Iterator it = PreferenceUtil.getIntegerArray(str).iterator();
        while (it.hasNext()) {
            clearNotification(((Integer) it.next()).intValue());
        }
        PreferenceUtil.setIntegerArray(str, null);
    }

    public static void clearNotification(int i) {
        try {
            NotificationManagerCompat.from(WishApplication.getInstance()).cancel(i);
        } catch (Throwable unused) {
        }
    }

    public static String getPushNotificationType() {
        if (GoogleManager.getInstance().isPlayServicesAvailable()) {
            return "3";
        }
        return null;
    }

    public static void createNotificationChannel(Context context) {
        if (VERSION.SDK_INT >= 26) {
            String string = context.getString(R.string.channel_name);
            String string2 = context.getString(R.string.channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(context.getString(R.string.default_notification_channel), string, 3);
            notificationChannel.setDescription(string2);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
