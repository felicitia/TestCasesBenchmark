package com.contextlogic.wish.activity.feed.freegifts;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.FeedTileLogger.FeedType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnViewVisibleListener;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.FreeGiftsCountdownTimerView;
import java.util.HashSet;

public class FreeGiftsTabProductFeedView extends BaseProductFeedView {
    private TextView mClaimButton;
    private FreeGiftsCountdownTimerView mCountdownTimerView;
    private WishFreeGiftTabInfo mFreeGiftTabInfo;
    /* access modifiers changed from: private */
    public ProductFeedFragment mProductFeedFragment;
    private View mSplashView;
    private TextView mSubtitle;
    private TextView mTitle;
    /* access modifiers changed from: private */
    public HashSet<String> mVisibleProducts;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.free_gifts_tab_product_feed;
    }

    public FreeGiftsTabProductFeedView(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment, WishFreeGiftTabInfo wishFreeGiftTabInfo) {
        super(i, drawerActivity, productFeedFragment);
        this.mProductFeedFragment = productFeedFragment;
        markLoadingComplete();
        updateFreeGiftTabInfo(wishFreeGiftTabInfo);
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        this.mVisibleProducts = new HashSet<>();
        this.mSplashView = view.findViewById(R.id.free_gifts_product_feed_splash_view);
        this.mTitle = (TextView) view.findViewById(R.id.free_gifts_product_feed_splash_title);
        this.mSubtitle = (TextView) view.findViewById(R.id.free_gifts_product_feed_splash_subtitle);
        this.mClaimButton = (TextView) view.findViewById(R.id.free_gifts_tab_product_feed_claim_button);
        this.mClaimButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_SPLASH_CLAIM);
                FreeGiftsTabProductFeedView.this.hideSplashScreen();
            }
        });
        ((TextView) view.findViewById(R.id.free_gifts_tab_product_feed_skip_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_SPLASH_REMIND_LATER);
                FreeGiftsTabProductFeedView.this.handleSkip();
            }
        });
        this.mCountdownTimerView = (FreeGiftsCountdownTimerView) view.findViewById(R.id.free_gifts_tab_product_feed_timer);
        this.mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                final WishProduct item = FreeGiftsTabProductFeedView.this.mAdapter.getItem(i);
                FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.CLICKED, i, item.getVideoStatus().ordinal(), FeedType.FREE_GIFT.toString());
                if (item != null) {
                    FreeGiftsTabProductFeedView.this.mProductFeedFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_FEED_VIEW_PRODUCT);
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, ProductDetailsActivity.class);
                            ProductDetailsActivity.addInitialProduct(intent, item);
                            intent.putExtra("ArgExtraSource", Source.FREE_GIFT);
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            }
        });
        this.mGridView.setOnViewVisibleListener(new OnViewVisibleListener() {
            public void onViewVisible(int i, View view) {
                WishProduct item = FreeGiftsTabProductFeedView.this.mAdapter.getItem(i);
                String productId = item.getProductId();
                if (!FreeGiftsTabProductFeedView.this.mVisibleProducts.contains(productId)) {
                    FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.IMPRESSION, i, item.getVideoStatus().ordinal(), FeedType.FREE_GIFT.toString());
                    FreeGiftsTabProductFeedView.this.mVisibleProducts.add(productId);
                }
            }
        });
    }

    private void updateFreeGiftTabInfo(WishFreeGiftTabInfo wishFreeGiftTabInfo) {
        this.mFreeGiftTabInfo = wishFreeGiftTabInfo;
        this.mTitle.setText(this.mFreeGiftTabInfo.getSplashTitle());
        this.mSubtitle.setText(this.mFreeGiftTabInfo.getSplashSubTitle());
        this.mClaimButton.setText(this.mFreeGiftTabInfo.getClaimText());
        if (wishFreeGiftTabInfo.showSplash()) {
            WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_SPLASH);
            this.mSplashView.setVisibility(0);
            this.mCountdownTimerView.setup(this.mFreeGiftTabInfo.getExpiry(), new DoneCallback() {
                public void onCountdownEnd() {
                }
            });
            this.mCountdownTimerView.startTimer();
            return;
        }
        this.mSplashView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void hideSplashScreen() {
        this.mSplashView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void handleSkip() {
        this.mSplashView.setVisibility(8);
        this.mProductFeedFragment.handleFreeGiftRemindMeLater();
    }

    /* access modifiers changed from: protected */
    public BaseProductFeedAdapter initializeAdapter() {
        return new FreeGiftsTabAdapter(this.mBaseActivity, this.mFragment);
    }

    public void releaseImages() {
        super.releaseImages();
        if (this.mCountdownTimerView != null) {
            this.mCountdownTimerView.pauseTimer();
        }
    }

    public void restoreImages() {
        super.restoreImages();
        if (this.mCountdownTimerView != null && this.mCountdownTimerView.isSetup() && this.mFreeGiftTabInfo != null) {
            this.mCountdownTimerView.startTimer();
        }
    }
}
