package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;

public class DataEntryCheckoutActionManager extends CartCheckoutActionManager {
    public boolean canShowPaymentCredentials() {
        return true;
    }

    public DataEntryCheckoutActionManager(CartContext cartContext) {
        super(cartContext);
    }

    public CheckoutButtonContext getCheckoutButtonContext() {
        return new CheckoutButtonContext() {
            public boolean allowExpressCheckout() {
                return false;
            }

            public String getCheckoutButtonText() {
                return WishApplication.getInstance().getString(R.string.checkout);
            }

            public CheckoutButtonMode getCheckoutButtonMode() {
                return CheckoutButtonMode.BUTTON;
            }

            public String getCartAbandonModalActionText() {
                return WishApplication.getInstance().getString(R.string.checkout);
            }
        };
    }

    public void checkout(CartCheckoutUiController cartCheckoutUiController, boolean z) {
        if (this.mCartContext.getEffectivePaymentMode().equals("PaymentModeCommerceLoan") && (!ExperimentDataCenter.getInstance().canCheckoutWithCommerceLoan(this.mCartContext) || !this.mCartContext.hasCommerceLoanBillingInfo(false))) {
            cartCheckoutUiController.handleInvalidCommerceLoan();
        } else if (!this.mCartContext.hasValidShippingInfo() && this.mCartContext.getCart() != null) {
            cartCheckoutUiController.showShippingView(true);
        } else if (!this.mCartContext.hasValidBillingInfo()) {
            cartCheckoutUiController.showBillingView(true);
        } else if (this.mCartContext.isCommerceCashMissingValidShippingInfo()) {
            cartCheckoutUiController.showBillingView(true);
        } else {
            cartCheckoutUiController.showItemsView();
        }
    }
}
