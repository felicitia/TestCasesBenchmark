package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.h;
import dagger.a;

/* compiled from: SignInTwoFactorFragment_MembersInjector */
public final class z implements a<SignInTwoFactorFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<a> b;
    private final javax.a.a<h> c;

    public z(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
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

    public static a<SignInTwoFactorFragment> a(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
        return new z(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(SignInTwoFactorFragment signInTwoFactorFragment) {
        if (signInTwoFactorFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        signInTwoFactorFragment.mSignInHandler = (a) this.b.b();
        signInTwoFactorFragment.mLoginRequester = (h) this.c.b();
    }
}
