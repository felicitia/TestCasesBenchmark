package com.contextlogic.wish.activity.signup.redesign.SelectCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class SelectCategoryCellView extends FrameLayout implements ImageRestorable {
    private AutoReleasableImageView mCategoryBorder;
    private AutoReleasableImageView mCategoryCheck;
    private NetworkImageView mCategoryImage;
    private ThemedTextView mCategoryName;
    private boolean mSelected;
    private String mTrueTag;

    public SelectCategoryCellView(Context context) {
        super(context);
        init();
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.signup_flow_category_cell_view, this);
        this.mCategoryImage = (NetworkImageView) inflate.findViewById(R.id.signup_category_image);
        this.mCategoryBorder = (AutoReleasableImageView) inflate.findViewById(R.id.signup_category_border);
        this.mCategoryCheck = (AutoReleasableImageView) inflate.findViewById(R.id.signup_flow_category_check);
        this.mCategoryBorder.setAlpha(0.0f);
        this.mCategoryCheck.setAlpha(0.0f);
        this.mCategoryName = (ThemedTextView) inflate.findViewById(R.id.signup_category_name);
        this.mSelected = false;
    }

    public void toggleSelect() {
        this.mSelected = !this.mSelected;
        float f = 0.0f;
        this.mCategoryBorder.animate().alpha(this.mSelected ? 1.0f : 0.0f).setDuration(200);
        ViewPropertyAnimator animate = this.mCategoryCheck.animate();
        if (this.mSelected) {
            f = 1.0f;
        }
        animate.alpha(f).setDuration(200);
        this.mCategoryName.setTextColor(getContext().getResources().getColor(this.mSelected ? R.color.wish_blue : R.color.cool_gray3));
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
        float f = 0.0f;
        this.mCategoryBorder.setAlpha(this.mSelected ? 1.0f : 0.0f);
        AutoReleasableImageView autoReleasableImageView = this.mCategoryCheck;
        if (this.mSelected) {
            f = 1.0f;
        }
        autoReleasableImageView.setAlpha(f);
        this.mCategoryName.setTextColor(getContext().getResources().getColor(this.mSelected ? R.color.wish_blue : R.color.cool_gray3));
    }

    public void setup(WishSignupFlowCategory wishSignupFlowCategory) {
        this.mTrueTag = wishSignupFlowCategory.getId();
        this.mCategoryName.setText(wishSignupFlowCategory.getTitle());
        this.mCategoryImage.setImage(wishSignupFlowCategory.getImg());
        float f = 0.0f;
        this.mCategoryBorder.setAlpha(isSelected() ? 1.0f : 0.0f);
        AutoReleasableImageView autoReleasableImageView = this.mCategoryCheck;
        if (isSelected()) {
            f = 1.0f;
        }
        autoReleasableImageView.setAlpha(f);
    }

    public void releaseImages() {
        if (this.mCategoryImage != null) {
            this.mCategoryImage.releaseImages();
        }
        if (this.mCategoryBorder != null) {
            this.mCategoryBorder.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mCategoryImage != null) {
            this.mCategoryImage.restoreImages();
        }
        if (this.mCategoryBorder != null) {
            this.mCategoryBorder.restoreImages();
        }
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public String getCategoryTrueTag() {
        return this.mTrueTag;
    }

    public AutoReleasableImageView getCategoryBorder() {
        return this.mCategoryBorder;
    }
}
