package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;

public class IdealPaymentFormView extends PaymentFormView {
    public boolean populateAndValidateParameters(Bundle bundle) {
        return true;
    }

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
    }

    public void recycle() {
    }

    public void refreshUi() {
    }

    public void releaseImages() {
    }

    public boolean requiresNextButton() {
        return true;
    }

    public void restoreImages() {
    }

    public void restoreState(Bundle bundle) {
    }

    public IdealPaymentFormView(Context context) {
        super(context);
    }

    public IdealPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IdealPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_ideal, this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public String getPaymentModeName() {
        return CartBillingSection.IDEAL.name();
    }
}
