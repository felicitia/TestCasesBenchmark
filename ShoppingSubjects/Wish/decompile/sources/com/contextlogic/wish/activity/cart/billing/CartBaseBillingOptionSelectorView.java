package com.contextlogic.wish.activity.cart.billing;

import android.content.Context;
import android.widget.LinearLayout;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.payments.CartContext.CartType;
import java.util.HashMap;

public abstract class CartBaseBillingOptionSelectorView extends LinearLayout {
    private CartBaseBillingOptionSelectorCallback mCallback;

    public interface CartBaseBillingOptionSelectorCallback {
        void onSectionSelected(CartBillingSection cartBillingSection, CartBillingSection cartBillingSection2);
    }

    public enum CartBillingSection {
        CREDIT_CARD,
        KLARNA,
        GOOGLE_PAY,
        BOLETO,
        OXXO,
        PAYPAL,
        IDEAL,
        COMMERCE_LOAN,
        PAYTM
    }

    public abstract boolean isSectionSelected(CartBillingSection cartBillingSection);

    public abstract boolean isSectionVisible(CartBillingSection cartBillingSection);

    public abstract void selectSection(CartBillingSection cartBillingSection, boolean z);

    public static CartBillingSection getCartBillingSectionByPaymentMode(PaymentMode paymentMode) {
        if (paymentMode == null || paymentMode == PaymentMode.Default) {
            return null;
        }
        if (paymentMode == PaymentMode.Klarna) {
            return CartBillingSection.KLARNA;
        }
        if (paymentMode == PaymentMode.GoogleWallet) {
            return CartBillingSection.GOOGLE_PAY;
        }
        if (paymentMode == PaymentMode.Boleto) {
            return CartBillingSection.BOLETO;
        }
        if (paymentMode == PaymentMode.Oxxo) {
            return CartBillingSection.OXXO;
        }
        if (paymentMode == PaymentMode.PayPal) {
            return CartBillingSection.PAYPAL;
        }
        if (paymentMode == PaymentMode.Ideal) {
            return CartBillingSection.IDEAL;
        }
        if (paymentMode == PaymentMode.Paytm) {
            return CartBillingSection.PAYTM;
        }
        return CartBillingSection.CREDIT_CARD;
    }

    public CartBaseBillingOptionSelectorView(Context context) {
        super(context);
    }

    public void setCallback(CartBaseBillingOptionSelectorCallback cartBaseBillingOptionSelectorCallback) {
        this.mCallback = cartBaseBillingOptionSelectorCallback;
    }

    /* access modifiers changed from: protected */
    public void alertCallbackOnSectionSelected(CartBillingSection cartBillingSection, CartBillingSection cartBillingSection2) {
        if (this.mCallback != null) {
            this.mCallback.onSectionSelected(cartBillingSection, cartBillingSection2);
        }
    }

    /* access modifiers changed from: protected */
    public int getNumActivePaymentMethods() {
        int i = 0;
        for (CartBillingSection isSectionVisible : CartBillingSection.values()) {
            if (isSectionVisible(isSectionVisible)) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public CartBillingSection getSelectedSection() {
        CartBillingSection[] values;
        for (CartBillingSection cartBillingSection : CartBillingSection.values()) {
            if (isSectionSelected(cartBillingSection)) {
                return cartBillingSection;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public WishAnalyticsEvent getSectionAnalyticEvent(CartBillingSection cartBillingSection) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_CC_TAB;
            case KLARNA:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_KLARNA_TAB;
            case GOOGLE_PAY:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_GWALLET_TAB;
            case BOLETO:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_BOLETO_TAB;
            case OXXO:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_OXXO_TAB;
            case PAYPAL:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_PAYPAL_TAB;
            case IDEAL:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_IDEAL_TAB;
            case COMMERCE_LOAN:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_COMMERCE_LOAN_TAB;
            case PAYTM:
                return WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_PAYTM_TAB;
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public void logSectionSelection(CartBillingSection cartBillingSection, CartType cartType) {
        if (!isSectionSelected(cartBillingSection)) {
            WishAnalyticsEvent sectionAnalyticEvent = getSectionAnalyticEvent(cartBillingSection);
            if (sectionAnalyticEvent != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("cart_type", cartType.toString());
                WishAnalyticsLogger.trackEvent(sectionAnalyticEvent, hashMap);
            }
        }
    }

    public void selectSection(CartBillingSection cartBillingSection) {
        if (!isSectionSelected(cartBillingSection)) {
            selectSection(cartBillingSection, true);
        }
    }
}
