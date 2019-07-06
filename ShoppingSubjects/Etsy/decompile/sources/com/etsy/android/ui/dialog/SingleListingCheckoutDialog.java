package com.etsy.android.ui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.SingleListingCheckout;
import com.etsy.android.lib.models.apiv3.cart.SingleListingCart;
import com.etsy.android.stylekit.EtsyButton;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.HashMap;
import java.util.List;

public class SingleListingCheckoutDialog extends EtsyFragment {
    private Button mProceedCheckoutButton;
    /* access modifiers changed from: private */
    public PaymentOption mSelectedPaymentOption;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_single_listing_checkout, viewGroup, false);
        buildView(inflate);
        return inflate;
    }

    private void buildView(View view) {
        Context context = view.getContext();
        Bundle extras = getActivity().getIntent().getExtras();
        final Listing listing = (Listing) extras.get("listing");
        SingleListingCheckout singleListingCheckout = listing.getSingleListingCheckout();
        List paymentOptions = singleListingCheckout.getPaymentOptions();
        this.mProceedCheckoutButton = (EtsyButton) view.findViewById(R.id.single_listing_checkout_proceed);
        this.mProceedCheckoutButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SingleListingCheckoutDialog.this.proceedToCheckout(listing);
                SingleListingCheckoutDialog.this.getAnalyticsContext().a("single_listing_checkout_tapped_buy_button", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.LISTING_ID, listing.getListingId());
                        put(AnalyticsLogAttribute.PAYMENT_METHOD, SingleListingCheckoutDialog.this.mSelectedPaymentOption.getPaymentMethod());
                    }
                });
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.single_listing_checkout_payment_methods);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                SingleListingCheckoutDialog.this.mSelectedPaymentOption = (PaymentOption) radioGroup.findViewById(i).getTag();
                SingleListingCheckoutDialog.this.updateProceedButton(SingleListingCheckoutDialog.this.mSelectedPaymentOption);
            }
        });
        if (singleListingCheckout.isInternational() && extras.get("single_listing_cart") != null) {
            view.findViewById(R.id.single_listing_checkout_international_container).setVisibility(0);
            populateInternationalLineItems(view, (SingleListingCart) extras.get("single_listing_cart"));
        }
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-1, context.getResources().getDimensionPixelSize(R.dimen.msco_radio_button_payment_option_height));
        int size = paymentOptions.size();
        for (int i = 0; i < size; i++) {
            PaymentOption paymentOption = (PaymentOption) paymentOptions.get(i);
            if (paymentOption.isSelected()) {
                this.mSelectedPaymentOption = paymentOption;
            }
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(marginLayoutParams);
            radioButton.setContentDescription(paymentOption.getLabel());
            radioButton.setTag(paymentOption);
            Drawable drawable = paymentOption.isPayPal() ? ContextCompat.getDrawable(context, R.drawable.cc_paypal) : paymentOption.isCreditCard() ? ContextCompat.getDrawable(context, R.drawable.cc_all) : paymentOption.isAndroidPay() ? ContextCompat.getDrawable(context, R.drawable.cc_android_pay) : paymentOption.isIdeal() ? ContextCompat.getDrawable(context, R.drawable.cc_ideal) : null;
            if (drawable == null) {
                radioButton.setText(paymentOption.getLabel());
            } else {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }
            radioGroup.addView(radioButton);
            radioButton.setChecked(paymentOption.isSelected());
        }
        updateProceedButton(this.mSelectedPaymentOption);
    }

    private void populateInternationalLineItems(@NonNull View view, @NonNull SingleListingCart singleListingCart) {
        Context context = view.getContext();
        ((TextView) view.findViewById(R.id.single_listing_checkout_item_total)).setText(singleListingCart.getItemTotal().getCurrencyFormattedShort());
        View findViewById = view.findViewById(R.id.single_listing_checkout_shipping_container);
        if (singleListingCart.hasFreeShipping()) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            ((TextView) view.findViewById(R.id.single_listing_checkout_shipping)).setText(singleListingCart.getShippingTotal().getCurrencyFormattedShort());
        }
        TextView textView = (TextView) view.findViewById(R.id.single_listing_checkout_shipping_destination);
        if (TextUtils.isEmpty(singleListingCart.getShippingDestination())) {
            textView.setVisibility(8);
        } else {
            textView.setText(context.getString(R.string.single_listing_checkout_shipping_destination, new Object[]{singleListingCart.getShippingDestination()}));
            textView.setVisibility(0);
        }
        ((TextView) view.findViewById(R.id.single_listing_checkout_total)).setText(singleListingCart.getTotal().getCurrencyFormattedShort());
        view.findViewById(R.id.single_listing_checkout_vat_description).setVisibility(singleListingCart.hasVAT() ? 0 : 8);
        TextView textView2 = (TextView) view.findViewById(R.id.single_listing_checkout_transparent_price_msg);
        if (!TextUtils.isEmpty(singleListingCart.getTransparentPriceMsg())) {
            textView2.setText(EtsyLinkify.a(context, Html.fromHtml(singleListingCart.getTransparentPriceMsg()), true, true, R.color.sk_gray_50, null));
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        TextView textView3 = (TextView) view.findViewById(R.id.single_listing_checkout_conversion_msg);
        if (singleListingCart.shouldShowCurrencyConversionNotice()) {
            textView3.setText(context.getString(R.string.single_listing_checkout_conversion_msg, new Object[]{singleListingCart.getItemTotal().getCurrencyFormattedShort(), singleListingCart.getShopName()}));
            textView3.setVisibility(0);
            return;
        }
        textView3.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void updateProceedButton(PaymentOption paymentOption) {
        if (paymentOption == null || !"paypal".equals(paymentOption.getPaymentMethod())) {
            this.mProceedCheckoutButton.setText(getContext().getString(R.string.listing_proceed_to_checkout));
        } else {
            this.mProceedCheckoutButton.setText(getContext().getString(R.string.listing_proceed_to_checkout_paypal));
        }
    }

    /* access modifiers changed from: private */
    public void proceedToCheckout(final Listing listing) {
        Bundle extras = getActivity().getIntent().getExtras();
        e.a((Fragment) this).a().a(listing, extras.getString("quantity"), this.mSelectedPaymentOption, extras.getString(ResponseConstants.OFFERING_ID));
        getAnalyticsContext().a("show_single_listing_checkout_webview", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.LISTING_ID, listing.getListingId());
                put(AnalyticsLogAttribute.PAYMENT_METHOD, SingleListingCheckoutDialog.this.mSelectedPaymentOption.getPaymentMethod());
            }
        });
    }
}
