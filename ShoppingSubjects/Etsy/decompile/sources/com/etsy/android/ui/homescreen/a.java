package com.etsy.android.ui.homescreen;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.w;
import com.etsy.android.util.f;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: HomescreenTabsActivity_MembersInjector */
public final class a implements dagger.a<HomescreenTabsActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;
    private final javax.a.a<f> d;
    private final javax.a.a<com.etsy.android.lib.logger.elk.a.a> e;
    private final javax.a.a<v> f;
    private final javax.a.a<com.etsy.android.deeplinking.a> g;

    public a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<f> aVar3, javax.a.a<com.etsy.android.lib.logger.elk.a.a> aVar4, javax.a.a<v> aVar5, javax.a.a<com.etsy.android.deeplinking.a> aVar6) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        if (a || aVar5 != null) {
                            this.f = aVar5;
                            if (a || aVar6 != null) {
                                this.g = aVar6;
                                return;
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static dagger.a<HomescreenTabsActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<f> aVar3, javax.a.a<com.etsy.android.lib.logger.elk.a.a> aVar4, javax.a.a<v> aVar5, javax.a.a<com.etsy.android.deeplinking.a> aVar6) {
        a aVar7 = new a(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
        return aVar7;
    }

    /* renamed from: a */
    public void injectMembers(HomescreenTabsActivity homescreenTabsActivity) {
        if (homescreenTabsActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(homescreenTabsActivity, this.b);
        homescreenTabsActivity.mAnalyticsTracker = (w) this.c.b();
        com.etsy.android.ui.core.a.a(homescreenTabsActivity, this.d);
        homescreenTabsActivity.graphite = (com.etsy.android.lib.logger.elk.a.a) this.e.b();
        homescreenTabsActivity.session = (v) this.f.b();
        homescreenTabsActivity.button = (com.etsy.android.deeplinking.a) this.g.b();
    }
}
