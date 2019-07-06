package com.contextlogic.wish.activity.merchantprofile;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsRatingsRowView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MerchantProfileRatingsAdapter extends ArrayAdapter<WishRating> {
    private List<WishRating> mData;
    private MerchantProfileFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private Set<Integer> mIndexToAnimate = new HashSet();
    private ListView mListView;
    /* access modifiers changed from: private */
    public MerchantProfileRatingsView mRatingsView;

    public long getItemId(int i) {
        return (long) i;
    }

    public MerchantProfileRatingsAdapter(Context context, MerchantProfileFragment merchantProfileFragment, ArrayList<WishRating> arrayList, ListView listView, ImageHttpPrefetcher imageHttpPrefetcher, MerchantProfileRatingsView merchantProfileRatingsView) {
        super(context, R.layout.product_details_fragment_ratings_item_row, arrayList);
        this.mFragment = merchantProfileFragment;
        this.mData = arrayList;
        this.mListView = listView;
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mRatingsView = merchantProfileRatingsView;
    }

    public int getCount() {
        if (this.mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductDetailsRatingsRowView productDetailsRatingsRowView;
        if (view != null) {
            productDetailsRatingsRowView = (ProductDetailsRatingsRowView) view;
        } else {
            productDetailsRatingsRowView = new ProductDetailsRatingsRowView(getContext());
        }
        final WishRating wishRating = (WishRating) getItem(i);
        if (wishRating != null) {
            productDetailsRatingsRowView.setImagePrefetcher(this.mImagePrefetcher);
            productDetailsRatingsRowView.setup(wishRating);
            productDetailsRatingsRowView.setOnItemClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (MerchantProfileRatingsAdapter.this.mRatingsView != null) {
                        MerchantProfileRatingsAdapter.this.mRatingsView.handleItemClick(wishRating);
                    }
                }
            });
            productDetailsRatingsRowView.setOnRatingImageClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MerchantProfileRatingsAdapter.this.showLargeImage(wishRating.getImageLargeUrlString());
                }
            });
        }
        return productDetailsRatingsRowView;
    }

    /* access modifiers changed from: private */
    public void showLargeImage(final String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_PHOTO_MERCHANT_PROFILE_REVIEWS);
        this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                Intent intent = new Intent();
                intent.setClass(merchantProfileActivity, ImageViewerActivity.class);
                intent.putExtra("ExtraImageUrl", str);
                intent.putExtra("ExtraStartIndex", 0);
                merchantProfileActivity.startActivity(intent);
            }
        });
    }

    public void refreshTimestamps() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                View childAt = this.mListView.getChildAt(i);
                if (childAt != null && (childAt instanceof ProductDetailsRatingsRowView)) {
                    ((ProductDetailsRatingsRowView) childAt).refreshTimestamp();
                }
            }
        }
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                View childAt = this.mListView.getChildAt(i);
                if (childAt != null && (childAt instanceof ImageRestorable)) {
                    ((ImageRestorable) childAt).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                View childAt = this.mListView.getChildAt(i);
                if (childAt != null && (childAt instanceof ImageRestorable)) {
                    ((ImageRestorable) childAt).restoreImages();
                }
            }
        }
    }
}
