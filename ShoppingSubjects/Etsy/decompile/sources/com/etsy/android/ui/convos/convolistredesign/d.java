package com.etsy.android.ui.convos.convolistredesign;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.w;
import dagger.a;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: ConvoListActivity_MembersInjector */
public final class d implements a<ConvoListActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;

    public d(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
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

    public static a<ConvoListActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2) {
        return new d(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(ConvoListActivity convoListActivity) {
        if (convoListActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(convoListActivity, this.b);
        convoListActivity.mAnalyticsTracker = (w) this.c.b();
    }
}
