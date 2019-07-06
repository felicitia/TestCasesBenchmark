package com.contextlogic.wish.payments;

import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCartSummaryItem;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.payments.CartContext.CartType;
import java.util.ArrayList;

public class CommerceCashCartContext extends CartContext {
    private WishCommerceCashCart mCommerceCashCart;

    public void updateData(WishCommerceCashCart wishCommerceCashCart, WishUserBillingInfo wishUserBillingInfo) {
        updateData(wishCommerceCashCart, wishUserBillingInfo, null);
    }

    public void updateData(WishCommerceCashCart wishCommerceCashCart, WishUserBillingInfo wishUserBillingInfo, WishShippingInfo wishShippingInfo) {
        this.mCommerceCashCart = wishCommerceCashCart;
        updateData((WishCart) null, wishShippingInfo, wishUserBillingInfo, false);
    }

    public WishCommerceCashCart getCommerceCashCart() {
        return this.mCommerceCashCart;
    }

    public String getCurrencyCode() {
        return (this.mCommerceCashCart == null || ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency()) ? "USD" : this.mCommerceCashCart.getAmount().getLocalizedCurrencyCode();
    }

    public CartType getCartType() {
        return CartType.COMMERCE_CASH;
    }

    public PaymentProcessor getPaymentProcessor() {
        if (this.mCommerceCashCart != null) {
            return this.mCommerceCashCart.getPaymentProcessor();
        }
        return PaymentProcessor.Unknown;
    }

    public boolean getRequiresFullBillingAddress() {
        if (this.mCommerceCashCart != null) {
            return this.mCommerceCashCart.getRequiresFullBillingAddress();
        }
        return false;
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        if (this.mCommerceCashCart != null) {
            return this.mCommerceCashCart.getSummaryItems(str);
        }
        return null;
    }

    public WishLocalizedCurrencyValue getTotal() {
        if (this.mCommerceCashCart != null) {
            return this.mCommerceCashCart.getAmount();
        }
        return null;
    }

    public ArrayList<PaymentMode> getSupportedPaymentModes() {
        return getCommerceCashCart().getSupportedPaymentModes();
    }
}
