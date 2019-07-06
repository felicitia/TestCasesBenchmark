package com.salesforce.marketingcloud.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

@RestrictTo({Scope.LIBRARY})
public interface m {
    void a(@NonNull NotificationMessage notificationMessage);

    void b(@NonNull NotificationMessage notificationMessage);
}
