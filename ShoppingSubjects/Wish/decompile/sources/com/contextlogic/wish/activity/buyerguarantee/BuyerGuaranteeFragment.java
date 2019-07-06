package com.contextlogic.wish.activity.buyerguarantee;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class BuyerGuaranteeFragment extends UiFragment<BuyerGuaranteeActivity> implements LoadingPageManager {
    private BuyerGuaranteeListViewAdapter mAdapter;
    private LinearLayout mContentContainer;
    private ListView mListView;
    private LoadingPageView mLoadingPageView;
    private ThemedTextView mSubTitle;
    private ThemedTextView mTitle;

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.buyer_guarantee_fragment;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.buyer_guarantee_content_view;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mLoadingPageView = (LoadingPageView) findViewById(R.id.buyer_guarantee_loading_page_view);
        this.mLoadingPageView.setLoadingPageManager(this);
        withServiceFragment(new ServiceTask<BaseActivity, BuyerGuaranteeServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BuyerGuaranteeServiceFragment buyerGuaranteeServiceFragment) {
                buyerGuaranteeServiceFragment.getBuyerGuaranteeInfoService();
            }
        });
        setupBaseActionBar();
        withActivity(new ActivityTask<BuyerGuaranteeActivity>() {
            public void performTask(BuyerGuaranteeActivity buyerGuaranteeActivity) {
                if (buyerGuaranteeActivity.getActionBarManager() != null) {
                    buyerGuaranteeActivity.getActionBarManager().setTitle(buyerGuaranteeActivity.getString(R.string.collapsable_section_buyer_guarantee));
                    buyerGuaranteeActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.X_ICON);
                }
            }
        });
    }

    public void handleLoadingBuyerGuaranteeInfoSuccess(BuyerGuaranteeInfo buyerGuaranteeInfo) {
        if (buyerGuaranteeInfo == null) {
            this.mLoadingPageView.markLoadingErrored();
            return;
        }
        this.mTitle.setText(buyerGuaranteeInfo.getPageTitle());
        this.mSubTitle.setText(buyerGuaranteeInfo.getPageSubtitle());
        this.mAdapter = new BuyerGuaranteeListViewAdapter(getContext(), this, this.mListView, buyerGuaranteeInfo.getPageItems());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setDivider(new ColorDrawable(WishApplication.getInstance().getResources().getColor(R.color.transparent)));
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.fourty_padding));
        this.mLoadingPageView.markLoadingComplete();
    }

    public void handleLoadingReturnPolicyInfoFailure() {
        this.mLoadingPageView.markLoadingErrored();
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

    public void handleReload() {
        withServiceFragment(new ServiceTask<BaseActivity, BuyerGuaranteeServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BuyerGuaranteeServiceFragment buyerGuaranteeServiceFragment) {
                buyerGuaranteeServiceFragment.getBuyerGuaranteeInfoService();
            }
        });
    }

    public void initializeLoadingContentView(View view) {
        this.mContentContainer = (LinearLayout) view.findViewById(R.id.buyer_guarantee_content_container);
        this.mTitle = (ThemedTextView) this.mContentContainer.findViewById(R.id.buyer_guarantee_title_text);
        this.mSubTitle = (ThemedTextView) this.mContentContainer.findViewById(R.id.buyer_guarantee_subtitle_text);
        this.mListView = (ListView) this.mContentContainer.findViewById(R.id.buyer_guarantee_row_list);
    }

    public boolean hasItems() {
        return this.mLoadingPageView.isLoadingComplete();
    }
}
