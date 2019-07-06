package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.logger.elk.f;
import com.etsy.android.lib.requests.apiv3.SuggestUsernameEndpoint;
import dagger.a;

/* compiled from: RegisterFragment_MembersInjector */
public final class h implements a<RegisterFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<a> b;
    private final javax.a.a<com.etsy.android.lib.auth.h> c;
    private final javax.a.a<SuggestUsernameEndpoint> d;
    private final javax.a.a<com.etsy.android.lib.f.a> e;
    private final javax.a.a<f> f;

    public h(javax.a.a<a> aVar, javax.a.a<com.etsy.android.lib.auth.h> aVar2, javax.a.a<SuggestUsernameEndpoint> aVar3, javax.a.a<com.etsy.android.lib.f.a> aVar4, javax.a.a<f> aVar5) {
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

    public static a<RegisterFragment> a(javax.a.a<a> aVar, javax.a.a<com.etsy.android.lib.auth.h> aVar2, javax.a.a<SuggestUsernameEndpoint> aVar3, javax.a.a<com.etsy.android.lib.f.a> aVar4, javax.a.a<f> aVar5) {
        h hVar = new h(aVar, aVar2, aVar3, aVar4, aVar5);
        return hVar;
    }

    /* renamed from: a */
    public void injectMembers(RegisterFragment registerFragment) {
        if (registerFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        registerFragment.mSignInHandler = (a) this.b.b();
        registerFragment.mLoginRequester = (com.etsy.android.lib.auth.h) this.c.b();
        registerFragment.suggestUsernameEndpoint = (SuggestUsernameEndpoint) this.d.b();
        registerFragment.schedulers = (com.etsy.android.lib.f.a) this.e.b();
        registerFragment.elkLogger = (f) this.f.b();
    }
}
