package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.requests.apiv3.SuggestUsernameEndpoint;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: SignInActivityModule_ProvidesSuggestUsernameEndpointFactory */
public final class m implements c<SuggestUsernameEndpoint> {
    static final /* synthetic */ boolean a = true;
    private final a<e> b;

    public m(a<e> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public SuggestUsernameEndpoint b() {
        return (SuggestUsernameEndpoint) f.a(j.a((e) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<SuggestUsernameEndpoint> a(a<e> aVar) {
        return new m(aVar);
    }
}
