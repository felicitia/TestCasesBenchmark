package com.contextlogic.wish.activity.profile.follow;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.view.ProfileImageView;
import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<WishUser> {
    private UserListFragment mFragment;
    private ListView mListView;
    private ArrayList<WishUser> mUsers = new ArrayList<>();

    static class ItemRowHolder {
        FollowButton followButton;
        ProfileImageView profileImageView;
        TextView titleText;
        WishUser user;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public UserListAdapter(Context context, ListView listView, UserListFragment userListFragment) {
        super(context, R.layout.user_list_fragment_row);
        this.mFragment = userListFragment;
        this.mListView = listView;
    }

    public void setUsers(ArrayList<WishUser> arrayList) {
        this.mUsers = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mUsers.size();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.user_list_fragment_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.profileImageView = (ProfileImageView) view.findViewById(R.id.user_list_fragment_row_profile_image_view);
            itemRowHolder.titleText = (TextView) view.findViewById(R.id.user_list_fragment_row_text);
            itemRowHolder.followButton = (FollowButton) view.findViewById(R.id.user_list_fragment_row_follow_button);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        WishUser item = getItem(i);
        itemRowHolder.user = item;
        itemRowHolder.profileImageView.clear();
        itemRowHolder.profileImageView.setup(item.getProfileImage(), item.getName());
        itemRowHolder.titleText.setText(item.getName());
        if (item.isWishStar()) {
            itemRowHolder.titleText.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding));
            itemRowHolder.titleText.setCompoundDrawablesWithIntrinsicBounds(null, null, view.getResources().getDrawable(R.drawable.wishstar_badge_16), null);
        } else {
            itemRowHolder.titleText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        itemRowHolder.followButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UserListAdapter.this.handleFollowButtonClick(itemRowHolder.followButton, i);
            }
        });
        refreshFollowButtonState(itemRowHolder);
        return view;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    if (itemRowHolder.profileImageView != null) {
                        itemRowHolder.profileImageView.releaseImages();
                    }
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    if (itemRowHolder.profileImageView != null) {
                        itemRowHolder.profileImageView.restoreImages();
                    }
                }
            }
        }
    }

    public void refreshFollowButtonStates() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    refreshFollowButtonState((ItemRowHolder) tag);
                }
            }
        }
    }

    private void refreshFollowButtonState(ItemRowHolder itemRowHolder) {
        itemRowHolder.followButton.setVisibility(itemRowHolder.user.isLoggedInUser() ? 8 : 0);
        itemRowHolder.followButton.setButtonMode(itemRowHolder.user.isFollowing() ? ButtonMode.Selected : ButtonMode.Unselected);
    }

    public WishUser getItem(int i) {
        return (WishUser) this.mUsers.get(i);
    }

    /* access modifiers changed from: private */
    public void handleFollowButtonClick(FollowButton followButton, int i) {
        WishUser item = getItem(i);
        if (!item.isFollowing()) {
            followButton.setButtonMode(ButtonMode.UnselectedLoading);
            this.mFragment.followUser(item.getUserId());
            return;
        }
        this.mFragment.unfollowUser(followButton, item.getUserId());
    }

    public void handleFollowSuccess(String str) {
        refreshFollowButtonStates();
    }

    public void handleUnfollowSuccess(String str) {
        refreshFollowButtonStates();
    }
}
