package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.commercecash.CommerceCashTermsActivity;
import com.contextlogic.wish.activity.returnpolicy.ReturnPolicyActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartSummaryItem;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class CartItemsFooterView extends LinearLayout {
    private CartContext mCartContext;
    private CartItemsView mCartItemsView;
    private CartPaymentStructureView mCartPaymentStructureView;
    private LinearLayout mCartSummaryContainer;
    private ThemedTextView mCartSummaryFirstInstallmentName;
    private LinearLayout mCartSummaryFirstInstallmentRow;
    private ThemedTextView mCartSummaryFirstInstallmentValue;
    private View mCartSummaryInstallmentRowsSeparator;
    private ThemedTextView mCartSummarySecondInstallmentName;
    private LinearLayout mCartSummarySecondInstallmentRow;
    private ThemedTextView mCartSummarySecondInstallmentValue;
    private TextView mCommerceCashReturnPolicyText;
    private TextView mCommerceCashTermsText;
    private View mOrderSummaryHeading;
    private View mOrderSummarySeparator;
    private CartItemsPromoCodeView mPromoCodeView;
    private TextView mReturnPolicyText;

    public void onStop() {
    }

    public CartItemsFooterView(Context context, CartItemsView cartItemsView, CartContext cartContext) {
        super(context);
        this.mCartItemsView = cartItemsView;
        init(context, cartContext);
    }

    private void init(Context context, CartContext cartContext) {
        setOrientation(1);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.cart_items_footer_view_modular, this);
        this.mCartPaymentStructureView = (CartPaymentStructureView) inflate.findViewById(R.id.cart_payment_structure_view);
        this.mCommerceCashReturnPolicyText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_footer_commerce_cash_return_policy);
        this.mCommerceCashTermsText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_footer_commerce_cash_terms);
        this.mReturnPolicyText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_footer_return_policy);
        this.mReturnPolicyText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemsFooterView.this.showReturnPolicy();
            }
        });
        this.mOrderSummaryHeading = inflate.findViewById(R.id.cart_fragment_cart_items_footer_order_summary_heading);
        this.mOrderSummarySeparator = inflate.findViewById(R.id.cart_fragment_cart_items_footer_summary_separator);
        int i = 8;
        if (cartContext != null && (cartContext.getCart() == null || cartContext.getCart().getItems().size() == 0)) {
            this.mReturnPolicyText.setVisibility(8);
        }
        this.mCartSummaryContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_footer_summary_container);
        if (cartContext != null && cartContext.getCommerceLoanCart() == null) {
            if (cartContext.getCommerceCashCart() == null) {
                this.mPromoCodeView = (CartItemsPromoCodeView) inflate.findViewById(R.id.cart_fragment_cart_items_footer_promo_code_view);
                this.mPromoCodeView.setup(this.mCartItemsView.getCartFragment());
                this.mPromoCodeView.setVisibility(0);
            } else {
                this.mCommerceCashReturnPolicyText.setVisibility(0);
                this.mCommerceCashReturnPolicyText.setText(cartContext.getCommerceCashCart().getRefundPolicy());
                if (!cartContext.getCommerceCashCart().shouldHideTermsOfCondition()) {
                    this.mCommerceCashTermsText.setVisibility(0);
                    this.mCommerceCashTermsText.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            CartItemsFooterView.this.showCommerceCashTerms();
                        }
                    });
                }
            }
        }
        if (cartContext != null) {
            boolean z = cartContext.getEffectivePaymentMode(true).equals("PaymentModeCC") && cartContext.getUserBillingInfo() != null && ExperimentDataCenter.getInstance().canSeePayHalfBillingOption() && cartContext.getPaymentStructureSelectionSpec() != null;
            if (z) {
                this.mCartPaymentStructureView.setup(this.mCartItemsView.getCartFragment());
            }
            this.mCartPaymentStructureView.setVisibility(z ? 0 : 8);
            View findViewById = inflate.findViewById(R.id.cart_payment_structure_view_separator);
            if (z) {
                i = 0;
            }
            findViewById.setVisibility(i);
            cartContext.updatePayHalfLaterFlag(false);
            if (z) {
                this.mCartSummaryInstallmentRowsSeparator = inflate.findViewById(R.id.cart_fragment_installment_rows_separator);
                this.mCartSummaryFirstInstallmentRow = (LinearLayout) inflate.findViewById(R.id.cart_fragment_first_installment_row);
                this.mCartSummaryFirstInstallmentName = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_first_installment_row_name);
                this.mCartSummaryFirstInstallmentValue = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_first_installment_row_value);
                this.mCartSummarySecondInstallmentRow = (LinearLayout) inflate.findViewById(R.id.cart_fragment_second_installment_row);
                this.mCartSummarySecondInstallmentName = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_second_installment_row_name);
                this.mCartSummarySecondInstallmentValue = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_second_installment_row_value);
                this.mCartSummaryFirstInstallmentName.setText(R.string.due_now);
                this.mCartSummaryFirstInstallmentValue.setText(this.mCartPaymentStructureView.getFirstInstallmentAmountText());
                this.mCartSummarySecondInstallmentName.setText(getResources().getString(R.string.due_on_day, new Object[]{this.mCartPaymentStructureView.getLocalizedDueDate()}));
                this.mCartSummarySecondInstallmentValue.setText(this.mCartPaymentStructureView.getSecondInstallmentAmountText());
            }
            updateCartContext(cartContext);
        }
    }

    /* access modifiers changed from: private */
    public void showCommerceCashTerms() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_CASH_TERMS_CART_VIEW);
        this.mCartItemsView.getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, CommerceCashTermsActivity.class);
                cartActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showReturnPolicy() {
        this.mCartItemsView.getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, ReturnPolicyActivity.class);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RETURN_POLICY_FROM_CART);
                cartActivity.startActivity(intent);
            }
        });
    }

    public void updateUiWithDefaultedLoan() {
        int i = this.mCartContext.hasDefaultedLoan() ? 8 : 0;
        this.mCartItemsView.setCartButtonVisibility(i);
        if (this.mOrderSummaryHeading != null) {
            this.mOrderSummaryHeading.setVisibility(i);
        }
        this.mOrderSummarySeparator.setVisibility(i);
        this.mCartSummaryContainer.setVisibility(i);
        if (this.mPromoCodeView != null) {
            this.mPromoCodeView.setVisibility(i);
        }
    }

    public void repopulateSummaryRows() {
        String effectivePaymentMode = this.mCartContext.getEffectivePaymentMode();
        if (this.mCartContext.getPayHalfLaterFlag()) {
            effectivePaymentMode = "PaymentModeCommerceLoan";
        }
        ArrayList<WishCartSummaryItem> summaryItems = this.mCartContext.getSummaryItems(effectivePaymentMode);
        this.mCartSummaryContainer.removeAllViews();
        for (WishCartSummaryItem cartItemsSummaryRow : summaryItems) {
            this.mCartSummaryContainer.addView(new CartItemsSummaryRow(getContext(), this.mCartContext, cartItemsSummaryRow, false));
        }
    }

    public void updateCartContext(CartContext cartContext) {
        this.mCartContext = cartContext;
        repopulateSummaryRows();
        updateUiWithDefaultedLoan();
        if (this.mCartPaymentStructureView.getVisibility() == 0) {
            this.mCartPaymentStructureView.refreshUi();
        }
        if (this.mCartSummaryFirstInstallmentRow != null) {
            updateInstallmentsSummaryRows();
        }
    }

    public void updateInstallmentsSummaryRows() {
        boolean z = this.mCartContext.getPayHalfLaterFlag() && this.mCartPaymentStructureView.getLocalizedDueDate() != null;
        int i = z ? 0 : 8;
        this.mCartSummaryFirstInstallmentRow.setVisibility(i);
        this.mCartSummarySecondInstallmentRow.setVisibility(i);
        this.mCartSummaryInstallmentRowsSeparator.setVisibility(i);
        if (z) {
            this.mCartSummaryFirstInstallmentValue.setText(this.mCartPaymentStructureView.getFirstInstallmentAmountText());
            this.mCartSummarySecondInstallmentName.setText(getResources().getString(R.string.due_on_day, new Object[]{this.mCartPaymentStructureView.getLocalizedDueDate()}));
            this.mCartSummarySecondInstallmentValue.setText(this.mCartPaymentStructureView.getSecondInstallmentAmountText());
        }
    }

    public void hideReturnPolicyText() {
        if (this.mReturnPolicyText != null) {
            this.mReturnPolicyText.setVisibility(8);
        }
    }
}
