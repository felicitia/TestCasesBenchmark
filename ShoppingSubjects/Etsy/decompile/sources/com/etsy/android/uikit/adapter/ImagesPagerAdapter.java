package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.core.img.e;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ImagesPagerAdapter<T extends BaseModelImage> extends PagerAdapter {
    private com.etsy.android.lib.util.b.a fileSupport;
    private boolean isWebp;
    protected Reference<Activity> mActivity;
    private boolean mHasVideo;
    private c mImageBatch;
    protected ArrayList<T> mImages = new ArrayList<>();
    private int mImgHeight;
    private int mImgWidth;
    private boolean mIsVideoEnabled;
    private int mLayoutId;
    /* access modifiers changed from: private */
    public b mOnImageClickListener;

    private static class a extends d {
        private View a;
        private View b;
        private boolean c;

        a(ImageView imageView, View view, View view2, Boolean bool) {
            super(imageView);
            this.a = view;
            this.b = view2;
            this.c = bool.booleanValue();
        }

        public void a(Bitmap bitmap, boolean z) {
            super.a(bitmap, z);
            this.a.setVisibility(8);
            if (this.c && this.b != null) {
                this.b.setVisibility(0);
            }
        }
    }

    public interface b<T> {
        void onImageClick(int i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ImagesPagerAdapter(Activity activity, @NonNull com.etsy.android.lib.logger.b bVar, int i, c cVar, @NonNull com.etsy.android.lib.util.b.a aVar) {
        setContext(activity);
        this.mImageBatch = cVar;
        this.fileSupport = aVar;
        this.mLayoutId = i;
        this.mHasVideo = false;
        this.mIsVideoEnabled = bVar.c().c(com.etsy.android.lib.config.b.bH);
    }

    public void onActivityCreated(Activity activity) {
        setContext(activity);
    }

    public void setContext(Activity activity) {
        this.mActivity = new WeakReference(activity);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return this.mLayoutId;
    }

    public void setImages(List<T> list) {
        this.mImages.clear();
        if (list != null) {
            this.mImages.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setHasVideo(boolean z) {
        this.mHasVideo = z;
    }

    public void setWebp(boolean z) {
        this.isWebp = z;
    }

    public void setImageSizes(int i, int i2) {
        this.mImgWidth = i;
        this.mImgHeight = i2;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mImages.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int layoutId = getLayoutId();
        if (i == 0 && this.mHasVideo) {
            layoutId = k.imageview_loading;
        }
        View inflate = LayoutInflater.from((Context) this.mActivity.get()).inflate(layoutId, viewGroup, false);
        inflate.setLayoutParams(new LayoutParams(this.mImgWidth, this.mImgHeight));
        View findViewById = inflate.findViewById(i.activity_indicator);
        ImageView upViewAndReturnImageView = setUpViewAndReturnImageView(inflate, i);
        if (i == 0 && this.mHasVideo) {
            upViewAndReturnImageView.setScaleType(ScaleType.FIT_CENTER);
        }
        View findViewById2 = inflate.findViewById(i.play_video_icon);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        BaseModelImage baseModelImage = (BaseModelImage) this.mImages.get(i);
        String imageUrl = getImageUrl(baseModelImage);
        if (this.fileSupport.a(imageUrl)) {
            e imageDownloadRequest = getImageDownloadRequest(imageUrl, findViewById, upViewAndReturnImageView, findViewById2, i, baseModelImage);
            imageDownloadRequest.a(this.isWebp);
            this.mImageBatch.a(imageDownloadRequest);
        } else if (i != 0 || !this.mHasVideo) {
            onUnsupportedImageShown(inflate, findViewById, imageUrl);
        } else {
            findViewById.setVisibility(8);
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
        }
        viewGroup.addView(inflate);
        return inflate;
    }

    public void setOnImageClickListener(b bVar) {
        this.mOnImageClickListener = bVar;
    }

    /* access modifiers changed from: protected */
    public ImageView setUpViewAndReturnImageView(View view, final int i) {
        view.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ImagesPagerAdapter.this.mOnImageClickListener != null) {
                    ImagesPagerAdapter.this.mOnImageClickListener.onImageClick(i);
                }
            }
        });
        ImageView imageView = (ImageView) view.findViewById(i.image);
        imageView.setContentDescription(((Activity) this.mActivity.get()).getResources().getString(o.listing_image_content_description));
        return imageView;
    }

    /* access modifiers changed from: protected */
    public e getImageDownloadRequest(String str, View view, ImageView imageView, View view2, int i, T t) {
        e eVar = new e(str, imageView);
        if (this.mImgWidth > 0) {
            eVar.a(this.mImgWidth);
        }
        if (this.mImgHeight > 0) {
            eVar.b(this.mImgHeight);
        }
        eVar.a((d) new a(imageView, view, view2, Boolean.valueOf(this.mIsVideoEnabled && this.mHasVideo && i == 0)));
        eVar.d(t.getImageColor());
        eVar.a(this.isWebp);
        return eVar;
    }

    /* access modifiers changed from: protected */
    public void onUnsupportedImageShown(View view, View view2, String str) {
        view2.setVisibility(8);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public ArrayList<T> getImages() {
        return this.mImages;
    }

    /* access modifiers changed from: protected */
    public String getImageUrl(T t) {
        if (this.mImgWidth > 0) {
            return t.getImageUrlForPixelWidth(this.mImgWidth);
        }
        return t.getImageUrl();
    }
}
