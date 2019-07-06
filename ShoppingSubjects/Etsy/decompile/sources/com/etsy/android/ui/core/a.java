package com.etsy.android.ui.core;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.logger.w;
import com.etsy.android.util.f;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: BaseLaunchActivity_MembersInjector */
public final class a implements dagger.a<BaseLaunchActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;
    private final javax.a.a<f> d;

    /* renamed from: a */
    public void injectMembers(BaseLaunchActivity baseLaunchActivity) {
        if (baseLaunchActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(baseLaunchActivity, this.b);
        baseLaunchActivity.mAnalyticsTracker = (w) this.c.b();
        baseLaunchActivity.sessionManager = (f) this.d.b();
    }

    public static void a(BaseLaunchActivity baseLaunchActivity, javax.a.a<f> aVar) {
        baseLaunchActivity.sessionManager = (f) aVar.b();
    }
}
