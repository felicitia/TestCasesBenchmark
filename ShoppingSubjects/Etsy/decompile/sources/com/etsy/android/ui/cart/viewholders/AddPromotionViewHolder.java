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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentAddCoupon;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class AddPromotionViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mBtnAddCouponCode = ((Button) findViewById(R.id.btn_add_coupon_code));
    private final Button mBtnApply = ((Button) findViewById(R.id.btn_apply));
    private final a mClickHandler;
    private final ImageView mIcon;
    /* access modifiers changed from: private */
    public final EditText mTxtCouponCode = ((EditText) findViewById(R.id.txt_coupon_code));
    private final TextView mTxtError;

    public AddPromotionViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_add_promotion, viewGroup, false));
        this.mClickHandler = aVar;
        this.mTxtCouponCode.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view == AddPromotionViewHolder.this.mTxtCouponCode && z) {
                    ((InputMethodManager) AddPromotionViewHolder.this.itemView.getContext().getSystemService("input_method")).showSoftInput(AddPromotionViewHolder.this.mTxtCouponCode, 1);
                }
            }
        });
        this.mTxtError = (TextView) findViewById(R.id.txt_coupon_error);
        this.mIcon = (ImageView) findViewById(R.id.icon);
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        final PaymentAddCoupon paymentAddCoupon = (PaymentAddCoupon) cartGroupItem.getData();
        if (!TextUtils.isEmpty(paymentAddCoupon.getLabel())) {
            this.mBtnAddCouponCode.setText(paymentAddCoupon.getLabel());
        }
        this.mTxtCouponCode.setHint(paymentAddCoupon.getHint());
        if (!TextUtils.isEmpty(paymentAddCoupon.getCode())) {
            showCouponFields(paymentAddCoupon);
        } else {
            hideCouponFields();
        }
        if (this.mClickHandler != null) {
            this.mBtnAddCouponCode.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    AddPromotionViewHolder.this.showCouponFields(paymentAddCoupon);
                }
            });
            this.mTxtCouponCode.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    AddPromotionViewHolder.this.applyCoupon(cartGroupItem);
                    return false;
                }
            });
            this.mBtnApply.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    AddPromotionViewHolder.this.applyCoupon(cartGroupItem);
                }
            });
        }
    }

    private void hideCouponFields() {
        this.mBtnAddCouponCode.setVisibility(0);
        this.mTxtCouponCode.setVisibility(8);
        this.mTxtError.setVisibility(8);
        this.mBtnApply.setVisibility(8);
        this.mIcon.setColorFilter(this.itemView.getResources().getColor(R.color.sk_orange_20));
    }

    /* access modifiers changed from: protected */
    public void showCouponFields(PaymentAddCoupon paymentAddCoupon) {
        this.mBtnAddCouponCode.setVisibility(8);
        this.mTxtCouponCode.setVisibility(0);
        this.mTxtCouponCode.setText(paymentAddCoupon.getCode());
        this.mTxtCouponCode.requestFocus();
        this.mBtnApply.setVisibility(0);
        String error = paymentAddCoupon.getError();
        if (!TextUtils.isEmpty(paymentAddCoupon.getError())) {
            this.mTxtError.setVisibility(0);
            this.mTxtError.setText(error);
        } else {
            this.mTxtError.setVisibility(8);
        }
        this.mIcon.setColorFilter(this.itemView.getResources().getColor(R.color.sk_text_gray));
    }

    /* access modifiers changed from: private */
    public void applyCoupon(CartGroupItem cartGroupItem) {
        ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_APPLY_COUPON);
        String obj = this.mTxtCouponCode.getText().toString();
        if (action != null && !TextUtils.isEmpty(obj)) {
            action.addParam("coupon_code", obj);
            this.mClickHandler.c(getRootView(), action);
        }
        this.mTxtCouponCode.clearFocus();
        s.a((View) this.mTxtCouponCode);
    }
}
