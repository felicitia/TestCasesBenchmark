package com.contextlogic.wish.activity.cart.commerceloan;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartType;
import java.util.HashMap;

public class CommerceLoanCartFragment extends CartFragment {
    private View mClickableBackgroundView;

    public int getLayoutResourceId() {
        return R.layout.commerce_loan_cart_fragment;
    }

    public void initialize() {
        super.initialize();
        this.mClickableBackgroundView = findViewById(R.id.cart_fragment_clickable_background);
        this.mClickableBackgroundView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommerceLoanCartFragment.this.withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("cart_type", CartType.COMMERCE_LOAN.toString());
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_CLOSE, hashMap);
                        cartActivity.onBackPressed();
                    }
                });
            }
        });
    }

    public void showItemsView(boolean z) {
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Bundle savedInstanceState = CommerceLoanCartFragment.this.mCurrentUiView == null ? CommerceLoanCartFragment.this.getSavedInstanceState() : null;
                CommerceLoanCartFragment.this.updateUiView(new CommerceLoanCartItemsView(CommerceLoanCartFragment.this, cartActivity, savedInstanceState), UiViewType.ITEMS, savedInstanceState);
            }
        });
    }

    public void handleCartLoadSuccess(CartContext cartContext) {
        super.handleCartLoadSuccess(cartContext);
        LayoutParams layoutParams = (LayoutParams) this.mLoadingView.getLayoutParams();
        layoutParams.gravity = 80;
        layoutParams.height = -2;
        this.mLoadingView.setLayoutParams(layoutParams);
        this.mLoadingView.setBackgroundResource(R.drawable.white_background);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.mClickableBackgroundView.getHeight(), 0.0f);
        translateAnimation.setDuration(400);
        this.mLoadingView.startAnimation(translateAnimation);
    }

    public void handleCartLoadError() {
        this.mLoadingView.setVisibility(8);
        this.mClickableBackgroundView.setVisibility(8);
    }

    public void handleReload() {
        if (this.mLoadingView != null) {
            LayoutParams layoutParams = (LayoutParams) this.mLoadingView.getLayoutParams();
            layoutParams.gravity = 17;
            layoutParams.height = -2;
            this.mLoadingView.setLayoutParams(layoutParams);
            this.mLoadingView.setBackgroundResource(0);
        }
        withServiceFragment(new ServiceTask<CommerceLoanCartActivity, CommerceLoanCartServiceFragment>() {
            public void performTask(CommerceLoanCartActivity commerceLoanCartActivity, CommerceLoanCartServiceFragment commerceLoanCartServiceFragment) {
                commerceLoanCartServiceFragment.getCommerceLoanCart();
            }
        });
    }
}
