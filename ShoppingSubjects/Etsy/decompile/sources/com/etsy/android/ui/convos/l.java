package com.etsy.android.ui.convos;

import dagger.a;

/* compiled from: ConvoThreadFragment_MembersInjector */
public final class l implements a<ConvoThreadFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;

    public l(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<ConvoThreadFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        return new l(aVar);
    }

    /* renamed from: a */
    public void injectMembers(ConvoThreadFragment convoThreadFragment) {
        if (convoThreadFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        convoThreadFragment.fileSupport = (com.etsy.android.lib.util.b.a) this.b.b();
    }
}
