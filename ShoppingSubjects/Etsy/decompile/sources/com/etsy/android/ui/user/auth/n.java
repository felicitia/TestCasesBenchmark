package com.etsy.android.ui.user.auth;

import android.support.v4.app.Fragment;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.logger.w;
import dagger.a;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: SignInActivity_MembersInjector */
public final class n implements a<SignInActivity> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Fragment>> b;
    private final javax.a.a<w> c;
    private final javax.a.a<h> d;

    public n(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<h> aVar3) {
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

    public static a<SignInActivity> a(javax.a.a<DispatchingAndroidInjector<Fragment>> aVar, javax.a.a<w> aVar2, javax.a.a<h> aVar3) {
        return new n(aVar, aVar2, aVar3);
    }

    /* renamed from: a */
    public void injectMembers(SignInActivity signInActivity) {
        if (signInActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.uikit.a.a(signInActivity, this.b);
        signInActivity.mAnalyticsTracker = (w) this.c.b();
        signInActivity.mLoginRequester = (h) this.d.b();
    }
}
