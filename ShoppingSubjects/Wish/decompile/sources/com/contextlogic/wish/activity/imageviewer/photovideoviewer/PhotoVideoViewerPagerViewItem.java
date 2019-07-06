package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class PhotoVideoViewerPagerViewItem extends LinearLayout implements ImageRestorable {
    /* access modifiers changed from: private */
    public ImageClickListener mImageClickListener;
    private NetworkImageView mImageThumbnailView;
    private AutoReleasableImageView mVideoIcon;
    private AutoReleasableImageView mVideoThumbnailView;

    public interface ImageClickListener {
        void onImageClick(int i);
    }

    public PhotoVideoViewerPagerViewItem(Context context, ImageClickListener imageClickListener) {
        super(context);
        this.mImageClickListener = imageClickListener;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.photo_video_viewer_grid_item, this);
        this.mImageThumbnailView = (NetworkImageView) inflate.findViewById(R.id.photo_video_viewer_grid_item_photo);
        this.mVideoThumbnailView = (AutoReleasableImageView) inflate.findViewById(R.id.photo_video_viewer_grid_item_video);
        this.mVideoIcon = (AutoReleasableImageView) inflate.findViewById(R.id.photo_video_viewer_grid_item_video_icon);
    }

    public void setImageThumbnail(WishImage wishImage, final int i, boolean z) {
        this.mImageThumbnailView.setVisibility(0);
        this.mVideoThumbnailView.setVisibility(8);
        if (z) {
            this.mVideoIcon.setVisibility(0);
        } else {
            this.mVideoIcon.setVisibility(8);
        }
        if (wishImage != null) {
            this.mImageThumbnailView.setImage(wishImage);
            this.mImageThumbnailView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (PhotoVideoViewerPagerViewItem.this.mImageClickListener != null) {
                        PhotoVideoViewerPagerViewItem.this.mImageClickListener.onImageClick(i);
                    }
                }
            });
        }
    }

    public void setBitmapThumbnail(Bitmap bitmap, final int i) {
        this.mImageThumbnailView.setVisibility(8);
        this.mVideoThumbnailView.setVisibility(0);
        this.mVideoIcon.setVisibility(0);
        this.mVideoThumbnailView.setImageBitmap(bitmap);
        this.mVideoThumbnailView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PhotoVideoViewerPagerViewItem.this.mImageClickListener != null) {
                    PhotoVideoViewerPagerViewItem.this.mImageClickListener.onImageClick(i);
                }
            }
        });
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageThumbnailView.setImagePrefetcher(imageHttpPrefetcher);
    }

    public void releaseImages() {
        if (this.mVideoThumbnailView != null) {
            this.mVideoThumbnailView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mVideoThumbnailView != null) {
            this.mVideoThumbnailView.restoreImages();
        }
    }
}
