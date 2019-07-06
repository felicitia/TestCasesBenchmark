package com.etsy.android.ui.convos;

import dagger.a;

/* compiled from: ManufacturerProjectFragment_MembersInjector */
public final class o implements a<ManufacturerProjectFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;

    public o(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<ManufacturerProjectFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar) {
        return new o(aVar);
    }

    /* renamed from: a */
    public void injectMembers(ManufacturerProjectFragment manufacturerProjectFragment) {
        if (manufacturerProjectFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        manufacturerProjectFragment.fileSupport = (com.etsy.android.lib.util.b.a) this.b.b();
    }
}
