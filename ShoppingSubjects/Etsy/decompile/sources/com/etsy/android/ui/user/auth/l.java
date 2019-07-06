package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.m;
import com.etsy.android.lib.push.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SignInActivityModule_ProvidesSignInHandlerFactory */
public final class l implements c<m> {
    static final /* synthetic */ boolean a = true;
    private final a<SignInActivity> b;
    private final a<f> c;
    private final a<com.etsy.android.lib.h.a> d;

    public l(a<SignInActivity> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
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
    public m b() {
        return (m) dagger.internal.f.a(j.b((SignInActivity) this.b.b(), (f) this.c.b(), (com.etsy.android.lib.h.a) this.d.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<m> a(a<SignInActivity> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
        return new l(aVar, aVar2, aVar3);
    }
}
