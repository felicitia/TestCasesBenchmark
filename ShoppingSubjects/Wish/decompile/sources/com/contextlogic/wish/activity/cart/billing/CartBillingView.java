package com.contextlogic.wish.activity.cart.billing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.braintreepayments.api.BraintreeFragment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.CartUiView;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBaseBillingOptionSelectorCallback;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.BoletoPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.CommerceLoanPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.CreditCardPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.GooglePayPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.IdealPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.KlarnaPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.OxxoPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PayPalPaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormServiceProviderTask;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormUiConnector;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaytmPaymentFormView;
import com.contextlogic.wish.activity.managepayments.ManagePaymentsView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.google.GooglePayManager;
import com.contextlogic.wish.util.KeyboardUtil;
import com.google.android.gms.wallet.PaymentData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CartBillingView extends CartUiView implements CartBaseBillingOptionSelectorCallback, PaymentFormUiConnector {
    /* access modifiers changed from: private */
    public boolean mAutoCheckoutOnCompletion;
    private BoletoPaymentFormView mBoletoView;
    private CommerceLoanPaymentFormView mCommerceLoanView;
    private boolean mCompleteFormAfterSelection;
    private CreditCardPaymentFormView mCreditCardView;
    /* access modifiers changed from: private */
    public PaymentFormView mCurrentPaymentFormView;
    /* access modifiers changed from: private */
    public CartBillingSection mCurrentSection;
    private TextView mFloatingNextButton;
    private View mFloatingNextButtonLayout;
    private boolean mFloatingNextButtonShown;
    private GooglePayPaymentFormView mGooglePayView;
    private FrameLayout mHeaderContainer;
    private IdealPaymentFormView mIdealView;
    private CartBillingSection mInitialSection;
    private TextView mInlineNextButton;
    private KlarnaPaymentFormView mKlarnaView;
    private OnGlobalLayoutListener mLayoutListener;
    private ManagePaymentsView mManagePaymentsView;
    private OxxoPaymentFormView mOxxoView;
    private PayPalPaymentFormView mPayPalView;
    private ArrayList<PaymentFormView> mPaymentSections;
    private PaytmPaymentFormView mPaytmView;
    protected CartBillingHeaderView mTabHeaderView;

    /* access modifiers changed from: protected */
    public CartBillingSection getParentBillingSection() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isForCommerceLoan() {
        return false;
    }

    public CartBillingView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle, boolean z, CartBillingSection cartBillingSection) {
        super(cartFragment, cartActivity, bundle);
        if (bundle != null) {
            this.mAutoCheckoutOnCompletion = bundle.getBoolean("SavedStateAutoCheckoutOnCompletion");
        } else {
            this.mAutoCheckoutOnCompletion = z;
        }
        this.mInitialSection = cartBillingSection;
    }

    public void initializeUi(Bundle bundle) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_cart_billing, this);
        this.mTabHeaderView = new CartBillingHeaderView(getContext());
        this.mTabHeaderView.setCartFragment(getCartFragment());
        this.mTabHeaderView.setCallback(this);
        this.mHeaderContainer = (FrameLayout) inflate.findViewById(R.id.cart_fragment_cart_billing_header_placeholder);
        this.mHeaderContainer.addView(this.mTabHeaderView);
        this.mPaymentSections = new ArrayList<>();
        this.mCreditCardView = (CreditCardPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_credit_card);
        this.mPaymentSections.add(this.mCreditCardView);
        this.mManagePaymentsView = (ManagePaymentsView) inflate.findViewById(R.id.cart_fragment_cart_billing_manage_payments);
        this.mManagePaymentsView.setFromCart(true);
        this.mPaymentSections.add(this.mManagePaymentsView);
        this.mKlarnaView = (KlarnaPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_klarna);
        this.mPaymentSections.add(this.mKlarnaView);
        this.mGooglePayView = (GooglePayPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_google_pay);
        this.mPaymentSections.add(this.mGooglePayView);
        this.mPayPalView = (PayPalPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_paypal);
        this.mPaymentSections.add(this.mPayPalView);
        this.mBoletoView = (BoletoPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_boleto);
        this.mPaymentSections.add(this.mBoletoView);
        this.mOxxoView = (OxxoPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_oxxo);
        this.mPaymentSections.add(this.mOxxoView);
        this.mIdealView = (IdealPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_ideal);
        this.mPaymentSections.add(this.mIdealView);
        this.mPaytmView = (PaytmPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_paytm);
        this.mPaymentSections.add(this.mPaytmView);
        this.mCommerceLoanView = (CommerceLoanPaymentFormView) inflate.findViewById(R.id.cart_fragment_cart_billing_commerce_loan);
        this.mPaymentSections.add(this.mCommerceLoanView);
        this.mCommerceLoanView.setFragment(getCartFragment());
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            PaymentFormView paymentFormView = (PaymentFormView) it.next();
            paymentFormView.setUiConnector(this);
            paymentFormView.initializeUi();
            if (bundle != null) {
                Bundle bundle2 = bundle.getBundle(paymentFormView.getClass().getCanonicalName());
                if (bundle2 != null) {
                    paymentFormView.restoreState(bundle2);
                }
            }
        }
        this.mFloatingNextButtonLayout = inflate.findViewById(R.id.cart_fragment_cart_billing_floating_bottom_next_layout);
        this.mFloatingNextButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_billing_floating_bottom_next_button);
        this.mInlineNextButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_billing_inline_bottom_next_button);
        if (this.mAutoCheckoutOnCompletion) {
            this.mFloatingNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            this.mInlineNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
        } else {
            this.mFloatingNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.save_info));
            this.mInlineNextButton.setText(WishApplication.getInstance().getResources().getString(R.string.save_info));
        }
        this.mFloatingNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartBillingView.this.handleFormComplete();
            }
        });
        this.mInlineNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartBillingView.this.handleFormComplete();
            }
        });
        this.mLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                CartBillingView.this.alertContentHeightChanged();
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        activatePaymentSections(bundle);
    }

    /* access modifiers changed from: protected */
    public void alertContentHeightChanged() {
        if (this.mCurrentPaymentFormView != null) {
            LayoutParams layoutParams = (LayoutParams) this.mHeaderContainer.getLayoutParams();
            this.mCurrentPaymentFormView.onAvailableContentHeightChanged((getHeight() - this.mHeaderContainer.getHeight()) - ((layoutParams.topMargin + layoutParams.bottomMargin) + 0));
        }
    }

    /* access modifiers changed from: protected */
    public void activatePaymentSections(Bundle bundle) {
        CartBillingSection cartBillingSection;
        resetSelectedViews();
        CartContext cartContext = getCartFragment().getCartContext();
        this.mTabHeaderView.setSectionVisible(CartBillingSection.CREDIT_CARD, CartContext.supportsPaymentMode(PaymentMode.CreditCard, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.KLARNA, CartContext.supportsPaymentMode(PaymentMode.Klarna, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.GOOGLE_PAY, CartContext.supportsPaymentMode(PaymentMode.GoogleWallet, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.PAYPAL, CartContext.supportsPaymentMode(PaymentMode.PayPal, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.BOLETO, CartContext.supportsPaymentMode(PaymentMode.Boleto, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.OXXO, CartContext.supportsPaymentMode(PaymentMode.Oxxo, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.IDEAL, CartContext.supportsPaymentMode(PaymentMode.Ideal, cartContext));
        this.mTabHeaderView.setSectionVisible(CartBillingSection.PAYTM, CartContext.supportsPaymentMode(PaymentMode.Paytm, cartContext));
        boolean z = false;
        if (!ExperimentDataCenter.getInstance().canSeeCommerceLoanBillingOption() || cartContext.getCommerceLoanTabSpec() == null || cartContext.getUserBillingInfo() == null || cartContext.getUserBillingInfo().getDefaultCreditCardInfo(cartContext.getPaymentProcessor()) == null) {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.COMMERCE_LOAN, false);
        } else {
            this.mTabHeaderView.setSectionVisible(CartBillingSection.COMMERCE_LOAN, true);
        }
        if (!(this.mInitialSection == null && bundle == null)) {
            if (this.mInitialSection != null) {
                cartBillingSection = this.mInitialSection;
            } else {
                cartBillingSection = (CartBillingSection) bundle.getSerializable("SavedStateSection");
            }
            if (cartBillingSection != null && this.mTabHeaderView.isSectionVisible(cartBillingSection)) {
                switch (cartBillingSection) {
                    case CREDIT_CARD:
                        this.mTabHeaderView.selectSection(CartBillingSection.CREDIT_CARD);
                        break;
                    case KLARNA:
                        showPaymentSection(this.mKlarnaView, null, CartBillingSection.KLARNA);
                        break;
                    case GOOGLE_PAY:
                        this.mTabHeaderView.selectSection(CartBillingSection.GOOGLE_PAY);
                        break;
                    case BOLETO:
                        this.mTabHeaderView.selectSection(CartBillingSection.BOLETO);
                        break;
                    case OXXO:
                        this.mTabHeaderView.selectSection(CartBillingSection.OXXO);
                        break;
                    case PAYPAL:
                        showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
                        break;
                    case IDEAL:
                        showPaymentSection(this.mIdealView, null, CartBillingSection.IDEAL);
                        break;
                    case COMMERCE_LOAN:
                        this.mTabHeaderView.selectSection(CartBillingSection.COMMERCE_LOAN);
                        break;
                    case PAYTM:
                        showPaymentSection(this.mPaytmView, null, CartBillingSection.PAYTM);
                        break;
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        if (this.mTabHeaderView.isSectionVisible(CartBillingSection.GOOGLE_PAY) && cartContext.getGooglePayPaymentData() != null) {
            this.mTabHeaderView.selectSection(CartBillingSection.GOOGLE_PAY);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.GOOGLE_PAY) && ExperimentDataCenter.getInstance().canUseGooglePlayAsDefault(cartContext)) {
            showPaymentSection(this.mGooglePayView, null, CartBillingSection.GOOGLE_PAY);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.CREDIT_CARD)) {
            this.mTabHeaderView.selectSection(CartBillingSection.CREDIT_CARD);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.PAYPAL)) {
            showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.BOLETO)) {
            this.mTabHeaderView.selectSection(CartBillingSection.BOLETO);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.OXXO)) {
            this.mTabHeaderView.selectSection(CartBillingSection.OXXO);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.IDEAL)) {
            showPaymentSection(this.mIdealView, null, CartBillingSection.IDEAL);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.GOOGLE_PAY)) {
            showPaymentSection(this.mGooglePayView, null, CartBillingSection.GOOGLE_PAY);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.KLARNA)) {
            this.mTabHeaderView.selectSection(CartBillingSection.KLARNA);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.PAYTM)) {
            showPaymentSection(this.mPaytmView, null, CartBillingSection.PAYTM);
        } else if (this.mTabHeaderView.isSectionVisible(CartBillingSection.COMMERCE_LOAN)) {
            this.mTabHeaderView.selectSection(CartBillingSection.COMMERCE_LOAN);
        }
    }

    public void onSectionSelected(CartBillingSection cartBillingSection, CartBillingSection cartBillingSection2) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                onCreditCardTabSelected(cartBillingSection2);
                break;
            case KLARNA:
                onKlarnaTabSelected(cartBillingSection2);
                break;
            case GOOGLE_PAY:
                onGooglePayTabSelected(cartBillingSection2);
                break;
            case BOLETO:
                onBoletoTabSelected(cartBillingSection2);
                break;
            case OXXO:
                onOxxoTabSelected(cartBillingSection2);
                break;
            case PAYPAL:
                onPaypalTabSelected(cartBillingSection2);
                break;
            case IDEAL:
                onIdealTabSelected(cartBillingSection2);
                break;
            case COMMERCE_LOAN:
                onCommerceLoanTabSelected(cartBillingSection2);
                break;
            case PAYTM:
                onPaytmTabSelected(cartBillingSection2);
                break;
        }
        if (this.mCompleteFormAfterSelection) {
            handleFormComplete();
            this.mCompleteFormAfterSelection = false;
        }
    }

    public void loadGooglePayPaymentData() {
        showLoadingDialog();
        getCartFragment().withServiceFragment(new ServiceTask<CartActivity, CartServiceFragment>() {
            public void performTask(final CartActivity cartActivity, final CartServiceFragment cartServiceFragment) {
                cartServiceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
                    public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                        cartActivity.getServiceFragment().loadPaymentData(GooglePayManager.getInstance().createPaymentDataRequest(CartBillingView.this.getCartContext(), braintreeFragment), cartActivity.addResultCodeCallback(new ActivityResultCallback() {
                            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                if (i2 == -1) {
                                    cartServiceFragment.getCartContext().updateGooglePayPaymentData(PaymentData.getFromIntent(intent));
                                    CartBillingView.this.hideLoadingDialog();
                                } else if (i2 == 0) {
                                    CartBillingView.this.hideLoadingDialog();
                                } else {
                                    CartBillingView.this.hideLoadingDialog();
                                    CartBillingView.this.showErrorMessage(GooglePayManager.getInstance().getGooglePayErrorMessage(intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413)));
                                }
                            }
                        }));
                    }

                    public void onBraintreeFragmentLoadFailed(String str) {
                        CartBillingView.this.hideLoadingDialog();
                        CartBillingView.this.showErrorMessage(str);
                    }
                });
            }
        });
    }

    public void showCreditCardPaymentFormView() {
        showPaymentSection(this.mCreditCardView, null, CartBillingSection.CREDIT_CARD);
        this.mCreditCardView.requestFocusOnCardNumberField();
    }

    private void onCreditCardTabSelected(CartBillingSection cartBillingSection) {
        if (ExperimentDataCenter.getInstance().shouldEnableMultiplePayments()) {
            showPaymentSection(this.mManagePaymentsView, null, CartBillingSection.CREDIT_CARD);
            return;
        }
        showPaymentSection(this.mCreditCardView, null, CartBillingSection.CREDIT_CARD);
        this.mCreditCardView.requestFocusOnCardNumberField();
    }

    private void onKlarnaTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mKlarnaView, null, CartBillingSection.KLARNA);
    }

    /* access modifiers changed from: protected */
    public void onPaypalTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mPayPalView, null, CartBillingSection.PAYPAL);
        if (!ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(getCartFragment().getCartContext())) {
            handleFormComplete();
        }
    }

    private void onBoletoTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mBoletoView, null, CartBillingSection.BOLETO);
    }

    private void onOxxoTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mOxxoView, null, CartBillingSection.OXXO);
    }

    /* access modifiers changed from: protected */
    public void onIdealTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mIdealView, null, CartBillingSection.IDEAL);
        handleFormComplete();
    }

    private void onGooglePayTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mGooglePayView, null, CartBillingSection.GOOGLE_PAY);
    }

    private void onCommerceLoanTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mCommerceLoanView, null, CartBillingSection.COMMERCE_LOAN);
    }

    /* access modifiers changed from: protected */
    public void onPaytmTabSelected(CartBillingSection cartBillingSection) {
        showPaymentSection(this.mPaytmView, null, CartBillingSection.PAYTM);
        handleFormComplete();
    }

    /* access modifiers changed from: protected */
    public void resetSelectedViews() {
        KeyboardUtil.hideKeyboard((View) this);
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            ((PaymentFormView) it.next()).setVisibility(8);
        }
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        if (this.mFloatingNextButtonShown) {
            refreshFloatingNextButtonKeyboardVisibility(z);
        }
        if (this.mCurrentPaymentFormView != null) {
            this.mCurrentPaymentFormView.onKeyboardVisiblityChanged(z);
        }
    }

    private void showFloatingNextButton() {
        this.mFloatingNextButtonShown = true;
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                CartBillingView.this.refreshFloatingNextButtonKeyboardVisibility(cartActivity.isKeyboardVisible());
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshFloatingNextButtonKeyboardVisibility(boolean z) {
        this.mFloatingNextButtonLayout.setVisibility(8);
        this.mInlineNextButton.setVisibility(8);
        if (z) {
            this.mInlineNextButton.setVisibility(0);
        } else {
            this.mFloatingNextButtonLayout.setVisibility(0);
        }
    }

    private void hideFloatingNextButton() {
        this.mFloatingNextButtonLayout.setVisibility(8);
        this.mInlineNextButton.setVisibility(8);
        this.mFloatingNextButtonShown = false;
    }

    private void refreshNextButtonVisibility(PaymentFormView paymentFormView) {
        if (paymentFormView.requiresNextButton()) {
            showFloatingNextButton();
        } else {
            hideFloatingNextButton();
        }
    }

    public void refreshNextButtonState() {
        if (this.mFloatingNextButton != null && this.mInlineNextButton != null) {
            if (this.mCurrentPaymentFormView == null || !this.mCurrentPaymentFormView.canSavePaymentMethod()) {
                this.mFloatingNextButton.setEnabled(false);
                this.mInlineNextButton.setEnabled(false);
                return;
            }
            this.mFloatingNextButton.setEnabled(true);
            this.mInlineNextButton.setEnabled(true);
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
        refreshNextButtonState();
        this.mTabHeaderView.selectSection(cartBillingSection, false);
        this.mCurrentSection = cartBillingSection;
    }

    public void refreshUi() {
        if (this.mCurrentPaymentFormView != null) {
            this.mCurrentPaymentFormView.refreshUi();
        }
    }

    public boolean onBackPressed() {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.confirmBillingViewClosing(CartBillingView.this.getParentBillingSection(), CartBillingView.this.mAutoCheckoutOnCompletion, !(CartBillingView.this.mCurrentSection == CartBillingSection.CREDIT_CARD && (CartBillingView.this.mCurrentPaymentFormView instanceof CreditCardPaymentFormView) && !((CreditCardPaymentFormView) CartBillingView.this.mCurrentPaymentFormView).isFormEmpty()));
            }
        });
        return true;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("SavedStateAutoCheckoutOnCompletion", this.mAutoCheckoutOnCompletion);
        bundle.putSerializable("SavedStateSection", this.mCurrentSection);
        Iterator it = this.mPaymentSections.iterator();
        while (it.hasNext()) {
            PaymentFormView paymentFormView = (PaymentFormView) it.next();
            Bundle bundle2 = new Bundle();
            paymentFormView.handleSaveInstanceState(bundle2);
            bundle.putBundle(paymentFormView.getClass().getCanonicalName(), bundle2);
        }
    }

    public void releaseImages() {
        if (this.mCurrentPaymentFormView != null) {
            this.mCurrentPaymentFormView.releaseImages();
        }
        if (this.mTabHeaderView != null) {
            this.mTabHeaderView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mCurrentPaymentFormView != null) {
            this.mCurrentPaymentFormView.restoreImages();
        }
        if (this.mTabHeaderView != null) {
            this.mTabHeaderView.restoreImages();
        }
    }

    public void recycle() {
        if (this.mCurrentPaymentFormView != null) {
            this.mCurrentPaymentFormView.recycle();
        }
        getViewTreeObserver().removeGlobalOnLayoutListener(this.mLayoutListener);
    }

    public List<WishAnalyticsEvent> getWishAnalyticImpressionEvents() {
        return Collections.singletonList(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_BILLING);
    }

    public void completeBillingSectionSelected(CartBillingSection cartBillingSection) {
        this.mTabHeaderView.completeSectionSelection(cartBillingSection, true);
    }

    public void handleFormComplete() {
        KeyboardUtil.hideKeyboard((View) this);
        final Bundle bundle = new Bundle();
        if (this.mCurrentPaymentFormView != null && this.mCurrentPaymentFormView.canSavePaymentMethod()) {
            boolean z = getCartContext() != null && getCartContext().getCartType() == CartType.COMMERCE_CASH;
            HashMap hashMap = null;
            if (z) {
                hashMap = new HashMap();
                if (this.mCurrentPaymentFormView instanceof BoletoPaymentFormView) {
                    if (getCartContext().getShippingInfo() == null || !getCartContext().getShippingInfo().getCountryCode().equalsIgnoreCase("BR")) {
                        hashMap.put("form_type", "full");
                    } else {
                        hashMap.put("form_type", "short");
                    }
                }
                hashMap.put("payment_type", this.mCurrentPaymentFormView.getPaymentModeName());
            }
            if (!this.mCurrentPaymentFormView.populateAndValidateParameters(bundle)) {
                if (z) {
                    hashMap.put("attempt", "failure");
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_FORM_DONE, hashMap);
                }
                return;
            }
            bundle.putBoolean("paramIsForCommerceLoan", isForCommerceLoan());
            if (z) {
                hashMap.put("attempt", "success");
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_FORM_DONE, hashMap);
            }
            getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                    cartServiceFragment.saveBillingInformation(CartBillingView.this.mTabHeaderView.getSelectedSection(), bundle, CartBillingView.this.getParentBillingSection(), CartBillingView.this.mAutoCheckoutOnCompletion);
                }
            });
        }
    }

    public void selectBillingSection(final CartBillingSection cartBillingSection, boolean z) {
        this.mCompleteFormAfterSelection = z;
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.selectBillingSection(cartBillingSection);
            }
        });
    }

    public void showLoadingDialog() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingDialog() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.hideLoadingDialog();
            }
        });
    }

    public void openActivity(final Class cls) {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, cls);
                cartActivity.startActivity(intent);
            }
        });
    }

    public void showItemsView() {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showItemsView();
            }
        });
    }

    public void showCommerceLoanCCBillingView() {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showCommerceLoanCCBillingView(CartBillingView.this.mAutoCheckoutOnCompletion);
            }
        });
    }

    public void showBrowser(final String str, final boolean z, final boolean z2) {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showBrowser(str, z, z2);
            }
        });
    }

    public void showErrorMessage(final String str) {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showErrorMessage(str);
            }
        });
    }

    public void withServiceProvider(final PaymentFormServiceProviderTask paymentFormServiceProviderTask) {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                paymentFormServiceProviderTask.performTask(cartServiceFragment);
            }
        });
    }

    public CartContext getCartContext() {
        return getCartFragment().getCartContext();
    }

    public void updateActionBar() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.getActionBarManager().setTitle(WishApplication.getInstance().getResources().getString(R.string.billing));
                cartActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.BACK_ARROW);
            }
        });
    }

    public void setReloadCartOnReenter(boolean z) {
        getCartContext().setReloadCartOnReenter(z);
    }
}
