package com.etsy.android.ui.core;

import com.etsy.android.lib.util.sharedprefs.b;
import dagger.a;

/* compiled from: ListingFragment_MembersInjector */
public final class h implements a<ListingFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<com.etsy.android.lib.util.b.a> b;
    private final javax.a.a<i> c;
    private final javax.a.a<b> d;
    private final javax.a.a<com.etsy.android.lib.f.a> e;

    public h(javax.a.a<com.etsy.android.lib.util.b.a> aVar, javax.a.a<i> aVar2, javax.a.a<b> aVar3, javax.a.a<com.etsy.android.lib.f.a> aVar4) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static a<ListingFragment> a(javax.a.a<com.etsy.android.lib.util.b.a> aVar, javax.a.a<i> aVar2, javax.a.a<b> aVar3, javax.a.a<com.etsy.android.lib.f.a> aVar4) {
        return new h(aVar, aVar2, aVar3, aVar4);
    }

    /* renamed from: a */
    public void injectMembers(ListingFragment listingFragment) {
        if (listingFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        listingFragment.fileSupport = (com.etsy.android.lib.util.b.a) this.b.b();
        listingFragment.shippingDetailsRepository = (i) this.c.b();
        listingFragment.sharedPreferencesProvider = (b) this.d.b();
        listingFragment.schedulers = (com.etsy.android.lib.f.a) this.e.b();
    }
}
