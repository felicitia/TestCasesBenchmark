package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;

public class SquareTaggableImageView extends TaggableImageView {
    public SquareTaggableImageView(Context context) {
        super(context);
    }

    public SquareTaggableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareTaggableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
