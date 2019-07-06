package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.LoadingFooterViewCallback;
import com.contextlogic.wish.ui.starrating.RedesignedBlueStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.google.android.flexbox.FlexboxLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public abstract class ProductDetailsRatingsView extends ProductDetailsPagerView {
    protected TextView mActionTextView;
    protected List<SortFilterButton> mFilterButtons;
    protected FlexboxLayout mFilterGroup;
    private ImageHttpPrefetcher mImagePrefetcher;
    protected NetworkImageView mImageView;
    protected ProductDetailsRatingsAdapter mListAdapter;
    protected ListeningListView mListView;
    protected LoadingFooterView mLoadingFooter;
    protected int mNextOffset;
    protected TextView mOverallRating;
    protected TextView mOverallRatingTitle;
    protected Set<String> mRatingIds;
    protected View mRatingSpacer;
    protected TextView mRatingStarText;
    protected WishRatingSummary mRatingSummary;
    protected ArrayList<WishRating> mRatings;
    /* access modifiers changed from: private */
    public FilterType mSelectedFilter = FilterType.ALL;
    protected View mSpacerView;
    protected RedesignedBlueStarRatingView mStarRatingView;
    protected TextView mTitleView;
    protected boolean mUpdatingList;

    /* access modifiers changed from: protected */
    public abstract void cancelNetworkRequest();

    /* access modifiers changed from: protected */
    public abstract String getActionButtonText();

    public int getLoadingContentLayoutResourceId() {
        return R.layout.product_details_fragment_ratings;
    }

    public abstract void handleActionClick();

    /* access modifiers changed from: protected */
    public abstract boolean isNetworkRequestPending();

    /* access modifiers changed from: protected */
    public abstract void performNetworkRequest();

    public void refreshWishStates(boolean z) {
    }

    /* access modifiers changed from: protected */
    public abstract void setUpFilters();

    public boolean shouldShowUpvote() {
        return false;
    }

    public ProductDetailsRatingsView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mRatings = new ArrayList<>();
        this.mRatingIds = new HashSet();
    }

    public boolean hasItems() {
        return this.mRatings.size() > 0 || this.mRatingSummary != null;
    }

    public void setup(WishProduct wishProduct, int i, ProductDetailsFragment productDetailsFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        View view;
        super.setup(wishProduct, i, productDetailsFragment);
        markShowNoMoreItemsFooter();
        this.mImagePrefetcher = imageHttpPrefetcher;
        setNoItemsMessage(WishApplication.getInstance().getString(R.string.no_reviews_found));
        this.mListView = (ListeningListView) this.mRootLayout.findViewById(R.id.product_details_fragment_ratings_listview);
        this.mListView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                ProductDetailsRatingsView.this.handleScrollChanged(i, i2);
            }
        });
        setupScroller(this.mListView);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProductRatingHeader()) {
            view = inflate(this.mRootLayout.getContext(), R.layout.merchant_tab_banner_redesign, null);
            this.mOverallRatingTitle = (TextView) view.findViewById(R.id.merchant_overall_title);
            this.mOverallRating = (TextView) view.findViewById(R.id.merchant_overall_rating);
            this.mRatingSpacer = view.findViewById(R.id.merchant_tab_ratings_spacer);
        } else {
            view = inflate(this.mRootLayout.getContext(), R.layout.merchant_tab_banner, null);
            this.mTitleView = (TextView) view.findViewById(R.id.merchant_tab_banner_name);
            this.mImageView = (NetworkImageView) view.findViewById(R.id.merchant_tab_banner_image);
            this.mImageView.setCircleCrop(true);
        }
        this.mFilterGroup = (FlexboxLayout) view.findViewById(R.id.merchant_tab_ratings_filter_group);
        this.mFilterButtons = new ArrayList();
        this.mListView.addHeaderView(view);
        this.mSpacerView = view.findViewById(R.id.merchant_tab_banner_spacer);
        this.mSpacerView.setLayoutParams(new LayoutParams(-1, productDetailsFragment.getTabAreaSize()));
        this.mActionTextView = (TextView) view.findViewById(R.id.merchant_tab_banner_action_text);
        this.mStarRatingView = (RedesignedBlueStarRatingView) view.findViewById(R.id.merchant_tab_banner_star_ratings_view);
        this.mRatingStarText = (TextView) view.findViewById(R.id.merchant_tab_banner_ratings_count);
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                ProductDetailsRatingsView.this.mLoadingFooter = new LoadingFooterView(productDetailsActivity);
                ProductDetailsRatingsView.this.mLoadingFooter.setPadding(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.loading_spinner_padding_top), 0, 0);
                ProductDetailsRatingsView.this.mLoadingFooter.setCallback(new LoadingFooterViewCallback() {
                    public void onTapToLoad() {
                        ProductDetailsRatingsView.this.loadNextPage();
                    }
                });
                ProductDetailsRatingsView.this.mListView.addFooterView(ProductDetailsRatingsView.this.mLoadingFooter);
                ProductDetailsRatingsView.this.setLoadingFooter(ProductDetailsRatingsView.this.mLoadingFooter);
            }
        });
        this.mListView.setFadingEdgeLength(0);
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                ProductDetailsRatingsView.this.handleScrollLoad(i, i2, i3);
            }
        });
        ProductDetailsRatingsAdapter productDetailsRatingsAdapter = new ProductDetailsRatingsAdapter(this.mFragment.getActivity(), this.mFragment, this.mRatings, this.mListView, this.mImagePrefetcher, this);
        this.mListAdapter = productDetailsRatingsAdapter;
        this.mListView.setAdapter(this.mListAdapter);
        this.mListAdapter.notifyDataSetChanged();
        loadNextPage();
        if (this.mFragment.getSavedInstanceState(i) != null) {
            restorePosition(this.mFragment.getSavedInstanceState(i).getInt("SavedStateFirstItemPosition"));
        }
        setUpFilters();
    }

    private void setUpFilterCounts() {
        if (this.mFilterButtons.size() > 0) {
            this.mFilterGroup.setVisibility(0);
        }
        if (this.mRatingSummary.getReviewSpreads() != null) {
            for (SortFilterButton sortFilterButton : this.mFilterButtons) {
                switch (sortFilterButton.getFilterType()) {
                    case ALL:
                        sortFilterButton.setCount(this.mRatingSummary.getReviewCount());
                        sortFilterButton.setShowCount(true);
                        break;
                    case RATING_1:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(0)).intValue());
                        break;
                    case RATING_2:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(1)).intValue());
                        break;
                    case RATING_3:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(2)).intValue());
                        break;
                    case RATING_4:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(3)).intValue());
                        break;
                    case RATING_5:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(4)).intValue());
                        break;
                    case PHOTO:
                        sortFilterButton.setCount((long) ((Integer) this.mRatingSummary.getReviewSpreads().get(5)).intValue());
                        sortFilterButton.setShowCount(true);
                        break;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public SortFilterButton addFilterButton(FilterType filterType) {
        final SortFilterButton sortFilterButton = new SortFilterButton(getContext());
        sortFilterButton.setFilterType(filterType);
        this.mFilterGroup.addView(sortFilterButton);
        this.mFilterButtons.add(sortFilterButton);
        sortFilterButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductDetailsRatingsView.this.mSelectedFilter = sortFilterButton.getFilterType();
                WishAnalyticsLogger.trackEvent(ProductDetailsRatingsView.this.mSelectedFilter.mLogEvent);
                ProductDetailsRatingsView.this.resetFilterButtonStates();
                sortFilterButton.setChecked(true);
                ProductDetailsRatingsView.this.reloadReviews();
            }
        });
        int dimension = (int) getResources().getDimension(R.dimen.eight_padding);
        ((FlexboxLayout.LayoutParams) sortFilterButton.getLayoutParams()).setMargins(0, 0, dimension, dimension);
        return sortFilterButton;
    }

    /* access modifiers changed from: protected */
    public FilterType getSelectedFilterType() {
        return this.mSelectedFilter;
    }

    /* access modifiers changed from: private */
    public void resetFilterButtonStates() {
        for (SortFilterButton checked : this.mFilterButtons) {
            checked.setChecked(false);
        }
    }

    public void cleanup() {
        releaseImages();
        cancelNetworkRequest();
    }

    public void releaseImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.releaseImages();
        }
        if (this.mImageView != null) {
            this.mImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.restoreImages();
        }
        if (this.mImageView != null) {
            this.mImageView.restoreImages();
        }
    }

    public int getCurrentScrollY() {
        if (this.mListView != null) {
            return this.mListView.getCurrentScrollY();
        }
        return 0;
    }

    public void onPagerScrollSettled() {
        super.onPagerScrollSettled();
        if (this.mFragment.getCurrentIndex() == this.mIndex) {
            refreshRowTimestamps();
        }
    }

    private void refreshRowTimestamps() {
        if (this.mListAdapter != null) {
            this.mListAdapter.refreshTimestamps();
        }
    }

    /* access modifiers changed from: protected */
    public void setupHeader() {
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                if (ExperimentDataCenter.getInstance().shouldSeeNewProductRatingHeader()) {
                    ProductDetailsRatingsView.this.mOverallRating.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(ProductDetailsRatingsView.this.mRatingSummary.getRating())}));
                    ProductDetailsRatingsView.this.mOverallRatingTitle.setText(R.string.rating_overall_title);
                    ProductDetailsRatingsView.this.mStarRatingView.setup(ProductDetailsRatingsView.this.mRatingSummary.getRating(), Size.INTERMEDIATE, null);
                    ProductDetailsRatingsView.this.mRatingStarText.setText(ProductDetailsRatingsView.this.mFragment.getResources().getQuantityString(R.plurals.ratings_capitalized_no_bracket, (int) ProductDetailsRatingsView.this.mRatingSummary.getRatingCount(), new Object[]{Integer.valueOf((int) ProductDetailsRatingsView.this.mRatingSummary.getRatingCount())}));
                    if (ProductDetailsRatingsView.this.getActionButtonText() != null) {
                        ProductDetailsRatingsView.this.mActionTextView.setVisibility(0);
                        ProductDetailsRatingsView.this.mActionTextView.setText(ProductDetailsRatingsView.this.getActionButtonText());
                        ProductDetailsRatingsView.this.mActionTextView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                ProductDetailsRatingsView.this.handleActionClick();
                            }
                        });
                        return;
                    }
                    return;
                }
                ProductDetailsRatingsView.this.mTitleView.setText(ProductDetailsRatingsView.this.mRatingSummary.getDisplayName());
                ProductDetailsRatingsView.this.mImageView.setImage(new WishImage(ProductDetailsRatingsView.this.mRatingSummary.getImageUrl()));
                ProductDetailsRatingsView.this.mStarRatingView.setup(ProductDetailsRatingsView.this.mRatingSummary.getRating(), Size.INTERMEDIATE, null);
                ProductDetailsRatingsView.this.mRatingStarText.setText(ProductDetailsRatingsView.this.mFragment.getResources().getQuantityString(R.plurals.ratings_capitalized_no_bracket, (int) ProductDetailsRatingsView.this.mRatingSummary.getRatingCount(), new Object[]{Integer.valueOf((int) ProductDetailsRatingsView.this.mRatingSummary.getRatingCount())}));
                ProductDetailsRatingsView.this.mActionTextView.setText(ProductDetailsRatingsView.this.getActionButtonText());
                ProductDetailsRatingsView.this.mActionTextView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ProductDetailsRatingsView.this.handleActionClick();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void reloadReviews() {
        this.mListAdapter.clear();
        this.mRatingIds.clear();
        this.mNextOffset = 0;
        resetNoMoreItems();
        performNetworkRequest();
        refreshViewState();
    }

    /* access modifiers changed from: private */
    public void handleScrollLoad(int i, int i2, int i3) {
        if ((!isLoadingErrored() && !getNoMoreItems() && !isNetworkRequestPending() && !this.mUpdatingList && isLoadingComplete()) && i > i3 - (i2 * 2)) {
            loadNextPage();
        }
    }

    /* access modifiers changed from: private */
    public void loadNextPage() {
        if (!getNoMoreItems()) {
            performNetworkRequest();
            refreshViewState();
        }
    }

    public void onSuccess(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, boolean z, int i, int i2) {
        this.mUpdatingList = true;
        final WishRatingSummary wishRatingSummary2 = wishRatingSummary;
        final ArrayList<WishRating> arrayList2 = arrayList;
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        AnonymousClass6 r1 = new Runnable() {
            public void run() {
                ProductDetailsRatingsView.this.handleLoadingSuccess(wishRatingSummary2, arrayList2, z2, i3, i4);
            }
        };
        queuePagerSettledTask(r1);
    }

    public void onFailure() {
        this.mUpdatingList = true;
        queuePagerSettledTask(new Runnable() {
            public void run() {
                ProductDetailsRatingsView.this.handleLoadingFailure();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleLoadingSuccess(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, boolean z, int i, int i2) {
        if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState() && (this instanceof ProductDetailsProductRatingsView) && this.mSelectedFilter == FilterType.ALL && this.mRatings.isEmpty() && this.mProduct.getTopRatings() != null && !this.mProduct.getTopRatings().isEmpty()) {
            WishRating wishRating = (WishRating) this.mProduct.getTopRatings().get(0);
            if (wishRating.getAuthor().isWishStar()) {
                this.mRatings.add(wishRating);
                this.mRatingIds.add(wishRating.getRatingId());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            WishRating wishRating2 = (WishRating) it.next();
            if (!this.mRatingIds.contains(wishRating2.getRatingId())) {
                this.mRatings.add(wishRating2);
                this.mRatingIds.add(wishRating2.getRatingId());
            }
        }
        clearError();
        if (z) {
            markNoMoreItems();
            if (this.mRatings.size() == 0) {
                this.mLoadingFooter.setNoMoreItemsText(WishApplication.getInstance().getString(R.string.no_reviews_found));
            } else {
                this.mLoadingFooter.setNoMoreItemsText(null);
            }
        }
        if (this.mRatingSummary == null) {
            this.mRatingSummary = wishRatingSummary;
            setupHeader();
        }
        this.mNextOffset = i2;
        this.mListAdapter.notifyDataSetChanged();
        this.mUpdatingList = false;
        markLoadingComplete();
        setUpFilterCounts();
        if (this.mRatingSpacer != null) {
            this.mRatingSpacer.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                productDetailsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(productDetailsActivity.getString(R.string.ratings_error_message)));
            }
        });
    }

    public int getFirstItemPosition() {
        return this.mListView.getFirstVisiblePosition();
    }

    public void restorePosition(final int i) {
        this.mListView.post(new Runnable() {
            public void run() {
                ProductDetailsRatingsView.this.mListView.setSelection(i);
            }
        });
    }

    public void handleReload() {
        loadNextPage();
    }
}
