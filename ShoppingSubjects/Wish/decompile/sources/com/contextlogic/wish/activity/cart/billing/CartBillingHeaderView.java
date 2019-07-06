package com.contextlogic.wish.activity.cart.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.ui.image.ImageRestorable;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class CartBillingHeaderView extends CartBaseBillingOptionSelectorView implements ImageRestorable {
    private LayoutParams mButtonLayoutParams;
    private LinearLayout mButtonsContainer;
    private CartFragment mCartFragment;
    private CartBillingHeaderPaymentButtonView mCommerceLoanButton;
    private LinkedHashMap<PaymentMode, CartBillingHeaderPaymentButtonView> mPaymentButtons;

    public CartBillingHeaderView(Context context) {
        super(context);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_billing_header, this);
        setOrientation(1);
        this.mButtonLayoutParams = new LayoutParams(0, (int) getResources().getDimension(R.dimen.cart_fragment_cart_billing_header_payment_tab_height), 1.0f);
        this.mButtonsContainer = (LinearLayout) inflate.findViewById(R.id.cart_billing_header_buttons_container);
        this.mPaymentButtons = new LinkedHashMap<>();
        this.mPaymentButtons.put(PaymentMode.CreditCard, null);
        this.mPaymentButtons.put(PaymentMode.Klarna, null);
        this.mPaymentButtons.put(PaymentMode.GoogleWallet, null);
        this.mPaymentButtons.put(PaymentMode.PayPal, null);
        this.mPaymentButtons.put(PaymentMode.Boleto, null);
        this.mPaymentButtons.put(PaymentMode.Oxxo, null);
        this.mPaymentButtons.put(PaymentMode.Ideal, null);
        this.mPaymentButtons.put(PaymentMode.Paytm, null);
    }

    /* access modifiers changed from: protected */
    public void updateTabVisibility() {
        this.mButtonsContainer.setVisibility(getNumActivePaymentMethods() > 1 ? 0 : 8);
    }

    public void setCartFragment(CartFragment cartFragment) {
        this.mCartFragment = cartFragment;
        if (this.mCartFragment.getCartContext() == null || this.mCartFragment.getCartContext().getSupportedPaymentModes() == null) {
            for (PaymentMode paymentMode : this.mPaymentButtons.keySet()) {
                setupAndAddPaymentModeButton(paymentMode);
            }
        } else {
            Iterator it = this.mCartFragment.getCartContext().getSupportedPaymentModes().iterator();
            while (it.hasNext()) {
                setupAndAddPaymentModeButton((PaymentMode) it.next());
            }
            for (PaymentMode paymentMode2 : this.mPaymentButtons.keySet()) {
                if (!this.mCartFragment.getCartContext().getSupportedPaymentModes().contains(paymentMode2)) {
                    setupAndAddPaymentModeButton(paymentMode2);
                }
            }
        }
        this.mCommerceLoanButton = new CartBillingHeaderPaymentButtonView(getContext());
        this.mCommerceLoanButton.setupCommerceLoanButton();
        this.mCommerceLoanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartBillingHeaderView.this.selectSection(CartBillingSection.COMMERCE_LOAN);
            }
        });
        this.mCommerceLoanButton.setLayoutParams(this.mButtonLayoutParams);
        this.mButtonsContainer.addView(this.mCommerceLoanButton);
        updateTabVisibility();
    }

    private View getButtonForSection(CartBillingSection cartBillingSection) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                return (View) this.mPaymentButtons.get(PaymentMode.CreditCard);
            case KLARNA:
                return (View) this.mPaymentButtons.get(PaymentMode.Klarna);
            case GOOGLE_PAY:
                return (View) this.mPaymentButtons.get(PaymentMode.GoogleWallet);
            case BOLETO:
                return (View) this.mPaymentButtons.get(PaymentMode.Boleto);
            case OXXO:
                return (View) this.mPaymentButtons.get(PaymentMode.Oxxo);
            case PAYPAL:
                return (View) this.mPaymentButtons.get(PaymentMode.PayPal);
            case IDEAL:
                return (View) this.mPaymentButtons.get(PaymentMode.Ideal);
            case PAYTM:
                return (View) this.mPaymentButtons.get(PaymentMode.Paytm);
            case COMMERCE_LOAN:
                return this.mCommerceLoanButton;
            default:
                return null;
        }
    }

    public boolean isSectionSelected(CartBillingSection cartBillingSection) {
        View buttonForSection = getButtonForSection(cartBillingSection);
        return buttonForSection != null && buttonForSection.isSelected();
    }

    public boolean isSectionVisible(CartBillingSection cartBillingSection) {
        View buttonForSection = getButtonForSection(cartBillingSection);
        return buttonForSection != null && buttonForSection.getVisibility() == 0;
    }

    public void setSectionVisible(CartBillingSection cartBillingSection, boolean z) {
        View buttonForSection = getButtonForSection(cartBillingSection);
        if (buttonForSection != null) {
            buttonForSection.setVisibility(z ? 0 : 8);
        }
        updateTabVisibility();
    }

    public void deselectAllSections() {
        for (CartBillingSection buttonForSection : CartBillingSection.values()) {
            View buttonForSection2 = getButtonForSection(buttonForSection);
            if (buttonForSection2 != null) {
                buttonForSection2.setSelected(false);
            }
        }
    }

    public void selectSection(final CartBillingSection cartBillingSection, boolean z) {
        if (isSectionVisible(cartBillingSection)) {
            if (!z) {
                completeSectionSelection(cartBillingSection, z);
            } else {
                logSectionSelection(cartBillingSection, this.mCartFragment.getCartContext().getCartType());
                this.mCartFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                        cartServiceFragment.selectBillingSection(cartBillingSection);
                    }
                });
            }
        }
    }

    public void completeSectionSelection(CartBillingSection cartBillingSection, boolean z) {
        CartBillingSection selectedSection = getSelectedSection();
        deselectAllSections();
        View buttonForSection = getButtonForSection(cartBillingSection);
        if (buttonForSection != null) {
            buttonForSection.setSelected(true);
        }
        if (z) {
            alertCallbackOnSectionSelected(cartBillingSection, selectedSection);
        }
    }

    private void setupAndAddPaymentModeButton(final PaymentMode paymentMode) {
        CartBillingHeaderPaymentButtonView cartBillingHeaderPaymentButtonView = new CartBillingHeaderPaymentButtonView(getContext());
        if (paymentMode != PaymentMode.CreditCard || this.mCartFragment.getCartContext() == null || this.mCartFragment.getCartContext().getCart() == null) {
            cartBillingHeaderPaymentButtonView.setup(paymentMode);
        } else {
            cartBillingHeaderPaymentButtonView.setup(paymentMode, this.mCartFragment.getCartContext().getCart().getPaymentTabCcImage());
        }
        cartBillingHeaderPaymentButtonView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartBillingHeaderView.this.selectSection(CartBaseBillingOptionSelectorView.getCartBillingSectionByPaymentMode(paymentMode));
            }
        });
        cartBillingHeaderPaymentButtonView.setLayoutParams(this.mButtonLayoutParams);
        this.mButtonsContainer.addView(cartBillingHeaderPaymentButtonView);
        this.mPaymentButtons.put(paymentMode, cartBillingHeaderPaymentButtonView);
    }

    public void releaseImages() {
        for (CartBillingHeaderPaymentButtonView cartBillingHeaderPaymentButtonView : this.mPaymentButtons.values()) {
            if (cartBillingHeaderPaymentButtonView != null) {
                cartBillingHeaderPaymentButtonView.releaseImages();
            }
        }
        if (this.mCommerceLoanButton != null) {
            this.mCommerceLoanButton.releaseImages();
        }
    }

    public void restoreImages() {
        for (CartBillingHeaderPaymentButtonView cartBillingHeaderPaymentButtonView : this.mPaymentButtons.values()) {
            if (cartBillingHeaderPaymentButtonView != null) {
                cartBillingHeaderPaymentButtonView.restoreImages();
            }
        }
        if (this.mCommerceLoanButton != null) {
            this.mCommerceLoanButton.restoreImages();
        }
    }
}
