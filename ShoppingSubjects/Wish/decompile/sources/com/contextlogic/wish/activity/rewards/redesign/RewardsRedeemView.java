package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardProductsAdapter.OnClickListener;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardsAdapter.Callback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnScrollListener;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.HashMap;

public class RewardsRedeemView extends RewardsPagerView {
    private ThemedTextView mAvailablePoints;
    private ThemedTextView mAvailableText;
    /* access modifiers changed from: private */
    public RewardsFragment mFragment;
    private StaggeredGridView mGridView;
    private ListeningListView mListView;
    /* access modifiers changed from: private */
    public WishRewardsRedeemableInfo mRewardInfo;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.rewards_fragment_redeem;
    }

    public RewardsRedeemView(Context context) {
        super(context);
    }

    public void setup(int i, RewardsFragment rewardsFragment) {
        super.setup(i, rewardsFragment);
        this.mFragment = rewardsFragment;
        this.mListView = (ListeningListView) this.mRootLayout.findViewById(R.id.rewards_fragment_redeem_coupons_list);
        this.mGridView = (StaggeredGridView) this.mRootLayout.findViewById(R.id.rewards_fragment_redeem_coupons_grid);
        this.mAvailableText = (ThemedTextView) this.mRootLayout.findViewById(R.id.rewards_fragment_redeem_footer_available_text);
        this.mAvailablePoints = (ThemedTextView) this.mRootLayout.findViewById(R.id.rewards_fragment_redeem_footer_available_points);
        loadNextPage();
    }

    private void setupRedeemableItemListView(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        this.mListView.setVisibility(0);
        this.mGridView.setVisibility(8);
        this.mListView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                RewardsRedeemView.this.handleScrollChanged(i, i2);
            }
        });
        RedeemableRewardsAdapter redeemableRewardsAdapter = new RedeemableRewardsAdapter(this.mFragment, WishApplication.getInstance().getResources().getString(R.string.available));
        redeemableRewardsAdapter.setOnClickListener(new Callback() {
            public void onClickListener(WishRedeemableRewardItem wishRedeemableRewardItem) {
                RewardsRedeemView.this.onWishRedeemableRewardItemClicked(wishRedeemableRewardItem);
            }
        });
        if (this.mListView.getFooterViewsCount() == 0) {
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding)));
            this.mListView.addFooterView(view);
        }
        if (this.mListView.getHeaderViewsCount() == 0) {
            this.mListView.addHeaderView(createRewardsRedeemHeader(wishRewardsRedeemableInfo));
        }
        this.mListView.setAdapter(redeemableRewardsAdapter);
        redeemableRewardsAdapter.setWishRewardItems(wishRewardsRedeemableInfo.getRedeemableRewards());
        redeemableRewardsAdapter.notifyDataSetChanged();
    }

    private RewardsRedeemHeaderView createRewardsRedeemHeader(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        RewardsRedeemHeaderView rewardsRedeemHeaderView = new RewardsRedeemHeaderView(getContext());
        rewardsRedeemHeaderView.setup(wishRewardsRedeemableInfo.getTitle(), wishRewardsRedeemableInfo.getDescription(), this.mFragment);
        return rewardsRedeemHeaderView;
    }

    private View createRedeemableProductGridHeader(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.transparent_sixteen_divider));
        linearLayout.setShowDividers(2);
        linearLayout.addView(createRewardsRedeemHeader(wishRewardsRedeemableInfo));
        linearLayout.addView(createTitleView(R.string.coupon_rewards, 0));
        RedeemableRewardsAdapter redeemableRewardsAdapter = new RedeemableRewardsAdapter(this.mFragment, WishApplication.getInstance().getResources().getString(R.string.available));
        redeemableRewardsAdapter.setWishRewardItems(wishRewardsRedeemableInfo.getRedeemableRewards());
        for (int i = 0; i < redeemableRewardsAdapter.getCount(); i++) {
            RewardsRowItem view = redeemableRewardsAdapter.getView(i, (View) null, (ViewGroup) linearLayout);
            view.setItem(redeemableRewardsAdapter.getItem(i), new Callback() {
                public void onClickListener(WishRedeemableRewardItem wishRedeemableRewardItem) {
                    RewardsRedeemView.this.onWishRedeemableRewardItemClicked(wishRedeemableRewardItem);
                }
            });
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            linearLayout.addView(view);
        }
        linearLayout.addView(createTitleView(R.string.item_rewards, getResources().getDimensionPixelSize(R.dimen.eight_padding)));
        return linearLayout;
    }

    private ThemedTextView createTitleView(int i, int i2) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.gray2));
        themedTextView.setTextSize(0, (float) getContext().getResources().getDimensionPixelSize(R.dimen.text_size_sixteen));
        themedTextView.setTypeface(1);
        themedTextView.setText(i);
        themedTextView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        themedTextView.setPadding(dimensionPixelSize, i2, dimensionPixelSize, 0);
        return themedTextView;
    }

    private void setupRedeemableProductGridView(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        this.mListView.setVisibility(8);
        this.mGridView.setVisibility(0);
        this.mGridView.setOnScrollListener(new OnScrollListener() {
            public void onScrollChanged(int i, int i2, int i3, int i4) {
                RewardsRedeemView.this.handleScrollChanged(i, i2);
            }
        });
        RedeemableRewardProductsAdapter redeemableRewardProductsAdapter = new RedeemableRewardProductsAdapter(getContext());
        if (this.mGridView.getFooterView() == null) {
            View view = new View(getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding)));
            this.mGridView.setFooterView(view);
        }
        this.mGridView.removeHeaderViews();
        this.mGridView.setHeaderView(createRedeemableProductGridHeader(wishRewardsRedeemableInfo));
        redeemableRewardProductsAdapter.setup(wishRewardsRedeemableInfo, createRedeemableProductClickListener());
        this.mGridView.setAdapter(redeemableRewardProductsAdapter);
    }

    private OnClickListener createRedeemableProductClickListener() {
        return new OnClickListener() {
            public void onImageClicked(WishProduct wishProduct) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REDEEMABLE_REWARD_PRODUCT_FEED_IMAGE);
                RewardsRedeemView.this.launchProductDetails(wishProduct);
            }

            public void onRedeemItemClicked(final WishProduct wishProduct) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REDEEMABLE_REWARD_PRODUCT_FEED_REDEEM);
                RewardsRedeemView.this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                        rewardsServiceFragment.redeemRewardProduct(wishProduct, new DefaultSuccessCallback() {
                            public void onSuccess() {
                                RewardsRedeemView.this.reload();
                            }
                        });
                    }
                });
            }
        };
    }

    /* access modifiers changed from: private */
    public void onWishRedeemableRewardItemClicked(final WishRedeemableRewardItem wishRedeemableRewardItem) {
        if (!wishRedeemableRewardItem.isDisabled()) {
            HashMap hashMap = new HashMap();
            hashMap.put("discount_badge_text", wishRedeemableRewardItem.getDiscountBadgeText());
            hashMap.put("reward_type", Integer.toString(wishRedeemableRewardItem.getRewardType()));
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REDEEMABLE_REWARD_ITEM.getValue(), (String) null, hashMap);
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                    rewardsServiceFragment.showRedeemCouponDialog(wishRedeemableRewardItem);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchProductDetails(final WishProduct wishProduct) {
        this.mFragment.withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                Intent intent = new Intent();
                intent.setClass(RewardsRedeemView.this.getContext(), ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, wishProduct);
                intent.putExtra("ArgExtraSource", Source.POINTS_REDEMPTION);
                intent.putExtra("ArgExtraAvailableRewardsPoints", RewardsRedeemView.this.mRewardInfo.getAvailablePoints());
                rewardsActivity.startActivityForResult(intent, rewardsActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 == -1) {
                            RewardsRedeemView.this.reload();
                        }
                    }
                }));
            }
        });
    }

    public int getCurrentScrollY() {
        if (this.mListView != null && this.mListView.getVisibility() == 0) {
            return this.mListView.getCurrentScrollY();
        }
        if (this.mGridView == null || this.mGridView.getVisibility() != 0) {
            return 0;
        }
        return this.mGridView.getScrollY();
    }

    public int getFirstItemPosition() {
        if (this.mListView != null && this.mListView.getVisibility() == 0) {
            return this.mListView.getFirstVisiblePosition();
        }
        if (this.mGridView == null || this.mGridView.getVisibility() != 0) {
            return 0;
        }
        return this.mGridView.getFirstItemPosition();
    }

    private void loadNextPage() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                rewardsServiceFragment.getRedeemableRewardsInfo();
            }
        });
    }

    public void handleLoadingSuccess(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        this.mRewardInfo = wishRewardsRedeemableInfo;
        this.mAvailableText.setText(wishRewardsRedeemableInfo.getFooterText());
        this.mAvailablePoints.setText(wishRewardsRedeemableInfo.getFooterAvailablePoints());
        if (wishRewardsRedeemableInfo.getRedeemableProducts().isEmpty()) {
            setupRedeemableItemListView(wishRewardsRedeemableInfo);
        } else {
            setupRedeemableProductGridView(wishRewardsRedeemableInfo);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEMABLE_REWARD_PRODUCT_FEED);
        }
        markLoadingComplete();
    }

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                rewardsServiceFragment.cancelLoadingRedeemableRewardsInfo();
            }
        });
    }

    public void onFailure() {
        queuePagerSettledTask(new Runnable() {
            public void run() {
                RewardsRedeemView.this.handleLoadingFailure();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mFragment.withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                rewardsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(rewardsActivity.getString(R.string.rewards_error_message)));
            }
        });
    }

    public void cleanup() {
        releaseImages();
        cancelNetworkRequest();
    }

    public void releaseImages() {
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void handleReload() {
        loadNextPage();
    }
}
