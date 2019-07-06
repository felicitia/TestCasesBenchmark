package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.payments.CartContext.CartType;

public class SignupBillingHeaderView extends CartBaseBillingOptionSelectorView {
    private View mButtonsContainer;
    private View mCreditCardButton;
    private SignupFreeGiftFragment mFreeGiftFragment;
    private View mPaypalButton;

    public int getLayoutId() {
        return R.layout.signup_billing_header;
    }

    public SignupBillingHeaderView(Context context) {
        super(context);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(getLayoutId(), this);
        setOrientation(1);
        this.mButtonsContainer = inflate.findViewById(R.id.signup_billing_header_buttons_container);
        this.mCreditCardButton = inflate.findViewById(R.id.signup_billing_header_credit_card_button);
        this.mCreditCardButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupBillingHeaderView.this.selectSection(CartBillingSection.CREDIT_CARD);
            }
        });
        this.mPaypalButton = inflate.findViewById(R.id.signup_billing_header_paypal_button);
        this.mPaypalButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupBillingHeaderView.this.selectSection(CartBillingSection.PAYPAL);
            }
        });
        updateTabVisibility();
    }

    /* access modifiers changed from: protected */
    public void updateTabVisibility() {
        this.mButtonsContainer.setVisibility(getNumActivePaymentMethods() > 1 ? 0 : 8);
    }

    private View getButtonForSection(CartBillingSection cartBillingSection) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                return this.mCreditCardButton;
            case PAYPAL:
                return this.mPaypalButton;
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
                logSectionSelection(cartBillingSection, CartType.COMMERCE_GOODS);
                this.mFreeGiftFragment.withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                        signupFreeGiftServiceFragment.selectBillingSection(cartBillingSection);
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

    public void setSignupFreeGiftFragment(SignupFreeGiftFragment signupFreeGiftFragment) {
        this.mFreeGiftFragment = signupFreeGiftFragment;
    }

    public CartBillingSection getSelectedSection() {
        return super.getSelectedSection();
    }
}
