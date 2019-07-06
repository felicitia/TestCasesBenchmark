package com.contextlogic.wish.activity.productdetails;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductDetailsPagerAdapter extends PagerAdapter {
    private DrawerActivity mDrawerActivity;
    private ProductDetailsFragment mFragment;
    private ImageHttpPrefetcher mMerchantRatingsImagePrefetcher;
    ProductDetailsMerchantRatingsView mMerchantRatingsView;
    private ArrayList<ProductDetailsPagerView> mPagerViews = new ArrayList<>();
    ProductDetailsOverviewView mProductDetailsOverviewView;
    private ImageHttpPrefetcher mProductRatingsImagePrefetcher;
    private boolean mProductRatingsTracked;
    ProductDetailsProductRatingsView mProductRatingsView;
    private ProductDetailsRelatedRowSpec mRelatedExpressShippingSpec;
    private boolean mRelatedProductsTracked;
    ProductDetailsRelatedProductFeedView mRelatedProductsView;
    private ArrayList<ProductDetailSection> mSections;
    private boolean mStoreRatingsTracked;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;
    private ProductDetailsRelatedRowSpec mVisuallySimilarItemsSpec;

    public enum ProductDetailSection {
        OVERVIEW,
        DESCRIPTION,
        PRODUCT_RATINGS,
        STORE_RATINGS,
        RELATED_PRODUCTS
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ProductDetailsPagerAdapter(DrawerActivity drawerActivity, ProductDetailsFragment productDetailsFragment, ViewPager viewPager) {
        this.mViewPager = viewPager;
        this.mDrawerActivity = drawerActivity;
        this.mFragment = productDetailsFragment;
    }

    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r0v8, types: [com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.contextlogic.wish.activity.productdetails.ProductDetailsDescriptionView, com.contextlogic.wish.activity.productdetails.ProductDetailsPagerView] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView] */
    /* JADX WARNING: type inference failed for: r0v24, types: [com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView] */
    /* JADX WARNING: type inference failed for: r0v30, types: [com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v31 */
    /* JADX WARNING: type inference failed for: r0v35 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r0v36 */
    /* JADX WARNING: type inference failed for: r0v37 */
    /* JADX WARNING: type inference failed for: r0v38 */
    /* JADX WARNING: type inference failed for: r0v39 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.ViewGroup r7, int r8) {
        /*
            r6 = this;
            java.util.ArrayList<com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter$ProductDetailSection> r0 = r6.mSections
            java.lang.Object r0 = r0.get(r8)
            com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter$ProductDetailSection r0 = (com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter.ProductDetailSection) r0
            int[] r1 = com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter.AnonymousClass2.$SwitchMap$com$contextlogic$wish$activity$productdetails$ProductDetailsPagerAdapter$ProductDetailSection
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 1: goto L_0x00b8;
                case 2: goto L_0x00a5;
                case 3: goto L_0x0069;
                case 4: goto L_0x003e;
                case 5: goto L_0x0017;
                default: goto L_0x0014;
            }
        L_0x0014:
            r0 = r1
            goto L_0x00e0
        L_0x0017:
            com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView r0 = r6.mRelatedProductsView
            if (r0 != 0) goto L_0x0032
            com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView r0 = new com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r3 = r6.mFragment
            r0.<init>(r8, r2, r3)
            r6.mRelatedProductsView = r0
            com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView r0 = r6.mRelatedProductsView
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r2 = r6.mFragment
            java.lang.String r2 = r2.getProductId()
            r0.setRequestId(r2)
            goto L_0x0037
        L_0x0032:
            com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView r0 = r6.mRelatedProductsView
            r0.restoreImages()
        L_0x0037:
            com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedProductFeedView r0 = r6.mRelatedProductsView
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00e0
        L_0x003e:
            com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView r0 = r6.mMerchantRatingsView
            if (r0 != 0) goto L_0x005b
            com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView r0 = new com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            r6.mMerchantRatingsView = r0
            com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView r0 = r6.mMerchantRatingsView
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r2 = r6.mFragment
            com.contextlogic.wish.api.model.WishProduct r2 = r2.getLoadedProduct()
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r3 = r6.mFragment
            com.contextlogic.wish.http.ImageHttpPrefetcher r4 = r6.mMerchantRatingsImagePrefetcher
            r0.setup(r2, r8, r3, r4)
            goto L_0x0065
        L_0x005b:
            com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView r0 = r6.mMerchantRatingsView
            r0.restoreImages()
            com.contextlogic.wish.http.ImageHttpPrefetcher r0 = r6.mMerchantRatingsImagePrefetcher
            r0.resumePrefetching()
        L_0x0065:
            com.contextlogic.wish.activity.productdetails.ProductDetailsMerchantRatingsView r0 = r6.mMerchantRatingsView
            goto L_0x00e0
        L_0x0069:
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            if (r0 != 0) goto L_0x0098
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = new com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            r6.mProductRatingsView = r0
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r2 = r6.mFragment
            com.contextlogic.wish.api.model.WishProduct r2 = r2.getLoadedProduct()
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r3 = r6.mFragment
            com.contextlogic.wish.http.ImageHttpPrefetcher r4 = r6.mProductRatingsImagePrefetcher
            r0.setup(r2, r8, r3, r4)
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            com.contextlogic.wish.ui.image.NetworkImageView r0 = r0.mImageView
            if (r0 == 0) goto L_0x00a2
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            com.contextlogic.wish.ui.image.NetworkImageView r0 = r0.mImageView
            com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter$1 r2 = new com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter$1
            r2.<init>()
            r0.setOnClickListener(r2)
            goto L_0x00a2
        L_0x0098:
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            r0.restoreImages()
            com.contextlogic.wish.http.ImageHttpPrefetcher r0 = r6.mProductRatingsImagePrefetcher
            r0.resumePrefetching()
        L_0x00a2:
            com.contextlogic.wish.activity.productdetails.ProductDetailsProductRatingsView r0 = r6.mProductRatingsView
            goto L_0x00e0
        L_0x00a5:
            com.contextlogic.wish.activity.productdetails.ProductDetailsDescriptionView r0 = new com.contextlogic.wish.activity.productdetails.ProductDetailsDescriptionView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r2 = r6.mFragment
            com.contextlogic.wish.api.model.WishProduct r2 = r2.getLoadedProduct()
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r3 = r6.mFragment
            r0.setup(r2, r8, r3)
            goto L_0x00e0
        L_0x00b8:
            com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView r0 = r6.mProductDetailsOverviewView
            if (r0 != 0) goto L_0x00d9
            com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView r0 = new com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            r6.mProductDetailsOverviewView = r0
            com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView r0 = r6.mProductDetailsOverviewView
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r2 = r6.mFragment
            com.contextlogic.wish.api.model.WishProduct r2 = r2.getLoadedProduct()
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r3 = r6.mFragment
            com.contextlogic.wish.activity.productdetails.ProductDetailsFragment r4 = r6.mFragment
            boolean r4 = r4.isLoading()
            r0.setup(r2, r8, r3, r4)
            goto L_0x00de
        L_0x00d9:
            com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView r0 = r6.mProductDetailsOverviewView
            r0.restoreImages()
        L_0x00de:
            com.contextlogic.wish.activity.productdetails.ProductDetailsOverviewView r0 = r6.mProductDetailsOverviewView
        L_0x00e0:
            if (r0 == 0) goto L_0x00e3
            r1 = r0
        L_0x00e3:
            if (r0 == 0) goto L_0x00f2
            java.util.ArrayList<com.contextlogic.wish.activity.productdetails.ProductDetailsPagerView> r2 = r6.mPagerViews
            boolean r2 = r2.contains(r0)
            if (r2 != 0) goto L_0x00f2
            java.util.ArrayList<com.contextlogic.wish.activity.productdetails.ProductDetailsPagerView> r2 = r6.mPagerViews
            r2.add(r0)
        L_0x00f2:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r1.setTag(r8)
            android.view.ViewGroup$LayoutParams r8 = new android.view.ViewGroup$LayoutParams
            r0 = -1
            r8.<init>(r0, r0)
            r7.addView(r1, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.productdetails.ProductDetailsPagerAdapter.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof ImageRestorable) {
            ((ImageRestorable) obj).releaseImages();
        }
        viewGroup.removeView((View) obj);
        ProductDetailSection section = getSection(i);
        if (ProductDetailSection.PRODUCT_RATINGS.equals(section)) {
            this.mProductRatingsImagePrefetcher.cancelPrefetching();
        } else if (ProductDetailSection.STORE_RATINGS.equals(section)) {
            this.mMerchantRatingsImagePrefetcher.cancelPrefetching();
        }
    }

    public void setProductRatingsImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mProductRatingsImagePrefetcher = imageHttpPrefetcher;
    }

    public void setMerchantRatingsImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mMerchantRatingsImagePrefetcher = imageHttpPrefetcher;
    }

    public void updatePages() {
        WishProduct loadedProduct = this.mFragment.getLoadedProduct();
        if (loadedProduct == null) {
            this.mSections = null;
        } else if (this.mFragment.getSource() == Source.POINTS_REDEMPTION) {
            this.mSections = new ArrayList<>();
            this.mSections.add(ProductDetailSection.OVERVIEW);
            if (loadedProduct.getProductRatingCount() > 0) {
                this.mSections.add(ProductDetailSection.PRODUCT_RATINGS);
            }
        } else if (loadedProduct.isCommerceProduct()) {
            this.mSections = new ArrayList<>();
            this.mSections.add(ProductDetailSection.OVERVIEW);
            this.mSections.add(ProductDetailSection.RELATED_PRODUCTS);
            if (loadedProduct.getProductRatingCount() > 0) {
                this.mSections.add(ProductDetailSection.PRODUCT_RATINGS);
            }
            if (loadedProduct.getMerchantRatingCount() > 0) {
                this.mSections.add(ProductDetailSection.STORE_RATINGS);
            }
        } else {
            this.mSections = new ArrayList<>();
            this.mSections.add(ProductDetailSection.OVERVIEW);
            this.mSections.add(ProductDetailSection.RELATED_PRODUCTS);
        }
        this.mRelatedProductsView = null;
        this.mProductRatingsView = null;
        this.mMerchantRatingsView = null;
        this.mProductDetailsOverviewView = null;
        notifyDataSetChanged();
    }

    public void addRelatedProducts(ArrayList<WishProduct> arrayList, int i, boolean z) {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleLoadingSuccess(arrayList, i, z);
        }
    }

    public void handleRelatedProductsFailed() {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleLoadingErrored();
        }
    }

    public void handleCurrentlyViewingLoadingSuccess(String str) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.refreshUsersViewingText(str);
        }
    }

    public void handleRatingsLoaded(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, boolean z, int i, int i2, boolean z2) {
        ProductDetailsRatingsView productDetailsRatingsView = z2 ? this.mProductRatingsView : this.mMerchantRatingsView;
        if (productDetailsRatingsView != null) {
            productDetailsRatingsView.onSuccess(wishRatingSummary, arrayList, z, i, i2);
        }
    }

    public void handleRatingsFailed(boolean z) {
        ProductDetailsRatingsView productDetailsRatingsView = z ? this.mProductRatingsView : this.mMerchantRatingsView;
        if (productDetailsRatingsView != null) {
            productDetailsRatingsView.onFailure();
        }
    }

    public void handleBundledProductLoaded(WishProduct wishProduct) {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleBundledProductLoaded(wishProduct);
        }
    }

    public void handleBundledProductFailed() {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleBundledProductFailed();
        }
    }

    public void handleGroupBuysLoaded(ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo, WishProduct wishProduct) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.handleGroupBuysLoaded(arrayList, wishGroupBuyInfo, wishProduct);
        }
    }

    public void handleRecommendedWishlistsFailed() {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleRecommendedWishlistsFailed();
        }
    }

    public void handleRecommendedWishlistsLoaded(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str, String str2) {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleRecommendedWishlistsLoaded(arrayList, arrayList2, str, str2);
        }
    }

    public void handleRelatedExpressItemsFailed() {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleRelatedExpressLoadFailed();
        }
    }

    public void handleVisuallysimilarItemsLoadFailed() {
        if (this.mRelatedProductsView != null) {
            this.mRelatedProductsView.handleVisuallySimilarRowLoadFailed();
        }
    }

    public void addVisuallySimilarProducts(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mVisuallySimilarItemsSpec != null) {
            this.mVisuallySimilarItemsSpec = productDetailsRelatedRowSpec;
        }
        if (this.mProductDetailsOverviewView != null && this.mFragment.shouldLoadVisuallySimilarItems()) {
            this.mProductDetailsOverviewView.handleVisuallySimilarRowLoadingSuccess(productDetailsRelatedRowSpec);
        }
        if (this.mRelatedProductsView != null && this.mFragment.shouldLoadVisuallySimilarItems()) {
            this.mRelatedProductsView.handleVisuallySimilarRowLoadingSuccess(productDetailsRelatedRowSpec);
        }
    }

    public void addExpressRelatedProducts(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mRelatedExpressShippingSpec == null) {
            this.mRelatedExpressShippingSpec = productDetailsRelatedRowSpec;
        }
        if (this.mProductDetailsOverviewView != null && this.mFragment.shouldLoadOverviewExpressItems()) {
            this.mProductDetailsOverviewView.handleFasterShippingRowLoadingSuccess(productDetailsRelatedRowSpec);
        }
        if (this.mRelatedProductsView != null && this.mFragment.shouldLoadRelatedExpressItems()) {
            this.mRelatedProductsView.handleRelatedExpressLoadingSuccess(productDetailsRelatedRowSpec);
        }
    }

    public void addProductBoostRelatedProducts(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.handleProductBoostRowLoadingSuccess(productDetailsRelatedRowSpec);
        }
    }

    public void handleBuyerGuaranteeInfoLoaded(BuyerGuaranteeInfo buyerGuaranteeInfo) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.handleBuyerGuaranteeInfoLoaded(buyerGuaranteeInfo);
        }
    }

    public void handlePriceChopDetailLoaded(String str, PriceChopProductDetail priceChopProductDetail) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.handlePriceChopDetailLoaded(str, priceChopProductDetail);
        }
    }

    public void handleBuyerGuaranteeFailed() {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.handleBuyerGuaranteeFailed();
        }
    }

    public void refreshWishStates(boolean z) {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.refreshWishStates(z);
            }
        }
    }

    public void releaseImages() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.restoreImages();
            }
        }
    }

    public void cleanup() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.cleanup();
            }
        }
    }

    public void onPagerScrollUnsettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollUnsettled();
            }
        }
    }

    public void onPagerScrollSettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollSettled();
            }
        }
        int currentIndex = this.mFragment.getCurrentIndex();
        if (this.mSections != null && currentIndex < this.mSections.size()) {
            switch ((ProductDetailSection) this.mSections.get(currentIndex)) {
                case PRODUCT_RATINGS:
                    if (!this.mProductRatingsTracked) {
                        this.mProductRatingsTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_PRODUCT_RATINGS, this.mFragment.getProductId());
                        return;
                    }
                    return;
                case STORE_RATINGS:
                    if (!this.mStoreRatingsTracked) {
                        this.mStoreRatingsTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_MERCHANT_RATINGS);
                        return;
                    }
                    return;
                case RELATED_PRODUCTS:
                    if (!this.mRelatedProductsTracked) {
                        this.mRelatedProductsTracked = true;
                        if (this.mRelatedProductsView != null) {
                            this.mRelatedProductsView.handleLogging();
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public int getSectionIndex(ProductDetailSection productDetailSection) {
        if (this.mSections != null) {
            for (int i = 0; i < this.mSections.size(); i++) {
                if (this.mSections.get(i) == productDetailSection) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getCount() {
        if (this.mSections == null) {
            return 0;
        }
        return this.mSections.size();
    }

    public String getPageTitle(int i) {
        if (this.mSections == null || i >= this.mSections.size()) {
            return "";
        }
        switch ((ProductDetailSection) this.mSections.get(i)) {
            case OVERVIEW:
                return this.mDrawerActivity.getString(R.string.overview);
            case DESCRIPTION:
                return this.mDrawerActivity.getString(R.string.product_details_main_tab_description);
            case PRODUCT_RATINGS:
                return this.mDrawerActivity.getString(R.string.detail_table_product_rating);
            case STORE_RATINGS:
                return this.mDrawerActivity.getString(R.string.detail_table_seller_rating);
            case RELATED_PRODUCTS:
                return this.mDrawerActivity.getString(R.string.related);
            default:
                return "";
        }
    }

    public ProductDetailSection getSection(int i) {
        if (this.mSections == null || i < 0 || i >= this.mSections.size()) {
            return null;
        }
        return (ProductDetailSection) this.mSections.get(i);
    }

    public void handleSaveInstanceState(Bundle bundle) {
        Iterator it = this.mPagerViews.iterator();
        while (it.hasNext()) {
            ProductDetailsPagerView productDetailsPagerView = (ProductDetailsPagerView) it.next();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("SavedStateFirstItemPosition", productDetailsPagerView.getFirstItemPosition());
            bundle.putBundle(this.mFragment.getPagedDataSavedInstanceStateKey(productDetailsPagerView.getIndex()), bundle2);
        }
        if (this.mRelatedProductsView != null) {
            bundle.putBundle(this.mFragment.getPagedDataSavedInstanceStateKey(this.mRelatedProductsView.getDataIndex()), this.mRelatedProductsView.getSavedInstanceState());
        }
    }

    public void wishlistAddSuccess(String str) {
        if (this.mProductDetailsOverviewView != null) {
            this.mProductDetailsOverviewView.wishListAddSuccess(str);
        }
    }
}
