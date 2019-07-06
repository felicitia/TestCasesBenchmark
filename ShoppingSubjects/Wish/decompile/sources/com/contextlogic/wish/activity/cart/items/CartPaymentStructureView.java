package com.contextlogic.wish.activity.cart.items;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.commerceloan.CommerceLoanPayHalfLearnMoreActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCommerceLoanInfo;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.util.DateUtil;
import java.util.Calendar;
import java.util.Date;

public class CartPaymentStructureView extends LinearLayout {
    /* access modifiers changed from: private */
    public CartContext mCartContext;
    /* access modifiers changed from: private */
    public CartFragment mCartFragment;
    private TextView mHowToPayText;
    /* access modifiers changed from: private */
    public Date mInstallmentDueDate;
    /* access modifiers changed from: private */
    public TextView mInstallmentDueDateSelector;
    private TextView mLearnMore;
    /* access modifiers changed from: private */
    public int mMaxDaysForPayment;
    /* access modifiers changed from: private */
    public AppCompatRadioButton mPayFullOption;
    private TextView mPayFullText;
    /* access modifiers changed from: private */
    public AppCompatRadioButton mPayHalfOption;
    /* access modifiers changed from: private */
    public TextView mPayHalfSubText;
    private TextView mPayHalfText;
    /* access modifiers changed from: private */
    public WishPaymentStructureSelectionSpec mPaymentStructureSpec;

    public CartPaymentStructureView(Context context) {
        super(context);
    }

    public CartPaymentStructureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CartPaymentStructureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup(CartFragment cartFragment) {
        this.mCartFragment = cartFragment;
        this.mCartContext = cartFragment.getCartContext();
        this.mPaymentStructureSpec = this.mCartContext.getPaymentStructureSelectionSpec();
        initializeUi();
    }

    public void initializeUi() {
        setOrientation(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_payment_structure, this, true);
        this.mHowToPayText = (TextView) inflate.findViewById(R.id.cart_how_want_to_pay_text);
        this.mLearnMore = (TextView) inflate.findViewById(R.id.cart_pay_half_learn_more_link);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.cart_pay_half_option_layout);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.cart_pay_full_amount_option_layout);
        this.mPayFullText = (TextView) inflate.findViewById(R.id.cart_pay_full_amount_option_text);
        this.mPayHalfText = (TextView) inflate.findViewById(R.id.cart_pay_half_option_text);
        this.mPayHalfSubText = (TextView) inflate.findViewById(R.id.cart_pay_half_option_subtext);
        this.mPayFullOption = (AppCompatRadioButton) inflate.findViewById(R.id.cart_pay_full_amount_radio_button);
        this.mPayHalfOption = (AppCompatRadioButton) inflate.findViewById(R.id.cart_pay_half_radio_button);
        this.mInstallmentDueDateSelector = (TextView) inflate.findViewById(R.id.installment_due_date_selector);
        this.mInstallmentDueDateSelector.setVisibility(8);
        this.mLearnMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAY_HALF_LEARN_MORE_VIEW);
                CartPaymentStructureView.this.mCartFragment.withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        Intent intent = new Intent();
                        intent.setClass(cartActivity, CommerceLoanPayHalfLearnMoreActivity.class);
                        cartActivity.startActivity(intent);
                    }
                });
            }
        });
        AnonymousClass2 r0 = new OnClickListener() {
            public void onClick(View view) {
                if (CartPaymentStructureView.this.mCartContext.getPayHalfLaterFlag()) {
                    CartPaymentStructureView.this.mPayFullOption.setChecked(true);
                    CartPaymentStructureView.this.mPayHalfOption.setChecked(false);
                    CartPaymentStructureView.this.mCartContext.updatePayHalfLaterFlag(false);
                    CartItemsFooterView cartItemsFooter = CartPaymentStructureView.this.mCartFragment.getCartItemsFooter();
                    if (cartItemsFooter != null) {
                        cartItemsFooter.updateInstallmentsSummaryRows();
                        cartItemsFooter.repopulateSummaryRows();
                    }
                    CartPaymentStructureView.this.mInstallmentDueDateSelector.setVisibility(8);
                    CartPaymentStructureView.this.mPayHalfSubText.setText(CartPaymentStructureView.this.mPaymentStructureSpec.getPayHalfNowSubText());
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAY_FULL_OPTION);
                }
            }
        };
        AnonymousClass3 r3 = new OnClickListener() {
            public void onClick(View view) {
                if (!CartPaymentStructureView.this.mCartContext.getPayHalfLaterFlag() && CartPaymentStructureView.this.mPaymentStructureSpec.getCanPayLater()) {
                    CartPaymentStructureView.this.mPayFullOption.setChecked(false);
                    CartPaymentStructureView.this.mPayHalfOption.setChecked(true);
                    CartPaymentStructureView.this.mCartContext.updatePayHalfLaterFlag(true);
                    CartItemsFooterView cartItemsFooter = CartPaymentStructureView.this.mCartFragment.getCartItemsFooter();
                    if (cartItemsFooter != null) {
                        cartItemsFooter.updateInstallmentsSummaryRows();
                        cartItemsFooter.repopulateSummaryRows();
                    }
                    if (CartPaymentStructureView.this.mInstallmentDueDate == null) {
                        CartPaymentStructureView.this.showInstallmentDatePicker();
                    }
                    CartPaymentStructureView.this.mInstallmentDueDateSelector.setVisibility(0);
                    CartPaymentStructureView.this.mPayHalfSubText.setText(CartPaymentStructureView.this.mPaymentStructureSpec.getPayHalfNowSubText());
                    if (CartPaymentStructureView.this.mInstallmentDueDate != null) {
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(CartPaymentStructureView.this.mInstallmentDueDate);
                        CartPaymentStructureView.this.updateInstallmentDueDate(instance, false);
                    }
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAY_HALF_OPTION);
                }
            }
        };
        linearLayout2.setOnClickListener(r0);
        this.mPayFullOption.setOnClickListener(r0);
        linearLayout.setOnClickListener(r3);
        this.mPayHalfOption.setOnClickListener(r3);
        this.mInstallmentDueDateSelector.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartPaymentStructureView.this.showInstallmentDatePicker();
            }
        });
    }

    public void refreshUi() {
        this.mPaymentStructureSpec = this.mCartContext.getPaymentStructureSelectionSpec();
        this.mHowToPayText.setText(this.mPaymentStructureSpec.getTitle());
        this.mLearnMore.setText(this.mPaymentStructureSpec.getLearnMoreText());
        this.mMaxDaysForPayment = this.mPaymentStructureSpec.getMaxDaysForPayment();
        this.mPayFullText.setText(this.mPaymentStructureSpec.getFullAmountText());
        this.mPayHalfText.setText(this.mPaymentStructureSpec.getPayHalfNowText());
        if (!this.mCartContext.getPayHalfLaterFlag() || this.mInstallmentDueDate == null) {
            this.mPayHalfSubText.setText(this.mPaymentStructureSpec.getPayHalfNowSubText());
        } else {
            updateInstallmentDueDate(null, false);
        }
        if (this.mPaymentStructureSpec.getCanPayLater()) {
            this.mPayHalfText.setTextColor(getResources().getColor(R.color.gray1));
            if (!this.mCartContext.getPayHalfLaterFlag()) {
                this.mPayHalfOption.setChecked(false);
            }
            this.mPayHalfOption.setEnabled(true);
            return;
        }
        this.mPayFullOption.setChecked(true);
        this.mPayHalfOption.setChecked(false);
        this.mPayHalfOption.setEnabled(false);
        this.mCartContext.updatePayHalfLaterFlag(false);
        if (this.mCartFragment.getCartItemsFooter() != null) {
            this.mCartFragment.getCartItemsFooter().updateInstallmentsSummaryRows();
        }
        this.mPayHalfText.setTextColor(getResources().getColor(R.color.gray4));
        this.mPayHalfText.setText(this.mPaymentStructureSpec.getPayHalfNowText());
        this.mPayHalfSubText.setText(this.mPaymentStructureSpec.getPayHalfNowSubText());
        this.mInstallmentDueDateSelector.setVisibility(8);
    }

    public String getFirstInstallmentAmountText() {
        return this.mPaymentStructureSpec.getFirstAmountText();
    }

    public String getSecondInstallmentAmountText() {
        return this.mPaymentStructureSpec.getSecondAmountText();
    }

    public String getLocalizedDueDate() {
        if (this.mInstallmentDueDate == null) {
            return null;
        }
        return DateUtil.getLocalizedDate(this.mInstallmentDueDate);
    }

    /* access modifiers changed from: private */
    public void showInstallmentDatePicker() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAY_HALF_ADJUST_DATE);
        final Calendar instance = Calendar.getInstance();
        WishCommerceLoanInfo commerceLoanInfo = this.mCartContext.getUserBillingInfo().getCommerceLoanInfo();
        Date preferredDueDate = commerceLoanInfo != null ? commerceLoanInfo.getPreferredDueDate() : null;
        if (preferredDueDate != null) {
            instance.setTime(preferredDueDate);
        }
        this.mCartFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.showCommerceLoanDatePicker(CartPaymentStructureView.this.mMaxDaysForPayment, instance, new OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        Calendar instance = Calendar.getInstance();
                        instance.set(i, i2, i3);
                        CartPaymentStructureView.this.updateInstallmentDueDate(instance, true);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateInstallmentDueDate(Calendar calendar, boolean z) {
        if (z) {
            this.mInstallmentDueDate = calendar.getTime();
            this.mCartFragment.updateCommerceLoanPreferredDueDate(this.mInstallmentDueDate);
            if (this.mCartFragment.getCartItemsFooter() != null) {
                this.mCartFragment.getCartItemsFooter().updateInstallmentsSummaryRows();
            }
        }
        String localizedDueDate = getLocalizedDueDate();
        this.mInstallmentDueDateSelector.setText(String.format(this.mPaymentStructureSpec.getInstallmentDueDateText(), new Object[]{localizedDueDate}));
        this.mPayHalfSubText.setText(String.format(this.mPaymentStructureSpec.getWillChargeCardText(), new Object[]{localizedDueDate}));
    }
}
