package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;

public class SignupFlowCheckoutActionManager extends CartCheckoutActionManager {
    public boolean canShowPaymentCredentials() {
        return true;
    }

    public SignupFlowCheckoutActionManager(CartContext cartContext) {
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
                if (!SignupFlowCheckoutActionManager.this.mCartContext.hasValidShippingInfo()) {
                    return WishApplication.getInstance().getString(R.string.next);
                }
                if (!SignupFlowCheckoutActionManager.this.mCartContext.hasValidBillingInfo()) {
                    return WishApplication.getInstance().getString(R.string.claim_gift);
                }
                return WishApplication.getInstance().getString(R.string.next);
            }
        };
    }

    public void checkout(CartCheckoutUiController cartCheckoutUiController, boolean z) {
        if (!this.mCartContext.hasValidShippingInfo()) {
            cartCheckoutUiController.showShippingView(true);
        } else if (!this.mCartContext.hasValidBillingInfo()) {
            cartCheckoutUiController.showBillingView(true);
        } else {
            cartCheckoutUiController.showItemsView();
        }
    }
}
