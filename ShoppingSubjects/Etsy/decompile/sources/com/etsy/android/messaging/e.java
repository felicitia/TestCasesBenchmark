package com.etsy.android.messaging;

import com.etsy.android.R;
import com.etsy.android.lib.messaging.GcmBroadcastReceiver;
import com.etsy.android.lib.messaging.f;
import com.etsy.android.ui.nav.NotificationActivity;

/* compiled from: EtsyNotificationDelegate */
public class e implements f {
    protected static String a;

    public int d() {
        return R.drawable.ic_stat_ic_notification;
    }

    public int e() {
        return R.color.sk_orange_30;
    }

    public Class<?> a() {
        return NotificationActivity.class;
    }

    public Class<?> b() {
        return GcmBroadcastReceiver.class;
    }

    public String c() {
        return a;
    }
}
