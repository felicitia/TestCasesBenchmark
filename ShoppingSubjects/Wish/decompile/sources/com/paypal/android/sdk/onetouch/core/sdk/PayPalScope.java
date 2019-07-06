package com.paypal.android.sdk.onetouch.core.sdk;

public enum PayPalScope {
    FUTURE_PAYMENTS("https://uri.paypal.com/services/payments/futurepayments"),
    PROFILE("profile"),
    PAYPAL_ATTRIBUTES("https://uri.paypal.com/services/paypalattributes"),
    OPENID("openid"),
    EMAIL("email"),
    ADDRESS("address"),
    PHONE("phone");
    
    private String mScopeUri;

    private PayPalScope(String str) {
        this.mScopeUri = str;
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }
}
