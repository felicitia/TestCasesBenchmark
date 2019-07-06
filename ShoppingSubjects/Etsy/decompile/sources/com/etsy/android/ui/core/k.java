package com.etsy.android.ui.core;

import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import kotlin.jvm.internal.p;

/* compiled from: ShippingDetailsRepository.kt */
public abstract class k {

    /* compiled from: ShippingDetailsRepository.kt */
    public static final class a extends k {
        public a() {
            super(null);
        }
    }

    /* compiled from: ShippingDetailsRepository.kt */
    public static final class b extends k {
        private final ListingShippingDetails a;

        public b(ListingShippingDetails listingShippingDetails) {
            p.b(listingShippingDetails, "listingShippingDetails");
            super(null);
            this.a = listingShippingDetails;
        }

        public final ListingShippingDetails a() {
            return this.a;
        }
    }

    private k() {
    }

    public /* synthetic */ k(o oVar) {
        this();
    }
}
