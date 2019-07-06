package com.contextlogic.wish.activity.merchantprofile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment.BooleanWrapper;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishUser.WishUserState;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.LoadingFooterViewCallback;
import java.util.ArrayList;
import java.util.Iterator;

public class MerchantProfileRatingsView extends MerchantProfilePagerView {
    private boolean mHasHeader = false;
    private ImageHttpPrefetcher mImagePrefetcher;
    private MerchantProfileRatingsAdapter mListAdapter;
    /* access modifiers changed from: private */
    public ListeningListView mListView;
    /* access modifiers changed from: private */
    public LoadingFooterView mLoadingFooter;
    /* access modifiers changed from: private */
    public String mMerchantId;
    /* access modifiers changed from: private */
    public int mNextOffset;
    private ArrayList<WishRating> mRatings;
    private boolean mUpdatingList;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.merchant_profile_fragment_ratings;
    }

    public void refreshWishStates(boolean z) {
    }

    public MerchantProfileRatingsView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mRatings = new ArrayList<>();
    }

    public boolean hasItems() {
        return this.mRatings.size() > 0;
    }

    public void setup(String str, int i, MerchantProfileFragment merchantProfileFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        super.setup(i, merchantProfileFragment);
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mMerchantId = str;
        setNoItemsMessage(WishApplication.getInstance().getString(R.string.no_reviews_found));
        this.mListView = (ListeningListView) this.mRootLayout.findViewById(R.id.merchant_profile_fragment_ratings_listview);
        this.mListView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                MerchantProfileRatingsView.this.handleScrollChanged(i, i2);
            }
        });
        setupScroller(this.mListView);
        this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                MerchantProfileRatingsView.this.mLoadingFooter = new LoadingFooterView(merchantProfileActivity);
                MerchantProfileRatingsView.this.mLoadingFooter.setCallback(new LoadingFooterViewCallback() {
                    public void onTapToLoad() {
                        MerchantProfileRatingsView.this.loadNextPage();
                    }
                });
                MerchantProfileRatingsView.this.mListView.addFooterView(MerchantProfileRatingsView.this.mLoadingFooter);
                MerchantProfileRatingsView.this.setLoadingFooter(MerchantProfileRatingsView.this.mLoadingFooter);
            }
        });
        this.mListView.setFadingEdgeLength(0);
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                MerchantProfileRatingsView.this.handleScrollLoad(i, i2, i3);
            }
        });
        MerchantProfileRatingsAdapter merchantProfileRatingsAdapter = new MerchantProfileRatingsAdapter(this.mFragment.getActivity(), this.mFragment, this.mRatings, this.mListView, this.mImagePrefetcher, this);
        this.mListAdapter = merchantProfileRatingsAdapter;
        this.mListView.setAdapter(this.mListAdapter);
        this.mListAdapter.notifyDataSetChanged();
        loadNextPage();
        if (this.mFragment.getSavedInstanceState(i) != null) {
            restorePosition(this.mFragment.getSavedInstanceState(i).getInt("SavedStateFirstItemPosition"));
        }
    }

    public void cleanup() {
        releaseImages();
        cancelNetworkRequest();
    }

    public void releaseImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.restoreImages();
        }
    }

    public int getCurrentScrollY() {
        if (this.mListView != null) {
            return this.mListView.getCurrentScrollY();
        }
        return 0;
    }

    public void onPagerScrollSettled() {
        super.onPagerScrollSettled();
        if (this.mFragment.getCurrentIndex() == this.mIndex) {
            refreshRowTimestamps();
        }
    }

    private void refreshRowTimestamps() {
        if (this.mListAdapter != null) {
            this.mListAdapter.refreshTimestamps();
        }
    }

    private void setupHeader(int i) {
        LinearLayout linearLayout = new LinearLayout(this.mFragment.getBaseActivity());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        layoutParams.setMargins(dimensionPixelSize, this.mFragment.getTabAreaSize() + dimensionPixelSize + i, 0, 0);
        TextView textView = new TextView(this.mFragment.getBaseActivity());
        textView.setText(getResources().getQuantityString(R.plurals.user_review, this.mFragment.getWishMerchant().getRatingCount(), new Object[]{Integer.valueOf(this.mFragment.getWishMerchant().getRatingCount())}));
        textView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_subtitle));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(getResources().getColor(R.color.text_primary));
        linearLayout.addView(textView, layoutParams);
        this.mListView.addHeaderView(linearLayout);
    }

    private void setupHeader() {
        setupHeader(0);
    }

    /* access modifiers changed from: private */
    public void handleScrollLoad(int i, int i2, int i3) {
        if ((!isLoadingErrored() && !getNoMoreItems() && !isNetworkRequestPending() && !this.mUpdatingList && isLoadingComplete()) && i > i3 - (i2 * 2)) {
            loadNextPage();
        }
    }

    public void handleItemClick(WishRating wishRating) {
        if (wishRating.getAuthor().getUserState() == WishUserState.Registered) {
            final String userId = wishRating.getAuthor().getUserId();
            this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
                public void performTask(MerchantProfileActivity merchantProfileActivity) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATING_AUTHOR_PHOTO_MERCHANT_PROFILE_REVIEWS);
                    Intent intent = new Intent();
                    intent.setClass(merchantProfileActivity, ProfileActivity.class);
                    intent.putExtra(ProfileActivity.EXTRA_USER_ID, userId);
                    merchantProfileActivity.startActivity(intent);
                }
            });
        }
    }

    private boolean isNetworkRequestPending() {
        final BooleanWrapper booleanWrapper = new BooleanWrapper(true);
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, MerchantProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, MerchantProfileServiceFragment merchantProfileServiceFragment) {
                booleanWrapper.state = merchantProfileServiceFragment.isMerchantRatingsPending();
            }
        });
        return booleanWrapper.state;
    }

    private void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, MerchantProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, MerchantProfileServiceFragment merchantProfileServiceFragment) {
                merchantProfileServiceFragment.cancelLoadingMerchantRatings();
            }
        });
    }

    private void performNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, MerchantProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, MerchantProfileServiceFragment merchantProfileServiceFragment) {
                merchantProfileServiceFragment.loadMerchantRatings(MerchantProfileRatingsView.this.mMerchantId, MerchantProfileRatingsView.this.mNextOffset, 25);
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadNextPage() {
        if (!getNoMoreItems()) {
            performNetworkRequest();
            refreshViewState();
        }
    }

    public void onSuccess(final ArrayList<WishRating> arrayList, final boolean z, final int i) {
        this.mUpdatingList = true;
        queuePagerSettledTask(new Runnable() {
            public void run() {
                MerchantProfileRatingsView.this.handleLoadingSuccess(arrayList, z, i);
            }
        });
    }

    public void onFailure() {
        this.mUpdatingList = true;
        queuePagerSettledTask(new Runnable() {
            public void run() {
                MerchantProfileRatingsView.this.handleLoadingFailure();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLoadingSuccess(ArrayList<WishRating> arrayList, boolean z, int i) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mRatings.add((WishRating) it.next());
        }
        clearError();
        if (z) {
            markNoMoreItems();
        }
        if (!this.mHasHeader) {
            setupHeader();
            this.mHasHeader = true;
        }
        this.mNextOffset = i;
        if (!getNoMoreItems() && this.mRatings.size() < 10) {
            loadNextPage();
        }
        this.mListAdapter.notifyDataSetChanged();
        this.mUpdatingList = false;
        this.mFragment.scrollOffset();
        markLoadingComplete();
    }

    /* access modifiers changed from: private */
    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                merchantProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(merchantProfileActivity.getString(R.string.ratings_error_message)));
            }
        });
    }

    public int getFirstItemPosition() {
        return this.mListView.getFirstVisiblePosition();
    }

    public void restorePosition(final int i) {
        this.mListView.post(new Runnable() {
            public void run() {
                MerchantProfileRatingsView.this.mListView.setSelection(i);
            }
        });
    }

    public void scrollOffset(int i) {
        this.mListView.smoothScrollBy(-i, 1);
    }
}
