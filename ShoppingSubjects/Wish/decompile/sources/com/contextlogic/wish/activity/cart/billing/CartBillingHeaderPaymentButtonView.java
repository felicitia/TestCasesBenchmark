package com.contextlogic.wish.activity.cart.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class CartBillingHeaderPaymentButtonView extends FrameLayout implements ImageRestorable {
    private NetworkImageView mPaymentButtonImage;
    private View mRootLayout;

    public CartBillingHeaderPaymentButtonView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mRootLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_billing_header_payment_button_view, this);
        this.mPaymentButtonImage = (NetworkImageView) this.mRootLayout.findViewById(R.id.cart_billing_header_payment_button_image);
    }

    public void setup(PaymentMode paymentMode) {
        setup(paymentMode, null);
    }

    public void setup(PaymentMode paymentMode, WishImage wishImage) {
        if (paymentMode == PaymentMode.CreditCard) {
            if (wishImage != null) {
                this.mPaymentButtonImage.setImage(wishImage);
            } else {
                this.mPaymentButtonImage.setImageResource(R.drawable.payment_tab_credit_card);
            }
        } else if (paymentMode == PaymentMode.Klarna) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_klarna));
        } else if (paymentMode == PaymentMode.GoogleWallet) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_google));
        } else if (paymentMode == PaymentMode.PayPal) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_paypal));
        } else if (paymentMode == PaymentMode.Boleto) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_boleto));
        } else if (paymentMode == PaymentMode.Oxxo) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_oxxo));
        } else if (paymentMode == PaymentMode.Ideal) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_ideal));
        } else if (paymentMode == PaymentMode.Paytm) {
            this.mPaymentButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.payment_tab_paytm));
        }
    }

    public void setupCommerceLoanButton() {
        this.mPaymentButtonImage.setVisibility(8);
        this.mRootLayout.findViewById(R.id.cart_billing_header_commerce_loan_text).setVisibility(0);
    }

    public void releaseImages() {
        if (this.mPaymentButtonImage != null) {
            this.mPaymentButtonImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mPaymentButtonImage != null) {
            this.mPaymentButtonImage.restoreImages();
        }
    }
}
