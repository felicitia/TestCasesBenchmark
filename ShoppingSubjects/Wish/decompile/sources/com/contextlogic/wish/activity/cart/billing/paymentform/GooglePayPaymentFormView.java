package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.payments.CartContext;

public class GooglePayPaymentFormView extends PaymentFormView {
    private View mGooglePayContainer;
    private View mGooglePayDefaultButton;
    private TextView mGooglePayDefaultText;
    private TextView mGooglePayDetailsTextView;
    private TextView mGooglePayEmailTextView;
    private boolean mRequiresNextButton;

    public boolean populateAndValidateParameters(Bundle bundle) {
        return true;
    }

    public void recycle() {
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void restoreState(Bundle bundle) {
    }

    public GooglePayPaymentFormView(Context context) {
        super(context);
    }

    public GooglePayPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GooglePayPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_payment_form_google_pay, this);
        this.mGooglePayDefaultText = (TextView) inflate.findViewById(R.id.cart_fragment_payment_form_google_pay_default_promo);
        this.mGooglePayDefaultButton = inflate.findViewById(R.id.cart_fragment_payment_form_google_default_button);
        this.mGooglePayDefaultButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GooglePayPaymentFormView.this.getUiConnector().selectBillingSection(CartBillingSection.GOOGLE_PAY, true);
            }
        });
        this.mGooglePayContainer = inflate.findViewById(R.id.cart_fragment_payment_form_google_pay_container);
        this.mGooglePayContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GooglePayPaymentFormView.this.getUiConnector().loadGooglePayPaymentData();
            }
        });
        this.mGooglePayEmailTextView = (TextView) inflate.findViewById(R.id.cart_fragment_payment_form_google_pay_email);
        this.mGooglePayDetailsTextView = (TextView) inflate.findViewById(R.id.cart_fragment_payment_form_google_pay_details);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public void refreshUi() {
        refreshGooglePayData();
    }

    public boolean requiresNextButton() {
        return this.mRequiresNextButton;
    }

    public void refreshGooglePayData() {
        CartContext cartContext = getUiConnector().getCartContext();
        if (cartContext.getGooglePayPaymentData() != null) {
            this.mRequiresNextButton = true;
            this.mGooglePayDefaultText.setVisibility(8);
            this.mGooglePayDefaultButton.setVisibility(8);
            this.mGooglePayContainer.setVisibility(0);
            this.mGooglePayEmailTextView.setText(cartContext.getGooglePayPaymentData().getEmail());
            this.mGooglePayDetailsTextView.setText(cartContext.getGooglePayPaymentData().getCardInfo().getCardDescription());
            return;
        }
        this.mRequiresNextButton = false;
        this.mGooglePayContainer.setVisibility(8);
        this.mGooglePayDefaultText.setVisibility(0);
        this.mGooglePayDefaultText.setText(getContext().getString(R.string.checkout_quickly_and_securely_with_google));
        this.mGooglePayDefaultButton.setVisibility(0);
    }

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
        refreshGooglePayData();
    }

    public String getPaymentModeName() {
        return CartBillingSection.GOOGLE_PAY.name();
    }
}
