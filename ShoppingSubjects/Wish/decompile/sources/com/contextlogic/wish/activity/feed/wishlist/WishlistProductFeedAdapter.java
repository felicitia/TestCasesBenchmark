package com.contextlogic.wish.activity.feed.wishlist;

import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedTileView;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetProductService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetProductService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.Iterator;

public class WishlistProductFeedAdapter extends BaseProductFeedAdapter {
    /* access modifiers changed from: private */
    public SparseArray<WishProduct> mProductsWithAllVariations = new SparseArray<>();

    public WishlistProductFeedAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment, int i) {
        super(drawerActivity, baseProductFeedFragment, i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ProductFeedTileView productFeedTileView = (ProductFeedTileView) super.getView(i, view, viewGroup);
        productFeedTileView.hideNumPurchasedTextView();
        productFeedTileView.showProductDetails();
        final WishProduct item = getItem(i);
        boolean shippingNotFullInMyRow = getShippingNotFullInMyRow(i);
        if ((item.getDefaultExpressShippingOption() == null || item.getDefaultStandardShippingOption() == null) && shippingNotFullInMyRow) {
            productFeedTileView.hideBottomShipping();
        } else {
            productFeedTileView.showBottomShipping();
        }
        if (ExperimentDataCenter.getInstance().shouldSeeAddToCartInWishlistTile()) {
            View findViewById = productFeedTileView.findViewById(R.id.product_feed_add_to_cart_button);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (WishlistProductFeedAdapter.this.mProductsWithAllVariations.get(i) != null) {
                        WishlistProductFeedAdapter.this.getFragment().addProductToCart((WishProduct) WishlistProductFeedAdapter.this.mProductsWithAllVariations.get(i));
                    } else {
                        WishlistProductFeedAdapter.this.getFragment().loadProductVariations(item, new SuccessCallback() {
                            public void onSuccess(WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                                WishlistProductFeedAdapter.this.mProductsWithAllVariations.put(i, wishProduct);
                                WishlistProductFeedAdapter.this.getFragment().addProductToCart(wishProduct);
                            }
                        }, new DefaultCodeFailureCallback() {
                            public void onFailure(String str, int i) {
                                WishlistProductFeedAdapter.this.getFragment().showUnableToAddToCartDialog();
                            }
                        });
                    }
                }
            });
        }
        return productFeedTileView;
    }

    public int getItemHeight(int i, int i2) {
        int i3;
        int dimension = (int) (((float) ((int) (((float) ((int) (((float) ((int) (((float) i2) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_price_layout_height)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_original_price_layout_height_small)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.eight_padding)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.star_container_height));
        if (getShippingNotFullInMyRow(i)) {
            i3 = (int) (((float) dimension) + WishApplication.getInstance().getResources().getDimension(R.dimen.shipping_option_information_height));
        } else {
            i3 = (int) (((float) ((int) (((float) dimension) + (WishApplication.getInstance().getResources().getDimension(R.dimen.shipping_option_information_height) * 2.0f)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.eight_padding));
        }
        return ExperimentDataCenter.getInstance().shouldSeeAddToCartInWishlistTile() ? (int) (((float) i3) + WishApplication.getInstance().getResources().getDimension(R.dimen.add_to_cart_button_product_tile_wishlist)) : i3;
    }

    private boolean getShippingNotFullInMyRow(int i) {
        ArrayList arrayList = new ArrayList();
        int columnCount = i / getColumnCount();
        for (int i2 = 0; i2 < getColumnCount(); i2++) {
            arrayList.add(getItem((getColumnCount() * columnCount) + i2));
        }
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            WishProduct wishProduct = (WishProduct) it.next();
            z &= wishProduct == null || wishProduct.getDefaultExpressShippingOption() == null || wishProduct.getDefaultStandardShippingOption() == null;
        }
        return z;
    }
}
