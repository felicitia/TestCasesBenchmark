package com.contextlogic.wish.activity.imageviewer;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.api.model.WishProductVideoInfo.VideoLength;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.slideshow.FullScreenSquareImageSlideshowView;
import com.contextlogic.wish.util.VideoUtil;
import com.contextlogic.wish.video.FullScreenVideoPlayerView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageViewerAdapter extends PagerAdapter {
    private ImageViewerActivity mBaseActivity;
    private int mCurrentPosition;
    private ImageViewerFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<NetworkImageView> mImageViews = new ArrayList<>();
    private ArrayList<FullScreenSquareImageSlideshowView> mSlideshowViews = new ArrayList<>();
    private SimpleExoPlayer mVideoPlayer = null;
    private SparseArray<FullScreenVideoPlayerView> mVideoPlayersViewMap = new SparseArray<>();
    private String mVideoUrl;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ImageViewerAdapter(ImageViewerActivity imageViewerActivity, ImageViewerFragment imageViewerFragment, int i) {
        this.mBaseActivity = imageViewerActivity;
        this.mFragment = imageViewerFragment;
        this.mCurrentPosition = i;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    private void prepareVideoPlayer(String str) {
        if (this.mVideoPlayer == null) {
            this.mVideoPlayer = VideoUtil.createLoopingMP4Player(this.mBaseActivity, str, true);
            this.mVideoPlayer.setPlayWhenReady(false);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.mFragment.getMediaSources() != null) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mFragment.getMediaSources().get(i);
            if (wishProductExtraImage != null && wishProductExtraImage.getSourceType() == SourceType.Video && wishProductExtraImage.getVideoInfo() != null) {
                if (this.mVideoPlayersViewMap.get(i) == null) {
                    FullScreenVideoPlayerView fullScreenVideoPlayerView = new FullScreenVideoPlayerView(this.mBaseActivity);
                    fullScreenVideoPlayerView.setVideoInfo(wishProductExtraImage.getVideoInfo());
                    fullScreenVideoPlayerView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.photo_video_viewer_background));
                    fullScreenVideoPlayerView.setLayoutParams(new LayoutParams(-1, -2));
                    this.mVideoPlayersViewMap.put(i, fullScreenVideoPlayerView);
                }
                if (this.mVideoPlayer != null && i == this.mCurrentPosition) {
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i)).setPlayer(this.mVideoPlayer);
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i)).showController();
                    this.mVideoPlayer.setPlayWhenReady(true);
                }
                viewGroup.addView((View) this.mVideoPlayersViewMap.get(i));
                return this.mVideoPlayersViewMap.get(i);
            } else if (wishProductExtraImage != null && wishProductExtraImage.getSourceType() == SourceType.Slideshow) {
                FullScreenSquareImageSlideshowView fullScreenSquareImageSlideshowView = new FullScreenSquareImageSlideshowView(this.mBaseActivity);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                layoutParams.gravity = 17;
                fullScreenSquareImageSlideshowView.setLayoutParams(layoutParams);
                fullScreenSquareImageSlideshowView.setSlideshow(wishProductExtraImage.getSlideshow());
                viewGroup.addView(fullScreenSquareImageSlideshowView);
                this.mSlideshowViews.add(fullScreenSquareImageSlideshowView);
                return fullScreenSquareImageSlideshowView;
            }
        }
        NetworkImageView networkImageView = new NetworkImageView(this.mBaseActivity);
        networkImageView.setLayoutParams(new LayoutParams(-1, -1));
        networkImageView.setImagePrefetcher(this.mImagePrefetcher);
        networkImageView.setUseDynamicScaling(true);
        networkImageView.setZoomable(true);
        networkImageView.setPlaceholderSpinner(WishApplication.getInstance().getResources().getColor(R.color.white));
        if (this.mFragment.getImageUrl() != null) {
            networkImageView.setImage(new WishImage(this.mFragment.getImageUrl()));
        } else if (this.mFragment.getMediaSources() != null) {
            networkImageView.setImage(((WishProductExtraImage) this.mFragment.getMediaSources().get(i)).getImage());
        }
        viewGroup.addView(networkImageView);
        this.mImageViews.add(networkImageView);
        return networkImageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if ((obj instanceof FullScreenVideoPlayerView) && this.mVideoPlayersViewMap != null) {
            FullScreenVideoPlayerView fullScreenVideoPlayerView = (FullScreenVideoPlayerView) obj;
            fullScreenVideoPlayerView.setPlayer(null);
            fullScreenVideoPlayerView.destroy();
            this.mVideoPlayersViewMap.remove(i);
            viewGroup.removeView(fullScreenVideoPlayerView);
        } else if (obj instanceof FullScreenSquareImageSlideshowView) {
            FullScreenSquareImageSlideshowView fullScreenSquareImageSlideshowView = (FullScreenSquareImageSlideshowView) obj;
            fullScreenSquareImageSlideshowView.releaseImages();
            this.mSlideshowViews.remove(fullScreenSquareImageSlideshowView);
            viewGroup.removeView(fullScreenSquareImageSlideshowView);
        } else if (obj instanceof NetworkImageView) {
            NetworkImageView networkImageView = (NetworkImageView) obj;
            networkImageView.recycle();
            this.mImageViews.remove(networkImageView);
            viewGroup.removeView(networkImageView);
        }
    }

    public void restoreImages() {
        String str;
        if (!(this.mVideoPlayersViewMap == null || this.mFragment.getMediaSources() == null || this.mFragment.getMediaSources().size() <= this.mCurrentPosition || this.mVideoPlayersViewMap.get(this.mCurrentPosition) == null)) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mFragment.getMediaSources().get(this.mCurrentPosition);
            if (wishProductExtraImage.isUgc()) {
                str = wishProductExtraImage.getVideoInfo().getUrlString(null);
            } else {
                str = wishProductExtraImage.getVideoInfo().getUrlString(VideoLength.SHORT);
            }
            prepareVideoPlayer(str);
            if (this.mVideoPlayersViewMap.get(this.mCurrentPosition) != null) {
                this.mVideoPlayer.setPlayWhenReady(true);
                ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(this.mCurrentPosition)).setPlayer(this.mVideoPlayer);
            }
        }
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            ((NetworkImageView) it.next()).restoreImages();
        }
        Iterator it2 = this.mSlideshowViews.iterator();
        while (it2.hasNext()) {
            ((FullScreenSquareImageSlideshowView) it2.next()).restoreImages();
        }
    }

    public void releaseImages() {
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            ((NetworkImageView) it.next()).releaseImages();
        }
        if (this.mVideoPlayersViewMap != null) {
            for (int i = 0; i < this.mVideoPlayersViewMap.size(); i++) {
                if (this.mVideoPlayersViewMap.valueAt(i) != null) {
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.valueAt(i)).setPlayer(null);
                }
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.release();
            this.mVideoPlayer = null;
        }
        Iterator it2 = this.mSlideshowViews.iterator();
        while (it2.hasNext()) {
            ((FullScreenSquareImageSlideshowView) it2.next()).releaseImages();
        }
    }

    public void handlePageSelected(int i) {
        this.mCurrentPosition = i;
        if (this.mFragment.getMediaSources() != null) {
            for (int i2 = 0; i2 < this.mFragment.getMediaSources().size(); i2++) {
                if (!(this.mVideoPlayersViewMap.get(i2) == null || i2 == i)) {
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i2)).setPlayer(null);
                }
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.setPlayWhenReady(false);
            this.mVideoPlayer.release();
            this.mVideoPlayer = null;
        }
        if (this.mFragment.getMediaSources() != null) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mFragment.getMediaSources().get(i);
            if (wishProductExtraImage != null && wishProductExtraImage.getSourceType() == SourceType.Video && wishProductExtraImage.getVideoInfo() != null) {
                String urlString = wishProductExtraImage.getVideoInfo().getUrlString(null);
                if (!wishProductExtraImage.isUgc()) {
                    this.mVideoUrl = wishProductExtraImage.getVideoInfo().getUrlString(VideoLength.LONG);
                    urlString = this.mVideoUrl;
                }
                prepareVideoPlayer(urlString);
                if (this.mVideoPlayersViewMap.get(i) != null) {
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i)).setPlayer(this.mVideoPlayer);
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i)).showController();
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(i)).showProgressBar();
                    this.mVideoPlayer.setPlayWhenReady(true);
                }
            }
        }
    }

    public void cleanup() {
        if (this.mVideoPlayersViewMap != null) {
            int size = this.mVideoPlayersViewMap.size();
            for (int i = 0; i < size; i++) {
                int keyAt = this.mVideoPlayersViewMap.keyAt(i);
                if (this.mVideoPlayersViewMap.get(keyAt) != null) {
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(keyAt)).setPlayer(null);
                    ((FullScreenVideoPlayerView) this.mVideoPlayersViewMap.get(keyAt)).destroy();
                    this.mVideoPlayersViewMap.remove(keyAt);
                }
            }
        }
        if (this.mVideoPlayer != null) {
            this.mVideoPlayer.release();
        }
        Iterator it = this.mSlideshowViews.iterator();
        while (it.hasNext()) {
            ((FullScreenSquareImageSlideshowView) it.next()).recycle();
        }
    }

    public int getCount() {
        if (this.mFragment.getImageUrl() != null) {
            return 1;
        }
        if (this.mFragment.getMediaSources() != null) {
            return this.mFragment.getMediaSources().size();
        }
        return 0;
    }
}
