package com.contextlogic.wish.activity.login.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;

public class SavedUserRow extends LinearLayout implements ImageRestorable {
    private ProfileImageView mImageView;
    private ThemedTextView mUserName;

    public SavedUserRow(Context context) {
        super(context);
        init();
    }

    public SavedUserRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SavedUserRow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        setOrientation(0);
        View inflate = layoutInflater.inflate(R.layout.saved_user_row, this);
        this.mImageView = (ProfileImageView) inflate.findViewById(R.id.saved_user_image);
        this.mUserName = (ThemedTextView) inflate.findViewById(R.id.saved_user_name);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        setPadding(getResources().getDimensionPixelSize(R.dimen.twelve_padding), dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.gray_5_border));
    }

    public void setup(WishImage wishImage, String str, boolean z) {
        this.mUserName.setText(str);
        this.mImageView.setup(wishImage, str, z);
    }

    public void releaseImages() {
        if (this.mImageView != null) {
            this.mImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImageView != null) {
            this.mImageView.restoreImages();
        }
    }
}
