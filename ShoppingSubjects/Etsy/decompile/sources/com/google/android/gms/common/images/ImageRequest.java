package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.common.images.internal.CrossFadingDrawable;
import com.google.android.gms.common.images.internal.ImageUtils;
import com.google.android.gms.common.images.internal.LoadingImageView;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache.PostProcessedResource;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

public abstract class ImageRequest {
    protected int mLoadingPlaceholderResId = 0;
    protected int mNoDataPlaceholderResId = 0;
    protected OnImageLoadedListener mOnImageLoadedListener;
    protected int mPostProcessingFlags;
    protected boolean mUseNewDrawable = false;
    final zza zzpk;
    private boolean zzpl = true;
    private boolean zzpm = false;
    private boolean zzpn = true;

    public static final class ImageViewImageRequest extends ImageRequest {
        private WeakReference<ImageView> zzpo;

        public ImageViewImageRequest(ImageView imageView, int i) {
            super(null, i);
            Asserts.checkNotNull(imageView);
            this.zzpo = new WeakReference<>(imageView);
        }

        public ImageViewImageRequest(ImageView imageView, Uri uri) {
            super(uri, 0);
            Asserts.checkNotNull(imageView);
            this.zzpo = new WeakReference<>(imageView);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof ImageViewImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.zzpo.get();
            ImageView imageView2 = (ImageView) ((ImageViewImageRequest) obj).zzpo.get();
            return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
        }

        public final int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public final void loadImage(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.zzpo.get();
            if (imageView != null) {
                int i = 0;
                boolean z4 = !z2 && !z3;
                if (z4 && (imageView instanceof LoadingImageView)) {
                    int loadedNoDataPlaceholderResId = ((LoadingImageView) imageView).getLoadedNoDataPlaceholderResId();
                    if (this.mNoDataPlaceholderResId != 0 && loadedNoDataPlaceholderResId == this.mNoDataPlaceholderResId) {
                        return;
                    }
                }
                boolean shouldCrossFade = shouldCrossFade(z, z2);
                if (this.mUseNewDrawable && drawable != null) {
                    drawable = drawable.getConstantState().newDrawable();
                }
                if (shouldCrossFade) {
                    drawable = createTransitionDrawable(imageView.getDrawable(), drawable);
                }
                imageView.setImageDrawable(drawable);
                if (imageView instanceof LoadingImageView) {
                    LoadingImageView loadingImageView = (LoadingImageView) imageView;
                    loadingImageView.setLoadedUri(z3 ? this.zzpk.uri : null);
                    if (z4) {
                        i = this.mNoDataPlaceholderResId;
                    }
                    loadingImageView.setLoadedNoDataPlaceholderResId(i);
                }
                if (shouldCrossFade) {
                    ((CrossFadingDrawable) drawable).startTransition(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                }
            }
        }
    }

    public static final class ListenerImageRequest extends ImageRequest {
        private WeakReference<OnImageLoadedListener> zzpp;

        public ListenerImageRequest(OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            Asserts.checkNotNull(onImageLoadedListener);
            this.zzpp = new WeakReference<>(onImageLoadedListener);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof ListenerImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ListenerImageRequest listenerImageRequest = (ListenerImageRequest) obj;
            OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.zzpp.get();
            OnImageLoadedListener onImageLoadedListener2 = (OnImageLoadedListener) listenerImageRequest.zzpp.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && Objects.equal(onImageLoadedListener2, onImageLoadedListener) && Objects.equal(listenerImageRequest.zzpk, this.zzpk);
        }

        public final int hashCode() {
            return Objects.hashCode(this.zzpk);
        }

        /* access modifiers changed from: protected */
        public final void loadImage(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.zzpp.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.zzpk.uri, drawable, z3);
                }
            }
        }
    }

    public static final class PostProcessingFlags {
        public static final int CIRCLE_CROP = 1;
    }

    public static final class TextViewImageRequest extends ImageRequest {
        public static final int POSITION_BOTTOM = 3;
        public static final int POSITION_END = 2;
        public static final int POSITION_START = 0;
        public static final int POSITION_TOP = 1;
        private WeakReference<TextView> zzpq;
        private int zzpr = -1;

        public TextViewImageRequest(TextView textView, int i, int i2) {
            super(null, i2);
            Asserts.checkNotNull(textView);
            boolean z = true;
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                z = false;
            }
            StringBuilder sb = new StringBuilder(29);
            sb.append("Invalid position: ");
            sb.append(i);
            Asserts.checkState(z, sb.toString());
            this.zzpq = new WeakReference<>(textView);
            this.zzpr = i;
        }

        public TextViewImageRequest(TextView textView, int i, Uri uri) {
            super(uri, 0);
            Asserts.checkNotNull(textView);
            boolean z = true;
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                z = false;
            }
            StringBuilder sb = new StringBuilder(29);
            sb.append("Invalid position: ");
            sb.append(i);
            Asserts.checkState(z, sb.toString());
            this.zzpq = new WeakReference<>(textView);
            this.zzpr = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof TextViewImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            TextViewImageRequest textViewImageRequest = (TextViewImageRequest) obj;
            TextView textView = (TextView) this.zzpq.get();
            TextView textView2 = (TextView) textViewImageRequest.zzpq.get();
            return textView2 != null && textView != null && Objects.equal(textView2, textView) && Objects.equal(Integer.valueOf(textViewImageRequest.zzpr), Integer.valueOf(this.zzpr));
        }

        public final int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.zzpr));
        }

        /* access modifiers changed from: protected */
        public final void loadImage(Drawable drawable, boolean z, boolean z2, boolean z3) {
            TextView textView = (TextView) this.zzpq.get();
            if (textView != null) {
                int i = this.zzpr;
                boolean shouldCrossFade = shouldCrossFade(z, z2);
                Drawable[] compoundDrawablesRelative = PlatformVersion.isAtLeastJellyBeanMR1() ? textView.getCompoundDrawablesRelative() : textView.getCompoundDrawables();
                Drawable drawable2 = compoundDrawablesRelative[i];
                if (shouldCrossFade) {
                    drawable = createTransitionDrawable(drawable2, drawable);
                }
                Drawable drawable3 = i == 0 ? drawable : compoundDrawablesRelative[0];
                Drawable drawable4 = i == 1 ? drawable : compoundDrawablesRelative[1];
                Drawable drawable5 = i == 2 ? drawable : compoundDrawablesRelative[2];
                Drawable drawable6 = i == 3 ? drawable : compoundDrawablesRelative[3];
                if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable3, drawable4, drawable5, drawable6);
                } else {
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable3, drawable4, drawable5, drawable6);
                }
                if (shouldCrossFade) {
                    ((CrossFadingDrawable) drawable).startTransition(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                }
            }
        }
    }

    static final class zza {
        public final Uri uri;

        public zza(Uri uri2) {
            this.uri = uri2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return Objects.equal(((zza) obj).uri, this.uri);
        }

        public final int hashCode() {
            return Objects.hashCode(this.uri);
        }
    }

    public ImageRequest(Uri uri, int i) {
        this.zzpk = new zza(uri);
        this.mNoDataPlaceholderResId = i;
    }

    private final Drawable zza(Context context, PostProcessedResourceCache postProcessedResourceCache, int i) {
        Resources resources = context.getResources();
        if (this.mPostProcessingFlags <= 0) {
            return resources.getDrawable(i);
        }
        PostProcessedResource postProcessedResource = new PostProcessedResource(i, this.mPostProcessingFlags);
        Drawable drawable = (Drawable) postProcessedResourceCache.get(postProcessedResource);
        if (drawable == null) {
            Drawable drawable2 = resources.getDrawable(i);
            drawable = (this.mPostProcessingFlags & 1) != 0 ? frameDrawableInCircle(resources, drawable2) : drawable2;
            postProcessedResourceCache.put(postProcessedResource, drawable);
        }
        return drawable;
    }

    /* access modifiers changed from: protected */
    public CrossFadingDrawable createTransitionDrawable(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof CrossFadingDrawable) {
            drawable = ((CrossFadingDrawable) drawable).getEndDrawable();
        }
        return new CrossFadingDrawable(drawable, drawable2);
    }

    /* access modifiers changed from: protected */
    public Drawable frameDrawableInCircle(Resources resources, Drawable drawable) {
        return ImageUtils.frameDrawableInCircle(resources, drawable);
    }

    /* access modifiers changed from: protected */
    public abstract void loadImage(Drawable drawable, boolean z, boolean z2, boolean z3);

    public void setCrossFadeAlwaysEnabled(boolean z) {
        this.zzpm = z;
        if (z) {
            setCrossFadeEnabled(true);
        }
    }

    public void setCrossFadeEnabled(boolean z) {
        this.zzpl = z;
    }

    public void setLoadingPlaceholder(int i) {
        this.mLoadingPlaceholderResId = i;
    }

    public void setLoadingPlaceholderEnabled(boolean z) {
        this.zzpn = z;
    }

    public void setNoDataPlaceholder(int i) {
        this.mNoDataPlaceholderResId = i;
    }

    public void setOnImageLoadedListener(OnImageLoadedListener onImageLoadedListener) {
        this.mOnImageLoadedListener = onImageLoadedListener;
    }

    public void setPostProcessingFlags(int i) {
        this.mPostProcessingFlags = i;
    }

    public void setUseNewDrawable(boolean z) {
        this.mUseNewDrawable = z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldCrossFade(boolean z, boolean z2) {
        return this.zzpl && !z2 && (!z || this.zzpm);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        if ((this.mPostProcessingFlags & 1) != 0) {
            bitmap = ImageUtils.frameBitmapInCircle(bitmap);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, bitmapDrawable, true);
        }
        loadImage(bitmapDrawable, z, false, true);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Context context, PostProcessedResourceCache postProcessedResourceCache) {
        if (this.zzpn) {
            Drawable drawable = null;
            if (this.mLoadingPlaceholderResId != 0) {
                drawable = zza(context, postProcessedResourceCache, this.mLoadingPlaceholderResId);
            }
            loadImage(drawable, false, true, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Context context, PostProcessedResourceCache postProcessedResourceCache, boolean z) {
        Drawable zza2 = this.mNoDataPlaceholderResId != 0 ? zza(context, postProcessedResourceCache, this.mNoDataPlaceholderResId) : null;
        if (this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, zza2, false);
        }
        loadImage(zza2, z, false, false);
    }
}
