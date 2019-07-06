package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.IFullImage;

public class FullImageView extends ForegroundImageView {
    public static final float ASPECT_FULL_HEIGHT = 0.0f;
    public static final float ASPECT_NONE = -3423432.0f;
    public static final float ASPECT_RATIO_SQUARE = 1.0f;
    public static final int IMAGE_SHAPE_CIRCULAR = 1;
    public static final int IMAGE_SHAPE_RECTANGULAR = 2;
    public static final float SHOP_ABOUT_IMAGE_HEIGHT_RATIO = 0.6158f;
    protected float mAspectRatio = 0.0f;
    protected IFullImage mImage;
    protected c mImageBatch;
    protected int mImageFullHeight = 1;
    protected int mImageFullWidth = 1;
    protected boolean mImageLoadRequested;
    protected int mImageShape = 2;
    protected int mLoadingColor;

    public FullImageView(Context context) {
        super(context);
        init(context, null);
    }

    public FullImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public FullImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void init(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet == null || context == null) {
            setHeightRatio(0.0f);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.FullImageView);
        setHeightRatio(obtainStyledAttributes.getFloat(q.FullImageView_imageAspect, 0.0f));
        obtainStyledAttributes.recycle();
    }

    public void setHeightRatio(float f) {
        this.mAspectRatio = f;
    }

    public void setImageInfo(IFullImage iFullImage, c cVar) {
        setImageInfo(iFullImage, cVar, 2);
    }

    public void setLoadingColor(@ColorInt int i) {
        this.mLoadingColor = i;
    }

    public void setImageInfo(IFullImage iFullImage, c cVar, int i) {
        this.mImage = iFullImage;
        this.mImageBatch = cVar;
        this.mImageLoadRequested = false;
        this.mImageShape = i;
        if (iFullImage != null) {
            this.mLoadingColor = iFullImage.getImageColor();
            this.mImageFullHeight = iFullImage.getFullHeight();
            this.mImageFullWidth = iFullImage.getFullWidth();
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        float f;
        if (this.mAspectRatio == -3423432.0f) {
            super.onMeasure(i, i2);
            return;
        }
        if (((double) Math.abs(this.mAspectRatio - 0.0f)) < 1.0E-7d) {
            f = ((float) this.mImageFullHeight) / ((float) this.mImageFullWidth);
        } else {
            f = this.mAspectRatio;
        }
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) * f));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mImage != null && !this.mImageLoadRequested) {
            this.mImageLoadRequested = true;
            loadImage();
        }
    }

    /* access modifiers changed from: protected */
    public void loadImage() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        String fullHeightImageUrlForPixelWidth = this.mImage.getFullHeightImageUrlForPixelWidth(measuredWidth);
        if (this.mImageShape == 2) {
            this.mImageBatch.a(fullHeightImageUrlForPixelWidth, this, measuredWidth, measuredHeight, this.mLoadingColor);
            return;
        }
        this.mImageBatch.b(fullHeightImageUrlForPixelWidth, this, measuredWidth, this.mLoadingColor);
    }

    public void cleanUp() {
        this.mImageBatch = null;
        this.mImage = null;
        setImageDrawable(null);
    }
}
