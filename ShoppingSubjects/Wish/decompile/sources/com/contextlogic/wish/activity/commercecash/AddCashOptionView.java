package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.commercecash.CommerceCashCartActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs.PurchaseOption;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.service.standalone.InitiatePayNearMePaymentService;
import com.contextlogic.wish.api.service.standalone.InitiatePayNearMePaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiatePayNearMePaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.HashMap;

public class AddCashOptionView extends FrameLayout {
    ThemedButton mAddCashButton;
    ThemedTextView mAmountText;
    ThemedTextView mBonusText;
    CommerceCashFragment mFragment;
    InitiatePayNearMePaymentService mInitiatePayNearMePaymentService;

    public AddCashOptionView(Context context, CommerceCashFragment commerceCashFragment) {
        this(context, commerceCashFragment, null);
    }

    public AddCashOptionView(Context context, CommerceCashFragment commerceCashFragment, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(commerceCashFragment);
    }

    private void init(CommerceCashFragment commerceCashFragment) {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.commerce_cash_fragment_add_cash_option, this, true);
        this.mAmountText = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_add_cash_amount);
        this.mBonusText = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_add_cash_bonus);
        this.mAddCashButton = (ThemedButton) findViewById(R.id.commerce_cash_fragment_add_cash_button);
        this.mFragment = commerceCashFragment;
        this.mInitiatePayNearMePaymentService = new InitiatePayNearMePaymentService();
    }

    public void setOption(final PurchaseOption purchaseOption, WishCommerceCashUserInfo wishCommerceCashUserInfo) {
        if (purchaseOption != null && wishCommerceCashUserInfo != null) {
            WishLocalizedCurrencyValue amount = purchaseOption.getAmount();
            WishLocalizedCurrencyValue bonus = purchaseOption.getBonus();
            this.mAmountText.setText(amount.toFormattedString(false, true));
            this.mBonusText.setText(WishApplication.getInstance().getString(R.string.wish_cash_get_bonus, new Object[]{bonus.toFormattedString(false, true)}));
            if (!purchaseOption.hasBonus()) {
                this.mBonusText.setVisibility(8);
            }
            this.mAddCashButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (ExperimentDataCenter.getInstance().shouldSeePayNearMe()) {
                        AddCashOptionView.this.mFragment.showLoadingSpinner();
                        final HashMap hashMap = new HashMap();
                        hashMap.put("cart_type", CartType.COMMERCE_CASH.toString());
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_CASH_ADD_CASH_BUTTON, hashMap);
                        AddCashOptionView.this.mInitiatePayNearMePaymentService.requestService(purchaseOption.getAmount().getValue(), new SuccessCallback() {
                            public void onSuccess(String str) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_CASH_ADD_CASH_SUCCESS, hashMap);
                                Intent intent = new Intent();
                                intent.setClass(AddCashOptionView.this.getContext(), WebViewActivity.class);
                                intent.putExtra("ExtraUrl", str);
                                AddCashOptionView.this.getContext().startActivity(intent);
                            }
                        }, new FailureCallback() {
                            public void onFailure(String str, int i) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_CASH_ADD_CASH_FAILURE, hashMap);
                                if (str == null) {
                                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
                                }
                                AddCashOptionView.this.mFragment.showErrorMessage(str);
                            }
                        });
                        return;
                    }
                    double value = purchaseOption.getAmount().getValue();
                    Intent intent = new Intent();
                    intent.setClass(AddCashOptionView.this.getContext(), CommerceCashCartActivity.class);
                    intent.putExtra(CommerceCashCartActivity.EXTRA_COMMERCE_CASH_CART_AMOUNT, value);
                    AddCashOptionView.this.getContext().startActivity(intent);
                }
            });
        }
    }
}
