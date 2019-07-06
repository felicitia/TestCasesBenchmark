package com.contextlogic.wish.activity.productdetails.recommendwishlists;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.profile.wishlist.WishlistActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class RecommendedWishlistAdapter extends Adapter {
    private DrawerActivity mBaseActivity;
    private ArrayList<WishWishlist> mRecommendedWishlists;

    private static class ViewHolderItem {
        NetworkImageView leftMainTile;
        NetworkImageView rightBottomTile;
        NetworkImageView rightTopTile;
        ProfileImageView wishlistCreatorProfileImageView;
        ThemedTextView wishlistCreatorUsername;
        ThemedTextView wishlistTitle;

        private ViewHolderItem() {
        }
    }

    public RecommendedWishlistAdapter(DrawerActivity drawerActivity, ArrayList<WishWishlist> arrayList) {
        this.mBaseActivity = drawerActivity;
        this.mRecommendedWishlists = arrayList;
    }

    public int getItemMargin() {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.discover_wishlist_listview_item_margin);
    }

    public int getItemWidth(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.discover_wishlist_total_tile_width);
    }

    public int getItemHeight(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.discover_wishlist_total_tile_height);
    }

    public int getCount() {
        if (this.mRecommendedWishlists != null) {
            return this.mRecommendedWishlists.size();
        }
        return 0;
    }

    public WishWishlist getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (WishWishlist) this.mRecommendedWishlists.get(i);
    }

    public void setRecommendedWishlists(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2) {
        if (arrayList != null && arrayList2 != null) {
            this.mRecommendedWishlists = new ArrayList<>();
            int size = arrayList.size();
            int size2 = arrayList2.size();
            if (size >= size2) {
                size = size2;
            }
            for (int i = 0; i < size; i++) {
                WishWishlist wishWishlist = (WishWishlist) arrayList.get(i);
                WishUser wishUser = (WishUser) arrayList2.get(i);
                if (!(wishWishlist == null || wishWishlist.getProductPreviews() == null || wishWishlist.getProductPreviews().size() < 3 || wishUser == null)) {
                    wishWishlist.setUserObject(wishUser);
                    this.mRecommendedWishlists.add(wishWishlist);
                }
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderItem viewHolderItem;
        if (view != null) {
            viewHolderItem = (ViewHolderItem) view.getTag();
        } else {
            view = this.mBaseActivity.getLayoutInflater().inflate(R.layout.product_details_recommended_wishlist_tile, null);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.wishlistTitle = (ThemedTextView) view.findViewById(R.id.tile_wishlist_title);
            viewHolderItem.wishlistCreatorUsername = (ThemedTextView) view.findViewById(R.id.tile_wishlist_creator_username);
            viewHolderItem.wishlistCreatorProfileImageView = (ProfileImageView) view.findViewById(R.id.tile_user_profile_image);
            viewHolderItem.leftMainTile = (NetworkImageView) view.findViewById(R.id.tile_left_main_image);
            viewHolderItem.rightTopTile = (NetworkImageView) view.findViewById(R.id.tile_right_top_image);
            viewHolderItem.rightBottomTile = (NetworkImageView) view.findViewById(R.id.tile_right_bottom_image);
            view.setTag(viewHolderItem);
        }
        loadContent(viewHolderItem, i);
        return view;
    }

    public void loadContent(ViewHolderItem viewHolderItem, int i) {
        WishWishlist item = getItem(i);
        if (item != null) {
            ArrayList productPreviews = item.getProductPreviews();
            WishUser userObject = item.getUserObject();
            String name = item.getName();
            if (name != null) {
                viewHolderItem.wishlistTitle.setText(name);
            }
            if (userObject != null) {
                String name2 = userObject.getName();
                if (name2 != null) {
                    viewHolderItem.wishlistCreatorUsername.setText(name2);
                }
                viewHolderItem.wishlistCreatorProfileImageView.clear();
                viewHolderItem.wishlistCreatorProfileImageView.setup(userObject.getProfileImage(), userObject.getFirstName());
            }
            if (productPreviews != null && productPreviews.size() >= 3) {
                WishProduct wishProduct = (WishProduct) productPreviews.get(0);
                if (wishProduct != null) {
                    viewHolderItem.leftMainTile.setImage(wishProduct.getImage());
                }
                WishProduct wishProduct2 = (WishProduct) productPreviews.get(1);
                if (wishProduct2 != null) {
                    viewHolderItem.rightTopTile.setImage(wishProduct2.getImage());
                }
                WishProduct wishProduct3 = (WishProduct) productPreviews.get(2);
                if (wishProduct3 != null) {
                    viewHolderItem.rightBottomTile.setImage(wishProduct3.getImage());
                }
            }
        }
    }

    public void onWishlistClicked(int i) {
        Intent intent = new Intent();
        intent.setClass(this.mBaseActivity, WishlistActivity.class);
        IntentUtil.putParcelableExtra(intent, WishlistActivity.EXTRA_WISHLIST, getItem(i));
        intent.putExtra(WishlistActivity.EXTRA_CAN_EDIT_WISHLIST, false);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECOMMENDED_WISHLIST);
        this.mBaseActivity.startActivity(intent);
    }
}
