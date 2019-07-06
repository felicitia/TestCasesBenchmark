package com.etsy.android.ui.feedback;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.w;
import dagger.a;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: FeedbackActivity_MembersInjector */
public final class f implements a<FeedbackActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;
    private final javax.a.a<g> d;

    public f(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<g> aVar3) {
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

    public static a<FeedbackActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<g> aVar3) {
        return new f(aVar, aVar2, aVar3);
    }

    /* renamed from: a */
    public void injectMembers(FeedbackActivity feedbackActivity) {
        if (feedbackActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(feedbackActivity, this.b);
        feedbackActivity.mAnalyticsTracker = (w) this.c.b();
        feedbackActivity.presenter = (g) this.d.b();
    }
}
