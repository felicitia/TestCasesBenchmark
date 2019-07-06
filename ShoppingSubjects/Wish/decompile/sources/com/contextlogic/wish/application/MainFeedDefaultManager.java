package com.contextlogic.wish.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.ActivityLifeCycleEventListener;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.EventType;

public class MainFeedDefaultManager implements ActivityLifeCycleEventListener {
    private static MainFeedDefaultManager sInstance = new MainFeedDefaultManager();
    private long mAppBackgroundedTimestamp;
    private boolean mBackgroundedWithoutDefaulting;
    private int mVisibleActivityCount;

    public static MainFeedDefaultManager getInstance() {
        return sInstance;
    }

    public void initialize() {
        ActivityLifeCycleCallbackManager.getInstance().addActivityLifeCycleEventListener(this);
    }

    public void onActivityLifecycleEvent(EventType eventType, Activity activity, Bundle bundle) {
        if (eventType == EventType.STARTED) {
            this.mVisibleActivityCount++;
            if (this.mBackgroundedWithoutDefaulting && this.mAppBackgroundedTimestamp > 0 && System.currentTimeMillis() - this.mAppBackgroundedTimestamp > 1200000 && ExperimentDataCenter.getInstance().shouldLoadFeedAfter20Mins(activity)) {
                if (!activity.getIntent().getBooleanExtra("ExtraIgnoreMainFeedDefaultLoad", false)) {
                    Intent intent = new Intent();
                    intent.setClass(WishApplication.getInstance(), BrowseActivity.class);
                    intent.setFlags(268468224);
                    activity.startActivity(intent);
                } else {
                    activity.getIntent().putExtra("ExtraIgnoreMainFeedDefaultLoad", false);
                }
            }
            this.mBackgroundedWithoutDefaulting = false;
        } else if (eventType == EventType.STOPPED) {
            this.mVisibleActivityCount--;
            if (!applicationVisible()) {
                this.mAppBackgroundedTimestamp = System.currentTimeMillis();
                this.mBackgroundedWithoutDefaulting = true;
            }
        }
    }

    private boolean applicationVisible() {
        return this.mVisibleActivityCount > 0;
    }
}
