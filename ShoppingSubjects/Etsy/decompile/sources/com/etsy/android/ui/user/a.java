package com.etsy.android.ui.user;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.w;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: PhabletsActivity_MembersInjector */
public final class a implements dagger.a<PhabletsActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;

    public a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
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

    public static dagger.a<PhabletsActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
        return new a(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(PhabletsActivity phabletsActivity) {
        if (phabletsActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(phabletsActivity, this.b);
        phabletsActivity.mAnalyticsTracker = (w) this.c.b();
    }
}
