package com.contextlogic.wish.activity.exampleugc;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;

public class ExampleUgcItemTileView extends FrameLayout implements ImageRestorable {
    private ProfileImageView mAuthorImageView;
    private ThemedTextView mAuthorName;
    private NetworkImageView mRatingImageView;

    public ExampleUgcItemTileView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.example_ugc_item_tile, this);
        this.mRatingImageView = (NetworkImageView) inflate.findViewById(R.id.rating_image);
        this.mAuthorImageView = (ProfileImageView) inflate.findViewById(R.id.rating_author_image);
        this.mAuthorName = (ThemedTextView) inflate.findViewById(R.id.rating_author_name);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mRatingImageView.setImagePrefetcher(imageHttpPrefetcher);
        this.mAuthorImageView.setImagePrefetcher(imageHttpPrefetcher);
    }

    public void setRating(WishRating wishRating) {
        this.mRatingImageView.setImage(wishRating.getThumbnailImage());
        this.mAuthorImageView.setup(wishRating.getAuthor().getProfileImage(), wishRating.getAuthor().getFirstName());
        this.mAuthorName.setText(formatName(wishRating.getAuthor().getFirstName()));
    }

    private String formatName(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = str.split("\\s")[0];
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(str2.charAt(0)));
        sb.append(str2.substring(1).toLowerCase());
        return sb.toString();
    }

    public void releaseImages() {
        if (this.mRatingImageView != null) {
            this.mRatingImageView.releaseImages();
        }
        if (this.mAuthorImageView != null) {
            this.mAuthorImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mRatingImageView != null) {
            this.mRatingImageView.restoreImages();
        }
        if (this.mAuthorImageView != null) {
            this.mAuthorImageView.restoreImages();
        }
    }
}
