package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.AndroidPayException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import org.json.JSONException;

@Deprecated
public class AndroidPay {
    @Deprecated
    public static void tokenize(BraintreeFragment braintreeFragment, FullWallet fullWallet, Cart cart) {
        try {
            braintreeFragment.postCallback((PaymentMethodNonce) AndroidPayCardNonce.fromFullWallet(fullWallet, cart));
            braintreeFragment.sendAnalyticsEvent("android-pay.nonce-received");
        } catch (JSONException unused) {
            braintreeFragment.sendAnalyticsEvent("android-pay.failed");
            try {
                braintreeFragment.postCallback((Exception) ErrorWithResponse.fromJson(fullWallet.getPaymentMethodToken().getToken()));
            } catch (JSONException e) {
                braintreeFragment.postCallback((Exception) e);
            }
        }
    }

    static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            if (intent.hasExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET")) {
                braintreeFragment.sendAnalyticsEvent("android-pay.authorized");
                tokenize(braintreeFragment, (FullWallet) intent.getParcelableExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET"), (Cart) intent.getParcelableExtra("com.braintreepayments.api.EXTRA_CART"));
            }
        } else if (i == 0) {
            braintreeFragment.sendAnalyticsEvent("android-pay.canceled");
        } else {
            if (intent != null) {
                if (intent.hasExtra("com.braintreepayments.api.EXTRA_ERROR")) {
                    braintreeFragment.postCallback((Exception) new AndroidPayException(intent.getStringExtra("com.braintreepayments.api.EXTRA_ERROR")));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Android Pay error code: ");
                    sb.append(intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", -1));
                    sb.append(" see https://developers.google.com/android/reference/com/google/android/gms/wallet/WalletConstants ");
                    sb.append("for more details");
                    braintreeFragment.postCallback((Exception) new AndroidPayException(sb.toString()));
                }
            }
            braintreeFragment.sendAnalyticsEvent("android-pay.failed");
        }
    }
}
