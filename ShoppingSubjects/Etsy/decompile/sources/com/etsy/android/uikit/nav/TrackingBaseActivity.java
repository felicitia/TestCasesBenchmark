package com.etsy.android.uikit.nav;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.bucketing.d;
import com.etsy.android.lib.logger.h;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;
import com.etsy.android.uikit.BaseActivity;

public abstract class TrackingBaseActivity extends BaseActivity implements j {
    public w mAnalyticsTracker;
    private h mEtsyGraphiteTimerManager;
    @Nullable
    private String mNameFromIntent;

    @NonNull
    public Context getAndroidContext() {
        return this;
    }

    @Nullable
    public j getTrackingParent() {
        return null;
    }

    public boolean shouldAutoTrack() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNameFromIntent = getIntent().getStringExtra("TRACKING_NAME");
        if (this.mAnalyticsTracker == null) {
            this.mAnalyticsTracker = w.a((j) this, true, getIntent().getExtras());
        }
        if (shouldAutoTrack()) {
            this.mAnalyticsTracker.a((j) this);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.b(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.i();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.j();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.k();
        }
    }

    @NonNull
    public h getGraphiteTimerManager() {
        if (this.mEtsyGraphiteTimerManager == null) {
            this.mEtsyGraphiteTimerManager = new h();
        }
        return this.mEtsyGraphiteTimerManager;
    }

    @NonNull
    public String getTrackingName() {
        if (this.mNameFromIntent != null) {
            return this.mNameFromIntent;
        }
        return getDefaultName();
    }

    @NonNull
    public final String getDefaultName() {
        return getClass().getSimpleName();
    }

    public com.etsy.android.lib.config.h getConfigMap() {
        return this.mAnalyticsTracker.c();
    }

    public boolean isNativeFlagEnabled(@NonNull d dVar) {
        return this.mAnalyticsTracker.a(dVar);
    }

    public w getAnalyticsContext() {
        return this.mAnalyticsTracker;
    }
}
