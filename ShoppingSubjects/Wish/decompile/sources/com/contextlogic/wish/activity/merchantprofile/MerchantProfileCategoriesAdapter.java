package com.contextlogic.wish.activity.merchantprofile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.merchantprofile.merchanttopcategory.MerchantTopCategoryActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MerchantProfileCategoriesAdapter extends ArrayAdapter<WishMerchantTopCategory> {
    private List<WishMerchantTopCategory> mCategories;
    /* access modifiers changed from: private */
    public MerchantProfileFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private Set<Integer> mIndexToAnimate = new HashSet();
    private ListView mListView;
    /* access modifiers changed from: private */
    public String mRequestId;

    static class ItemRowHolder {
        TextView categoryCount;
        TextView categoryTitle;
        int firstVisiblePosition;
        int position;
        ArrayList<NetworkImageView> productTiles;
        LinearLayout rowOne;
        LinearLayout rowTwo;
        WishMerchantTopCategory topCategoryObject;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public MerchantProfileCategoriesAdapter(Context context, MerchantProfileFragment merchantProfileFragment, ListView listView, ArrayList<WishMerchantTopCategory> arrayList, ImageHttpPrefetcher imageHttpPrefetcher) {
        super(context, R.layout.merchant_profile_fragment_categories_item_row);
        this.mFragment = merchantProfileFragment;
        this.mListView = listView;
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mCategories = arrayList;
    }

    public int getCount() {
        if (this.mCategories == null) {
            return 0;
        }
        return this.mCategories.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.merchant_profile_fragment_categories_item_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.categoryTitle = (TextView) view.findViewById(R.id.fragment_categories_item_title);
            itemRowHolder.categoryCount = (TextView) view.findViewById(R.id.fragment_categories_item_count);
            itemRowHolder.rowOne = (LinearLayout) view.findViewById(R.id.fragment_categories_items_row_one);
            itemRowHolder.rowTwo = (LinearLayout) view.findViewById(R.id.fragment_categories_items_row_two);
            float dimension = WishApplication.getInstance().getResources().getDimension(R.dimen.eight_padding);
            LayoutParams layoutParams = new LayoutParams(-1, (int) ((((float) DisplayUtil.getDisplayWidth()) - (((float) (getNumColumn() - 1)) * dimension)) / ((float) getNumColumn())));
            itemRowHolder.rowOne.setLayoutParams(layoutParams);
            itemRowHolder.rowTwo.setLayoutParams(layoutParams);
            itemRowHolder.productTiles = new ArrayList<>();
            int i2 = 0;
            while (i2 < getNumColumn() * 2) {
                NetworkImageView networkImageView = new NetworkImageView(getContext());
                LayoutParams layoutParams2 = new LayoutParams(0, -1);
                layoutParams2.weight = 1.0f;
                layoutParams2.setMargins((i2 == 0 || i2 == getNumColumn()) ? 0 : (int) dimension, 0, 0, (int) dimension);
                networkImageView.setLayoutParams(layoutParams2);
                networkImageView.setImagePrefetcher(this.mImagePrefetcher);
                itemRowHolder.productTiles.add(networkImageView);
                if (i2 < getNumColumn()) {
                    itemRowHolder.rowOne.addView(networkImageView);
                } else {
                    itemRowHolder.rowTwo.addView(networkImageView);
                }
                i2++;
            }
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        itemRowHolder.position = i;
        itemRowHolder.firstVisiblePosition = this.mListView.getFirstVisiblePosition();
        TransitionDrawable transitionDrawable = (TransitionDrawable) view.getBackground();
        if (this.mIndexToAnimate.remove(Integer.valueOf(i))) {
            transitionDrawable.startTransition(0);
            transitionDrawable.reverseTransition(1000);
        }
        WishMerchantTopCategory wishMerchantTopCategory = (WishMerchantTopCategory) this.mCategories.get(i);
        itemRowHolder.topCategoryObject = wishMerchantTopCategory;
        itemRowHolder.categoryTitle.setText(itemRowHolder.topCategoryObject.getCategoryTitle());
        itemRowHolder.categoryCount.setText(getContext().getResources().getQuantityString(R.plurals.item, itemRowHolder.topCategoryObject.getCategoryItemCount(), new Object[]{Integer.valueOf(itemRowHolder.topCategoryObject.getCategoryItemCount())}));
        for (int i3 = 0; i3 < itemRowHolder.productTiles.size(); i3++) {
            ((NetworkImageView) itemRowHolder.productTiles.get(i3)).setImage(null);
            ((NetworkImageView) itemRowHolder.productTiles.get(i3)).setImageBitmap(null);
        }
        ArrayList topCategoryProducts = wishMerchantTopCategory.getTopCategoryProducts();
        int i4 = 0;
        while (i4 < wishMerchantTopCategory.getTopCategoryProducts().size() && i4 < itemRowHolder.productTiles.size()) {
            ((NetworkImageView) itemRowHolder.productTiles.get(i4)).setImage(((WishProduct) topCategoryProducts.get(i4)).getImage());
            i4++;
        }
        if (wishMerchantTopCategory.getTopCategoryProducts().size() < getNumColumn()) {
            itemRowHolder.rowTwo.setVisibility(8);
        } else {
            itemRowHolder.rowTwo.setVisibility(0);
        }
        if (this.mImagePrefetcher != null && this.mListView.getLastVisiblePosition() >= 0) {
            int min = Math.min(Math.max(i, this.mListView.getLastVisiblePosition()) + 1, getCount());
            int min2 = Math.min(min + 5, getCount());
            while (min < min2) {
                WishMerchantTopCategory item = getItem(min);
                if (item != null) {
                    int i5 = 0;
                    while (i5 < item.getTopCategoryProducts().size() && i5 < itemRowHolder.productTiles.size()) {
                        this.mImagePrefetcher.queueImage(((WishProduct) item.getTopCategoryProducts().get(i5)).getImage());
                        i5++;
                    }
                }
                min++;
            }
        }
        attachCategoryClickListener(view, itemRowHolder.topCategoryObject);
        return view;
    }

    private void attachCategoryClickListener(View view, final WishMerchantTopCategory wishMerchantTopCategory) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANT_CATEGORIES_ROW);
                Intent intent = new Intent();
                intent.setClass(WishApplication.getInstance(), MerchantTopCategoryActivity.class);
                intent.putExtra(MerchantTopCategoryActivity.EXTRA_REQUEST_ID, MerchantProfileCategoriesAdapter.this.mRequestId);
                intent.putStringArrayListExtra(MerchantTopCategoryActivity.EXTRA_TAG_IDS, wishMerchantTopCategory.getTags());
                ((MerchantProfileActivity) MerchantProfileCategoriesAdapter.this.mFragment.getBaseActivity()).startActivity(intent);
            }
        });
    }

    private int getNumColumn() {
        return TabletUtil.isTablet() ? 5 : 3;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    for (int i2 = 0; i2 < itemRowHolder.productTiles.size(); i2++) {
                        ((NetworkImageView) itemRowHolder.productTiles.get(i2)).releaseImages();
                    }
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    for (int i2 = 0; i2 < itemRowHolder.productTiles.size(); i2++) {
                        ((NetworkImageView) itemRowHolder.productTiles.get(i2)).restoreImages();
                    }
                }
            }
        }
    }

    public WishMerchantTopCategory getItem(int i) {
        return (WishMerchantTopCategory) this.mCategories.get(i);
    }

    public void setRequestId(String str) {
        this.mRequestId = str;
    }
}
