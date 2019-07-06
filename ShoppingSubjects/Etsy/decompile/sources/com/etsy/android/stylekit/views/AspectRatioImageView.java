package com.etsy.android.stylekit.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.etsy.android.stylekit.a.j;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class AspectRatioImageView extends ImageView {
    public static final int STANDARD_4_3_ATTR_VALUE = -1;
    private float mHeightRatio = 0.0f;

    public enum AspectRatio {
        STANDARD_4_3(0.75f),
        SQUARE(1.0f),
        FULL(0.0f);
        
        Float mAspectRatio;

        private AspectRatio(float f) {
            this.mAspectRatio = Float.valueOf(f);
        }

        public float getAspectRatio() {
            return this.mAspectRatio.floatValue();
        }
    }

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        readAttributes(attributeSet);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        readAttributes(attributeSet);
    }

    @TargetApi(21)
    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        readAttributes(attributeSet);
    }

    private void readAttributes(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.sk_AspectRatioImageView);
            this.mHeightRatio = obtainStyledAttributes.getFloat(j.sk_AspectRatioImageView_sk_aspectRatio, AspectRatio.STANDARD_4_3.getAspectRatio());
            if (this.mHeightRatio == -1.0f) {
                setHeightAspectRatio(AspectRatio.STANDARD_4_3);
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mHeightRatio <= 0.0f) {
            super.onMeasure(i, i2);
        } else {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec((int) (((float) MeasureSpec.getSize(i)) * this.mHeightRatio), ErrorDialogData.SUPPRESSED));
        }
    }

    public void setHeightAspectRatio(float f) {
        this.mHeightRatio = f;
        requestLayout();
    }

    public void setHeightAspectRatio(AspectRatio aspectRatio) {
        this.mHeightRatio = aspectRatio.getAspectRatio();
        requestLayout();
    }
}
