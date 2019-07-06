package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentAppliedCoupon;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class PaymentAppliedCouponViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mButtonRemove = ((Button) findViewById(R.id.button_remove));
    /* access modifiers changed from: private */
    public final a mClickListener;
    private final TextView mTxtCouponCode = ((TextView) findViewById(R.id.txt_coupon_code));
    private final TextView mTxtDescription = ((TextView) findViewById(R.id.txt_description));
    private final TextView mTxtPriceDiscount = ((TextView) findViewById(R.id.txt_price_discount));

    public PaymentAppliedCouponViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_applied_coupon, viewGroup, false));
        this.mClickListener = aVar;
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        PaymentAppliedCoupon paymentAppliedCoupon = (PaymentAppliedCoupon) cartGroupItem.getData();
        this.mTxtCouponCode.setText(paymentAppliedCoupon.getCouponCode());
        String description = paymentAppliedCoupon.getDescription();
        this.mTxtDescription.setText(TextUtils.isEmpty(description) ? "" : description.trim());
        this.mTxtPriceDiscount.setText(paymentAppliedCoupon.getDiscountAmountString());
        this.mButtonRemove.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (PaymentAppliedCouponViewHolder.this.mClickListener != null) {
                    ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON);
                    if (action != null) {
                        PaymentAppliedCouponViewHolder.this.mClickListener.c(PaymentAppliedCouponViewHolder.this.getRootView(), action);
                    }
                }
            }
        });
    }
}
