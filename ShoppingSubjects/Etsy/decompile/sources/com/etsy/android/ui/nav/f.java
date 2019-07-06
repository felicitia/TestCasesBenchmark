package com.etsy.android.ui.nav;

import io.reactivex.functions.Consumer;

final /* synthetic */ class f implements Consumer {
    private final NotificationActivity a;

    f(NotificationActivity notificationActivity) {
        this.a = notificationActivity;
    }

    public void accept(Object obj) {
        this.a.lambda$onStart$0$NotificationActivity((Long) obj);
    }
}
