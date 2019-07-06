package com.contextlogic.wish.activity.cart.commerceloan;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.items.CartItemsView;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceLoanRepaymentBannerView extends LinearLayout {
    private CartContext mCartContext;
    private CartItemsView mCartItemsView;
    private ThemedTextView mDescription;
    private ThemedButton mOrderDetailsButton;
    private ThemedButton mPayButton;
    /* access modifiers changed from: private */
    public WishLoanRepaymentBannerSpec mRepaymentBannerSpec;
    private ThemedTextView mTitle;

    public CommerceLoanRepaymentBannerView(Context context) {
        super(context);
        init();
    }

    public CommerceLoanRepaymentBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CommerceLoanRepaymentBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.commerce_loan_repayment_view, this);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.commerce_loan_repayment_view_title);
        this.mDescription = (ThemedTextView) inflate.findViewById(R.id.commerce_loan_repayment_view_description);
        this.mOrderDetailsButton = (ThemedButton) inflate.findViewById(R.id.commerce_loan_repayment_view_order_details_button);
        this.mPayButton = (ThemedButton) inflate.findViewById(R.id.commerce_loan_repayment_view_pay_button);
    }

    public void setup(CartItemsView cartItemsView) {
        this.mCartItemsView = cartItemsView;
        this.mCartContext = cartItemsView.getCartFragment().getCartContext();
        this.mRepaymentBannerSpec = this.mCartContext.getLoanRepaymentBannerSpec();
        this.mTitle.setText(this.mRepaymentBannerSpec.getTitle());
        this.mDescription.setText(this.mRepaymentBannerSpec.getMessage());
        this.mOrderDetailsButton.setText(this.mRepaymentBannerSpec.getOrderDetailsButtonText());
        this.mPayButton.setText(this.mRepaymentBannerSpec.getPayNowButtonText());
        this.mOrderDetailsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommerceLoanRepaymentBannerView.this.goToOrderDetails(CommerceLoanRepaymentBannerView.this.mRepaymentBannerSpec.getTransactionId());
            }
        });
        this.mPayButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommerceLoanRepaymentBannerView.this.openLoanCart();
            }
        });
    }

    /* access modifiers changed from: private */
    public void goToOrderDetails(String str) {
        Intent intent = new Intent();
        intent.setClass(getContext(), WebViewActivity.class);
        intent.putExtra("ExtraUrl", WebViewActivity.getOrderUrl(str));
        intent.putExtra("ExtraActionBarTitle", getResources().getString(R.string.order_details));
        getContext().startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void openLoanCart() {
        Intent intent = new Intent();
        intent.setClass(getContext(), CommerceLoanCartActivity.class);
        intent.putExtra("ArgSuccessSheetTitle", this.mRepaymentBannerSpec.getSuccessSheetTitle());
        getContext().startActivity(intent);
    }
}
