package com.contextlogic.wish.activity.signup.redesign;

import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment.SignupPage;
import com.contextlogic.wish.api.model.SignupFlowPageInfo;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import java.util.ArrayList;

public abstract class SignupFlowBaseView extends LoadingPageView implements ImageRestorable, LoadingPageManager {
    protected FullScreenActivity mBaseActivity;
    protected SignupFlowFragment mFragment;
    private ArrayList<SignupFlowPageInfo> mPageInfos;

    public boolean canPullToRefresh() {
        return false;
    }

    public abstract int getLoadingContentLayoutResourceId();

    public void handleReload() {
    }

    public void handleSaveInstanceState(Bundle bundle) {
    }

    public boolean hasItems() {
        return true;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public SignupFlowBaseView(FullScreenActivity fullScreenActivity, SignupFlowFragment signupFlowFragment) {
        super(fullScreenActivity);
        this.mBaseActivity = fullScreenActivity;
        this.mFragment = signupFlowFragment;
        setLoadingPageManager(this);
    }

    /* access modifiers changed from: protected */
    public void setupFooter(SignupPage signupPage, SignupFlowFooterView signupFlowFooterView) {
        SignupFlowPageInfo signupFlowPageInfo;
        if (this.mPageInfos == null && ((SignupFlowActivity) this.mFragment.getBaseActivity()).getSignupFlowContext() != null) {
            this.mPageInfos = ((SignupFlowActivity) this.mFragment.getBaseActivity()).getSignupFlowContext().signupFlowPageInfos;
        }
        if (this.mPageInfos != null && this.mPageInfos.size() > 0) {
            if (signupPage == SignupPage.UploadProfilePhoto) {
                signupFlowPageInfo = (SignupFlowPageInfo) this.mPageInfos.get(SignupPage.UploadProfilePhoto.ordinal());
            } else if (signupPage == SignupPage.SelectGender) {
                signupFlowPageInfo = (SignupFlowPageInfo) this.mPageInfos.get(SignupPage.SelectGender.ordinal());
            } else if (signupPage == SignupPage.RankFilter) {
                signupFlowPageInfo = (SignupFlowPageInfo) this.mPageInfos.get(SignupPage.RankFilter.ordinal());
            } else if (signupPage == SignupPage.SelectCategory) {
                signupFlowPageInfo = (SignupFlowPageInfo) this.mPageInfos.get(SignupPage.SelectCategory.ordinal());
            } else if (signupPage == SignupPage.BirthdayPicker) {
                signupFlowPageInfo = (SignupFlowPageInfo) this.mPageInfos.get(SignupPage.BirthdayPicker.ordinal());
            } else {
                return;
            }
            signupFlowFooterView.setup(signupFlowPageInfo);
        }
    }
}
