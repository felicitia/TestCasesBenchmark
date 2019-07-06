package com.etsy.android.ui.user;

import com.etsy.android.lib.push.f;
import dagger.a;

/* compiled from: UserSettingsFragment_MembersInjector */
public final class k implements a<UserSettingsFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<f> b;

    public k(javax.a.a<f> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<UserSettingsFragment> a(javax.a.a<f> aVar) {
        return new k(aVar);
    }

    /* renamed from: a */
    public void injectMembers(UserSettingsFragment userSettingsFragment) {
        if (userSettingsFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        userSettingsFragment.pushRegistration = (f) this.b.b();
    }
}
