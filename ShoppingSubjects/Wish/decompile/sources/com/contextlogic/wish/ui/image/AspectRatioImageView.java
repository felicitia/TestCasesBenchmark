package com.contextlogic.wish.ui.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpManager;
import com.contextlogic.wish.http.ImageHttpManager.ImageRequest;
import com.contextlogic.wish.ui.view.Recyclable;
import com.crashlytics.android.Crashlytics;

public class AspectRatioImageView extends ImageView implements ImageRestorable, Recyclable {
    private double mAspectRatio;
    private int mColor;
    private boolean mImageFadeIn;
    private boolean mImageReleased;
    private int mImageResourceId;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAspectRatio(double d) {
        if (d != this.mAspectRatio) {
            this.mAspectRatio = d;
            requestLayout();
        }
    }

    public double getAspectRatio() {
        return this.mAspectRatio;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mAspectRatio > 0.0d) {
            int size = MeasureSpec.getSize(i);
            setMeasuredDimension(size, (int) (((double) size) * this.mAspectRatio));
            return;
        }
        try {
            super.onMeasure(i, i2);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Second try: ");
            sb.append(th.toString());
            Crashlytics.logException(new Exception(sb.toString()));
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        }
    }

    public void onImageDrawableLoaded(Drawable drawable) {
        updateBackgroundColor(drawable);
        super.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bitmap) {
        updateBackgroundColor(bitmap);
        this.mImageResourceId = 0;
        super.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable) {
        updateBackgroundColor(drawable);
        this.mImageResourceId = 0;
        super.setImageDrawable(drawable);
    }

    public void setImageResource(int i) {
        setImageResource(i, false);
    }

    public void setBackgroundColor(int i) {
        this.mColor = i;
        super.setBackgroundColor(i);
    }

    public void setPlaceholderBackground(int i) {
        this.mColor = i;
    }

    public void setImageResource(int i, boolean z) {
        this.mImageResourceId = i;
        this.mImageFadeIn = z;
        if (i > 0) {
            super.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
            if (this.mImageFadeIn) {
                ImageRequest imageRequest = new ImageRequest(this.mImageResourceId);
                imageRequest.setFadeIn(z);
                imageRequest.setImageView(this, null);
                ImageHttpManager.getInstance().request(imageRequest);
                return;
            }
            try {
                super.setImageResource(this.mImageResourceId);
            } catch (Throwable unused) {
                super.setBackgroundColor(this.mColor);
            }
        } else {
            super.setBackgroundColor(this.mColor);
            super.setImageResource(0);
        }
    }

    public void recycle() {
        ImageHttpManager.getInstance().cancelRequest(this);
        if (getDrawable() != null) {
            getDrawable().setCallback(null);
        }
        super.setBackgroundColor(this.mColor);
        super.setImageDrawable(null);
    }

    public void releaseImages() {
        if (this.mImageResourceId != 0) {
            this.mImageReleased = true;
            recycle();
        }
    }

    public void restoreImages() {
        if (this.mImageReleased) {
            this.mImageReleased = false;
            if (this.mImageResourceId != 0) {
                setImageResource(this.mImageResourceId, this.mImageFadeIn);
            }
        }
    }

    private void updateBackgroundColor(Object obj) {
        if (obj == null) {
            super.setBackgroundColor(this.mColor);
        } else {
            super.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
        }
    }
}
