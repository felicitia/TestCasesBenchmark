package com.contextlogic.wish.dialog.dailygiveaway;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishDailyGiveawayNotificationInfo;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.Margin;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.HashMap;

public class DailyGiveawayDialogFragment<A extends BaseActivity> extends BaseDialogFragment {
    /* access modifiers changed from: private */
    public ViewGroup mPopupHolder;

    /* access modifiers changed from: protected */
    public int getPopupTranslateLength() {
        return 500;
    }

    public static DailyGiveawayDialogFragment<BaseActivity> createDailyGiveawayDialog(WishDailyGiveawayNotificationInfo wishDailyGiveawayNotificationInfo) {
        if (wishDailyGiveawayNotificationInfo == null) {
            return null;
        }
        DailyGiveawayDialogFragment<BaseActivity> dailyGiveawayDialogFragment = new DailyGiveawayDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentNotificationInfo", wishDailyGiveawayNotificationInfo);
        dailyGiveawayDialogFragment.setArguments(bundle);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_SPLASH_NOTI, getGiveawayImpressionInfo());
        return dailyGiveawayDialogFragment;
    }

    /* access modifiers changed from: private */
    public static HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            hashMap.put("GiveawayType", "DailyRaffle");
        } else {
            hashMap.put("GiveawayType", "DailyGiveaway");
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void openDailyGiveaway() {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                Intent intent = new Intent();
                intent.setClass(baseActivity, BrowseActivity.class);
                intent.putExtra("ExtraCategoryId", "daily_giveaway__tab");
                baseActivity.startActivity(intent);
            }
        });
        cancel();
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.daily_giveaway_dialog_fragment, viewGroup, false);
        WishDailyGiveawayNotificationInfo wishDailyGiveawayNotificationInfo = (WishDailyGiveawayNotificationInfo) getArguments().getParcelable("ArgumentNotificationInfo");
        this.mPopupHolder = (ViewGroup) inflate.findViewById(R.id.daily_giveaway_popup_holder);
        ((AutoReleasableImageView) inflate.findViewById(R.id.daily_giveaway_dialog_x_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_SPLASH_NOTI_CANCEL, DailyGiveawayDialogFragment.getGiveawayImpressionInfo());
                DailyGiveawayDialogFragment.this.cancel();
            }
        });
        ((LinearLayout) inflate.findViewById(R.id.daily_giveaway_dialog_claim_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_SPLASH_NOTI_CLAIM, DailyGiveawayDialogFragment.getGiveawayImpressionInfo());
                DailyGiveawayDialogFragment.this.openDailyGiveaway();
            }
        });
        ((ThemedTextView) inflate.findViewById(R.id.daily_giveaway_dialog_later_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_SPLASH_NOTI_LATER, DailyGiveawayDialogFragment.getGiveawayImpressionInfo());
                DailyGiveawayDialogFragment.this.cancel();
            }
        });
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.daily_giveaway_dialog_title);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.daily_giveaway_dialog_desc);
        ThemedTextView themedTextView3 = (ThemedTextView) inflate.findViewById(R.id.daily_giveaway_dialog_claim_button_text);
        HorizontalListView horizontalListView = (HorizontalListView) inflate.findViewById(R.id.daily_giveaway_dialog_list_view);
        if (wishDailyGiveawayNotificationInfo != null) {
            themedTextView.setText(wishDailyGiveawayNotificationInfo.getTitle());
            themedTextView2.setText(wishDailyGiveawayNotificationInfo.getDescription());
            themedTextView3.setText(wishDailyGiveawayNotificationInfo.getMainActionText());
            horizontalListView.setAdapter(new DailyGiveawayDialogAdapter(getContext(), wishDailyGiveawayNotificationInfo.getGiveaways()));
            horizontalListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(int i, View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_SPLASH_NOTI_PRODUCT, DailyGiveawayDialogFragment.getGiveawayImpressionInfo());
                    DailyGiveawayDialogFragment.this.openDailyGiveaway();
                }
            });
            horizontalListView.notifyDataSetChanged(true);
        }
        inflate.setLayoutParams(new LayoutParams(getDialogWidth(), -1));
        performPopupAnimation();
        return inflate;
    }

    private void performPopupAnimation() {
        this.mPopupHolder.setVisibility(8);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) DisplayUtil.getDisplayHeight(), 0.0f);
        translateAnimation.setDuration((long) getPopupTranslateLength());
        translateAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                DailyGiveawayDialogFragment.this.mPopupHolder.setVisibility(0);
            }
        });
        this.mPopupHolder.startAnimation(translateAnimation);
    }

    public Margin getDialogMargin() {
        Margin margin = new Margin(0, DisplayUtil.getStatusBarHeight(), 0, 0);
        return margin;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }
}
