package com.etsy.android.ui.core;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.logger.w;
import dagger.a;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: CoreActivity_MembersInjector */
public final class b implements a<CoreActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;
    private final javax.a.a<l> d;

    public b(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<l> aVar3) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static a<CoreActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<l> aVar3) {
        return new b(aVar, aVar2, aVar3);
    }

    /* renamed from: a */
    public void injectMembers(CoreActivity coreActivity) {
        if (coreActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(coreActivity, this.b);
        coreActivity.mAnalyticsTracker = (w) this.c.b();
        coreActivity.logCat = (l) this.d.b();
    }
}
