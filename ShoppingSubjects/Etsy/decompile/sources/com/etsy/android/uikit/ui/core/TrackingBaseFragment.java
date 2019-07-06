package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.bucketing.d;
import com.etsy.android.lib.config.h;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;

public abstract class TrackingBaseFragment extends BaseFragment implements j {
    private TrackingFragmentDelegate mTrackingDelegate = new TrackingFragmentDelegate(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTrackingDelegate.a(bundle);
    }

    public void onResume() {
        super.onResume();
        this.mTrackingDelegate.a();
    }

    public void onPause() {
        super.onPause();
        this.mTrackingDelegate.b();
    }

    public void onStop() {
        super.onStop();
        this.mTrackingDelegate.c();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mTrackingDelegate.d();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mTrackingDelegate.a(activity);
    }

    public void onDetach() {
        super.onDetach();
        this.mTrackingDelegate.e();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mTrackingDelegate.b(bundle);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mTrackingDelegate.a(z);
    }

    public h getConfigMap() {
        return this.mTrackingDelegate.f();
    }

    public boolean isNativeFlagEnabled(@NonNull d dVar) {
        return this.mTrackingDelegate.a(dVar);
    }

    @NonNull
    public final String getDefaultName() {
        return this.mTrackingDelegate.getDefaultName();
    }

    @NonNull
    public String getTrackingName() {
        return this.mTrackingDelegate.getTrackingName();
    }

    @Nullable
    public j getTrackingParent() {
        return this.mTrackingDelegate.getTrackingParent();
    }

    public w getAnalyticsContext() {
        return this.mTrackingDelegate.getAnalyticsContext();
    }

    @Nullable
    public Context getAndroidContext() {
        return this.mTrackingDelegate.getAndroidContext();
    }

    public boolean shouldAutoTrack() {
        return this.mTrackingDelegate.g();
    }
}
