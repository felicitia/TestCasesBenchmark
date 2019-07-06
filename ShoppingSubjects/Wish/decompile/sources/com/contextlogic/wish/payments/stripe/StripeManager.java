package com.contextlogic.wish.payments.stripe;

import com.contextlogic.wish.api.model.WishCachedBillingInfo;

public class StripeManager {
    private static StripeManager sInstance = new StripeManager();
    private WishCachedBillingInfo mCachedBillingInfo;

    public static StripeManager getInstance() {
        return sInstance;
    }

    public void setCachedBillingInfo(WishCachedBillingInfo wishCachedBillingInfo) {
        this.mCachedBillingInfo = wishCachedBillingInfo;
    }

    public WishCachedBillingInfo getCachedBillingInfo() {
        return this.mCachedBillingInfo;
    }
}
