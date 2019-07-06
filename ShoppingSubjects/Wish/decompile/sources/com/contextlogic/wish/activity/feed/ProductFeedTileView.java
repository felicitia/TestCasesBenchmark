package com.contextlogic.wish.activity.feed;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.productboost.AdProductBadgeView;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishBrand;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProduct.VideoStatus;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.api.model.WishProductBoostFeedTileLabelSpec;
import com.contextlogic.wish.api.model.WishProductBoostFeedTileLabelSpec.LabelType;
import com.contextlogic.wish.api.model.WishProductVideoInfo.VideoLength;
import com.contextlogic.wish.api.model.WishShippingOption;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.StaggeredGridViewSizedCell;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.contextlogic.wish.ui.view.Destroyable;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.video.VideoManager;
import com.contextlogic.wish.video.VideoPlayerView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;

public class ProductFeedTileView extends LinearLayout implements StaggeredGridViewSizedCell, ImageRestorable, Destroyable, Recyclable {
    private LinearLayout mBadgeList;
    private TextView mBottomShippingCost;
    private TextView mBottomShippingDeliveryTime;
    /* access modifiers changed from: private */
    public NetworkImageView mBrandLogo;
    private CheckBox mCheckbox;
    /* access modifiers changed from: private */
    public TextView mDiscountBannerText;
    private View mEditOverlay;
    private TextView mNumPurchasedText;
    private TextView mNumericalRatingTextView;
    private int mPosition;
    private View mPriceLayout;
    private TextView mPriceLayoutMainText;
    private TextView mPriceLayoutMainTextSwapped;
    private TextView mPriceLayoutMainTextUnswapped;
    private TextView mPriceLayoutSubText;
    private TextView mPriceLayoutSubTextSwapped;
    private TextView mPriceLayoutSubTextUnswapped;
    /* access modifiers changed from: private */
    public WishProduct mProduct;
    private TextView mProductBoostBannerText;
    private ViewStub mProductDetailsViewStub;
    /* access modifiers changed from: private */
    public NetworkImageView mProductImage;
    private boolean mSelected;
    private TextView mTopShippingCost;
    private TextView mTopShippingDeliveryTime;
    private TextView mUrgencyBannerText;
    private FrameLayout mVideoPlayerContainer;
    private VideoPlayerView mVideoPlayerView;
    private TextView mWishlistNewItemBadge;

    public ProductFeedTileView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_feed_tile, this);
        this.mProductImage = (NetworkImageView) inflate.findViewById(R.id.product_feed_image);
        this.mProductImage.setPlaceholderColor(WishApplication.getInstance().getResources().getColor(R.color.image_placeholder_light_background));
        this.mProductImage.disableTouchEvents();
        this.mVideoPlayerContainer = (FrameLayout) inflate.findViewById(R.id.product_feed_video_container);
        this.mCheckbox = (CheckBox) inflate.findViewById(R.id.product_feed_edit_checkbox);
        this.mEditOverlay = inflate.findViewById(R.id.product_feed_edit_overlay);
        this.mUrgencyBannerText = (TextView) inflate.findViewById(R.id.product_feed_urgency_banner_text);
        this.mDiscountBannerText = (TextView) inflate.findViewById(R.id.product_feed_discount_banner_text);
        this.mProductBoostBannerText = (TextView) inflate.findViewById(R.id.product_feed_product_boost_banner);
        this.mBrandLogo = (NetworkImageView) inflate.findViewById(R.id.product_feed_tile_brand_logo_image);
        this.mPriceLayout = inflate.findViewById(R.id.product_feed_price_layout);
        this.mPriceLayoutMainText = (TextView) inflate.findViewById(R.id.product_feed_price_main_text);
        this.mPriceLayoutSubText = (TextView) inflate.findViewById(R.id.product_feed_price_sub_text);
        this.mWishlistNewItemBadge = (TextView) inflate.findViewById(R.id.product_feed_new_wishlist_item_badge);
        this.mPriceLayoutSubText.setPaintFlags(this.mPriceLayoutSubText.getPaintFlags() | 16);
        this.mNumPurchasedText = (TextView) inflate.findViewById(R.id.product_feed_num_purchased_text);
        this.mPriceLayoutMainTextUnswapped = this.mPriceLayoutMainText;
        this.mPriceLayoutSubTextUnswapped = this.mPriceLayoutSubText;
        this.mPriceLayoutMainTextSwapped = (TextView) inflate.findViewById(R.id.product_feed_price_main_text_swapped);
        this.mPriceLayoutSubTextSwapped = (TextView) inflate.findViewById(R.id.product_feed_price_sub_text_swapped);
        this.mPriceLayoutMainTextSwapped.setVisibility(8);
        this.mPriceLayoutSubTextSwapped.setVisibility(8);
        this.mPriceLayoutSubTextSwapped.setPaintFlags(this.mPriceLayoutSubText.getPaintFlags() | 16);
        this.mProductDetailsViewStub = (ViewStub) findViewById(R.id.product_details_container_stub);
        this.mSelected = false;
        this.mBadgeList = (LinearLayout) inflate.findViewById(R.id.product_feed_badge_list);
    }

    public void setProduct(WishProduct wishProduct) {
        this.mProductImage.setImage(wishProduct.getImage(), 0);
        this.mProduct = wishProduct;
        refreshView();
        refreshCommerceState();
        refreshVideoState();
    }

    private void refreshVideoState() {
        this.mVideoPlayerContainer.setVisibility(8);
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.setPlayer(null);
        }
        if (this.mProduct != null && this.mProduct.getVideoInfo() != null) {
            initializeVideoPlayerIfNeeded();
            String urlString = this.mProduct.getVideoInfo().getUrlString(VideoLength.SHORT);
            SimpleExoPlayer requestVideoPlayer = VideoManager.getInstance().requestVideoPlayer(getContext(), this.mProduct.getProductId(), urlString);
            if (requestVideoPlayer == null || urlString == null) {
                this.mProduct.changeVideoStatus(VideoStatus.SKIPPED);
            } else {
                this.mVideoPlayerView.setPlayer(requestVideoPlayer);
                this.mProductImage.setVisibility(8);
                this.mVideoPlayerContainer.setVisibility(0);
                requestVideoPlayer.setPlayWhenReady(true);
                this.mProduct.changeVideoStatus(VideoStatus.PLAYED);
            }
        }
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mProductImage.setImagePrefetcher(imageHttpPrefetcher);
    }

    public static int getExpectedHeight(int i) {
        return (int) (((float) ((int) (((float) ((int) (((float) i) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_price_layout_height)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_original_price_layout_height_small)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.eight_padding));
    }

    public void setProductSelected(boolean z) {
        if (isSelectable()) {
            this.mSelected = z;
            refreshSelectedState();
        }
    }

    public void refreshSelectedState() {
        if (this.mProduct != null && isSelectable()) {
            this.mCheckbox.setChecked(this.mSelected);
        }
    }

    public void setEditModeEnabled(boolean z) {
        if (!isSelectable()) {
            z = false;
        }
        if (z) {
            this.mEditOverlay.setVisibility(0);
        } else {
            setProductSelected(false);
            this.mEditOverlay.setVisibility(8);
        }
        refreshSelectedState();
    }

    public void onCellSizeChanged(int i, int i2) {
        this.mUrgencyBannerText.setMaxWidth(((int) (((double) i) * 0.9d)) - getResources().getDimensionPixelSize(R.dimen.filter_feed_fragment_urgency_banner_image_size));
    }

    public String getProductId() {
        if (this.mProduct != null) {
            return this.mProduct.getProductId();
        }
        return null;
    }

    public boolean isSelectable() {
        return this.mProduct != null;
    }

    public NetworkImageView getProductImage() {
        return this.mProductImage;
    }

    public void refreshCommerceState() {
        if (this.mProduct.isCommerceProduct()) {
            if (ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInFeed()) {
                this.mPriceLayoutMainTextUnswapped.setVisibility(8);
                this.mPriceLayoutSubTextUnswapped.setVisibility(8);
                this.mPriceLayoutMainTextSwapped.setVisibility(0);
                this.mPriceLayoutSubTextSwapped.setVisibility(0);
                this.mPriceLayoutMainText = this.mPriceLayoutMainTextSwapped;
                this.mPriceLayoutSubText = this.mPriceLayoutSubTextSwapped;
            } else {
                this.mPriceLayoutMainTextSwapped.setVisibility(8);
                this.mPriceLayoutSubTextSwapped.setVisibility(8);
                this.mPriceLayoutMainTextUnswapped.setVisibility(0);
                this.mPriceLayoutSubTextUnswapped.setVisibility(0);
                this.mPriceLayoutMainText = this.mPriceLayoutMainTextUnswapped;
                this.mPriceLayoutSubText = this.mPriceLayoutSubTextUnswapped;
            }
            WishLocalizedCurrencyValue commerceValue = this.mProduct.getCommerceValue();
            String str = null;
            if (this.mProduct.getUrgencyText() != null) {
                str = this.mProduct.getUrgencyText();
            }
            if (str != null) {
                this.mUrgencyBannerText.setVisibility(0);
                this.mUrgencyBannerText.setText(str);
            } else {
                this.mUrgencyBannerText.setVisibility(8);
            }
            WishLocalizedCurrencyValue value = this.mProduct.getValue();
            if (value.getValue() <= commerceValue.getValue() || commerceValue.getValue() <= 0.0d) {
                this.mDiscountBannerText.setVisibility(8);
            } else {
                double divide = value.subtract(commerceValue).divide(value) * 100.0d;
                if (Math.floor(divide) > 0.0d) {
                    this.mDiscountBannerText.setVisibility(0);
                    this.mDiscountBannerText.setText(String.format("-%1$.0f%%", new Object[]{Double.valueOf(Math.floor(divide))}));
                } else {
                    this.mDiscountBannerText.setVisibility(8);
                }
            }
            if (this.mProduct.getAuthorizedBrand() == null || this.mProduct.getAuthorizedBrand().getLogoUrl() == null) {
                this.mBrandLogo.setVisibility(8);
            } else {
                this.mBrandLogo.setVisibility(0);
                post(new Runnable() {
                    public void run() {
                        WishBrand authorizedBrand = ProductFeedTileView.this.mProduct.getAuthorizedBrand();
                        if (authorizedBrand != null && authorizedBrand.getLogoUrl() != null) {
                            int dimensionPixelSize = ProductFeedTileView.this.getResources().getDimensionPixelSize(R.dimen.product_feed_tile_branded_logo_height);
                            int logoAspectRatio = (int) (((double) dimensionPixelSize) * authorizedBrand.getLogoAspectRatio());
                            int width = (ProductFeedTileView.this.getWidth() - ProductFeedTileView.this.mDiscountBannerText.getWidth()) - ProductFeedTileView.this.getResources().getDimensionPixelSize(R.dimen.screen_padding);
                            if (logoAspectRatio > width) {
                                dimensionPixelSize = (int) (((double) width) / authorizedBrand.getLogoAspectRatio());
                                logoAspectRatio = width;
                            }
                            LayoutParams layoutParams = new LayoutParams(logoAspectRatio, dimensionPixelSize);
                            layoutParams.addRule(11, -1);
                            layoutParams.addRule(6, ProductFeedTileView.this.mProductImage.getId());
                            ProductFeedTileView.this.mBrandLogo.setLayoutParams(layoutParams);
                            ProductFeedTileView.this.mBrandLogo.setImageUrl(authorizedBrand.getLogoUrl());
                        }
                    }
                });
            }
            this.mPriceLayout.setVisibility(0);
            if (commerceValue.getValue() > 0.0d) {
                this.mPriceLayoutMainText.setText(commerceValue.toFormattedString());
            } else {
                this.mPriceLayoutMainText.setText(getContext().getString(R.string.free));
            }
            if (value.getValue() > commerceValue.getValue()) {
                this.mPriceLayoutSubText.setVisibility(0);
                if (value.getValue() > 0.0d) {
                    this.mPriceLayoutSubText.setText(value.toFormattedString());
                } else {
                    this.mPriceLayoutSubText.setText(getContext().getString(R.string.free));
                }
            } else {
                this.mPriceLayoutSubText.setVisibility(8);
            }
            TreeMap treeMap = new TreeMap(Collections.reverseOrder());
            showProductBadgesIfNecessary(treeMap);
            showProductBoostLabelIfNecessary(treeMap);
            if (!treeMap.isEmpty()) {
                this.mBadgeList.removeAllViews();
                Iterator it = treeMap.values().iterator();
                for (int i = 0; i < 2 && it.hasNext(); i++) {
                    this.mBadgeList.addView((View) it.next());
                }
            }
            if (this.mProduct.getNumPurchasedText() != null) {
                this.mNumPurchasedText.setVisibility(0);
                this.mNumPurchasedText.setText(this.mProduct.getNumPurchasedText());
            } else {
                this.mNumPurchasedText.setVisibility(4);
            }
            if (this.mProduct.getIsWishlistNewItem()) {
                this.mWishlistNewItemBadge.setVisibility(0);
            } else {
                this.mWishlistNewItemBadge.setVisibility(8);
            }
        } else {
            this.mPriceLayout.setVisibility(8);
            this.mDiscountBannerText.setVisibility(8);
            this.mDiscountBannerText.clearAnimation();
        }
    }

    private void initializeVideoPlayerIfNeeded() {
        if (this.mVideoPlayerView == null) {
            this.mVideoPlayerView = new VideoPlayerView(getContext());
            this.mVideoPlayerView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
            this.mVideoPlayerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.mVideoPlayerContainer.addView(this.mVideoPlayerView);
        }
    }

    public void releaseImages() {
        if (this.mProductImage != null) {
            this.mProductImage.releaseImages();
        }
        if (this.mBrandLogo != null) {
            this.mBrandLogo.releaseImages();
        }
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.setPlayer(null);
            VideoManager.getInstance().releaseVideoPlayer(this.mProduct.getProductId());
        }
    }

    public void restoreImages() {
        if (this.mProductImage != null) {
            this.mProductImage.restoreImages();
        }
        if (this.mBrandLogo != null) {
            this.mBrandLogo.restoreImages();
        }
        refreshView();
        refreshVideoState();
    }

    public void recycle() {
        if (this.mProductImage != null) {
            this.mProductImage.recycle();
        }
        if (this.mBrandLogo != null) {
            this.mBrandLogo.recycle();
        }
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.setPlayer(null);
            VideoManager.getInstance().releaseVideoPlayer(this.mProduct.getProductId());
        }
    }

    public void destroy() {
        if (this.mVideoPlayerView != null) {
            this.mVideoPlayerView.destroy();
        }
        VideoManager.getInstance().releaseVideoPlayer(this.mProduct.getProductId());
    }

    private void showProductBadgesIfNecessary(TreeMap<Integer, View> treeMap) {
        ArrayList productBadges = this.mProduct.getProductBadges();
        if (productBadges != null) {
            this.mBadgeList.removeAllViews();
            for (int i = 0; i < productBadges.size(); i++) {
                WishProductBadge wishProductBadge = (WishProductBadge) productBadges.get(i);
                AutoReleasableImageView autoReleasableImageView = new AutoReleasableImageView(getContext());
                autoReleasableImageView.setImageResource(wishProductBadge.getBadgeIcon());
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.product_badge_image_feed_side_length);
                autoReleasableImageView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
                treeMap.put(Integer.valueOf(wishProductBadge.getPriority()), autoReleasableImageView);
                if (treeMap.size() > 2) {
                    treeMap.pollFirstEntry();
                }
            }
        }
    }

    public void showProductBoostLabelIfNecessary(TreeMap<Integer, View> treeMap) {
        WishProductBoostFeedTileLabelSpec productBoostFeedTileLabelSpec = this.mProduct.getProductBoostFeedTileLabelSpec();
        if (productBoostFeedTileLabelSpec == null || productBoostFeedTileLabelSpec.getLabelType() == LabelType.NONE || productBoostFeedTileLabelSpec.getLabelText() == null || productBoostFeedTileLabelSpec.getLabelText().isEmpty()) {
            this.mProductBoostBannerText.setVisibility(8);
        } else if (productBoostFeedTileLabelSpec.getLabelType() == LabelType.BADGE) {
            this.mProductBoostBannerText.setVisibility(8);
            treeMap.put(Integer.valueOf(1), new AdProductBadgeView(getContext()));
            if (treeMap.size() > 2) {
                treeMap.pollFirstEntry();
            }
        } else if (productBoostFeedTileLabelSpec.getLabelType() == LabelType.BANNER) {
            this.mProductBoostBannerText.setText(productBoostFeedTileLabelSpec.getLabelText());
            this.mProductBoostBannerText.setVisibility(0);
            this.mDiscountBannerText.setVisibility(8);
        }
    }

    public void refreshView() {
        this.mProductImage.setVisibility(0);
        this.mPriceLayout.setAlpha(1.0f);
        this.mUrgencyBannerText.setAlpha(1.0f);
        this.mDiscountBannerText.setAlpha(1.0f);
        this.mBrandLogo.setAlpha(1.0f);
    }

    public void hideNumPurchasedTextView() {
        if (this.mNumPurchasedText != null) {
            this.mNumPurchasedText.setVisibility(8);
        }
    }

    public void initializeProductDetailsContainer() {
        this.mProductDetailsViewStub.setVisibility(0);
        this.mNumericalRatingTextView = (TextView) findViewById(R.id.ratings_numerical_text_view);
        this.mTopShippingCost = (TextView) findViewById(R.id.top_shipping_cost_text_view);
        this.mTopShippingDeliveryTime = (TextView) findViewById(R.id.top_shipping_delivery_estimate_text_view);
        this.mBottomShippingCost = (TextView) findViewById(R.id.bottom_shipping_cost_text_view);
        this.mBottomShippingDeliveryTime = (TextView) findViewById(R.id.bottom_shipping_delivery_estimate_text_view);
    }

    public void setUpStarsAndNumberOfRatings() {
        ((RedesignedBlueStarRatingView) findViewById(R.id.product_details_blue_star_rating_view)).setup(this.mProduct.getProductRating(), Size.SMALL, null);
        SpannableString spannableString = new SpannableString(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(this.mProduct.getProductRating())}));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_primary)), 0, spannableString.length(), 18);
        SpannableString spannableString2 = new SpannableString(String.format(Locale.getDefault(), " / %,d ratings", new Object[]{Integer.valueOf(this.mProduct.getProductRatingCount())}));
        spannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray2)), 0, spannableString2.length(), 18);
        this.mNumericalRatingTextView.setText(TextUtils.concat(new CharSequence[]{spannableString, spannableString2}));
    }

    public void showShippingDetails() {
        WishShippingOption wishShippingOption;
        WishShippingOption wishShippingOption2;
        String str;
        String str2;
        if (this.mProduct.getDefaultStandardShippingOption() != null) {
            wishShippingOption = this.mProduct.getDefaultStandardShippingOption();
        } else {
            wishShippingOption = this.mProduct.getDefaultExpressShippingOption();
        }
        if (wishShippingOption == this.mProduct.getDefaultExpressShippingOption()) {
            wishShippingOption2 = null;
        } else {
            wishShippingOption2 = this.mProduct.getDefaultExpressShippingOption();
        }
        this.mTopShippingCost.setText(String.format("%1$s : %2$s", new Object[]{wishShippingOption.getName(), wishShippingOption.getPrice().toFormattedString()}));
        this.mTopShippingDeliveryTime.setText(wishShippingOption.getShippingTimeString());
        if (wishShippingOption2 == null) {
            str = "";
        } else {
            str = String.format("%1$s : %2$s", new Object[]{wishShippingOption2.getName(), wishShippingOption2.getPrice().toFormattedString()});
        }
        this.mBottomShippingCost.setText(str);
        if (wishShippingOption2 == null) {
            str2 = "";
        } else {
            str2 = wishShippingOption2.getShippingTimeString();
        }
        this.mBottomShippingDeliveryTime.setText(str2);
    }

    public void showProductDetails() {
        initializeProductDetailsContainer();
        if (this.mProduct != null) {
            setUpStarsAndNumberOfRatings();
            showShippingDetails();
        }
    }

    public void hideBottomShipping() {
        if (this.mBottomShippingCost != null && this.mBottomShippingDeliveryTime != null) {
            this.mBottomShippingDeliveryTime.setVisibility(8);
            this.mBottomShippingCost.setVisibility(8);
        }
    }

    public void showBottomShipping() {
        if (this.mBottomShippingCost != null && this.mBottomShippingDeliveryTime != null) {
            this.mBottomShippingDeliveryTime.setVisibility(0);
            this.mBottomShippingCost.setVisibility(0);
        }
    }
}
