package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ProfileImageView extends FrameLayout {
    private ImageView mAnotherUserImage;
    private int mImageSize;
    private NetworkImageView mProfileImage;
    private ThemedTextView mProfileImageText;
    private int mTextSize;

    public ProfileImageView(Context context) {
        this(context, null);
    }

    public ProfileImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mImageSize = context.getResources().getDimensionPixelSize(R.dimen.profile_image_view_default_image_size);
        this.mTextSize = context.getResources().getDimensionPixelSize(R.dimen.text_size_title);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ProfileImageView, 0, 0);
            try {
                this.mImageSize = obtainStyledAttributes.getDimensionPixelSize(0, this.mImageSize);
                this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(1, this.mTextSize);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        init();
    }

    public ProfileImageView(Context context, int i, int i2) {
        super(context, null);
        this.mImageSize = i;
        this.mTextSize = i2;
        init();
    }

    public void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.profile_image_view, this, true);
        this.mProfileImage = (NetworkImageView) findViewById(R.id.profile_image);
        this.mProfileImage.getLayoutParams().height = this.mImageSize;
        this.mProfileImage.getLayoutParams().width = this.mImageSize;
        this.mProfileImage.setCircleCrop(true);
        this.mProfileImageText = (ThemedTextView) findViewById(R.id.profile_image_view_text);
        this.mProfileImageText.setTextSize(0, (float) this.mTextSize);
        this.mProfileImageText.getLayoutParams().height = this.mImageSize;
        this.mProfileImageText.getLayoutParams().width = this.mImageSize;
        this.mAnotherUserImage = (ImageView) findViewById(R.id.another_user_img);
        this.mAnotherUserImage.getLayoutParams().height = this.mImageSize;
        this.mAnotherUserImage.getLayoutParams().width = this.mImageSize;
    }

    public void setAnotherUserImage(int i) {
        this.mAnotherUserImage.setImageResource(i);
    }

    public void setup(WishImage wishImage, String str) {
        if (wishImage != null) {
            this.mProfileImage.setImage(wishImage);
            this.mProfileImage.setVisibility(0);
            this.mProfileImageText.setVisibility(8);
            return;
        }
        if (str == null || str.trim().length() <= 0) {
            this.mProfileImageText.setText("W");
        } else {
            this.mProfileImageText.setText(str.trim().toUpperCase().substring(0, 1));
        }
        this.mProfileImageText.setFontResizable(true);
        this.mProfileImage.setVisibility(8);
        this.mProfileImageText.setVisibility(0);
    }

    public void setup(WishImage wishImage, String str, boolean z) {
        if (!z) {
            setup(wishImage, str);
            return;
        }
        this.mProfileImage.setVisibility(8);
        this.mProfileImageText.setVisibility(8);
        this.mAnotherUserImage.setVisibility(0);
    }

    public void clear() {
        this.mProfileImage.setImage(null);
        this.mProfileImage.setVisibility(0);
        this.mProfileImageText.setVisibility(8);
    }

    public NetworkImageView getProfileImage() {
        return this.mProfileImage;
    }

    public void updateProfileImage(WishImage wishImage) {
        this.mProfileImage.setImage(wishImage);
        this.mProfileImage.setVisibility(0);
    }

    public void releaseImages() {
        if (this.mProfileImage != null) {
            this.mProfileImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProfileImage != null) {
            this.mProfileImage.restoreImages();
        }
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        if (this.mProfileImage != null) {
            this.mProfileImage.setImagePrefetcher(imageHttpPrefetcher);
        }
    }
}
