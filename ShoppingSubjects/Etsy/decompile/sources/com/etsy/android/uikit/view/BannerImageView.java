package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.core.img.e;
import com.etsy.android.lib.models.apiv3.Image;

public class BannerImageView extends AppCompatImageView {
    @Nullable
    private d mDownloadListener;
    private Image mImage;
    private c mImageBatch;
    private boolean mImageLoadRequested;
    private int mLoadingColor;

    public BannerImageView(Context context) {
        super(context);
    }

    public BannerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageInfo(@NonNull Image image, @NonNull c cVar, d dVar) {
        this.mImage = image;
        this.mImageBatch = cVar;
        this.mDownloadListener = dVar;
        this.mLoadingColor = image.getImageColor();
        this.mImageLoadRequested = false;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        float fullHeight = ((float) this.mImage.getFullHeight()) / ((float) this.mImage.getFullWidth());
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) * fullHeight));
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
        e eVar = new e(c.a(getMeasuredWidth(), getMeasuredHeight(), this.mImage), this);
        eVar.d(this.mLoadingColor);
        if (this.mDownloadListener != null) {
            eVar.a(this.mDownloadListener);
        }
        this.mImageBatch.a(eVar);
    }
}
