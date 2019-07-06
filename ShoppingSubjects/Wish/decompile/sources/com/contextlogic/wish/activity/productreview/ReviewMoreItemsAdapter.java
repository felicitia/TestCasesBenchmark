package com.contextlogic.wish.activity.productreview;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;

public class ReviewMoreItemsAdapter extends Adapter<ItemRowHolder> {
    /* access modifiers changed from: private */
    public ProductReviewActivity mActivity;
    private ProductReviewFragment mFragment;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<WishProductReviewItem> mItems;

    class ItemRowHolder extends ViewHolder implements ImageRestorable {
        NetworkImageView itemImage;
        ThemedTextView itemName;
        ThemedTextView itemSize;
        ThemedButton postButton;

        public ItemRowHolder(View view) {
            super(view);
            this.itemImage = (NetworkImageView) view.findViewById(R.id.review_more_item_image);
            this.itemImage.setImagePrefetcher(ReviewMoreItemsAdapter.this.mImagePrefetcher);
            this.itemName = (ThemedTextView) view.findViewById(R.id.review_more_item_name);
            this.itemSize = (ThemedTextView) view.findViewById(R.id.review_more_item_size);
            this.postButton = (ThemedButton) view.findViewById(R.id.review_more_item_post_button);
        }

        public void bind(final WishProductReviewItem wishProductReviewItem) {
            this.itemImage.setImage(wishProductReviewItem.getProductImage());
            this.itemName.setText(wishProductReviewItem.getProductTitle());
            this.postButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_REVIEW_MORE_POST_BUTTON);
                    ReviewMoreItemsAdapter.this.mActivity.startCameraActivity(wishProductReviewItem);
                }
            });
            String access$200 = ReviewMoreItemsAdapter.this.getItemSizeAndColor(wishProductReviewItem);
            if (access$200.equals("")) {
                this.itemSize.setVisibility(8);
                return;
            }
            this.itemSize.setVisibility(0);
            this.itemSize.setText(access$200);
        }

        public void releaseImages() {
            if (this.itemImage != null) {
                this.itemImage.releaseImages();
            }
        }

        public void restoreImages() {
            if (this.itemImage != null) {
                this.itemImage.restoreImages();
            }
        }
    }

    public ReviewMoreItemsAdapter(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment) {
        this.mActivity = productReviewActivity;
        this.mFragment = productReviewFragment;
    }

    public void setItems(ArrayList<WishProductReviewItem> arrayList) {
        this.mItems = arrayList;
        notifyDataSetChanged();
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public WishProductReviewItem getItem(int i) {
        if (i < getItemCount()) {
            return (WishProductReviewItem) this.mItems.get(i);
        }
        return null;
    }

    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemRowHolder(this.mActivity.getLayoutInflater().inflate(R.layout.review_more_item_view, viewGroup, false));
    }

    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {
        if (getItem(i) != null) {
            itemRowHolder.bind(getItem(i));
        }
    }

    public int getItemCount() {
        if (this.mItems == null) {
            return 0;
        }
        return this.mItems.size();
    }

    /* access modifiers changed from: private */
    public String getItemSizeAndColor(WishProductReviewItem wishProductReviewItem) {
        String str;
        String str2 = "";
        if (wishProductReviewItem.getSize() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(StringUtil.capitalize(wishProductReviewItem.getSize()));
            str2 = sb.toString();
        }
        if (wishProductReviewItem.getColor() == null) {
            return str2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        if (str2.equals("")) {
            str = StringUtil.capitalize(wishProductReviewItem.getColor());
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(", ");
            sb3.append(StringUtil.capitalize(wishProductReviewItem.getColor()));
            str = sb3.toString();
        }
        sb2.append(str);
        return sb2.toString();
    }
}
