package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.m;
import com.etsy.android.lib.c.e;
import com.etsy.android.lib.h.a;
import com.etsy.android.lib.push.f;
import com.etsy.android.lib.requests.apiv3.SuggestUsernameEndpoint;

/* compiled from: SignInActivityModule */
public abstract class j {
    static a a(SignInActivity signInActivity, f fVar, a aVar) {
        return new a(signInActivity, signInActivity, fVar, aVar);
    }

    static m b(SignInActivity signInActivity, f fVar, a aVar) {
        return new a(signInActivity, signInActivity, fVar, aVar);
    }

    static SuggestUsernameEndpoint a(e eVar) {
        return (SuggestUsernameEndpoint) eVar.a().a(SuggestUsernameEndpoint.class);
    }
}
