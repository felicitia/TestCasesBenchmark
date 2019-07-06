package com.etsy.android.ui.core;

import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.requests.apiv3.ShippingDetailsEndpoint;
import io.reactivex.functions.g;
import kotlin.jvm.internal.p;

/* compiled from: ShippingDetailsRepository.kt */
public final class i {
    private final ShippingDetailsEndpoint a;
    private final v b;

    /* compiled from: ShippingDetailsRepository.kt */
    static final class a<T, R> implements g<T, R> {
        public static final a a = new a();

        a() {
        }

        /* renamed from: a */
        public final k apply(ListingShippingDetails listingShippingDetails) {
            p.b(listingShippingDetails, "it");
            return new com.etsy.android.ui.core.k.b(listingShippingDetails);
        }
    }

    /* compiled from: ShippingDetailsRepository.kt */
    static final class b<T, R> implements g<Throwable, k> {
        public static final b a = new b();

        b() {
        }

        /* renamed from: a */
        public final com.etsy.android.ui.core.k.a apply(Throwable th) {
            p.b(th, "it");
            return new com.etsy.android.ui.core.k.a();
        }
    }

    public i(ShippingDetailsEndpoint shippingDetailsEndpoint, v vVar) {
        p.b(shippingDetailsEndpoint, "endpoint");
        p.b(vVar, "session");
        this.a = shippingDetailsEndpoint;
        this.b = vVar;
    }

    public final io.reactivex.v<k> a(long j, String str, String str2) {
        p.b(str, "countryCode");
        io.reactivex.v<k> c = this.a.getShippingDetails(j, str, str2, this.b.e()).b((g<? super T, ? extends R>) a.a).c(b.a);
        p.a((Object) c, "endpoint.getShippingDeta…hippingDetailsFailure() }");
        return c;
    }
}
