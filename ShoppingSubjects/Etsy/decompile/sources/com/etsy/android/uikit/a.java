package com.etsy.android.uikit;

import android.support.v4.app.Fragment;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: BaseActivity_MembersInjector */
public final class a implements dagger.a<BaseActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;

    /* renamed from: a */
    public void injectMembers(BaseActivity baseActivity) {
        if (baseActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        baseActivity.mSupportFragmentInjector = (DispatchingAndroidInjector) this.b.b();
    }

    public static void a(BaseActivity baseActivity, javax.a.a<DispatchingAndroidInjector<Fragment>> aVar) {
        baseActivity.mSupportFragmentInjector = (DispatchingAndroidInjector) aVar.b();
    }
}
