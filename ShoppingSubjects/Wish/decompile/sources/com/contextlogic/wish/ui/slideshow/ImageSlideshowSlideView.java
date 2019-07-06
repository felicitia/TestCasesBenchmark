package com.contextlogic.wish.ui.slideshow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImageSlideshowSlide;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.image.NetworkImageView.NetworkImageViewCallback;
import com.contextlogic.wish.ui.image.NetworkImageView.ResizeType;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.util.BitmapUtil;

public class ImageSlideshowSlideView extends FrameLayout implements ImageRestorable, NetworkImageViewCallback, Recyclable {
    private int mImageLoadActionsNeeded;
    private ImageSlideshowView mImageSlideshowView;
    private NetworkImageView mImageView;
    private boolean mReady;
    private View mReviewContainer;
    private TextView mReviewText;
    private WishImageSlideshowSlide mSlide;
    private RedesignedBlueStarRatingView mStarRatingView;
    private NetworkImageView mUserImageView;
    private TextView mUserNameText;

    public ImageSlideshowSlideView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.image_slideshow_slide, this);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.image_slideshow_slide_image);
        this.mUserImageView = (NetworkImageView) inflate.findViewById(R.id.image_slideshow_slide_user_image);
        this.mUserImageView.setCircleCrop(true);
        this.mUserNameText = (TextView) inflate.findViewById(R.id.image_slideshow_slide_user_name_text);
        this.mReviewText = (TextView) inflate.findViewById(R.id.image_slideshow_slide_review_text);
        this.mReviewContainer = inflate.findViewById(R.id.image_slideshow_slide_review_container);
        this.mStarRatingView = (RedesignedBlueStarRatingView) inflate.findViewById(R.id.image_slideshow_slide_star_rating);
    }

    public void setSlide(WishImageSlideshowSlide wishImageSlideshowSlide) {
        this.mSlide = wishImageSlideshowSlide;
        this.mReady = false;
        this.mImageLoadActionsNeeded = 0;
        this.mImageView.setImage(null);
        this.mUserImageView.setImage(null);
        if (wishImageSlideshowSlide != null) {
            if (wishImageSlideshowSlide.getReviewText() == null || wishImageSlideshowSlide.getReviewText().isEmpty() || wishImageSlideshowSlide.getUserName() == null || wishImageSlideshowSlide.getUserName().isEmpty()) {
                this.mReviewContainer.setVisibility(8);
            } else {
                this.mReviewContainer.setVisibility(0);
                this.mReviewText.setText(wishImageSlideshowSlide.getReviewText());
                this.mUserNameText.setText(wishImageSlideshowSlide.getUserName());
                if (wishImageSlideshowSlide.getUserImage() != null) {
                    this.mImageLoadActionsNeeded++;
                    this.mUserImageView.setVisibility(0);
                    this.mUserImageView.setImage(wishImageSlideshowSlide.getUserImage(), (NetworkImageViewCallback) this);
                } else {
                    this.mUserImageView.setVisibility(8);
                }
                this.mStarRatingView.setup(wishImageSlideshowSlide.getStarRating(), Size.SMALL, null);
            }
            this.mImageLoadActionsNeeded++;
            this.mImageView.setImage(wishImageSlideshowSlide.getImage(), this.mSlide.getCropImage() ? ResizeType.CROP : ResizeType.FIT, this, true);
        }
    }

    public void setImageSlideshowView(ImageSlideshowView imageSlideshowView) {
        this.mImageSlideshowView = imageSlideshowView;
    }

    public WishImageSlideshowSlide getSlide() {
        return this.mSlide;
    }

    public boolean isReady() {
        return this.mReady;
    }

    public void recycle() {
        setSlide(null);
    }

    public void onImageLoaded() {
        this.mImageLoadActionsNeeded--;
        if (this.mImageLoadActionsNeeded <= 0 && this.mImageSlideshowView != null && this.mImageView != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) this.mImageView.getDrawable();
            if (bitmapDrawable != null) {
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap == null) {
                    setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
                } else if (this.mSlide.getCropImage()) {
                    setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
                } else {
                    setBackgroundColor(BitmapUtil.getDominantColor(bitmap));
                }
            } else {
                setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
            }
            this.mReady = true;
            this.mImageSlideshowView.onSlideLoadComplete(this);
        }
    }

    public void onImageLoadFailed() {
        setSlide(null);
        if (this.mImageSlideshowView != null) {
            this.mImageSlideshowView.onSlideLoadFailed(this);
        }
    }

    public void releaseImages() {
        if (this.mImageView != null) {
            this.mImageView.releaseImages();
        }
        if (this.mUserImageView != null) {
            this.mUserImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImageView != null) {
            this.mImageView.restoreImages();
        }
        if (this.mUserImageView != null) {
            this.mUserImageView.restoreImages();
        }
    }
}
