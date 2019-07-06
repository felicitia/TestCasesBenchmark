package com.etsy.android.ui.view.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.PaymentAdjustment;

/* compiled from: ReceiptRefundViewHolder */
public class a {
    public View a;
    public LinearLayout b;
    public TextView c;
    public int d = R.layout.list_item_refund;
    public int e = R.id.text_refund_value;
    public int f = R.id.refund_reason;

    public a(View view) {
        this.a = view.findViewById(R.id.refund_view);
        this.b = (LinearLayout) view.findViewById(R.id.refund_list_layout);
        this.c = (TextView) view.findViewById(R.id.text_adjusted_total_value);
    }

    public void a(Payment payment) {
        this.b.removeAllViews();
        if (payment == null || !payment.hasRefund()) {
            this.a.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(this.b.getContext());
        for (PaymentAdjustment paymentAdjustment : payment.getAdjustments()) {
            View inflate = from.inflate(this.d, this.b, false);
            TextView textView = (TextView) inflate.findViewById(this.e);
            StringBuilder sb = new StringBuilder();
            sb.append("- ");
            sb.append(paymentAdjustment.getRefundAmount());
            textView.setText(sb.toString());
            ((TextView) inflate.findViewById(this.f)).setText(paymentAdjustment.getFormattedReason());
            this.b.addView(inflate);
        }
        this.c.setText(payment.getFormattedAdjustedTotal());
        this.a.setVisibility(0);
    }
}
