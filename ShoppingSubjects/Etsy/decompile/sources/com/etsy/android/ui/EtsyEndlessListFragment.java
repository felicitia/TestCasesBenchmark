package com.etsy.android.ui;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.etsy.android.R;
import com.etsy.android.uikit.e;
import com.etsy.android.uikit.listwrapper.ListViewEndlessWrapper;
import com.etsy.android.uikit.listwrapper.a.C0109a;

@Deprecated
public abstract class EtsyEndlessListFragment extends EtsyCommonListFragment implements e, C0109a {
    protected ListViewEndlessWrapper mEndlessWrapper;
    protected boolean mIsEndlessRunning;
    protected int mOffset;

    public EtsyEndlessListFragment(int i) {
        super(i);
    }

    public EtsyEndlessListFragment() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mEndlessWrapper = new ListViewEndlessWrapper(this.mListView, R.layout.endless_footer, R.layout.endless_error, R.id.btn_retry_endless);
        this.mEndlessWrapper.setLoadMoreListener(this);
    }

    public void onStart() {
        super.onStart();
        if (this.mListView != null) {
            if (this.mIsEndlessRunning) {
                this.mEndlessWrapper.startEndless();
            }
            this.mEndlessWrapper.setLoadMoreListener(this);
        }
    }

    public void onStop() {
        super.onStop();
        this.mIsEndlessRunning = this.mEndlessWrapper.isEndlessLoading();
    }

    public void setListAdapter(ListAdapter listAdapter) {
        if (this.mEndlessWrapper != null) {
            this.mEndlessWrapper.setAdapter(listAdapter);
        } else {
            super.setListAdapter(listAdapter);
        }
    }

    public int getApiOffset() {
        return this.mOffset;
    }

    public void setApiOffset(int i) {
        this.mOffset = i;
    }

    public void startEndless() {
        this.mEndlessWrapper.startEndless();
    }

    public void stopEndless() {
        this.mEndlessWrapper.stopEndless();
    }

    public void showEndlessError() {
        this.mEndlessWrapper.showEndlessError();
    }

    public void removeEndlessError() {
        this.mEndlessWrapper.removeEndlessError();
    }

    public void showLoadingView() {
        super.showLoadingView();
        this.mEndlessWrapper.stopEndless();
    }

    public void showEmptyView() {
        super.showEmptyView();
        this.mEndlessWrapper.stopEndless();
    }

    public void showErrorView() {
        super.showErrorView();
        this.mEndlessWrapper.stopEndless();
    }
}
