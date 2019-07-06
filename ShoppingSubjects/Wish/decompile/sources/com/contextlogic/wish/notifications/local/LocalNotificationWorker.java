package com.contextlogic.wish.notifications.local;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import androidx.work.Worker;
import androidx.work.Worker.WorkerResult;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.link.DeepLinkActivity;
import com.contextlogic.wish.analytics.LocalNotificationLogger;
import com.contextlogic.wish.analytics.LocalNotificationLogger.ActionType;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.util.NotificationUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.Random;

public class LocalNotificationWorker extends Worker {
    private static final String ACTION_LOCAL_NOTIFICATION = "com.contextlogic.wish.notifications.local.LocalNotificationWorker";

    public WorkerResult doWork() {
        if (ForegroundWatcher.getInstance().isForeground()) {
            return WorkerResult.SUCCESS;
        }
        String string = getInputData().getString("notification_id", null);
        String string2 = getInputData().getString("notification_text", null);
        String string3 = getInputData().getString("notification_deep_link", null);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid LocalNotificationWorker params: notificationId = ");
            sb.append(string);
            sb.append(", notificationText = ");
            sb.append(string2);
            sb.append(", notificationDeepLink = ");
            sb.append(string3);
            Crashlytics.logException(new Exception(sb.toString()));
            return WorkerResult.SUCCESS;
        }
        LocalNotificationLogger.logAction(string, ActionType.IMPRESSION);
        Builder builder = new Builder(getApplicationContext(), getApplicationContext().getString(R.string.default_notification_channel));
        builder.setAutoCancel(true);
        builder.setTicker(string2);
        builder.setContentTitle(getApplicationContext().getString(R.string.app_name));
        builder.setContentText(string2);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(3);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.main_primary);
        builder.setLights(color, 400, 2000);
        builder.setColor(color);
        builder.setSmallIcon(R.drawable.notification_status_bar);
        builder.setStyle(new BigTextStyle().bigText(string2).setBigContentTitle(getApplicationContext().getString(R.string.app_name)));
        int hashCode = string.hashCode();
        Intent intent = new Intent(getApplicationContext(), DeepLinkActivity.class);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(ACTION_LOCAL_NOTIFICATION);
        sb2.append(hashCode);
        intent.setAction(sb2.toString());
        intent.putExtra(DeepLinkActivity.EXTRA_LOCAL_NOTIFICATION_ID, string);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(string3));
        builder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), new Random().nextInt(1000000), intent, 0));
        NotificationUtil.clearNotification(hashCode);
        NotificationUtil.sendNotification(builder.build(), hashCode);
        ArrayList integerArray = PreferenceUtil.getIntegerArray("LocalNotificationIds");
        if (!integerArray.contains(Integer.valueOf(hashCode))) {
            integerArray.add(Integer.valueOf(hashCode));
        }
        PreferenceUtil.setIntegerArray("LocalNotificationIds", integerArray);
        PreferenceUtil.setLong("lastLocalNotificationSent", System.currentTimeMillis());
        return WorkerResult.SUCCESS;
    }
}
