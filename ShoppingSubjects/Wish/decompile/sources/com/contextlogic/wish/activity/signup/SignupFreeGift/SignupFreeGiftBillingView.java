package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBaseBillingOptionSelectorCallback;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.CreditCardPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PayPalPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormServiceProviderTask;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormUiConnector;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class SignupFreeGiftBillingView extends SignupFreeGiftUiView implements CartBaseBillingOptionSelectorCallback, PaymentFormUiConnector {
    private TextView mCancelButton;
    private boolean mCompleteFormAfterSelection;
    private CreditCardPaymentFormView mCreditCardView;
    private PaymentFormView mCurrentPaymentFormView;
    private CartBillingSection mCurrentSection;
    private TextView mFloatingNextButton;
    private View mFloatingNextButtonLayout;
    private boolean mFloatingNextButtonShown;
    private FrameLayout mHeaderContainer;
    private TextView mInlineNextButton;
    private OnGlobalLayoutListener mLayoutListener;
    private PayPalPaymentFormView mPayPalView;
    private ArrayList<PaymentFormView> mPaymentSections;
    /* access modifiers changed from: private */
    public SignupBillingHeaderView mTabHeaderView;
    private View mTitleContainer;

    public void loadGooglePayPaymentData() {
    }

    public void openActivity(Class cls) {
    }

    public void refreshNextButtonState() {
    }

    public void showBrowser(String str, boolean z, boolean z2) {
    }

    public void showCommerceLoanCCBillingView() {
    }

    public void showCreditCardPaymentFormView() {
    }

    public void withServiceProvider(PaymentFormServiceProviderTask paymentFormServiceProviderTask) {
    }

    public SignupFreeGiftBillingView(SignupFreeGiftFragment signupFreeGiftFragment, SignupFreeGiftActivity signupFreeGiftActivity, Bundle bundle) {
        super(signupFreeGiftFragment, signupFreeGiftActivity, bundle);
    }

    public void onSectionSelected(CartBillingSection cartBillingSection, CartBillingSection cartBillingSection2) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                onCreditCardTabSelected(cartBillingSection2);
                return;
            case PAYPAL:
                onPaypalTabSelected(cartBillingSection2);
                return;
            default:
                return;
        }
    }

    private void onCreditCardTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mCreditCardView, null, CartBillingSection.CREDIT_CARD);
    }

    /* access modifiers changed from: protected */
    public void onPaypalTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
        if (!ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(getFreeGiftFragment().getCartContext())) {
            handleFormComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void initializeUi(Bundle bundle) {
        View view;
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT_BILLING);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            view = layoutInflater.inflate(R.layout.free_gift_fragment_billing_view_redesign, this);
        } else {
            view = layoutInflater.inflate(R.layout.free_gift_fragment_billing_view, this);
        }
        this.mTitleContainer = view.findViewById(R.id.signup_free_gift_billing_view_header);
        this.mCancelButton = (TextView) view.findViewById(R.id.signup_free_gift_billing_view_cancel);
        if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mCancelButton.setPaintFlags(this.mCancelButton.getPaintFlags() | 8);
        }
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_BILLING_ABANDONMENT_MODAL);
                SignupFreeGiftBillingView.this.getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                        signupFreeGiftServiceFragment.showFreeGiftAbandonDialog(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_BILLING_ABANDONMENT_MODAL_RETURN, WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_BILLING_ABANDONMENT_MODAL_PROCEED);
                    }
                });
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mTabHeaderView = new SignupBillingHeaderViewRedesign(getContext());
        } else {
            this.mTabHeaderView = new SignupBillingHeaderView(getContext());
        }
        this.mTabHeaderView.setSignupFreeGiftFragment(getFreeGiftFragment());
        this.mTabHeaderView.setCallback(this);
        this.mHeaderContainer = (FrameLayout) view.findViewById(R.id.signup_free_gift_billing_view_header_placeholder);
        this.mHeaderContainer.addView(this.mTabHeaderView);
        this.mPaymentSections = new ArrayList<>();
        this.mCreditCardView = (CreditCardPaymentFormView) view.findViewById(R.id.signup_free_gift_billing_view_credit_card);
        this.mPaymentSections.add(this.mCreditCardView);
        this.mPayPalView = (PayPalPaymentFormView) view.findViewById(R.id.signup_free_gift_billing_view_paypal);
        this.mPaymentSections.add(this.mPayPalView);
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            PaymentFormView paymentFormView = (PaymentFormView) it.next();
            paymentFormView.setUiConnector(this);
            paymentFormView.initializeUi();
        }
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mCreditCardView.showRedesignedBackground();
        }
        this.mFloatingNextButtonLayout = view.findViewById(R.id.signup_free_gift_billing_view_floating_bottom_next_layout);
        this.mFloatingNextButton = (TextView) view.findViewById(R.id.signup_free_gift_billing_view_floating_bottom_next_button);
        this.mInlineNextButton = (TextView) view.findViewById(R.id.signup_free_gift_billing_view_inline_bottom_next_button);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mFloatingNextButton.setText(getFreeGiftFragment().getString(R.string.pay_amount_shipping, getFreeGiftFragment().getWishSignupFreeGiftCart().getShippingAmount()));
            this.mInlineNextButton.setText(getFreeGiftFragment().getString(R.string.pay_amount_shipping, getFreeGiftFragment().getWishSignupFreeGiftCart().getShippingAmount()));
        } else {
            this.mFloatingNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            this.mInlineNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
        }
        this.mFloatingNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftBillingView.this.handleFormComplete();
            }
        });
        this.mInlineNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftBillingView.this.handleFormComplete();
            }
        });
        this.mLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SignupFreeGiftBillingView.this.alertContentHeightChanged();
            }
        };
        ThemedTextView themedTextView = (ThemedTextView) view.findViewById(R.id.cart_billing_view_almost_done_textview);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            themedTextView.setText(getFreeGiftFragment().getWishSignupFreeGiftCart().getFreeGiftShippingText());
        } else {
            themedTextView.setText(getFreeGiftFragment().getWishSignupFreeGiftCart().getAlmostDone());
        }
        themedTextView.setVisibility(0);
        ((LinearLayout) view.findViewById(R.id.cart_billing_view_signup_cart_layout)).setVisibility(0);
        WishCartItem wishCartItem = (WishCartItem) getFreeGiftFragment().getCartContext().getCart().getItems().get(0);
        ((NetworkImageView) view.findViewById(R.id.cart_billing_view_product_image)).setImage(wishCartItem.getImage());
        ((ThemedTextView) view.findViewById(R.id.cart_billing_view_cost_message)).setText(getFreeGiftFragment().getWishSignupFreeGiftCart().getFreeGiftShippingText());
        ThemedTextView themedTextView2 = (ThemedTextView) view.findViewById(R.id.cart_billing_view_subtotal_new);
        ThemedTextView themedTextView3 = (ThemedTextView) view.findViewById(R.id.cart_billing_view_subtotal_original);
        themedTextView3.setPaintFlags(themedTextView3.getPaintFlags() | 16);
        if (wishCartItem.getProductSubtotal().getValue() > 0.0d) {
            themedTextView2.setText(getFreeGiftFragment().getCartContext().getCart().getSubtotal().toFormattedString());
        } else {
            themedTextView2.setText(getContext().getString(R.string.free));
        }
        WishLocalizedCurrencyValue retailPrice = wishCartItem.getRetailPrice();
        if (retailPrice.getValue() > wishCartItem.getProductSubtotal().getValue()) {
            themedTextView3.setVisibility(0);
            if (retailPrice.getValue() > 0.0d) {
                themedTextView3.setText(retailPrice.toFormattedString());
            } else {
                themedTextView3.setText(getContext().getString(R.string.free));
            }
        } else {
            themedTextView3.setVisibility(8);
        }
        ThemedTextView themedTextView4 = (ThemedTextView) view.findViewById(R.id.cart_billing_view_shipping_cost);
        if (getFreeGiftFragment().getCartContext().getCart().getShippingPrice().getValue() > 0.0d) {
            themedTextView4.setText(getFreeGiftFragment().getCartContext().getCart().getShippingPrice().toFormattedString());
        } else {
            themedTextView4.setText(getContext().getString(R.string.free));
        }
        ThemedTextView themedTextView5 = (ThemedTextView) view.findViewById(R.id.cart_billing_view_total_cost);
        if (getFreeGiftFragment().getCartContext().getCart().getTotal().getValue() > 0.0d) {
            themedTextView5.setText(getFreeGiftFragment().getCartContext().getCart().getTotal().toFormattedString());
        } else {
            themedTextView5.setText(getContext().getString(R.string.free));
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
    }

    /* access modifiers changed from: protected */
    public void alertContentHeightChanged() {
        if (this.mCurrentPaymentFormView != null) {
            LayoutParams layoutParams = (LayoutParams) this.mTitleContainer.getLayoutParams();
            int i = layoutParams.topMargin + layoutParams.bottomMargin + 0;
            LayoutParams layoutParams2 = (LayoutParams) this.mHeaderContainer.getLayoutParams();
            this.mCurrentPaymentFormView.onAvailableContentHeightChanged(((getHeight() - this.mTitleContainer.getHeight()) - this.mHeaderContainer.getHeight()) - (i + (layoutParams2.topMargin + layoutParams2.bottomMargin)));
        }
    }

    public void activatePaymentSections(Bundle bundle, CartBillingSection cartBillingSection) {
        resetSelectedViews();
        CartContext cartContext = getFreeGiftFragment().getCartContext();
        boolean z = false;
        if (ExperimentDataCenter.getInstance().canCheckoutWithCreditCard(cartContext)) {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.CREDIT_CARD, true);
        } else {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.CREDIT_CARD, false);
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithPayPal(cartContext)) {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.PAYPAL, true);
        } else {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.PAYPAL, false);
        }
        if (cartBillingSection == null && bundle != null) {
            cartBillingSection = (CartBillingSection) bundle.getSerializable("SavedStateSection");
        }
        if (cartBillingSection != null) {
            switch (cartBillingSection) {
                case CREDIT_CARD:
                    this.mTabHeaderView.selectSection(CartBillingSection.CREDIT_CARD);
                    break;
                case PAYPAL:
                    showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
                    break;
            }
            z = true;
        }
        if (z) {
            return;
        }
        if (this.mTabHeaderView.isSectionVisible(CartBillingSection.CREDIT_CARD)) {
            this.mTabHeaderView.selectSection(CartBillingSection.CREDIT_CARD);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.PAYPAL)) {
            showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
        }
    }

    /* access modifiers changed from: protected */
    public void showPaymentSection(PaymentFormView paymentFormView, PaymentFormShownContext paymentFormShownContext, CartBillingSection cartBillingSection) {
        resetSelectedViews();
        if (paymentFormShownContext == null) {
            paymentFormShownContext = new PaymentFormShownContext();
        }
        paymentFormView.prepareToShow(paymentFormShownContext);
        this.mCurrentPaymentFormView = paymentFormView;
        paymentFormView.setVisibility(0);
        refreshNextButtonVisibility(paymentFormView);
        this.mTabHeaderView.selectSection(cartBillingSection, false);
        this.mCurrentSection = cartBillingSection;
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        if (this.mFloatingNextButtonShown) {
            refreshFloatingNextButtonKeyboardVisibility(z);
        }
        this.mCurrentPaymentFormView.onKeyboardVisiblityChanged(z);
    }

    private void refreshNextButtonVisibility(PaymentFormView paymentFormView) {
        if (paymentFormView.requiresNextButton()) {
            showFloatingNextButton();
        } else {
            hideFloatingNextButton();
        }
    }

    private void showFloatingNextButton() {
        this.mFloatingNextButtonShown = true;
        getFreeGiftFragment().withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView() || ExperimentDataCenter.getInstance().shouldSeeNewFloatingButton()) {
                    SignupFreeGiftBillingView.this.refreshFloatingNextButtonKeyboardVisibility(signupFreeGiftActivity.isKeyboardVisible());
                } else {
                    SignupFreeGiftBillingView.this.refreshFloatingNextButtonKeyboardVisibility(SignupFreeGiftBillingView.this.mTabHeaderView.getSelectedSection() == CartBillingSection.CREDIT_CARD);
                }
            }
        });
    }

    private void hideFloatingNextButton() {
        this.mFloatingNextButtonLayout.setVisibility(8);
        this.mInlineNextButton.setVisibility(8);
        this.mFloatingNextButtonShown = false;
    }

    /* access modifiers changed from: private */
    public void refreshFloatingNextButtonKeyboardVisibility(boolean z) {
        this.mFloatingNextButtonLayout.setVisibility(8);
        this.mInlineNextButton.setVisibility(8);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            z |= !ExperimentDataCenter.getInstance().shouldSeeNewFloatingButton();
        }
        if (z) {
            this.mInlineNextButton.setVisibility(0);
        } else {
            this.mFloatingNextButtonLayout.setVisibility(0);
        }
    }

    private void resetSelectedViews() {
        KeyboardUtil.hideKeyboard((View) this);
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            ((PaymentFormView) it.next()).setVisibility(8);
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putSerializable("SavedStateSection", this.mCurrentSection);
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            PaymentFormView paymentFormView = (PaymentFormView) it.next();
            Bundle bundle2 = new Bundle();
            paymentFormView.handleSaveInstanceState(bundle2);
            bundle.putBundle(paymentFormView.getClass().getCanonicalName(), bundle2);
        }
    }

    public void refreshUi() {
        this.mCurrentPaymentFormView.refreshUi();
    }

    public boolean onBackPressed() {
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.confirmBillingViewClosing();
            }
        });
        return true;
    }

    public void releaseImages() {
        this.mCurrentPaymentFormView.releaseImages();
    }

    public void restoreImages() {
        this.mCurrentPaymentFormView.restoreImages();
    }

    public void handleFormComplete() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_BILLING_REDEEM_BUTTON);
        KeyboardUtil.hideKeyboard((View) this);
        final Bundle bundle = new Bundle();
        if (this.mCurrentPaymentFormView.populateAndValidateParameters(bundle)) {
            getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                    signupFreeGiftServiceFragment.saveBillingInformation(SignupFreeGiftBillingView.this.mTabHeaderView.getSelectedSection(), bundle);
                }
            });
        }
    }

    public void selectBillingSection(final CartBillingSection cartBillingSection, boolean z) {
        this.mCompleteFormAfterSelection = z;
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.selectBillingSection(cartBillingSection);
            }
        });
    }

    public void showLoadingDialog() {
        getFreeGiftFragment().withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingDialog() {
        getFreeGiftFragment().withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.hideLoadingDialog();
            }
        });
    }

    public void showItemsView() {
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showItemsView();
            }
        });
    }

    public void showErrorMessage(final String str) {
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_BILLING_ERROR_MODAL);
                signupFreeGiftServiceFragment.showErrorMessage(str);
            }
        });
    }

    public CartContext getCartContext() {
        return getFreeGiftFragment().getCartContext();
    }

    public void recycle() {
        this.mCurrentPaymentFormView.recycle();
        getViewTreeObserver().removeGlobalOnLayoutListener(this.mLayoutListener);
    }

    public void completeBillingSectionSelected(CartBillingSection cartBillingSection) {
        this.mTabHeaderView.completeSectionSelection(cartBillingSection, true);
    }

    public void setReloadCartOnReenter(boolean z) {
        getCartContext().setReloadCartOnReenter(z);
    }
}
