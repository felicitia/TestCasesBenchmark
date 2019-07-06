package com.salesforce.marketingcloud.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Builder;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.salesforce.marketingcloud.MCService;

public abstract class c {
    static int a = 3000;

    public interface a {
        Builder a(@NonNull Context context, @NonNull NotificationMessage notificationMessage);
    }

    public interface b {
        String a(@NonNull Context context, @NonNull NotificationMessage notificationMessage);
    }

    /* renamed from: com.salesforce.marketingcloud.notifications.c$c reason: collision with other inner class name */
    public interface C0173c {
        PendingIntent a(@NonNull Context context, @NonNull NotificationMessage notificationMessage);
    }

    public interface d {
        void a(@NonNull NotificationMessage notificationMessage);
    }

    @NonNull
    public static final PendingIntent a(@NonNull Context context, @NonNull PendingIntent pendingIntent, @NonNull NotificationMessage notificationMessage, boolean z) {
        Bundle bundle = new Bundle(3);
        bundle.putParcelable("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE", notificationMessage);
        bundle.putParcelable("com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT", pendingIntent);
        bundle.putBoolean("com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL", z);
        int i = a;
        a = i + 1;
        return PendingIntent.getService(context, i, MCService.a(context, bundle), ErrorDialogData.BINDER_CRASH);
    }

    @NonNull
    public static final Builder a(@NonNull Context context, @NonNull NotificationMessage notificationMessage) {
        return e.a(context, notificationMessage);
    }

    @Nullable
    public static final NotificationMessage a(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (NotificationMessage) intent.getParcelableExtra("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE");
    }

    public static void b(@NonNull Context context, @NonNull NotificationMessage notificationMessage) {
        if (notificationMessage.notificationId() >= 0) {
            ((NotificationManager) context.getSystemService("notification")).cancel("com.marketingcloud.salesforce.notifications.TAG", notificationMessage.notificationId());
        }
    }
}
