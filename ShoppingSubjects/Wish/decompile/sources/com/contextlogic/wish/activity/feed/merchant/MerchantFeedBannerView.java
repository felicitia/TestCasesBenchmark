package com.contextlogic.wish.activity.feed.merchant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishMerchant;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class MerchantFeedBannerView extends BaseFeedHeaderView {
    private BaseProductFeedFragment mFragment;
    private NetworkImageView mImageView;
    private WishMerchant mMerchant;
    private TextView mNameText;
    private TextView mProductCountText;
    private ImageView mRatingStarFive;
    private ImageView mRatingStarFour;
    private ImageView mRatingStarOne;
    private ImageView mRatingStarThree;
    private ImageView mRatingStarTwo;
    private TextView mRatingText;
    private View mRatingView;
    private View mViewAllRatingsText;

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public MerchantFeedBannerView(Context context, BaseProductFeedFragment baseProductFeedFragment) {
        super(context);
        this.mFragment = baseProductFeedFragment;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.merchant_feed_banner, this);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.merchant_feed_banner_image);
        this.mNameText = (TextView) inflate.findViewById(R.id.merchant_feed_banner_name);
        this.mProductCountText = (TextView) inflate.findViewById(R.id.merchant_feed_banner_product_count);
        this.mViewAllRatingsText = inflate.findViewById(R.id.merchant_feed_banner_view_ratings);
        this.mRatingText = (TextView) inflate.findViewById(R.id.merchant_feed_banner_rating_count);
        this.mRatingStarOne = (ImageView) inflate.findViewById(R.id.merchant_feed_banner_rating_image_one);
        this.mRatingStarTwo = (ImageView) inflate.findViewById(R.id.merchant_feed_banner_rating_image_two);
        this.mRatingStarThree = (ImageView) inflate.findViewById(R.id.merchant_feed_banner_rating_image_three);
        this.mRatingStarFour = (ImageView) inflate.findViewById(R.id.merchant_feed_banner_rating_image_four);
        this.mRatingStarFive = (ImageView) inflate.findViewById(R.id.merchant_feed_banner_rating_image_five);
        this.mRatingView = inflate.findViewById(R.id.merchant_feed_banner_rating_view);
        this.mRatingView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MerchantFeedBannerView.this.showRatings();
            }
        });
        this.mViewAllRatingsText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MerchantFeedBannerView.this.showRatings();
            }
        });
    }

    public void setMerchant(WishMerchant wishMerchant) {
        this.mMerchant = wishMerchant;
        this.mImageView.setImage(new WishImage(wishMerchant.getImageUrl()));
        this.mNameText.setText(wishMerchant.getDisplayName());
        if (wishMerchant.getProductCount() > 0) {
            this.mProductCountText.setVisibility(0);
            this.mProductCountText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.selling_product, wishMerchant.getProductCount(), new Object[]{Integer.valueOf(wishMerchant.getProductCount())}));
        } else {
            this.mProductCountText.setVisibility(8);
        }
        if (wishMerchant.getRatingCount() > 0) {
            this.mRatingView.setVisibility(0);
            int[] iArr = new int[5];
            int i = 0;
            while (i < 5) {
                int i2 = i + 1;
                double d = (double) i2;
                if (wishMerchant.getRating() >= d) {
                    iArr[i] = R.drawable.yellow_star;
                } else {
                    double rating = d - wishMerchant.getRating();
                    if (rating <= 0.25d) {
                        iArr[i] = R.drawable.yellow_star;
                    } else if (rating <= 0.75d) {
                        iArr[i] = R.drawable.half_star;
                    } else {
                        iArr[i] = R.drawable.gray_star;
                    }
                }
                i = i2;
            }
            this.mRatingStarOne.setImageResource(iArr[0]);
            this.mRatingStarTwo.setImageResource(iArr[1]);
            this.mRatingStarThree.setImageResource(iArr[2]);
            this.mRatingStarFour.setImageResource(iArr[3]);
            this.mRatingStarFive.setImageResource(iArr[4]);
            this.mRatingText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.rating, wishMerchant.getRatingCount(), new Object[]{Integer.valueOf(wishMerchant.getRatingCount())}));
            return;
        }
        this.mRatingView.setVisibility(8);
        this.mViewAllRatingsText.setVisibility(8);
    }

    public void showRatings() {
        if (this.mMerchant != null) {
        }
    }

    public void setFragment(BaseProductFeedFragment baseProductFeedFragment) {
        this.mFragment = baseProductFeedFragment;
    }
}
