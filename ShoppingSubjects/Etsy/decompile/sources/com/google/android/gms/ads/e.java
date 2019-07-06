package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.a;
import com.google.android.gms.internal.ads.ait;
import com.google.android.gms.internal.ads.ajm;
import com.google.android.gms.internal.ads.ka;

class e extends ViewGroup {
    protected final ajm zzut;

    public e(Context context, int i) {
        super(context);
        this.zzut = new ajm(this, i);
    }

    public e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zzut = new ajm(this, attributeSet, false, i);
    }

    public e(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zzut = new ajm(this, attributeSet, false, i2);
    }

    public void destroy() {
        this.zzut.a();
    }

    public a getAdListener() {
        return this.zzut.b();
    }

    public d getAdSize() {
        return this.zzut.c();
    }

    public String getAdUnitId() {
        return this.zzut.e();
    }

    public String getMediationAdapterClassName() {
        return this.zzut.k();
    }

    public boolean isLoading() {
        return this.zzut.l();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(c cVar) {
        this.zzut.a(cVar.a());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
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
    public void onMeasure(int i, int i2) {
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

    public void pause() {
        this.zzut.h();
    }

    public void resume() {
        this.zzut.j();
    }

    public void setAdListener(a aVar) {
        this.zzut.a(aVar);
        if (aVar == null) {
            this.zzut.a((ait) null);
            this.zzut.a((a) null);
            return;
        }
        if (aVar instanceof ait) {
            this.zzut.a((ait) aVar);
        }
        if (aVar instanceof a) {
            this.zzut.a((a) aVar);
        }
    }

    public void setAdSize(d dVar) {
        this.zzut.a(dVar);
    }

    public void setAdUnitId(String str) {
        this.zzut.a(str);
    }
}
