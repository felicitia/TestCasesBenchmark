package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.h;
import dagger.a;

/* compiled from: SignInNagFragment_MembersInjector */
public final class x implements a<SignInNagFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<a> b;
    private final javax.a.a<h> c;

    public x(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
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

    public static a<SignInNagFragment> a(javax.a.a<a> aVar, javax.a.a<h> aVar2) {
        return new x(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(SignInNagFragment signInNagFragment) {
        if (signInNagFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        signInNagFragment.mSignInHandler = (a) this.b.b();
        signInNagFragment.mLoginRequester = (h) this.c.b();
    }
}
