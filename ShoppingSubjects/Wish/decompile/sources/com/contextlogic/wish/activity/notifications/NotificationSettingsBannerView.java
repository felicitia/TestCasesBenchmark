package com.contextlogic.wish.activity.notifications;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.settings.notifications.NotificationSettingsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import java.util.HashMap;

public class NotificationSettingsBannerView<A extends BaseActivity> extends FrameLayout {
    private UiFragment<A> mFragment;
    /* access modifiers changed from: private */
    public String mSource;

    public NotificationSettingsBannerView(Context context) {
        super(context);
        init();
    }

    public void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.notification_settings_banner_view, this, true);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray6));
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationSettingsBannerView.this.clickBanner();
            }
        });
        setMinimumHeight(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.notification_settings_banner_height));
    }

    public void trackImpression(String str) {
        this.mSource = str;
        HashMap hashMap = new HashMap();
        if (this.mSource != null) {
            hashMap.put("source", this.mSource);
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NOTIFICATION_SETTINGS_BANNER, (String) null, hashMap);
    }

    public void setFragment(UiFragment<A> uiFragment) {
        this.mFragment = uiFragment;
    }

    /* access modifiers changed from: private */
    public void clickBanner() {
        this.mFragment.withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                HashMap hashMap = new HashMap();
                if (NotificationSettingsBannerView.this.mSource != null) {
                    hashMap.put("source", NotificationSettingsBannerView.this.mSource);
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NOTIFICATION_SETTINGS_BANNER, (String) null, hashMap);
                Intent intent = new Intent();
                intent.setClass(a, NotificationSettingsActivity.class);
                a.startActivity(intent);
            }
        });
    }
}
