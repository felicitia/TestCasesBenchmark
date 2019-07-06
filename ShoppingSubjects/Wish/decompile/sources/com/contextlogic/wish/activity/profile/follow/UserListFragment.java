package com.contextlogic.wish.activity.profile.follow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.VisibilityMode;
import java.util.ArrayList;

public class UserListFragment extends LoadingUiFragment<UserListActivity> {
    private UserListAdapter mAdapter;
    /* access modifiers changed from: private */
    public int mLastItem;
    private ListView mListView;
    private LoadingFooterView mLoadingFooterView;
    /* access modifiers changed from: private */
    public UserListMode mMode;
    /* access modifiers changed from: private */
    public boolean mNoMoreItems;
    /* access modifiers changed from: private */
    public int mOffset;
    /* access modifiers changed from: private */
    public String mSetId;
    private ArrayList<WishUser> mUsers;

    public enum UserListMode {
        Following,
        Followers
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.user_list_fragment;
    }

    public void handleLoadingFailure() {
    }

    public void initializeLoadingContentView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.user_list_fragment_listview);
        this.mAdapter = new UserListAdapter(getActivity(), this.mListView, this);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UserListFragment.this.handleUserSelected(i);
            }
        });
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int i4 = i + i2;
                if (i3 >= 2) {
                    i3 -= 2;
                }
                if (i4 == i3 && UserListFragment.this.mLastItem != i4) {
                    if (!UserListFragment.this.mNoMoreItems) {
                        UserListFragment.this.loadUsersList();
                    }
                    UserListFragment.this.mLastItem = i4;
                }
            }
        });
        this.mListView.setAdapter(this.mAdapter);
        this.mLoadingFooterView = new LoadingFooterView(getActivity());
        this.mLoadingFooterView.setReserveSpaceWhenHidden(false);
        this.mLoadingFooterView.setVisibilityMode(VisibilityMode.HIDDEN);
        this.mLoadingFooterView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UserListFragment.this.loadUsersList();
            }
        });
        this.mListView.addFooterView(this.mLoadingFooterView);
        getLoadingPageView().setLoadingFooter(this.mLoadingFooterView);
        getLoadingPageView().setNoItemsMessage(getString(R.string.no_users_found));
        initializeValues();
    }

    private void initializeValues() {
        this.mUsers = new ArrayList<>();
        this.mOffset = 0;
        this.mLastItem = 0;
        this.mNoMoreItems = false;
        withActivity(new ActivityTask<UserListActivity>() {
            public void performTask(UserListActivity userListActivity) {
                UserListFragment.this.mSetId = userListActivity.getSetId();
                UserListFragment.this.mMode = userListActivity.getMode();
            }
        });
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null) {
            getLoadingPageView().isLoadingComplete();
        }
    }

    public void restoreImages() {
        if (this.mAdapter != null) {
            this.mAdapter.restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mAdapter != null) {
            this.mAdapter.releaseImages();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void handleReload() {
        this.mOffset = 0;
        this.mLastItem = 0;
        this.mNoMoreItems = false;
        loadUsersList();
    }

    public void loadUsersList() {
        if (!this.mNoMoreItems) {
            withServiceFragment(new ServiceTask<BaseActivity, UserListServiceFragment>() {
                public void performTask(BaseActivity baseActivity, UserListServiceFragment userListServiceFragment) {
                    userListServiceFragment.loadUsers(UserListFragment.this.mSetId, UserListFragment.this.mOffset);
                }
            });
        }
    }

    public void followUser(final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, UserListServiceFragment>() {
            public void performTask(BaseActivity baseActivity, UserListServiceFragment userListServiceFragment) {
                userListServiceFragment.followUser(str);
            }
        });
    }

    public void unfollowUser(final FollowButton followButton, final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, UserListServiceFragment>() {
            public void performTask(BaseActivity baseActivity, UserListServiceFragment userListServiceFragment) {
                userListServiceFragment.unfollowUser(followButton, str);
            }
        });
    }

    public void handleFollowUserSuccess(String str) {
        handleIntentFlagUpdates();
        this.mAdapter.handleFollowSuccess(str);
    }

    public void handleUnfollowUserSuccess(String str) {
        handleIntentFlagUpdates();
        this.mAdapter.handleUnfollowSuccess(str);
    }

    public void handleFollowUserFailure() {
        this.mAdapter.refreshFollowButtonStates();
    }

    public void handleLoadingSuccess(ArrayList<WishUser> arrayList, int i, boolean z) {
        if (z) {
            getLoadingPageView().markNoMoreItems();
            this.mLoadingFooterView.setVisibilityMode(VisibilityMode.HIDDEN);
        }
        this.mUsers.addAll(arrayList);
        this.mOffset = i;
        this.mNoMoreItems = z;
        getLoadingPageView().markLoadingComplete();
        if (this.mAdapter != null) {
            this.mAdapter.setUsers(this.mUsers);
        }
    }

    /* access modifiers changed from: private */
    public void handleUserSelected(int i) {
        final WishUser item = this.mAdapter.getItem(i);
        withActivity(new ActivityTask<UserListActivity>() {
            public void performTask(UserListActivity userListActivity) {
                Intent intent = new Intent();
                intent.setClass(userListActivity, ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_USER_ID, item.getUserId());
                userListActivity.startActivity(intent);
            }
        });
    }

    private void handleIntentFlagUpdates() {
        withActivity(new ActivityTask<UserListActivity>() {
            public void performTask(UserListActivity userListActivity) {
                Intent intent = new Intent();
                intent.putExtra("ExtraRequiresReload", true);
                userListActivity.setResult(-1, intent);
            }
        });
    }

    public boolean hasItems() {
        return this.mUsers != null && this.mUsers.size() > 0;
    }
}
