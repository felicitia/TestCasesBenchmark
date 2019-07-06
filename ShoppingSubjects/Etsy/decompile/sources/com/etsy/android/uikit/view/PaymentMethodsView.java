package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;

public class PaymentMethodsView extends FlowLayout {
    private View mCreditCardIcons;
    private View mGiftCards;
    private View mPayPalIcon;

    public PaymentMethodsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PaymentMethodsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public PaymentMethodsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        inflate(context, k.view_payment_methods, this);
        this.mCreditCardIcons = findViewById(i.icons_credit_cards);
        this.mPayPalIcon = findViewById(i.icon_paypal);
        this.mGiftCards = findViewById(i.section_giftcards);
    }

    public void setCreditCardsVisible(boolean z) {
        this.mCreditCardIcons.setVisibility(z ? 0 : 8);
    }

    public void setPayPalVisible(boolean z) {
        this.mPayPalIcon.setVisibility(z ? 0 : 8);
    }

    public void setGiftCardsVisible(boolean z) {
        this.mGiftCards.setVisibility(z ? 0 : 8);
    }
}
