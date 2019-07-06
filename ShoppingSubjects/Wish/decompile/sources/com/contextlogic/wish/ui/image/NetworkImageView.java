package com.contextlogic.wish.ui.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView.ScaleType;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpManager;
import com.contextlogic.wish.http.ImageHttpManager.ImageCallback;
import com.contextlogic.wish.http.ImageHttpManager.ImageRequest;
import com.contextlogic.wish.http.ImageHttpManager.ImageTransformation;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.drawable.SizedInsetDrawable;
import com.contextlogic.wish.ui.loading.CircularProgressDrawable;
import com.contextlogic.wish.ui.loading.ThreeDotProgressDrawable;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkImageView extends ZoomingImageView implements OnPreDrawListener, ImageRestorable, Recyclable {
    private static final Pattern PRODUCT_ID_REGEX = Pattern.compile("/[0-9,a-f]{24}(-[0-9]+)?");
    private NetworkImageViewCallback mCallBack;
    /* access modifiers changed from: private */
    public boolean mCircleCrop;
    /* access modifiers changed from: private */
    public Runnable mDeferredPlaceholderAction;
    /* access modifiers changed from: private */
    public Runnable mDeferredRequestAction;
    private WishImage mImage;
    private boolean mImageLoaded;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    private boolean mImageReleased;
    private String mLastFetchedUrl;
    private Drawable mPlaceholderDrawable;
    private int mPlaceholderResId;
    private ResizeType mResizeType;
    /* access modifiers changed from: private */
    public boolean mRoundedCorners;
    private boolean mSizeInvalidated;
    /* access modifiers changed from: private */
    public boolean mUseDynamicScaling;
    private boolean mViewTreeObserverRegistered;
    private Drawable mWrappedPlaceholderDrawable;
    private boolean mZoomable;

    private static class CircleTransformation implements ImageTransformation {
        public String getCacheKey() {
            return "circle";
        }

        private CircleTransformation() {
        }

        public Bitmap transform(Bitmap bitmap) {
            int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
            float f = ((float) min) / 2.0f;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
            Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
            new Canvas(createBitmap).drawCircle(f, f, f, paint);
            if (bitmap != createBitmap) {
                bitmap.recycle();
            }
            return createBitmap;
        }
    }

    private static class DensitySizeTransformation implements ImageTransformation {
        private int mMaxHeight;
        private int mMaxWidth;

        public DensitySizeTransformation(int i, int i2) {
            this.mMaxWidth = i;
            this.mMaxHeight = i2;
        }

        public Bitmap transform(Bitmap bitmap) {
            try {
                float f = WishApplication.getInstance().getResources().getDisplayMetrics().density;
                int width = (int) (((float) bitmap.getWidth()) * f);
                int height = (int) (f * ((float) bitmap.getHeight()));
                double d = (double) width;
                double d2 = ((double) this.mMaxWidth) / d;
                double d3 = (double) height;
                double d4 = ((double) this.mMaxHeight) / d3;
                if (d2 <= d4 && d2 < 1.0d) {
                    width = (int) (d * d2);
                    height = (int) (d2 * d3);
                } else if (d4 < d2 && d4 < 1.0d) {
                    width = (int) (d * d4);
                    height = (int) (d4 * d3);
                }
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                if (createScaledBitmap != bitmap) {
                    bitmap.recycle();
                }
                return createScaledBitmap;
            } catch (Throwable unused) {
                return bitmap;
            }
        }

        public String getCacheKey() {
            StringBuilder sb = new StringBuilder();
            sb.append("density-size-");
            sb.append(this.mMaxWidth);
            sb.append("-");
            sb.append(this.mMaxHeight);
            return sb.toString();
        }
    }

    public interface NetworkImageViewCallback {
        void onImageLoadFailed();

        void onImageLoaded();
    }

    public enum ResizeType {
        CROP,
        FIT,
        NONE
    }

    private static class ResizedImageUrl {
        private boolean mIsResized;
        /* access modifiers changed from: private */
        public String mUrl;

        public ResizedImageUrl(String str, boolean z) {
            this.mUrl = str;
            this.mIsResized = z;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public boolean isResized() {
            return this.mIsResized;
        }
    }

    private static class RoundedCornerTransformation implements ImageTransformation {
        public String getCacheKey() {
            return "rounded-corner";
        }

        private RoundedCornerTransformation() {
        }

        public Bitmap transform(Bitmap bitmap) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawRoundRect(rectF, 8.0f, 8.0f, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            if (bitmap != createBitmap) {
                bitmap.recycle();
            }
            return createBitmap;
        }
    }

    private static boolean shouldForceHttp() {
        return true;
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mSizeInvalidated = true;
        this.mImageLoaded = false;
        setZoomable(false);
        setCircleCrop(false);
        setUseDynamicScaling(false);
        setRoundedCorners(false);
    }

    public void setZoomable(boolean z) {
        this.mZoomable = z;
        if (!this.mZoomable || !this.mImageLoaded) {
            disableZooming();
        } else {
            enableZooming();
        }
    }

    public void setUseDynamicScaling(boolean z) {
        this.mUseDynamicScaling = z;
    }

    public void setCircleCrop(boolean z) {
        this.mCircleCrop = z;
    }

    public void setRoundedCorners(boolean z) {
        this.mRoundedCorners = z;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void setImageUrl(String str) {
        setImageUrl(str, ResizeType.CROP);
    }

    public void setImageUrl(String str, ResizeType resizeType) {
        setImageUrl(str, resizeType, null);
    }

    public void setImageUrl(String str, ResizeType resizeType, NetworkImageViewCallback networkImageViewCallback) {
        if (str != null) {
            setImage(new WishImage(str), resizeType, networkImageViewCallback);
        } else {
            setImage(null, resizeType, networkImageViewCallback);
        }
    }

    public void setImage(WishImage wishImage) {
        setImage(wishImage, ResizeType.CROP);
    }

    public void setImage(WishImage wishImage, boolean z) {
        setImage(wishImage, ResizeType.CROP, null, z, 2);
    }

    public void setImage(WishImage wishImage, ResizeType resizeType) {
        setImage(wishImage, resizeType, null, true, 2);
    }

    public void setImage(WishImage wishImage, ResizeType resizeType, NetworkImageViewCallback networkImageViewCallback) {
        setImage(wishImage, resizeType, networkImageViewCallback, true, 2);
    }

    public void setImage(WishImage wishImage, int i) {
        setImage(wishImage, ResizeType.CROP, null, true, i);
    }

    public void setImage(WishImage wishImage, NetworkImageViewCallback networkImageViewCallback) {
        setImage(wishImage, ResizeType.CROP, networkImageViewCallback, true, 2);
    }

    public void setImage(WishImage wishImage, ResizeType resizeType, NetworkImageViewCallback networkImageViewCallback, boolean z) {
        setImage(wishImage, resizeType, networkImageViewCallback, z, 2);
    }

    public void setImage(WishImage wishImage, ResizeType resizeType, NetworkImageViewCallback networkImageViewCallback, boolean z, int i) {
        this.mImage = wishImage;
        this.mCallBack = networkImageViewCallback;
        if (this.mUseDynamicScaling) {
            resizeType = ResizeType.FIT;
        }
        this.mResizeType = resizeType;
        requestImage(z, i);
    }

    public void setImageResource(int i) {
        setImageResource(i, ResizeType.FIT);
    }

    public void setImageResource(int i, ResizeType resizeType) {
        this.mResizeType = resizeType;
        clearImage(false);
        super.setImageResource(i);
    }

    private void requestImage() {
        requestImage(true, 2);
    }

    private void requestImage(boolean z, int i) {
        if (z) {
            clearImage(true);
        }
        this.mImageReleased = false;
        if (this.mImage != null) {
            if (getViewTreeObserver() != null && !this.mViewTreeObserverRegistered) {
                this.mViewTreeObserverRegistered = true;
                getViewTreeObserver().addOnPreDrawListener(this);
            }
            this.mDeferredRequestAction = createImageRequestAction(this.mImage, this.mResizeType, i);
            if (hasValidatedSize()) {
                this.mDeferredRequestAction.run();
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearImage(boolean z) {
        if (getViewTreeObserver() != null && this.mViewTreeObserverRegistered) {
            getViewTreeObserver().removeOnPreDrawListener(this);
            this.mViewTreeObserverRegistered = false;
        }
        this.mImageLoaded = false;
        this.mDeferredRequestAction = null;
        this.mDeferredPlaceholderAction = null;
        ImageHttpManager.getInstance().cancelRequest(this);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setCallback(null);
        }
        setImageDrawable(null);
        if (this.mUseDynamicScaling) {
            setScaleType(ScaleType.CENTER);
        } else if (this.mResizeType == ResizeType.CROP) {
            setScaleType(ScaleType.CENTER_CROP);
        } else if (this.mResizeType == ResizeType.FIT) {
            setScaleType(ScaleType.FIT_CENTER);
        }
        disableZooming();
        if (z) {
            this.mDeferredPlaceholderAction = createPlaceholderAction();
            if (hasValidatedSize()) {
                this.mDeferredPlaceholderAction.run();
            } else if (getViewTreeObserver() != null && !this.mViewTreeObserverRegistered) {
                this.mViewTreeObserverRegistered = true;
                getViewTreeObserver().addOnPreDrawListener(this);
            }
        }
    }

    public void setPlaceholder(int i) {
        clearPlaceholder();
        this.mPlaceholderResId = i;
        if (!this.mImageLoaded) {
            resetPlaceholder();
        }
    }

    public void setPlaceholderSpinner(int i) {
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.loading_spinner_drawable_size);
            ThreeDotProgressDrawable threeDotProgressDrawable = new ThreeDotProgressDrawable(i);
            SizedInsetDrawable sizedInsetDrawable = new SizedInsetDrawable(threeDotProgressDrawable, dimensionPixelSize, dimensionPixelSize);
            clearPlaceholder();
            this.mWrappedPlaceholderDrawable = threeDotProgressDrawable;
            this.mPlaceholderDrawable = sizedInsetDrawable;
        } else {
            int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.progress_bar_drawable_size);
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(i, (float) getContext().getResources().getDimensionPixelSize(R.dimen.progress_bar_drawable_stroke), 2000, 600);
            SizedInsetDrawable sizedInsetDrawable2 = new SizedInsetDrawable(circularProgressDrawable, dimensionPixelSize2, dimensionPixelSize2);
            clearPlaceholder();
            this.mWrappedPlaceholderDrawable = circularProgressDrawable;
            this.mPlaceholderDrawable = sizedInsetDrawable2;
        }
        if (!this.mImageLoaded) {
            resetPlaceholder();
        }
    }

    public void setPlaceholderColor(int i) {
        setPlaceholder((Drawable) new ColorDrawable(i));
    }

    public void setPlaceholder(Drawable drawable) {
        clearPlaceholder();
        this.mPlaceholderDrawable = drawable;
        if (!this.mImageLoaded) {
            resetPlaceholder();
        }
    }

    public void clearPlaceholder() {
        this.mPlaceholderResId = 0;
        if (this.mPlaceholderDrawable != null) {
            stopDrawable(this.mPlaceholderDrawable);
            this.mPlaceholderDrawable.setCallback(null);
        }
        this.mPlaceholderDrawable = null;
        if (this.mWrappedPlaceholderDrawable != null) {
            stopDrawable(this.mWrappedPlaceholderDrawable);
            this.mWrappedPlaceholderDrawable.setCallback(null);
        }
        this.mWrappedPlaceholderDrawable = null;
    }

    /* access modifiers changed from: private */
    public void resetPlaceholder() {
        if (this.mPlaceholderResId != 0) {
            super.setImageResource(this.mPlaceholderResId);
        } else if (this.mPlaceholderDrawable != null) {
            setImageDrawable(this.mPlaceholderDrawable);
        } else {
            super.setImageResource(0);
        }
        Drawable drawable = getDrawable();
        if ((drawable instanceof InsetDrawable) && drawable == this.mPlaceholderDrawable) {
            drawable = this.mWrappedPlaceholderDrawable;
        }
        if (drawable != null) {
            startDrawable(drawable);
        }
    }

    private void startDrawable(Drawable drawable) {
        if (drawable != null && (drawable instanceof Animatable)) {
            ((Animatable) drawable).start();
        }
    }

    private void stopDrawable(Drawable drawable) {
        if (drawable != null && (drawable instanceof Animatable)) {
            ((Animatable) drawable).stop();
        }
    }

    public void recycle() {
        clearImage(false);
    }

    public void releaseImages() {
        if (this.mImage != null && !this.mImageReleased) {
            recycle();
            this.mImageReleased = true;
        }
    }

    public void restoreImages() {
        if (this.mImageReleased) {
            requestImage();
        }
    }

    /* access modifiers changed from: private */
    public boolean hasValidatedSize() {
        return getWidth() > 0 && getHeight() > 0 && !this.mSizeInvalidated;
    }

    public String getLastFetchedUrl() {
        return this.mLastFetchedUrl;
    }

    private static ResizedImageUrl getResizedImageUrl(WishImage wishImage, ResizeType resizeType, int i, int i2, boolean z, int i3) {
        String str;
        boolean z2;
        if (Math.max(i, i2) >= 200) {
            str = wishImage.getUrlString(ImageSize.LARGE);
        } else {
            str = wishImage.getUrlString(ImageSize.MEDIUM);
        }
        if (resizeType == ResizeType.NONE) {
            return new ResizedImageUrl(str, true);
        }
        boolean z3 = false;
        if (!z) {
            ArrayList resizedProductImagePaths = ConfigDataCenter.getInstance().getResizedProductImagePaths();
            if (resizedProductImagePaths.size() > 0) {
                Iterator it = resizedProductImagePaths.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (str.contains((String) it.next())) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z2 = false;
            if (z2) {
                Pair productIdAndSequenceIdFromUrl = getProductIdAndSequenceIdFromUrl(str);
                String str2 = (String) productIdAndSequenceIdFromUrl.first;
                String str3 = (String) productIdAndSequenceIdFromUrl.second;
                if (str2 != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(ConfigDataCenter.getInstance().getProductImageResizeUrl());
                    sb.append("?contest_id=");
                    sb.append(str2);
                    sb.append("&w=");
                    sb.append(String.valueOf(i));
                    sb.append("&h=");
                    sb.append(String.valueOf(i2));
                    sb.append("&m=");
                    int i4 = -1;
                    if (resizeType == ResizeType.FIT) {
                        i4 = 0;
                    } else if (resizeType == ResizeType.CROP) {
                        i4 = 1;
                    }
                    sb.append(String.valueOf(i4));
                    if (str3 != null) {
                        sb.append("&s=");
                        sb.append(str3);
                    }
                    str = sb.toString();
                    z3 = true;
                }
            }
        }
        String cacheBuster = wishImage.getCacheBuster();
        if (!(cacheBuster == null || str == null)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                str = parse.buildUpon().appendQueryParameter("_app_cache_bust", cacheBuster).build().toString();
            }
        }
        if (shouldForceHttp()) {
            str = str.replace("https://", "http://");
        }
        return new ResizedImageUrl(str, z3);
    }

    private static Pair<String, String> getProductIdAndSequenceIdFromUrl(String str) {
        String str2;
        Matcher matcher = PRODUCT_ID_REGEX.matcher(str);
        String str3 = null;
        if (matcher.find()) {
            str2 = matcher.group(0).substring(1);
            int indexOf = str2.indexOf(45);
            if (indexOf != -1) {
                str3 = str2.substring(indexOf + 1);
                str2 = str2.substring(0, indexOf);
            }
        } else {
            str2 = null;
        }
        return new Pair<>(str2, str3);
    }

    private Runnable createPlaceholderAction() {
        return new Runnable() {
            public void run() {
                if (this == NetworkImageView.this.mDeferredPlaceholderAction) {
                    if (NetworkImageView.this.hasValidatedSize()) {
                        NetworkImageView.this.resetPlaceholder();
                    }
                    NetworkImageView.this.mDeferredPlaceholderAction = null;
                }
            }
        };
    }

    private Runnable createImageRequestAction(WishImage wishImage, ResizeType resizeType, int i) {
        final WishImage wishImage2 = wishImage;
        final ResizeType resizeType2 = resizeType;
        final int i2 = i;
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                if (this == NetworkImageView.this.mDeferredRequestAction) {
                    if (NetworkImageView.this.hasValidatedSize()) {
                        NetworkImageView.performImageRequestAction(this, wishImage2, resizeType2, NetworkImageView.this.getWidth(), NetworkImageView.this.getHeight(), NetworkImageView.this.mUseDynamicScaling, NetworkImageView.this.mCircleCrop, NetworkImageView.this.getDrawable(), null, i2, NetworkImageView.this.mRoundedCorners);
                        if (NetworkImageView.this.mImagePrefetcher != null) {
                            NetworkImageView.this.mImagePrefetcher.cancelPrefetch(wishImage2);
                        }
                        if (NetworkImageView.this.mImagePrefetcher != null) {
                            WishImage dequeueImage = NetworkImageView.this.mImagePrefetcher.dequeueImage();
                            while (dequeueImage != null) {
                                NetworkImageView.prefetchImage(dequeueImage, resizeType2, NetworkImageView.this.getWidth(), NetworkImageView.this.getHeight(), NetworkImageView.this.mUseDynamicScaling, NetworkImageView.this.mCircleCrop, NetworkImageView.this.mImagePrefetcher, i2, NetworkImageView.this.mRoundedCorners);
                                dequeueImage = NetworkImageView.this.mImagePrefetcher.dequeueImage();
                            }
                        }
                    } else {
                        NetworkImageView.this.clearImage(true);
                    }
                    NetworkImageView.this.mDeferredRequestAction = null;
                }
            }
        };
        return r0;
    }

    public static void prefetchImage(WishImage wishImage, ResizeType resizeType, int i, int i2, boolean z, boolean z2, ImageHttpPrefetcher imageHttpPrefetcher, int i3, boolean z3) {
        WishImage wishImage2 = wishImage;
        performImageRequestAction(null, wishImage2, resizeType, i, i2, z, z2, null, imageHttpPrefetcher.getPrefetchTag(wishImage2), i3, z3);
    }

    /* access modifiers changed from: private */
    public static void performImageRequestAction(NetworkImageView networkImageView, WishImage wishImage, ResizeType resizeType, int i, int i2, boolean z, boolean z2, Drawable drawable, Object obj, int i3, boolean z3) {
        NetworkImageView networkImageView2 = networkImageView;
        int i4 = i;
        int i5 = i2;
        boolean z4 = z;
        ResizedImageUrl resizedImageUrl = getResizedImageUrl(wishImage, resizeType, i4, i5, z4, i3);
        ImageRequest imageRequest = getImageRequest(networkImageView2, resizedImageUrl, i4, i5, z4, z2, drawable, obj, z3);
        imageRequest.setImageSource(Integer.valueOf(i3));
        if (networkImageView2 != null) {
            networkImageView2.mLastFetchedUrl = resizedImageUrl.mUrl;
        }
        ImageHttpManager.getInstance().request(imageRequest);
    }

    private static ImageRequest getImageRequest(NetworkImageView networkImageView, ResizedImageUrl resizedImageUrl, int i, int i2, boolean z, boolean z2, Drawable drawable, Object obj, boolean z3) {
        String url = resizedImageUrl.getUrl();
        boolean isResized = resizedImageUrl.isResized();
        boolean z4 = networkImageView == null;
        boolean z5 = !z;
        ImageRequest imageRequest = new ImageRequest(url);
        imageRequest.setSize(i, i2);
        imageRequest.setFadeIn(z5);
        imageRequest.setFetchOnly(z4);
        if (!isResized) {
            imageRequest.addImageTransformation(new DensitySizeTransformation(i, i2));
        }
        if (z2) {
            imageRequest.addImageTransformation(new CircleTransformation());
        }
        if (z3) {
            imageRequest.addImageTransformation(new RoundedCornerTransformation());
        }
        if (!z4) {
            imageRequest.setImageView(networkImageView, drawable);
            imageRequest.setImageCallback(new ImageCallback(networkImageView) {
                final /* synthetic */ NetworkImageView val$target;

                {
                    this.val$target = r1;
                }

                public void onSuccess() {
                    this.val$target.setImageLoaded();
                    this.val$target.enableZoomingIfZoomable();
                }

                public void onError() {
                    this.val$target.setImageLoadFailed();
                }
            });
        } else if (obj != null) {
            imageRequest.setTag(obj);
        }
        return imageRequest;
    }

    /* access modifiers changed from: private */
    public void setImageLoadFailed() {
        if (this.mCallBack != null) {
            this.mCallBack.onImageLoadFailed();
            this.mCallBack = null;
        }
    }

    /* access modifiers changed from: private */
    public void setImageLoaded() {
        this.mImageLoaded = true;
        if (this.mCallBack != null) {
            this.mCallBack.onImageLoaded();
            this.mCallBack = null;
        }
    }

    /* access modifiers changed from: private */
    public void enableZoomingIfZoomable() {
        if (this.mZoomable) {
            enableZooming();
        }
    }

    public boolean onPreDraw() {
        if (!getViewTreeObserver().isAlive()) {
            return true;
        }
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return true;
        }
        this.mSizeInvalidated = false;
        if (this.mDeferredPlaceholderAction != null) {
            this.mDeferredPlaceholderAction.run();
        }
        if (this.mDeferredRequestAction != null) {
            this.mDeferredRequestAction.run();
        }
        return true;
    }
}
