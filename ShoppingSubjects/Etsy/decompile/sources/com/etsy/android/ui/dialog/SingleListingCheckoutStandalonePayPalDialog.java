package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.stylekit.EtsyButton;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class SingleListingCheckoutStandalonePayPalDialog extends EtsyFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_single_listing_checkout_standalone_paypal, viewGroup, false);
        buildView(inflate);
        return inflate;
    }

    private void buildView(View view) {
        final Listing listing = (Listing) getActivity().getIntent().getExtras().get("listing");
        ((EtsyButton) view.findViewById(R.id.single_listing_checkout_paypal_checkout)).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SingleListingCheckoutStandalonePayPalDialog.this.proceedToCheckout(listing);
            }
        });
    }

    /* access modifiers changed from: private */
    public void proceedToCheckout(Listing listing) {
        Bundle extras = getActivity().getIntent().getExtras();
        PaymentOption paymentOption = (PaymentOption) listing.getSingleListingCheckout().getPaymentOptions().get(0);
        e.a((Fragment) this).a().a(listing, extras.getString("quantity"), paymentOption, extras.getString(ResponseConstants.OFFERING_ID));
    }
}
