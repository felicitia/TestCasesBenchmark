package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentAddCoupon;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class PaymentAddCouponViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mBtnAddCouponCode = ((Button) findViewById(R.id.btn_add_coupon_code));
    /* access modifiers changed from: private */
    public final a mClickHandler;
    private final View mLabelCouponCode = findViewById(R.id.label_coupon_code);
    /* access modifiers changed from: private */
    public final EditText mTxtCouponCode = ((EditText) findViewById(R.id.txt_coupon_code));
    private final TextView mTxtError;

    public PaymentAddCouponViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_add_coupon, viewGroup, false));
        this.mClickHandler = aVar;
        this.mTxtCouponCode.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view == PaymentAddCouponViewHolder.this.mTxtCouponCode && z) {
                    ((InputMethodManager) PaymentAddCouponViewHolder.this.itemView.getContext().getSystemService("input_method")).showSoftInput(PaymentAddCouponViewHolder.this.mTxtCouponCode, 1);
                }
            }
        });
        this.mTxtError = (TextView) findViewById(R.id.txt_coupon_error);
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        final PaymentAddCoupon paymentAddCoupon = (PaymentAddCoupon) cartGroupItem.getData();
        if (!TextUtils.isEmpty(paymentAddCoupon.getLabel())) {
            this.mBtnAddCouponCode.setText(paymentAddCoupon.getLabel());
        }
        if (!TextUtils.isEmpty(paymentAddCoupon.getCode())) {
            showCouponFields(paymentAddCoupon);
        } else {
            hideCouponFields();
        }
        if (this.mClickHandler != null) {
            this.mBtnAddCouponCode.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    PaymentAddCouponViewHolder.this.showCouponFields(paymentAddCoupon);
                }
            });
            this.mTxtCouponCode.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_APPLY_COUPON);
                    String obj = PaymentAddCouponViewHolder.this.mTxtCouponCode.getText().toString();
                    if (action != null && !TextUtils.isEmpty(obj)) {
                        action.addParam("coupon_code", obj);
                        PaymentAddCouponViewHolder.this.mClickHandler.c(PaymentAddCouponViewHolder.this.getRootView(), action);
                    }
                    textView.clearFocus();
                    s.a((View) textView);
                    return false;
                }
            });
        }
    }

    private void hideCouponFields() {
        this.mBtnAddCouponCode.setVisibility(0);
        this.mLabelCouponCode.setVisibility(8);
        this.mTxtCouponCode.setVisibility(8);
        this.mTxtError.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void showCouponFields(PaymentAddCoupon paymentAddCoupon) {
        this.mBtnAddCouponCode.setVisibility(8);
        this.mLabelCouponCode.setVisibility(0);
        this.mTxtCouponCode.setVisibility(0);
        this.mTxtCouponCode.setText(paymentAddCoupon.getCode());
        this.mTxtCouponCode.requestFocus();
        String error = paymentAddCoupon.getError();
        if (!TextUtils.isEmpty(paymentAddCoupon.getError())) {
            this.mTxtError.setVisibility(0);
            this.mTxtError.setText(error);
            return;
        }
        this.mTxtError.setVisibility(8);
    }
}
