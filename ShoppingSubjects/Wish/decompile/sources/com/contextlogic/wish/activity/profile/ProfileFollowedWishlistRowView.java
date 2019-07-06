package com.contextlogic.wish.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFollowedWishlist;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUser.WishUserState;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;

public class ProfileFollowedWishlistRowView extends LinearLayout implements ImageRestorable {
    private WishFollowedWishlist mFollowedWishlist;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<NetworkImageView> mImageViews;
    private TextView mProductCountText;
    private ProfileImageView mProfileImageView;
    private LinearLayout mRow1;
    private LinearLayout mRow2;
    private TextView mSubTitleText;
    private TextView mTitleText;
    /* access modifiers changed from: private */
    public FollowButton mUnfollowButton;

    private NetworkImageView getImage(int i) {
        if (this.mImageViews.size() > i) {
            return (NetworkImageView) this.mImageViews.get(i);
        }
        return null;
    }

    public static int getNumColumn() {
        return TabletUtil.isTablet() ? 5 : 3;
    }

    public static int getImageViewSize() {
        return getNumColumn() * 2;
    }

    public ProfileFollowedWishlistRowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.profile_followed_wishlist_row, this);
        this.mProfileImageView = (ProfileImageView) inflate.findViewById(R.id.fragment_wishlist_item_profile_image_view);
        this.mTitleText = (TextView) inflate.findViewById(R.id.fragment_wishlist_profile_item_title);
        this.mSubTitleText = (TextView) inflate.findViewById(R.id.fragment_wishlist_profile_item_sub_title);
        this.mProductCountText = (TextView) inflate.findViewById(R.id.fragment_wishlist_profile_item_new_count);
        this.mRow1 = (LinearLayout) inflate.findViewById(R.id.fragment_wishlist_profile_item_images_row1);
        this.mRow2 = (LinearLayout) inflate.findViewById(R.id.fragment_wishlist_profile_item_images_row2);
        this.mUnfollowButton = (FollowButton) inflate.findViewById(R.id.wishlist_unfollow_button);
        this.mImageViews = new ArrayList<>();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        setDescendantFocusability(393216);
        setOrientation(1);
        float dimension = getResources().getDimension(R.dimen.eight_padding);
        LayoutParams layoutParams = new LayoutParams(-1, (int) ((((float) DisplayUtil.getDisplayWidth()) - (((float) (getNumColumn() - 1)) * dimension)) / ((float) getNumColumn())));
        this.mRow1.setLayoutParams(layoutParams);
        this.mRow2.setLayoutParams(layoutParams);
        int i = 0;
        while (i < getNumColumn() * 2) {
            NetworkImageView networkImageView = new NetworkImageView(getContext());
            LayoutParams layoutParams2 = new LayoutParams(0, -1);
            layoutParams2.weight = 1.0f;
            layoutParams2.setMargins((i == 0 || i == getNumColumn()) ? 0 : (int) dimension, 0, 0, (int) dimension);
            networkImageView.setLayoutParams(layoutParams2);
            networkImageView.setPlaceholderColor(getContext().getResources().getColor(R.color.light_gray_3));
            this.mImageViews.add(networkImageView);
            if (i < getNumColumn()) {
                this.mRow1.addView(networkImageView);
            } else {
                this.mRow2.addView(networkImageView);
            }
            i++;
        }
    }

    public void setup(final WishFollowedWishlist wishFollowedWishlist, final ProfileFragment profileFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mTitleText.setText(wishFollowedWishlist.getName());
        this.mSubTitleText.setText(wishFollowedWishlist.getUserObject().getName());
        this.mProfileImageView.clear();
        this.mProfileImageView.setup(wishFollowedWishlist.getUserObject().getProfileImage(), wishFollowedWishlist.getUserObject().getFirstName());
        this.mUnfollowButton.setButtonMode(ButtonMode.Selected);
        this.mUnfollowButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_FROM_PROFILE);
                profileFragment.withServiceFragment(new ServiceTask<ProfileActivity, ProfileServiceFragment>() {
                    public void performTask(ProfileActivity profileActivity, final ProfileServiceFragment profileServiceFragment) {
                        profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(profileActivity.getString(R.string.wishlist_unfollow_message, new Object[]{wishFollowedWishlist.getName()}), null), new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_CANCEL);
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                if (i == 1) {
                                    ProfileFollowedWishlistRowView.this.mUnfollowButton.setButtonMode(ButtonMode.SelectedLoading);
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_CONFIRM);
                                    profileServiceFragment.unFollowWishlist(wishFollowedWishlist);
                                }
                            }
                        });
                    }
                });
            }
        });
        if (this.mProfileImageView != null) {
            this.mProfileImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (wishFollowedWishlist.getUserObject().getUserState() == WishUserState.Registered) {
                        String userId = wishFollowedWishlist.getUserObject().getUserId();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_AUTHOR_PHOTO_MERCHANT_PROFILE_REVIEWS);
                        Intent intent = new Intent();
                        intent.setClass(ProfileFollowedWishlistRowView.this.getContext(), ProfileActivity.class);
                        intent.putExtra(ProfileActivity.EXTRA_USER_ID, userId);
                        ProfileFollowedWishlistRowView.this.getContext().startActivity(intent);
                    }
                }
            });
            this.mProfileImageView.setImagePrefetcher(new ImageHttpPrefetcher());
        }
        int i = 0;
        if (wishFollowedWishlist.getNewProductsCount() > 0) {
            this.mProductCountText.setVisibility(0);
            this.mProductCountText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.wishlist_new_count, wishFollowedWishlist.getNewProductsCount(), new Object[]{Integer.valueOf(wishFollowedWishlist.getNewProductsCount())}));
        } else {
            this.mProductCountText.setVisibility(8);
        }
        ArrayList productPreviews = wishFollowedWishlist.getProductPreviews();
        while (i < productPreviews.size() && i < this.mImageViews.size()) {
            getImage(i).setImagePrefetcher(imageHttpPrefetcher);
            getImage(i).setImage(((WishProduct) productPreviews.get(i)).getImage(), true);
            i++;
        }
        this.mFollowedWishlist = wishFollowedWishlist;
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            getImage(i).releaseImages();
        }
        this.mProfileImageView.releaseImages();
    }

    public void restoreImages() {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            getImage(i).restoreImages();
        }
        this.mProfileImageView.restoreImages();
    }
}
