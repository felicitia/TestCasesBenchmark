package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class DynamicHeightViewPager extends FixedViewPager {
    private double mHeightRatio;

    public DynamicHeightViewPager(Context context) {
        super(context);
    }

    public DynamicHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setHeightRatio(double d) {
        if (d != this.mHeightRatio) {
            this.mHeightRatio = d;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return this.mHeightRatio;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mHeightRatio > 0.0d) {
            i2 = MeasureSpec.makeMeasureSpec((int) (((double) MeasureSpec.getSize(i)) * this.mHeightRatio), ErrorDialogData.SUPPRESSED);
        }
        super.onMeasure(i, i2);
    }
}
