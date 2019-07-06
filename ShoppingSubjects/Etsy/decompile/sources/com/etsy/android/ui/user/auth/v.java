package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.h;
import dagger.a;

/* compiled from: SignInFragment_MembersInjector */
public final class v implements a<SignInFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<a> b;
    private final javax.a.a<h> c;

    public v(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
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

    public static a<SignInFragment> a(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
        return new v(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(SignInFragment signInFragment) {
        if (signInFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        signInFragment.mSignInHandler = (a) this.b.b();
        signInFragment.mLoginRequester = (h) this.c.b();
    }
}
