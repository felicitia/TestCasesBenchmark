package com.etsy.android.ui.finds.cardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.etsy.android.e.b;
import com.etsy.android.lib.logger.f;

public class FindsCardView extends CardView {
    private static final String TAG = f.a(FindsCardView.class);
    private Float mAspectRatio;

    public FindsCardView(Context context) {
        super(context);
    }

    public FindsCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAspectRatio(context, attributeSet);
    }

    public FindsCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAspectRatio(context, attributeSet);
    }

    private void initAspectRatio(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.FindsCardView);
        this.mAspectRatio = Float.valueOf(obtainStyledAttributes.getFloat(0, 1.0f));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec((int) (((float) MeasureSpec.getSize(i)) * this.mAspectRatio.floatValue()), MeasureSpec.getMode(i)));
    }
}
