package com.contextlogic.wish.activity.feed.dailyraffle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import com.contextlogic.wish.ui.view.ProfileImageView;
import java.util.ArrayList;
import java.util.List;

public class UserListHorizontalAdapter extends Adapter {
    private Context mContext;
    private List<WishUser> mUsers = new ArrayList();
    private int mUsersNotShownCount = 0;

    static class UserProfileBorderedView extends FrameLayout implements ImageRestorable {
        ProfileImageView profileImage;
        LinearLayout profileImageBorder;

        public UserProfileBorderedView(Context context) {
            this(context, null);
        }

        public UserProfileBorderedView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            init(context);
        }

        private void init(Context context) {
            this.profileImage = new ProfileImageView(context, context.getResources().getDimensionPixelOffset(R.dimen.daily_raffle_winners_horizontal_list_image_size), context.getResources().getDimensionPixelOffset(R.dimen.daily_raffle_winners_horizontal_list_text_size));
            this.profileImageBorder = new LinearLayout(context);
            this.profileImageBorder.setLayoutParams(new LayoutParams(-1, -1));
            this.profileImageBorder.setBackgroundResource(R.drawable.raffle_profile_picture_border);
            this.profileImageBorder.setGravity(17);
            this.profileImageBorder.addView(this.profileImage);
            addView(this.profileImageBorder);
        }

        public void releaseImages() {
            if (this.profileImage != null) {
                this.profileImage.releaseImages();
            }
        }

        public void restoreImages() {
            if (this.profileImage != null) {
                this.profileImage.restoreImages();
            }
        }

        public void setup(WishImage wishImage, String str) {
            if (this.profileImage != null) {
                this.profileImage.setup(wishImage, str);
            }
        }
    }

    public boolean includeLeadingMargin() {
        return false;
    }

    public boolean includeTrailingMargin() {
        return false;
    }

    UserListHorizontalAdapter(Context context) {
        this.mContext = context;
    }

    public void setUsers(List<WishUser> list) {
        if (this.mUsers == null) {
            this.mUsers = new ArrayList();
        }
        this.mUsers.clear();
        this.mUsers.addAll(list.subList(0, Math.min(list.size(), 6)));
        if (list.size() > 6) {
            this.mUsersNotShownCount = list.size() - 6;
        } else {
            this.mUsersNotShownCount = 0;
        }
    }

    public int getItemWidth(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_raffle_winners_horizontal_list_image_background_size);
    }

    public int getItemHeight(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_raffle_winners_horizontal_list_image_background_size);
    }

    public int getCount() {
        if (this.mUsers != null) {
            return this.mUsers.size();
        }
        return 0;
    }

    public WishUser getItem(int i) {
        if (getCount() > i) {
            return (WishUser) this.mUsers.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        UserProfileBorderedView userProfileBorderedView;
        WishUser item = getItem(i);
        if (item == null) {
            return view;
        }
        if (view == null || !(view instanceof UserProfileBorderedView)) {
            userProfileBorderedView = new UserProfileBorderedView(this.mContext);
        } else {
            userProfileBorderedView = (UserProfileBorderedView) view;
        }
        userProfileBorderedView.setup(item.getProfileImage(), item.getName());
        userProfileBorderedView.bringToFront();
        return userProfileBorderedView;
    }

    public int getItemMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_raffle_winners_horizontal_list_inset_distance);
    }

    /* access modifiers changed from: 0000 */
    public int getUsersNotShownCount() {
        return this.mUsersNotShownCount;
    }
}
