package com.etsy.android.ui.core;

import dagger.a;

/* compiled from: DetailedImageFragment_MembersInjector */
public final class d implements a<DetailedImageFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;

    public d(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<DetailedImageFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        return new d(aVar);
    }

    /* renamed from: a */
    public void injectMembers(DetailedImageFragment detailedImageFragment) {
        if (detailedImageFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        detailedImageFragment.fileSupport = (com.etsy.android.lib.util.b.a) this.b.b();
    }
}
