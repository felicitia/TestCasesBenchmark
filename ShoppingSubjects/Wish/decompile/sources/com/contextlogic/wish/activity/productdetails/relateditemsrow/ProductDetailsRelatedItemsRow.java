package com.contextlogic.wish.activity.productdetails.relateditemsrow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedItemsActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnViewVisibleListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import com.crashlytics.android.Crashlytics;
import java.util.HashSet;
import java.util.List;

public class ProductDetailsRelatedItemsRow extends LinearLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public ProductDetailsRelatedItemAdapter mAdapter;
    protected DrawerActivity mBaseActivity;
    protected DataMode mDataMode;
    /* access modifiers changed from: private */
    public ProductDetailsFragment mFragment;
    private HorizontalListView mHorizontalListView;
    private ImageHttpPrefetcher mImagePrefetcher;
    protected String mProductId;
    private ThemedTextView mTitleText;
    private ThemedTextView mViewAllButton;
    /* access modifiers changed from: private */
    public HashSet<String> mVisibleProducts;

    public ProductDetailsRelatedItemsRow(Context context) {
        super(context);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.product_details_fragment_related_items_view, this, true);
        this.mHorizontalListView = (HorizontalListView) findViewById(R.id.product_details_fragment_related_items_list);
        this.mViewAllButton = (ThemedTextView) findViewById(R.id.product_details_fragment_related_items_view_all_button);
        this.mTitleText = (ThemedTextView) findViewById(R.id.product_details_fragment_related_items_row_title);
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        LayoutParams layoutParams = (LayoutParams) this.mHorizontalListView.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelSize(ExperimentDataCenter.getInstance().shouldSeeLargerExpressShippingTiles() ? R.dimen.product_details_express_shipping_item_height_larger : R.dimen.product_details_express_shipping_item_height);
        this.mHorizontalListView.setLayoutParams(layoutParams);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        setOrientation(1);
        setPadding(0, 0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        setVisibility(8);
    }

    public void setup(ProductDetailsFragment productDetailsFragment, String str, DataMode dataMode) {
        this.mBaseActivity = (DrawerActivity) productDetailsFragment.getBaseActivity();
        this.mFragment = productDetailsFragment;
        this.mProductId = str;
        this.mDataMode = dataMode;
        this.mAdapter = new ProductDetailsRelatedItemAdapter(this.mBaseActivity);
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mVisibleProducts = new HashSet<>();
        this.mHorizontalListView.setAdapter(this.mAdapter);
        this.mHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                WishProduct item = ProductDetailsRelatedItemsRow.this.mAdapter.getItem(i);
                if (item != null && (item instanceof WishProduct)) {
                    WishProduct wishProduct = item;
                    Intent intent = new Intent();
                    intent.setClass(ProductDetailsRelatedItemsRow.this.mBaseActivity, ProductDetailsActivity.class);
                    ProductDetailsActivity.addInitialProduct(intent, wishProduct);
                    if (ProductDetailsRelatedItemsRow.this.mFragment.getSource() == Source.BRANDED) {
                        intent.putExtra("ArgExtraSource", Source.BRANDED);
                    }
                    FeedTileLogger.getInstance().addToQueue(wishProduct.getLoggingFields(), Action.CLICKED, i);
                    ProductDetailsRelatedItemsRow.this.mBaseActivity.startActivity(intent);
                }
            }
        });
        this.mHorizontalListView.setOnViewVisibleListener(new OnViewVisibleListener() {
            public void onViewVisible(int i, View view) {
                WishProduct item = ProductDetailsRelatedItemsRow.this.mAdapter.getItem(i);
                if (item != null) {
                    String productId = item.getProductId();
                    if (!ProductDetailsRelatedItemsRow.this.mVisibleProducts.contains(productId)) {
                        FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.IMPRESSION, i);
                        ProductDetailsRelatedItemsRow.this.mVisibleProducts.add(productId);
                    }
                }
            }
        });
    }

    public void releaseImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
        if (this.mHorizontalListView != null) {
            this.mHorizontalListView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
        if (this.mHorizontalListView != null) {
            this.mHorizontalListView.restoreImages();
        }
    }

    public void recycle() {
        releaseImages();
    }

    public void handleLoadingSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
        if (this.mAdapter == null) {
            onFailure();
            StringBuilder sb = new StringBuilder();
            sb.append("ProductDetailsRelatedItemsRow Adapter is null. DataMode: ");
            sb.append(this.mDataMode == null ? "null" : this.mDataMode.name());
            Crashlytics.logException(new Exception(sb.toString()));
        } else if (productDetailsRelatedRowSpec == null) {
            onFailure();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ProductDetailsRelatedItemsRow spec is null. DataMode: ");
            sb2.append(this.mDataMode == null ? "null" : this.mDataMode.name());
            Crashlytics.logException(new Exception(sb2.toString()));
        } else {
            List products = productDetailsRelatedRowSpec.getProducts();
            if (products == null || products.size() == 0) {
                onFailure();
                return;
            }
            setVisibility(0);
            if (productDetailsRelatedRowSpec.getImpressionEvent() > 0) {
                WishAnalyticsLogger.trackEvent(productDetailsRelatedRowSpec.getImpressionEvent());
            }
            final String feedTitle = productDetailsRelatedRowSpec.getFeedTitle();
            final int viewAllClickEvent = productDetailsRelatedRowSpec.getViewAllClickEvent();
            if (productDetailsRelatedRowSpec.hideViewAll()) {
                this.mViewAllButton.setVisibility(8);
            } else {
                this.mViewAllButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(ProductDetailsRelatedItemsRow.this.mBaseActivity, ProductDetailsRelatedItemsActivity.class);
                        intent.putExtra("ExtraId", ProductDetailsRelatedItemsRow.this.mProductId);
                        intent.putExtra("ExtraDataMode", ProductDetailsRelatedItemsRow.this.mDataMode);
                        intent.putExtra("ExtraTitle", feedTitle);
                        WishAnalyticsLogger.trackEvent(viewAllClickEvent);
                        ProductDetailsRelatedItemsRow.this.mBaseActivity.startActivity(intent);
                    }
                });
            }
            this.mTitleText.setText(productDetailsRelatedRowSpec.getRowTitle());
            this.mAdapter.setItems(products);
            this.mHorizontalListView.notifyDataSetChanged();
        }
    }

    public void onFailure() {
        setVisibility(8);
    }
}
