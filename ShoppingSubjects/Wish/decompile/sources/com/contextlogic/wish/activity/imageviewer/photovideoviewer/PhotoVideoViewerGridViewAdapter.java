package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerViewItem.ImageClickListener;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;

public class PhotoVideoViewerGridViewAdapter extends Adapter {
    private final Context mContext;
    private ImageClickListener mImageClickListener;
    private ImageHttpPrefetcher mImagePrefetcher;
    private SparseArray<WishProductExtraImage> mThumbnails = new SparseArray<>();

    public int getColumnCount() {
        return 3;
    }

    public PhotoVideoViewerGridViewAdapter(Context context, ImageClickListener imageClickListener) {
        this.mContext = context;
        this.mImageClickListener = imageClickListener;
    }

    public void setThumbnails(SparseArray<WishProductExtraImage> sparseArray) {
        this.mThumbnails = sparseArray;
        notifyDataSetChanged();
    }

    public int getItemHeight(int i, int i2) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.photo_video_viewer_thumbnail_size);
    }

    public int getItemMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding);
    }

    public int getCount() {
        if (this.mThumbnails == null) {
            return 0;
        }
        return this.mThumbnails.size();
    }

    public WishProductExtraImage getItem(int i) {
        if (getCount() > i) {
            return (WishProductExtraImage) this.mThumbnails.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PhotoVideoViewerPagerViewItem photoVideoViewerPagerViewItem;
        if (view != null) {
            photoVideoViewerPagerViewItem = (PhotoVideoViewerPagerViewItem) view;
        } else {
            photoVideoViewerPagerViewItem = new PhotoVideoViewerPagerViewItem(this.mContext, this.mImageClickListener);
        }
        if (this.mImagePrefetcher != null) {
            photoVideoViewerPagerViewItem.setImagePrefetcher(this.mImagePrefetcher);
        }
        int keyAt = this.mThumbnails.keyAt(i);
        boolean z = ((WishProductExtraImage) this.mThumbnails.get(keyAt)).getSourceType() == SourceType.Video;
        if (((WishProductExtraImage) this.mThumbnails.get(keyAt)).getThumbnail() != null) {
            photoVideoViewerPagerViewItem.setImageThumbnail(((WishProductExtraImage) this.mThumbnails.get(keyAt)).getThumbnail(), keyAt, z);
        } else if (z) {
            photoVideoViewerPagerViewItem.setBitmapThumbnail(((WishProductExtraImage) this.mThumbnails.get(keyAt)).getGeneratedThumbnail(), keyAt);
        }
        return photoVideoViewerPagerViewItem;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }
}
