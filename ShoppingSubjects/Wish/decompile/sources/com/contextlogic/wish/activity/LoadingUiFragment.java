package com.contextlogic.wish.activity;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;

public abstract class LoadingUiFragment<A extends BaseActivity> extends UiFragment<A> implements LoadingPageManager {
    private LoadingPageView mLoadingPageView;

    /* access modifiers changed from: protected */
    public final int getLayoutResourceId() {
        return R.layout.loading_ui_fragment;
    }

    public final void initialize() {
        this.mLoadingPageView = (LoadingPageView) findViewById(R.id.loading_ui_fragment_loading_view);
        this.mLoadingPageView.setLoadingPageManager(this);
    }

    public LoadingPageView getLoadingPageView() {
        return this.mLoadingPageView;
    }
}
