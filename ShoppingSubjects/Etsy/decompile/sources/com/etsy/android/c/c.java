package com.etsy.android.c;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.requests.apiv3.ShippingDetailsEndpoint;
import kotlin.jvm.internal.p;

/* compiled from: ListingFragmentModule.kt */
public final class c {
    public final ShippingDetailsEndpoint a(e eVar) {
        p.b(eVar, "configuredRetrofit");
        Object a = eVar.a().a(ShippingDetailsEndpoint.class);
        p.a(a, "configuredRetrofit.v3mos…ailsEndpoint::class.java)");
        return (ShippingDetailsEndpoint) a;
    }
}
