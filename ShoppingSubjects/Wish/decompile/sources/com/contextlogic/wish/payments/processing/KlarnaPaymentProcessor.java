package com.contextlogic.wish.payments.processing;

import android.net.Uri;
import android.net.Uri.Builder;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;

public class KlarnaPaymentProcessor extends CartPaymentProcessor {
    public static String getKlarnaPurchaseConfirmationPath() {
        return "/m/klarna/confirmation";
    }

    public KlarnaPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public static String getPaymentMethodImageUrl(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://cdn.klarna.com/1.0/shared/image/generic/badge/");
        sb.append(ConfigDataCenter.getInstance().getKlarnaCountryCode());
        sb.append("/checkout/long-blue.png?width=");
        sb.append(Math.min(i, 880));
        return sb.toString();
    }

    public static String getKlarnaCheckoutUrl(CartContext cartContext, boolean z) {
        String format = String.format("https://%s/m/klarna/checkout", new Object[]{ServerConfig.getInstance().getServerHost()});
        try {
            Builder buildUpon = Uri.parse(format).buildUpon();
            String checkoutOfferId = cartContext.getCheckoutOfferId();
            if (checkoutOfferId != null) {
                buildUpon.appendQueryParameter("checkout_offer_id", checkoutOfferId);
            }
            if (ExperimentDataCenter.getInstance().canCheckoutWithKlarnaNative(cartContext)) {
                buildUpon.appendQueryParameter("show_in_modal", "false");
                if (!cartContext.hasValidBillingInfo() || !z) {
                    buildUpon.appendQueryParameter("express_checkout", "false");
                } else {
                    buildUpon.appendQueryParameter("express_checkout", "true");
                }
            }
            return buildUpon.toString();
        } catch (Throwable unused) {
            return format;
        }
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        handleSuccessfulPayment();
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.buyUrl = getKlarnaCheckoutUrl(this.mServiceFragment.getCartContext(), true);
        successListener.onSuccess(this, paymentContext);
    }
}
