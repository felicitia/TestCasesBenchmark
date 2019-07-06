package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Preconditions;

public final class AdView extends e {
    public AdView(Context context) {
        super(context, 0);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public AdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public AdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
    }

    public final /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }

    public final /* bridge */ /* synthetic */ a getAdListener() {
        return super.getAdListener();
    }

    public final /* bridge */ /* synthetic */ d getAdSize() {
        return super.getAdSize();
    }

    public final /* bridge */ /* synthetic */ String getAdUnitId() {
        return super.getAdUnitId();
    }

    public final /* bridge */ /* synthetic */ String getMediationAdapterClassName() {
        return super.getMediationAdapterClassName();
    }

    public final i getVideoController() {
        if (this.zzut != null) {
            return this.zzut.m();
        }
        return null;
    }

    public final /* bridge */ /* synthetic */ boolean isLoading() {
        return super.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final /* bridge */ /* synthetic */ void loadAd(c cVar) {
        super.loadAd(cVar);
    }

    public final /* bridge */ /* synthetic */ void pause() {
        super.pause();
    }

    public final /* bridge */ /* synthetic */ void resume() {
        super.resume();
    }

    public final /* bridge */ /* synthetic */ void setAdListener(a aVar) {
        super.setAdListener(aVar);
    }

    public final /* bridge */ /* synthetic */ void setAdSize(d dVar) {
        super.setAdSize(dVar);
    }

    public final /* bridge */ /* synthetic */ void setAdUnitId(String str) {
        super.setAdUnitId(str);
    }
}
