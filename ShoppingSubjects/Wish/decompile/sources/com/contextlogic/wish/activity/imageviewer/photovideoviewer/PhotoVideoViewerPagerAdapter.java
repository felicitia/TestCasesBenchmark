package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerViewItem.ImageClickListener;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.Iterator;

public class PhotoVideoViewerPagerAdapter extends PagerAdapter {
    private SparseArray<WishProductExtraImage> mAllExtraImages;
    private Context mContext;
    private SparseArray<WishProductExtraImage> mExtraImages;
    private SparseArray<WishProductExtraImage> mExtraVideos;
    private ImageClickListener mImageClickListener;
    /* access modifiers changed from: private */
    public OnViewVisibleListener mOnViewVisibleListener;
    private ArrayList<PhotoVideoViewerPagerView> mPagerViews;
    private boolean mTrackedAllTabImpression = false;
    private boolean mTrackedPhotoTabImpression = false;
    private boolean mTrackedVideoTabImpression = false;

    public interface OnViewVisibleListener {
        void onViewVisible(int i, int i2, View view);
    }

    public int getCount() {
        return 3;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PhotoVideoViewerPagerAdapter(Context context, ImageClickListener imageClickListener) {
        this.mContext = context;
        this.mAllExtraImages = new SparseArray<>();
        this.mExtraImages = new SparseArray<>();
        this.mExtraVideos = new SparseArray<>();
        this.mPagerViews = new ArrayList<>();
        this.mImageClickListener = imageClickListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.ViewGroup r5, final int r6) {
        /*
            r4 = this;
            com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerView r0 = new com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerView
            android.content.Context r1 = r4.mContext
            r0.<init>(r1)
            r4.fillImageListsIfNecessary()
            r1 = 1
            if (r6 != r1) goto L_0x0013
            android.util.SparseArray<com.contextlogic.wish.api.model.WishProductExtraImage> r1 = r4.mExtraImages
            r0.setExtraImages(r1)
            goto L_0x0021
        L_0x0013:
            r2 = 2
            if (r6 != r2) goto L_0x001c
            android.util.SparseArray<com.contextlogic.wish.api.model.WishProductExtraImage> r2 = r4.mExtraVideos
            r0.setExtraImages(r2)
            goto L_0x0022
        L_0x001c:
            android.util.SparseArray<com.contextlogic.wish.api.model.WishProductExtraImage> r1 = r4.mAllExtraImages
            r0.setExtraImages(r1)
        L_0x0021:
            r1 = 0
        L_0x0022:
            java.util.ArrayList<com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerView> r2 = r4.mPagerViews
            boolean r2 = r2.contains(r0)
            if (r2 != 0) goto L_0x002f
            java.util.ArrayList<com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerView> r2 = r4.mPagerViews
            r2.add(r0)
        L_0x002f:
            com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerViewItem$ImageClickListener r2 = r4.mImageClickListener
            com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerAdapter$1 r3 = new com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerAdapter$1
            r3.<init>(r6)
            r0.setup(r2, r1, r3)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0.setTag(r6)
            android.view.ViewGroup$LayoutParams r6 = new android.view.ViewGroup$LayoutParams
            r1 = -1
            r6.<init>(r1, r1)
            r5.addView(r0, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerAdapter.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    public void trackPageChange(int i, boolean z, String str) {
        if (i == 1) {
            if (!this.mTrackedPhotoTabImpression) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PHOTO_VIDEO_VIEWER_PHOTO_TAB, str);
                this.mTrackedPhotoTabImpression = true;
            }
            if (z) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_PHOTOS_TAB, str);
            }
        } else if (i == 2) {
            if (!this.mTrackedVideoTabImpression) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PHOTO_VIDEO_VIEWER_VIDEO_TAB, str);
                this.mTrackedVideoTabImpression = true;
            }
            if (z) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_VIDEOS_TAB, str);
            }
        } else {
            if (!this.mTrackedAllTabImpression) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PHOTO_VIDEO_VIEWER_ALL_TAB, str);
                this.mTrackedAllTabImpression = true;
            }
            if (z) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_ALL_TAB, str);
            }
        }
    }

    public void setAllExtraImages(ArrayList<WishProductExtraImage> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                this.mAllExtraImages.put(i, arrayList.get(i));
            }
            fillImageListsIfNecessary();
        }
    }

    private void fillImageListsIfNecessary() {
        if (this.mAllExtraImages.size() > 0 && this.mExtraImages.size() + this.mExtraVideos.size() != this.mAllExtraImages.size()) {
            for (int i = 0; i < this.mAllExtraImages.size(); i++) {
                int keyAt = this.mAllExtraImages.keyAt(i);
                WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mAllExtraImages.get(keyAt);
                if (wishProductExtraImage.getSourceType() == SourceType.Image) {
                    this.mExtraImages.put(keyAt, wishProductExtraImage);
                } else if (((WishProductExtraImage) this.mAllExtraImages.get(keyAt)).getSourceType() == SourceType.Video) {
                    this.mExtraVideos.put(keyAt, wishProductExtraImage);
                }
            }
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.mPagerViews != null) {
            Iterator it = this.mPagerViews.iterator();
            while (it.hasNext()) {
                ((PhotoVideoViewerPagerView) it.next()).releaseImages();
            }
        }
    }

    public String getPageTitle(int i) {
        if (i == 1) {
            return WishApplication.getInstance().getString(R.string.photos);
        }
        if (i == 2) {
            return WishApplication.getInstance().getString(R.string.videos);
        }
        return WishApplication.getInstance().getString(R.string.all);
    }

    public void releaseImages() {
        if (this.mPagerViews != null) {
            Iterator it = this.mPagerViews.iterator();
            while (it.hasNext()) {
                ((PhotoVideoViewerPagerView) it.next()).releaseImages();
            }
        }
    }

    public void restoreImages() {
        if (this.mPagerViews != null) {
            Iterator it = this.mPagerViews.iterator();
            while (it.hasNext()) {
                ((PhotoVideoViewerPagerView) it.next()).restoreImages();
            }
        }
    }

    public void setOnViewVisibleListener(OnViewVisibleListener onViewVisibleListener) {
        this.mOnViewVisibleListener = onViewVisibleListener;
    }

    public int getExtraImagesSize() {
        if (this.mExtraImages == null) {
            return 0;
        }
        return this.mExtraImages.size();
    }

    public int getExtraVideosSize() {
        if (this.mExtraVideos == null) {
            return 0;
        }
        return this.mExtraVideos.size();
    }
}
