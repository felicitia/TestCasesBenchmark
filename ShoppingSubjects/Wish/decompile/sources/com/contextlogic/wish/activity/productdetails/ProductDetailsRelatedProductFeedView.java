package com.contextlogic.wish.activity.productdetails;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.productdetails.bundles.BundlesHeader;
import com.contextlogic.wish.activity.productdetails.recommendwishlists.RecommendWishlistsView;
import com.contextlogic.wish.activity.productdetails.relateditemsrow.ProductDetailsRelatedItemsRow;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetailsRelatedProductFeedView extends BaseProductFeedView {
    private BundlesHeader mBundlesHeader;
    private ProductDetailsFragment mFragment;
    private LinearLayout mHeaderView = ((LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.product_details_fragment_related_feed_header, this, false));
    private RecommendWishlistsView mRecommendedWishlistsView;
    private ProductDetailsRelatedItemsRow mRelatedExpressRowView;
    private ProductDetailsRelatedRowSpec mRelatedExpressShippingSpec;
    private TextView mRelatedTitle = ((TextView) this.mHeaderView.findViewById(R.id.fragment_related_product_title));
    private ProductDetailsRelatedItemsRow mVisuallySimilarItemsRowView;
    private ProductDetailsRelatedRowSpec mVisuallySimilarItemsSpec;

    public ProductDetailsRelatedProductFeedView(int i, DrawerActivity drawerActivity, ProductDetailsFragment productDetailsFragment) {
        super(i, drawerActivity, productDetailsFragment);
        this.mFragment = productDetailsFragment;
        if (!(productDetailsFragment.getLoadedProduct() == null || productDetailsFragment.getLoadedProduct().getBundledProductIds() == null || productDetailsFragment.getLoadedProduct().getBundledProductIds().size() <= 0)) {
            this.mBundlesHeader = new BundlesHeader(drawerActivity);
            this.mBundlesHeader.setup(this.mFragment);
        }
        this.mRecommendedWishlistsView = new RecommendWishlistsView(drawerActivity);
        this.mRecommendedWishlistsView.setup(this.mBaseActivity, this.mFragment);
        if (productDetailsFragment.shouldLoadRelatedExpressItems()) {
            this.mRelatedExpressRowView = new ProductDetailsRelatedItemsRow(drawerActivity);
            this.mRelatedExpressRowView.setup(productDetailsFragment, productDetailsFragment.getLoadedProduct().getProductId(), DataMode.RelatedExpressProducts);
        }
        if (productDetailsFragment.shouldLoadVisuallySimilarItems()) {
            this.mVisuallySimilarItemsRowView = new ProductDetailsRelatedItemsRow(drawerActivity);
            this.mVisuallySimilarItemsRowView.setup(productDetailsFragment, productDetailsFragment.getLoadedProduct().getProductId(), DataMode.VisuallySimilar);
        }
        setCustomHeaderView(this.mHeaderView);
    }

    public void handleResume() {
        super.handleResume();
        if (this.mBundlesHeader != null && !this.mBundlesHeader.isLoaded()) {
            this.mBundlesHeader.loadBundledProducts();
        }
        if (this.mRecommendedWishlistsView != null && !this.mRecommendedWishlistsView.isLoaded()) {
            this.mRecommendedWishlistsView.loadRecommendedWishlists();
        }
        if (this.mRelatedExpressRowView != null && this.mRelatedExpressShippingSpec == null && this.mFragment.shouldLoadRelatedExpressItems()) {
            this.mFragment.loadRelatedExpressShippingItems();
        }
        if (this.mVisuallySimilarItemsRowView != null && this.mVisuallySimilarItemsSpec == null && this.mFragment.shouldLoadVisuallySimilarItems()) {
            this.mFragment.loadVisuallySimilarItems();
        }
    }

    public void releaseImages() {
        if (!ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            super.releaseImages();
        }
    }

    public void handleRecommendedWishlistsLoaded(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str, String str2) {
        if (this.mHeaderView != null && this.mRecommendedWishlistsView != null) {
            int indexOfChild = this.mBundlesHeader == null ? -1 : this.mHeaderView.indexOfChild(this.mBundlesHeader);
            if (!arrayList.isEmpty() && indexOfChild < 0 && notYetAddedToHeader(this.mRecommendedWishlistsView)) {
                this.mRecommendedWishlistsView.handleRecommendedWishlistsLoaded(arrayList, arrayList2, str);
                this.mHeaderView.addView(this.mRecommendedWishlistsView, 0);
            }
            if (!TextUtils.isEmpty(str2) && this.mRelatedTitle != null) {
                this.mRelatedTitle.setText(str2);
            }
        }
    }

    public void handleRecommendedWishlistsFailed() {
        if (this.mRecommendedWishlistsView != null) {
            this.mRecommendedWishlistsView.onFailure();
        }
    }

    public void handleRelatedExpressLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mHeaderView != null && this.mRelatedExpressRowView != null && this.mRelatedExpressShippingSpec == null) {
            this.mRelatedExpressShippingSpec = productDetailsRelatedRowSpec;
            this.mRelatedExpressRowView.handleLoadingSuccess(productDetailsRelatedRowSpec);
            if (notYetAddedToHeader(this.mRelatedExpressRowView)) {
                this.mHeaderView.addView(this.mRelatedExpressRowView, this.mHeaderView.indexOfChild(this.mRelatedTitle));
            }
        }
    }

    public void handleVisuallySimilarRowLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mHeaderView != null && this.mVisuallySimilarItemsRowView != null && this.mVisuallySimilarItemsSpec == null) {
            this.mVisuallySimilarItemsSpec = productDetailsRelatedRowSpec;
            this.mVisuallySimilarItemsRowView.handleLoadingSuccess(productDetailsRelatedRowSpec);
            if (notYetAddedToHeader(this.mVisuallySimilarItemsRowView)) {
                this.mHeaderView.addView(this.mVisuallySimilarItemsRowView, this.mHeaderView.indexOfChild(this.mRelatedTitle));
            }
        }
    }

    public void handleRelatedExpressLoadFailed() {
        if (this.mRelatedExpressRowView != null) {
            this.mRelatedExpressRowView.onFailure();
        }
    }

    public void handleVisuallySimilarRowLoadFailed() {
        if (this.mVisuallySimilarItemsRowView != null) {
            this.mVisuallySimilarItemsRowView.onFailure();
        }
    }

    public void handleBundledProductLoaded(WishProduct wishProduct) {
        if (this.mHeaderView != null && this.mBundlesHeader != null) {
            this.mBundlesHeader.onBundledProductLoadSuccess(wishProduct);
            if (notYetAddedToHeader(this.mBundlesHeader)) {
                this.mHeaderView.removeView(this.mRecommendedWishlistsView);
                this.mHeaderView.addView(this.mBundlesHeader, 0);
            }
        }
    }

    public void handleBundledProductFailed() {
        if (this.mBundlesHeader != null) {
            this.mBundlesHeader.onBundledProductLoadFailure();
        }
    }

    public void handleLogging() {
        if (this.mBundlesHeader != null && this.mFragment != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("bundled_product_ids", StringUtil.joinList(this.mFragment.getLoadedProduct().getBundledProductIds(), ",", false, false));
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_BUNDLES.getValue(), this.mFragment.getLoadedProduct().getProductId(), hashMap);
            this.mBundlesHeader.logBundledProductImpressions();
        }
    }

    private boolean notYetAddedToHeader(View view) {
        return this.mHeaderView != null && this.mHeaderView.indexOfChild(view) < 0;
    }

    public void onPagerScrollSettled() {
        super.onPagerScrollSettled();
        if (this.mFragment.getCurrentIndex() == getDataIndex()) {
            handleResume();
        }
    }
}
