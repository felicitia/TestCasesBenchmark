package com.contextlogic.wish.ui.slideshow;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.api.model.WishImageSlideshow;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;

public class FullScreenSquareImageSlideshowView extends FrameLayout implements ImageRestorable, Recyclable {
    private SquareImageSlideshowView mSlideshowView;

    public FullScreenSquareImageSlideshowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mSlideshowView = new SquareImageSlideshowView(getContext());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        addView(this.mSlideshowView, layoutParams);
    }

    public void setSlideshow(WishImageSlideshow wishImageSlideshow) {
        this.mSlideshowView.setSlideshow(wishImageSlideshow);
    }

    public void releaseImages() {
        this.mSlideshowView.releaseImages();
    }

    public void restoreImages() {
        this.mSlideshowView.restoreImages();
    }

    public void recycle() {
        this.mSlideshowView.recycle();
    }
}
