package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.d;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.j;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.ajm;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzks;

public final class PublisherAdView extends ViewGroup {
    private final ajm zzut;

    public PublisherAdView(Context context) {
        super(context);
        this.zzut = new ajm(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzut = new ajm(this, attributeSet, true);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzut = new ajm(this, attributeSet, true);
    }

    public final void destroy() {
        this.zzut.a();
    }

    public final a getAdListener() {
        return this.zzut.b();
    }

    public final d getAdSize() {
        return this.zzut.c();
    }

    public final d[] getAdSizes() {
        return this.zzut.d();
    }

    public final String getAdUnitId() {
        return this.zzut.e();
    }

    public final a getAppEventListener() {
        return this.zzut.f();
    }

    public final String getMediationAdapterClassName() {
        return this.zzut.k();
    }

    public final c getOnCustomRenderedAdLoadedListener() {
        return this.zzut.g();
    }

    public final i getVideoController() {
        return this.zzut.m();
    }

    public final j getVideoOptions() {
        return this.zzut.o();
    }

    public final boolean isLoading() {
        return this.zzut.l();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(d dVar) {
        this.zzut.a(dVar.a());
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            d dVar = null;
            try {
                dVar = getAdSize();
            } catch (NullPointerException e) {
                ka.b("Unable to retrieve ad size.", e);
            }
            if (dVar != null) {
                Context context = getContext();
                int b = dVar.b(context);
                i3 = dVar.a(context);
                i4 = b;
            } else {
                i3 = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public final void pause() {
        this.zzut.h();
    }

    public final void recordManualImpression() {
        this.zzut.i();
    }

    public final void resume() {
        this.zzut.j();
    }

    public final void setAdListener(a aVar) {
        this.zzut.a(aVar);
    }

    public final void setAdSizes(d... dVarArr) {
        if (dVarArr == null || dVarArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzut.b(dVarArr);
    }

    public final void setAdUnitId(String str) {
        this.zzut.a(str);
    }

    public final void setAppEventListener(a aVar) {
        this.zzut.a(aVar);
    }

    public final void setCorrelator(f fVar) {
        this.zzut.a(fVar);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzut.a(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(c cVar) {
        this.zzut.a(cVar);
    }

    public final void setVideoOptions(j jVar) {
        this.zzut.a(jVar);
    }

    public final boolean zza(zzks zzks) {
        return this.zzut.a(zzks);
    }
}
