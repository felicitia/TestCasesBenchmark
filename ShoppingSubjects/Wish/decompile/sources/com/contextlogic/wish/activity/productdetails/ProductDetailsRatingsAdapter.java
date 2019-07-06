package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsRatingsAdapter extends ArrayAdapter<WishRating> {
    private List<WishRating> mData;
    /* access modifiers changed from: private */
    public ProductDetailsFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ListView mListView;
    private ProductDetailsRatingsView mRatingsView;

    public long getItemId(int i) {
        return (long) i;
    }

    public ProductDetailsRatingsAdapter(Context context, ProductDetailsFragment productDetailsFragment, ArrayList<WishRating> arrayList, ListView listView, ImageHttpPrefetcher imageHttpPrefetcher, ProductDetailsRatingsView productDetailsRatingsView) {
        super(context, R.layout.product_details_fragment_ratings_item_row, arrayList);
        this.mFragment = productDetailsFragment;
        this.mData = arrayList;
        this.mListView = listView;
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mRatingsView = productDetailsRatingsView;
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
        final WishRating item = getItem(i);
        if (item != null) {
            productDetailsRatingsRowView.setImagePrefetcher(this.mImagePrefetcher);
            productDetailsRatingsRowView.setup(item);
            productDetailsRatingsRowView.setOnItemClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsRatingsAdapter.this.mFragment.showRatingAuthorProfile(item);
                }
            });
            productDetailsRatingsRowView.setOnWishStarBadgeClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsRatingsAdapter.this.mFragment.showWishStarDialog(item.getAuthor().getFirstName());
                }
            });
            productDetailsRatingsRowView.setOnRatingImageClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsRatingsAdapter.this.mFragment.showRatingPhotosImageViewer(item, 0);
                }
            });
            productDetailsRatingsRowView.setOnRatingVideoClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductDetailsRatingsAdapter.this.mFragment.showRatingPhotosImageViewer(item, 1);
                }
            });
            if (this.mRatingsView != null && this.mRatingsView.shouldShowUpvote()) {
                productDetailsRatingsRowView.setupUpvoteRatings(this.mFragment);
            }
        }
        return productDetailsRatingsRowView;
    }

    public WishRating getItem(int i) {
        if (i < getCount()) {
            return (WishRating) this.mData.get(i);
        }
        return null;
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
