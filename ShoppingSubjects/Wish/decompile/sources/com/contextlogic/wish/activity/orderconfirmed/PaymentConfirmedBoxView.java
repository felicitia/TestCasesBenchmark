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
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.application.WishApplication;

public class PaymentConfirmedBoxView extends LinearLayout {
    private final String mPaymentProcessedDate;
    private final String mTransactionId;

    protected PaymentConfirmedBoxView(Context context, String str, String str2) {
        super(context);
        this.mPaymentProcessedDate = str;
        this.mTransactionId = str2;
        init();
    }

    private void init() {
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.order_confirmed_box_view_gap);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        setLayoutParams(layoutParams);
        setOrientation(1);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.payment_confirmed_box_view, this);
        TextView textView = (TextView) inflate.findViewById(R.id.payment_confirmed_confirmed_box_view_email);
        TextView textView2 = (TextView) inflate.findViewById(R.id.payment_confirmed_box_view_payment_confirmed);
        ((TextView) inflate.findViewById(R.id.payment_confirmed_confirmed_box_order_details_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaymentConfirmedBoxView.this.onViewHistoryClicked();
            }
        });
        inflate.setBackgroundResource(R.drawable.order_confirmed_box_border);
        textView.setText(ProfileDataCenter.getInstance().getEmail());
        textView2.setText(this.mPaymentProcessedDate);
    }

    /* access modifiers changed from: private */
    public void onViewHistoryClicked() {
        startWebViewActivity(WebViewActivity.getOrderUrl(this.mTransactionId), WishApplication.getInstance().getResources().getString(R.string.order_details));
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
