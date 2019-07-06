package com.etsy.android.ui.convos.convolistredesign;

import dagger.a;

/* compiled from: ConvosListFragment_MembersInjector */
public final class l implements a<ConvosListFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<m> b;

    public l(javax.a.a<m> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<ConvosListFragment> a(javax.a.a<m> aVar) {
        return new l(aVar);
    }

    /* renamed from: a */
    public void injectMembers(ConvosListFragment convosListFragment) {
        if (convosListFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        convosListFragment.presenter = (m) this.b.b();
    }
}
