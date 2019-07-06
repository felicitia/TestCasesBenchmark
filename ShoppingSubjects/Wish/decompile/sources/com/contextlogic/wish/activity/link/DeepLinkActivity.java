package com.contextlogic.wish.activity.link;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.LocalNotificationLogger;
import com.contextlogic.wish.analytics.LocalNotificationLogger.ActionType;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLink.TargetType;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.util.NotificationUtil;

public class DeepLinkActivity extends FullScreenActivity {
    public static String EXTRA_CANCEL_NOTIFICATION_ID = "ExtraCancelNotificationId";
    public static String EXTRA_LOCAL_NOTIFICATION_ID = "ExtraLocalNotificationId";
    public static String EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER = "ExtraPushNotificationBucketNumber";
    public static String EXTRA_PUSH_NOTIFICATION_NUMBER = "ExtraPushNotificationNumber";

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isHeadlessActivity() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        return false;
    }

    public void handleOnCreate(Bundle bundle) {
        DeepLink deepLink;
        super.handleOnCreate(bundle);
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getDeepLinkProtocol());
        sb.append("://notifications");
        DeepLink deepLink2 = new DeepLink(sb.toString());
        try {
            Uri data = getIntent().getData();
            if (data != null) {
                Uri data2 = getIntent().getData();
                if (data.getHost() == null || (!data.getScheme().equals("http") && !data.getScheme().equals("https"))) {
                    deepLink = new DeepLink(data);
                } else {
                    String replaceFirst = data2.toString().replaceFirst("/m/", "/").replaceFirst("/m\\?", "/?").replaceFirst("/m#", "/#");
                    if (replaceFirst.endsWith("/m")) {
                        replaceFirst = replaceFirst.substring(0, replaceFirst.length() - 2);
                    }
                    deepLink = new DeepLink(replaceFirst);
                }
                if (deepLink.getTargetType() != TargetType.NONE) {
                    deepLink2 = deepLink;
                }
            }
        } catch (Throwable unused) {
        }
        DeepLinkManager.getInstance().setLastAppLaunchDeepLink(deepLink2);
        String stringExtra = getIntent().getStringExtra(EXTRA_LOCAL_NOTIFICATION_ID);
        if (stringExtra != null) {
            LocalNotificationLogger.logAction(stringExtra, ActionType.CLICKED);
        }
        WishNotification wishNotification = null;
        int intExtra = getIntent().getIntExtra(EXTRA_PUSH_NOTIFICATION_BUCKET_NUMBER, -1);
        int intExtra2 = getIntent().getIntExtra(EXTRA_PUSH_NOTIFICATION_NUMBER, -1);
        if (intExtra >= 0 && intExtra2 >= 0) {
            wishNotification = new WishNotification(intExtra, intExtra2);
        }
        int intExtra3 = getIntent().getIntExtra(EXTRA_CANCEL_NOTIFICATION_ID, -1);
        if (intExtra3 >= 0) {
            NotificationUtil.clearNotification(intExtra3);
        }
        Intent intent = DeepLinkManager.getIntent(deepLink2, true, wishNotification);
        intent.setFlags(268435456);
        startActivity(intent);
        finish();
    }
}
