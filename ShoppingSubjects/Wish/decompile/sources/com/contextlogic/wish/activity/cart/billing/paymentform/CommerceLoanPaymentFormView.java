package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.commerceloanform.CommerceLoanOptionView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.activity.commerceloan.CommerceLoanLearnMoreActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCommerceLoanInfo;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.Calendar;
import java.util.Date;

public class CommerceLoanPaymentFormView extends PaymentFormView {
    /* access modifiers changed from: private */
    public CartFragment mCartFragment;
    private WishCommerceLoanInfo mCommerceLoanInfo;
    /* access modifiers changed from: private */
    public WishCommerceLoanTabSpec mCommerceLoanTabSpec;
    private ThemedTextView mLearnMore;
    private ThemedTextView mNewBadge;
    /* access modifiers changed from: private */
    public CommerceLoanOptionView mPaymentDueOptionView;
    /* access modifiers changed from: private */
    public CommerceLoanOptionView mPaymentMethodOptionView;
    private PaymentProcessor mPaymentProcessor = null;
    /* access modifiers changed from: private */
    public Date mPreferredPaymentDueDate = null;
    private RelativeLayout mStatusContainer;
    private ThemedTextView mStatusMessage;
    private ThemedTextView mStatusTitle;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

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

    public CommerceLoanPaymentFormView(Context context) {
        super(context);
    }

    public CommerceLoanPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CommerceLoanPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_commerce_loan, this);
        CartContext cartContext = getUiConnector().getCartContext();
        this.mCommerceLoanTabSpec = cartContext.getCommerceLoanTabSpec();
        if (cartContext.getUserBillingInfo() != null) {
            this.mCommerceLoanInfo = cartContext.getUserBillingInfo().getCommerceLoanInfo();
        }
        this.mStatusContainer = (RelativeLayout) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_status);
        if (this.mCommerceLoanTabSpec == null) {
            this.mStatusContainer.setVisibility(0);
            return;
        }
        this.mNewBadge = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_new_badge);
        if (this.mCommerceLoanTabSpec.isNew()) {
            this.mNewBadge.setVisibility(0);
        }
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_title);
        this.mTitle.setText(this.mCommerceLoanTabSpec.getTitle());
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_subtitle);
        this.mSubtitle.setText(this.mCommerceLoanTabSpec.getDescription());
        this.mLearnMore = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_learn_more);
        this.mLearnMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommerceLoanPaymentFormView.this.getUiConnector().openActivity(CommerceLoanLearnMoreActivity.class);
            }
        });
        this.mStatusTitle = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_status_title);
        this.mStatusMessage = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_status_message);
        if (!this.mCommerceLoanTabSpec.canPayLater()) {
            this.mStatusContainer.setVisibility(0);
            this.mStatusTitle.setText(this.mCommerceLoanTabSpec.getInvalidBannerTitle());
            this.mStatusMessage.setText(this.mCommerceLoanTabSpec.getInvalidReason());
        } else {
            this.mStatusContainer.setVisibility(8);
        }
        this.mPaymentDueOptionView = (CommerceLoanOptionView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_payment_due_view);
        this.mPaymentDueOptionView.setIcon(WishApplication.getInstance().getResources().getDrawable(R.drawable.commerce_loan_calendar_icon));
        if (this.mCommerceLoanTabSpec.hasOutstandingLoan()) {
            this.mPaymentDueOptionView.setTitle(this.mCommerceLoanTabSpec.getOutstandingLoanText());
        } else if (PreferenceUtil.getLong("TempLoanPreferredDueDate", -1) != -1) {
            updatePaymentDueDate(new Date(PreferenceUtil.getLong("TempLoanPreferredDueDate", -1)));
        } else if (this.mCommerceLoanInfo != null) {
            updatePaymentDueDate(this.mCommerceLoanInfo.getPreferredDueDate());
        } else {
            updatePaymentDueDate(null);
        }
        this.mPaymentDueOptionView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CommerceLoanPaymentFormView.this.mPaymentDueOptionView.isEnabled() && CommerceLoanPaymentFormView.this.mCartFragment != null) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAYMENT_FORM_DATE);
                    final Calendar instance = Calendar.getInstance();
                    if (CommerceLoanPaymentFormView.this.mPreferredPaymentDueDate != null) {
                        instance.setTime(CommerceLoanPaymentFormView.this.mPreferredPaymentDueDate);
                    }
                    CommerceLoanPaymentFormView.this.mCartFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                            cartServiceFragment.showCommerceLoanDatePicker(CommerceLoanPaymentFormView.this.mCommerceLoanTabSpec.getMaxPaymentDays(), instance, new OnDateSetListener() {
                                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                                    Calendar instance = Calendar.getInstance();
                                    instance.set(i, i2, i3);
                                    CommerceLoanPaymentFormView.this.updatePaymentDueDate(instance.getTime());
                                }
                            });
                        }
                    });
                }
            }
        });
        this.mPaymentMethodOptionView = (CommerceLoanOptionView) inflate.findViewById(R.id.cart_fragment_payment_form_commerce_loan_payment_method_view);
        this.mPaymentMethodOptionView.setIcon(WishApplication.getInstance().getResources().getDrawable(R.drawable.commerce_loan_credit_card_icon));
        if (this.mCommerceLoanTabSpec.hasOutstandingLoan() && this.mCommerceLoanInfo != null && this.mCommerceLoanInfo.getCreditCardInfo() != null) {
            WishCreditCardInfo creditCardInfo = this.mCommerceLoanInfo.getCreditCardInfo();
            String creditCardTypeDisplayString = CreditCardUtil.getCreditCardTypeDisplayString(creditCardInfo.getCardType());
            String string = WishApplication.getInstance().getString(R.string.card_ending_in, new Object[]{creditCardInfo.getLastFourDigits()});
            this.mPaymentMethodOptionView.setTitle(creditCardTypeDisplayString);
            this.mPaymentMethodOptionView.setSubtitle(string);
        } else if (this.mCommerceLoanInfo != null && this.mCommerceLoanInfo.getCreditCardInfo() != null) {
            WishCreditCardInfo creditCardInfo2 = this.mCommerceLoanInfo.getCreditCardInfo();
            String creditCardTypeDisplayString2 = CreditCardUtil.getCreditCardTypeDisplayString(creditCardInfo2.getCardType());
            String string2 = WishApplication.getInstance().getString(R.string.card_ending_in, new Object[]{creditCardInfo2.getLastFourDigits()});
            this.mPaymentMethodOptionView.setTitle(creditCardTypeDisplayString2);
            this.mPaymentMethodOptionView.setSubtitle(string2);
            this.mPaymentMethodOptionView.setSelectText(WishApplication.getInstance().getString(R.string.change));
            this.mPaymentProcessor = this.mCommerceLoanInfo.getCreditCardInfo().getPaymentProcessor();
        } else if (cartContext.hasCreditCardBillingInfo()) {
            WishCreditCardInfo defaultCreditCardInfo = cartContext.getUserBillingInfo().getDefaultCreditCardInfo(cartContext.getPaymentProcessor());
            String creditCardTypeDisplayString3 = CreditCardUtil.getCreditCardTypeDisplayString(defaultCreditCardInfo.getCardType());
            String string3 = WishApplication.getInstance().getString(R.string.card_ending_in, new Object[]{defaultCreditCardInfo.getLastFourDigits()});
            this.mPaymentMethodOptionView.setTitle(creditCardTypeDisplayString3);
            this.mPaymentMethodOptionView.setSubtitle(string3);
            this.mPaymentMethodOptionView.setSelectText(WishApplication.getInstance().getString(R.string.change));
            this.mPaymentProcessor = cartContext.getPaymentProcessor();
        } else {
            this.mPaymentMethodOptionView.setSelectText(StringUtil.toTitleCase(WishApplication.getInstance().getString(R.string.choose_payment_method)));
        }
        this.mPaymentMethodOptionView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CommerceLoanPaymentFormView.this.mPaymentMethodOptionView.isEnabled()) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_PAYMENT_FORM_CARD);
                    CommerceLoanPaymentFormView.this.getUiConnector().showCommerceLoanCCBillingView();
                }
            }
        });
        if (this.mCommerceLoanTabSpec.isAboveMaxAmount() && !this.mCommerceLoanTabSpec.hasOutstandingLoan()) {
            this.mPaymentDueOptionView.setDisabled();
            this.mPaymentMethodOptionView.setDisabled();
        }
    }

    /* access modifiers changed from: private */
    public void updatePaymentDueDate(Date date) {
        if (date == null || !date.after(Calendar.getInstance().getTime())) {
            this.mPaymentDueOptionView.setTitle(WishApplication.getInstance().getString(R.string.mm_dd_yy));
            this.mPaymentDueOptionView.setSelectText(WishApplication.getInstance().getString(R.string.choose_date));
        } else {
            this.mPreferredPaymentDueDate = date;
            PreferenceUtil.setLong("TempLoanPreferredDueDate", date.getTime());
            this.mPaymentDueOptionView.setTitle(DateUtil.getLocalizedReadableDate(date));
            this.mPaymentDueOptionView.setSelectText(WishApplication.getInstance().getString(R.string.change));
        }
        getUiConnector().refreshNextButtonState();
    }

    public boolean populateAndValidateParameters(Bundle bundle) {
        bundle.putString("paramDueDate", DateUtil.isoDateFromDate(this.mPreferredPaymentDueDate));
        bundle.putInt("paramPaymentProcessor", this.mPaymentProcessor.getValue());
        return true;
    }

    public void onAvailableContentHeightChanged(int i) {
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = i;
        setLayoutParams(layoutParams);
    }

    public boolean canSavePaymentMethod() {
        return (this.mCommerceLoanTabSpec == null || !this.mCommerceLoanTabSpec.canPayLater() || this.mPreferredPaymentDueDate == null || this.mPaymentProcessor == null) ? false : true;
    }

    public void setFragment(CartFragment cartFragment) {
        this.mCartFragment = cartFragment;
    }

    public String getPaymentModeName() {
        return CartBillingSection.COMMERCE_LOAN.name();
    }
}
