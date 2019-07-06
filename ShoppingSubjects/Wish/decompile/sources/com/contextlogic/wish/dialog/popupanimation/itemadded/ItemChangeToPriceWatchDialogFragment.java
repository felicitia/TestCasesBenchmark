package com.contextlogic.wish.dialog.popupanimation.itemadded;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.pricewatch.PriceWatchActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class ItemChangeToPriceWatchDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private String mBody;
    private boolean mShowPriceWatchLink;
    private String mTitle;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.item_added_animation_holder;
    }

    public static ItemChangeToPriceWatchDialogFragment<BaseActivity> createItemChangeToPriceWatchDialogFragment(String str, String str2, boolean z) {
        ItemChangeToPriceWatchDialogFragment<BaseActivity> itemChangeToPriceWatchDialogFragment = new ItemChangeToPriceWatchDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentTitle", str);
        bundle.putString("ArgumentBody", str2);
        bundle.putBoolean("ArgumentShowPriceWatchLink", z);
        itemChangeToPriceWatchDialogFragment.setArguments(bundle);
        return itemChangeToPriceWatchDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        handleArguments(getArguments());
        View inflate = layoutInflater.inflate(getLayout(), viewGroup, false);
        this.mPopupHolder = getPopupHolder(inflate);
        ItemChangeToPriceWatchPopupView popupView = getPopupView();
        if (this.mShowPriceWatchLink && popupView.getPriceWatchLink() != null) {
            popupView.setOnClickListener(getPopupClickListener());
            popupView.getPriceWatchLink().setOnClickListener(getPopupClickListener());
        }
        this.mPopupHolder.addView(popupView);
        performPopupAnimation();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mTitle = bundle.getString("ArgumentTitle");
        this.mBody = bundle.getString("ArgumentBody");
        this.mShowPriceWatchLink = bundle.getBoolean("ArgumentShowPriceWatchLink");
    }

    /* access modifiers changed from: protected */
    public ItemChangeToPriceWatchPopupView getPopupView() {
        return new ItemChangeToPriceWatchPopupView(getContext(), this.mTitle, this.mBody, this.mShowPriceWatchLink);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.item_added_popup_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(this.mShowPriceWatchLink ? R.dimen.price_watch_popup_with_link_height : R.dimen.price_watch_popup_without_link_height);
    }

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        if (!this.mShowPriceWatchLink) {
            return null;
        }
        return new OnClickListener() {
            public void onClick(View view) {
                ItemChangeToPriceWatchDialogFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_SHOW_LINK);
                        Intent intent = new Intent();
                        intent.setClass(baseActivity, PriceWatchActivity.class);
                        intent.putExtra("ExtraShowBackButton", true);
                        ItemChangeToPriceWatchDialogFragment.this.startActivity(intent);
                        ItemChangeToPriceWatchDialogFragment.this.cancel();
                    }
                });
            }
        };
    }
}
