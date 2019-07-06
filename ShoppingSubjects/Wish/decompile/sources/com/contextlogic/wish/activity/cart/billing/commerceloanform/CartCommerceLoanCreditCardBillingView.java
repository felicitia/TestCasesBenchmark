package com.contextlogic.wish.activity.cart.billing.commerceloanform;

import android.os.Bundle;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.CartBillingView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.payments.CartContext;
import java.util.Collections;
import java.util.List;

public class CartCommerceLoanCreditCardBillingView extends CartBillingView {
    /* access modifiers changed from: protected */
    public boolean isForCommerceLoan() {
        return true;
    }

    public void showCreditCardPaymentFormView() {
    }

    public CartCommerceLoanCreditCardBillingView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle, boolean z) {
        super(cartFragment, cartActivity, bundle, z, null);
    }

    /* access modifiers changed from: protected */
    public void activatePaymentSections(Bundle bundle) {
        resetSelectedViews();
        CartContext cartContext = getCartFragment().getCartContext();
        for (CartBillingSection sectionVisible : CartBillingSection.values()) {
            this.mTabHeaderView.setSectionVisible(sectionVisible, false);
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithCreditCard(cartContext)) {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.CREDIT_CARD, true);
        }
        this.mTabHeaderView.selectSection(CartBillingSection.CREDIT_CARD);
    }

    public List<WishAnalyticsEvent> getWishAnalyticImpressionEvents() {
        return Collections.singletonList(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_BILLING_COMMERCE_LOAN_CC);
    }

    /* access modifiers changed from: protected */
    public CartBillingSection getParentBillingSection() {
        return CartBillingSection.COMMERCE_LOAN;
    }
}
