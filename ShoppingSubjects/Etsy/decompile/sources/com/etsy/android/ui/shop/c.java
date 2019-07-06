package com.etsy.android.ui.shop;

import dagger.a;

/* compiled from: ShopHomeFragment_MembersInjector */
public final class c implements a<ShopHomeFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;

    public c(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<ShopHomeFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        return new c(aVar);
    }

    /* renamed from: a */
    public void injectMembers(ShopHomeFragment shopHomeFragment) {
        if (shopHomeFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com.etsy.android.lib.shophome.vertical.a.a(shopHomeFragment, this.b);
    }
}
