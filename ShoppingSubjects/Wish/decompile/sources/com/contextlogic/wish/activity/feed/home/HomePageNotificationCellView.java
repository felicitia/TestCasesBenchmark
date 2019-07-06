package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageNotificationItemHolder;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;

public class HomePageNotificationCellView extends RelativeLayout implements ImageRestorable, Recyclable {
    private NetworkImageView mBottomSecondaryImageView;
    private ThemedTextView mMessage;
    HomePageNotificationItemHolder mNotification;
    private NetworkImageView mPrimaryImageView;
    private NetworkImageView mTopSecondaryImageView;

    public HomePageNotificationCellView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_notification_view, this);
        this.mPrimaryImageView = (NetworkImageView) inflate.findViewById(R.id.home_page_notification_view_cell_primary_image);
        this.mTopSecondaryImageView = (NetworkImageView) inflate.findViewById(R.id.home_page_notification_view_cell_top_secondary_image);
        this.mBottomSecondaryImageView = (NetworkImageView) inflate.findViewById(R.id.home_page_notification_view_cell_bottom_secondary_image);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_image_container_height), (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_image_container_height)), new RectF(0.0f, 0.0f, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_half_image_width), (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_image_container_height)), ScaleToFit.END);
        this.mBottomSecondaryImageView.setImageMatrix(matrix);
        this.mTopSecondaryImageView.setImageMatrix(matrix);
        this.mMessage = (ThemedTextView) inflate.findViewById(R.id.home_page_notification_view_cell_collection_title);
    }

    public void setNotification(HomePageNotificationItemHolder homePageNotificationItemHolder) {
        this.mNotification = homePageNotificationItemHolder;
        setImages(this.mNotification.getImageUrls());
        this.mMessage.setText(this.mNotification.getText());
    }

    private void setImages(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            this.mPrimaryImageView.setVisibility(8);
        } else if (arrayList.size() <= 2) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            this.mTopSecondaryImageView.setVisibility(8);
            this.mBottomSecondaryImageView.setVisibility(8);
            this.mPrimaryImageView.setLayoutParams(layoutParams);
            this.mPrimaryImageView.setImage(new WishImage((String) arrayList.get(0)));
        } else if (arrayList.size() == 3) {
            this.mPrimaryImageView.setVisibility(0);
            this.mTopSecondaryImageView.setVisibility(0);
            this.mBottomSecondaryImageView.setVisibility(0);
            this.mPrimaryImageView.setImage(new WishImage((String) arrayList.get(0)));
            this.mTopSecondaryImageView.setImage(new WishImage((String) arrayList.get(1)));
            this.mBottomSecondaryImageView.setImage(new WishImage((String) arrayList.get(2)));
        }
    }

    public void releaseImages() {
        this.mPrimaryImageView.releaseImages();
        this.mBottomSecondaryImageView.releaseImages();
        this.mTopSecondaryImageView.releaseImages();
    }

    public void restoreImages() {
        this.mPrimaryImageView.restoreImages();
        this.mTopSecondaryImageView.restoreImages();
        this.mBottomSecondaryImageView.restoreImages();
    }

    public void recycle() {
        this.mPrimaryImageView.releaseImages();
        this.mPrimaryImageView.setImage(null);
        this.mTopSecondaryImageView.releaseImages();
        this.mTopSecondaryImageView.setImage(null);
        this.mBottomSecondaryImageView.releaseImages();
        this.mBottomSecondaryImageView.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mPrimaryImageView.setImagePrefetcher(imageHttpPrefetcher);
        this.mTopSecondaryImageView.setImagePrefetcher(imageHttpPrefetcher);
        this.mBottomSecondaryImageView.setImagePrefetcher(imageHttpPrefetcher);
    }
}
