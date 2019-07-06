package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;
import com.contextlogic.wish.ui.button.SliderButton;

public class CartCheckoutView extends LinearLayout {
    CheckoutButtonMode mButtonMode;
    /* access modifiers changed from: private */
    public CartItemsView mCartItemsView;
    private TextView mCheckoutButton;
    private View mGooglePayButton;
    private SliderButton mSlideButton;

    public CartCheckoutView(Context context, CartItemsView cartItemsView) {
        super(context);
        this.mCartItemsView = cartItemsView;
        this.mButtonMode = cartItemsView.getButtonMode();
        init();
    }

    public void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_checkout, this);
        CartContext cartContext = this.mCartItemsView.getCartFragment().getCartContext();
        this.mGooglePayButton = inflate.findViewById(R.id.cart_fragment_cart_items_google_button);
        this.mGooglePayButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartCheckoutView.this.mCartItemsView.getCartFragment().checkout(false);
            }
        });
        this.mCheckoutButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_button);
        this.mCheckoutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext() != null && CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext().getCartType() == CartType.COMMERCE_CASH) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_CHECKOUT_BUTTON, CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext().getEffectivePaymentMode());
                }
                CartCheckoutView.this.mCartItemsView.getCartFragment().checkout(false);
            }
        });
        this.mSlideButton = (SliderButton) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_slider_button);
        this.mSlideButton.setSlideListener(new OnClickListener() {
            public void onClick(View view) {
                if (CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext() != null && CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext().getCartType() == CartType.COMMERCE_CASH) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_SLIDE_BUTTON, CartCheckoutView.this.mCartItemsView.getCartFragment().getCartContext().getEffectivePaymentMode());
                }
                CartCheckoutView.this.mCartItemsView.getCartFragment().checkout(false);
            }
        });
        String checkoutButtonText = cartContext.getCheckoutActionManager().getCheckoutButtonContext().getCheckoutButtonText();
        if (this.mButtonMode == CheckoutButtonMode.GOOGLE_PAY) {
            this.mGooglePayButton.setVisibility(0);
            this.mCheckoutButton.setVisibility(8);
            this.mSlideButton.setVisibility(8);
        } else if (this.mButtonMode == CheckoutButtonMode.SLIDER) {
            this.mGooglePayButton.setVisibility(8);
            this.mCheckoutButton.setVisibility(8);
            this.mSlideButton.setVisibility(0);
            this.mSlideButton.resetState();
            this.mSlideButton.setConfirmedText(getContext().getString(R.string.confirmed));
            this.mSlideButton.setSlideText(checkoutButtonText);
        } else {
            this.mGooglePayButton.setVisibility(8);
            this.mCheckoutButton.setVisibility(0);
            this.mCheckoutButton.setText(checkoutButtonText);
            this.mSlideButton.setVisibility(8);
        }
    }
}
