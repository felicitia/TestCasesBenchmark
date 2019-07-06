package com.contextlogic.wish.dialog.popupanimation.bundleadded;

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
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;
import java.util.ArrayList;

public class BundleAddedDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private ArrayList<WishCartItem> mCartItems;
    private ArrayList<WishProduct> mProducts;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.bundle_added_animation_holder;
    }

    public static BundleAddedDialogFragment<BaseActivity> createBundleAddedDialogFragment(ArrayList<WishProduct> arrayList, ArrayList<WishCartItem> arrayList2) {
        BundleAddedDialogFragment<BaseActivity> bundleAddedDialogFragment = new BundleAddedDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArgumentBundledProducts", arrayList);
        bundle.putParcelableArrayList("ArgumentCartItems", arrayList2);
        bundleAddedDialogFragment.setArguments(bundle);
        return bundleAddedDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mProducts = bundle.getParcelableArrayList("ArgumentBundledProducts");
        this.mCartItems = bundle.getParcelableArrayList("ArgumentCartItems");
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new BundleAddedPopupView(getContext(), this.mCartItems);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.bundle_added_popup_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.bundle_added_dialog_popup_height);
    }

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                BundleAddedDialogFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        if (!(baseActivity instanceof CartActivity)) {
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, CartActivity.class);
                            BundleAddedDialogFragment.this.startActivity(intent);
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLE_ADDED_TO_CART_POPUP);
                        BundleAddedDialogFragment.this.cancel();
                    }
                });
            }
        };
    }
}
