package com.etsy.android.ui.cart;

import dagger.a;

/* compiled from: SavedCartItemsFragment_MembersInjector */
public final class g implements a<SavedCartItemsFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.c.g> b;

    public g(javax.a.a<com.etsy.android.lib.c.g> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<SavedCartItemsFragment> a(javax.a.a<com.etsy.android.lib.c.g> aVar) {
        return new g(aVar);
    }

    /* renamed from: a */
    public void injectMembers(SavedCartItemsFragment savedCartItemsFragment) {
        if (savedCartItemsFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        savedCartItemsFragment.retrofit = (com.etsy.android.lib.c.g) this.b.b();
    }
}
