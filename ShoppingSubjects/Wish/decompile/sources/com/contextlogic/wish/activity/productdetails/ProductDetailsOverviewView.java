package com.contextlogic.wish.activity.productdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.groupbuylearnmore.GroupBuyLearnMoreActivity;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponBannerView;
import com.contextlogic.wish.activity.pricechop.PriceChopDetailView;
import com.contextlogic.wish.activity.productdetails.ProductDetailsCapsuleButton.ButtonType;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.PriceWatchCallback;
import com.contextlogic.wish.activity.productdetails.ProductDetailsMainPhotoAdapter.PhotoAdapterCallback;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView.BuyCallback;
import com.contextlogic.wish.activity.productdetails.relateditemsrow.ProductDetailsRelatedItemsRow;
import com.contextlogic.wish.activity.profile.wishlist.SelectWishlistDialogFragment;
import com.contextlogic.wish.activity.textviewer.TextViewerActivity;
import com.contextlogic.wish.activity.wishpartner.WishPartnerDetailView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.api.model.WishPromotionProductDetailsStripeSpec;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSizeSummaryBar;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.WishTooltip;
import com.contextlogic.wish.dialog.WishTooltip.WishTooltipListener;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.popupanimation.itemadded.ItemAddedToWishlistDialogFragment;
import com.contextlogic.wish.payments.processing.KlarnaPaymentProcessor;
import com.contextlogic.wish.ui.badge.ProductBadgeRow;
import com.contextlogic.wish.ui.image.ContainerRestorable;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView.ScrollViewListener;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.text.WishFontSpan;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.TabletUtil;
import com.contextlogic.wish.util.ValueUtil;
import com.google.android.flexbox.FlexboxLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ProductDetailsOverviewView extends ProductDetailsPagerView implements PriceWatchCallback, PhotoAdapterCallback, ScrollViewListener {
    private boolean mAddedRelatedExpressRow;
    private boolean mAddedVisuallySimilarProducts;
    private View mBannerView;
    private BuyerGuaranteeCollapsableSection mBuyerGuaranteeCollapsableSection;
    private BuyerGuaranteeView mBuyerGuaranteeView;
    /* access modifiers changed from: private */
    public LinearLayout mContentContainer;
    private DescriptionCollapsableSection mDescriptionCollapsableSection;
    private LinearLayout mDiscountStripesContainer;
    /* access modifiers changed from: private */
    public LinearLayout mGroupBuyContainer;
    /* access modifiers changed from: private */
    public View mGroupBuyDivider;
    /* access modifiers changed from: private */
    public ThemedTextView mGroupBuyLearnMore;
    /* access modifiers changed from: private */
    public ThemedTextView mGroupBuyTitle;
    /* access modifiers changed from: private */
    public GroupBuyView mGroupBuyView;
    /* access modifiers changed from: private */
    public boolean mImageScrollTracked;
    private ArrayList<NetworkImageView> mImageViews;
    private InviteCouponBannerView mInviteCouponBannerView;
    private ItemSpecificationCollapsableSection mItemSpecificationCollapsableSection;
    /* access modifiers changed from: private */
    public NetworkImageView mKlarnaPaymentMethodImage;
    private MerchantInfoView mMerchantInfoView;
    /* access modifiers changed from: private */
    public View mMessengerShareButton;
    private TextSwitcher mNumUsersView;
    /* access modifiers changed from: private */
    public ProductDetailsMainPhotoAdapter mPhotoAdapter;
    /* access modifiers changed from: private */
    public PriceChopDetailView mPriceChopDetailView;
    /* access modifiers changed from: private */
    public View mPriceWatchButton;
    private ImageView mPriceWatchImage;
    private ViewPager mProductImageViewpager;
    private RedesignedBlueStarRatingView mProductRatingStarRatingView;
    private TextView mProductRatingText;
    private View mProductRatingView;
    private View mProgressBar;
    private LinearLayout mRecentRatingsContainer;
    private View mRecentRatingsHeader;
    private TextView mRecentRatingsText;
    private View mRecentReviewsHolder;
    private ProductDetailsRecentVideosAdapter mRecentVideosAdapter;
    private View mRecentVideosHeader;
    private View mRecentVideosHolder;
    private HorizontalListView mRecentVideosHorizontalListView;
    private ThemedTextView mRecentVideosViewAll;
    private LinearLayout mRelatedRowsContainer;
    /* access modifiers changed from: private */
    public ObservableScrollView mScroller;
    private HashSet<Integer> mSeenUserImages;
    private View mShareButton;
    private ShippingCollapsableSection mShippingCollapsableSection;
    private TextView mShippingOfferText;
    private TextView mShippingOfferTitle;
    private View mShippingOfferView;
    private boolean mShouldShowProductDetailTransition;
    private LinearLayout mSizingBarNameContainer;
    private LinearLayout mSizingBarRatioContainer;
    private LinearLayout mSizingContainer;
    private View mSizingHeader;
    private TextView mSizingSubtitle;
    private TextView mTitleView;
    private TextView mUploadedByText;
    private NetworkImageView mUploaderImage;
    private View mUploaderLayout;
    private TextView mUploaderSizeText;
    private TextView mUploaderText;
    /* access modifiers changed from: private */
    public boolean mUserPriceWatching;
    private TextView mViewMoreRatingsText;
    /* access modifiers changed from: private */
    public View mWishButton;
    private ImageView mWishButtonImage;
    private ProgressBar mWishButtonSpinner;
    /* access modifiers changed from: private */
    public ImageView mWishCheckmark;
    private WishPartnerDetailView mWishPartnerDetailView;

    public ProductDetailsOverviewView(Context context) {
        super(context);
    }

    public static boolean isTabletInLandscape() {
        return DisplayUtil.isLandscape() && TabletUtil.isTablet();
    }

    public int getLoadingContentLayoutResourceId() {
        return isTabletInLandscape() ? R.layout.product_details_fragment_overview_tablet : R.layout.product_details_fragment_overview;
    }

    public void setup(final WishProduct wishProduct, int i, final ProductDetailsFragment productDetailsFragment, boolean z) {
        int i2;
        super.setup(wishProduct, i, productDetailsFragment);
        this.mShouldShowProductDetailTransition = ExperimentDataCenter.getInstance().shouldShowProductDetailTransition();
        this.mSeenUserImages = new HashSet<>();
        this.mScroller = (ObservableScrollView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_scroller);
        this.mScroller.setScrollViewListener(this);
        if (!isTabletInLandscape()) {
            setupScroller(this.mScroller);
        }
        this.mSpacerView = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_spacer_view);
        if (!this.mShouldShowProductDetailTransition) {
            updateSpacerHeight();
        }
        this.mNumUsersView = (TextSwitcher) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_current_viewers);
        setUpNumUsersView();
        this.mTitleView = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_title);
        ProductDetailsCapsuleButton productDetailsCapsuleButton = (ProductDetailsCapsuleButton) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_play_video);
        final ProductDetailsCapsuleButton productDetailsCapsuleButton2 = (ProductDetailsCapsuleButton) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_image_index);
        if (this.mShouldShowProductDetailTransition) {
            ((LayoutParams) productDetailsCapsuleButton.getLayoutParams()).gravity = 8388691;
            ((LayoutParams) productDetailsCapsuleButton2.getLayoutParams()).gravity = 8388691;
        }
        if (this.mProduct.getVideoInfo() != null) {
            productDetailsCapsuleButton.setVisibility(0);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            this.mProgressBar = this.mRootLayout.findViewById(R.id.product_details_three_dot_progress_bar);
        } else {
            this.mProgressBar = this.mRootLayout.findViewById(R.id.product_details_progress_bar);
        }
        this.mContentContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_content_container);
        this.mProgressBar.setVisibility(productDetailsFragment.isLoading() ? 0 : 8);
        this.mContentContainer.setVisibility((productDetailsFragment.isLoading() || productDetailsFragment.hasError()) ? 8 : 0);
        ViewGroup viewGroup = (ViewGroup) this.mRootLayout.findViewById(R.id.product_details_loading_error_view);
        viewGroup.setVisibility(productDetailsFragment.hasError() ? 0 : 8);
        if (productDetailsFragment.hasError()) {
            ((TextView) viewGroup.findViewById(R.id.product_details_try_again_button)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsOverviewView.this.mFragment.handleReload();
                }
            });
        }
        this.mWishCheckmark = (ImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_wish_checkmark);
        this.mUserPriceWatching = this.mFragment.getLoadedProduct() != null ? this.mFragment.getLoadedProduct().isUserWatchingPrice() : false;
        this.mPriceWatchButton = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_price_watch);
        this.mPriceWatchImage = (ImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_price_watch_button_image);
        if (isPriceWatchEligible()) {
            this.mPriceWatchButton.setVisibility(0);
            this.mFragment.addPriceWatchListener(this);
            this.mPriceWatchButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (ProductDetailsOverviewView.this.mFragment.getLoadedProduct() == null || ProductDetailsOverviewView.this.mUserPriceWatching) {
                        ProductDetailsOverviewView.this.removeFromPriceWatch();
                    } else if (!PreferenceUtil.getBoolean("SeenPriceWatchTooltipTwice")) {
                        ProductDetailsOverviewView.this.showPriceWatchInstructions();
                    } else {
                        ProductDetailsOverviewView.this.addToPriceWatch();
                    }
                }
            });
        } else {
            this.mPriceWatchButton.setVisibility(8);
        }
        this.mShareButton = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_share_button);
        this.mShareButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                productDetailsFragment.handleRecommendClick(wishProduct);
            }
        });
        this.mMessengerShareButton = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_messenger_share_button);
        this.mMessengerShareButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                productDetailsFragment.handleMessengerShareClick();
            }
        });
        this.mWishButton = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_save_to_wishlist_button);
        this.mWishButtonImage = (ImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_save_to_wishlist_button_image);
        this.mWishButtonSpinner = (ProgressBar) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_save_to_wishlist_button_progress_spinner);
        this.mWishButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductDetailsOverviewView.this.addToWishlist();
            }
        });
        this.mRecentVideosHolder = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_recent_videos_holder);
        this.mRecentReviewsHolder = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_recent_reviews_holder);
        this.mUploaderLayout = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_uploader_layout);
        this.mUploaderImage = (NetworkImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_uploader_image);
        this.mUploaderImage.setCircleCrop(true);
        this.mUploaderText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_uploader_text);
        this.mUploadedByText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_uploaded_by_text);
        this.mUploaderSizeText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_photo_size_text);
        this.mImageViews = new ArrayList<>();
        setupKlarna();
        this.mProductImageViewpager = (ViewPager) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_image_viewpager);
        if (this.mProduct.getVideoInfo() != null) {
            this.mProductImageViewpager.setOffscreenPageLimit(3);
        }
        if (this.mShouldShowProductDetailTransition) {
            if (isTabletInLandscape()) {
                i2 = getImageHeightForProduct(wishProduct, productDetailsFragment.getActivity(), productDetailsFragment.isOfferViewShowing());
            } else {
                i2 = DisplayUtil.getDisplayWidth();
            }
            ViewGroup.LayoutParams layoutParams = this.mProductImageViewpager.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i2;
        } else {
            int imageHeightForProduct = getImageHeightForProduct(wishProduct, productDetailsFragment.getActivity(), productDetailsFragment.isOfferViewShowing());
            this.mProductImageViewpager.setLayoutParams(new LayoutParams(isTabletInLandscape() ? imageHeightForProduct : -1, imageHeightForProduct));
        }
        this.mPhotoAdapter = new ProductDetailsMainPhotoAdapter(productDetailsFragment, this, wishProduct);
        this.mProductImageViewpager.setAdapter(this.mPhotoAdapter);
        this.mProductImageViewpager.clearOnPageChangeListeners();
        this.mProductImageViewpager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (i != 0 && !ProductDetailsOverviewView.this.mImageScrollTracked) {
                    ProductDetailsOverviewView.this.mImageScrollTracked = true;
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SCROLL_MAIN_PRODUCT_IMAGE, ProductDetailsOverviewView.this.mProduct.getProductId());
                }
                ProductDetailsOverviewView.this.mFragment.setOverviewPhotoIndex(i);
                productDetailsCapsuleButton2.setSecondText(String.format(WishApplication.getInstance().getString(R.string.add_to_cart_modal_item_number), new Object[]{Integer.valueOf(i + 1), Integer.valueOf(ProductDetailsOverviewView.this.mPhotoAdapter.getCount())}));
                ProductDetailsOverviewView.this.mPhotoAdapter.handlePageSelected(i);
                ProductDetailsOverviewView.this.logUgcImpressions();
                ProductDetailsOverviewView.this.refreshImageUploader();
            }
        });
        this.mProductImageViewpager.setCurrentItem(this.mFragment.getOverviewPhotoIndex());
        refreshImageUploader();
        productDetailsCapsuleButton.setProduct(wishProduct, ButtonType.PlayVideo);
        productDetailsCapsuleButton.setFragment(this.mFragment);
        productDetailsCapsuleButton2.setProduct(wishProduct, ButtonType.ImageIndex);
        productDetailsCapsuleButton2.setFragment(this.mFragment);
        productDetailsCapsuleButton2.setSecondText(String.format(WishApplication.getInstance().getString(R.string.add_to_cart_modal_item_number), new Object[]{Integer.valueOf(this.mFragment.getOverviewPhotoIndex() + 1), Integer.valueOf(this.mPhotoAdapter.getCount())}));
        productDetailsCapsuleButton.setVisibility(8);
        productDetailsCapsuleButton2.setVisibility(8);
        ProductDetailsCapsuleButton productDetailsCapsuleButton3 = (ProductDetailsCapsuleButton) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_photo_video_count);
        productDetailsCapsuleButton3.setFragment(this.mFragment);
        productDetailsCapsuleButton3.setVisibility(0);
        productDetailsCapsuleButton3.setProduct(wishProduct, ButtonType.PhotoVideoCount, this.mPhotoAdapter.getPhotoCount(), this.mPhotoAdapter.getVideoCount());
        if (this.mShouldShowProductDetailTransition) {
            ((LayoutParams) productDetailsCapsuleButton3.getLayoutParams()).gravity = 8388691;
        }
        if (!z && !this.mAddedRelatedExpressRow && this.mFragment.shouldLoadOverviewExpressItems()) {
            this.mFragment.loadRelatedExpressShippingItems();
        } else if (!z && !this.mAddedVisuallySimilarProducts && this.mFragment.shouldLoadVisuallySimilarItems()) {
            this.mFragment.loadVisuallySimilarItems();
        }
        if (!z && (ExperimentDataCenter.getInstance().shouldSeeCollapsableBuyerGuarantee() || ExperimentDataCenter.getInstance().shouldSeeFullSectionBuyerGuarantee())) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadBuyerGuaranteeInfo();
                }
            });
        }
        if (!z && ExperimentDataCenter.getInstance().shouldSeeRelatedPbRow() && wishProduct != null && !TextUtils.isEmpty(wishProduct.getProductId())) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadRelatedProductBoostProducts(0, wishProduct.getProductId(), 0, 30);
                }
            });
        }
        this.mTitleView.setText(this.mProduct.getName());
        setupBadges(this.mProduct.getProductBadges());
        if (this.mProduct.isCommerceProduct()) {
            setupCommerceItems();
        }
        setupRecentVideos();
        if (this.mProduct.getUsersCurrentlyViewingInfo() != null) {
            refreshUsersViewingText(this.mProduct.getUsersCurrentlyViewingInfo().getMessage());
        }
        if (this.mFragment.getSavedInstanceState(i) != null) {
            restorePosition(this.mFragment.getSavedInstanceState(i).getInt("SavedStateFirstItemPosition"));
        }
        this.mRelatedRowsContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_related_rows_container);
        this.mBuyerGuaranteeCollapsableSection = (BuyerGuaranteeCollapsableSection) this.mRootLayout.findViewById(R.id.product_details_fragment_buyer_guarantee_section);
        this.mBuyerGuaranteeView = (BuyerGuaranteeView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_buyer_guarantee_view);
        this.mPriceChopDetailView = (PriceChopDetailView) this.mRootLayout.findViewById(R.id.product_details_fragment_price_chop_detail_view);
        handlePriceChopDetailLoaded(wishProduct.getProductId(), wishProduct.getPriceChopProductDetail());
        handleDividers();
        if (!TextUtils.isEmpty(this.mProduct.getWishlistTooltipText()) && !PreferenceUtil.getBoolean("HideDetailsWishlistTooltip")) {
            showWishlistTooltip(this.mProduct.getWishlistTooltipText());
        } else if (isPriceWatchEligible() && !PreferenceUtil.getBoolean("SeenPriceWatchTooltipTwice")) {
            showPriceWatchTooltip(WishApplication.getInstance().getResources().getString(R.string.price_watch_tooltip_text));
        }
        if (ExperimentDataCenter.getInstance().shouldSeeMessengerShareButtonInProductDetails()) {
            this.mMessengerShareButton.setVisibility(0);
            if (!PreferenceUtil.getBoolean("HideMessengerShareTooltip")) {
                showMessengerTooltip();
            }
            if (ExperimentDataCenter.getInstance().shouldReplaceShareButtonInProductDetails()) {
                this.mShareButton.setVisibility(8);
            }
        }
    }

    private void showPriceWatchTooltip(final String str) {
        if (this.mPriceWatchButton != null) {
            if (PreferenceUtil.getBoolean("SeenPriceWatchTooltipOnce")) {
                PreferenceUtil.setBoolean("SeenPriceWatchTooltipTwice", true);
            } else {
                PreferenceUtil.setBoolean("SeenPriceWatchTooltipOnce", true);
            }
            this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    WishTooltip.make(str, 2).setTargetViewOverlay(WishTooltip.createSimpleCircleOverlay(ProductDetailsOverviewView.this.getContext())).setCallback(new WishTooltipListener() {
                        public void clickedOutsideTooltip() {
                        }

                        public void clickedTooltip() {
                            ProductDetailsOverviewView.this.mPriceWatchButton.performClick();
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_TOOLTIP);
                        }
                    }).showWhenReady(productDetailsActivity, ProductDetailsOverviewView.this.mPriceWatchButton);
                }
            });
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRICE_WATCH_TOOLTIP);
        }
    }

    private void showWishlistTooltip(final String str) {
        if (this.mWishButton != null) {
            this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    WishTooltip.make(str, 2).setTargetViewOverlay(WishTooltip.createSimpleCircleOverlay(ProductDetailsOverviewView.this.getContext())).setCallback(new WishTooltipListener() {
                        public void clickedOutsideTooltip() {
                        }

                        public void clickedTooltip() {
                            ProductDetailsOverviewView.this.mWishButton.performClick();
                            PreferenceUtil.setBoolean("HideDetailsWishlistTooltip", true);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NEW_USER_GIFT_PACK_WISHLIST_TOOLTIP);
                        }
                    }).showWhenReady(productDetailsActivity, ProductDetailsOverviewView.this.mWishButton);
                }
            });
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NEW_USER_GIFT_PACK_WISHLIST_TOOLTIP);
        }
    }

    private void showMessengerTooltip() {
        if (this.mMessengerShareButton != null) {
            this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    PreferenceUtil.setBoolean("HideMessengerShareTooltip", true);
                    WishTooltip.make(productDetailsActivity.getString(R.string.share_on_messenger), 1).setTargetViewOverlay(WishTooltip.createSimpleCircleOverlay(ProductDetailsOverviewView.this.getContext())).setCallback(new WishTooltipListener() {
                        public void clickedOutsideTooltip() {
                        }

                        public void clickedTooltip() {
                            ProductDetailsOverviewView.this.mMessengerShareButton.performClick();
                        }
                    }).showWhenReady(productDetailsActivity, ProductDetailsOverviewView.this.mMessengerShareButton);
                }
            });
        }
    }

    public int getFirstItemPosition() {
        if (this.mScroller.getChildCount() > 0) {
            LinearLayout linearLayout = (LinearLayout) this.mScroller.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (linearLayout.getChildAt(i).getBottom() > this.mScroller.getScrollY()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void restorePosition(final int i) {
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                View childAt = ((LinearLayout) ProductDetailsOverviewView.this.mScroller.getChildAt(0)).getChildAt(i);
                if (childAt != null) {
                    ProductDetailsOverviewView.this.mScroller.scrollTo(0, childAt.getTop());
                }
                ProductDetailsOverviewView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void setupRecentVideos() {
        SparseArray sparseArray = new SparseArray();
        int i = this.mProduct.getVideoInfo() != null ? 2 : 1;
        for (int i2 = 0; i2 < this.mProduct.getExtraPhotos().size(); i2++) {
            if (((WishProductExtraImage) this.mProduct.getExtraPhotos().get(i2)).getSourceType() == SourceType.Video && ((WishProductExtraImage) this.mProduct.getExtraPhotos().get(i2)).isUgc()) {
                sparseArray.put(i2 + i, this.mProduct.getExtraPhotos().get(i2));
            }
        }
        this.mRecentVideosHeader = findViewById(R.id.product_details_fragment_overview_recent_videos_header);
        this.mRecentVideosViewAll = (ThemedTextView) findViewById(R.id.product_details_fragment_overview_recent_videos_view_all_text);
        this.mRecentVideosHorizontalListView = (HorizontalListView) findViewById(R.id.product_details_fragment_overview_recent_videos_horizontal_list_view);
        this.mRecentVideosAdapter = new ProductDetailsRecentVideosAdapter(getContext(), this.mFragment);
        if (sparseArray.size() > 0) {
            this.mRecentVideosAdapter.setExtraVideos(sparseArray);
            this.mRecentVideosHorizontalListView.setAdapter(this.mRecentVideosAdapter);
            this.mRecentVideosHeader.setVisibility(0);
            this.mRecentVideosHorizontalListView.setVisibility(0);
            this.mRecentVideosHolder.setVisibility(0);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_RECENT_VIDEOS, this.mProduct.getProductId());
            this.mRecentVideosViewAll.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENT_VIDEO_VIEW_ALL, ProductDetailsOverviewView.this.mProduct.getProductId());
                    ProductDetailsOverviewView.this.mFragment.showProductExtraPhotos(2);
                }
            });
            return;
        }
        this.mRecentVideosHeader.setVisibility(8);
        this.mRecentVideosHorizontalListView.setVisibility(8);
        this.mRecentVideosHolder.setVisibility(8);
    }

    private void setUpNumUsersView() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 2.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -2.0f, 1, 0.0f);
        translateAnimation2.setDuration(400);
        translateAnimation2.setFillAfter(true);
        this.mNumUsersView.setOutAnimation(translateAnimation);
        this.mNumUsersView.setInAnimation(translateAnimation2);
        this.mNumUsersView.setFactory(new ViewFactory() {
            public View makeView() {
                ThemedTextView themedTextView = new ThemedTextView(ProductDetailsOverviewView.this.getContext());
                themedTextView.setLayoutParams(new LayoutParams(-1, -2));
                themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.red));
                themedTextView.setTextSize(ValueUtil.convertPxToDp((float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body)));
                return themedTextView;
            }
        });
    }

    public static int getImageHeightForProduct(WishProduct wishProduct, Activity activity, boolean z) {
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        double d = (double) width;
        int aspectRatio = (int) (d / (wishProduct.getVideoInfo() != null ? wishProduct.getVideoInfo().getAspectRatio() : wishProduct.getAspectRatio()));
        int originalImageHeight = (int) (wishProduct.getOriginalImageHeight() * ((double) displayMetrics.density));
        int max = Math.max(width / 2, originalImageHeight);
        if (originalImageHeight == -1 || max >= aspectRatio) {
            max = aspectRatio;
        }
        int dimensionPixelOffset = activity.getResources().getDimensionPixelOffset(R.dimen.product_details_fragment_image_height_subtract);
        if (z) {
            dimensionPixelOffset += activity.getResources().getDimensionPixelOffset(R.dimen.add_to_cart_offer_container_height);
        }
        if (!isTabletInLandscape()) {
            dimensionPixelOffset += activity.getResources().getDimensionPixelOffset(R.dimen.product_details_fragment_image_height_subtract_additional_offset);
        }
        int min = Math.min(defaultDisplay.getHeight() - dimensionPixelOffset, max);
        if (!isTabletInLandscape()) {
            return min;
        }
        double d2 = (double) min;
        double aspectRatio2 = (double) ((int) (wishProduct.getAspectRatio() * d2));
        double d3 = d * 0.5d;
        return aspectRatio2 >= d3 ? (int) (d2 * (d3 / aspectRatio2)) : min;
    }

    private void setupKlarna() {
        this.mKlarnaPaymentMethodImage = (NetworkImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_klarna_image);
        if (ConfigDataCenter.getInstance().getKlarnaCountryCode() != null) {
            this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    ProductDetailsOverviewView.this.mKlarnaPaymentMethodImage.setScaleType(ScaleType.FIT_CENTER);
                    int width = ((WindowManager) productDetailsActivity.getSystemService("window")).getDefaultDisplay().getWidth() - (productDetailsActivity.getResources().getDimensionPixelOffset(R.dimen.screen_padding) * 2);
                    ProductDetailsOverviewView.this.mKlarnaPaymentMethodImage.getLayoutParams().height = (int) (((double) width) * 0.19d);
                    ProductDetailsOverviewView.this.mKlarnaPaymentMethodImage.setImage(new WishImage(KlarnaPaymentProcessor.getPaymentMethodImageUrl(width)));
                }
            });
        } else {
            this.mKlarnaPaymentMethodImage.setVisibility(8);
        }
    }

    private void setupCommerceItems() {
        boolean z = this.mProduct.getTopRatings() != null && this.mProduct.getTopRatings().size() > 0 && this.mProduct.getProductRatingCount() > 0;
        boolean z2 = this.mProduct.getTopMerchantRatings() != null && this.mProduct.getTopMerchantRatings().size() > 0 && this.mProduct.getMerchantRatingCount() > 0 && !z;
        setupSellerInfoSectionIfNecessary();
        setupWishPartnerSectionIfNeeded();
        this.mRecentRatingsText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_reviews_text);
        this.mViewMoreRatingsText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_reviews_view_more_text);
        this.mRecentRatingsHeader = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_recent_reviews_header);
        this.mRecentRatingsContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_recent_reviews);
        if (z) {
            this.mRecentRatingsHeader.setVisibility(0);
            this.mRecentRatingsContainer.setVisibility(0);
            this.mRecentReviewsHolder.setVisibility(0);
            if (ExperimentDataCenter.getInstance().showTopReviews()) {
                this.mRecentRatingsText.setText(R.string.top_reviews);
                this.mViewMoreRatingsText.setVisibility(0);
                this.mViewMoreRatingsText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_DETAILS_VIEW_MORE_RATINGS);
                        ProductDetailsOverviewView.this.mFragment.showProductRatings();
                    }
                });
            } else if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState() && this.mFragment.getLoadedProduct() != null) {
                ArrayList topRatings = this.mFragment.getLoadedProduct().getTopRatings();
                if (topRatings != null && !topRatings.isEmpty() && ((WishRating) topRatings.get(0)).getAuthor().isWishStar()) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mRecentRatingsText.getLayoutParams();
                    layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.sixteen_padding);
                    this.mRecentRatingsText.setLayoutParams(layoutParams);
                }
            }
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_TOP_PRODUCT_REVIEWS);
            this.mRecentRatingsContainer.removeAllViews();
            int i = 0;
            while (i < this.mProduct.getTopRatings().size()) {
                addRatingRowToViewGroup((WishRating) this.mProduct.getTopRatings().get(i), this.mRecentRatingsContainer, i == this.mProduct.getTopRatings().size() - 1);
                i++;
            }
        } else if (z2) {
            ((TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_reviews_text)).setText(R.string.store_reviews);
            this.mRecentRatingsHeader.setVisibility(0);
            this.mRecentRatingsContainer.setVisibility(0);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_TOP_MERCHANT_REVIEWS);
            this.mRecentRatingsContainer.removeAllViews();
            int i2 = 0;
            while (i2 < this.mProduct.getTopMerchantRatings().size()) {
                addRatingRowToViewGroup((WishRating) this.mProduct.getTopMerchantRatings().get(i2), this.mRecentRatingsContainer, i2 == this.mProduct.getTopMerchantRatings().size() - 1);
                i2++;
            }
        } else {
            this.mRecentRatingsHeader.setVisibility(8);
            this.mRecentRatingsContainer.setVisibility(8);
            this.mRecentReviewsHolder.setVisibility(8);
        }
        this.mSizingHeader = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_sizing_header);
        this.mSizingContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_sizing_container);
        this.mSizingSubtitle = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_sizing_subtitle);
        this.mSizingBarNameContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_sizing_bar_name_container);
        this.mSizingBarRatioContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_sizing_bar_ratio_container);
        if (this.mProduct.getRatingSizeSummary() == null || this.mProduct.getRatingSizeSummary().getNumRatings() <= 0) {
            this.mSizingHeader.setVisibility(8);
            this.mSizingContainer.setVisibility(8);
            this.mSizingSubtitle.setVisibility(8);
        } else {
            this.mSizingHeader.setVisibility(0);
            this.mSizingContainer.setVisibility(0);
            this.mSizingBarNameContainer.removeAllViews();
            this.mSizingBarRatioContainer.removeAllViews();
            Iterator it = this.mProduct.getRatingSizeSummary().getSizeBars().iterator();
            while (it.hasNext()) {
                addSizingSummaryBar((WishRatingSizeSummaryBar) it.next());
            }
            this.mSizingSubtitle.setText(this.mProduct.getRatingSizeSummary().getSubtitle());
        }
        this.mItemSpecificationCollapsableSection = (ItemSpecificationCollapsableSection) this.mRootLayout.findViewById(R.id.product_details_fragment_item_specification_section);
        this.mItemSpecificationCollapsableSection.init(this.mProduct);
        this.mItemSpecificationCollapsableSection.setParentScrollView(this.mScroller);
        this.mDescriptionCollapsableSection = (DescriptionCollapsableSection) this.mRootLayout.findViewById(R.id.product_details_fragment_item_description_section);
        if (this.mProduct.getDescription() == null || this.mProduct.getDescription().equals("")) {
            this.mDescriptionCollapsableSection.setVisibility(8);
        } else {
            this.mDescriptionCollapsableSection.init(this.mProduct);
            this.mDescriptionCollapsableSection.setParentScrollView(this.mScroller);
        }
        this.mShippingCollapsableSection = (ShippingCollapsableSection) this.mRootLayout.findViewById(R.id.product_details_fragment_item_shipping_section);
        this.mShippingCollapsableSection.init(this.mProduct, this.mRootLayout.findViewById(R.id.divider7));
        this.mShippingCollapsableSection.setParentScrollView(this.mScroller);
        if (this.mFragment.getSource() == Source.POINTS_REDEMPTION) {
            this.mShippingCollapsableSection.hideShippingPrices();
        }
        Resources resources = getResources();
        String str = (this.mProduct.getVariationSizes() == null || this.mProduct.getVariationSizes().size() <= 0) ? (this.mProduct.getHiddenVariationSizes() == null || this.mProduct.getHiddenVariationSizes().size() <= 0) ? null : resources.getQuantityString(R.plurals.detail_table_size, this.mProduct.getHiddenVariationSizes().size()) : resources.getQuantityString(R.plurals.detail_table_size, this.mProduct.getVariationSizes().size());
        String str2 = (this.mProduct.getVariationColors() == null || this.mProduct.getVariationColors().size() <= 0) ? (this.mProduct.getHiddenVariationColors() == null || this.mProduct.getHiddenVariationColors().size() <= 0) ? null : resources.getQuantityString(R.plurals.detail_table_color, this.mProduct.getHiddenVariationColors().size()) : resources.getQuantityString(R.plurals.detail_table_color, this.mProduct.getVariationColors().size());
        if (str == null && str2 == null) {
            this.mItemSpecificationCollapsableSection.setVisibility(8);
        }
        this.mShippingOfferView = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_shipping_offer_view);
        this.mShippingOfferTitle = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_shipping_offer_title);
        this.mShippingOfferText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_shipping_offer_text);
        if (this.mProduct.getShippingOfferText() == null || this.mProduct.getShippingOfferTitle() == null) {
            this.mShippingOfferView.setVisibility(8);
        } else {
            this.mShippingOfferView.setVisibility(0);
            this.mShippingOfferTitle.setText(this.mProduct.getShippingOfferTitle());
            this.mShippingOfferText.setText(this.mProduct.getShippingOfferText());
            ((ImageView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_shipping_offer_image)).setImageResource(R.drawable.product_detail_shipping_offer_truck);
        }
        this.mProductRatingView = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_rating_container);
        this.mProductRatingText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_rating_text);
        this.mProductRatingStarRatingView = (RedesignedBlueStarRatingView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_rating_star_rating_view);
        this.mProductRatingText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_rating_text);
        if (z) {
            this.mProductRatingView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsOverviewView.this.mFragment.showProductRatings();
                }
            });
            double productRating = this.mProduct.getProductRating();
            this.mProductRatingText.setText(String.format("(%1$s)", new Object[]{NumberFormat.getInstance().format((long) this.mProduct.getProductRatingCount())}));
            this.mProductRatingStarRatingView.setup(productRating, Size.INTERMEDIATE, null);
            this.mProductRatingStarRatingView.setVisibility(0);
            this.mProductRatingText.setTextSize(1, 13.0f);
        } else if (z2) {
            this.mProductRatingView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsOverviewView.this.mFragment.showStoreRatings();
                }
            });
            this.mProductRatingStarRatingView.setup(this.mProduct.getMerchantRating(), Size.INTERMEDIATE, null);
            this.mProductRatingText.setText(this.mFragment.getResources().getQuantityString(R.plurals.store_ratings_with_bracket, this.mProduct.getMerchantRatingCount(), new Object[]{Integer.valueOf(this.mProduct.getMerchantRatingCount())}));
            this.mProductRatingText.setTextSize(1, 13.0f);
        } else {
            this.mProductRatingView.setVisibility(8);
        }
        this.mDiscountStripesContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_discount_stripes_holder);
        ArrayList promotionProductDetailsStripeSpecs = this.mFragment.getLoadedProduct().getPromotionProductDetailsStripeSpecs();
        if (promotionProductDetailsStripeSpecs != null && !promotionProductDetailsStripeSpecs.isEmpty()) {
            Iterator it2 = promotionProductDetailsStripeSpecs.iterator();
            while (it2.hasNext()) {
                WishPromotionProductDetailsStripeSpec wishPromotionProductDetailsStripeSpec = (WishPromotionProductDetailsStripeSpec) it2.next();
                DiscountStripeView discountStripeView = new DiscountStripeView(WishApplication.getInstance().getBaseContext());
                discountStripeView.setup(wishPromotionProductDetailsStripeSpec);
                this.mDiscountStripesContainer.addView(discountStripeView);
            }
        }
        if (this.mFragment.getSource() != Source.DAILY_GIVEAWAY && ExperimentDataCenter.getInstance().shouldSeeGroupBuy() && this.mProduct.getGroupBuyPrice() != null && this.mProduct.isInStock()) {
            this.mGroupBuyView = (GroupBuyView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_group_buy_view);
            this.mGroupBuyTitle = (ThemedTextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_group_buy_title);
            this.mGroupBuyLearnMore = (ThemedTextView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_group_buy_learn_more);
            this.mGroupBuyContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_group_buy_container);
            this.mGroupBuyDivider = this.mRootLayout.findViewById(R.id.divider0);
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadGroupBuys(ProductDetailsOverviewView.this.mFragment.getProductId(), false);
                }
            });
        }
        this.mInviteCouponBannerView = (InviteCouponBannerView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_invite_coupon_banner);
        View findViewById = this.mRootLayout.findViewById(R.id.product_details_fragment_overview_promotion_banner_container);
        ViewParent parent = findViewById.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            if (!(this.mFragment.getLoadedProduct().getPromotionSpec() == null || indexOfChild == -1)) {
                viewGroup.removeViewAt(indexOfChild);
                this.mBannerView = this.mFragment.getLoadedProduct().getPromotionSpec().getProductOverviewBannerView(this.mFragment, WishAnalyticsEvent.IMPRESSION_PROMO_BANNER_PRODUCT_DETAIL, WishAnalyticsEvent.CLICK_PROMO_BANNER_PRODUCT_DETAIL);
                if (this.mBannerView != null) {
                    viewGroup.addView(this.mBannerView, indexOfChild);
                }
            }
        }
        if (ExperimentDataCenter.getInstance().canSeeInviteCouponBanner() && (this.mBannerView == null || this.mBannerView.getVisibility() != 0)) {
            this.mInviteCouponBannerView.setVisibility(0);
        }
        refreshWishStates(false);
        if (ExperimentDataCenter.getInstance().shouldSeePriceWatch()) {
            refreshPriceWatchState();
        }
    }

    private void setupSellerInfoSectionIfNecessary() {
        if (this.mProduct.hasMerchantInfo()) {
            this.mMerchantInfoView = (MerchantInfoView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_merchant_info_view);
            this.mMerchantInfoView.setVisibility(0);
            this.mMerchantInfoView.setup(this.mFragment, this.mProduct);
        }
    }

    private void setupWishPartnerSectionIfNeeded() {
        if (this.mProduct.getWishPartnerInfo() != null) {
            this.mWishPartnerDetailView = (WishPartnerDetailView) this.mRootLayout.findViewById(R.id.product_details_fragment_overview_wish_partner_view);
            this.mWishPartnerDetailView.setVisibility(0);
            this.mWishPartnerDetailView.setup(this.mProduct, this.mFragment);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_PRODUCT_DETAILS_VIEW);
        }
    }

    private void addSizingSummaryBar(WishRatingSizeSummaryBar wishRatingSizeSummaryBar) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setText(wishRatingSizeSummaryBar.getName());
        themedTextView.setTextSize(1, 14.0f);
        themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray1));
        int convertDpToPx = (int) ValueUtil.convertDpToPx(25.0f);
        int convertDpToPx2 = (int) ValueUtil.convertDpToPx(5.0f);
        themedTextView.setPadding(0, 0, (int) ValueUtil.convertDpToPx(15.0f), 0);
        themedTextView.setGravity(51);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, convertDpToPx);
        layoutParams.bottomMargin = convertDpToPx2;
        themedTextView.setLayoutParams(layoutParams);
        this.mSizingBarNameContainer.addView(themedTextView);
        ProgressBar progressBar = new ProgressBar(getContext(), null, 16842872);
        progressBar.setProgressDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.product_details_sizing_progress_bar));
        int convertDpToPx3 = (int) ValueUtil.convertDpToPx(18.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, (int) ValueUtil.convertDpToPx(12.0f));
        layoutParams2.bottomMargin = convertDpToPx3;
        progressBar.setLayoutParams(layoutParams2);
        progressBar.setMax(100);
        progressBar.setProgress((int) (wishRatingSizeSummaryBar.getRatio() * 100.0d));
        this.mSizingBarRatioContainer.addView(progressBar);
    }

    /* access modifiers changed from: private */
    public void logUgcImpressions() {
        int currentItem = this.mProductImageViewpager.getCurrentItem();
        WishProductExtraImage media = this.mPhotoAdapter.getMedia(currentItem);
        if (!this.mSeenUserImages.contains(Integer.valueOf(currentItem))) {
            if (media.getRatingId() != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("rating_id", media.getRatingId());
                if (media.getSourceType() == SourceType.Video) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_DETAILS_UGC_VIDEO, this.mProduct.getProductId(), hashMap);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_DETAILS_UGC_IMAGE, this.mProduct.getProductId(), hashMap);
                }
            }
            this.mSeenUserImages.add(Integer.valueOf(currentItem));
        }
    }

    /* access modifiers changed from: private */
    public void refreshImageUploader() {
        WishProductExtraImage media = this.mPhotoAdapter.getMedia(this.mProductImageViewpager.getCurrentItem());
        if (media.getSourceType() != SourceType.Image || media.getUploader() == null || media.getUploader().getName() == null || media.getUploader().getProfileImage() == null || media.getUploader().getProfileImage().getUrlString(ImageSize.MEDIUM) == null) {
            hideUploaderLayout();
            return;
        }
        this.mUploaderLayout.setVisibility(0);
        this.mUploaderImage.setImage(media.getUploader().getProfileImage());
        this.mUploaderText.setText(media.getUploader().getName());
        if (media.getSize() != null) {
            this.mUploaderText.setVisibility(8);
            this.mUploadedByText.setText(media.getUploader().getName());
            this.mUploaderSizeText.setVisibility(0);
            this.mUploaderSizeText.setText(WishApplication.getInstance().getResources().getString(R.string.sizes_detail, new Object[]{media.getSize()}));
            return;
        }
        this.mUploadedByText.setText(WishApplication.getInstance().getResources().getString(R.string.uploaded_by));
        this.mUploaderSizeText.setVisibility(8);
        this.mUploaderText.setVisibility(0);
    }

    private void hideUploaderLayout() {
        this.mUploaderLayout.setVisibility(8);
        this.mUploaderImage.setImage(null);
        this.mUploaderText.setText(null);
    }

    public boolean isPriceWatchEligible() {
        Source source = this.mFragment.getSource();
        return ExperimentDataCenter.getInstance().shouldSeePriceWatch() && source != Source.DAILY_GIVEAWAY && source != Source.FREE_GIFT && source != Source.POINTS_REDEMPTION && !this.mFragment.isDealDashProduct() && !this.mFragment.getLoadedProduct().isDealDash();
    }

    public void onPriceWatchStateChanges(boolean z) {
        if (ExperimentDataCenter.getInstance().shouldSeePriceWatch()) {
            if (this.mFragment.getLoadedProduct() != null) {
                this.mUserPriceWatching = z;
            }
            refreshPriceWatchState();
        }
    }

    public void refreshPriceWatchState() {
        if (this.mFragment.getLoadedProduct() == null) {
            return;
        }
        if (this.mUserPriceWatching) {
            this.mPriceWatchImage.setImageResource(R.drawable.price_watch_icon_selected);
        } else {
            this.mPriceWatchImage.setImageResource(R.drawable.price_watch_icon_off);
        }
    }

    public void refreshWishStates(boolean z) {
        if (this.mProduct.isAlreadyWishing()) {
            this.mWishButton.setEnabled(true);
            this.mWishButtonImage.setVisibility(0);
            this.mWishButtonImage.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.save_to_wishlist_pencil));
            this.mWishButtonSpinner.setVisibility(8);
        } else if (this.mFragment.isWishPending(this.mProduct.getProductId())) {
            this.mWishButton.setEnabled(false);
            this.mWishButtonImage.setVisibility(8);
            this.mWishButtonSpinner.setVisibility(0);
        } else {
            this.mWishButton.setEnabled(true);
            this.mWishButtonImage.setVisibility(0);
            this.mWishButtonImage.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.save_to_wishlist_heart));
            this.mWishButtonSpinner.setVisibility(8);
        }
    }

    private void animateConfirmationDialog(final String str) {
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.startDialog(ItemAddedToWishlistDialogFragment.createItemAddedToWishListDialogFragment(str, ProductDetailsOverviewView.this.mProduct.getImage()));
            }
        });
    }

    private void animateWishCheckmark() {
        if (this.mWishCheckmark.getDrawable() == null) {
            this.mWishCheckmark.setImageResource(R.drawable.wishedcheck);
        }
        this.mWishCheckmark.clearAnimation();
        this.mWishCheckmark.setVisibility(0);
        ScaleAnimation scaleAnimation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(250);
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setStartOffset(750);
                alphaAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        ProductDetailsOverviewView.this.mWishCheckmark.setImageDrawable(null);
                        ProductDetailsOverviewView.this.mWishCheckmark.setVisibility(8);
                    }
                });
                ProductDetailsOverviewView.this.mWishCheckmark.startAnimation(alphaAnimation);
            }
        });
        this.mWishCheckmark.startAnimation(scaleAnimation);
    }

    public void cleanup() {
        releaseImages();
        if (this.mPhotoAdapter != null) {
            this.mPhotoAdapter.cleanup();
        }
    }

    public void releaseImages() {
        if (this.mPhotoAdapter != null) {
            this.mPhotoAdapter.releaseImages();
        }
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            NetworkImageView networkImageView = (NetworkImageView) it.next();
            if (networkImageView != null) {
                networkImageView.releaseImages();
            }
        }
        if (this.mMerchantInfoView != null) {
            this.mMerchantInfoView.releaseImages();
        }
        if (this.mBannerView != null && (this.mBannerView instanceof ImageRestorable)) {
            ((ImageRestorable) this.mBannerView).releaseImages();
        }
        ContainerRestorable.releaseChildren(this.mRelatedRowsContainer);
        ContainerRestorable.releaseChildren(this.mRecentRatingsContainer);
    }

    public void restoreImages() {
        if (this.mPhotoAdapter != null) {
            this.mPhotoAdapter.restoreImages();
        }
        Iterator it = this.mImageViews.iterator();
        while (it.hasNext()) {
            NetworkImageView networkImageView = (NetworkImageView) it.next();
            if (networkImageView != null) {
                networkImageView.restoreImages();
            }
        }
        if (this.mMerchantInfoView != null) {
            this.mMerchantInfoView.restoreImages();
        }
        if (this.mBannerView != null && (this.mBannerView instanceof ImageRestorable)) {
            ((ImageRestorable) this.mBannerView).restoreImages();
        }
        ContainerRestorable.restoreChildren(this.mRelatedRowsContainer);
        ContainerRestorable.restoreChildren(this.mRecentRatingsContainer);
    }

    public int getCurrentScrollY() {
        if (this.mScroller != null) {
            return this.mScroller.getScrollY();
        }
        return 0;
    }

    public void refreshUsersViewingText(String str) {
        if (this.mNumUsersView != null) {
            if (str == null || str.equals("")) {
                this.mNumUsersView.setVisibility(8);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("   ");
            sb.append(str);
            SpannableString spannableString = new SpannableString(sb.toString());
            TextView textView = null;
            if (this.mNumUsersView.getChildCount() > 0) {
                textView = (TextView) this.mNumUsersView.getChildAt(0);
            }
            if (textView != null) {
                int lineHeight = textView.getLineHeight();
                Drawable drawable = getResources().getDrawable(R.drawable.fire_icon);
                drawable.setBounds(0, 0, lineHeight, lineHeight);
                spannableString.setSpan(new ImageSpan(drawable), 0, 1, 33);
                if (this.mNumUsersView.getCurrentView() != null && !((ThemedTextView) this.mNumUsersView.getCurrentView()).getText().toString().equals(spannableString.toString())) {
                    this.mNumUsersView.setVisibility(0);
                    this.mNumUsersView.setText(spannableString);
                }
            }
        }
    }

    public void postDelayedTask(Runnable runnable, int i) {
        this.mTitleView.postDelayed(runnable, (long) i);
    }

    public void onScrollChanged(int i, int i2) {
        handleScrollChanged(i, i2);
    }

    public void showExtraPhotosImageViewer(int i) {
        this.mFragment.showProductExtraPhotosImageViewer(i);
    }

    public void onMainPhotoImageLoaded() {
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.supportStartPostponedEnterTransition();
            }
        });
    }

    private void addRatingRowToViewGroup(final WishRating wishRating, ViewGroup viewGroup, boolean z) {
        ProductDetailsRatingsRowView productDetailsRatingsRowView = new ProductDetailsRatingsRowView(getContext());
        productDetailsRatingsRowView.setup(wishRating);
        productDetailsRatingsRowView.setOnRatingImageClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (wishRating.getImageLargeUrlString() != null) {
                    ProductDetailsOverviewView.this.mFragment.showRatingPhotosImageViewer(wishRating, 0);
                }
            }
        });
        productDetailsRatingsRowView.hideDivider(z);
        productDetailsRatingsRowView.setOnItemClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductDetailsOverviewView.this.mFragment.showRatingAuthorProfile(wishRating);
            }
        });
        productDetailsRatingsRowView.setOnWishStarBadgeClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductDetailsOverviewView.this.mFragment.showWishStarDialog(wishRating.getAuthor().getFirstName());
            }
        });
        viewGroup.addView(productDetailsRatingsRowView);
    }

    private int getPriceWatchLimit() {
        return ExperimentDataCenter.getInstance().canAddFivePriceWatch() ? 5 : 3;
    }

    public void showPriceWatchInstructions() {
        final MultiButtonDialogFragment createMultiButtonYesDialog = MultiButtonDialogFragment.createMultiButtonYesDialog(this.mFragment.getString(R.string.price_watch), this.mFragment.getString(R.string.price_watch_instructions_text, Integer.toString(getPriceWatchLimit())), this.mFragment.getString(R.string.price_watch_add_to), R.drawable.main_button_selector);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRICE_WATCH_TOOLTIP_INFO_POPUP);
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.startDialog(createMultiButtonYesDialog, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_TOOLTIP_INFO_POPUP);
                        ProductDetailsOverviewView.this.addToPriceWatch();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void addToPriceWatch() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                if (ProductDetailsOverviewView.this.mProduct.getCommerceValue() != null) {
                    productDetailsServiceFragment.addToPriceWatch(ProductDetailsOverviewView.this.mProduct.getProductId());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeFromPriceWatch() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.removeFromPriceWatch(ProductDetailsOverviewView.this.mProduct.getProductId());
            }
        });
    }

    /* access modifiers changed from: private */
    public void addToWishlist() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, final ProductDetailsServiceFragment productDetailsServiceFragment) {
                SelectWishlistDialogFragment selectWishlistDialogFragment = new SelectWishlistDialogFragment();
                final ArrayList arrayList = new ArrayList();
                arrayList.add(ProductDetailsOverviewView.this.mProduct.getProductId());
                baseActivity.startDialog(selectWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 2000) {
                            ProductDetailsOverviewView.this.createWishlist();
                        } else if (bundle != null) {
                            if (ProductDetailsOverviewView.this.mProduct.getAuthorizedBrand() != null) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_WISH);
                            }
                            WishWishlist wishWishlist = (WishWishlist) bundle.getParcelable("ResultWishlist");
                            if (wishWishlist != null) {
                                productDetailsServiceFragment.addToWishlist(arrayList, wishWishlist.getWishlistId());
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void createWishlist() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(ProductDetailsOverviewView.this.mProduct.getProductId());
                productDetailsServiceFragment.createAndAddToWishlist(arrayList);
            }
        });
    }

    public void handleScrollChanged(int i, int i2) {
        if (!isTabletInLandscape()) {
            super.handleScrollChanged(i, i2);
        }
    }

    public void handleGroupBuysLoaded(final ArrayList<WishGroupBuyRowInfo> arrayList, final WishGroupBuyInfo wishGroupBuyInfo, final WishProduct wishProduct) {
        if (this.mGroupBuyView != null) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, final ProductDetailsServiceFragment productDetailsServiceFragment) {
                    if (wishGroupBuyInfo == null) {
                        ProductDetailsOverviewView.this.mGroupBuyContainer.setVisibility(8);
                        ProductDetailsOverviewView.this.mGroupBuyDivider.setVisibility(8);
                    } else if (arrayList.size() == 0 && (!wishGroupBuyInfo.canCreate() || wishGroupBuyInfo.getCreatorName() == null || wishGroupBuyInfo.getCreatorTitle() == null)) {
                        ProductDetailsOverviewView.this.mGroupBuyContainer.setVisibility(8);
                        ProductDetailsOverviewView.this.mGroupBuyDivider.setVisibility(8);
                    } else {
                        ProductDetailsOverviewView.this.mGroupBuyContainer.setVisibility(0);
                        ProductDetailsOverviewView.this.mGroupBuyDivider.setVisibility(0);
                        ProductDetailsOverviewView.this.mGroupBuyTitle.setText(wishGroupBuyInfo.getTitle());
                        ProductDetailsOverviewView.this.mGroupBuyLearnMore.setText(wishGroupBuyInfo.getLearnMoreText());
                        ProductDetailsOverviewView.this.mGroupBuyLearnMore.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                ProductDetailsOverviewView.this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                                    public void performTask(ProductDetailsActivity productDetailsActivity) {
                                        Intent intent = new Intent();
                                        if (ExperimentDataCenter.getInstance().shouldSeeGroupBuyRedesign()) {
                                            intent.setClass(productDetailsActivity, GroupBuyLearnMoreActivity.class);
                                            intent.putExtra("ArgGroupBuyInfo", wishGroupBuyInfo);
                                            if (!arrayList.isEmpty()) {
                                                intent.putExtra("ArgGroupBuy", (Parcelable) arrayList.get(0));
                                            } else {
                                                WishGroupBuyRowInfo wishGroupBuyRowInfo = new WishGroupBuyRowInfo(null, wishGroupBuyInfo.getCreatorImage(), wishGroupBuyInfo.getCreatorName(), wishGroupBuyInfo.getCreatorTitle(), wishGroupBuyInfo.getCreatorMessage(), wishGroupBuyInfo.getCreateButtonText(), null);
                                                intent.putExtra("ArgGroupBuy", wishGroupBuyRowInfo);
                                            }
                                            intent.putExtra("ArgProduct", wishProduct);
                                            productDetailsActivity.startActivity(intent);
                                            return;
                                        }
                                        intent.setClass(productDetailsActivity, TextViewerActivity.class);
                                        intent.putExtra("ExtraTitle", wishGroupBuyInfo.getTitle());
                                        intent.putExtra("ExtraContent", wishGroupBuyInfo.getLearnMoreDetail());
                                        productDetailsActivity.startActivity(intent);
                                    }
                                });
                            }
                        });
                        ProductDetailsOverviewView.this.mGroupBuyView.setup(arrayList, wishGroupBuyInfo, wishProduct, ProductDetailsOverviewView.this.mFragment.getBaseActivity(), new BuyCallback() {
                            public void onBuy(String str) {
                                productDetailsServiceFragment.joinGroupBuy(str, true);
                            }

                            public void onCreate() {
                                productDetailsServiceFragment.createGroupBuy(true);
                            }
                        });
                    }
                }
            });
        }
    }

    private void handleDividers() {
        ViewGroup[] viewGroupArr = {(ViewGroup) this.mRootLayout.findViewById(R.id.section1), (ViewGroup) this.mRootLayout.findViewById(R.id.section2), (ViewGroup) this.mRootLayout.findViewById(R.id.section3), (ViewGroup) this.mRootLayout.findViewById(R.id.section4)};
        View[] viewArr = {this.mRootLayout.findViewById(R.id.divider1), this.mRootLayout.findViewById(R.id.divider2), this.mRootLayout.findViewById(R.id.divider3), this.mRootLayout.findViewById(R.id.divider4)};
        for (int i = 0; i < viewGroupArr.length; i++) {
            hideDividerIfSectionEmpty(viewGroupArr[i], viewArr[i]);
        }
        if (!(this.mItemSpecificationCollapsableSection == null || this.mItemSpecificationCollapsableSection.getVisibility() == 0)) {
            this.mRootLayout.findViewById(R.id.divider6).setVisibility(8);
        }
        if (!(this.mDescriptionCollapsableSection == null || this.mDescriptionCollapsableSection.getVisibility() == 0)) {
            this.mRootLayout.findViewById(R.id.divider7).setVisibility(8);
        }
        if (!(this.mShippingCollapsableSection == null || this.mShippingCollapsableSection.getVisibility() == 0)) {
            this.mRootLayout.findViewById(R.id.divider8).setVisibility(8);
        }
        if (!(this.mBuyerGuaranteeCollapsableSection == null || this.mBuyerGuaranteeCollapsableSection.getVisibility() == 0)) {
            this.mRootLayout.findViewById(R.id.divider_buyer_guarantee_col).setVisibility(8);
        }
        this.mRootLayout.findViewById(R.id.price_cut_detail_view_divider).setVisibility(this.mPriceChopDetailView.getVisibility());
    }

    private void hideDividerIfSectionEmpty(ViewGroup viewGroup, View view) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= viewGroup.getChildCount()) {
                z = true;
                break;
            } else if (viewGroup.getChildAt(i).getVisibility() == 0) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            view.setVisibility(8);
        }
    }

    public void handleFasterShippingRowLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (!this.mAddedRelatedExpressRow) {
            this.mAddedRelatedExpressRow = true;
            ProductDetailsRelatedItemsRow productDetailsRelatedItemsRow = new ProductDetailsRelatedItemsRow(getContext());
            productDetailsRelatedItemsRow.setup(this.mFragment, this.mFragment.getProductId(), DataMode.RelatedExpressProducts);
            productDetailsRelatedItemsRow.handleLoadingSuccess(productDetailsRelatedRowSpec);
            this.mRelatedRowsContainer.setVisibility(0);
            this.mRelatedRowsContainer.addView(productDetailsRelatedItemsRow, 0);
        }
    }

    public void handleVisuallySimilarRowLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (!this.mAddedVisuallySimilarProducts) {
            this.mAddedVisuallySimilarProducts = true;
            ProductDetailsRelatedItemsRow productDetailsRelatedItemsRow = new ProductDetailsRelatedItemsRow(getContext());
            productDetailsRelatedItemsRow.setup(this.mFragment, this.mFragment.getProductId(), DataMode.VisuallySimilar);
            productDetailsRelatedItemsRow.handleLoadingSuccess(productDetailsRelatedRowSpec);
            this.mRelatedRowsContainer.setVisibility(0);
            this.mRelatedRowsContainer.addView(productDetailsRelatedItemsRow, 0);
        }
    }

    public void handleProductBoostRowLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        ProductDetailsRelatedItemsRow productDetailsRelatedItemsRow = new ProductDetailsRelatedItemsRow(getContext());
        productDetailsRelatedItemsRow.setup(this.mFragment, this.mFragment.getProductId(), DataMode.RelatedProductBoostProducts);
        productDetailsRelatedItemsRow.handleLoadingSuccess(productDetailsRelatedRowSpec);
        this.mRelatedRowsContainer.setVisibility(0);
        this.mRelatedRowsContainer.addView(productDetailsRelatedItemsRow);
    }

    public void handleBuyerGuaranteeInfoLoaded(BuyerGuaranteeInfo buyerGuaranteeInfo) {
        if (buyerGuaranteeInfo == null) {
            handleBuyerGuaranteeFailed();
            return;
        }
        if (ExperimentDataCenter.getInstance().shouldSeeCollapsableBuyerGuarantee()) {
            this.mBuyerGuaranteeCollapsableSection.init(this.mFragment, buyerGuaranteeInfo);
            this.mBuyerGuaranteeCollapsableSection.setParentScrollView(this.mScroller);
            this.mBuyerGuaranteeCollapsableSection.setVisibility(0);
            this.mRootLayout.findViewById(R.id.divider_buyer_guarantee_col).setVisibility(0);
            this.mBuyerGuaranteeView.setVisibility(8);
        } else if (ExperimentDataCenter.getInstance().shouldSeeFullSectionBuyerGuarantee()) {
            this.mBuyerGuaranteeView.setup(this.mFragment, buyerGuaranteeInfo);
            this.mBuyerGuaranteeView.setVisibility(0);
            this.mBuyerGuaranteeCollapsableSection.setVisibility(8);
        }
    }

    public void handleBuyerGuaranteeFailed() {
        this.mBuyerGuaranteeCollapsableSection.setVisibility(8);
        this.mBuyerGuaranteeView.setVisibility(8);
    }

    public void handlePriceChopDetailLoaded(String str, PriceChopProductDetail priceChopProductDetail) {
        if (priceChopProductDetail == null) {
            this.mPriceChopDetailView.setVisibility(8);
            return;
        }
        this.mPriceChopDetailView.setVisibility(0);
        this.mPriceChopDetailView.setup(this.mFragment, str, priceChopProductDetail);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_PRICE_CHOP_DETAIL);
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                if (productDetailsActivity.shouldSmoothScrollToPriceChop()) {
                    ProductDetailsOverviewView.this.postDelayed(new Runnable() {
                        public void run() {
                            ProductDetailsOverviewView.this.mScroller.smoothScrollTo(0, ((ProductDetailsOverviewView.this.mPriceChopDetailView.getTop() + ProductDetailsOverviewView.this.mContentContainer.getTop()) - ProductDetailsOverviewView.this.mScroller.getHeight()) + ProductDetailsOverviewView.this.mPriceChopDetailView.getHeight());
                        }
                    }, 500);
                }
            }
        });
    }

    public void wishListAddSuccess(String str) {
        if (ExperimentDataCenter.getInstance().shouldSeeItemAfterWishListAdd() || ExperimentDataCenter.getInstance().shouldSeeHeartAfterWishListAdd()) {
            animateConfirmationDialog(str);
        } else {
            animateWishCheckmark();
        }
    }

    /* access modifiers changed from: private */
    public void showFullBadgeDescription(final List<WishProductBadge> list) {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.showBadgeDescriptionDialog(list);
            }
        });
    }

    private void setupBadges(List<WishProductBadge> list) {
        ViewGroup viewGroup;
        if (list != null && list.size() != 0) {
            if (ExperimentDataCenter.getInstance().shouldSeeSmallCondensedBadges() || ExperimentDataCenter.getInstance().shouldSeeLongCondensedBadges()) {
                viewGroup = (ViewGroup) findViewById(R.id.badge_container);
            } else {
                viewGroup = (ViewGroup) findViewById(R.id.product_details_badges);
            }
            viewGroup.setVisibility(0);
            for (WishProductBadge wishProductBadge : list) {
                if (wishProductBadge != null) {
                    if (ExperimentDataCenter.getInstance().shouldSeeSmallCondensedBadges() || ExperimentDataCenter.getInstance().shouldSeeLongCondensedBadges()) {
                        View createCondensedBadgeView = createCondensedBadgeView(wishProductBadge);
                        createCondensedBadgeView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAIL_CONDENSED_BADGE);
                                ProductDetailsOverviewView.this.showFullBadgeDescription(ProductDetailsOverviewView.this.mProduct.getProductBadges());
                            }
                        });
                        viewGroup.addView(createCondensedBadgeView);
                    } else {
                        viewGroup.addView(new ProductBadgeRow(WishApplication.getInstance().getBaseContext(), wishProductBadge));
                    }
                }
            }
        }
    }

    private View createCondensedBadgeView(WishProductBadge wishProductBadge) {
        CharSequence charSequence;
        int i;
        int i2;
        int i3;
        int dimensionPixelSize;
        int dimensionPixelSize2;
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setTextColor(ContextCompat.getColor(getContext(), wishProductBadge.getBadgeColor()));
        themedTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.condensed_badges_font_size));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(ContextCompat.getColor(getContext(), wishProductBadge.getBackgroundBadgeColor()));
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.condensed_badges_radius));
        themedTextView.setBackground(gradientDrawable);
        CharSequence spannableString = new SpannableString(wishProductBadge.getTitle());
        spannableString.setSpan(new WishFontSpan(1), 0, spannableString.length(), 17);
        if (ExperimentDataCenter.getInstance().shouldSeeSmallCondensedBadges()) {
            themedTextView.setMaxLines(1);
            themedTextView.setEllipsize(TruncateAt.END);
            if (wishProductBadge.getType() == 1) {
                i2 = getResources().getDimensionPixelSize(R.dimen.small_condensed_badge_verified_icon_size);
                i = getResources().getDimensionPixelSize(R.dimen.small_condensed_badge_verified_icon_size);
            } else if (wishProductBadge.getType() == 2) {
                i2 = getResources().getDimensionPixelSize(R.dimen.small_express_badge_icon_width);
                i = getResources().getDimensionPixelSize(R.dimen.small_express_badge_icon_height);
            } else {
                charSequence = spannableString;
                i3 = -2;
            }
            charSequence = spannableString;
            i3 = -2;
            int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
            int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            themedTextView.setPadding(dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize3, dimensionPixelSize4);
            Drawable drawable = getResources().getDrawable(wishProductBadge.getCondensedBadgeIcon());
            drawable.setBounds(0, 0, i2, i);
            themedTextView.setCompoundDrawables(drawable, null, null, null);
            themedTextView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.condensed_badges_icon_text_padding));
            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(i3, -2);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            themedTextView.setLayoutParams(layoutParams);
            themedTextView.setText(charSequence);
            return themedTextView;
        } else if (ExperimentDataCenter.getInstance().shouldSeeLongCondensedBadges()) {
            CharSequence concat = TextUtils.concat(new CharSequence[]{spannableString, " ", wishProductBadge.getCondensedBadgeShortenedDescription()});
            if (wishProductBadge.getType() == 1) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.long_condensed_badge_verified_icon_size);
                dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.long_condensed_badge_verified_icon_size);
            } else if (wishProductBadge.getType() == 2) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.long_express_badge_icon_width);
                dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.long_express_badge_icon_height);
            } else {
                charSequence = concat;
                i3 = -1;
            }
            i2 = dimensionPixelSize;
            i = dimensionPixelSize2;
            charSequence = concat;
            i3 = -1;
            int dimensionPixelSize32 = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
            int dimensionPixelSize42 = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            themedTextView.setPadding(dimensionPixelSize32, dimensionPixelSize42, dimensionPixelSize32, dimensionPixelSize42);
            Drawable drawable2 = getResources().getDrawable(wishProductBadge.getCondensedBadgeIcon());
            drawable2.setBounds(0, 0, i2, i);
            themedTextView.setCompoundDrawables(drawable2, null, null, null);
            themedTextView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.condensed_badges_icon_text_padding));
            FlexboxLayout.LayoutParams layoutParams2 = new FlexboxLayout.LayoutParams(i3, -2);
            layoutParams2.rightMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
            themedTextView.setLayoutParams(layoutParams2);
            themedTextView.setText(charSequence);
            return themedTextView;
        } else {
            charSequence = spannableString;
            i3 = 0;
        }
        i2 = 0;
        i = 0;
        int dimensionPixelSize322 = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize422 = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        themedTextView.setPadding(dimensionPixelSize322, dimensionPixelSize422, dimensionPixelSize322, dimensionPixelSize422);
        Drawable drawable22 = getResources().getDrawable(wishProductBadge.getCondensedBadgeIcon());
        drawable22.setBounds(0, 0, i2, i);
        themedTextView.setCompoundDrawables(drawable22, null, null, null);
        themedTextView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.condensed_badges_icon_text_padding));
        FlexboxLayout.LayoutParams layoutParams22 = new FlexboxLayout.LayoutParams(i3, -2);
        layoutParams22.rightMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        layoutParams22.bottomMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        themedTextView.setLayoutParams(layoutParams22);
        themedTextView.setText(charSequence);
        return themedTextView;
    }
}
