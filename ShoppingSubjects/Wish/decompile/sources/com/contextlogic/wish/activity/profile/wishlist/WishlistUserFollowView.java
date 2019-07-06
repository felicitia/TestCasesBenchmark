package com.contextlogic.wish.activity.profile.wishlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishUser.WishUserState;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.OnToggleLoadingButtonClickListener;
import com.contextlogic.wish.ui.view.ProfileImageView;

public class WishlistUserFollowView extends BaseFeedHeaderView {
    private FollowButton mFollowButton;
    private ProfileImageView mProfileImageView;
    private TextView mUserName;
    private TextView mWishlistName;

    public WishlistUserFollowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setVisibility(8);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_user_follow_view, this);
        this.mWishlistName = (TextView) inflate.findViewById(R.id.wishlist_user_follow_view_list_name);
        this.mUserName = (TextView) inflate.findViewById(R.id.wishlist_user_follow_view_user);
        this.mProfileImageView = (ProfileImageView) inflate.findViewById(R.id.fragment_ratings_item_profile_image_view);
        this.mFollowButton = (FollowButton) inflate.findViewById(R.id.wishlist_follow_button);
    }

    public void setup(WishWishlist wishWishlist, OnToggleLoadingButtonClickListener onToggleLoadingButtonClickListener) {
        setVisibility(0);
        final WishUser userObject = wishWishlist.getUserObject();
        this.mProfileImageView.clear();
        this.mProfileImageView.setup(userObject.getProfileImage(), userObject.getFirstName());
        if (this.mProfileImageView != null) {
            this.mProfileImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (userObject.getUserState() == WishUserState.Registered) {
                        String userId = userObject.getUserId();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_AUTHOR_PHOTO_MERCHANT_PROFILE_REVIEWS);
                        Intent intent = new Intent();
                        intent.setClass(WishlistUserFollowView.this.getContext(), ProfileActivity.class);
                        intent.putExtra(ProfileActivity.EXTRA_USER_ID, userId);
                        WishlistUserFollowView.this.getContext().startActivity(intent);
                    }
                }
            });
            this.mProfileImageView.setImagePrefetcher(new ImageHttpPrefetcher());
        }
        this.mWishlistName.setText(wishWishlist.getName());
        this.mUserName.setText(userObject.getName());
        this.mFollowButton.setOnFollowButtonClickListener(onToggleLoadingButtonClickListener);
    }

    public void setFollowState(boolean z) {
        this.mFollowButton.setButtonMode(z ? ButtonMode.Selected : ButtonMode.Unselected);
    }

    public void releaseImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.restoreImages();
        }
    }
}
