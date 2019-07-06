package com.contextlogic.wish.ui.slideshow;

import android.content.Context;
import android.view.View.MeasureSpec;

public class SquareImageSlideshowView extends ImageSlideshowView {
    public SquareImageSlideshowView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i), 1073741824));
    }
}
