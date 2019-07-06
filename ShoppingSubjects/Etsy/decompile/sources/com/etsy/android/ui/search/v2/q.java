package com.etsy.android.ui.search.v2;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.w;
import dagger.a;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: SearchV2Activity_MembersInjector */
public final class q implements a<SearchV2Activity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;

    public q(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
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

    public static a<SearchV2Activity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
        return new q(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(SearchV2Activity searchV2Activity) {
        if (searchV2Activity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(searchV2Activity, this.b);
        searchV2Activity.mAnalyticsTracker = (w) this.c.b();
    }
}
