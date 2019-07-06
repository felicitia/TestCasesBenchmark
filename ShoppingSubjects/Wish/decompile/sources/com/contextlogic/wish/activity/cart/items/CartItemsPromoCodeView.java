package com.contextlogic.wish.activity.cart.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.login.LoginFormEditText;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.ThemedButton;

public class CartItemsPromoCodeView extends LinearLayout {
    /* access modifiers changed from: private */
    public ThemedButton mApplyCouponButton;
    /* access modifiers changed from: private */
    public CartFragment mCartFragment;
    /* access modifiers changed from: private */
    public LoginFormEditText mCouponCodeEditText;

    public CartItemsPromoCodeView(Context context) {
        super(context);
    }

    public CartItemsPromoCodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setup(CartFragment cartFragment) {
        this.mCartFragment = cartFragment;
        init();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void init() {
        setOrientation(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_cart_items_promo_code, this);
        this.mCouponCodeEditText = (LoginFormEditText) inflate.findViewById(R.id.promo_code_input);
        this.mCouponCodeEditText.setFocusable(false);
        this.mApplyCouponButton = (ThemedButton) inflate.findViewById(R.id.promo_code_apply_button);
        this.mCouponCodeEditText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemsPromoCodeView.this.mCartFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                        cartServiceFragment.showApplyPromoDialog();
                    }
                });
            }
        });
        this.mApplyCouponButton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 3) {
                    switch (action) {
                        case 0:
                            CartItemsPromoCodeView.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
                            break;
                        case 1:
                            CartItemsPromoCodeView.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                            break;
                    }
                } else {
                    CartItemsPromoCodeView.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                }
                return false;
            }
        });
        this.mCouponCodeEditText.setLongClickable(false);
        this.mApplyCouponButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CartItemsPromoCodeView.this.mCouponCodeEditText.getText() != null && CartItemsPromoCodeView.this.mCouponCodeEditText.getText().length() > 0) {
                    CartItemsPromoCodeView.this.mCartFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                            cartServiceFragment.applyPromoCodeService(CartItemsPromoCodeView.this.mCouponCodeEditText.getText().toString());
                        }
                    });
                }
            }
        });
    }
}
