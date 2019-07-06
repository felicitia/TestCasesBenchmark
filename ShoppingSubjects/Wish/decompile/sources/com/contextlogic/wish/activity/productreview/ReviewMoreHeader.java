package com.contextlogic.wish.activity.productreview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ReviewMoreHeader extends LinearLayout {
    /* access modifiers changed from: private */
    public ProductReviewActivity mActivity;
    private Button mContinueShoppingButton;
    private ThemedTextView mPointsApprovalMessage;
    private ThemedTextView mPointsEarned;
    private ThemedTextView mReviewSubmittedMessage;
    private AutoReleasableImageView mThumbnailImage;

    public ReviewMoreHeader(ProductReviewActivity productReviewActivity) {
        super(productReviewActivity);
        this.mActivity = productReviewActivity;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) this.mActivity.getSystemService("layout_inflater")).inflate(R.layout.review_more_header, this);
        this.mThumbnailImage = (AutoReleasableImageView) inflate.findViewById(R.id.review_item_photo);
        this.mReviewSubmittedMessage = (ThemedTextView) inflate.findViewById(R.id.review_submitted_message);
        this.mPointsEarned = (ThemedTextView) inflate.findViewById(R.id.reward_points_earned);
        this.mPointsApprovalMessage = (ThemedTextView) inflate.findViewById(R.id.reward_points_approval_message);
        this.mContinueShoppingButton = (Button) inflate.findViewById(R.id.continue_shopping_button);
        this.mContinueShoppingButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReviewMoreHeader.this.mActivity.startActivity(new Intent(ReviewMoreHeader.this.mActivity, BrowseActivity.class));
            }
        });
    }

    public void setup(int i, Bitmap bitmap, int i2, WishProductReviewItem wishProductReviewItem, boolean z) {
        this.mPointsEarned.setText(WishApplication.getInstance().getString(R.string.plus_x_points, new Object[]{Integer.valueOf(i)}));
        this.mPointsApprovalMessage.setText(WishApplication.getInstance().getString(R.string.you_will_earn_x_points, new Object[]{Integer.valueOf(i)}));
        this.mThumbnailImage.setImageBitmap(bitmap);
        if (i2 == 0) {
            this.mReviewSubmittedMessage.setText(wishProductReviewItem.hasRating() ? R.string.photo_submitted : R.string.photo_and_review_submitted);
        } else {
            this.mReviewSubmittedMessage.setText(wishProductReviewItem.hasRating() ? R.string.video_submitted : R.string.video_and_review_submitted);
        }
        if (z) {
            this.mContinueShoppingButton.setVisibility(0);
        } else {
            this.mContinueShoppingButton.setVisibility(8);
        }
    }
}
