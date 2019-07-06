package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.etsy.android.lib.a.q;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class MaxWidthRelativeLayout extends RelativeLayout {
    public int mMaxWidth;

    public MaxWidthRelativeLayout(Context context) {
        super(context);
    }

    public MaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, q.MaxWidthLayout, 0, 0);
        try {
            this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(q.MaxWidthLayout_maxWidth, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mMaxWidth > 0 && MeasureSpec.getSize(i) > this.mMaxWidth) {
            i = MeasureSpec.makeMeasureSpec(this.mMaxWidth, ErrorDialogData.SUPPRESSED);
        }
        super.onMeasure(i, i2);
    }
}
