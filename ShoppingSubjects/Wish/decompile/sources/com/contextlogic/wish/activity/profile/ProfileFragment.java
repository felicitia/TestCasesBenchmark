package com.contextlogic.wish.activity.profile;

import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.profile.ProfileHeaderView.HeaderTabState;
import com.contextlogic.wish.activity.profile.ProfileHeaderView.WishlistSectionState;
import com.contextlogic.wish.activity.profile.follow.UserListActivity;
import com.contextlogic.wish.activity.profile.follow.UserListFragment.UserListMode;
import com.contextlogic.wish.activity.profile.wishlist.WishlistActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishFollowedWishlist;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileFragment extends LoadingUiFragment<ProfileActivity> implements ProfileHeaderInterface, BottomNavigationInterface {
    private int mActionBarHeight;
    /* access modifiers changed from: private */
    public BottomNavigationHelper mBottomNavigationHelper;
    /* access modifiers changed from: private */
    public HeaderTabState mCurrentTabState;
    private OnGlobalLayoutListener mDeferredEmptyStateResizeAction;
    private ArrayList<WishFollowedWishlist> mFollowedWishlists;
    /* access modifiers changed from: private */
    public View mFooter;
    private TextView mFooterActionButton;
    private TextView mFooterMessageText;
    private View mFooterProgressBar;
    private TextView mFooterTitleText;
    /* access modifiers changed from: private */
    public ProfileHeaderView mHeaderView;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<BaseAdapter> mListAdapter;
    /* access modifiers changed from: private */
    public ListeningListView mListView;
    private boolean mLoadingComplete;
    /* access modifiers changed from: private */
    public boolean mNoMoreItems;
    /* access modifiers changed from: private */
    public int mOffset;
    private ArrayList<WishImage> mPhotos;
    private ProfileProductRatingsAdapter mProductRatingsAdapter;
    /* access modifiers changed from: private */
    public ProfileFollowedWishlistAdapter mProfileFollowedWishlistAdapter;
    private ProfilePhotosAdapter mProfilePhotosAdapter;
    private boolean mProfilePictureChanged;
    /* access modifiers changed from: private */
    public ProfileWishlistAdapter mProfileWishlistAdapter;
    private ArrayList<WishRating> mRatings;
    private ThemedButton mRedesignCreateWishlistButton;
    /* access modifiers changed from: private */
    public View mRedesignEmptyStateFooter;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mRedesignEmptyStateFooterButton;
    /* access modifiers changed from: private */
    public TextView mRedesignEmptyStateFooterSubtitle;
    /* access modifiers changed from: private */
    public TextView mRedesignEmptyStateFooterTitle;
    /* access modifiers changed from: private */
    public boolean mShouldSeeNotificationUiPref;
    /* access modifiers changed from: private */
    public WishUser mUser;
    /* access modifiers changed from: private */
    public String mUserId;
    /* access modifiers changed from: private */
    public WishlistSectionState mWishlistSectionState;
    /* access modifiers changed from: private */
    public WishlistState mWishlistState;
    private ArrayList<WishWishlist> mWishlists;

    public enum WishlistState {
        NO_RESULTS,
        NO_ITEM_IN_WISHLIST,
        ACTIVE,
        LOAD_ERROR
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.profile_fragment;
    }

    public void trackEvent(WishAnalyticsEvent wishAnalyticsEvent) {
        HashMap hashMap = new HashMap();
        if (this.mUser != null) {
            hashMap.put("profile_user_id", this.mUser.getUserId());
        }
        hashMap.put("viewing_user_id", ProfileDataCenter.getInstance().getUserId());
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent.getValue(), (String) null, hashMap);
    }

    public void initializeLoadingContentView(View view) {
        this.mListView = (ListeningListView) view.findViewById(R.id.profile_fragment_listview);
        this.mHeaderView = new ProfileHeaderView(getContext());
        this.mHeaderView.findViewById(R.id.wish_star_profile_text).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.showWishStarDialog();
            }
        });
        this.mFooter = getActivity().getLayoutInflater().inflate(R.layout.profile_fragment_footer, null);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            this.mFooterProgressBar = this.mFooter.findViewById(R.id.profile_fragment_footer_three_dot_progress_bar);
        } else {
            this.mFooterProgressBar = this.mFooter.findViewById(R.id.profile_fragment_footer_progress_bar);
        }
        this.mFooterProgressBar.setVisibility(8);
        this.mFooterTitleText = (TextView) this.mFooter.findViewById(R.id.profile_fragment_footer_title);
        this.mFooterMessageText = (TextView) this.mFooter.findViewById(R.id.profile_fragment_footer_message);
        this.mFooterActionButton = (TextView) this.mFooter.findViewById(R.id.profile_fragment_footer_action_button);
        this.mFooterActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.handleAddWishList();
            }
        });
        this.mShouldSeeNotificationUiPref = !PreferenceUtil.getBoolean("HideProfileWishlistTutorial");
        this.mRedesignEmptyStateFooter = getActivity().getLayoutInflater().inflate(R.layout.redesigned_profile_fragment_footer, this.mListView, false);
        this.mRedesignEmptyStateFooterButton = (AutoReleasableImageView) this.mRedesignEmptyStateFooter.findViewById(R.id.redesign_profile_footer_button);
        this.mRedesignEmptyStateFooterTitle = (TextView) this.mRedesignEmptyStateFooter.findViewById(R.id.redesign_profile_footer_title);
        this.mRedesignEmptyStateFooterSubtitle = (TextView) this.mRedesignEmptyStateFooter.findViewById(R.id.redesign_profile_footer_subtitle);
        this.mRedesignCreateWishlistButton = (ThemedButton) this.mRedesignEmptyStateFooter.findViewById(R.id.redesign_profile_create_new_wishlist_button);
        this.mRedesignEmptyStateFooterButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.mListView.smoothScrollToPosition(1);
            }
        });
        this.mRedesignCreateWishlistButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.handleAddWishList();
            }
        });
        hideRedesignEmptyStateFooter();
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ProfileFragment.this.handleWishlistSelected(i);
            }
        });
        this.mListView.addHeaderView(this.mHeaderView);
        this.mListView.addFooterView(this.mRedesignEmptyStateFooter);
        this.mListView.addFooterView(this.mFooter);
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                WishWishlist wishWishlist;
                if (ProfileFragment.this.mListView.getFirstVisiblePosition() > 0) {
                    ProfileFragment.this.onVerticalScrollPositionUpdated(ProfileFragment.this.getActivity().getResources().getDisplayMetrics().heightPixels);
                } else if (ProfileFragment.this.mListView.getChildAt(0) == null) {
                    ProfileFragment.this.onVerticalScrollPositionUpdated(0);
                } else {
                    ProfileFragment.this.onVerticalScrollPositionUpdated(-ProfileFragment.this.mListView.getChildAt(0).getTop());
                }
                if (i2 + i >= (i3 >= 2 ? i3 - 2 : i3) && !ProfileFragment.this.mNoMoreItems && ProfileFragment.this.mWishlistState != WishlistState.LOAD_ERROR) {
                    ProfileFragment.this.loadTabData();
                }
                if (ProfileFragment.this.mImagePrefetcher != null && ProfileFragment.this.mListView.getLastVisiblePosition() >= 0 && ProfileFragment.this.mCurrentTabState == HeaderTabState.WISHLISTS) {
                    int headerViewsCount = i3 - (ProfileFragment.this.mListView.getHeaderViewsCount() + ProfileFragment.this.mListView.getFooterViewsCount());
                    int min = Math.min(Math.max(i, ProfileFragment.this.mListView.getLastVisiblePosition()) + 1, headerViewsCount);
                    int min2 = Math.min(min + 5, headerViewsCount);
                    while (min < min2) {
                        if (ProfileFragment.this.mWishlistSectionState == WishlistSectionState.USER) {
                            wishWishlist = ProfileFragment.this.mProfileWishlistAdapter.getItem(min);
                        } else {
                            wishWishlist = ProfileFragment.this.mProfileFollowedWishlistAdapter.getItem(min);
                        }
                        if (!(wishWishlist == null || wishWishlist.getProductPreviews() == null)) {
                            int min3 = Math.min(wishWishlist.getProductPreviews().size(), ProfileFollowedWishlistRowView.getImageViewSize());
                            for (int i4 = 0; i4 < min3; i4++) {
                                ProfileFragment.this.mImagePrefetcher.queueImage(((WishProduct) wishWishlist.getProductPreviews().get(i4)).getImage());
                            }
                        }
                        min++;
                    }
                }
            }
        });
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            this.mBottomNavigationHelper = new BottomNavigationHelper(this);
            this.mListView.setScrollViewListener(new ScrollViewListener() {
                public void onScrollChanged(int i, int i2) {
                    ProfileFragment.this.mBottomNavigationHelper.handleScrollChanged(i2);
                }
            });
        }
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mProfileWishlistAdapter = new ProfileWishlistAdapter(getActivity(), this.mListView, this);
        this.mProfileFollowedWishlistAdapter = new ProfileFollowedWishlistAdapter(getActivity(), this.mListView, this);
        this.mProductRatingsAdapter = new ProfileProductRatingsAdapter(getActivity(), this.mListView, this);
        this.mProfilePhotosAdapter = new ProfilePhotosAdapter(getActivity(), this.mListView, this);
        this.mProfileWishlistAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mProfileFollowedWishlistAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mProfilePhotosAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mProductRatingsAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mListView.setAdapter(this.mProfileWishlistAdapter);
        this.mListAdapter = new ArrayList<>();
        this.mListAdapter.add(this.mProfileWishlistAdapter);
        this.mListAdapter.add(this.mProductRatingsAdapter);
        this.mListAdapter.add(this.mProfilePhotosAdapter);
        this.mWishlistSectionState = WishlistSectionState.USER;
        initializeValues();
    }

    private void initializeValues() {
        this.mWishlistState = WishlistState.NO_RESULTS;
        this.mCurrentTabState = HeaderTabState.WISHLISTS;
        this.mWishlists = new ArrayList<>();
        this.mFollowedWishlists = new ArrayList<>();
        this.mRatings = new ArrayList<>();
        this.mPhotos = new ArrayList<>();
        this.mOffset = 0;
        this.mNoMoreItems = false;
        this.mActionBarHeight = DisplayUtil.getActionBarHeight(getActivity());
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                ProfileFragment.this.mUserId = ((ProfileActivity) ProfileFragment.this.getBaseActivity()).getUserId();
                if (ProfileFragment.this.mUserId == null) {
                    ProfileFragment.this.mUserId = ProfileDataCenter.getInstance().getUserId();
                }
            }
        });
        if (getSavedInstanceState() != null) {
            this.mProfilePictureChanged = getSavedInstanceState().getBoolean("SavedStateProfilePictureChanged");
            WishUser wishUser = (WishUser) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateUser", WishUser.class);
            ArrayList parcelableList = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateWishlist", WishWishlist.class);
            int i = getSavedInstanceState().getInt("SavedStateOffset");
            boolean z = getSavedInstanceState().getBoolean("SavedStateNoMoreItems");
            if (wishUser != null) {
                handleUserLoadingSuccess(wishUser);
                getLoadingPageView().markLoadingComplete();
            }
            this.mCurrentTabState = (HeaderTabState) getSavedInstanceState().getSerializable("SavedStateTabState");
            ArrayList parcelableList2 = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateRatings", WishRating.class);
            ArrayList parcelableList3 = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStatePhotos", WishImage.class);
            if (this.mCurrentTabState == HeaderTabState.WISHLISTS && parcelableList != null && parcelableList.size() > 0) {
                this.mListView.setAdapter(this.mProfileWishlistAdapter);
                handleWishlistLoadingSuccess(parcelableList, i, z);
            }
            if (this.mCurrentTabState == HeaderTabState.REVIEWS && parcelableList2 != null && parcelableList2.size() > 0) {
                this.mListView.setAdapter(this.mProductRatingsAdapter);
                handleLoadingUserProductRatingsSuccess(parcelableList2, i, z);
            }
            if (this.mCurrentTabState == HeaderTabState.PHOTOS && parcelableList3 != null && parcelableList3.size() > 0) {
                this.mListView.setAdapter(this.mProfilePhotosAdapter);
                loadUserPhotosSuccess(parcelableList3, i, z);
            }
        }
        if (isCurrentUser()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MY_PROFILE);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_OTHER_PROFILE);
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putString("SavedStateUser", StateStoreCache.getInstance().storeParcelable(this.mUser));
            bundle.putString("SavedStateWishlist", StateStoreCache.getInstance().storeParcelableList(this.mWishlists));
            bundle.putString("SavedStateFollowedWishlist", StateStoreCache.getInstance().storeParcelableList(this.mFollowedWishlists));
            bundle.putInt("SavedStateOffset", this.mOffset);
            bundle.putBoolean("SavedStateNoMoreItems", this.mNoMoreItems);
            bundle.putBoolean("SavedStateProfilePictureChanged", this.mProfilePictureChanged);
            bundle.putSerializable("SavedStateTabState", this.mCurrentTabState);
            bundle.putString("SavedStateRatings", StateStoreCache.getInstance().storeParcelableList(this.mRatings));
            bundle.putString("SavedStatePhotos", StateStoreCache.getInstance().storeParcelableList(this.mPhotos));
        }
    }

    public void restoreImages() {
        if (this.mProfileWishlistAdapter != null) {
            this.mProfileWishlistAdapter.restoreImages();
        }
        if (this.mProfileFollowedWishlistAdapter != null) {
            this.mProfileFollowedWishlistAdapter.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void releaseImages() {
        if (this.mProfileWishlistAdapter != null) {
            this.mProfileWishlistAdapter.releaseImages();
        }
        if (this.mProfileFollowedWishlistAdapter != null) {
            this.mProfileFollowedWishlistAdapter.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void handleReload() {
        loadUser();
        loadTabData();
    }

    private void loadUser() {
        if (!this.mLoadingComplete) {
            withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                    profileServiceFragment.loadUser(ProfileFragment.this.mUserId);
                }
            });
        } else if (this.mUser != null && !this.mUser.isPreview()) {
            handleUserLoadingSuccess(this.mUser);
            withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                    profileServiceFragment.loadUser(ProfileFragment.this.mUser.getUserId());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        this.mOffset = 0;
        this.mNoMoreItems = false;
        this.mWishlists.clear();
        this.mFollowedWishlists.clear();
        this.mRatings.clear();
        this.mPhotos.clear();
        loadTabData();
    }

    /* access modifiers changed from: private */
    public void loadTabData() {
        if (!this.mNoMoreItems) {
            this.mFooterProgressBar.setVisibility(0);
            this.mFooterTitleText.setVisibility(8);
            this.mFooterMessageText.setVisibility(8);
            this.mFooterActionButton.setVisibility(8);
            final String userId = this.mUserId != null ? this.mUserId : ProfileDataCenter.getInstance().getUserId();
            if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
                withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                        profileServiceFragment.loadUserProductRatings(userId, ProfileFragment.this.mOffset);
                    }
                });
            } else if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
                withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                        if (ProfileFragment.this.mWishlistSectionState == WishlistSectionState.USER) {
                            profileServiceFragment.loadWishlist(userId, ProfileFragment.this.mOffset);
                        } else {
                            profileServiceFragment.loadFollowedWishlist(userId, ProfileFragment.this.mOffset);
                        }
                    }
                });
            } else if (this.mCurrentTabState == HeaderTabState.PHOTOS) {
                withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                        profileServiceFragment.loadUserProductPhotos(userId, ProfileFragment.this.mOffset);
                    }
                });
            }
        }
    }

    public boolean hasItems() {
        boolean z = true;
        if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            if (this.mWishlistSectionState == WishlistSectionState.USER) {
                if (this.mWishlists == null) {
                    z = false;
                }
                return z;
            }
            if (this.mFollowedWishlists == null) {
                z = false;
            }
            return z;
        } else if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
            if (this.mRatings == null) {
                z = false;
            }
            return z;
        } else if (this.mCurrentTabState != HeaderTabState.PHOTOS) {
            return false;
        } else {
            if (this.mPhotos == null) {
                z = false;
            }
            return z;
        }
    }

    public void handleProfilePictureClicked() {
        if (isCurrentUser()) {
            trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_EDIT_PROFILE_PHOTO);
            withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                    profileServiceFragment.changeProfileImage();
                }
            });
        }
    }

    public void handleFollowerClicked() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                Intent intent = new Intent();
                intent.setClass(profileActivity, UserListActivity.class);
                intent.putExtra(UserListActivity.EXTRA_USER_LIST_MODE, UserListMode.Followers.ordinal());
                intent.putExtra(UserListActivity.EXTRA_SET_ID, ProfileFragment.this.mUser.getFollowersSetId());
                profileActivity.startActivity(intent);
            }
        });
    }

    public void handleFollowingClicked() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                Intent intent = new Intent();
                intent.setClass(profileActivity, UserListActivity.class);
                intent.putExtra(UserListActivity.EXTRA_USER_LIST_MODE, UserListMode.Following.ordinal());
                intent.putExtra(UserListActivity.EXTRA_SET_ID, ProfileFragment.this.mUser.getFollowingSetId());
                profileActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleAddWishList() {
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                profileServiceFragment.createWishlist();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleContinueShopping() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                Intent intent = new Intent();
                intent.setClass(profileActivity, BrowseActivity.class);
                profileActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleWishlistSelected(int i) {
        if (this.mListView.getItemAtPosition(i) != null && this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            final WishWishlist wishWishlist = (WishWishlist) this.mListView.getItemAtPosition(i);
            withActivity(new ActivityTask<ProfileActivity>() {
                public void performTask(final ProfileActivity profileActivity) {
                    boolean equals = wishWishlist.getUserId().equals(ProfileDataCenter.getInstance().getUserId());
                    Intent intent = new Intent();
                    intent.setClass(profileActivity, WishlistActivity.class);
                    IntentUtil.putParcelableExtra(intent, WishlistActivity.EXTRA_WISHLIST, wishWishlist);
                    intent.putExtra(WishlistActivity.EXTRA_CAN_EDIT_WISHLIST, equals);
                    profileActivity.startActivityForResult(intent, profileActivity.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (i2 == 1000) {
                                profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonOkDialog(ProfileFragment.this.getString(R.string.done), ProfileFragment.this.getString(R.string.wishlist_deleted)));
                                ProfileFragment.this.reloadData();
                            } else if (i2 == 1001) {
                                ProfileFragment.this.reloadData();
                            }
                        }
                    }));
                }
            });
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SELECT_WISHLIST);
            if (this.mWishlistSectionState == WishlistSectionState.FOLLOWED) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FOLLOWED_WISHLIST_CLICK);
            }
        }
    }

    public void handleFollowButtonClicked(final boolean z) {
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                if (z) {
                    profileServiceFragment.followUser(ProfileFragment.this.mHeaderView, ProfileFragment.this.mUserId);
                } else {
                    profileServiceFragment.unfollowUser(ProfileFragment.this.mHeaderView, ProfileFragment.this.mUserId);
                }
            }
        });
    }

    public void handleUserLoadingSuccess(final WishUser wishUser) {
        setupBaseActionBar();
        if (ExperimentDataCenter.getInstance().shouldRemoveCartFromProfile()) {
            removeCartItemInActionBar();
        }
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                if (ProfileFragment.this.isCurrentUser()) {
                    Drawable drawable = ContextCompat.getDrawable(profileActivity, R.drawable.action_bar_add);
                    drawable.setColorFilter(profileActivity.getActionBarManager().getTextColor(), Mode.SRC_ATOP);
                    profileActivity.getActionBarManager().addActionBarItem(new ActionBarItem(ProfileFragment.this.getString(R.string.create_wishlist), 2000, drawable));
                } else if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState()) {
                } else {
                    if (wishUser.isFollowing()) {
                        profileActivity.getActionBarManager().addActionBarItem(new ActionBarItem(ProfileFragment.this.getString(R.string.following), 2002, (Drawable) null));
                    } else {
                        profileActivity.getActionBarManager().addActionBarItem(new ActionBarItem(ProfileFragment.this.getString(R.string.follow), 2001, (Drawable) null));
                    }
                }
            }
        });
        this.mLoadingComplete = true;
        getLoadingPageView().markLoadingComplete();
        this.mUser = wishUser;
        this.mUserId = wishUser.getUserId();
        if (!this.mProfilePictureChanged) {
            this.mProfilePictureChanged = true;
            withActivity(new ActivityTask<ProfileActivity>() {
                public void performTask(ProfileActivity profileActivity) {
                    if (profileActivity.getProfilePictureNeedsChanging()) {
                        ProfileFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                                profileServiceFragment.changeProfileImage();
                            }
                        });
                    }
                }
            });
        }
        this.mHeaderView.setup(this.mUser, this, this.mListAdapter);
    }

    public void updateUserImage(WishUser wishUser) {
        this.mHeaderView.updateProfileImage(wishUser);
        ProfileDataCenter.getInstance().refresh();
    }

    public void handleWishlistLoadingSuccess(ArrayList<WishWishlist> arrayList, int i, boolean z) {
        this.mWishlists.addAll(arrayList);
        this.mOffset = i;
        this.mNoMoreItems = z;
        if (this.mWishlists.size() == 0) {
            this.mWishlistState = WishlistState.NO_RESULTS;
        } else if (this.mWishlists.size() == 1 && ((WishWishlist) this.mWishlists.get(0)).getProductCount() == 0) {
            this.mWishlistState = WishlistState.NO_ITEM_IN_WISHLIST;
        } else {
            this.mWishlistState = WishlistState.ACTIVE;
        }
        this.mProfileWishlistAdapter.setWishlists(this.mWishlists);
        refreshFooter();
    }

    public void handleFollowedWishlistLoadingSuccess(List<WishFollowedWishlist> list, int i, boolean z) {
        this.mFollowedWishlists.addAll(list);
        this.mOffset = i;
        this.mNoMoreItems = z;
        if (this.mFollowedWishlists.size() == 0) {
            this.mWishlistState = WishlistState.NO_RESULTS;
        } else if (this.mFollowedWishlists.size() == 1 && ((WishFollowedWishlist) this.mFollowedWishlists.get(0)).getProductCount() == 0) {
            this.mWishlistState = WishlistState.NO_ITEM_IN_WISHLIST;
        } else {
            this.mWishlistState = WishlistState.ACTIVE;
        }
        this.mProfileFollowedWishlistAdapter.setFollowedWishlists(this.mFollowedWishlists);
        refreshFooter();
    }

    public void handleCreateWishlistSuccess() {
        this.mNoMoreItems = false;
        reloadData();
    }

    public void handleLoadingWishlistFailure() {
        this.mWishlistState = WishlistState.LOAD_ERROR;
        refreshFooter();
    }

    public void handleLoadingFailure() {
        refreshFooter();
    }

    public void handleFollowActionSuccess(String str) {
        handleReload();
    }

    private void refreshFooter() {
        this.mFooterProgressBar.setVisibility(8);
        this.mFooterTitleText.setVisibility(8);
        this.mFooterMessageText.setVisibility(8);
        this.mFooterActionButton.setVisibility(8);
        hideRedesignEmptyStateFooter();
        if (this.mWishlistState == WishlistState.LOAD_ERROR) {
            updateFooterLoadError();
        } else if (this.mWishlistState == WishlistState.NO_ITEM_IN_WISHLIST) {
            updateFooterNoItemsInWishList();
        } else if (this.mWishlistState == WishlistState.NO_RESULTS && isCurrentUser()) {
            updateRedesignEmptyState();
        } else if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            updateFooterWishlists();
        } else if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
            updateFooterReviews();
        } else if (this.mCurrentTabState == HeaderTabState.PHOTOS) {
            updateFooterPhotos();
        }
    }

    private void hideRedesignEmptyStateFooter() {
        if (this.mRedesignEmptyStateFooter != null) {
            this.mRedesignEmptyStateFooterButton.setVisibility(8);
            this.mRedesignEmptyStateFooterTitle.setVisibility(8);
            this.mRedesignEmptyStateFooterSubtitle.setVisibility(8);
            this.mRedesignCreateWishlistButton.setVisibility(8);
            LayoutParams layoutParams = this.mRedesignEmptyStateFooter.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = 0;
                this.mRedesignEmptyStateFooter.setLayoutParams(layoutParams);
            }
            this.mFooter.setPadding(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.triple_screen_padding));
        }
    }

    private void showRedesignEmptyStateFooter() {
        if (this.mRedesignEmptyStateFooter != null) {
            if (this.mDeferredEmptyStateResizeAction == null) {
                this.mDeferredEmptyStateResizeAction = new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        int i;
                        if (ProfileFragment.this.mListView.getHeight() > 0) {
                            LayoutParams layoutParams = ProfileFragment.this.mRedesignEmptyStateFooter.getLayoutParams();
                            if (ProfileFragment.this.mCurrentTabState != HeaderTabState.WISHLISTS || ProfileFragment.this.mWishlistSectionState == WishlistSectionState.FOLLOWED || !ProfileFragment.this.mShouldSeeNotificationUiPref) {
                                i = ProfileFragment.this.getResources().getDimensionPixelSize(R.dimen.redesign_profile_footer_min_height);
                            } else {
                                i = ProfileFragment.this.getResources().getDimensionPixelSize(R.dimen.redesign_profile_footer_min_height_tutorial);
                            }
                            layoutParams.height = Math.max(i, ProfileFragment.this.mListView.getHeight() - ProfileFragment.this.mHeaderView.getHeight());
                            ProfileFragment.this.mRedesignEmptyStateFooter.setLayoutParams(layoutParams);
                            ProfileFragment.this.mRedesignEmptyStateFooterButton.setVisibility(0);
                            ProfileFragment.this.mRedesignEmptyStateFooterTitle.setVisibility(0);
                            ProfileFragment.this.mRedesignEmptyStateFooterSubtitle.setVisibility(0);
                            ProfileFragment.this.mFooter.setPadding(0, 0, 0, 0);
                            ProfileFragment.this.mListView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                };
            } else {
                this.mListView.getViewTreeObserver().removeOnGlobalLayoutListener(this.mDeferredEmptyStateResizeAction);
            }
            if (this.mListView.getHeight() == 0) {
                this.mListView.getViewTreeObserver().addOnGlobalLayoutListener(this.mDeferredEmptyStateResizeAction);
            } else {
                this.mDeferredEmptyStateResizeAction.onGlobalLayout();
            }
        }
    }

    private void updateRedesignEmptyState() {
        showRedesignEmptyStateFooter();
        if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            if (!this.mShouldSeeNotificationUiPref || this.mWishlistSectionState == WishlistSectionState.FOLLOWED) {
                this.mRedesignEmptyStateFooterButton.setVisibility(8);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NEW_USER_GIFT_PACK_PROFILE_WISHLIST);
            } else {
                this.mRedesignEmptyStateFooterButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wishlist_tutorial_300x156));
                PreferenceUtil.setBoolean("HideProfileWishlistTutorial", true);
                this.mRedesignEmptyStateFooterTitle.setText(R.string.profile_wishlist_empty_state_title_tutorial);
                this.mRedesignEmptyStateFooterSubtitle.setText(R.string.profile_wishlist_empty_state_subtitle_tutorial);
                this.mRedesignCreateWishlistButton.setVisibility(8);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NEW_USER_GIFT_PACK_PROFILE_WISHLIST_TUTORIAL);
            }
            if (this.mShouldSeeNotificationUiPref || this.mWishlistSectionState != WishlistSectionState.USER) {
                this.mRedesignCreateWishlistButton.setVisibility(8);
                this.mRedesignEmptyStateFooterTitle.setText(R.string.wishlist_followed_empty_title);
                this.mRedesignEmptyStateFooterSubtitle.setText(R.string.wishlist_followed_empty_subtitle);
                return;
            }
            this.mRedesignCreateWishlistButton.setVisibility(0);
            this.mRedesignEmptyStateFooterTitle.setText(R.string.profile_wishlist_empty_state_title);
            this.mRedesignEmptyStateFooterSubtitle.setText(R.string.profile_wishlist_empty_state_subtitle);
        } else if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
            this.mRedesignEmptyStateFooterButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.review_placeholder_56));
            this.mRedesignEmptyStateFooterButton.setVisibility(0);
            this.mRedesignEmptyStateFooterTitle.setText(R.string.profile_reviews_empty_state_title);
            this.mRedesignEmptyStateFooterSubtitle.setText(R.string.profile_reviews_empty_state_subtitle);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NEW_USER_GIFT_PACK_PROFILE_REVIEWS);
        } else if (this.mCurrentTabState == HeaderTabState.PHOTOS) {
            this.mRedesignEmptyStateFooterButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.photo_placeholder_56));
            this.mRedesignEmptyStateFooterButton.setVisibility(0);
            this.mRedesignEmptyStateFooterTitle.setText(R.string.profile_photos_empty_state_title);
            this.mRedesignEmptyStateFooterSubtitle.setText(R.string.profile_photos_empty_state_subtitle);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NEW_USER_GIFT_PACK_PROFILE_PHOTOS);
        } else {
            hideRedesignEmptyStateFooter();
        }
    }

    private void updateFooterLoadError() {
        this.mFooterMessageText.setVisibility(0);
        if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            if (!isCurrentUser()) {
                this.mFooterMessageText.setText(getString(R.string.wishlist_load_error));
            } else {
                this.mFooterMessageText.setText(getString(R.string.wishlist_load_error_other));
            }
        } else if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
            if (isCurrentUser()) {
                this.mFooterMessageText.setText(getString(R.string.review_load_error));
            } else {
                this.mFooterMessageText.setText(getString(R.string.review_load_error_other));
            }
        } else if (this.mCurrentTabState == HeaderTabState.PHOTOS) {
            if (isCurrentUser()) {
                this.mFooterMessageText.setText(getString(R.string.photos_load_error_own));
            } else {
                this.mFooterMessageText.setText(getString(R.string.photos_load_error_other));
            }
        }
        this.mFooterActionButton.setText(getString(R.string.click_to_retry));
        this.mFooterActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.reloadData();
            }
        });
        this.mFooterActionButton.setVisibility(0);
    }

    private void updateFooterNoItemsInWishList() {
        if (!isCurrentUser()) {
            this.mFooterMessageText.setVisibility(8);
            return;
        }
        this.mFooterMessageText.setVisibility(0);
        this.mFooterMessageText.setText(R.string.no_item_in_wishlist);
        this.mFooterActionButton.setText(R.string.continue_shopping);
        this.mFooterActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.handleContinueShopping();
            }
        });
        this.mFooterActionButton.setVisibility(0);
    }

    private void updateFooterWishlists() {
        if (this.mWishlistState == WishlistState.NO_RESULTS && this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            if (isCurrentUser()) {
                this.mFooterTitleText.setVisibility(0);
            }
            this.mFooterMessageText.setVisibility(0);
            if (!isCurrentUser()) {
                this.mFooterMessageText.setText(getString(R.string.no_wishlist_created_other));
            } else if (this.mWishlistSectionState == WishlistSectionState.USER) {
                this.mFooterMessageText.setText(R.string.no_wishlist_created);
            } else {
                this.mFooterMessageText.setText(R.string.wishlist_followed_empty_title);
                this.mFooterTitleText.setVisibility(8);
            }
        }
        Drawable drawable = WishApplication.getInstance().getResources().getDrawable(R.drawable.action_bar_add_primary);
        if (drawable != null) {
            int lineHeight = this.mFooterActionButton.getLineHeight();
            drawable.setBounds(0, 0, lineHeight, lineHeight);
            ImageSpan imageSpan = new ImageSpan(drawable);
            String string = getString(R.string.create_a_wishlist);
            StringBuilder sb = new StringBuilder();
            sb.append("  ");
            sb.append(string);
            SpannableString spannableString = new SpannableString(sb.toString());
            spannableString.setSpan(imageSpan, 0, 1, 0);
            this.mFooterActionButton.setText(spannableString);
        }
        this.mFooterActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileFragment.this.handleAddWishList();
            }
        });
        if (!isCurrentUser() || this.mWishlistSectionState == WishlistSectionState.FOLLOWED) {
            this.mFooterActionButton.setVisibility(8);
        } else {
            this.mFooterActionButton.setVisibility(0);
        }
    }

    private void updateFooterReviews() {
        if (this.mWishlistState == WishlistState.NO_RESULTS) {
            this.mFooterMessageText.setVisibility(0);
            this.mFooterMessageText.setText(getString(R.string.no_reviews_written));
        } else if (this.mWishlistState == WishlistState.ACTIVE) {
            this.mFooterMessageText.setVisibility(0);
            this.mFooterMessageText.setText(null);
        }
    }

    private void updateFooterPhotos() {
        if (this.mWishlistState == WishlistState.NO_RESULTS) {
            this.mFooterMessageText.setVisibility(0);
            this.mFooterMessageText.setText(getString(R.string.no_photos_available));
        } else if (this.mWishlistState == WishlistState.ACTIVE) {
            this.mFooterMessageText.setVisibility(0);
            this.mFooterMessageText.setText(getString(R.string.no_more_photos));
        }
    }

    public boolean isCurrentUser() {
        return this.mUserId != null && this.mUserId.equals(ProfileDataCenter.getInstance().getUserId());
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i == 2000) {
            handleAddWishList();
            return true;
        } else if (i == 2001) {
            handleFollowButtonClicked(true);
            return true;
        } else if (i != 2002) {
            return false;
        } else {
            handleFollowButtonClicked(false);
            return true;
        }
    }

    public void setWishlistPrivacy(final int i, final String str, final boolean z) {
        trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_POPUP_PRIVATE_WISHLIST);
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                profileServiceFragment.setWishlistPrivacy(i, str, z);
            }
        });
    }

    public void setWishlistPrivacySuccess(int i, boolean z) {
        this.mProfileWishlistAdapter.setWishlistPrivacySuccess(i, z);
    }

    public void renameWishlist(final WishWishlist wishWishlist) {
        trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_POPUP_RENAME_WISHLIST);
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                profileServiceFragment.renameWishlist(wishWishlist);
            }
        });
    }

    public void handleWishlistRenameSuccess(WishWishlist wishWishlist, String str) {
        wishWishlist.setName(str);
        this.mProfileWishlistAdapter.notifyDataSetChanged();
    }

    public void handleUnfollowWishlistSuccess(WishWishlist wishWishlist) {
        this.mProfileFollowedWishlistAdapter.removeWishlist(wishWishlist);
        if (this.mProfileFollowedWishlistAdapter.getCount() == 0) {
            this.mWishlistState = WishlistState.NO_RESULTS;
            refreshFooter();
        }
    }

    public void handleLoadingUserProductRatingsSuccess(ArrayList<WishRating> arrayList, int i, boolean z) {
        this.mRatings.addAll(arrayList);
        if (this.mRatings.size() == 0) {
            this.mWishlistState = WishlistState.NO_RESULTS;
        } else {
            this.mWishlistState = WishlistState.ACTIVE;
        }
        this.mOffset = i;
        this.mNoMoreItems = z;
        this.mProductRatingsAdapter.setProductRatings(this.mRatings);
        refreshFooter();
    }

    public void handleLoadingUserProductRatingsFailure() {
        this.mWishlistState = WishlistState.LOAD_ERROR;
        refreshFooter();
    }

    public void loadUserPhotosSuccess(ArrayList<WishImage> arrayList, int i, boolean z) {
        this.mPhotos.addAll(arrayList);
        if (this.mPhotos.size() == 0) {
            this.mWishlistState = WishlistState.NO_RESULTS;
        } else {
            this.mWishlistState = WishlistState.ACTIVE;
        }
        this.mOffset = i;
        this.mNoMoreItems = z;
        this.mProfilePhotosAdapter.setPhotos(this.mPhotos);
        refreshFooter();
    }

    public void loadUserPhotosFailure() {
        this.mWishlistState = WishlistState.LOAD_ERROR;
        refreshFooter();
    }

    public void openProductDetailsPage(final String str) {
        trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_PRODUCT_REVIEW);
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                profileServiceFragment.launchProductDetail(str);
            }
        });
    }

    private void resetTabState() {
        this.mWishlistState = WishlistState.NO_RESULTS;
        this.mWishlists = new ArrayList<>();
        this.mFollowedWishlists = new ArrayList<>();
        this.mRatings = new ArrayList<>();
        this.mPhotos = new ArrayList<>();
        this.mOffset = 0;
        this.mNoMoreItems = false;
        this.mWishlistSectionState = WishlistSectionState.USER;
    }

    public void tabSelected(HeaderTabState headerTabState) {
        this.mCurrentTabState = headerTabState;
        if (this.mCurrentTabState == HeaderTabState.WISHLISTS) {
            this.mHeaderView.updateWishlistSectionTab(false);
            handleMyWishlistClicked();
            trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_WISHLISTS_TAB);
            return;
        }
        resetTabState();
        if (this.mCurrentTabState == HeaderTabState.REVIEWS) {
            this.mListView.setAdapter(this.mProductRatingsAdapter);
            trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_REVIEWS_TAB);
        } else if (this.mCurrentTabState == HeaderTabState.PHOTOS) {
            this.mListView.setAdapter(this.mProfilePhotosAdapter);
            trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_PHOTOS_TAB);
        }
        loadTabData();
    }

    public void handleMyWishlistClicked() {
        resetTabState();
        this.mListView.setAdapter(this.mProfileWishlistAdapter);
        this.mWishlistSectionState = WishlistSectionState.USER;
        loadTabData();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_WISHLIST_TAB_MINE);
    }

    public void handleFollowedWishlistClicked() {
        resetTabState();
        this.mListView.setAdapter(this.mProfileFollowedWishlistAdapter);
        this.mWishlistSectionState = WishlistSectionState.FOLLOWED;
        loadTabData();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_WISHLIST_TAB_FOLLOWED);
    }

    /* access modifiers changed from: private */
    public void showWishStarDialog() {
        withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                profileServiceFragment.showWishStarDialog(ProfileFragment.this.mUser.getFirstName());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onVerticalScrollPositionUpdated(int i) {
        float f;
        ActionBarManager actionBarManager = getBaseActivity() == null ? null : ((ProfileActivity) getBaseActivity()).getActionBarManager();
        if (actionBarManager != null && actionBarManager.hasTransparentActionBar()) {
            int max = Math.max(0, i);
            if (max >= this.mActionBarHeight) {
                f = 1.0f;
            } else {
                f = ((float) max) / ((float) this.mActionBarHeight);
            }
            actionBarManager.interpolateBackground(f);
        }
    }

    public void popInBottomNavigation(final boolean z) {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.popInBottomNavigation(z);
            }
        });
    }

    public void popOutBottomNavigation(final boolean z) {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.popOutBottomNavigation(z);
            }
        });
    }
}
