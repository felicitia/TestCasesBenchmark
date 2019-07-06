package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class ExpandToFitImageView extends AppCompatImageView {
    private static final int MATCH_PARENT = -1;
    private static final int WRAP_CONTENT = -2;

    public ExpandToFitImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int i3 = getLayoutParams().width;
            int i4 = getLayoutParams().height;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth == -1 || intrinsicHeight == -1) {
                super.onMeasure(i, i2);
                return;
            }
            float f = ((float) intrinsicWidth) / ((float) intrinsicHeight);
            if (i3 == -1 && i4 == -2) {
                int size = MeasureSpec.getSize(i);
                setMeasuredDimension(size, (int) Math.ceil((double) (((float) size) / f)));
            } else if (i4 == -1 && i3 == -2) {
                int size2 = MeasureSpec.getSize(i2);
                setMeasuredDimension((int) Math.ceil((double) (((float) size2) * f)), size2);
            } else {
                super.onMeasure(i, i2);
            }
        } else {
            super.onMeasure(i, i2);
        }
    }
}
