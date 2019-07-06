package com.etsy.android.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.core.img.e;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.util.l;
import java.util.ArrayList;
import java.util.List;

public class ImageBatchSwitcher extends ImageSwitcher {
    private static final String TAG = f.a(ImageBatchSwitcher.class);
    /* access modifiers changed from: private */
    @NonNull
    public List<BitmapDrawable> mBitmapDrawables = new ArrayList();
    /* access modifiers changed from: private */
    public int mCurrentImage = 0;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private c mImageBatch;
    @NonNull
    private List<String> mImageUrls = new ArrayList();
    private Runnable mRunnable;
    /* access modifiers changed from: private */
    public long mSwitchTimeMillis;

    private class a extends d {
        public a() {
            super(null);
        }

        public void a(Bitmap bitmap, boolean z) {
            super.a(bitmap, z);
            ImageBatchSwitcher.this.mBitmapDrawables.add(new BitmapDrawable(ImageBatchSwitcher.this.getResources(), bitmap));
            if (ImageBatchSwitcher.this.mBitmapDrawables.size() == 1) {
                ImageBatchSwitcher.this.setImageDrawable((BitmapDrawable) ImageBatchSwitcher.this.mBitmapDrawables.get(0));
                ImageBatchSwitcher.this.mCurrentImage = 1;
            }
            ImageBatchSwitcher.this.runLoop();
        }
    }

    public ImageBatchSwitcher(Context context) {
        super(context);
        init();
    }

    public ImageBatchSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setFactory(new ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(ImageBatchSwitcher.this.getContext());
                imageView.setLayoutParams(new LayoutParams(-1, -1));
                imageView.setScaleType(ScaleType.CENTER_CROP);
                return imageView;
            }
        });
        this.mImageBatch = new c();
        this.mHandler = new Handler();
    }

    public void startSwitchingImages(@NonNull List<String> list, @AnimRes int i, @AnimRes int i2, long j) {
        this.mImageUrls.clear();
        this.mImageUrls.addAll(list);
        setParamsAndFetchImages(i, i2, j);
    }

    public void startSwitchingBaseModelImages(@NonNull List<? extends BaseModelImage> list, @AnimRes int i, @AnimRes int i2, long j) {
        this.mImageUrls.clear();
        l lVar = new l(getContext());
        for (BaseModelImage baseModelImage : list) {
            if (getWidth() > 0) {
                this.mImageUrls.add(baseModelImage.getImageUrlForPixelWidth(getWidth()));
            } else {
                this.mImageUrls.add(baseModelImage.getImageUrlForPixelWidth(lVar.d()));
            }
        }
        setParamsAndFetchImages(i, i2, j);
    }

    private void setParamsAndFetchImages(@AnimRes int i, @AnimRes int i2, long j) {
        this.mSwitchTimeMillis = j;
        setInAnimation(getContext(), i);
        setOutAnimation(getContext(), i2);
        for (String eVar : this.mImageUrls) {
            e eVar2 = new e(eVar, null);
            eVar2.a((d) new a());
            this.mImageBatch.a(eVar2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoop();
        this.mImageBatch.a();
        this.mBitmapDrawables.clear();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 8 || this.mRunnable == null) {
            runLoop();
        } else {
            stopLoop();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            try {
                super.setImageDrawable(drawable);
            } catch (NullPointerException e) {
                f.b("Null pointer exception setting drawable of ImageBatchSwitcher. ViewFactory views may be missing.", (Throwable) e);
            }
        }
    }

    private void stopLoop() {
        this.mHandler.removeCallbacks(this.mRunnable);
        this.mRunnable = null;
    }

    /* access modifiers changed from: private */
    public void runLoop() {
        if (this.mRunnable == null && shouldRunSwitchingHandler()) {
            this.mRunnable = new Runnable() {
                public void run() {
                    ImageBatchSwitcher.this.mCurrentImage = ImageBatchSwitcher.this.mCurrentImage % ImageBatchSwitcher.this.mBitmapDrawables.size();
                    ImageBatchSwitcher.this.setImageDrawable((BitmapDrawable) ImageBatchSwitcher.this.mBitmapDrawables.get(ImageBatchSwitcher.this.mCurrentImage));
                    ImageBatchSwitcher.this.mCurrentImage = ImageBatchSwitcher.this.mCurrentImage + 1;
                    ImageBatchSwitcher.this.mHandler.postDelayed(this, ImageBatchSwitcher.this.mSwitchTimeMillis);
                }
            };
            this.mHandler.postDelayed(this.mRunnable, this.mSwitchTimeMillis);
        }
    }

    private boolean shouldRunSwitchingHandler() {
        return getVisibility() == 0 && this.mImageUrls.size() > 1 && this.mBitmapDrawables.size() > 1;
    }
}
