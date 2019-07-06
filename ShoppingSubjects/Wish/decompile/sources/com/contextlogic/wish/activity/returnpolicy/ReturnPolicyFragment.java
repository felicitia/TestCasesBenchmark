package com.contextlogic.wish.activity.returnpolicy;

import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.api.model.WishReturnPolicyInfo;
import com.contextlogic.wish.api.model.WishRewardsReturnPolicyInformation;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class ReturnPolicyFragment extends UiFragment<ReturnPolicyActivity> implements LoadingPageManager {
    private LinearLayout mContainer;
    private LinearLayout mContentContainer;
    private LoadingPageView mLoadingPageView;
    private ThemedTextView mTitle1TextView;
    private ThemedTextView mTitle2TextView;

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.return_policy_fragment;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.return_policy_content_container;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mLoadingPageView = (LoadingPageView) findViewById(R.id.return_policy_loading_page_view);
        withActivity(new ActivityTask<ReturnPolicyActivity>() {
            public void performTask(ReturnPolicyActivity returnPolicyActivity) {
                returnPolicyActivity.getActionBarManager().setTitle(WishApplication.getInstance().getResources().getString(R.string.return_policy));
                returnPolicyActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.X_ICON);
            }
        });
        this.mLoadingPageView.setLoadingPageManager(this);
        withServiceFragment(new ServiceTask<BaseActivity, ReturnPolicyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ReturnPolicyServiceFragment returnPolicyServiceFragment) {
                returnPolicyServiceFragment.getReturnPolicyInfoService();
            }
        });
    }

    public void handleLoadingReturnPolicyInfoFailure() {
        this.mLoadingPageView.markLoadingErrored();
    }

    public void handleLoadingReturnPolicyInfoSuccess(WishReturnPolicyInfo wishReturnPolicyInfo) {
        this.mLoadingPageView.markLoadingComplete();
        this.mTitle1TextView.setText(wishReturnPolicyInfo.getTitle1());
        this.mTitle2TextView.setText(wishReturnPolicyInfo.getTitle2());
        Iterator it = wishReturnPolicyInfo.getInformationRows().iterator();
        while (it.hasNext()) {
            WishRewardsReturnPolicyInformation wishRewardsReturnPolicyInformation = (WishRewardsReturnPolicyInformation) it.next();
            ReturnPolicyInformationView returnPolicyInformationView = new ReturnPolicyInformationView(getContext());
            returnPolicyInformationView.setup(wishRewardsReturnPolicyInformation);
            this.mContainer.addView(returnPolicyInformationView);
        }
    }

    public void handleReload() {
        withServiceFragment(new ServiceTask<BaseActivity, ReturnPolicyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ReturnPolicyServiceFragment returnPolicyServiceFragment) {
                returnPolicyServiceFragment.getReturnPolicyInfoService();
            }
        });
    }

    public void initializeLoadingContentView(View view) {
        this.mContentContainer = (LinearLayout) view.findViewById(R.id.return_policy_content_container);
        this.mTitle1TextView = (ThemedTextView) this.mContentContainer.findViewById(R.id.return_policy_title_1);
        this.mTitle2TextView = (ThemedTextView) this.mContentContainer.findViewById(R.id.return_policy_title_2);
        this.mContainer = (LinearLayout) this.mContentContainer.findViewById(R.id.return_policy_container);
    }

    public boolean hasItems() {
        return this.mLoadingPageView.isLoadingComplete();
    }
}
