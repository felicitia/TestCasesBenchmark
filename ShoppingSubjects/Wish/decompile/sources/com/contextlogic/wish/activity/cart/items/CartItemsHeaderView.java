package com.contextlogic.wish.activity.cart.items;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.commerceloan.CommerceLoanRepaymentBannerView;
import com.contextlogic.wish.activity.commerceloan.CommerceLoanBannerView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CartItemsHeaderView extends LinearLayout {
    private ThemedTextView mBillingInfo;
    private View mBillingInfoChevron;
    private View mBillingInfoContainer;
    /* access modifiers changed from: private */
    public CartContext mCartContext;
    /* access modifiers changed from: private */
    public final CartItemsView mCartItemsView;
    private CommerceLoanBannerView mCommerceLoanBannerView;
    private CommerceLoanRepaymentBannerView mCommerceLoanRepaymentBannerView;
    private boolean mDisableEditingPaymentInfo;
    private AutoReleasableImageView mPaymentDueChevron;
    private View mPaymentDueContainer;
    private ThemedTextView mPaymentDueInfo;
    private View mShippingAndBillingDivider;
    private ThemedTextView mShippingInfo;
    private View mShippingInfoContainer;

    public CartItemsHeaderView(Context context, CartItemsView cartItemsView, CartContext cartContext) {
        super(context);
        this.mCartItemsView = cartItemsView;
        init(cartContext);
    }

    private void init(final CartContext cartContext) {
        this.mCartContext = cartContext;
        setOrientation(1);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_items_header_view_modular, this);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.header_view_section_title);
        themedTextView.setText(getResources().getString(R.string.shipping_and_payment));
        if (!(cartContext.getCommerceCashCart() == null && cartContext.getCommerceLoanCart() == null)) {
            themedTextView.setText(getResources().getString(R.string.billing_info));
        }
        this.mShippingInfo = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_header_shipping_info);
        this.mShippingInfoContainer = inflate.findViewById(R.id.cart_fragment_cart_items_header_shipping_info_container);
        this.mShippingInfo.setText(cartContext.getShippingDescriptionText());
        this.mShippingInfoContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_EDIT_SHIPPING);
                CartItemsHeaderView.this.mCartItemsView.editShipping();
            }
        });
        this.mShippingAndBillingDivider = inflate.findViewById(R.id.cart_fragment_cart_items_header_commerce_loan_divider);
        this.mBillingInfo = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_header_billing_info);
        this.mBillingInfoContainer = inflate.findViewById(R.id.cart_fragment_cart_items_header_billing_info_container);
        this.mBillingInfoContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!CartItemsHeaderView.this.shouldDisableEditingPaymentInfo()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("cart_type", cartContext.getCartType().toString());
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_EDIT_BILLING, hashMap);
                    CartItemsHeaderView.this.mCartItemsView.editBilling();
                }
            }
        });
        this.mBillingInfoChevron = inflate.findViewById(R.id.cart_fragment_cart_items_header_billing_chevron);
        this.mPaymentDueInfo = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_header_payment_due_info);
        this.mPaymentDueChevron = (AutoReleasableImageView) inflate.findViewById(R.id.cart_fragment_cart_items_header_payment_due_chevron);
        this.mPaymentDueContainer = inflate.findViewById(R.id.cart_fragment_cart_items_header_payment_due_container);
        this.mPaymentDueContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_ITEMS_HEADER_DATE);
                if (CartItemsHeaderView.this.mCartContext == null || !ExperimentDataCenter.getInstance().canCheckoutWithCommerceLoan(CartItemsHeaderView.this.mCartContext) || !CartItemsHeaderView.this.mCartContext.hasCommerceLoanBillingInfo(true)) {
                    CartItemsHeaderView.this.mCartItemsView.handleInvalidCommerceLoan();
                    return;
                }
                final Calendar instance = Calendar.getInstance();
                if (CartItemsHeaderView.this.mCartContext.getUserBillingInfo().getCommerceLoanInfo().getPreferredDueDate() != null) {
                    instance.setTime(CartItemsHeaderView.this.mCartContext.getUserBillingInfo().getCommerceLoanInfo().getPreferredDueDate());
                }
                CartItemsHeaderView.this.mCartItemsView.getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                        cartServiceFragment.showCommerceLoanDatePicker(CartItemsHeaderView.this.mCartContext.getCommerceLoanTabSpec().getMaxPaymentDays(), instance, new OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                                Calendar instance = Calendar.getInstance();
                                instance.set(i, i2, i3);
                                CartItemsHeaderView.this.mCartItemsView.updateCommerceLoanPreferredDueDate(instance.getTime());
                            }
                        });
                    }
                });
            }
        });
        this.mCommerceLoanBannerView = (CommerceLoanBannerView) inflate.findViewById(R.id.cart_fragment_cart_items_header_commerce_loan_banner);
        this.mCommerceLoanRepaymentBannerView = (CommerceLoanRepaymentBannerView) inflate.findViewById(R.id.cart_fragment_cart_items_header_commerce_loan_repayment_banner);
        setUpCommerceLoanRepaymentBanner();
        updateCartContext(cartContext);
    }

    public void setUpCommerceLoanRepaymentBanner() {
        if (this.mCartContext.getLoanRepaymentBannerSpec() != null && this.mCartContext.getLoanRepaymentBannerSpec().getSuccessSheetTitle() != null) {
            this.mCommerceLoanRepaymentBannerView.setup(this.mCartItemsView);
        }
    }

    public void updateCommerceLoanRepaymentBanner() {
        WishLoanRepaymentBannerSpec loanRepaymentBannerSpec = this.mCartContext.getLoanRepaymentBannerSpec();
        if (loanRepaymentBannerSpec == null || loanRepaymentBannerSpec.getSuccessSheetTitle() == null) {
            this.mCommerceLoanRepaymentBannerView.setVisibility(8);
        } else {
            this.mCommerceLoanRepaymentBannerView.setVisibility(0);
        }
    }

    public void updateCartContext(CartContext cartContext) {
        this.mCartContext = cartContext;
        if (!this.mCartContext.hasValidShippingInfo() || !this.mCartContext.getCheckoutActionManager().canShowPaymentCredentials() || this.mCartContext.getCartType() == CartType.COMMERCE_CASH) {
            this.mShippingInfoContainer.setVisibility(8);
        } else {
            this.mShippingInfoContainer.setVisibility(0);
            String shippingDescriptionText = this.mCartContext.getShippingDescriptionText();
            if (shippingDescriptionText != null) {
                this.mShippingInfo.setText(shippingDescriptionText);
            } else {
                this.mShippingInfoContainer.setVisibility(8);
            }
        }
        this.mShippingAndBillingDivider.setVisibility(8);
        this.mPaymentDueContainer.setVisibility(8);
        if (this.mCartContext.isFreeOrder() || ((!this.mCartContext.hasValidBillingInfo() && !this.mCartContext.getCheckoutActionManager().alwaysShowPaymentCredentials()) || !this.mCartContext.getCheckoutActionManager().canShowPaymentCredentials() || this.mCartContext.isCommerceCashMissingValidShippingInfo())) {
            this.mBillingInfoContainer.setVisibility(8);
        } else {
            this.mBillingInfoContainer.setVisibility(0);
            String paymentCredentialsDescriptionText = this.mCartContext.getPaymentCredentialsDescriptionText();
            if (paymentCredentialsDescriptionText != null) {
                this.mBillingInfo.setText(paymentCredentialsDescriptionText);
                if (this.mShippingInfoContainer.getVisibility() == 0) {
                    this.mShippingAndBillingDivider.setVisibility(0);
                }
                if (this.mCartContext.getEffectivePaymentMode().equals("PaymentModeCommerceLoan") && this.mCartContext.hasCommerceLoanBillingInfo(true)) {
                    this.mPaymentDueContainer.setVisibility(0);
                    updatePaymentDueDate(this.mCartContext.getUserBillingInfo().getCommerceLoanInfo().getPreferredDueDate());
                }
            } else {
                this.mBillingInfoContainer.setVisibility(8);
            }
        }
        updateCommerceLoanRepaymentBanner();
        if (ExperimentDataCenter.getInstance().canSeeCommerceLoanBillingOption() && !cartContext.isFreeOrder() && cartContext.getCommerceLoanBannerSpec() != null) {
            this.mCommerceLoanBannerView.setup(cartContext.getCommerceLoanBannerSpec(), this.mCartItemsView.getCartFragment());
            this.mCommerceLoanBannerView.setVisibility(0);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_COMMERCE_LOAN_BANNER);
        }
        PreferenceUtil.setLong("TempLoanPreferredDueDate", -1);
    }

    public boolean hasContent() {
        boolean z = (this.mBillingInfoContainer == null || this.mBillingInfoContainer.getVisibility() == 8) ? false : true;
        if (this.mShippingInfoContainer == null || this.mShippingInfoContainer.getVisibility() == 8) {
            return z;
        }
        return true;
    }

    private void updatePaymentDueDate(Date date) {
        if (date == null || !date.after(Calendar.getInstance().getTime())) {
            this.mPaymentDueInfo.setText(WishApplication.getInstance().getResources().getString(R.string.choose_payment_date));
            this.mPaymentDueInfo.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            this.mPaymentDueChevron.setImageResource(R.drawable.chevron_colored_right);
            return;
        }
        this.mPaymentDueInfo.setText(DateUtil.getLocalizedReadableDate(date));
        this.mPaymentDueInfo.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray2));
        this.mPaymentDueChevron.setImageResource(R.drawable.chevron_right);
    }

    /* access modifiers changed from: private */
    public boolean shouldDisableEditingPaymentInfo() {
        return this.mDisableEditingPaymentInfo;
    }

    public void setEditingPaymentInfoDisabled(boolean z) {
        this.mDisableEditingPaymentInfo = z;
        this.mBillingInfoChevron.setVisibility(z ? 8 : 0);
        LayoutParams layoutParams = (LayoutParams) this.mBillingInfo.getLayoutParams();
        layoutParams.addRule(11);
        this.mBillingInfo.setLayoutParams(layoutParams);
    }
}
