package com.contextlogic.wish.dialog.freegift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class GiftInCartDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private WishProduct mWishProduct;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.item_added_animation_holder;
    }

    public static GiftInCartDialogFragment<BaseActivity> createGiftInCartDialogFragment(WishProduct wishProduct) {
        GiftInCartDialogFragment<BaseActivity> giftInCartDialogFragment = new GiftInCartDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentFreeGiftItem", wishProduct);
        giftInCartDialogFragment.setArguments(bundle);
        return giftInCartDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mWishProduct = (WishProduct) bundle.getParcelable("ArgumentFreeGiftItem");
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.item_added_popup_holder);
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new GiftInCartDialogView(getContext(), this.mWishProduct);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.item_added_dialog_popup_height);
    }

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                GiftInCartDialogFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        if (!(baseActivity instanceof CartActivity)) {
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, CartActivity.class);
                            GiftInCartDialogFragment.this.startActivity(intent);
                        }
                        GiftInCartDialogFragment.this.cancel();
                    }
                });
            }
        };
    }
}
