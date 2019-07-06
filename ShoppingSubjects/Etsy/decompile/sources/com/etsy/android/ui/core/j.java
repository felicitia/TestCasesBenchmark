package com.etsy.android.ui.core;

import com.etsy.android.lib.core.v;
import com.etsy.android.lib.requests.apiv3.ShippingDetailsEndpoint;
import dagger.internal.c;
import javax.a.a;

/* compiled from: ShippingDetailsRepository_Factory */
public final class j implements c<i> {
    static final /* synthetic */ boolean a = true;
    private final a<ShippingDetailsEndpoint> b;
    private final a<v> c;

    public j(a<ShippingDetailsEndpoint> aVar, a<v> aVar2) {
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

    /* renamed from: a */
    public i b() {
        return new i((ShippingDetailsEndpoint) this.b.b(), (v) this.c.b());
    }

    public static c<i> a(a<ShippingDetailsEndpoint> aVar, a<v> aVar2) {
        return new j(aVar, aVar2);
    }
}
