package com.contextlogic.wish.payments;

import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCartSummaryItem;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.payments.CartContext.CartType;
import java.util.ArrayList;

public class CommerceLoanCartContext extends CartContext {
    private WishCommerceLoanCart mCommerceLoanCart;

    public boolean isCommerceLoanCartContext() {
        return true;
    }

    public void updateData(WishCommerceLoanCart wishCommerceLoanCart, WishUserBillingInfo wishUserBillingInfo) {
        this.mCommerceLoanCart = wishCommerceLoanCart;
        updateData((WishCart) null, (WishShippingInfo) null, wishUserBillingInfo, false);
    }

    public WishCommerceLoanCart getCommerceLoanCart() {
        return this.mCommerceLoanCart;
    }

    public String getCurrencyCode() {
        return (this.mCommerceLoanCart == null || ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency()) ? "USD" : this.mCommerceLoanCart.getAmount().getLocalizedCurrencyCode();
    }

    public CartType getCartType() {
        return CartType.COMMERCE_LOAN;
    }

    public PaymentProcessor getPaymentProcessor() {
        if (this.mCommerceLoanCart != null) {
            return this.mCommerceLoanCart.getPaymentProcessor();
        }
        return PaymentProcessor.Unknown;
    }

    public boolean getRequiresFullBillingAddress() {
        return this.mCommerceLoanCart != null && this.mCommerceLoanCart.getRequiresFullBillingAddress();
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        if (this.mCommerceLoanCart != null) {
            return this.mCommerceLoanCart.getSummaryItems(str);
        }
        return null;
    }

    public WishLocalizedCurrencyValue getTotal() {
        if (this.mCommerceLoanCart != null) {
            return this.mCommerceLoanCart.getAmount();
        }
        return null;
    }
}
