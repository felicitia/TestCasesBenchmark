package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerViewItem.ImageClickListener;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnViewVisibleListener;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class PhotoVideoViewerPagerView extends LoadingPageView implements ImageRestorable, LoadingPageManager {
    private PhotoVideoViewerGridViewAdapter mAdapter;
    private SparseArray<WishProductExtraImage> mExtraImages;
    private StaggeredGridView mGridView;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ThemedTextView mNoItemsTextView;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.photo_video_viewer_page_view;
    }

    public boolean hasItems() {
        return true;
    }

    public PhotoVideoViewerPagerView(Context context) {
        super(context);
        setLoadingPageManager(this);
    }

    public void setExtraImages(SparseArray<WishProductExtraImage> sparseArray) {
        this.mExtraImages = sparseArray;
    }

    public void setup(ImageClickListener imageClickListener, boolean z, OnViewVisibleListener onViewVisibleListener) {
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mAdapter = new PhotoVideoViewerGridViewAdapter(getContext(), imageClickListener);
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mGridView.setAdapter(this.mAdapter);
        if (this.mExtraImages == null || this.mExtraImages.size() <= 0) {
            this.mNoItemsTextView.setVisibility(0);
            this.mGridView.setVisibility(8);
            if (z) {
                this.mNoItemsTextView.setText(getResources().getString(R.string.photo_video_viewer_no_videos_text));
            } else {
                this.mNoItemsTextView.setText(getResources().getString(R.string.photo_video_viewer_no_images_text));
            }
        } else {
            this.mNoItemsTextView.setVisibility(8);
            this.mAdapter.setThumbnails(this.mExtraImages);
        }
        this.mGridView.setOnViewVisibleListener(onViewVisibleListener);
        markLoadingComplete();
        this.mAdapter.notifyDataSetChanged();
    }

    public void initializeLoadingContentView(View view) {
        this.mGridView = (StaggeredGridView) findViewById(R.id.photo_video_viewer_page_view_grid_view);
        this.mNoItemsTextView = (ThemedTextView) findViewById(R.id.photo_video_viewer_no_items_text);
    }

    public void handleReload() {
        this.mGridView.notifyDataSetChanged();
    }

    public void releaseImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
        if (this.mGridView != null) {
            this.mGridView.restoreImages();
        }
    }
}
