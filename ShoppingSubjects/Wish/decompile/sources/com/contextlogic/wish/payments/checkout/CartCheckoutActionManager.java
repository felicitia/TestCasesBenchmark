package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.payments.CartContext;

public abstract class CartCheckoutActionManager {
    protected CartContext mCartContext;

    public static class CheckoutButtonContext {

        public enum CheckoutButtonMode {
            BUTTON,
            SLIDER,
            GOOGLE_PAY
        }

        public boolean allowExpressCheckout() {
            return false;
        }

        public String getCartAbandonModalActionText() {
            return null;
        }

        public String getCheckoutButtonText() {
            return null;
        }

        public CheckoutButtonMode getCheckoutButtonMode() {
            return CheckoutButtonMode.BUTTON;
        }
    }

    public boolean alwaysShowPaymentCredentials() {
        return false;
    }

    public abstract boolean canShowPaymentCredentials();

    public abstract void checkout(CartCheckoutUiController cartCheckoutUiController, boolean z);

    public abstract CheckoutButtonContext getCheckoutButtonContext();

    public CartCheckoutActionManager(CartContext cartContext) {
        this.mCartContext = cartContext;
    }
}
