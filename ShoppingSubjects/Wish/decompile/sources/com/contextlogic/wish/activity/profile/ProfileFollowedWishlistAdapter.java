package com.contextlogic.wish.activity.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishFollowedWishlist;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ContainerRestorable;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;
import java.util.ArrayList;

public class ProfileFollowedWishlistAdapter extends ArrayAdapter<WishWishlist> implements TextTabProvider {
    protected ImageHttpPrefetcher mImagePrefetcher;
    protected ListView mListView;
    private ProfileFragment mProfileFragment;
    protected ArrayList<WishFollowedWishlist> mWishlists = new ArrayList<>();

    public long getItemId(int i) {
        return (long) i;
    }

    public ProfileFollowedWishlistAdapter(Context context, ListView listView, ProfileFragment profileFragment) {
        super(context, R.layout.profile_fragment_my_wishlist_row);
        this.mProfileFragment = profileFragment;
        this.mListView = listView;
    }

    public void setFollowedWishlists(ArrayList<WishFollowedWishlist> arrayList) {
        this.mWishlists = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mWishlists.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProfileFollowedWishlistRowView profileFollowedWishlistRowView;
        if (view == null) {
            profileFollowedWishlistRowView = new ProfileFollowedWishlistRowView(getContext());
        } else {
            profileFollowedWishlistRowView = (ProfileFollowedWishlistRowView) view;
        }
        profileFollowedWishlistRowView.setup((WishFollowedWishlist) this.mWishlists.get(i), this.mProfileFragment, this.mImagePrefetcher);
        return profileFollowedWishlistRowView;
    }

    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.wishlist);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            ContainerRestorable.releaseChildren(this.mListView);
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            ContainerRestorable.restoreChildren(this.mListView);
        }
    }

    public WishWishlist getItem(int i) {
        if (this.mWishlists.size() > i) {
            return (WishWishlist) this.mWishlists.get(i);
        }
        return null;
    }

    public void removeWishlist(WishWishlist wishWishlist) {
        this.mWishlists.remove(wishWishlist);
        notifyDataSetChanged();
    }
}
