package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.util.StringUtil;

public class OrderConfirmedBoxView extends LinearLayout {
    private String mBoletoDueDate;
    private String mCommerceLoanDueDate;
    private final boolean mHasUpfrontPayment;
    private String mShippingInfo;
    private String mTransactionId;

    protected OrderConfirmedBoxView(Context context, String str, String str2, String str3, String str4, boolean z) {
        super(context);
        this.mShippingInfo = str;
        this.mTransactionId = str2;
        this.mBoletoDueDate = str3;
        this.mCommerceLoanDueDate = str4;
        this.mHasUpfrontPayment = z;
        init();
    }

    private void init() {
        TextView textView;
        View view;
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.order_confirmed_box_view_gap);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        setLayoutParams(layoutParams);
        setOrientation(1);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        LayoutInflater from = LayoutInflater.from(getContext());
        if (this.mBoletoDueDate == null) {
            view = from.inflate(R.layout.order_confirmed_box_view, this);
            textView = (TextView) view.findViewById(R.id.order_confirmed_box_view_email);
            TextView textView2 = (TextView) view.findViewById(R.id.order_confirmed_box_order_details_button);
            ((TextView) view.findViewById(R.id.order_confirmed_box_view_shipping_info_content)).setText(this.mShippingInfo);
            TextView textView3 = (TextView) view.findViewById(R.id.order_confirmed_box_view_commerce_loan_payment_due_on);
            TextView textView4 = (TextView) view.findViewById(R.id.order_confirmed_box_view_commerce_loan_payment_due_date);
            if (this.mCommerceLoanDueDate != null) {
                textView3.setVisibility(0);
                textView4.setVisibility(0);
                textView4.setText(this.mCommerceLoanDueDate);
            }
            if (this.mHasUpfrontPayment) {
                textView3.setText(R.string.next_payment_due_on_colon);
            }
            textView2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    OrderConfirmedBoxView.this.onViewHistoryClicked();
                }
            });
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ORDER_CONFIRMED_BOLETO);
            view = from.inflate(R.layout.boleto_order_confirmed_box_view, this);
            TextView textView5 = (TextView) view.findViewById(R.id.boleto_order_confirmed_box_view_email);
            ((TextView) findViewById(R.id.boleto_print_and_pay_before_due_date_text)).setText(StringUtil.boldSubstring(WishApplication.getInstance().getString(R.string.pay_before_due_date, new Object[]{this.mBoletoDueDate}), this.mBoletoDueDate));
            TextView textView6 = (TextView) view.findViewById(R.id.boleto_order_confirmed_box_order_details_button);
            ((ThemedButton) view.findViewById(R.id.boleto_order_confirmed_box_view_boleto_button)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    OrderConfirmedBoxView.this.onViewBoletoClicked();
                }
            });
            textView6.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    OrderConfirmedBoxView.this.onViewHistoryClicked();
                }
            });
            textView = textView5;
        }
        view.setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.order_confirmed_box_border));
        textView.setText(ProfileDataCenter.getInstance().getEmail());
    }

    /* access modifiers changed from: private */
    public void onViewHistoryClicked() {
        startWebViewActivity(WebViewActivity.getOrderUrl(this.mTransactionId), WishApplication.getInstance().getResources().getString(R.string.order_details));
    }

    /* access modifiers changed from: private */
    public void onViewBoletoClicked() {
        startWebViewActivity(WebViewActivity.getBoletoViewUrl(this.mTransactionId), null);
    }

    /* access modifiers changed from: 0000 */
    public void startWebViewActivity(String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(getContext(), WebViewActivity.class);
        intent.putExtra("ExtraUrl", str);
        intent.putExtra("ExtraActionBarTitle", str2);
        getContext().startActivity(intent);
    }
}
