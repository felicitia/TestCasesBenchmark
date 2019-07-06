package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;

public class KlarnaCheckoutActionManager extends PlaceOrderCheckoutActionManager {
    public KlarnaCheckoutActionManager(CartContext cartContext) {
        super(cartContext);
    }

    public CheckoutButtonContext getCheckoutButtonContext() {
        return new CheckoutButtonContext() {
            public String getCheckoutButtonText() {
                if (!KlarnaCheckoutActionManager.this.mCartContext.hasValidBillingInfo()) {
                    return WishApplication.getInstance().getString(R.string.checkout);
                }
                if (getCheckoutButtonMode() == CheckoutButtonMode.SLIDER) {
                    return WishApplication.getInstance().getString(R.string.slide_to_pay);
                }
                return WishApplication.getInstance().getString(R.string.place_order);
            }

            public CheckoutButtonMode getCheckoutButtonMode() {
                if (KlarnaCheckoutActionManager.this.mCartContext.hasValidBillingInfo()) {
                    return CheckoutButtonMode.SLIDER;
                }
                return CheckoutButtonMode.BUTTON;
            }

            public String getCartAbandonModalActionText() {
                if (KlarnaCheckoutActionManager.this.mCartContext.hasValidBillingInfo()) {
                    return WishApplication.getInstance().getString(R.string.place_order);
                }
                return WishApplication.getInstance().getString(R.string.checkout);
            }

            public boolean allowExpressCheckout() {
                return KlarnaCheckoutActionManager.this.mCartContext.hasValidBillingInfo(true);
            }
        };
    }

    public boolean canShowPaymentCredentials() {
        return this.mCartContext.hasValidBillingInfo();
    }
}
