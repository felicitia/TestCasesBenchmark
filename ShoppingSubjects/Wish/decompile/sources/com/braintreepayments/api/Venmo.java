package com.braintreepayments.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.AppHelper;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.internal.SignatureVerification;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.VenmoAccountBuilder;
import com.braintreepayments.api.models.VenmoAccountNonce;

public class Venmo {
    public static boolean isVenmoInstalled(Context context) {
        return AppHelper.isIntentAvailable(context, getVenmoIntent()) && SignatureVerification.isSignatureValid(context, "com.venmo", "CN=Andrew Kortina,OU=Engineering,O=Venmo,L=Philadelphia,ST=PA,C=US", "CN=Andrew Kortina,OU=Engineering,O=Venmo,L=Philadelphia,ST=PA,C=US", -129711843);
    }

    private static Intent getVenmoIntent() {
        return new Intent().setComponent(new ComponentName("com.venmo", "com.venmo.controller.SetupMerchantActivity"));
    }

    private static boolean shouldVault(Context context) {
        return BraintreeSharedPreferences.getSharedPreferences(context).getBoolean("com.braintreepayments.api.Venmo.VAULT_VENMO_KEY", false);
    }

    static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            braintreeFragment.sendAnalyticsEvent("pay-with-venmo.app-switch.success");
            String stringExtra = intent.getStringExtra("com.braintreepayments.api.EXTRA_PAYMENT_METHOD_NONCE");
            if (!shouldVault(braintreeFragment.getApplicationContext()) || !(braintreeFragment.getAuthorization() instanceof ClientToken)) {
                String stringExtra2 = intent.getStringExtra("com.braintreepayments.api.EXTRA_USER_NAME");
                braintreeFragment.postCallback((PaymentMethodNonce) new VenmoAccountNonce(stringExtra, stringExtra2, stringExtra2));
                return;
            }
            vault(braintreeFragment, stringExtra);
        } else if (i == 0) {
            braintreeFragment.sendAnalyticsEvent("pay-with-venmo.app-switch.canceled");
        }
    }

    private static void vault(final BraintreeFragment braintreeFragment, String str) {
        TokenizationClient.tokenize(braintreeFragment, new VenmoAccountBuilder().nonce(str), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                braintreeFragment.postCallback(paymentMethodNonce);
                braintreeFragment.sendAnalyticsEvent("pay-with-venmo.vault.success");
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
                braintreeFragment.sendAnalyticsEvent("pay-with-venmo.vault.failed");
            }
        });
    }
}
