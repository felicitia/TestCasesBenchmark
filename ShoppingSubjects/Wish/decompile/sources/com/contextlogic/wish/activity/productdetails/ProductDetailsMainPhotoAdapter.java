package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.api.model.WishProductVideoInfo.VideoLength;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.image.NetworkImageView.NetworkImageViewCallback;
import com.contextlogic.wish.ui.image.NetworkImageView.ResizeType;
import com.contextlogic.wish.ui.slideshow.ImageSlideshowView;
import com.contextlogic.wish.util.VideoUtil;
import com.contextlogic.wish.video.VideoPlayerView;
import com.crashlytics.android.Crashlytics;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductDetailsMainPhotoAdapter extends PagerAdapter {
    /* access modifiers changed from: private */
    public PhotoAdapterCallback mCallback;
    private Context mContext;
    private int mCurrentPosition = 0;
    private ProductDetailsFragment mFragment;
    private ArrayList<NetworkImageView> mImageViews = new ArrayList<>();
    private ArrayList<WishProductExtraImage> mMediaSources = new ArrayList<>();
    private int mPhotoCount = 0;
    private WishProduct mProduct;
    private ArrayList<ImageSlideshowView> mSlideshowViews = new ArrayList<>();
    private int mVideoCount = 0;
    private SimpleExoPlayer mVideoPlayer = null;
    private SparseArray<VideoPlayerView> mVideoPlayersViewMap = new SparseArray<>();

    public interface PhotoAdapterCallback {
        void onMainPhotoImageLoaded();

        void showExtraPhotosImageViewer(int i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ProductDetailsMainPhotoAdapter(ProductDetailsFragment productDetailsFragment, PhotoAdapterCallback photoAdapterCallback, WishProduct wishProduct) {
        this.mFragment = productDetailsFragment;
        this.mContext = productDetailsFragment.getContext();
        this.mCallback = photoAdapterCallback;
        this.mProduct = wishProduct;
        updateImages(wishProduct);
    }

    public void updateImages(WishProduct wishProduct) {
        int i;
        this.mMediaSources.clear();
        this.mMediaSources.add(new WishProductExtraImage(wishProduct.getImage()));
        this.mMediaSources.addAll(wishProduct.getExtraPhotos());
        if (wishProduct.getVideoInfo() != null) {
            WishProductExtraImage wishProductExtraImage = new WishProductExtraImage(wishProduct.getVideoInfo());
            i = 2;
            if (this.mMediaSources.size() < 2) {
                this.mMediaSources.add(wishProductExtraImage);
            } else {
                this.mMediaSources.add(1, wishProductExtraImage);
            }
        } else {
            i = 1;
        }
        if (wishProduct.getSlideshow() != null) {
            WishProductExtraImage wishProductExtraImage2 = new WishProductExtraImage(wishProduct.getSlideshow());
            if (this.mMediaSources.size() < i + 1) {
                this.mMediaSources.add(wishProductExtraImage2);
            } else {
                this.mMediaSources.add(i, wishProductExtraImage2);
            }
        }
        for (int i2 = 0; i2 < this.mMediaSources.size(); i2++) {
            WishProductExtraImage wishProductExtraImage3 = (WishProductExtraImage) this.mMediaSources.get(i2);
            if (wishProductExtraImage3.getSourceType() == SourceType.Video) {
                this.mVideoCount++;
            } else if (wishProductExtraImage3.getSourceType() == SourceType.Image) {
                this.mPhotoCount++;
            }
        }
    }

    private void prepareVideoPlayer(String str) {
        if (this.mVideoPlayer == null) {
            this.mVideoPlayer = VideoUtil.createLoopingMP4Player(this.mContext, str);
            this.mVideoPlayer.setPlayWhenReady(true);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mMediaSources.get(i);
        if (wishProductExtraImage != null && wishProductExtraImage.getSourceType() == SourceType.Video) {
            if (this.mVideoPlayersViewMap.get(i) == null) {
                VideoPlayerView videoPlayerView = new VideoPlayerView(this.mContext);
                videoPlayerView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.black));
                videoPlayerView.setLayoutParams(new LayoutParams(-1, -1));
                videoPlayerView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ProductDetailsMainPhotoAdapter.this.mCallback.showExtraPhotosImageViewer(i);
                    }
                });
                this.mVideoPlayersViewMap.put(i, videoPlayerView);
            }
            if (i == 0) {
                this.mCallback.onMainPhotoImageLoaded();
            }
            viewGroup.addView((View) this.mVideoPlayersViewMap.get(i));
            return this.mVideoPlayersViewMap.get(i);
        } else if (wishProductExtraImage == null || wishProductExtraImage.getSourceType() != SourceType.Slideshow) {
            NetworkImageView networkImageView = new NetworkImageView(this.mContext);
            networkImageView.setScaleType(ScaleType.FIT_CENTER);
            networkImageView.setLayoutParams(new LayoutParams(-1, -1));
            if (i != 0) {
                networkImageView.setUseDynamicScaling(true);
            }
            WishImage wishImage = null;
            if (wishProductExtraImage == null || wishProductExtraImage.getImage() == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Main photo image is null, productId=");
                sb.append(this.mProduct.getProductId());
                sb.append(", position=");
                sb.append(i);
                Crashlytics.log(sb.toString());
            } else {
                wishImage = new WishImage(wishProductExtraImage.getImage().getUrlString(ImageSize.LARGE));
            }
            setImageWithCache(i, networkImageView, wishImage);
            networkImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsMainPhotoAdapter.this.mCallback.showExtraPhotosImageViewer(i);
                }
            });
            viewGroup.addView(networkImageView);
            this.mImageViews.add(networkImageView);
            return networkImageView;
        } else {
            ImageSlideshowView imageSlideshowView = new ImageSlideshowView(this.mContext);
            imageSlideshowView.setLayoutParams(new LayoutParams(-1, -1));
            imageSlideshowView.setSlideshow(wishProductExtraImage.getSlideshow());
            imageSlideshowView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsMainPhotoAdapter.this.mCallback.showExtraPhotosImageViewer(i);
                }
            });
            if (i == 0) {
                this.mCallback.onMainPhotoImageLoaded();
            }
            viewGroup.addView(imageSlideshowView);
            this.mSlideshowViews.add(imageSlideshowView);
            return imageSlideshowView;
        }
    }

    private void setImageWithCache(int i, NetworkImageView networkImageView, WishImage wishImage) {
        if (i != 0 || this.mFragment.getTransitionImageUrl() == null) {
            networkImageView.setPlaceholderSpinner(this.mContext.getResources().getColor(R.color.main_primary));
            networkImageView.setImage(wishImage, 1);
            return;
        }
        networkImageView.setImageUrl(this.mFragment.getTransitionImageUrl(), ResizeType.NONE, new NetworkImageViewCallback() {
            public void onImageLoaded() {
                ProductDetailsMainPhotoAdapter.this.mCallback.onMainPhotoImageLoaded();
            }

            public void onImageLoadFailed() {
                ProductDetailsMainPhotoAdapter.this.mCallback.onMainPhotoImageLoaded();
            }
        });
    }

    public void releaseImages() {
        if (this.mVideoPlayersViewMap != null) {
            for (int i = 0; i < this.mVideoPlayersViewMap.size(); i++) {
                if (this.mVideoPlayersViewMap.valueAt(i) != null) {
                    ((VideoPlayerView) this.mVideoPlayersViewMap.valueAt(i)).setPlayer(null);
                }
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.release();
            this.mVideoPlayer = null;
        }
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            ((NetworkImageView) it.next()).releaseImages();
        }
        Iterator it2 = this.mSlideshowViews.iterator();
        while (it2.hasNext()) {
            ((ImageSlideshowView) it2.next()).releaseImages();
        }
    }

    public void restoreImages() {
        String str;
        if (!(this.mVideoPlayersViewMap == null || this.mMediaSources == null || this.mMediaSources.size() <= this.mCurrentPosition || this.mVideoPlayersViewMap.get(this.mCurrentPosition) == null)) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mMediaSources.get(this.mCurrentPosition);
            if (wishProductExtraImage.isUgc()) {
                str = wishProductExtraImage.getVideoInfo().getUrlString(null);
            } else {
                str = wishProductExtraImage.getVideoInfo().getUrlString(VideoLength.SHORT);
            }
            prepareVideoPlayer(str);
            if (this.mVideoPlayersViewMap.get(this.mCurrentPosition) != null) {
                ((VideoPlayerView) this.mVideoPlayersViewMap.get(this.mCurrentPosition)).setPlayer(this.mVideoPlayer);
            }
        }
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            ((NetworkImageView) it.next()).restoreImages();
        }
        Iterator it2 = this.mSlideshowViews.iterator();
        while (it2.hasNext()) {
            ((ImageSlideshowView) it2.next()).restoreImages();
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if ((obj instanceof VideoPlayerView) && this.mVideoPlayersViewMap != null) {
            VideoPlayerView videoPlayerView = (VideoPlayerView) obj;
            videoPlayerView.setPlayer(null);
            this.mVideoPlayersViewMap.remove(i);
            viewGroup.removeView(videoPlayerView);
        } else if (obj instanceof ImageSlideshowView) {
            ImageSlideshowView imageSlideshowView = (ImageSlideshowView) obj;
            imageSlideshowView.releaseImages();
            this.mSlideshowViews.remove(imageSlideshowView);
            viewGroup.removeView(imageSlideshowView);
        } else if (obj instanceof NetworkImageView) {
            NetworkImageView networkImageView = (NetworkImageView) obj;
            networkImageView.releaseImages();
            this.mImageViews.remove(networkImageView);
            viewGroup.removeView(networkImageView);
        }
    }

    public void cleanup() {
        if (this.mVideoPlayersViewMap != null) {
            int size = this.mVideoPlayersViewMap.size();
            for (int i = 0; i < size; i++) {
                int keyAt = this.mVideoPlayersViewMap.keyAt(i);
                if (this.mVideoPlayersViewMap.get(keyAt) != null) {
                    ((VideoPlayerView) this.mVideoPlayersViewMap.get(keyAt)).setPlayer(null);
                    this.mVideoPlayersViewMap.remove(keyAt);
                }
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.release();
        }
        Iterator it = this.mSlideshowViews.iterator();
        while (it.hasNext()) {
            ((ImageSlideshowView) it.next()).recycle();
        }
    }

    public void handlePageSelected(int i) {
        this.mCurrentPosition = i;
        for (int i2 = 0; i2 < this.mMediaSources.size(); i2++) {
            if (!(this.mVideoPlayersViewMap.get(i2) == null || i2 == i)) {
                ((VideoPlayerView) this.mVideoPlayersViewMap.get(i2)).setPlayer(null);
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.setPlayWhenReady(false);
            this.mVideoPlayer.release();
            this.mVideoPlayer = null;
        }
        if (this.mMediaSources != null) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mMediaSources.get(i);
            if (wishProductExtraImage != null && wishProductExtraImage.getSourceType() == SourceType.Video && wishProductExtraImage.getVideoInfo() != null) {
                String urlString = wishProductExtraImage.getVideoInfo().getUrlString(null);
                if (!wishProductExtraImage.isUgc()) {
                    urlString = wishProductExtraImage.getVideoInfo().getUrlString(VideoLength.LONG);
                }
                prepareVideoPlayer(urlString);
                if (this.mVideoPlayersViewMap.get(i) != null) {
                    ((VideoPlayerView) this.mVideoPlayersViewMap.get(i)).showProgressBar();
                    ((VideoPlayerView) this.mVideoPlayersViewMap.get(i)).setPlayer(this.mVideoPlayer);
                    this.mVideoPlayer.setPlayWhenReady(true);
                }
            }
        }
    }

    public int getCount() {
        if (this.mMediaSources == null) {
            return 0;
        }
        return this.mMediaSources.size();
    }

    public int getPhotoCount() {
        return this.mPhotoCount;
    }

    public int getVideoCount() {
        return this.mVideoCount;
    }

    public WishProductExtraImage getMedia(int i) {
        return (WishProductExtraImage) this.mMediaSources.get(i);
    }
}
