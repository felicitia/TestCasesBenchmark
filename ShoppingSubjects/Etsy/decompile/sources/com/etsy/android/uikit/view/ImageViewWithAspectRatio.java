package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.core.img.e;
import com.etsy.android.lib.models.BaseModelImage;

public class ImageViewWithAspectRatio extends AppCompatImageView {
    public static final double STANDARD_IMAGE_ASPECT_RATIO = 0.75d;
    private CropType mCropType = CropType.NONE;
    private boolean mDoMaintainAspectRatio = false;
    private double mHeightRatio;
    private BaseModelImage mImage;
    private c mImageBatch;
    private boolean mImageLoadRequested;
    private d mListener;
    private int mLoadingColor;
    private boolean mUseStandardRatio = false;

    public enum CropType {
        TOP,
        BOTTOM,
        NONE
    }

    public ImageViewWithAspectRatio(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public ImageViewWithAspectRatio(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ImageViewWithAspectRatio(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.ImageViewWithAspectRatio);
            setAspectRatio((double) obtainStyledAttributes.getFloat(q.ImageViewWithAspectRatio_heightRatio, 0.0f));
            this.mDoMaintainAspectRatio = obtainStyledAttributes.getBoolean(q.ImageViewWithAspectRatio_doMaintainAspect, this.mDoMaintainAspectRatio);
            obtainStyledAttributes.recycle();
        }
    }

    public void setAspectRatio(double d) {
        this.mHeightRatio = d;
        this.mUseStandardRatio = Math.abs(d - 0.75d) < 1.0E-7d;
    }

    public void setUseStandardRatio(boolean z) {
        this.mUseStandardRatio = z;
        if (z) {
            this.mHeightRatio = 0.75d;
        }
    }

    public void setImageDownloadListener(d dVar) {
        this.mListener = dVar;
    }

    public void setImageInfo(BaseModelImage baseModelImage, c cVar) {
        this.mImage = baseModelImage;
        this.mImageBatch = cVar;
        this.mImageLoadRequested = false;
        if (baseModelImage != null) {
            this.mLoadingColor = baseModelImage.getImageColor();
        }
        requestLayout();
    }

    private void specialCrop() {
        float f;
        float f2;
        float f3;
        if (getDrawable() != null) {
            int intrinsicWidth = getDrawable().getIntrinsicWidth();
            int intrinsicHeight = getDrawable().getIntrinsicHeight();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            Matrix matrix = new Matrix();
            if (intrinsicWidth * height > width * intrinsicHeight) {
                float f4 = ((float) height) / ((float) intrinsicHeight);
                f2 = (((float) width) - (((float) intrinsicWidth) * f4)) * 0.5f;
                f3 = f4;
                f = 0.0f;
            } else {
                f3 = ((float) width) / ((float) intrinsicWidth);
                f = ((float) height) - (((float) intrinsicHeight) * f3);
                f2 = 0.0f;
            }
            matrix.setScale(f3, f3);
            switch (this.mCropType) {
                case TOP:
                    matrix.postTranslate((float) ((int) (f2 + 0.5f)), 0.0f);
                    break;
                case BOTTOM:
                    matrix.postTranslate((float) ((int) (f2 + 0.5f)), (float) ((int) (f + 0.5f)));
                    break;
                default:
                    matrix.postTranslate((float) ((int) (f2 + 0.5f)), (float) ((int) ((f * 0.5f) + 0.5f)));
                    break;
            }
            setScaleType(ScaleType.MATRIX);
            setImageMatrix(matrix);
        }
    }

    public void setSpecialCrop(CropType cropType) {
        if (cropType != null) {
            this.mCropType = cropType;
        }
    }

    public void setDoMaintainAspectRatio(boolean z) {
        this.mDoMaintainAspectRatio = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        String str;
        int size = MeasureSpec.getSize(i);
        this.mHeightRatio = (this.mImage == null || !this.mDoMaintainAspectRatio) ? this.mHeightRatio : (double) (((float) this.mImage.getFullHeight()) / ((float) this.mImage.getFullWidth()));
        int i3 = (int) (((double) size) * this.mHeightRatio);
        if (this.mImage != null) {
            setMeasuredDimension(size, i3);
            if (!this.mImageLoadRequested) {
                setBackgroundColor(this.mLoadingColor);
                if (size > 0) {
                    this.mImageLoadRequested = true;
                    if (this.mUseStandardRatio) {
                        str = this.mImage.get4to3ImageUrlForPixelWidth(size);
                    } else {
                        str = this.mImage.getImageUrlForPixelWidth(size);
                    }
                    e eVar = new e(str, this);
                    eVar.a(size, i3);
                    if (!this.mImageBatch.a(str, size, i3) && this.mListener != null) {
                        eVar.a(this.mListener);
                    }
                    this.mImageBatch.a(eVar);
                }
            }
        } else if (this.mHeightRatio != 0.0d) {
            setMeasuredDimension(size, i3);
        } else {
            super.onMeasure(i, i2);
        }
        if (this.mCropType != CropType.NONE) {
            specialCrop();
        }
    }

    public void cleanup() {
        setImageDrawable(null);
    }
}
