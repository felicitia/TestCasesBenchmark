package com.contextlogic.wish.activity.productdetails;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.feed.BaseInitialProductWrapper;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerActivity;
import com.contextlogic.wish.activity.pricechop.PriceChopDetailCounterView;
import com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter.ProductDetailSection;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.activity.profile.WishStarDialogFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishUser.WishUserState;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedContext;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.ui.text.BoldBorderSpan;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.CountCallback;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.ui.timer.TimerTextView;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ProductDetailsFragment extends BaseProductFeedFragment<ProductDetailsActivity> {
    /* access modifiers changed from: private */
    public TextView mAddToCartButton;
    private View mAddToCartButtonContainer;
    /* access modifiers changed from: private */
    public ImageView mAddToCartOfferArrow;
    /* access modifiers changed from: private */
    public FrameLayout mAddToCartOfferCounterContainer;
    /* access modifiers changed from: private */
    public TextView mAddToCartOfferExpiredTextView;
    /* access modifiers changed from: private */
    public TextView mAddToCartOfferText;
    /* access modifiers changed from: private */
    public CountdownTimerView mAddToCartOfferTimer;
    /* access modifiers changed from: private */
    public LinearLayout mAddToCartOfferView;
    /* access modifiers changed from: private */
    public int mAvailabeRewardsPoints;
    private View mBuyBar;
    /* access modifiers changed from: private */
    public int mCountdownHeight;
    /* access modifiers changed from: private */
    public String mDealDashCouponCode;
    /* access modifiers changed from: private */
    public String mDealDashPercentOff;
    /* access modifiers changed from: private */
    public Date mDealDashTargetDate;
    /* access modifiers changed from: private */
    public View mDealdashCountdownContainer;
    private ThemedTextView mDealdashCountdownText;
    private CountdownTimerView mDealdashCountdownView;
    private boolean mExpressShippingOnlyFilter = false;
    /* access modifiers changed from: private */
    public HashMap<String, String> mExtraInfo;
    private boolean mFirstTimeDescription = true;
    private boolean mFirstTimeProductRating = true;
    private boolean mFirstTimeRelated = true;
    private boolean mFirstTimeShippingInfo = true;
    private boolean mFirstTimeStoreRating = true;
    private ImageHttpPrefetcher mImagePrefetcher;
    /* access modifiers changed from: private */
    public WishProduct mInitialProduct;
    /* access modifiers changed from: private */
    public boolean mIsTransitioning;
    private HashSet<String> mLastSeenProductIds;
    private ThemedTextView mListPriceText;
    /* access modifiers changed from: private */
    public ThemedTextView mListPriceTextSwapped;
    protected WishProduct mLoadedProduct;
    /* access modifiers changed from: private */
    public ArrayList<WishProductExtraImage> mMediaSources;
    private ImageHttpPrefetcher mMerchantRatingsImagePrefetcher;
    private TimerTextView mNewDealDashCountdownView;
    private int mOverviewPhotoIndex;
    private OnPageChangeListener mPageScrollListener;
    protected ProductDetailsPagerAdapter mPagerAdapter;
    private PriceChopDetailCounterView mPriceChopDetailCounterView;
    /* access modifiers changed from: private */
    public LinearLayout mPriceLayout;
    private PriceWatchCallback mPriceWatchCallback;
    private LinearLayout mPriceWrapper;
    private LinearLayout mPriceWrapperSwapped;
    private ImageHttpPrefetcher mProductRatingsImagePrefetcher;
    /* access modifiers changed from: private */
    public Source mProductSource;
    protected SafeViewPager mProductViewPager;
    private int mRestoredIndex;
    /* access modifiers changed from: private */
    public boolean mShouldShowCrossedOutPriceLeft;
    private boolean mShouldShowFeedToDetailTransition;
    /* access modifiers changed from: private */
    public boolean mShouldShowProductDetailTransition;
    private View mSoldOutButton;
    /* access modifiers changed from: private */
    public int mTabBarHeight;
    protected PagerSlidingTabStrip mTabStrip;
    /* access modifiers changed from: private */
    public ValueAnimator mTabStripAnimator;
    /* access modifiers changed from: private */
    public View mTabStripContainer;
    /* access modifiers changed from: private */
    public View mTabStripShadow;
    private TextView mTaxText;
    private TextView mTaxTextSwapped;
    /* access modifiers changed from: private */
    public View mTopShadow;
    /* access modifiers changed from: private */
    public ThemedTextView mYourPriceText;
    /* access modifiers changed from: private */
    public ThemedTextView mYourPriceTextSwapped;
    /* access modifiers changed from: private */
    public PageState pageState = PageState.NONE;

    public static class BooleanWrapper {
        public boolean state;

        public BooleanWrapper(boolean z) {
            this.state = z;
        }
    }

    private enum PageState {
        NONE,
        LOADING,
        ERROR,
        COMPLETE
    }

    public interface PriceWatchCallback {
        void onPriceWatchStateChanges(boolean z);
    }

    public interface ProductDetailsCallback {
        void onUnWished();
    }

    public boolean canFeedViewPullToRefresh() {
        return false;
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public BaseInitialProductWrapper getInitialProductInfo(int i) {
        return null;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.product_details_fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShouldShowProductDetailTransition = ExperimentDataCenter.getInstance().shouldShowProductDetailTransition();
        this.mShouldShowFeedToDetailTransition = ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition() && VERSION.SDK_INT >= 22;
        this.mShouldShowCrossedOutPriceLeft = ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInProductDetail();
        this.mPageScrollListener = new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
                if (!ProductDetailsFragment.this.mShouldShowProductDetailTransition && ProductDetailsFragment.this.mTabStripContainer.getAnimation() == null) {
                    ProductDetailsFragment.this.showTabArea(true);
                }
            }

            public void onPageSelected(int i) {
                ProductDetailsFragment.this.handlePageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    ProductDetailsFragment.this.onPagerScrollSettled();
                } else {
                    ProductDetailsFragment.this.onPagerScrollUnsettled();
                }
            }
        };
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ProductDetailsFragment.this.mInitialProduct = productDetailsActivity.getInitialProduct();
                ProductDetailsFragment.this.mExtraInfo = productDetailsActivity.getExtraInfo();
                ProductDetailsFragment.this.mDealDashTargetDate = productDetailsActivity.getDealDashTargetDate();
                ProductDetailsFragment.this.mProductSource = productDetailsActivity.getProductSource();
                ProductDetailsFragment.this.mDealDashCouponCode = productDetailsActivity.getDealDashCouponCode();
                ProductDetailsFragment.this.mDealDashPercentOff = productDetailsActivity.getDealDashPercentOff();
                ProductDetailsFragment.this.mAvailabeRewardsPoints = productDetailsActivity.getAvailableRewardsPoints();
                if (ProductDetailsFragment.this.mProductSource == Source.FREE_GIFT) {
                    WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_PRODUCT_DETAILS);
                } else if (ProductDetailsFragment.this.mProductSource == Source.POINTS_REDEMPTION) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEMABLE_REWARD_PRODUCT_PRODUCT_DETAILS);
                }
                ProductDetailsFragment.this.mTabBarHeight = productDetailsActivity.getResources().getDimensionPixelOffset(R.dimen.tab_bar_height);
                ProductDetailsFragment.this.mCountdownHeight = productDetailsActivity.getResources().getDimensionPixelOffset(R.dimen.countdown_height);
            }
        });
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mProductRatingsImagePrefetcher = new ImageHttpPrefetcher();
        this.mMerchantRatingsImagePrefetcher = new ImageHttpPrefetcher();
        this.mPriceWatchCallback = null;
        this.mRestoredIndex = -1;
        this.mLastSeenProductIds = new HashSet<>();
        if (bundle != null) {
            this.mLoadedProduct = (WishProduct) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateLoadedProduct", WishProduct.class);
            this.mRestoredIndex = bundle.getInt("SavedStateTabIndex");
            this.mFirstTimeRelated = bundle.getBoolean("SavedStateFirstTimeRelated");
            this.mFirstTimeDescription = bundle.getBoolean("SavedStateFirstTimeDescription");
            this.mFirstTimeProductRating = bundle.getBoolean("SavedStateFirstTimeProductRating");
            this.mFirstTimeStoreRating = bundle.getBoolean("SavedStateFirstTimeStoreRating");
            this.mFirstTimeShippingInfo = bundle.getBoolean("SavedStateFirstTimeShippingInfo");
            this.mOverviewPhotoIndex = bundle.getInt("SavedStateOverviewPhotoIndex");
        }
        setupAnimationAfterTransition();
    }

    public void handleRelatedProductsLoaded(ArrayList<Object> arrayList, boolean z, int i) {
        if (this.mPagerAdapter != null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof WishProduct) {
                    WishProduct wishProduct = (WishProduct) next;
                    if (!this.mLastSeenProductIds.contains(wishProduct.getProductId())) {
                        arrayList2.add(wishProduct);
                        this.mLastSeenProductIds.add(wishProduct.getProductId());
                    }
                }
            }
            this.mPagerAdapter.addRelatedProducts(arrayList2, i, z);
        }
    }

    public void handleRelatedExpressItemsLoaded(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.addExpressRelatedProducts(productDetailsRelatedRowSpec);
        }
    }

    public void handleVisuallySimilarItemsLoaded(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.addVisuallySimilarProducts(productDetailsRelatedRowSpec);
        }
    }

    public void handleRelatedProductBoostItemsLoaded(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.addProductBoostRelatedProducts(productDetailsRelatedRowSpec);
        }
    }

    public void handleRatingsLoaded(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, boolean z, int i, int i2, boolean z2) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRatingsLoaded(wishRatingSummary, arrayList, z, i, i2, z2);
        }
    }

    public void handleRatingsFailed(boolean z) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRatingsFailed(z);
        }
    }

    public void handleBundledProductLoaded(WishProduct wishProduct) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleBundledProductLoaded(wishProduct);
        }
    }

    public void handleGroupBuysLoaded(ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleGroupBuysLoaded(arrayList, wishGroupBuyInfo, getLoadedProduct());
        }
    }

    public void handlePriceChopDetailLoaded(PriceChopProductDetail priceChopProductDetail) {
        if (this.mLoadedProduct != null) {
            this.mLoadedProduct.setPriceChopProductDetail(priceChopProductDetail);
            if (!(this.mPagerAdapter == null || this.mPagerAdapter.mProductDetailsOverviewView == null)) {
                this.mPagerAdapter.handlePriceChopDetailLoaded(this.mLoadedProduct.getProductId(), priceChopProductDetail);
            }
            this.mPriceChopDetailCounterView.setup(this, priceChopProductDetail);
            if (this.mLoadedProduct.getPriceChopProductDetail() != null && this.mLoadedProduct.getPriceChopProductDetail().isRunning()) {
                this.mAddToCartButtonContainer.setBackgroundResource(R.color.white);
                this.mYourPriceText.setTextColor(ContextCompat.getColor(getContext(), R.color.price_chop_yellow));
                this.mAddToCartButton.setBackgroundResource(R.drawable.price_chop_button_bg);
            }
        }
    }

    public void handleRecommendedWishlistsLoaded(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str, String str2) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRecommendedWishlistsLoaded(arrayList, arrayList2, str, str2);
        }
    }

    public void handleBundledProductsFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleBundledProductFailed();
        }
    }

    public void handleRelatedProductsFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRelatedProductsFailed();
        }
    }

    public void handleRecommendedWishlistsFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRecommendedWishlistsFailed();
        }
    }

    public void handleRelatedExpressItemsFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleRelatedExpressItemsFailed();
        }
    }

    public void handleVisuallySimilarItemsLoadFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleVisuallysimilarItemsLoadFailed();
        }
    }

    public void handleBuyerGuaranteeInfoLoaded(BuyerGuaranteeInfo buyerGuaranteeInfo) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleBuyerGuaranteeInfoLoaded(buyerGuaranteeInfo);
        }
    }

    public void handleBuyerGuaranteeInfoFailed() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleBuyerGuaranteeFailed();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        if (this.mLoadedProduct != null) {
            bundle.putString("SavedStateLoadedProduct", StateStoreCache.getInstance().storeParcelable(this.mLoadedProduct));
        }
        bundle.putInt("SavedStateTabIndex", getCurrentIndex());
        bundle.putBoolean("SavedStateFirstTimeRelated", this.mFirstTimeRelated);
        bundle.putBoolean("SavedStateFirstTimeDescription", this.mFirstTimeDescription);
        bundle.putBoolean("SavedStateFirstTimeProductRating", this.mFirstTimeProductRating);
        bundle.putBoolean("SavedStateFirstTimeStoreRating", this.mFirstTimeStoreRating);
        bundle.putBoolean("SavedStateFirstTimeShippingInfo", this.mFirstTimeShippingInfo);
        bundle.putInt("SavedStateOverviewPhotoIndex", this.mOverviewPhotoIndex);
        this.mPagerAdapter.handleSaveInstanceState(bundle);
    }

    public String getPagedDataSavedInstanceStateKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SavedStatePagedData_");
        sb.append(i);
        return sb.toString();
    }

    public Bundle getSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            return getSavedInstanceState().getBundle(getPagedDataSavedInstanceStateKey(i));
        }
        return null;
    }

    public void setOverviewPhotoIndex(int i) {
        this.mOverviewPhotoIndex = i;
    }

    public int getOverviewPhotoIndex() {
        return this.mOverviewPhotoIndex;
    }

    public void setUpBuyBar(View view) {
        this.mPriceLayout = (LinearLayout) view.findViewById(R.id.product_details_fragment_price_layout);
        this.mAddToCartButtonContainer = view.findViewById(R.id.product_details_fragment_add_to_cart_button_container);
        this.mAddToCartOfferView = (LinearLayout) view.findViewById(R.id.product_details_fragment_add_to_cart_offer);
        this.mAddToCartOfferArrow = (ImageView) view.findViewById(R.id.product_details_fragment_add_to_cart_offer_arrow);
        this.mAddToCartOfferText = (TextView) view.findViewById(R.id.product_details_fragment_add_to_cart_offer_text);
        this.mAddToCartOfferCounterContainer = (FrameLayout) view.findViewById(R.id.product_details_fragment_add_to_cart_offer_timer_container);
        this.mAddToCartOfferExpiredTextView = (TextView) this.mAddToCartOfferCounterContainer.findViewById(R.id.checkout_timer_expired_text_view);
        this.mTaxText = (TextView) view.findViewById(R.id.product_details_fragment_tax_text);
        this.mTaxTextSwapped = (TextView) view.findViewById(R.id.product_details_fragment_tax_text_swapped);
        this.mYourPriceText = (ThemedTextView) view.findViewById(R.id.product_details_fragment_your_price);
        this.mListPriceText = (ThemedTextView) view.findViewById(R.id.product_details_fragment_list_price);
        this.mYourPriceTextSwapped = (ThemedTextView) view.findViewById(R.id.product_details_fragment_your_price_experiment_swapped);
        this.mListPriceTextSwapped = (ThemedTextView) view.findViewById(R.id.product_details_fragment_list_price_experiment_swapped);
        this.mPriceChopDetailCounterView = (PriceChopDetailCounterView) view.findViewById(R.id.product_detail_price_chop_counter);
        this.mYourPriceTextSwapped.setVisibility(8);
        this.mListPriceTextSwapped.setVisibility(8);
        this.mPriceWrapper = (LinearLayout) view.findViewById(R.id.product_details_buy_bar_price_wrapper);
        this.mPriceWrapperSwapped = (LinearLayout) view.findViewById(R.id.product_details_buy_bar_price_wrapper_swapped);
        this.mSoldOutButton = view.findViewById(R.id.product_details_fragment_sold_out_button);
        this.mAddToCartButton = (TextView) view.findViewById(R.id.product_details_fragment_add_to_cart_button);
        if (this.mProductSource == Source.FREE_GIFT) {
            this.mYourPriceText.setPaintFlags(this.mListPriceText.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.claim));
        } else if (this.mProductSource == Source.DAILY_GIVEAWAY && !ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            this.mAddToCartButton.setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.main_button_selector));
            this.mYourPriceText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            this.mListPriceText.setPaintFlags(this.mListPriceText.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.claim_for_free));
        } else if (this.mProductSource == Source.POINTS_REDEMPTION) {
            this.mAddToCartButton.setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.main_button_selector));
            this.mYourPriceText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            this.mListPriceText.setPaintFlags(this.mListPriceText.getPaintFlags() | 16);
            this.mAddToCartButton.setText(R.string.redeem);
        } else {
            if (this.mShouldShowCrossedOutPriceLeft) {
                this.mYourPriceTextSwapped.setVisibility(0);
                this.mListPriceTextSwapped.setVisibility(0);
                this.mYourPriceText.setVisibility(8);
                this.mListPriceText.setVisibility(8);
                this.mYourPriceText = this.mYourPriceTextSwapped;
                this.mListPriceText = this.mListPriceTextSwapped;
                this.mPriceWrapperSwapped.setVisibility(0);
                this.mPriceWrapper.setVisibility(8);
            }
            this.mListPriceText.setPaintFlags(this.mListPriceText.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.buy));
        }
        this.mAddToCartButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ProductDetailsFragment.this.mLoadedProduct != null) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUY_BOTTOM_BAR_BUTTON);
                    if (ProductDetailsFragment.this.mExtraInfo != null && ProductDetailsFragment.this.mExtraInfo.containsKey("add_to_cart_event_id")) {
                        try {
                            WishAnalyticsLogger.trackEvent(Integer.parseInt((String) ProductDetailsFragment.this.mExtraInfo.get("add_to_cart_event_id")));
                        } catch (NumberFormatException unused) {
                        }
                    }
                    if (ProductDetailsFragment.this.mLoadedProduct.getPriceChopProductDetail() != null && ProductDetailsFragment.this.mLoadedProduct.getPriceChopProductDetail().isRunning()) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRICE_CHOP_DETAIL_BUY);
                    }
                    if (ProductDetailsFragment.this.mProductSource == Source.FREE_GIFT) {
                        WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_PRODUCT_DETAILS_CHECKOUT);
                        ProductDetailsFragment.this.addProductToCart(ProductDetailsFragment.this.mLoadedProduct, Source.FREE_GIFT);
                    } else if (ProductDetailsFragment.this.mProductSource == Source.DAILY_GIVEAWAY) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_PRODUCT_DETAILS_CLAIM, ProductDetailsFragment.this.mLoadedProduct.getProductId(), ProductDetailsFragment.getGiveawayImpressionInfo());
                        ProductDetailsFragment.this.addProductToCart(ProductDetailsFragment.this.mLoadedProduct, Source.DAILY_GIVEAWAY);
                    } else if (ProductDetailsFragment.this.mProductSource == Source.POINTS_REDEMPTION) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REDEEMABLE_REWARD_PRODUCT_PRODUCT_DETAIL_REDEEM);
                        ProductDetailsFragment.this.redeemRewardProduct(ProductDetailsFragment.this.mLoadedProduct);
                    } else {
                        ProductDetailsFragment.this.addProductToCart(ProductDetailsFragment.this.mLoadedProduct);
                    }
                }
            }
        });
        if (this.mDealDashTargetDate != null && !this.mShouldShowProductDetailTransition) {
            LayoutParams layoutParams = (LayoutParams) this.mProductViewPager.getLayoutParams();
            layoutParams.setMargins(0, this.mCountdownHeight, 0, 0);
            this.mProductViewPager.setLayoutParams(layoutParams);
        }
        if (this.mInitialProduct != null && this.mInitialProduct.getImage() != null && this.mImagePrefetcher != null) {
            this.mImagePrefetcher.queueImage(this.mInitialProduct.getImage());
        }
    }

    /* access modifiers changed from: private */
    public static HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            hashMap.put("GiveawayType", "DailyRaffle");
        } else {
            hashMap.put("GiveawayType", "DailyGiveaway");
        }
        return hashMap;
    }

    public void onStop() {
        super.onStop();
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.stopLoadingCurrentlyViewingUsers();
            }
        });
    }

    public void handleResume() {
        super.handleResume();
        if (!shouldShowFeedToDetailTransition()) {
            if (this.mLoadedProduct != null && !getLoadingPageView().isLoadingComplete()) {
                handleLoadingSuccess(this.mLoadedProduct);
            }
            if (!getLoadingPageView().isLoadingComplete()) {
                getLoadingPageView().reload();
            }
        } else if (this.pageState == PageState.NONE) {
            if (this.mLoadedProduct != null) {
                this.pageState = PageState.COMPLETE;
                updateUI();
                getLoadingPageView().markLoadingComplete();
            } else {
                this.pageState = PageState.LOADING;
                this.mIsTransitioning = true;
                updateUI();
                getLoadingPageView().markLoadingComplete();
                handleReload();
            }
        }
        if (this.mAddToCartOfferTimer != null) {
            this.mAddToCartOfferTimer.startTimer();
        }
        if (this.mLoadedProduct != null) {
            withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadCurrentlyViewingUsers(ProductDetailsFragment.this.mLoadedProduct.getProductId());
                }
            });
        }
        refreshWishStates(false);
    }

    public void onPause() {
        super.onPause();
        if (this.mAddToCartOfferTimer != null) {
            this.mAddToCartOfferTimer.pauseTimer();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.cleanup();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
        if (this.mProductRatingsImagePrefetcher != null) {
            this.mProductRatingsImagePrefetcher.cancelPrefetching();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.cancelPrefetching();
        }
    }

    public void releaseImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.releaseImages();
        }
        if (this.mAddToCartOfferTimer != null) {
            this.mAddToCartOfferTimer.pauseTimer();
        }
        if (this.mDealdashCountdownView != null && !ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mDealdashCountdownView.pauseTimer();
        }
        if (this.mProductRatingsImagePrefetcher != null) {
            this.mProductRatingsImagePrefetcher.pausePrefetching();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.restoreImages();
        }
        if (this.mAddToCartOfferTimer != null) {
            this.mAddToCartOfferTimer.startTimer();
        }
        if (!(this.mDealdashCountdownView == null || this.mDealDashTargetDate == null || ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon())) {
            this.mDealdashCountdownView.startTimer();
        }
        if (this.mProductRatingsImagePrefetcher != null) {
            this.mProductRatingsImagePrefetcher.resumePrefetching();
        }
        if (this.mMerchantRatingsImagePrefetcher != null) {
            this.mMerchantRatingsImagePrefetcher.resumePrefetching();
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldShowFeedToDetailTransition() {
        return this.mShouldShowFeedToDetailTransition && getTransitionImageUrl() != null;
    }

    public boolean shouldLoadOverviewExpressItems() {
        return getLoadedProduct() != null && getLoadedProduct().getLoadDetailsOverviewExpressItems() && !ExperimentDataCenter.getInstance().shouldReplaceExpressRowWithPbRow() && !ExperimentDataCenter.getInstance().shouldSeeVisuallySimilarItems();
    }

    public boolean shouldLoadRelatedExpressItems() {
        return getLoadedProduct() != null && getLoadedProduct().getLoadDetailsRelatedExpressItems() && !ExperimentDataCenter.getInstance().shouldSeeVisuallySimilarItems();
    }

    public boolean shouldLoadVisuallySimilarItems() {
        return getLoadedProduct() != null && ExperimentDataCenter.getInstance().shouldSeeVisuallySimilarItems();
    }

    public void switchToPosition(int i, boolean z) {
        if (i >= 0 && i < this.mPagerAdapter.getCount()) {
            this.mProductViewPager.setCurrentItem(i, z);
        }
    }

    private void loadProduct() {
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                if (ProductDetailsFragment.this.mInitialProduct != null) {
                    if (ProductDetailsFragment.this.mExtraInfo == null) {
                        ProductDetailsFragment.this.mExtraInfo = new HashMap();
                    }
                    ProductDetailsFragment.this.mExtraInfo.remove("is_free_gift");
                    ProductDetailsFragment.this.mExtraInfo.remove("is_for_points_redemption");
                    if (ProductDetailsFragment.this.mProductSource == Source.FREE_GIFT) {
                        ProductDetailsFragment.this.mExtraInfo.put("is_free_gift", "true");
                    } else if (ProductDetailsFragment.this.mProductSource == Source.DAILY_GIVEAWAY) {
                        ProductDetailsFragment.this.mExtraInfo.put("is_daily_giveaway", "true");
                    } else if (ProductDetailsFragment.this.mProductSource == Source.POINTS_REDEMPTION) {
                        ProductDetailsFragment.this.mExtraInfo.put("is_for_points_redemption", "true");
                    }
                    productDetailsServiceFragment.loadProduct(ProductDetailsFragment.this.mInitialProduct.getProductId(), ProductDetailsFragment.this.mExtraInfo);
                    return;
                }
                ProductDetailsFragment.this.handleLoadingFailure(false);
            }
        });
    }

    public WishProduct getLoadedProduct() {
        return this.mLoadedProduct != null ? this.mLoadedProduct : this.mInitialProduct;
    }

    public boolean isLoading() {
        return getTransitionImageUrl() != null && this.pageState == PageState.LOADING;
    }

    public boolean hasError() {
        return this.pageState == PageState.ERROR;
    }

    public void handleLoadingSuccess(final WishProduct wishProduct) {
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                if (ProductDetailsFragment.this.shouldShowFeedToDetailTransition()) {
                    ProductDetailsFragment.this.pageState = PageState.COMPLETE;
                    ProductDetailsFragment.this.mLoadedProduct = wishProduct;
                    ProductDetailsFragment.this.updateUI();
                } else {
                    if (ProductDetailsFragment.this.mShouldShowProductDetailTransition) {
                        productDetailsActivity.setThemeLoadSuccess();
                    }
                    ProductDetailsFragment.this.mLoadedProduct = wishProduct;
                    ProductDetailsFragment.this.updateUI();
                    ProductDetailsFragment.this.getLoadingPageView().markLoadingComplete();
                }
                if (wishProduct.getPromotionSpec() instanceof NewUserGiftPackSpec) {
                    final NewUserGiftPackSpec newUserGiftPackSpec = (NewUserGiftPackSpec) wishProduct.getPromotionSpec();
                    if (newUserGiftPackSpec.getStampSpec() != null) {
                        ProductDetailsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                                productDetailsServiceFragment.showDailyLoginPopup(newUserGiftPackSpec.getStampSpec());
                            }
                        });
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateUI() {
        if (isAdded() && (this.pageState == PageState.LOADING || !this.mIsTransitioning)) {
            WishProduct loadedProduct = getLoadedProduct();
            if (loadedProduct != null) {
                this.mPriceLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        ProductDetailsFragment.this.mPriceLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        float dimension = ProductDetailsFragment.this.getResources().getDimension(R.dimen.add_to_cart_offer_container_item_margin);
                        float width = (((float) ProductDetailsFragment.this.mPriceLayout.getWidth()) - (((float) ProductDetailsFragment.this.mAddToCartButton.getWidth()) + dimension)) / ((float) ProductDetailsFragment.this.getResources().getInteger(R.integer.buy_bar_layout_weight_segment));
                        if (!ProductDetailsFragment.this.mShouldShowCrossedOutPriceLeft) {
                            ProductDetailsFragment.this.mYourPriceText.setMaxWidth(Math.round((width * ((float) ProductDetailsFragment.this.getResources().getInteger(R.integer.buy_bar_your_price_layout_weight))) - (dimension * 2.0f)));
                            return;
                        }
                        ProductDetailsFragment.this.mYourPriceTextSwapped.setMaxWidth(Math.round((((float) ProductDetailsFragment.this.getResources().getInteger(R.integer.buy_bar_your_price_layout_weight)) * width) - dimension));
                        ProductDetailsFragment.this.mListPriceTextSwapped.setMaxWidth(Math.round(width * ((float) ProductDetailsFragment.this.getResources().getInteger(R.integer.buy_bar_list_price_layout_weight))));
                    }
                });
                if (loadedProduct.isDailyGiveaway()) {
                    this.mProductSource = Source.DAILY_GIVEAWAY;
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_PRODUCT_DETAILS, loadedProduct.getProductId(), getGiveawayImpressionInfo());
                    if (this.mAddToCartButton != null) {
                        this.mAddToCartButton.setBackground(WishApplication.getInstance().getResources().getDrawable(R.drawable.main_button_selector));
                        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
                            this.mAddToCartButton.setText(getString(R.string.claim_prize));
                        } else {
                            this.mAddToCartButton.setText(getString(R.string.claim_for_free));
                        }
                    }
                    if (this.mYourPriceText != null) {
                        this.mYourPriceText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                    }
                }
                this.mPagerAdapter.updatePages();
                customizeTabStrip();
                this.mTabStrip.setViewPager(this.mProductViewPager);
                this.mTabStrip.setOnPageChangeListener(this.mPageScrollListener);
                refreshTabStripFontColors();
                goToRestoredTab();
                populateBuyBar();
                handlePriceChopDetailLoaded(loadedProduct.getPriceChopProductDetail());
            }
        }
    }

    public void populateBuyBar() {
        if (this.mLoadedProduct == null || !this.mLoadedProduct.isCommerceProduct()) {
            this.mAddToCartButtonContainer.setVisibility(8);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("fb_currency", this.mLoadedProduct.getCommerceValue().getLocalizedCurrencyCode());
            bundle.putString("fb_content_type", "product");
            bundle.putString("fb_content_id", this.mLoadedProduct.getProductId());
            FacebookManager.getInstance().getLogger().logEvent("fb_mobile_content_view", this.mLoadedProduct.getCommerceValue().getValue(), bundle);
            GoogleAnalyticsLogger.getInstance().logProductView(this.mLoadedProduct.getProductId());
            if (this.mLoadedProduct.isInStock()) {
                this.mSoldOutButton.setVisibility(8);
            } else {
                this.mAddToCartButton.setVisibility(8);
            }
            WishLocalizedCurrencyValue commerceValue = this.mLoadedProduct.getCommerceValue();
            WishLocalizedCurrencyValue value = this.mLoadedProduct.getValue();
            if (this.mProductSource == Source.FREE_GIFT) {
                if (commerceValue.getValue() > 0.0d) {
                    this.mYourPriceText.setText(value.toFormattedString());
                } else {
                    this.mYourPriceText.setText(getString(R.string.free));
                }
            } else if (this.mProductSource != Source.POINTS_REDEMPTION || this.mLoadedProduct.getValueInPointsForVariation(this.mLoadedProduct.getDefaultCommerceVariationId()) <= 0) {
                if (commerceValue.getValue() > 0.0d) {
                    this.mYourPriceText.setText(commerceValue.toFormattedString());
                } else {
                    this.mYourPriceText.setText(getString(R.string.free));
                }
                if (value.getValue() <= commerceValue.getValue()) {
                    this.mListPriceText.setText("");
                } else if (value.getValue() > 0.0d) {
                    this.mListPriceText.setText(value.toFormattedString());
                } else {
                    this.mListPriceText.setText(getString(R.string.free));
                }
            } else {
                boolean z = true;
                this.mYourPriceText.setText(getString(R.string.pts_amount, Integer.valueOf(this.mLoadedProduct.getValueInPointsForVariation(this.mLoadedProduct.getDefaultCommerceVariationId()))));
                this.mListPriceText.setText(value.getValue() > commerceValue.getValue() ? value.toFormattedString() : "");
                if (this.mLoadedProduct == null || this.mLoadedProduct.getValueInPointsForVariation(this.mLoadedProduct.getDefaultCommerceVariationId()) <= 0 || this.mLoadedProduct.getValueInPointsForVariation(this.mLoadedProduct.getDefaultCommerceVariationId()) > this.mAvailabeRewardsPoints) {
                    z = false;
                }
                this.mAddToCartButton.setAlpha(z ? 1.0f : 0.4f);
                this.mAddToCartButton.setClickable(z);
            }
            if (TextUtils.isEmpty(this.mListPriceText.getText())) {
                this.mListPriceText.setVisibility(8);
            } else {
                this.mListPriceText.setVisibility(0);
            }
            this.mAddToCartButtonContainer.setVisibility(0);
        }
        if (this.mLoadedProduct == null || this.mLoadedProduct.getTaxText() == null) {
            this.mTaxTextSwapped.setVisibility(8);
            this.mTaxText.setVisibility(8);
        } else if (this.mShouldShowCrossedOutPriceLeft) {
            this.mTaxTextSwapped.setText(this.mLoadedProduct.getTaxText());
            this.mTaxTextSwapped.setVisibility(0);
        } else {
            this.mTaxText.setVisibility(0);
            this.mTaxText.setText(this.mLoadedProduct.getTaxText());
        }
        if (this.mLoadedProduct != null && this.mLoadedProduct.getPartnerBuyButtonPromoMessage() != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_PRODUCT_DETAILS_BUY_BANNER);
            setUpBuyButtonPromo();
        } else if (this.mLoadedProduct == null || this.mLoadedProduct.getAddToCartOffer() == null || this.mLoadedProduct.getAddToCartOffer().isExpired() || this.mProductSource == Source.POINTS_REDEMPTION) {
            this.mAddToCartOfferView.setVisibility(8);
            this.mAddToCartOfferArrow.setVisibility(8);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ADD_TO_CART_OFFER);
            setupTimerView();
        }
    }

    /* access modifiers changed from: private */
    public void animateOfferGone() {
        if (this.mAddToCartOfferTimer != null && this.mAddToCartOfferText != null) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(this.mAddToCartOfferText, "alpha", new float[]{1.0f, 0.0f}).setDuration(600);
            duration.setStartDelay(1000);
            ObjectAnimator duration2 = ObjectAnimator.ofFloat(this.mAddToCartOfferText, "alpha", new float[]{0.0f, 1.0f}).setDuration(600);
            duration2.setStartDelay(250);
            duration2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    ProductDetailsFragment.this.mAddToCartOfferText.setText(R.string.instant_offer_expired);
                }
            });
            this.mAddToCartOfferView.setPivotY((float) this.mAddToCartOfferView.getHeight());
            ObjectAnimator duration3 = ObjectAnimator.ofFloat(this.mAddToCartOfferView, "scaleY", new float[]{0.0f}).setDuration(600);
            duration3.setStartDelay(2000);
            ObjectAnimator duration4 = ObjectAnimator.ofFloat(this.mAddToCartOfferArrow, "scaleY", new float[]{0.0f}).setDuration(200);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{duration, duration2, duration3, duration4});
            animatorSet.start();
        }
    }

    public void handleLoadingFailure(final boolean z) {
        if (shouldShowFeedToDetailTransition()) {
            this.pageState = PageState.ERROR;
            updateUI();
        } else if (this.mShouldShowProductDetailTransition) {
            ((ProductDetailsActivity) getBaseActivity()).setThemeLoadFailure();
            getLoadingPageView().markLoadingErrored();
        } else {
            getLoadingPageView().markLoadingErrored();
        }
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(productDetailsActivity.getString(R.string.product_details_error_message)));
                if (z) {
                    ProductDetailsFragment.this.getHandler().postDelayed(new Runnable() {
                        public void run() {
                            ProductDetailsFragment.this.withActivity(new ActivityTask<ProductDetailsActivity>() {
                                public void performTask(ProductDetailsActivity productDetailsActivity) {
                                    productDetailsActivity.startHomeActivity();
                                }
                            });
                        }
                    }, 3000);
                }
            }
        });
    }

    private void initializeNavigationBar() {
        setupBaseActionBar();
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ActionBarManager actionBarManager = productDetailsActivity.getActionBarManager();
                if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                    actionBarManager.addActionBarItem(ActionBarItem.createCartActionBarItem(actionBarManager, false));
                }
                actionBarManager.addActionBarItem(ActionBarItem.createSearchActionBarItem(actionBarManager));
                if (ProductDetailsFragment.this.mProductSource == Source.POINTS_REDEMPTION) {
                    actionBarManager.setBadgeVisible(false);
                    actionBarManager.clearRightActionBarItems();
                    actionBarManager.setHomeButtonMode(HomeButtonMode.X_ICON);
                }
            }
        });
    }

    public boolean isWishPending(final String str) {
        final BooleanWrapper booleanWrapper = new BooleanWrapper(true);
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                booleanWrapper.state = productDetailsServiceFragment.isWishPending(str);
            }
        });
        return booleanWrapper.state;
    }

    public void handleRecommendClick(WishProduct wishProduct) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RECOMMEND);
        final String shareSubject = wishProduct.getShareSubject();
        final String shareMessage = wishProduct.getShareMessage();
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.showShareDialog(shareSubject, shareMessage);
                productDetailsActivity.shareEventTriggered();
            }
        });
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessengerShareClick() {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            com.contextlogic.wish.activity.BaseActivity r2 = r4.getBaseActivity()     // Catch:{ NameNotFoundException -> 0x0015 }
            com.contextlogic.wish.activity.productdetails.ProductDetailsActivity r2 = (com.contextlogic.wish.activity.productdetails.ProductDetailsActivity) r2     // Catch:{ NameNotFoundException -> 0x0015 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0015 }
            java.lang.String r3 = "com.facebook.orca"
            r2.getPackageInfo(r3, r1)     // Catch:{ NameNotFoundException -> 0x0015 }
            r1 = 1
            goto L_0x0015
        L_0x0013:
            r0 = move-exception
            goto L_0x005c
        L_0x0015:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r2.<init>()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r3 = "messenger_installed"
            java.lang.String r1 = java.lang.Boolean.toString(r1)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r2.put(r3, r1)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            com.contextlogic.wish.analytics.WishAnalyticsLogger$WishAnalyticsEvent r1 = com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent.CLICK_MOBILE_FB_MESSENGER_SHARE     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            com.contextlogic.wish.analytics.WishAnalyticsLogger.trackEvent(r1, r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r1.<init>()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r2 = "https://m.me/wish?ref=source_android_app-share_cid_button-uid_"
            r1.append(r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            com.contextlogic.wish.api.datacenter.ProfileDataCenter r2 = com.contextlogic.wish.api.datacenter.ProfileDataCenter.getInstance()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r2 = r2.getUserId()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r1.append(r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r2 = "-cid_"
            r1.append(r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            com.contextlogic.wish.api.model.WishProduct r2 = r4.mInitialProduct     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r2 = r2.getProductId()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r1.append(r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r2 = "-dest_bot"
            r1.append(r2)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            java.lang.String r1 = r1.toString()     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            android.content.Intent r0 = android.content.Intent.parseUri(r1, r0)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            r4.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x0087, URISyntaxException -> 0x0013 }
            goto L_0x0087
        L_0x005c:
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Messenger sharing button returned bad URI intent for cid "
            r2.append(r3)
            com.contextlogic.wish.api.model.WishProduct r3 = r4.mInitialProduct
            java.lang.String r3 = r3.getProductId()
            r2.append(r3)
            java.lang.String r3 = " with message "
            r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            com.crashlytics.android.Crashlytics.logException(r1)
        L_0x0087:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.handleMessengerShareClick():void");
    }

    private void customizeTabStrip() {
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ProductDetailsFragment.this.mTabStripContainer.setVisibility(0);
                ProductDetailsFragment.this.mTabStripShadow.setVisibility(0);
                ProductDetailsFragment.this.mTabBarHeight = productDetailsActivity.getResources().getDimensionPixelOffset(R.dimen.tab_bar_height);
                if (ProductDetailsFragment.this.getBaseActivity() != null && ((ProductDetailsActivity) ProductDetailsFragment.this.getBaseActivity()).getActionBarManager() != null) {
                    ((ProductDetailsActivity) ProductDetailsFragment.this.getBaseActivity()).getActionBarManager().stylizeTabStrip(ProductDetailsFragment.this.mTabStrip, ProductDetailsFragment.this.mShouldShowProductDetailTransition);
                }
            }
        });
    }

    private void refreshTabStripFontColors() {
        if (getBaseActivity() != null && ((ProductDetailsActivity) getBaseActivity()).getActionBarManager() != null) {
            ((ProductDetailsActivity) getBaseActivity()).getActionBarManager().refreshTabStripFontColors(this.mTabStrip, this.mProductViewPager.getCurrentItem(), this.mShouldShowProductDetailTransition);
        }
    }

    public boolean isOfferViewShowing() {
        return this.mAddToCartOfferView.getVisibility() == 0;
    }

    public void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.addRelatedProducts(arrayList, i2, z);
        }
    }

    public void loadProducts(final int i, String str, final int i2) {
        final FeedContext feedContext = new FeedContext();
        feedContext.requestId = str;
        if (i2 == 0) {
            this.mLastSeenProductIds.clear();
        }
        if (this.mExpressShippingOnlyFilter) {
            withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadRelatedExpressShippingProducts(feedContext.requestId, i2, 30);
                }
            });
        } else {
            withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.loadRelatedProducts(i, i2, feedContext);
                }
            });
        }
    }

    public void handleCurrentlyViewingLoadingSuccess(String str) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.handleCurrentlyViewingLoadingSuccess(str);
        }
    }

    public int getTabAreaOffset() {
        if (this.mShouldShowProductDetailTransition) {
            return 0;
        }
        return ((LayoutParams) this.mTabStripContainer.getLayoutParams()).topMargin;
    }

    private boolean isOverviewSection() {
        ProductDetailSection section = this.mPagerAdapter.getSection(getCurrentIndex());
        return section == null || section == ProductDetailSection.OVERVIEW;
    }

    public int getHiddenTabBarStripOffset() {
        return getTabAreaSize() * -1;
    }

    public void setTabAreaOffset(int i) {
        if (!this.mShouldShowProductDetailTransition) {
            this.mTabStripContainer.clearAnimation();
            LayoutParams layoutParams = (LayoutParams) this.mTabStripContainer.getLayoutParams();
            layoutParams.topMargin = Math.min(Math.max(i, getHiddenTabBarStripOffset()), 0);
            this.mTabStripContainer.setLayoutParams(layoutParams);
        } else if (isOverviewSection()) {
            if (this.mTabStripAnimator != null) {
                this.mTabStripAnimator.cancel();
            }
            setTabAreaAlpha(Math.max(0.0f, Math.min(1.0f, this.mTabStripContainer.getAlpha() + ((((float) i) * -1.0f) / ((float) (DisplayUtil.getDisplayHeight() * 3))))));
        }
    }

    /* access modifiers changed from: private */
    public void setTabAreaAlpha(float f) {
        this.mTabStripContainer.setAlpha(f);
        int i = 8;
        this.mTabStripContainer.setVisibility(f != 0.0f ? 0 : 8);
        float f2 = 1.0f - f;
        this.mTopShadow.setAlpha(f2);
        View view = this.mTopShadow;
        if (f2 != 0.0f) {
            i = 0;
        }
        view.setVisibility(i);
        if (getBaseActivity() != null && ((ProductDetailsActivity) getBaseActivity()).getActionBarManager() != null) {
            ((ProductDetailsActivity) getBaseActivity()).getActionBarManager().interpolateBackground(f);
        }
    }

    public void hideTabArea(boolean z) {
        if (!this.mShouldShowProductDetailTransition) {
            final int hiddenTabBarStripOffset = getHiddenTabBarStripOffset();
            int tabAreaOffset = getTabAreaOffset();
            if (tabAreaOffset != hiddenTabBarStripOffset) {
                this.mTabStripContainer.clearAnimation();
                int i = hiddenTabBarStripOffset - tabAreaOffset;
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i);
                translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i)) / ((double) getTabAreaSize())) * 250.0d)) : 0);
                translateAnimation.setFillAfter(false);
                translateAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        ProductDetailsFragment.this.setTabAreaOffset(hiddenTabBarStripOffset);
                    }
                });
                this.mTabStripContainer.startAnimation(translateAnimation);
            }
        } else if (isOverviewSection()) {
            showTabAreaInternal(z);
        }
    }

    public void showTabArea(boolean z) {
        if (!this.mShouldShowProductDetailTransition) {
            int tabAreaOffset = getTabAreaOffset();
            if (tabAreaOffset != 0) {
                this.mTabStripContainer.clearAnimation();
                int i = 0 - tabAreaOffset;
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i);
                translateAnimation.setDuration(z ? (long) ((int) ((((double) Math.abs(i)) / ((double) getTabAreaSize())) * 250.0d)) : 0);
                translateAnimation.setFillAfter(false);
                translateAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        ProductDetailsFragment.this.setTabAreaOffset(0);
                    }
                });
                this.mTabStripContainer.startAnimation(translateAnimation);
            }
        } else if (isOverviewSection()) {
            hideTabAreaInternal(z);
        }
    }

    private void showTabAreaInternal(boolean z) {
        animateAlpha(z, 1.0f);
    }

    private void hideTabAreaInternal(boolean z) {
        animateAlpha(z, 0.0f);
    }

    private void animateAlpha(boolean z, float f) {
        if (this.mTabStripAnimator != null) {
            this.mTabStripAnimator.cancel();
        }
        float alpha = this.mTabStripContainer.getAlpha();
        if (alpha != f) {
            this.mTabStripAnimator = ValueAnimator.ofFloat(new float[]{alpha, f});
            this.mTabStripAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ProductDetailsFragment.this.setTabAreaAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            this.mTabStripAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    ProductDetailsFragment.this.mTabStripAnimator = null;
                }

                public void onAnimationEnd(Animator animator) {
                    ProductDetailsFragment.this.mTabStripAnimator = null;
                }
            });
            this.mTabStripAnimator.setDuration(z ? (long) ((int) (((double) Math.abs(f - alpha)) * 250.0d)) : 0);
            this.mTabStripAnimator.start();
        }
    }

    private int getStatusBarOffset() {
        if (VERSION.SDK_INT >= 21) {
            return DisplayUtil.getStatusBarHeight();
        }
        return 0;
    }

    public int getTabAreaSize() {
        if (this.mShouldShowProductDetailTransition) {
            return this.mTabBarHeight + DisplayUtil.getActionBarHeight(getActivity()) + getStatusBarOffset();
        }
        return this.mTabBarHeight;
    }

    public String getProductId() {
        return this.mInitialProduct.getProductId();
    }

    /* access modifiers changed from: protected */
    public void handlePageSelected(int i) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAIL_SECTION);
        refreshTabStripFontColors();
        setBuyBarVisibility(0);
        ProductDetailSection section = this.mPagerAdapter.getSection(i);
        if (section != null) {
            switch (section) {
                case RELATED_PRODUCTS:
                    if (this.mFirstTimeRelated) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RELATED_TAB);
                        this.mFirstTimeRelated = false;
                    }
                    if (this.mShouldShowProductDetailTransition) {
                        showTabAreaInternal(true);
                    }
                    setBuyBarVisibility(8);
                    break;
                case DESCRIPTION:
                    if (this.mFirstTimeDescription) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DESCRIPTION_TAB);
                        this.mFirstTimeDescription = false;
                    }
                    if (this.mShouldShowProductDetailTransition) {
                        showTabAreaInternal(true);
                        break;
                    }
                    break;
                case PRODUCT_RATINGS:
                    if (this.mFirstTimeProductRating) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_RATING_TAB);
                        this.mFirstTimeProductRating = false;
                    }
                    if (this.mShouldShowProductDetailTransition) {
                        showTabAreaInternal(true);
                        break;
                    }
                    break;
                case STORE_RATINGS:
                    if (this.mFirstTimeStoreRating) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_STORE_RATING_TAB);
                        this.mFirstTimeStoreRating = false;
                    }
                    if (this.mShouldShowProductDetailTransition) {
                        showTabAreaInternal(true);
                        break;
                    }
                    break;
                case OVERVIEW:
                    if (this.mShouldShowProductDetailTransition) {
                        hideTabAreaInternal(true);
                        break;
                    }
                    break;
            }
        }
    }

    public int getCurrentIndex() {
        return this.mProductViewPager.getCurrentItem();
    }

    public void addPriceWatchListener(PriceWatchCallback priceWatchCallback) {
        this.mPriceWatchCallback = priceWatchCallback;
    }

    public void refreshPriceWatchState(boolean z) {
        if (this.mPriceWatchCallback != null) {
            this.mPriceWatchCallback.onPriceWatchStateChanges(z);
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollUnsettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollUnsettled();
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollSettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollSettled();
        }
    }

    public void refreshWishStates(boolean z) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.refreshWishStates(z);
        }
    }

    private void goToRestoredTab() {
        if (this.mPagerAdapter != null) {
            switchToPosition(this.mRestoredIndex, false);
        }
    }

    public void showProductRatings() {
        if (this.mPagerAdapter != null) {
            int sectionIndex = this.mPagerAdapter.getSectionIndex(ProductDetailSection.PRODUCT_RATINGS);
            if (sectionIndex != -1) {
                switchToPosition(sectionIndex, true);
            }
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_RATING_SEE_MORE);
    }

    public void showStoreRatings() {
        if (this.mPagerAdapter != null) {
            int sectionIndex = this.mPagerAdapter.getSectionIndex(ProductDetailSection.STORE_RATINGS);
            if (sectionIndex != -1) {
                switchToPosition(sectionIndex, true);
            }
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_STORE_RATING_SEE_MORE);
    }

    public void setMediaSources() {
        this.mMediaSources = new ArrayList<>();
        this.mMediaSources.add(new WishProductExtraImage(this.mLoadedProduct.getImage()));
        this.mMediaSources.addAll(this.mLoadedProduct.getExtraPhotos());
        int i = 1;
        if (this.mLoadedProduct.getVideoInfo() != null && 1 < this.mMediaSources.size()) {
            this.mMediaSources.add(1, new WishProductExtraImage(1, this.mLoadedProduct.getVideoInfo()));
            i = 2;
        }
        if (this.mLoadedProduct.getSlideshow() != null && i < this.mMediaSources.size()) {
            this.mMediaSources.add(i, new WishProductExtraImage(this.mLoadedProduct.getSlideshow()));
        }
    }

    public void showProductExtraPhotosImageViewer(final int i) {
        if (this.mLoadedProduct != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_EXTRA_PHOTOS, this.mLoadedProduct.getProductId());
            withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(final ProductDetailsActivity productDetailsActivity) {
                    Intent intent = new Intent();
                    intent.setClass(productDetailsActivity, ImageViewerActivity.class);
                    if (ProductDetailsFragment.this.mMediaSources == null) {
                        ProductDetailsFragment.this.setMediaSources();
                    }
                    IntentUtil.putParcelableArrayListExtra(intent, "ExtraMediaSources", ProductDetailsFragment.this.mMediaSources);
                    intent.putExtra("ExtraStartIndex", i);
                    intent.putExtra("ExtraProductId", ProductDetailsFragment.this.getProductId());
                    productDetailsActivity.startActivityForResult(intent, productDetailsActivity.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (i2 == -1) {
                                ProductDetailsActivity productDetailsActivity = productDetailsActivity;
                                ArrayList parcelableArrayListExtra = IntentUtil.getParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources");
                                if (parcelableArrayListExtra != null) {
                                    ProductDetailsFragment.this.mMediaSources = parcelableArrayListExtra;
                                }
                            }
                        }
                    }));
                }
            });
        }
    }

    public void showProductExtraPhotos(final int i) {
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(final ProductDetailsActivity productDetailsActivity) {
                if (ProductDetailsFragment.this.mLoadedProduct != null) {
                    Intent intent = new Intent();
                    intent.setClass(productDetailsActivity, PhotoVideoViewerActivity.class);
                    if (ProductDetailsFragment.this.mMediaSources == null) {
                        ProductDetailsFragment.this.setMediaSources();
                    }
                    IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraMediaSources", ProductDetailsFragment.this.mMediaSources);
                    intent.putExtra("ArgExtraStartIndex", i);
                    intent.putExtra("ArgExtraProductId", ProductDetailsFragment.this.getProductId());
                    productDetailsActivity.startActivityForResult(intent, productDetailsActivity.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (i2 == -1) {
                                ProductDetailsActivity productDetailsActivity = productDetailsActivity;
                                ArrayList parcelableArrayListExtra = IntentUtil.getParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources");
                                if (parcelableArrayListExtra != null) {
                                    ProductDetailsFragment.this.mMediaSources = parcelableArrayListExtra;
                                }
                            }
                        }
                    }));
                }
            }
        });
    }

    public void showRatingPhotosImageViewer(final WishRating wishRating, final int i) {
        if (i == 0) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_PHOTO);
        } else if (i == 1) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_VIDEO);
        }
        WishProductExtraImage extraImage = wishRating.getExtraImage();
        WishProductExtraImage extraVideo = wishRating.getExtraVideo();
        final ArrayList arrayList = new ArrayList();
        if (extraImage != null) {
            arrayList.add(extraImage);
        }
        if (extraVideo != null) {
            arrayList.add(extraVideo);
        }
        if (!arrayList.isEmpty()) {
            if (i == 1 && extraImage == null) {
                i = 0;
            }
            withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    Intent intent = new Intent();
                    intent.setClass(productDetailsActivity, ImageViewerActivity.class);
                    IntentUtil.putParcelableArrayListExtra(intent, "ExtraMediaSources", arrayList);
                    intent.putExtra("ExtraStartIndex", i);
                    intent.putExtra("ExtraProductId", ProductDetailsFragment.this.getProductId());
                    String str = "ExtraShowSingleImage";
                    boolean z = true;
                    if (arrayList.size() != 1) {
                        z = false;
                    }
                    intent.putExtra(str, z);
                    productDetailsActivity.startActivityForResult(intent, productDetailsActivity.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (i2 == -1 && intent != null) {
                                ArrayList parcelableArrayListExtra = IntentUtil.getParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources");
                                if (parcelableArrayListExtra != null) {
                                    WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) parcelableArrayListExtra.get(0);
                                    wishRating.setNumUpvotes(wishProductExtraImage.getUserUpvoteCount());
                                    wishRating.setUserUpvoted(wishProductExtraImage.hasUserUpvoted());
                                }
                            }
                        }
                    }));
                }
            });
        }
    }

    public void handleReload() {
        loadProduct();
        if (this.mShouldShowFeedToDetailTransition) {
            this.pageState = PageState.LOADING;
            updateUI();
        }
    }

    public void initializeLoadingContentView(View view) {
        this.mTabStripContainer = view.findViewById(R.id.product_details_fragment_viewpager_tab_container);
        this.mTabStripShadow = view.findViewById(R.id.product_details_fragment_viewpager_shadow);
        this.mTopShadow = view.findViewById(R.id.product_details_top_shadow);
        if (!this.mShouldShowProductDetailTransition) {
            this.mTopShadow.setVisibility(8);
        }
        if (this.mShouldShowProductDetailTransition) {
            this.mDealdashCountdownContainer = view.findViewById(R.id.product_details_fragment_countdown_container_bottom);
        } else {
            this.mDealdashCountdownContainer = view.findViewById(R.id.product_details_fragment_countdown_container);
        }
        this.mNewDealDashCountdownView = (TimerTextView) this.mDealdashCountdownContainer.findViewById(R.id.product_details_new_fragment_timer_top);
        this.mDealdashCountdownView = (CountdownTimerView) this.mDealdashCountdownContainer.findViewById(R.id.product_details_fragment_timer_top);
        this.mDealdashCountdownView.setCountCallback(new CountCallback() {
            public void onCount(int i) {
                if (i < 180) {
                    ProductDetailsFragment.this.setRushCountDownView();
                }
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mNewDealDashCountdownView.setVisibility(0);
            this.mDealdashCountdownView.setVisibility(8);
        }
        this.mDealdashCountdownText = (ThemedTextView) this.mDealdashCountdownContainer.findViewById(R.id.product_details_fragment_caption);
        if (this.mDealDashTargetDate != null) {
            if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon() || this.mDealDashCouponCode == null) {
                this.mDealdashCountdownText.setText(getActivity().getString(R.string.blitz_buy_countdown_text));
            } else {
                this.mDealdashCountdownContainer.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.deal_dash_coupon_minimum_height));
                this.mDealdashCountdownText.setLineSpacing(0.0f, 1.3f);
                this.mDealdashCountdownText.setText(getDealDashCouponStringSpan());
                this.mDealdashCountdownText.setFontResizable(true);
                this.mDealdashCountdownText.setMaxLines(2);
                this.mDealdashCountdownText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_COUPON_COPY);
                        if (ClipboardUtil.copyToClipboard(ProductDetailsFragment.this.mDealDashCouponCode)) {
                            ProductDetailsFragment.this.withActivity(new ActivityTask<ProductDetailsActivity>() {
                                public void performTask(ProductDetailsActivity productDetailsActivity) {
                                    SuccessBottomSheetDialog.create(productDetailsActivity).setTitle(ProductDetailsFragment.this.getString(R.string.copied_exclamation)).autoDismiss().show();
                                }
                            });
                        }
                    }
                });
            }
            this.mDealdashCountdownContainer.setVisibility(0);
            if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
                this.mNewDealDashCountdownView.setVisibility(0);
                this.mDealdashCountdownView.setVisibility(8);
                this.mNewDealDashCountdownView.setup(this.mDealDashTargetDate, new TimerWatcherAdapter() {
                    public void onCountdownComplete() {
                        ProductDetailsFragment.this.mDealdashCountdownContainer.setVisibility(8);
                    }
                }, false);
            } else {
                this.mNewDealDashCountdownView.setVisibility(8);
                this.mDealdashCountdownView.setVisibility(0);
                this.mDealdashCountdownView.setup(this.mDealDashTargetDate, getResources().getDimensionPixelSize(R.dimen.product_details_fragment_timer_height), getResources().getColor(R.color.white), getResources().getColor(R.color.black), getResources().getColor(R.color.white), R.drawable.timer_square, true, false, new DoneCallback() {
                    public void onCountdownEnd() {
                        ProductDetailsFragment.this.mDealdashCountdownContainer.setVisibility(8);
                    }
                });
                this.mDealdashCountdownView.startTimer();
            }
        }
        this.mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.product_details_fragment_viewpager_tabs);
        this.mProductViewPager = (SafeViewPager) view.findViewById(R.id.product_details_fragment_viewpager);
        this.mPagerAdapter = new ProductDetailsPagerAdapter((DrawerActivity) getActivity(), this, this.mProductViewPager);
        this.mProductViewPager.setAdapter(this.mPagerAdapter);
        this.mPagerAdapter.setProductRatingsImagePrefetcher(this.mProductRatingsImagePrefetcher);
        this.mPagerAdapter.setMerchantRatingsImagePrefetcher(this.mMerchantRatingsImagePrefetcher);
        this.mBuyBar = view.findViewById(R.id.buy_bar);
        setUpBuyBar(this.mBuyBar);
        initializeNavigationBar();
        if (!(this.mInitialProduct == null || this.mInitialProduct.getImage() == null || this.mInitialProduct.getImage().getUrlString(ImageSize.LARGE) == null || this.mInitialProduct.getAspectRatio() <= 0.0d)) {
            this.mImagePrefetcher.queueImage(this.mInitialProduct.getImage());
        }
        if (this.mShouldShowProductDetailTransition) {
            showTabArea(false);
            int actionBarHeight = DisplayUtil.getActionBarHeight(getActivity());
            InsetDrawable insetDrawable = new InsetDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.main_dark)), 0, 0, 0, actionBarHeight + this.mTabBarHeight);
            this.mTabStripContainer.setBackground(insetDrawable);
            this.mTabStripContainer.setPadding(0, actionBarHeight + getStatusBarOffset(), 0, 0);
        }
    }

    public String getTransitionImageUrl() {
        if (getBaseActivity() == null) {
            return null;
        }
        return ((ProductDetailsActivity) getBaseActivity()).getTransitionImageUrl();
    }

    private void setupAnimationAfterTransition() {
        if (VERSION.SDK_INT >= 21 && getBaseActivity() != null && shouldShowFeedToDetailTransition()) {
            ((ProductDetailsActivity) getBaseActivity()).setEnterSharedElementCallback(new SharedElementCallback() {
                public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
                    ProductDetailsFragment.this.getHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (ProductDetailsFragment.this.mIsTransitioning) {
                                ProductDetailsFragment.this.mIsTransitioning = false;
                                ProductDetailsFragment.this.updateUI();
                            }
                        }
                    }, 500);
                    ProductDetailsFragment.this.mTopShadow.setAlpha(0.0f);
                    ProductDetailsFragment.this.mTopShadow.animate().alpha(1.0f).setStartDelay(500).setDuration(1000).start();
                }
            });
        }
    }

    private void setUpBuyButtonPromo() {
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ProductDetailsFragment.this.mAddToCartOfferExpiredTextView.setVisibility(8);
                if (ProductDetailsFragment.this.mAddToCartOfferTimer != null) {
                    ProductDetailsFragment.this.mAddToCartOfferCounterContainer.removeView(ProductDetailsFragment.this.mAddToCartOfferTimer);
                }
                ProductDetailsFragment.this.mAddToCartOfferCounterContainer.setVisibility(8);
                ProductDetailsFragment.this.mAddToCartOfferView.setBackgroundResource(R.color.product_details_blue);
                ProductDetailsFragment.this.mAddToCartOfferArrow.setImageResource(R.drawable.littlebluearrow);
                ProductDetailsFragment.this.mAddToCartOfferText.setText(ProductDetailsFragment.this.mLoadedProduct.getPartnerBuyButtonPromoMessage());
                int dimensionPixelSize = ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.twelve_padding);
                int dimensionPixelSize2 = ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
                ProductDetailsFragment.this.mAddToCartOfferText.setPadding(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
                ProductDetailsFragment.this.mAddToCartOfferText.setTextSize(0, (float) ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.text_size_body));
                ProductDetailsFragment.this.mAddToCartOfferView.setVisibility(0);
                ProductDetailsFragment.this.mAddToCartOfferArrow.setVisibility(0);
            }
        });
    }

    private void setupTimerView() {
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ProductDetailsFragment.this.mAddToCartOfferView.setVisibility(0);
                ProductDetailsFragment.this.mAddToCartOfferArrow.setVisibility(0);
                ProductDetailsFragment.this.mAddToCartOfferView.setBackgroundResource(R.color.orange);
                ProductDetailsFragment.this.mAddToCartOfferText.setPadding(0, 0, ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.screen_padding), 0);
                ProductDetailsFragment.this.mAddToCartOfferText.setTextSize(0, (float) ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.buy_bar_add_to_cart_offer_text_size));
                ProductDetailsFragment.this.mAddToCartOfferExpiredTextView.setVisibility(8);
                if (ProductDetailsFragment.this.mAddToCartOfferTimer != null) {
                    ProductDetailsFragment.this.mAddToCartOfferCounterContainer.removeView(ProductDetailsFragment.this.mAddToCartOfferTimer);
                }
                ProductDetailsFragment.this.mAddToCartOfferTimer = new CountdownTimerView(productDetailsActivity);
                if (ExperimentDataCenter.getInstance().shouldHideOfferView() || ExperimentDataCenter.getInstance().shouldSeeDifferentExpiredText()) {
                    ProductDetailsFragment.this.mAddToCartOfferTimer.disableExpiredText();
                }
                ProductDetailsFragment.this.mAddToCartOfferTimer.setup(ProductDetailsFragment.this.mLoadedProduct.getAddToCartOffer().getExpiry(), ProductDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.cart_timer_height), ProductDetailsFragment.this.getResources().getColor(R.color.white), ProductDetailsFragment.this.getResources().getColor(R.color.dark_gray_1), (DoneCallback) new DoneCallback() {
                    public void onCountdownEnd() {
                        ProductDetailsFragment.this.mAddToCartOfferTimer.pauseTimer();
                        if (ProductDetailsFragment.this.mAddToCartOfferView == null) {
                            return;
                        }
                        if (ExperimentDataCenter.getInstance().shouldHideOfferView()) {
                            ProductDetailsFragment.this.animateOfferGone();
                        } else if (ExperimentDataCenter.getInstance().shouldSeeDifferentExpiredText()) {
                            ProductDetailsFragment.this.mAddToCartOfferTimer.setBlankBackgroundColorResId(R.color.expired_timer_dark_orange);
                            ProductDetailsFragment.this.mAddToCartOfferText.setTextColor(ProductDetailsFragment.this.getResources().getColor(R.color.expired_timer_dark_orange));
                            ProductDetailsFragment.this.mAddToCartOfferExpiredTextView.setVisibility(0);
                            ProductDetailsFragment.this.mAddToCartOfferExpiredTextView.bringToFront();
                        }
                    }
                });
                ProductDetailsFragment.this.mAddToCartOfferTimer.startTimer();
                ProductDetailsFragment.this.mAddToCartOfferCounterContainer.addView(ProductDetailsFragment.this.mAddToCartOfferTimer);
                ProductDetailsFragment.this.mAddToCartOfferArrow.setImageResource(R.drawable.add_to_cart_offer_arrow_down);
                ProductDetailsFragment.this.mAddToCartOfferText.setText(ProductDetailsFragment.this.mLoadedProduct.getAddToCartOffer().getTitle());
            }
        });
    }

    /* access modifiers changed from: private */
    public void setRushCountDownView() {
        if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mDealdashCountdownContainer.setBackgroundColor(getResources().getColor(R.color.red));
            this.mDealdashCountdownText.setText(getString(R.string.blitz_buy_countdown_text_rush));
        }
    }

    public void handleWishlistProductActionSuccess() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.refreshWishStates(true);
        }
    }

    public void handleWishlistProductActionSuccess(String str) {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.refreshWishStates(true);
            if (str != null) {
                this.mPagerAdapter.wishlistAddSuccess(str);
            }
        }
    }

    public void showRatingAuthorProfile(WishRating wishRating) {
        if (wishRating.getAuthor().getUserState() == WishUserState.Registered && !wishRating.isSyndicated()) {
            final String userId = wishRating.getAuthor().getUserId();
            withActivity(new ActivityTask<ProductDetailsActivity>() {
                public void performTask(ProductDetailsActivity productDetailsActivity) {
                    Intent intent = new Intent();
                    intent.setClass(productDetailsActivity, ProfileActivity.class);
                    intent.putExtra(ProfileActivity.EXTRA_USER_ID, userId);
                    productDetailsActivity.startActivity(intent);
                }
            });
        }
    }

    public boolean hasItems() {
        boolean z = false;
        if (this.mShouldShowFeedToDetailTransition) {
            if (getLoadedProduct() != null) {
                z = true;
            }
            return z;
        }
        if (this.mLoadedProduct != null) {
            z = true;
        }
        return z;
    }

    public boolean isDealDashProduct() {
        return this.mDealDashTargetDate != null;
    }

    public void setBuyBarVisibility(int i) {
        this.mBuyBar.setVisibility(i);
    }

    public void loadRelatedExpressShippingItems() {
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.loadRelatedExpressShippingProductsRow(ProductDetailsFragment.this.getLoadedProduct().getProductId(), 0, 30);
            }
        });
    }

    public void loadVisuallySimilarItems() {
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.loadVisuallySimilarProductRow(ProductDetailsFragment.this.getLoadedProduct().getProductId(), 0, 30);
            }
        });
    }

    public CharSequence getAddToCartButtonText() {
        return this.mAddToCartButton.getText();
    }

    public void showWishStarDialog(String str) {
        final WishStarDialogFragment createWishStarDialogFragment = WishStarDialogFragment.createWishStarDialogFragment(str);
        withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.startDialog(createWishStarDialogFragment);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_STAR_DIALOG);
            }
        });
    }

    public Source getSource() {
        return this.mProductSource;
    }

    public SpannableString getDealDashCouponStringSpan() {
        String str = this.mDealDashCouponCode;
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        sb.append(str);
        sb.append("   ");
        String sb2 = sb.toString();
        String format = String.format(getString(R.string.blitz_buy_coupon_countdown_text), new Object[]{sb2, this.mDealDashPercentOff});
        int indexOf = format.indexOf(sb2);
        if (indexOf != -1) {
            int length = sb2.length() + indexOf;
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new BoldBorderSpan(getResources().getDrawable(R.drawable.deal_dash_coupon_drawable), getResources().getDimensionPixelOffset(R.dimen.two_padding), this.mDealdashCountdownText.getLineSpacingMultiplier()), indexOf + 1, length - 1, 33);
            return spannableString;
        }
        Crashlytics.logException(new Exception("ProductDetailsDealDash: Invalid index of coupon!"));
        return new SpannableString(format);
    }

    /* access modifiers changed from: private */
    public void redeemRewardProduct(final WishProduct wishProduct) {
        withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(final BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.redeemRewardProduct(wishProduct, new DefaultSuccessCallback() {
                    public void onSuccess() {
                        baseActivity.setResult(-1);
                    }
                });
            }
        });
    }
}
