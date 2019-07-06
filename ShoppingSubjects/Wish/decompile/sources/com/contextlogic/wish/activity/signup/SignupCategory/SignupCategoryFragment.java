package com.contextlogic.wish.activity.signup.SignupCategory;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.signup.SignupCategory.SignupCategoryGridCellView.SignupCategoryCellFragment;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.activity.signup.redesign.SignupGetEmailView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishSignupCategory;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.grid.StaggeredGridView.VisibleItemTask;
import java.util.ArrayList;
import java.util.Iterator;

public class SignupCategoryFragment extends LoadingUiFragment<SignupCategoryActivity> implements SignupCategoryCellFragment {
    private ArrayList<WishSignupCategory> mCategories;
    /* access modifiers changed from: private */
    public boolean mDialogShown;
    /* access modifiers changed from: private */
    public SignupCategoryAdapter mGridAdapter;
    /* access modifiers changed from: private */
    public StaggeredGridView mGridView;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    private View mNextButton;
    /* access modifiers changed from: private */
    public View mNextButtonContainer;
    /* access modifiers changed from: private */
    public int mSelectedCategoryCount;
    /* access modifiers changed from: private */
    public SignupGetEmailView mSignupGetEmailView;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.signup_category_fragment;
    }

    public boolean onBackPressed() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void initializeLoadingContentView(View view) {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_CATEGORY_FEED);
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mGridView = (StaggeredGridView) findViewById(R.id.signup_category_fragment_gridview);
        this.mCategories = new ArrayList<>();
        withActivity(new ActivityTask<SignupCategoryActivity>() {
            public void performTask(SignupCategoryActivity signupCategoryActivity) {
                SignupCategoryFragment.this.mGridAdapter = new SignupCategoryAdapter(signupCategoryActivity, SignupCategoryFragment.this);
                SignupCategoryFragment.this.mGridAdapter.setImagePrefetcher(SignupCategoryFragment.this.mImagePrefetcher);
                SignupCategoryFragment.this.mGridView.setAdapter(SignupCategoryFragment.this.mGridAdapter);
                SignupCategoryFragment.this.mGridView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(int i, View view) {
                        SignupCategoryFragment.this.onSelectCell(i);
                    }
                });
                SignupCategoryFragment.this.mGridView.notifyDataSetChanged();
            }
        });
        this.mNextButtonContainer = findViewById(R.id.signup_category_fragment_next_button_container);
        this.mNextButtonContainer.setVisibility(8);
        this.mNextButton = findViewById(R.id.signup_category_fragment_next_button);
        this.mNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupCategoryFragment.this.handleNext();
            }
        });
        initializeValues();
    }

    private void showSuccessDialog() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIGNUP_CATEGORY_NEXT);
        withActivity(new ActivityTask<SignupCategoryActivity>() {
            public void performTask(SignupCategoryActivity signupCategoryActivity) {
                if (!signupCategoryActivity.showSuccessMessage() || SignupCategoryFragment.this.mDialogShown) {
                    SignupCategoryFragment.this.withUiFragment(new UiTask<SignupCategoryActivity, UiFragment>() {
                        public void performTask(SignupCategoryActivity signupCategoryActivity, UiFragment uiFragment) {
                            if (signupCategoryActivity.getLoginMode() == null || signupCategoryActivity.getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                                SignupCategoryFragment.this.startBrowseActivity();
                            } else {
                                SignupCategoryFragment.this.showGetEmailView();
                            }
                        }
                    });
                    return;
                }
                SignupCategoryFragment.this.mDialogShown = true;
                signupCategoryActivity.startDialog(MultiButtonDialogFragment.createMultiButtonOkDialog(SignupCategoryFragment.this.getString(R.string.thanks), SignupCategoryFragment.this.getString(R.string.personalize_success_message)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        SignupCategoryFragment.this.withUiFragment(new UiTask<SignupCategoryActivity, SignupCategoryFragment>() {
                            public void performTask(SignupCategoryActivity signupCategoryActivity, SignupCategoryFragment signupCategoryFragment) {
                                if (signupCategoryActivity.getLoginMode() == null || signupCategoryActivity.getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                                    SignupCategoryFragment.this.startBrowseActivity();
                                } else {
                                    SignupCategoryFragment.this.showGetEmailView();
                                }
                            }
                        });
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        SignupCategoryFragment.this.withUiFragment(new UiTask<SignupCategoryActivity, SignupCategoryFragment>() {
                            public void performTask(SignupCategoryActivity signupCategoryActivity, SignupCategoryFragment signupCategoryFragment) {
                                if (signupCategoryActivity.getLoginMode() == null || signupCategoryActivity.getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                                    SignupCategoryFragment.this.startBrowseActivity();
                                } else {
                                    SignupCategoryFragment.this.showGetEmailView();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mSelectedCategoryCount = getSavedInstanceState().getInt("ArgSelectedCategoriesCount");
            this.mDialogShown = getSavedInstanceState().getBoolean("ArgDialogShown");
            ArrayList parcelableList = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "StoredStateData", WishSignupCategory.class);
            this.mCategories.clear();
            if (parcelableList != null && parcelableList.size() > 0) {
                handleLoadingSuccess(parcelableList);
            }
            this.mGridView.notifyDataSetChanged();
            refreshWishStates();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putInt("ArgSelectedCategoriesCount", this.mSelectedCategoryCount);
            bundle.putBoolean("ArgDialogShown", this.mDialogShown);
            bundle.putString("StoredStateData", StateStoreCache.getInstance().storeParcelableList(this.mCategories));
        }
    }

    public void handleResume() {
        super.handleResume();
        if (this.mCategories == null || this.mCategories.size() == 0 || !getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
        getLoadingPageView().refreshViewState();
    }

    public void releaseImages() {
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        if (this.mGridView != null) {
            this.mGridView.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    /* access modifiers changed from: private */
    public void handleNext() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_CATEGORY_FEED_DONE);
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_SIGNUP_SUCCESS);
        showSuccessDialog();
    }

    public void startBrowseActivity() {
        withActivity(new ActivityTask<SignupCategoryActivity>() {
            public void performTask(final SignupCategoryActivity signupCategoryActivity) {
                SignupCategoryFragment.this.getHandler().post(new Runnable() {
                    public void run() {
                        signupCategoryActivity.startHomeActivity();
                    }
                });
            }
        });
    }

    public boolean isWishPending(final String str, SignupCategoryGridCellView signupCategoryGridCellView) {
        final boolean[] zArr = {true};
        withServiceFragment(new ServiceTask<BaseActivity, SignupCategoryServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryServiceFragment signupCategoryServiceFragment) {
                zArr[0] = signupCategoryServiceFragment.isWishPending(str);
            }
        });
        return zArr[0];
    }

    private void refreshWishStatesDelayed() {
        if (getView() != null) {
            getView().post(new Runnable() {
                public void run() {
                    SignupCategoryFragment.this.refreshWishStates();
                }
            });
        }
    }

    public void refreshWishStates() {
        if (getView() != null) {
            this.mGridView.withVisibleItems(new VisibleItemTask() {
                public void performTask(View view) {
                    ((SignupCategoryGridCellView) view).refreshWishState();
                }
            });
        }
        withServiceFragment(new ServiceTask<BaseActivity, SignupCategoryServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryServiceFragment signupCategoryServiceFragment) {
                if (SignupCategoryFragment.this.mSelectedCategoryCount > signupCategoryServiceFragment.getPendingUnwishRequestCount()) {
                    SignupCategoryFragment.this.mNextButtonContainer.setVisibility(0);
                } else {
                    SignupCategoryFragment.this.mNextButtonContainer.setVisibility(8);
                }
            }
        });
    }

    public void onSelectCell(final int i) {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_CATEGORY_FEED_ITEM);
        final WishSignupCategory wishSignupCategory = (WishSignupCategory) this.mCategories.get(i);
        withServiceFragment(new ServiceTask<BaseActivity, SignupCategoryServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryServiceFragment signupCategoryServiceFragment) {
                if (!signupCategoryServiceFragment.isWishPending(wishSignupCategory.getId())) {
                    if (wishSignupCategory.isAlreadyWishing()) {
                        signupCategoryServiceFragment.unwishForCategory(wishSignupCategory.getId(), i);
                    } else {
                        signupCategoryServiceFragment.wishForCategory(wishSignupCategory.getId(), i);
                    }
                    SignupCategoryFragment.this.refreshWishStates();
                }
            }
        });
    }

    private void loadFeed() {
        withServiceFragment(new ServiceTask<BaseActivity, SignupCategoryServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryServiceFragment signupCategoryServiceFragment) {
                signupCategoryServiceFragment.getSignupCategories();
            }
        });
    }

    public void handleLoadingSuccess(ArrayList<WishSignupCategory> arrayList) {
        getLoadingPageView().markLoadingComplete();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            WishSignupCategory wishSignupCategory = (WishSignupCategory) it.next();
            this.mCategories.add(wishSignupCategory);
            if (wishSignupCategory.isAlreadyWishing()) {
                this.mSelectedCategoryCount++;
            }
        }
        this.mGridView.scrollTo(0, 0);
        this.mGridView.notifyDataSetChanged();
        withActivity(new ActivityTask<SignupCategoryActivity>() {
            public void performTask(SignupCategoryActivity signupCategoryActivity) {
                FrameLayout frameLayout = new FrameLayout(signupCategoryActivity);
                frameLayout.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 80.0f, SignupCategoryFragment.this.getResources().getDisplayMetrics())));
                SignupCategoryFragment.this.mGridView.setFooterView(frameLayout);
            }
        });
        getLoadingPageView().refreshViewState();
    }

    public void handleLoadingFailure() {
        getLoadingPageView().markLoadingErrored();
        MultiButtonDialogFragment.createMultiButtonErrorDialog(getString(R.string.error_loading_categories));
        getLoadingPageView().refreshViewState();
    }

    public ArrayList<WishSignupCategory> getSignupCategories() {
        return this.mCategories;
    }

    public void handleReload() {
        this.mCategories.clear();
        this.mSelectedCategoryCount = 0;
        this.mGridView.notifyDataSetChanged();
        getLoadingPageView().clearError();
        loadFeed();
    }

    public boolean hasItems() {
        return this.mCategories != null && this.mCategories.size() > 0;
    }

    public void wishSuccess(int i) {
        ((WishSignupCategory) this.mCategories.get(i)).setAlreadyWishing(true);
        this.mSelectedCategoryCount++;
        refreshWishStatesDelayed();
    }

    public void unwishSuccess(int i) {
        ((WishSignupCategory) this.mCategories.get(i)).setAlreadyWishing(false);
        this.mSelectedCategoryCount--;
        refreshWishStatesDelayed();
    }

    public void wishOrUnwishFailure() {
        refreshWishStatesDelayed();
    }

    public void showGetEmailView() {
        ViewGroup viewGroup = (ViewGroup) this.mNextButtonContainer.getParent();
        viewGroup.removeAllViews();
        this.mSignupGetEmailView = new SignupGetEmailView(getBaseActivity());
        this.mSignupGetEmailView.setOnFinishClicked(new FooterManager() {
            public void handleFooterButtonClick() {
                SignupCategoryFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SignupCategoryServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupCategoryServiceFragment signupCategoryServiceFragment) {
                        String enteredEmail = SignupCategoryFragment.this.mSignupGetEmailView.getEnteredEmail();
                        if (!TextUtils.isEmpty(enteredEmail)) {
                            signupCategoryServiceFragment.updateEmail(enteredEmail);
                            return;
                        }
                        SignupCategoryFragment.this.showInvalidEmail(SignupCategoryFragment.this.getString(R.string.please_enter_a_valid_email_address));
                    }
                });
            }
        });
        viewGroup.addView(this.mSignupGetEmailView);
    }

    public void showInvalidEmail(String str) {
        if (this.mSignupGetEmailView != null) {
            this.mSignupGetEmailView.showInvalidEmail(str);
        }
    }
}
