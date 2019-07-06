package com.contextlogic.wish.activity.productdetails.relateditemsrow;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsRelatedItemAdapter extends Adapter {
    private DrawerActivity mBaseActivity;
    private ImageHttpPrefetcher mImagePrefetcher;
    private List<WishProduct> mProducts = new ArrayList();

    public boolean includeLeadingMargin() {
        return false;
    }

    public boolean includeTrailingMargin() {
        return false;
    }

    public ProductDetailsRelatedItemAdapter(DrawerActivity drawerActivity) {
        this.mBaseActivity = drawerActivity;
    }

    public int getItemWidth(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(ExperimentDataCenter.getInstance().shouldSeeLargerExpressShippingTiles() ? R.dimen.product_details_express_shipping_item_width_larger : R.dimen.product_details_express_shipping_item_width);
    }

    public int getItemHeight(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(ExperimentDataCenter.getInstance().shouldSeeLargerExpressShippingTiles() ? R.dimen.product_details_express_shipping_item_height_larger : R.dimen.product_details_express_shipping_item_height);
    }

    public int getCount() {
        if (this.mProducts != null) {
            return this.mProducts.size();
        }
        return 0;
    }

    public WishProduct getItem(int i) {
        if (this.mProducts == null || this.mProducts.size() <= i) {
            return null;
        }
        return (WishProduct) this.mProducts.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        FasterShippingRowItemView fasterShippingRowItemView;
        if (view != null) {
            fasterShippingRowItemView = (FasterShippingRowItemView) view;
        } else {
            fasterShippingRowItemView = new FasterShippingRowItemView(this.mBaseActivity);
            if (this.mImagePrefetcher != null) {
                fasterShippingRowItemView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        fasterShippingRowItemView.setProduct(getItem(i));
        return fasterShippingRowItemView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void setItems(List<WishProduct> list) {
        this.mProducts = list;
        notifyDataSetChanged();
    }

    public int getItemMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(ExperimentDataCenter.getInstance().shouldSeeLargerExpressShippingTiles() ? R.dimen.twelve_padding : R.dimen.eight_padding);
    }
}
