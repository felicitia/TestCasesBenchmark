package com.contextlogic.wish.dialog.popupanimation.itemadded;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class ItemAddedDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private WishCartItem mCartItem;
    private WishLocalizedCurrencyValue mOriginalValue;
    private int mQuantity;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.item_added_animation_holder;
    }

    public static ItemAddedDialogFragment<BaseActivity> createItemAddedDialogFragment(WishCartItem wishCartItem, int i, WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        ItemAddedDialogFragment<BaseActivity> itemAddedDialogFragment = new ItemAddedDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentSelectedCartItem", wishCartItem);
        bundle.putInt("ArgumentQuantity", i);
        bundle.putParcelable("ArgumentOriginalValue", wishLocalizedCurrencyValue);
        itemAddedDialogFragment.setArguments(bundle);
        return itemAddedDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mCartItem = (WishCartItem) bundle.getParcelable("ArgumentSelectedCartItem");
        this.mQuantity = bundle.getInt("ArgumentQuantity");
        this.mOriginalValue = (WishLocalizedCurrencyValue) bundle.getParcelable("ArgumentOriginalValue");
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new ItemAddedPopupView(getContext(), this.mCartItem, this.mQuantity, this.mOriginalValue);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.item_added_popup_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.item_added_dialog_popup_height);
    }

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                ItemAddedDialogFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        if (!(baseActivity instanceof CartActivity)) {
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, CartActivity.class);
                            ItemAddedDialogFragment.this.startActivity(intent);
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ITEM_ADDED_TO_CART_POPUP);
                        ItemAddedDialogFragment.this.cancel();
                    }
                });
            }
        };
    }
}
