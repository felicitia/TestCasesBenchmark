package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.push.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SignInActivityModule_ProvidesEtsySignInHandlerFactory */
public final class k implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final a<SignInActivity> b;
    private final a<f> c;
    private final a<com.etsy.android.lib.h.a> d;

    public k(a<SignInActivity> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
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

    /* renamed from: a */
    public a b() {
        return (a) dagger.internal.f.a(j.a((SignInActivity) this.b.b(), (f) this.c.b(), (com.etsy.android.lib.h.a) this.d.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<a> a(a<SignInActivity> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
        return new k(aVar, aVar2, aVar3);
    }
}
