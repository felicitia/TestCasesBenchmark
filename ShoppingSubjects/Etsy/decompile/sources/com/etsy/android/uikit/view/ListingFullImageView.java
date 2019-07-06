package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;

public class ListingFullImageView extends FullImageView {
    public static final float ASPECT_RATIO_STANDARD = 0.75f;

    public ListingFullImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ListingFullImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ListingFullImageView(Context context) {
        super(context);
    }

    public void setUseStandardRatio(boolean z) {
        this.mAspectRatio = z ? 0.75f : 0.0f;
    }

    /* access modifiers changed from: protected */
    public void loadImage() {
        String fullHeightImageUrlForPixelWidth;
        boolean z = Math.abs(this.mAspectRatio - 0.75f) < 1.0E-7f;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (z) {
            fullHeightImageUrlForPixelWidth = this.mImage.get4to3ImageUrlForPixelWidth(measuredWidth);
        } else {
            fullHeightImageUrlForPixelWidth = this.mImage.getFullHeightImageUrlForPixelWidth(measuredWidth);
        }
        this.mImageBatch.a(fullHeightImageUrlForPixelWidth, this, measuredWidth, measuredHeight, this.mLoadingColor);
    }
}
