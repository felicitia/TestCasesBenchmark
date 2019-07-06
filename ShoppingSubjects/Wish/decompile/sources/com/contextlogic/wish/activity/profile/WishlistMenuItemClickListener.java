package com.contextlogic.wish.activity.profile;

import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.contextlogic.wish.R;

public class WishlistMenuItemClickListener implements OnMenuItemClickListener {
    private ProfileWishlistAdapter mProfileAdapter;
    private int mWishlistPosition;

    public WishlistMenuItemClickListener(ProfileWishlistAdapter profileWishlistAdapter, int i) {
        this.mProfileAdapter = profileWishlistAdapter;
        this.mWishlistPosition = i;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profile_wishlist_private /*2131298076*/:
                this.mProfileAdapter.togglePrivacy(this.mWishlistPosition);
                return true;
            case R.id.profile_wishlist_rename /*2131298077*/:
                this.mProfileAdapter.renameWishlist(this.mWishlistPosition);
                return true;
            case R.id.profile_wishlist_share /*2131298078*/:
                this.mProfileAdapter.shareWishlist(this.mWishlistPosition);
                return true;
            default:
                return false;
        }
    }
}
