package com.contextlogic.wish.activity.feed;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.ProductDetailsCallback;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class BaseProductFeedItemsAdapter<T extends BaseActivity, S extends BaseProductFeedFragment> extends Adapter {
    private T mBaseActivity;
    /* access modifiers changed from: private */
    public S mFragment;
    protected ImageHttpPrefetcher mImagePrefetcher;
    protected boolean mInEditMode;
    private int mMargin;
    protected HashSet<Integer> mSelectedIndices;

    public abstract ArrayList<WishProduct> getItems();

    public BaseProductFeedItemsAdapter(T t, S s) {
        this.mMargin = -1;
        this.mBaseActivity = t;
        this.mFragment = s;
        this.mSelectedIndices = new HashSet<>();
    }

    public BaseProductFeedItemsAdapter(T t, S s, int i) {
        this(t, s);
        this.mMargin = i;
    }

    /* access modifiers changed from: protected */
    public T getBaseActivity() {
        return this.mBaseActivity;
    }

    /* access modifiers changed from: protected */
    public S getFragment() {
        return this.mFragment;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public int getItemMargin() {
        return this.mMargin != -1 ? (int) ValueUtil.convertDpToPx((float) this.mMargin) : super.getItemMargin();
    }

    public int getItemHeight(int i, int i2) {
        return ProductFeedTileView.getExpectedHeight(i2);
    }

    public final int getCount() {
        ArrayList items = getItems();
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public final WishProduct getItem(int i) {
        ArrayList items = getItems();
        if (i < 0 || i >= items.size()) {
            return null;
        }
        return (WishProduct) items.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductFeedTileView productFeedTileView;
        if (view != null) {
            productFeedTileView = (ProductFeedTileView) view;
        } else {
            productFeedTileView = new ProductFeedTileView(getBaseActivity());
            productFeedTileView.setImagePrefetcher(this.mImagePrefetcher);
        }
        productFeedTileView.setProduct(getItem(i));
        productFeedTileView.setPosition(i);
        productFeedTileView.setEditModeEnabled(this.mInEditMode);
        if (this.mInEditMode) {
            productFeedTileView.setProductSelected(this.mSelectedIndices.contains(Integer.valueOf(productFeedTileView.getPosition())));
        } else {
            productFeedTileView.setProductSelected(false);
        }
        return productFeedTileView;
    }

    public void attachDefaultClickListener(StaggeredGridView staggeredGridView, final ProductDetailsCallback productDetailsCallback) {
        staggeredGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, final View view) {
                WishProduct item = BaseProductFeedItemsAdapter.this.getItem(i);
                if (item != null) {
                    FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.CLICKED, i, item.getVideoStatus().ordinal());
                }
                if (!BaseProductFeedItemsAdapter.this.mInEditMode) {
                    if (BaseProductFeedItemsAdapter.this.mFragment.getDataMode() == DataMode.Wishlist) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SELECT_WISHLIST_PRODUCT);
                    }
                    if (BaseProductFeedItemsAdapter.this.mFragment.getSource() == Source.BRANDED) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_SELECT_PRODUCT);
                    }
                    final WishProduct item2 = BaseProductFeedItemsAdapter.this.getItem(i);
                    if (item2 != null) {
                        BaseProductFeedItemsAdapter.this.getFragment().withActivity(new ActivityTask<BaseActivity>() {
                            public void performTask(BaseActivity baseActivity) {
                                ProductDetailsActivity.startActivityWithTransition(baseActivity, view instanceof ProductFeedTileView ? ((ProductFeedTileView) view).getProductImage() : null, item2, BaseProductFeedItemsAdapter.this.mFragment.getSource() == Source.BRANDED ? Source.BRANDED : Source.DEFAULT, new ActivityResultCallback() {
                                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                        if (i2 == 1000 && productDetailsCallback != null) {
                                            productDetailsCallback.onUnWished();
                                        }
                                    }
                                });
                            }
                        });
                    }
                } else if (view instanceof ProductFeedTileView) {
                    ProductFeedTileView productFeedTileView = (ProductFeedTileView) view;
                    if (productFeedTileView.isSelectable()) {
                        if (BaseProductFeedItemsAdapter.this.mSelectedIndices.contains(Integer.valueOf(i))) {
                            BaseProductFeedItemsAdapter.this.mSelectedIndices.remove(Integer.valueOf(i));
                        } else {
                            BaseProductFeedItemsAdapter.this.mSelectedIndices.add(Integer.valueOf(i));
                        }
                        productFeedTileView.setProductSelected(BaseProductFeedItemsAdapter.this.mSelectedIndices.contains(Integer.valueOf(i)));
                    }
                }
            }
        });
    }

    public ArrayList<WishProduct> getSelectedItems() {
        ArrayList<WishProduct> arrayList = new ArrayList<>();
        Iterator it = this.mSelectedIndices.iterator();
        while (it.hasNext()) {
            arrayList.add(getItem(((Integer) it.next()).intValue()));
        }
        return arrayList;
    }

    public void setEditModeEnabled(boolean z) {
        if (this.mInEditMode != z) {
            this.mSelectedIndices.clear();
        }
        this.mInEditMode = z;
    }

    public void setSelectedIndex(int i) {
        this.mSelectedIndices.add(Integer.valueOf(i));
    }
}
