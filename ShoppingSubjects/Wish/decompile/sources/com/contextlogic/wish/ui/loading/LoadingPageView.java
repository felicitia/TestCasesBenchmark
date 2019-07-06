package com.contextlogic.wish.ui.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.LoadingFooterView.TapToLoadStyle;
import com.contextlogic.wish.ui.loading.LoadingFooterView.VisibilityMode;
import com.contextlogic.wish.util.AnimationUtil;
import com.contextlogic.wish.util.NetworkUtil;
import java.util.HashMap;
import java.util.Map.Entry;

public class LoadingPageView extends FrameLayout {
    private View mContentItems;
    private FrameLayout mContentView;
    private ViewGroup mEffectiveContentView;
    private boolean mEffectiveContentViewAnimatedOnFirstLoad;
    private View mEmptyBrowseButton;
    private TextView mErrorMessage;
    private ErrorType mErrorType = ErrorType.DEFAULT_ERROR;
    private FrameLayout mErrorView;
    private boolean mForceTapToLoad;
    private boolean mHideEmptyState;
    private boolean mHideErrors;
    private LinearLayout mInternetConnectionErrorView;
    private boolean mLoadingComplete;
    private boolean mLoadingErrored;
    private LoadingFooterView mLoadingFooter;
    private FrameLayout mLoadingView;
    private LoadingPageManager mManager;
    private TextView mNoItemsCaption;
    private ImageView mNoItemsImageView;
    private TextView mNoItemsMessage;
    private View mNoItemsView;
    private boolean mNoMoreItems;
    private SwipeRefreshLayout mRefreshContentView;
    private boolean mShowNoMoreItemsFooter;
    private TextView mTryAgainButton;
    private View mTryAgainButtonNoInternetView;

    enum ErrorType {
        DEFAULT_ERROR,
        INTERNET_CONNECTION_ERROR
    }

    public interface LoadingPageManager {
        boolean canPullToRefresh();

        int getLoadingContentLayoutResourceId();

        void handleReload();

        boolean hasItems();

        void initializeLoadingContentView(View view);
    }

    public LoadingPageView(Context context) {
        super(context);
        init();
    }

    public LoadingPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LoadingPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private final void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.loading_page, this);
        this.mErrorView = (FrameLayout) inflate.findViewById(R.id.loading_page_error_view);
        this.mInternetConnectionErrorView = (LinearLayout) inflate.findViewById(R.id.no_internet_connection_error_view);
        this.mLoadingView = (FrameLayout) inflate.findViewById(R.id.loading_page_loading_view);
        this.mErrorView = (FrameLayout) inflate.findViewById(R.id.loading_page_error_view);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingView.findViewById(R.id.loading_page_primary_progress_bar);
            View findViewById2 = this.mLoadingView.findViewById(R.id.loading_page_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mContentView = (FrameLayout) inflate.findViewById(R.id.loading_page_content_view);
        this.mNoItemsView = inflate.findViewById(R.id.loading_page_no_items_view);
        this.mNoItemsImageView = (ImageView) inflate.findViewById(R.id.loading_page_no_items_image_view);
        this.mNoItemsCaption = (TextView) inflate.findViewById(R.id.loading_page_no_items_caption_text);
        this.mNoItemsMessage = (TextView) inflate.findViewById(R.id.loading_page_no_items_message_text);
        this.mEmptyBrowseButton = findViewById(R.id.loading_page_no_items_view_browse_button);
        this.mErrorMessage = (TextView) inflate.findViewById(R.id.loading_page_error_message_text);
        this.mRefreshContentView = (SwipeRefreshLayout) inflate.findViewById(R.id.loading_page_refresh_content_view);
        this.mRefreshContentView.setEnabled(false);
        this.mRefreshContentView.setColorSchemeResources(R.color.main_primary);
        this.mRefreshContentView.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                LoadingPageView.this.reload();
            }
        });
        this.mRefreshContentView.setVisibility(8);
        this.mTryAgainButtonNoInternetView = findViewById(R.id.no_internet_connection_try_again_button);
        this.mTryAgainButtonNoInternetView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_TRY_AGAIN_NEW_NO_INTERNET_VIEW);
                LoadingPageView.this.reload();
            }
        });
        this.mTryAgainButton = (TextView) inflate.findViewById(R.id.loading_page_try_again_button);
        this.mTryAgainButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoadingPageView.this.reload();
            }
        });
        refreshEffectiveContentView();
        refreshViewState();
    }

    public void setRefresherOffset(int i) {
        this.mRefreshContentView.setProgressViewOffset(false, 0, i);
    }

    public void setErrorOffset(int i) {
        setFrameLayoutOffset(this.mErrorView, i);
    }

    public void setLoadingOffset(int i) {
        setFrameLayoutOffset(this.mLoadingView, i);
    }

    private void setFrameLayoutOffset(FrameLayout frameLayout, int i) {
        LayoutParams layoutParams = (LayoutParams) frameLayout.getLayoutParams();
        layoutParams.topMargin = i;
        frameLayout.setLayoutParams(layoutParams);
    }

    public void setLoadingFooter(LoadingFooterView loadingFooterView) {
        this.mLoadingFooter = loadingFooterView;
        refreshViewState();
    }

    private void refreshEffectiveContentView() {
        if (this.mManager == null) {
            return;
        }
        if (this.mManager.canPullToRefresh()) {
            this.mEffectiveContentView = this.mRefreshContentView;
        } else {
            this.mEffectiveContentView = this.mContentView;
        }
    }

    public void setLoadingPageManager(LoadingPageManager loadingPageManager) {
        this.mManager = loadingPageManager;
        if (this.mManager != null) {
            refreshEffectiveContentView();
            this.mContentItems = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(this.mManager.getLoadingContentLayoutResourceId(), this.mEffectiveContentView, true);
            this.mManager.initializeLoadingContentView(this.mContentItems);
        }
        refreshViewState();
    }

    public void clearError() {
        this.mLoadingErrored = false;
        refreshViewState();
    }

    public void setHideErrors(boolean z) {
        this.mHideErrors = z;
    }

    public void setHideEmptyState(boolean z) {
        this.mHideEmptyState = z;
    }

    public void markLoadingComplete() {
        this.mLoadingComplete = true;
        this.mRefreshContentView.setRefreshing(false);
        refreshViewState();
    }

    public boolean isLoadingComplete() {
        return this.mLoadingComplete;
    }

    public void markNoMoreItems() {
        this.mNoMoreItems = true;
        refreshViewState();
    }

    public void resetNoMoreItems() {
        this.mNoMoreItems = false;
        refreshViewState();
    }

    public void markShowNoMoreItemsFooter() {
        this.mShowNoMoreItemsFooter = true;
        refreshViewState();
    }

    public boolean getNoMoreItems() {
        return this.mNoMoreItems;
    }

    public void markLoadingErrored() {
        if (NetworkUtil.isNetworkAvailable()) {
            markLoadingErrored(ErrorType.DEFAULT_ERROR);
        } else {
            markLoadingErrored(ErrorType.INTERNET_CONNECTION_ERROR);
        }
    }

    public void markLoadingErrored(ErrorType errorType) {
        this.mErrorType = errorType;
        this.mLoadingErrored = true;
        this.mRefreshContentView.setRefreshing(false);
        refreshViewState();
    }

    public boolean isLoadingErrored() {
        return this.mLoadingErrored;
    }

    public void setErrorMessage(String str) {
        setErrorMessage(str, true);
    }

    public void setErrorMessage(String str, boolean z) {
        this.mErrorMessage.setText(str);
        if (z) {
            this.mTryAgainButtonNoInternetView.setVisibility(0);
            this.mTryAgainButton.setVisibility(0);
            return;
        }
        this.mTryAgainButtonNoInternetView.setVisibility(8);
        this.mTryAgainButton.setVisibility(8);
    }

    public void setNoItemsMessage(String str) {
        this.mNoItemsMessage.setText(str);
        this.mNoItemsImageView.setVisibility(8);
        this.mNoItemsCaption.setVisibility(8);
    }

    public void setNoItemsCaption(String str, String str2) {
        this.mNoItemsView.setBackground(new ColorDrawable(WishApplication.getInstance().getResources().getColor(R.color.cool_gray1)));
        this.mNoItemsView.getBackground().setAlpha(8);
        this.mNoItemsCaption.setText(str);
        this.mNoItemsImageView.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.empty_notifications_48));
        if (str2 != null) {
            this.mNoItemsMessage.setText(str2);
            this.mNoItemsMessage.setVisibility(0);
            return;
        }
        this.mNoItemsMessage.setVisibility(8);
    }

    public void setEmptyBrowseButton(OnClickListener onClickListener) {
        this.mEmptyBrowseButton.setVisibility(0);
        this.mEmptyBrowseButton.setOnClickListener(onClickListener);
    }

    public void refreshViewState() {
        HashMap hashMap = new HashMap();
        hashMap.put(this.mLoadingView, Integer.valueOf(8));
        hashMap.put(this.mErrorView, Integer.valueOf(8));
        hashMap.put(this.mContentView, Integer.valueOf(8));
        hashMap.put(this.mRefreshContentView, Integer.valueOf(8));
        hashMap.put(this.mNoItemsView, Integer.valueOf(8));
        hashMap.put(this.mInternetConnectionErrorView, Integer.valueOf(8));
        if (this.mLoadingErrored) {
            if (this.mManager != null && this.mManager.hasItems()) {
                showRefreshContentChild();
                this.mRefreshContentView.setEnabled(true);
                hashMap.put(this.mEffectiveContentView, Integer.valueOf(0));
                if (this.mLoadingFooter != null) {
                    if (!this.mNoMoreItems) {
                        this.mLoadingFooter.setVisibilityMode(VisibilityMode.TAP_TO_LOAD);
                    } else if (this.mShowNoMoreItemsFooter) {
                        this.mLoadingFooter.setVisibilityMode(VisibilityMode.NO_MORE_ITEMS);
                    } else {
                        this.mLoadingFooter.setVisibilityMode(VisibilityMode.HIDDEN);
                    }
                }
            } else if (!this.mHideErrors) {
                if (this.mErrorType != ErrorType.INTERNET_CONNECTION_ERROR || !ExperimentDataCenter.getInstance().shouldSeeNewInternetErrorView()) {
                    hashMap.put(this.mErrorView, Integer.valueOf(0));
                } else {
                    hashMap.put(this.mInternetConnectionErrorView, Integer.valueOf(0));
                }
            }
        } else if (!this.mLoadingComplete) {
            if (this.mRefreshContentView.isRefreshing()) {
                hideRefreshContentChild();
                this.mRefreshContentView.setEnabled(true);
                hashMap.put(this.mRefreshContentView, Integer.valueOf(0));
            } else {
                if (this.mLoadingView.getTag() == null) {
                    this.mLoadingView.setVisibility(8);
                    this.mLoadingView.setTag(new Object());
                }
                hashMap.put(this.mLoadingView, Integer.valueOf(0));
                this.mLoadingView.requestLayout();
            }
            if (this.mLoadingFooter != null) {
                this.mLoadingFooter.setVisibilityMode(VisibilityMode.HIDDEN);
            }
        } else if (this.mManager != null && this.mManager.hasItems()) {
            showRefreshContentChild();
            this.mRefreshContentView.setEnabled(true);
            hashMap.put(this.mEffectiveContentView, Integer.valueOf(0));
            if (this.mLoadingFooter != null) {
                if (this.mNoMoreItems) {
                    if (this.mShowNoMoreItemsFooter) {
                        this.mLoadingFooter.setVisibilityMode(VisibilityMode.NO_MORE_ITEMS);
                    } else {
                        this.mLoadingFooter.setVisibilityMode(VisibilityMode.HIDDEN);
                    }
                } else if (this.mForceTapToLoad) {
                    this.mLoadingFooter.setVisibilityMode(VisibilityMode.TAP_TO_LOAD);
                } else {
                    this.mLoadingFooter.setVisibilityMode(VisibilityMode.LOADING);
                }
            }
        } else if (!this.mHideEmptyState) {
            hashMap.put(this.mNoItemsView, Integer.valueOf(0));
        }
        for (Entry entry : hashMap.entrySet()) {
            int i = ((Integer) entry.getValue()).intValue() == 8 ? 8 : ((Integer) entry.getValue()).intValue() == 0 ? 0 : 4;
            ((View) entry.getKey()).setVisibility(i);
        }
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && this.mLoadingComplete && !this.mEffectiveContentViewAnimatedOnFirstLoad) {
            this.mEffectiveContentViewAnimatedOnFirstLoad = true;
            AnimationUtil.animateViewFadeInLinear(this.mEffectiveContentView, new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }
            });
        }
    }

    private void hideRefreshContentChild() {
        this.mContentItems.setVisibility(8);
    }

    private void showRefreshContentChild() {
        this.mContentItems.setVisibility(0);
    }

    public final void prepareForReload() {
        this.mLoadingComplete = false;
        this.mLoadingErrored = false;
        this.mNoMoreItems = false;
        refreshViewState();
    }

    public final void reload() {
        prepareForReload();
        if (this.mManager != null) {
            this.mManager.handleReload();
        }
    }

    public boolean getForceTapToLoad() {
        return this.mForceTapToLoad;
    }

    public void setForceTapToLoad(boolean z) {
        this.mForceTapToLoad = z;
    }

    public void setFooterTapToLoadStyle(TapToLoadStyle tapToLoadStyle) {
        if (this.mLoadingFooter != null) {
            this.mLoadingFooter.setTapToLoadStyle(tapToLoadStyle);
        }
    }

    public void setFooterTapToLoadText(String str) {
        if (this.mLoadingFooter != null) {
            this.mLoadingFooter.setTapToLoadText(str);
        }
    }
}
