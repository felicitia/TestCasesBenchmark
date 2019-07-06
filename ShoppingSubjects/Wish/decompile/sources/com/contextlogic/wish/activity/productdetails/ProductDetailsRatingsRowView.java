package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.LocaleUtil;

public class ProductDetailsRatingsRowView extends LinearLayout implements ImageRestorable {
    private TextView mAuthor;
    private TextView mCommentText;
    private ProfileImageView mProfileImageView;
    /* access modifiers changed from: private */
    public WishRating mRating;
    private NetworkImageView mRatingImage;
    private View mRatingVideoIcon;
    private NetworkImageView mRatingVideoThumbnail;
    private RedesignedBlueStarRatingView mStarRatingView;
    private TextView mSyndicatedText;
    private TextView mTimestamp;
    private LinearLayout mUpvoteButton;
    /* access modifiers changed from: private */
    public TextView mUpvoteCountText;
    /* access modifiers changed from: private */
    public ImageView mUpvoteImage;
    private View mUpvoteRatingsView;
    private TextView mUpvoteText;
    private TextView mWishStarBadge;

    public ProductDetailsRatingsRowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_fragment_ratings_item_row, this);
        this.mCommentText = (TextView) inflate.findViewById(R.id.fragment_ratings_item_text_body);
        this.mAuthor = (TextView) inflate.findViewById(R.id.fragment_ratings_item_author);
        this.mWishStarBadge = (TextView) inflate.findViewById(R.id.fragment_ratings_item_wish_star_badge);
        this.mProfileImageView = (ProfileImageView) inflate.findViewById(R.id.fragment_ratings_item_profile_image_view);
        this.mTimestamp = (TextView) inflate.findViewById(R.id.fragment_ratings_item_timestamp);
        this.mRatingImage = (NetworkImageView) inflate.findViewById(R.id.fragment_ratings_item_rating_image);
        this.mRatingVideoThumbnail = (NetworkImageView) inflate.findViewById(R.id.fragment_ratings_item_rating_video_thumbnail);
        this.mRatingVideoIcon = inflate.findViewById(R.id.fragment_ratings_item_rating_video_icon);
        this.mSyndicatedText = (TextView) inflate.findViewById(R.id.fragment_ratings_item_syndicated_text);
        this.mStarRatingView = (RedesignedBlueStarRatingView) inflate.findViewById(R.id.fragment_ratings_star_ratings_view);
        this.mUpvoteRatingsView = inflate.findViewById(R.id.upvote_ratings_section);
        this.mUpvoteButton = (LinearLayout) inflate.findViewById(R.id.like_review_button);
        this.mUpvoteImage = (ImageView) inflate.findViewById(R.id.like_review_icon);
        this.mUpvoteCountText = (TextView) inflate.findViewById(R.id.fragment_ratings_upvote_count);
        this.mUpvoteText = (TextView) inflate.findViewById(R.id.like_review_text);
    }

    public void setup(WishRating wishRating) {
        this.mRating = wishRating;
        String comment = this.mRating.getComment();
        if (comment == null || comment.trim().equals("")) {
            this.mCommentText.setText(getContext().getResources().getString(R.string.ratings_no_comment));
            this.mCommentText.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray_3));
        } else {
            this.mCommentText.setText(comment);
            this.mCommentText.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray_2));
        }
        this.mAuthor.setText(this.mRating.getAuthor().getFirstName());
        if (!this.mRating.getAuthor().isWishStar()) {
            this.mAuthor.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState()) {
                setBackgroundColor(getResources().getColor(R.color.white));
                this.mWishStarBadge.setVisibility(8);
            }
        } else if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState()) {
            this.mAuthor.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            setBackgroundColor(getResources().getColor(R.color.gray7));
            this.mWishStarBadge.setVisibility(0);
        } else {
            this.mAuthor.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding));
            this.mAuthor.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.wishstar_badge_16), null);
            this.mWishStarBadge.setVisibility(8);
        }
        this.mProfileImageView.clear();
        this.mProfileImageView.setup(this.mRating.getAuthor().getProfileImage(), this.mRating.getAuthor().getFirstName());
        this.mTimestamp.setText(DateUtil.getFuzzyDateFromNow(this.mRating.getTimestamp()));
        if (!this.mRating.isSyndicated() || this.mRating.getSyndicatedText() == null) {
            this.mSyndicatedText.setVisibility(8);
        } else {
            this.mSyndicatedText.setVisibility(0);
            this.mSyndicatedText.setText(this.mRating.getSyndicatedText());
        }
        this.mRatingImage.setImage(null);
        if (this.mRating.getImageThumbnailUrlString() != null) {
            this.mRatingImage.setVisibility(0);
            this.mRatingImage.setImage(new WishImage(this.mRating.getImageThumbnailUrlString()));
        } else {
            this.mRatingImage.setVisibility(8);
        }
        this.mRatingVideoThumbnail.setImage(null);
        if (this.mRating.getExtraVideo() != null) {
            this.mRatingVideoIcon.setVisibility(0);
            this.mRatingVideoThumbnail.setVisibility(0);
            this.mRatingVideoThumbnail.setImage(new WishImage(this.mRating.getVideoThumbnailUrlString()));
        } else {
            this.mRatingVideoIcon.setVisibility(8);
            this.mRatingVideoThumbnail.setVisibility(8);
        }
        this.mStarRatingView.setup((double) this.mRating.getRating(), Size.INTERMEDIATE, null);
        this.mUpvoteRatingsView.setVisibility(8);
        setupCountryIndicatorIfNecessary();
    }

    public void setOnItemClickListener(OnClickListener onClickListener) {
        if (this.mAuthor != null) {
            this.mAuthor.setOnClickListener(onClickListener);
        }
        if (this.mProfileImageView != null) {
            this.mProfileImageView.setOnClickListener(onClickListener);
        }
        if (this.mTimestamp != null) {
            this.mTimestamp.setOnClickListener(onClickListener);
        }
    }

    public void setOnWishStarBadgeClickListener(OnClickListener onClickListener) {
        if (this.mWishStarBadge != null) {
            this.mWishStarBadge.setOnClickListener(onClickListener);
        }
    }

    public void setOnRatingImageClickListener(OnClickListener onClickListener) {
        if (this.mRatingImage != null && this.mRatingImage.getVisibility() == 0) {
            this.mRatingImage.setOnClickListener(onClickListener);
        }
    }

    public void setOnRatingVideoClickListener(OnClickListener onClickListener) {
        if (this.mRatingVideoThumbnail != null && this.mRatingVideoThumbnail.getVisibility() == 0) {
            this.mRatingVideoThumbnail.setOnClickListener(onClickListener);
        }
    }

    private void setupCountryIndicatorIfNecessary() {
        Drawable drawable;
        String countryCode = this.mRating.getAuthor().getCountryCode();
        if (countryCode != null) {
            int resIdFromCountryCode = LocaleUtil.getResIdFromCountryCode(countryCode);
            if (resIdFromCountryCode != 0) {
                drawable = ContextCompat.getDrawable(getContext(), resIdFromCountryCode);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            } else {
                drawable = null;
            }
            this.mTimestamp.setCompoundDrawables(drawable, null, null, null);
            this.mTimestamp.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.six_padding));
            return;
        }
        this.mTimestamp.setCompoundDrawables(null, null, null, null);
        this.mAuthor.setCompoundDrawables(null, null, null, null);
    }

    public void setupUpvoteRatings(final ProductDetailsFragment productDetailsFragment) {
        this.mUpvoteRatingsView.setVisibility(0);
        this.mUpvoteCountText.setText(getUpvoteString(this.mRating.getNumUpvotes()));
        setUpvoteButtonStyle(this.mRating.hasUserUpvoted());
        this.mUpvoteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int i;
                if (ProductDetailsRatingsRowView.this.mRating.hasUserUpvoted()) {
                    productDetailsFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                            productDetailsServiceFragment.removeProductRatingUpvote(ProductDetailsRatingsRowView.this.mRating.getRatingId());
                        }
                    });
                    i = -1;
                } else {
                    productDetailsFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                            productDetailsServiceFragment.productRatingUpvote(ProductDetailsRatingsRowView.this.mRating.getRatingId());
                        }
                    });
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation.setDuration(50);
                    ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation2.setDuration(50);
                    scaleAnimation2.setStartOffset(50);
                    AnimationSet animationSet = new AnimationSet(true);
                    animationSet.setInterpolator(new LinearInterpolator());
                    animationSet.addAnimation(scaleAnimation);
                    animationSet.addAnimation(scaleAnimation2);
                    ProductDetailsRatingsRowView.this.mUpvoteImage.startAnimation(animationSet);
                    i = 1;
                }
                ProductDetailsRatingsRowView.this.mRating.setNumUpvotes(ProductDetailsRatingsRowView.this.mRating.getNumUpvotes() + i);
                ProductDetailsRatingsRowView.this.mUpvoteCountText.setText(ProductDetailsRatingsRowView.this.getUpvoteString(ProductDetailsRatingsRowView.this.mRating.getNumUpvotes()));
                ProductDetailsRatingsRowView.this.mRating.setUserUpvoted(true ^ ProductDetailsRatingsRowView.this.mRating.hasUserUpvoted());
                ProductDetailsRatingsRowView.this.setUpvoteButtonStyle(ProductDetailsRatingsRowView.this.mRating.hasUserUpvoted());
            }
        });
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.setImagePrefetcher(imageHttpPrefetcher);
        }
        if (this.mRatingImage != null) {
            this.mRatingImage.setImagePrefetcher(imageHttpPrefetcher);
        }
    }

    public void refreshTimestamp() {
        this.mTimestamp.setText(DateUtil.getFuzzyDateFromNow(this.mRating.getTimestamp()));
    }

    /* access modifiers changed from: private */
    public void setUpvoteButtonStyle(boolean z) {
        if (z) {
            this.mUpvoteImage.setImageResource(R.drawable.like_btn);
            int color = WishApplication.getInstance().getResources().getColor(R.color.main_primary);
            this.mUpvoteCountText.setTextColor(color);
            this.mUpvoteText.setTextColor(color);
            this.mUpvoteButton.setSelected(true);
            return;
        }
        this.mUpvoteImage.setImageResource(R.drawable.like_btn_gray);
        int color2 = WishApplication.getInstance().getResources().getColor(R.color.cool_gray3);
        this.mUpvoteCountText.setTextColor(color2);
        this.mUpvoteText.setTextColor(color2);
        this.mUpvoteButton.setSelected(false);
    }

    public String getUpvoteString(int i) {
        if (i == 0) {
            return "";
        }
        return WishApplication.getInstance().getResources().getQuantityString(R.plurals.number_said_thanks, i, new Object[]{Integer.valueOf(i)});
    }

    public void hideDivider(boolean z) {
        View findViewById = findViewById(R.id.fragment_rating_review_divider);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 8 : 0);
        }
    }

    public void releaseImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.releaseImages();
        }
        if (this.mRatingImage != null) {
            this.mRatingImage.releaseImages();
        }
        if (this.mRatingVideoThumbnail != null) {
            this.mRatingVideoThumbnail.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.restoreImages();
        }
        if (this.mRatingImage != null) {
            this.mRatingImage.restoreImages();
        }
        if (this.mRatingVideoThumbnail != null) {
            this.mRatingVideoThumbnail.restoreImages();
        }
    }
}
