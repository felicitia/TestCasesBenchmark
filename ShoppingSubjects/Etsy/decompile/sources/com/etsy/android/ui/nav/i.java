package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.l;
import dagger.a;

/* compiled from: NotificationActivity_MembersInjector */
public final class i implements a<NotificationActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<l> b;
    private final javax.a.a<com.etsy.android.deeplinking.a> c;

    public i(javax.a.a<l> aVar, javax.a.a<com.etsy.android.deeplinking.a> aVar2) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static a<NotificationActivity> a(javax.a.a<l> aVar, javax.a.a<com.etsy.android.deeplinking.a> aVar2) {
        return new i(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(NotificationActivity notificationActivity) {
        if (notificationActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        notificationActivity.log = (l) this.b.b();
        notificationActivity.button = (com.etsy.android.deeplinking.a) this.c.b();
    }
}
