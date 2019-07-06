package com.contextlogic.wish.activity.merchantprofile;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.service.standalone.GetMerchantTopCategoriesService.FeedContext;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.LoadingFooterViewCallback;
import java.util.ArrayList;

public class MerchantProfileCategoriesView extends MerchantProfilePagerView {
    private boolean mHasHeader;
    private ImageHttpPrefetcher mImagePrefetcher;
    protected MerchantProfileCategoriesAdapter mListAdapter;
    protected ListeningListView mListView;
    protected LoadingFooterView mLoadingFooter;
    private String mMerchantId;
    protected ArrayList<WishMerchantTopCategory> mTopCategories;
    protected boolean mUpdatingList;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.merchant_profile_fragment_categories;
    }

    public void refreshWishStates(boolean z) {
    }

    public MerchantProfileCategoriesView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mTopCategories = new ArrayList<>();
    }

    public int getCurrentScrollY() {
        if (this.mListView != null) {
            return this.mListView.getCurrentScrollY();
        }
        return 0;
    }

    public boolean hasItems() {
        return this.mTopCategories.size() > 0;
    }

    public void setup(String str, int i, MerchantProfileFragment merchantProfileFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        super.setup(i, merchantProfileFragment);
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mMerchantId = str;
        setNoItemsMessage(WishApplication.getInstance().getString(R.string.no_top_categories_found));
        this.mListView = (ListeningListView) this.mRootLayout.findViewById(R.id.merchant_profile_fragment_categories_listview);
        this.mListView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                MerchantProfileCategoriesView.this.handleScrollChanged(i, i2);
            }
        });
        setupScroller(this.mListView);
        this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                MerchantProfileCategoriesView.this.mLoadingFooter = new LoadingFooterView(merchantProfileActivity);
                MerchantProfileCategoriesView.this.mLoadingFooter.setCallback(new LoadingFooterViewCallback() {
                    public void onTapToLoad() {
                        MerchantProfileCategoriesView.this.loadNextPage();
                    }
                });
                MerchantProfileCategoriesView.this.mListView.addFooterView(MerchantProfileCategoriesView.this.mLoadingFooter);
                MerchantProfileCategoriesView.this.setLoadingFooter(MerchantProfileCategoriesView.this.mLoadingFooter);
            }
        });
        this.mListView.setFadingEdgeLength(0);
        MerchantProfileCategoriesAdapter merchantProfileCategoriesAdapter = new MerchantProfileCategoriesAdapter(this.mFragment.getActivity(), this.mFragment, this.mListView, this.mTopCategories, this.mImagePrefetcher);
        this.mListAdapter = merchantProfileCategoriesAdapter;
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

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, MerchantProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, MerchantProfileServiceFragment merchantProfileServiceFragment) {
                merchantProfileServiceFragment.cancelLoadingMerchantTopCategories();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void performNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, MerchantProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, MerchantProfileServiceFragment merchantProfileServiceFragment) {
                FeedContext feedContext = new FeedContext();
                feedContext.merchantId = ((MerchantProfileActivity) MerchantProfileCategoriesView.this.mFragment.getBaseActivity()).getMerchantId();
                merchantProfileServiceFragment.loadMerchantTopCategories(feedContext);
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

    public void onSuccess(final ArrayList<WishMerchantTopCategory> arrayList, final String str) {
        this.mUpdatingList = true;
        queuePagerSettledTask(new Runnable() {
            public void run() {
                MerchantProfileCategoriesView.this.handleLoadingSuccess(arrayList, str);
            }
        });
    }

    public void onFailure() {
        this.mUpdatingList = true;
        queuePagerSettledTask(new Runnable() {
            public void run() {
                MerchantProfileCategoriesView.this.handleLoadingFailure();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleLoadingSuccess(ArrayList<WishMerchantTopCategory> arrayList, String str) {
        this.mListAdapter.setRequestId(str);
        this.mTopCategories.addAll(arrayList);
        markNoMoreItems();
        if (!this.mHasHeader) {
            setupHeader();
            this.mHasHeader = true;
        }
        this.mListAdapter.notifyDataSetChanged();
        this.mUpdatingList = false;
        this.mFragment.scrollOffset();
        markLoadingComplete();
    }

    /* access modifiers changed from: protected */
    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mFragment.withActivity(new ActivityTask<MerchantProfileActivity>() {
            public void performTask(MerchantProfileActivity merchantProfileActivity) {
                merchantProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(merchantProfileActivity.getString(R.string.merchant_categories_error_message)));
            }
        });
    }

    private void setupHeader() {
        View view = new View(getContext());
        view.setLayoutParams(new LayoutParams(0, this.mFragment.getTabAreaSize()));
        this.mListView.addHeaderView(view);
    }

    public int getFirstItemPosition() {
        return this.mListView.getFirstVisiblePosition();
    }

    public void restorePosition(final int i) {
        this.mListView.post(new Runnable() {
            public void run() {
                MerchantProfileCategoriesView.this.mListView.setSelection(i);
            }
        });
    }

    public void scrollOffset(int i) {
        this.mListView.smoothScrollBy(-i, 1);
    }
}
