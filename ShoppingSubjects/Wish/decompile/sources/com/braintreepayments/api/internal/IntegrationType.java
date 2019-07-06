package com.braintreepayments.api.internal;

import android.app.Activity;

public class IntegrationType {
    public static String get(Activity activity) {
        try {
            if (Class.forName("com.braintreepayments.api.BraintreePaymentActivity").isInstance(activity)) {
                return "dropin";
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            if (Class.forName("com.braintreepayments.api.dropin.DropInActivity").isInstance(activity)) {
                return "dropin2";
            }
        } catch (ClassNotFoundException unused2) {
        }
        return "custom";
    }
}
