package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;

public abstract class PaymentFormView extends LinearLayout implements ImageRestorable, Recyclable {
    private PaymentFormUiConnector mUiConnector;

    public static class PaymentFormShownContext {
    }

    public boolean canSavePaymentMethod() {
        return true;
    }

    public String getPaymentModeName() {
        return "";
    }

    public void handleSaveInstanceState(Bundle bundle) {
    }

    public abstract void initializeUi();

    public void onAvailableContentHeightChanged(int i) {
    }

    public void onKeyboardVisiblityChanged(boolean z) {
    }

    public abstract boolean populateAndValidateParameters(Bundle bundle);

    public abstract void prepareToShow(PaymentFormShownContext paymentFormShownContext);

    public abstract void refreshUi();

    public abstract boolean requiresNextButton();

    public abstract void restoreState(Bundle bundle);

    public PaymentFormView(Context context) {
        super(context);
        init();
    }

    public PaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setOrientation(1);
    }

    public void setUiConnector(PaymentFormUiConnector paymentFormUiConnector) {
        this.mUiConnector = paymentFormUiConnector;
    }

    /* access modifiers changed from: protected */
    public PaymentFormUiConnector getUiConnector() {
        return this.mUiConnector;
    }
}
