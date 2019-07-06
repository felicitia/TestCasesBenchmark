package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import java.util.HashMap;

public class ProductDetailsRecentVideosHorizontalListViewItem extends RelativeLayout implements ImageRestorable {
    /* access modifiers changed from: private */
    public ProductDetailsFragment mFragment;
    private NetworkImageView mThumbnail;

    public ProductDetailsRecentVideosHorizontalListViewItem(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mThumbnail = (NetworkImageView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_recent_videos_horizontal_list_view_item, this).findViewById(R.id.product_details_horizontal_list_view_item_video_thumbnail);
    }

    public void setup(final WishProductExtraImage wishProductExtraImage, final int i, ProductDetailsFragment productDetailsFragment) {
        this.mFragment = productDetailsFragment;
        if (wishProductExtraImage.getThumbnail() != null) {
            this.mThumbnail.setImage(wishProductExtraImage.getThumbnail());
            this.mThumbnail.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("rating_id", wishProductExtraImage.getRatingId());
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENT_VIDEO, ProductDetailsRecentVideosHorizontalListViewItem.this.mFragment.getProductId(), hashMap);
                    ProductDetailsRecentVideosHorizontalListViewItem.this.mFragment.showProductExtraPhotosImageViewer(i);
                }
            });
        }
    }

    public void releaseImages() {
        if (this.mThumbnail != null) {
            this.mThumbnail.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mThumbnail != null) {
            this.mThumbnail.restoreImages();
        }
    }
}
