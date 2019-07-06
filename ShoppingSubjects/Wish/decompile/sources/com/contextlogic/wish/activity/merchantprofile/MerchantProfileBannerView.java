package com.contextlogic.wish.activity.merchantprofile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishMerchant;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import java.text.SimpleDateFormat;

public class MerchantProfileBannerView extends LinearLayout {
    private TextView mApprovedDateText;
    private NetworkImageView mImageView;
    private TextView mNameText;
    private TextView mPercentPositiveFeedbackText;
    private TextView mPositiveFeedbackText;
    private LinearLayout mRatingAndFeedbackSection;
    private TextView mRatingCountText;
    private RedesignedBlueStarRatingView mStarRatingView;
    private TextView mUserRatingText;

    public MerchantProfileBannerView(Context context) {
        this(context, null);
    }

    public MerchantProfileBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MerchantProfileBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.merchant_profile_fragment_banner, this);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.merchant_feed_banner_image);
        this.mImageView.setCircleCrop(true);
        this.mNameText = (TextView) inflate.findViewById(R.id.merchant_profile_fragment_banner_name);
        this.mRatingCountText = (TextView) inflate.findViewById(R.id.merchant_profile_fragment_banner_rating_count);
        this.mUserRatingText = (TextView) inflate.findViewById(R.id.user_ratings_text);
        this.mStarRatingView = (RedesignedBlueStarRatingView) inflate.findViewById(R.id.merchant_profile_fragment_star_ratings_view);
        this.mApprovedDateText = (TextView) inflate.findViewById(R.id.merchant_profile_fragment_banner_approved_date);
        this.mPositiveFeedbackText = (TextView) inflate.findViewById(R.id.positive_feedback_text);
        this.mPercentPositiveFeedbackText = (TextView) inflate.findViewById(R.id.merchant_profile_fragment_banner_positive_feedback_percent);
        this.mRatingAndFeedbackSection = (LinearLayout) inflate.findViewById(R.id.merchant_profile_fragment_banner_rating_section);
    }

    public void setMerchantHeader(BaseProductFeedFragment baseProductFeedFragment, WishMerchant wishMerchant) {
        this.mImageView.setImage(new WishImage(wishMerchant.getImageUrl()));
        this.mNameText.setText(wishMerchant.getDisplayName());
        TextView textView = this.mApprovedDateText;
        StringBuilder sb = new StringBuilder();
        sb.append(baseProductFeedFragment.getString(R.string.seller_since));
        sb.append(" ");
        sb.append(new SimpleDateFormat("MMMM yyyy").format(wishMerchant.getApprovedDate()));
        textView.setText(sb.toString());
        if (wishMerchant.getRatingCount() > 0) {
            this.mRatingAndFeedbackSection.setVisibility(0);
            this.mStarRatingView.setup(wishMerchant.getRatingCount(), wishMerchant.getRating(), Size.INTERMEDIATE, null);
            this.mRatingCountText.setText(Integer.toString(wishMerchant.getRatingCount()));
            this.mUserRatingText.setText(getResources().getQuantityString(R.plurals.user_rating, wishMerchant.getRatingCount()));
            this.mPositiveFeedbackText.setText(getResources().getString(R.string.positive_feedback));
            TextView textView2 = this.mPercentPositiveFeedbackText;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Integer.toString(wishMerchant.getPercentPositiveFeedback()));
            sb2.append("%");
            textView2.setText(sb2.toString());
            return;
        }
        this.mRatingAndFeedbackSection.setVisibility(8);
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
    }
}
