package com.etsy.android.ui.user.auth;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.d;
import com.etsy.android.lib.auth.a.b.C0055a;
import com.etsy.android.lib.auth.e.b;
import com.etsy.android.lib.auth.e.c;
import com.etsy.android.lib.auth.external.a.C0057a;
import com.etsy.android.lib.auth.external.t;
import com.etsy.android.lib.auth.f.a.C0059f;
import com.etsy.android.lib.auth.f.a.e;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.auth.m;
import com.etsy.android.lib.auth.q;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.push.f;
import com.etsy.android.lib.requests.LocaleRequest;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import java.util.concurrent.Callable;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;
import org.scribe.model.Token;

/* compiled from: EtsySignInHandler.kt */
public final class a extends m {
    private final FragmentActivity a;
    private final i b;

    /* renamed from: com.etsy.android.ui.user.auth.a$a reason: collision with other inner class name */
    /* compiled from: EtsySignInHandler.kt */
    static final class C0100a<V> implements Callable<T> {
        final /* synthetic */ com.etsy.android.lib.auth.q.a a;

        C0100a(com.etsy.android.lib.auth.q.a aVar) {
            this.a = aVar;
        }

        /* renamed from: a */
        public final e call() {
            v a2 = v.a();
            p.a((Object) a2, "Session.getInstance()");
            k kVar = (k) a2.j().b(new d(EtsyApplication.get()));
            if (kVar != null && kVar.a()) {
                LocaleRequest.setUserLocale();
                User a3 = d.a(kVar);
                if (a3 != null) {
                    return new e(this.a.a(), a3);
                }
            }
            throw new RuntimeException("Error downloading user");
        }
    }

    public a(FragmentActivity fragmentActivity, i iVar, f fVar, com.etsy.android.lib.h.a aVar) {
        p.b(fragmentActivity, "activity");
        p.b(iVar, "signInActivityCallback");
        p.b(fVar, "pushRegistration");
        p.b(aVar, "userRepository");
        Context applicationContext = fragmentActivity.getApplicationContext();
        p.a((Object) applicationContext, "activity.applicationContext");
        super(applicationContext, fVar, aVar);
        this.a = fragmentActivity;
        this.b = iVar;
    }

    private final void a(b bVar) {
        Bundle bundle = new Bundle();
        C0055a a2 = bVar.a();
        bundle.putString("email", a2.d());
        bundle.putString("account_type_name", a2.a());
        bundle.putString("account_id", a2.b());
        com.etsy.android.lib.auth.external.a c = a2.c();
        if (c instanceof C0057a) {
            bundle.putString("auth_code", ((C0057a) c).a());
        } else if (c instanceof com.etsy.android.lib.auth.external.a.b) {
            bundle.putString("auth_token", ((com.etsy.android.lib.auth.external.a.b) c).a());
        }
        bundle.putBoolean("link_external_account", true);
        this.b.showLinkAccountSignInScreen(bundle);
    }

    private final void a(c cVar) {
        String str;
        com.etsy.android.lib.auth.external.d a2 = cVar.a();
        String str2 = null;
        String str3 = null;
        t e = a2.e();
        if (e != null) {
            str3 = e.a();
            str = e.b();
        } else {
            str = str3;
        }
        switch (b.a[a2.h().ordinal()]) {
            case 1:
                str2 = RegisterFragment.GENDER_NAME_FEMALE;
                break;
            case 2:
                str2 = RegisterFragment.GENDER_NAME_MALE;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.FIRST_NAME, str3);
        bundle.putString(ResponseConstants.LAST_NAME, str);
        bundle.putString("email", a2.g());
        bundle.putString("gender", str2);
        bundle.putString("birthday", a2.i());
        bundle.putString("avatar_url", a2.j());
        bundle.putString("account_type_name", a2.a());
        bundle.putString("account_id", a2.d());
        com.etsy.android.lib.auth.external.a b2 = a2.b();
        if (b2 instanceof C0057a) {
            bundle.putString("auth_code", ((C0057a) b2).a());
        } else if (b2 instanceof com.etsy.android.lib.auth.external.a.b) {
            bundle.putString("auth_token", ((com.etsy.android.lib.auth.external.a.b) b2).a());
        }
        this.b.showLinkRegisterScreen(bundle);
    }

    public final void a(C0059f fVar) {
        p.b(fVar, "twoFactor");
        SignInFlow signInFlow = SignInFlow.REGULAR;
        Bundle bundle = new Bundle();
        q.b a2 = fVar.a();
        com.etsy.android.lib.auth.a.b c = a2.c();
        if (c != null) {
            bundle.putString("account_type_name", c.a());
            bundle.putString("account_id", c.b());
            com.etsy.android.lib.auth.external.a c2 = c.c();
            if (c2 instanceof C0057a) {
                bundle.putString("auth_code", ((C0057a) c2).a());
            } else if (c2 instanceof com.etsy.android.lib.auth.external.a.b) {
                bundle.putString("auth_token", ((com.etsy.android.lib.auth.external.a.b) c2).a());
            }
            signInFlow = SignInFlow.LINK;
        }
        this.b.showTwoFactor(bundle, a2.b(), a2.a(), signInFlow);
    }

    /* access modifiers changed from: protected */
    public io.reactivex.v<com.etsy.android.lib.auth.f.a> a(com.etsy.android.lib.auth.q.a aVar) {
        p.b(aVar, "success");
        io.reactivex.v<com.etsy.android.lib.auth.f.a> b2 = io.reactivex.v.b((Callable<? extends T>) new C0100a<Object>(aVar));
        p.a((Object) b2, "Single.fromCallable {\n  …nloading user\")\n        }");
        return b2;
    }

    public final void a(com.etsy.android.lib.auth.f.a aVar, h hVar) {
        p.b(aVar, ResponseConstants.STATE);
        p.b(hVar, "repository");
        if (aVar instanceof e) {
            Token a2 = ((e) aVar).a().a();
            p.a((Object) a2, "state.xAuthResult.accessToken");
            a(a2);
            this.b.onFetchedUser();
        } else if (aVar instanceof com.etsy.android.lib.auth.f.a.b) {
            com.etsy.android.lib.auth.f.a.b bVar = (com.etsy.android.lib.auth.f.a.b) aVar;
            com.etsy.android.lib.auth.e a3 = bVar.a();
            if (a3 instanceof b) {
                com.etsy.android.lib.auth.e a4 = bVar.a();
                if (a4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.lib.auth.ExternalAccountState.Link");
                }
                a((b) a4);
            } else if (a3 instanceof c) {
                com.etsy.android.lib.auth.e a5 = bVar.a();
                if (a5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.lib.auth.ExternalAccountState.Register");
                }
                a((c) a5);
            }
            hVar.b();
        } else if (aVar instanceof C0059f) {
            a((C0059f) aVar);
            hVar.b();
        }
    }
}
