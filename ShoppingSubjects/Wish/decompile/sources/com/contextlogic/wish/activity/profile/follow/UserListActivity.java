package com.contextlogic.wish.activity.profile.follow;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.profile.follow.UserListFragment.UserListMode;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class UserListActivity extends DrawerActivity {
    public static String EXTRA_SET_ID = "ExtraUserSetId";
    public static String EXTRA_USER_LIST_MODE = "ExtraUserListMode";

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public String getMenuKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new UserListServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new UserListFragment();
    }

    public String getActionBarTitle() {
        if (getMode() == UserListMode.Followers) {
            return getString(R.string.follower_title);
        }
        return getString(R.string.following_title);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.UserList;
    }

    public UserListMode getMode() {
        return UserListMode.values()[getIntent().getIntExtra(EXTRA_USER_LIST_MODE, 0)];
    }

    public String getSetId() {
        return getIntent().getStringExtra(EXTRA_SET_ID);
    }
}
