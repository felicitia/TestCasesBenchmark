package com.contextlogic.wish.payments.google;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.GooglePayment;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.google.android.gms.wallet.CardRequirements;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.ShippingAddressRequirements;
import com.google.android.gms.wallet.TransactionInfo;
import com.google.android.gms.wallet.TransactionInfo.Builder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class GooglePayManager {
    private static GooglePayManager sInstance = new GooglePayManager();
    /* access modifiers changed from: private */
    public boolean mGooglePayReady = false;

    private GooglePayManager() {
    }

    public static GooglePayManager getInstance() {
        return sInstance;
    }

    public void setGooglePayReady(boolean z) {
        this.mGooglePayReady = z;
    }

    private TransactionInfo createGooglePayTransactionInfo(CartContext cartContext) {
        Builder newBuilder = TransactionInfo.newBuilder();
        if (ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency()) {
            newBuilder.setCurrencyCode("USD");
            newBuilder.setTotalPrice(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(cartContext.getTotal().getUsdValue())}));
        } else {
            newBuilder.setCurrencyCode(cartContext.getCurrencyCode());
            newBuilder.setTotalPrice(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(cartContext.getTotal().getValue())}));
        }
        newBuilder.setTotalPriceStatus(3);
        return newBuilder.build();
    }

    public PaymentDataRequest createPaymentDataRequest(CartContext cartContext, BraintreeFragment braintreeFragment) {
        final PaymentDataRequest.Builder addAllowedPaymentMethod = PaymentDataRequest.newBuilder().setPhoneNumberRequired(true).setEmailRequired(true).setShippingAddressRequired(true).setShippingAddressRequirements(ShippingAddressRequirements.newBuilder().addAllowedCountryCodes(Arrays.asList(new String[]{"AU", "CA", "US", "GB", "SG", "IT", "ES", "FR", "BR", "MX", "DE", "SE", "CH", "NO", "NL", "DK", "PR", "PT", "CR", "IE", "PL", "AT", "BE"})).build()).setTransactionInfo(createGooglePayTransactionInfo(cartContext)).addAllowedPaymentMethod(1).addAllowedPaymentMethod(2);
        if (cartContext.getPaymentProcessor() == PaymentProcessor.Braintree) {
            GooglePayment.getTokenizationParameters(braintreeFragment, new TokenizationParametersListener() {
                public void onResult(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, Collection<Integer> collection) {
                    addAllowedPaymentMethod.setCardRequirements(CardRequirements.newBuilder().setBillingAddressRequired(true).addAllowedCardNetworks(collection).build()).setPaymentMethodTokenizationParameters(paymentMethodTokenizationParameters);
                }
            });
        } else {
            addAllowedPaymentMethod.setCardRequirements(CardRequirements.newBuilder().setBillingAddressRequired(true).addAllowedCardNetworks(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5), Integer.valueOf(4)})).build()).setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters.newBuilder().setPaymentMethodTokenizationType(1).addParameter("gateway", "stripe").addParameter("stripe:publishableKey", ConfigDataCenter.getInstance().getPaymentProcessorData().getStripeKey()).addParameter("stripe:version", "1.40.0").build());
        }
        return addAllowedPaymentMethod.build();
    }

    public String getGooglePayErrorMessage(int i) {
        if (i == 409 || i == 411) {
            return WishApplication.getInstance().getString(R.string.google_pay_account_unavailable_error);
        }
        if (i == 405 || i == 402 || i == 412 || i == 1 || i == 2 || i == 3 || i == 9 || i == 16 || i == 11 || i == 5) {
            return WishApplication.getInstance().getString(R.string.google_pay_unavailable_error);
        }
        return WishApplication.getInstance().getString(R.string.google_pay_error);
    }

    public void updateGooglePayReadyStatus(ServiceFragment serviceFragment) {
        serviceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoadFailed(String str) {
            }

            public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                GooglePayment.isReadyToPay(braintreeFragment, new BraintreeResponseListener<Boolean>() {
                    public void onResponse(Boolean bool) {
                        GooglePayManager.this.mGooglePayReady = bool.booleanValue();
                    }
                });
            }
        });
    }
}
