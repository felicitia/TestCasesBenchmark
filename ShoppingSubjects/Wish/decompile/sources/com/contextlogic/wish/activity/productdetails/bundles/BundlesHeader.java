package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsPagerView;
import com.contextlogic.wish.activity.productdetails.ProductDetailsServiceFragment;
import com.contextlogic.wish.activity.productdetails.bundles.BundlesView.BuyCallback;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.SizeColorSelectorDialogFragment;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BundlesHeader extends ProductDetailsPagerView {
    /* access modifiers changed from: private */
    public int mAddToCartIndex;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mBundledProducts;
    /* access modifiers changed from: private */
    public BundlesView mBundlesView;
    /* access modifiers changed from: private */
    public LinearLayout mContainer;
    /* access modifiers changed from: private */
    public ProductDetailsFragment mFragment;
    private boolean mLoaded;
    /* access modifiers changed from: private */
    public int mProductCount;
    /* access modifiers changed from: private */
    public ArrayList<WishCartItem> mSelectedCartItems;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mSelectedProducts;
    /* access modifiers changed from: private */
    public boolean mSetupComplete;

    public interface AddToCartCallback {
        void onSuccess(WishCartItem wishCartItem);
    }

    public void cleanup() {
    }

    public int getCurrentScrollY() {
        return 0;
    }

    public int getFirstItemPosition() {
        return 0;
    }

    public int getLoadingContentLayoutResourceId() {
        return 0;
    }

    public void refreshWishStates(boolean z) {
    }

    public BundlesHeader(Context context) {
        this(context, null);
    }

    public BundlesHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setup(ProductDetailsFragment productDetailsFragment) {
        this.mFragment = productDetailsFragment;
        this.mBundledProducts = new ArrayList<>();
        this.mSelectedCartItems = new ArrayList<>();
        this.mBundledProducts.add(0, this.mFragment.getLoadedProduct());
        this.mSetupComplete = false;
        this.mLoaded = false;
    }

    public void loadBundledProducts() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                Iterator it = BundlesHeader.this.mFragment.getLoadedProduct().getBundledProductIds().iterator();
                while (it.hasNext()) {
                    productDetailsServiceFragment.loadBundledProduct((String) it.next(), null);
                }
            }
        });
    }

    public void setup() {
        markLoadingComplete();
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                LayoutParams layoutParams = new LayoutParams(-1, -2);
                BundlesHeader.this.mContainer = new LinearLayout(BundlesHeader.this.getContext());
                BundlesHeader.this.mContainer.setLayoutParams(layoutParams);
                BundlesHeader.this.mBundlesView = new BundlesView(BundlesHeader.this.getContext());
                BundlesHeader.this.mBundlesView.setup(BundlesHeader.this.mBundledProducts, productDetailsActivity, new BuyCallback() {
                    public void onBuy(ArrayList<WishProduct> arrayList) {
                        BundlesHeader.this.mSelectedProducts = arrayList;
                        BundlesHeader.this.mSelectedCartItems = new ArrayList();
                        BundlesHeader.this.addProductsToCart(0);
                    }
                });
                BundlesHeader.this.mContainer.addView(BundlesHeader.this.mBundlesView);
                BundlesHeader.this.addView(BundlesHeader.this.mContainer);
                BundlesHeader.this.mSetupComplete = true;
            }
        });
    }

    public void onBundledProductLoadSuccess(final WishProduct wishProduct) {
        this.mLoaded = true;
        if (this.mBundledProducts != null && !this.mSetupComplete) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    BundlesHeader.this.mBundledProducts.add(wishProduct);
                    BundlesHeader.this.mProductCount = BundlesHeader.this.mProductCount + 1;
                    if (BundlesHeader.this.mProductCount >= BundlesHeader.this.mFragment.getLoadedProduct().getBundledProductIds().size()) {
                        if (BundlesHeader.this.mBundledProducts.size() > 1) {
                            BundlesHeader.this.setup();
                        } else {
                            BundlesHeader.this.onFailure();
                        }
                    }
                }
            });
        }
    }

    public void onBundledProductLoadFailure() {
        this.mLoaded = true;
        if (this.mBundledProducts != null && !this.mSetupComplete) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    BundlesHeader.this.mProductCount = BundlesHeader.this.mProductCount + 1;
                    if (BundlesHeader.this.mProductCount >= BundlesHeader.this.mFragment.getLoadedProduct().getBundledProductIds().size()) {
                        if (BundlesHeader.this.mBundledProducts.size() > 1) {
                            BundlesHeader.this.setup();
                        } else {
                            BundlesHeader.this.onFailure();
                        }
                    }
                }
            });
        }
    }

    public void onFailure() {
        markLoadingComplete();
        setVisibility(8);
        this.mLoaded = true;
    }

    private String commaSeparatedProductIds(ArrayList<WishProduct> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(((WishProduct) arrayList.get(i)).getProductId());
        }
        return StringUtil.joinList(arrayList2, ",", false, false);
    }

    private void trackBuyClick(int i) {
        if (i == 0 && this.mSelectedProducts != null) {
            String commaSeparatedProductIds = commaSeparatedProductIds(this.mSelectedProducts);
            HashMap hashMap = new HashMap();
            hashMap.put("bundled_products_added_to_cart", commaSeparatedProductIds);
            switch (this.mSelectedProducts.size()) {
                case 1:
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_BUY_ONE.getValue(), (String) null, hashMap);
                    return;
                case 2:
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_BUY_TWO.getValue(), (String) null, hashMap);
                    return;
                case 3:
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_BUY_THREE.getValue(), (String) null, hashMap);
                    return;
                default:
                    return;
            }
        }
    }

    public void addProductsToCart(int i) {
        trackBuyClick(i);
        this.mAddToCartIndex = i;
        if (i < this.mSelectedProducts.size()) {
            boolean z = true;
            if (this.mAddToCartIndex + 1 != this.mSelectedProducts.size()) {
                z = false;
            }
            showAddToCartModal((WishProduct) this.mSelectedProducts.get(i), z);
        }
    }

    private void showAddToCartModal(final WishProduct wishProduct, final boolean z) {
        final String addToCartOfferId = wishProduct.getAddToCartOfferId();
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                BaseDialogFragment baseDialogFragment;
                if (ExperimentDataCenter.getInstance().shouldShowSizeColorSelector() || ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()) {
                    baseDialogFragment = SizeColorSelectorDialogFragment.createSizeColorSelectorDialogFragment(wishProduct, Source.DEFAULT, true);
                } else {
                    baseDialogFragment = AddToCartDialogFragment.createAddToCartDialog(wishProduct, Source.DEFAULT, true);
                }
                productDetailsActivity.startDialog(baseDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        BundlesHeader.this.performAddToCart(wishProduct, z, bundle.getString("ResultProductId"), bundle.getString("ResultVariationId"), addToCartOfferId);
                    }
                });
            }
        });
    }

    public void logBundledProductImpressions() {
        if (this.mBundledProducts != null) {
            for (int i = 0; i < this.mBundledProducts.size(); i++) {
                FeedTileLogger.getInstance().addToQueue(((WishProduct) this.mBundledProducts.get(i)).getLoggingFields(), Action.IMPRESSION, i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void performAddToCart(WishProduct wishProduct, boolean z, String str, String str2, String str3) {
        final String defaultShippingOptionId = wishProduct.getDefaultShippingOptionId(str2);
        if (!z) {
            ProductDetailsFragment productDetailsFragment = this.mFragment;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            AnonymousClass6 r0 = new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    productDetailsServiceFragment.addBundledProductToCart(str4, str5, defaultShippingOptionId, str6, new AddToCartCallback() {
                        public void onSuccess(WishCartItem wishCartItem) {
                            BundlesHeader.this.mSelectedCartItems.add(wishCartItem);
                            BundlesHeader.this.addProductsToCart(BundlesHeader.this.mAddToCartIndex + 1);
                        }
                    });
                }
            };
            productDetailsFragment.withServiceFragment(r0);
            return;
        }
        ProductDetailsFragment productDetailsFragment2 = this.mFragment;
        final WishProduct wishProduct2 = wishProduct;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str;
        AnonymousClass7 r02 = new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, final ProductDetailsServiceFragment productDetailsServiceFragment) {
                if (BundlesHeader.this.mSelectedProducts.size() == 1) {
                    productDetailsServiceFragment.addItemToCart(wishProduct2, str7, defaultShippingOptionId, str8, wishProduct2.getValue());
                    return;
                }
                productDetailsServiceFragment.addBundledProductToCart(str9, str7, defaultShippingOptionId, str8, new AddToCartCallback() {
                    public void onSuccess(WishCartItem wishCartItem) {
                        BundlesHeader.this.mSelectedCartItems.add(wishCartItem);
                        productDetailsServiceFragment.showBundleAddedDialogFragment(BundlesHeader.this.mSelectedProducts, BundlesHeader.this.mSelectedCartItems);
                    }
                });
            }
        };
        productDetailsFragment2.withServiceFragment(r02);
    }

    public void releaseImages() {
        this.mBundlesView.releaseImages();
    }

    public void restoreImages() {
        this.mBundlesView.restoreImages();
    }

    public boolean isLoaded() {
        return this.mLoaded;
    }
}
