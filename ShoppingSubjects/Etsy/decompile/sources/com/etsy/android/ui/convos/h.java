package com.etsy.android.ui.convos;

import dagger.a;

/* compiled from: ConvoComposeFragment_MembersInjector */
public final class h implements a<ConvoComposeFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<i> b;
    private final javax.a.a<com.etsy.android.lib.f.a> c;

    public h(javax.a.a<i> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2) {
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

    public static a<ConvoComposeFragment> a(javax.a.a<i> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2) {
        return new h(aVar, aVar2);
    }

    /* renamed from: a */
    public void injectMembers(ConvoComposeFragment convoComposeFragment) {
        if (convoComposeFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        convoComposeFragment.convoRepository = (i) this.b.b();
        convoComposeFragment.schedulers = (com.etsy.android.lib.f.a) this.c.b();
    }
}
