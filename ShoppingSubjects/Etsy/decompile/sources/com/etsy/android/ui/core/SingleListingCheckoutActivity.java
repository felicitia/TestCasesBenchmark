package com.etsy.android.ui.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.SingleListingCheckout;
import com.etsy.android.ui.nav.a;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;
import java.util.HashMap;
import java.util.List;

public class SingleListingCheckoutActivity extends DialogActivity {
    public static final String CHECKED_OUT_SINGLE_LISTING = "checked_out_single_listing";
    public static final String PARAM_LISTING_ID = "listing_id";
    public static final String PARAM_LISTING_INVENTORY_ID = "listing_inventory_id";
    public static final String PARAM_LISTING_VARIATION = "listing_variation";
    public static final String PARAM_PAYMENT_METHOD = "payment_method";
    public static final String PARAM_QUANTITY = "quantity";

    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        a a = e.a((FragmentActivity) this).d().a(buildInterceptingDismissListener(onDismissListener));
        if (isStandalonePayPal()) {
            a.g();
        } else {
            a.f();
        }
    }

    private boolean isStandalonePayPal() {
        boolean z = false;
        if (getIntent() == null || getIntent().getExtras() == null || !getIntent().hasExtra("listing")) {
            return false;
        }
        Listing listing = (Listing) getIntent().getExtras().get("listing");
        if (listing == null || !listing.isSingleListingCheckoutAvailable()) {
            return false;
        }
        SingleListingCheckout singleListingCheckout = listing.getSingleListingCheckout();
        if (singleListingCheckout.acceptsPayPal() && !singleListingCheckout.acceptsMultiplePaymentMethods()) {
            z = true;
        }
        return z;
    }

    private OnDismissListener buildInterceptingDismissListener(OnDismissListener onDismissListener) {
        return new n(this, onDismissListener);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$buildInterceptingDismissListener$0$SingleListingCheckoutActivity(OnDismissListener onDismissListener, DialogInterface dialogInterface) {
        onDismissListener.onDismiss(dialogInterface);
        Listing listing = (Listing) getIntent().getExtras().get("listing");
        w analyticsContext = getAnalyticsContext();
        if (analyticsContext != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsLogAttribute.LISTING_ID, listing.getListingId());
            if (!isStandalonePayPal()) {
                hashMap.put(AnalyticsLogAttribute.PAYMENT_METHODS, buildPaymentOptionsParam(listing));
            }
            analyticsContext.a("closed_paypal_overlay", hashMap);
        }
    }

    private String buildPaymentOptionsParam(Listing listing) {
        List paymentOptions = listing.getSingleListingCheckout().getPaymentOptions();
        int size = paymentOptions.size();
        String str = "";
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(((PaymentOption) paymentOptions.get(i)).getPaymentMethod());
            sb.append(",");
            str = sb.toString();
        }
        return str.substring(0, str.length() - 1);
    }
}
