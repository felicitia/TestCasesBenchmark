package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.d;
import com.google.android.gms.internal.ads.ajm;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ka;

@bu
public final class SearchAdView extends ViewGroup {
    private final ajm zzut;

    public SearchAdView(Context context) {
        super(context);
        this.zzut = new ajm(this);
    }

    public SearchAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzut = new ajm(this, attributeSet, false);
    }

    public SearchAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzut = new ajm(this, attributeSet, false);
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

    public final String getAdUnitId() {
        return this.zzut.e();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(a aVar) {
        if (!d.j.equals(getAdSize())) {
            throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
        }
        this.zzut.a(aVar.a());
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(b bVar) {
        this.zzut.a(bVar.b());
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

    public final void resume() {
        this.zzut.j();
    }

    public final void setAdListener(a aVar) {
        this.zzut.a(aVar);
    }

    public final void setAdSize(d dVar) {
        this.zzut.a(dVar);
    }

    public final void setAdUnitId(String str) {
        this.zzut.a(str);
    }
}
