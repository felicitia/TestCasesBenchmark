package com.contextlogic.wish.activity.signup.redesign.SelectCategory;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowBaseView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment.SignupPage;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.ui.listview.HorizontalListGridView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class SelectCategoryView extends SignupFlowBaseView implements FooterManager {
    private SelectCategoryAdapter mAdapter;
    private SignupFlowFooterView mFooterView;
    private String mGender;
    private HorizontalListGridView mGridView;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.redesign_signup_select_category;
    }

    public SelectCategoryView(FullScreenActivity fullScreenActivity, SignupFlowFragment signupFlowFragment) {
        super(fullScreenActivity, signupFlowFragment);
    }

    public void initializeLoadingContentView(View view) {
        this.mFooterView = (SignupFlowFooterView) findViewById(R.id.signup_footer_view);
        setupFooter(SignupPage.SelectCategory, this.mFooterView);
        this.mFooterView.setFooterManager(this);
        this.mGridView = (HorizontalListGridView) findViewById(R.id.select_category_grid);
        this.mAdapter = new SelectCategoryAdapter(this.mBaseActivity, this, 3);
        resetGridViewPosition();
        handleOnPageSelected();
        this.mFragment.withServiceFragment(new ServiceTask<SignupFlowActivity, SignupFlowServiceFragment>() {
            public void performTask(SignupFlowActivity signupFlowActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                String str = "neutral";
                if (signupFlowActivity.getSignupFlowContext() != null) {
                    str = signupFlowActivity.getSignupFlowContext().genderInferred;
                }
                signupFlowServiceFragment.getCategories(str);
            }
        });
        this.mGridView.setTranslationX(0.0f);
        Bundle savedInstanceState = this.mFragment.getSavedInstanceState(SignupPage.SelectCategory.ordinal());
        if (savedInstanceState != null) {
            this.mAdapter.setSelectedCategoryIndex(savedInstanceState.getIntegerArrayList("SelectedCategory"));
        }
        markLoadingComplete();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_INTERESTS_IMPRESSION);
    }

    public void resetGridViewPosition() {
        this.mGridView.scrollTo(0, 0);
        this.mGridView.setTranslationX((float) (DisplayUtil.getDisplayWidth() / 2));
    }

    public void handleFooterButtonClick() {
        this.mFragment.uploadSelectedSigupFlowCategories(this.mAdapter.getSelectedCategories());
        if (!this.mAdapter.getSelectedCategories().isEmpty()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_SELECT_CATEGORY);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_SKIP_SELECT_CATEGORY);
        }
    }

    public void handleCategoriesLoadSuccess(ArrayList<WishSignupFlowCategory> arrayList) {
        this.mAdapter.setCategories(arrayList);
        this.mGridView.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mGridView.notifyDataSetChanged();
        boolean z = false;
        int itemWidth = ((this.mAdapter.getItemWidth(0) * this.mAdapter.getCount()) / this.mAdapter.getRowCount()) + (this.mAdapter.getHorizontalMargin() * ((this.mAdapter.getCount() / this.mAdapter.getRowCount()) + 1));
        if (DisplayUtil.getDisplayWidth() > itemWidth) {
            z = true;
        }
        if (z) {
            this.mGridView.setLayoutParams(new LayoutParams(itemWidth, getContext().getResources().getDimensionPixelSize(R.dimen.signup_flow_select_category_height)));
            return;
        }
        this.mGridView.setLayoutParams(new LayoutParams(-1, getContext().getResources().getDimensionPixelSize(R.dimen.signup_flow_select_category_height)));
    }

    public void handleOnPageSelected() {
        this.mBaseActivity.getSupportActionBar().show();
    }

    public void releaseImages() {
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mGridView != null) {
            this.mGridView.restoreImages();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putIntegerArrayList("SelectedCategory", this.mAdapter.getSelectedCategoryIndex());
        bundle2.putString("gender", this.mGender);
        bundle.putBundle(this.mFragment.getPagerKey(SignupPage.SelectCategory.ordinal()), bundle2);
    }
}
