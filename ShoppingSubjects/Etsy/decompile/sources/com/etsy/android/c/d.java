package com.etsy.android.c;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.requests.apiv3.ShippingDetailsEndpoint;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ListingFragmentModule_ProvidesShippingDetailsEndpointFactory */
public final class d implements c<ShippingDetailsEndpoint> {
    static final /* synthetic */ boolean a = true;
    private final c b;
    private final a<e> c;

    public d(c cVar, a<e> aVar) {
        if (a || cVar != null) {
            this.b = cVar;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public ShippingDetailsEndpoint b() {
        return (ShippingDetailsEndpoint) f.a(this.b.a((e) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<ShippingDetailsEndpoint> a(c cVar, a<e> aVar) {
        return new d(cVar, aVar);
    }
}
