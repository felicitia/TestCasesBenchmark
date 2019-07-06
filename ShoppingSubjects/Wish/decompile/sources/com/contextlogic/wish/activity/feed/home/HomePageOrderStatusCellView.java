package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageOrderStatusItemHolder;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;

public class HomePageOrderStatusCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private NetworkImageView mContestImage;
    private ThemedTextView mDescription;
    private ThemedTextView mDetail;
    HomePageOrderStatusItemHolder mOrderStatus;
    private ThemedTextView mTitle;

    public HomePageOrderStatusCellView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setOrientation(1);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_order_status_view, this);
        this.mContestImage = (NetworkImageView) inflate.findViewById(R.id.home_page_order_status_view_contest_image);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.home_page_order_status_view_title);
        this.mDescription = (ThemedTextView) inflate.findViewById(R.id.home_page_order_status_view_description);
        this.mDetail = (ThemedTextView) inflate.findViewById(R.id.home_page_order_status_view_detail);
    }

    public void setOrderStatus(HomePageOrderStatusItemHolder homePageOrderStatusItemHolder) {
        this.mOrderStatus = homePageOrderStatusItemHolder;
        setImageViewUrlsToNull();
        this.mTitle.setText(this.mOrderStatus.getTitle());
        this.mDescription.setText(this.mOrderStatus.getDescription());
        this.mContestImage.setImage(new WishImage(this.mOrderStatus.getContestImageUrl()));
        this.mDetail.setText(this.mOrderStatus.getDetail());
    }

    public void setImageViewUrlsToNull() {
        this.mContestImage.setImage(null);
    }

    public void releaseImages() {
        this.mContestImage.releaseImages();
    }

    public void restoreImages() {
        this.mContestImage.restoreImages();
    }

    public void recycle() {
        this.mContestImage.releaseImages();
        this.mContestImage.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mContestImage.setImagePrefetcher(imageHttpPrefetcher);
    }
}
