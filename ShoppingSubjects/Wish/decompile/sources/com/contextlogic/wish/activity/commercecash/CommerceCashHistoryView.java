package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.loading.LoadingFooterView;

public class CommerceCashHistoryView extends CommerceCashPagerView {
    private CommerceCashEventsAdapter mAdapter;
    private LinearLayout mContainer;
    private AutoReleasableImageView mEmptyHistoryIcon;
    private View mEmptyHistoryText;
    private CommerceCashFragment mFragment;
    private WishCommerceCashHistory mHistory;
    private ListView mHistoryOptions;
    private boolean mLoadedAllEvents = false;
    private LoadingFooterView mLoadingFooterView;
    private int mOffset = 0;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.commerce_cash_fragment_history;
    }

    public void scrollPage(int i) {
    }

    public CommerceCashHistoryView(Context context) {
        super(context);
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void handleReload() {
        super.handleReload();
        this.mHistory = null;
        if (this.mAdapter != null) {
            this.mAdapter.clearEvents();
        }
        this.mFragment.loadPages();
    }

    public void setup(int i, CommerceCashFragment commerceCashFragment) {
        super.setup(i, commerceCashFragment);
        this.mFragment = commerceCashFragment;
        this.mAdapter = new CommerceCashEventsAdapter((CommerceCashActivity) this.mFragment.getBaseActivity());
        this.mFragment.loadHistory(this.mOffset, 15);
        this.mContainer = (LinearLayout) findViewById(R.id.commerce_cash_history_container);
        this.mHistoryOptions = (ListView) findViewById(R.id.commerce_cash_fragment_history_options);
        this.mHistoryOptions.setAdapter(this.mAdapter);
        this.mLoadingFooterView = new LoadingFooterView(getContext());
        this.mLoadingFooterView.setReserveSpaceWhenHidden(false);
        setLoadingFooter(this.mLoadingFooterView);
        this.mHistoryOptions.addFooterView(this.mLoadingFooterView);
        this.mHistoryOptions.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (view instanceof CommerceCashEventView) {
                    String transactionId = ((CommerceCashEventView) view).getTransactionId();
                    if (transactionId != null) {
                        Intent intent = new Intent();
                        intent.setClass(CommerceCashHistoryView.this.getContext(), WebViewActivity.class);
                        intent.putExtra("ExtraUrl", WebViewActivity.getOrderUrl(transactionId));
                        intent.putExtra("ExtraActionBarTitle", CommerceCashHistoryView.this.getResources().getString(R.string.order_details));
                        CommerceCashHistoryView.this.getContext().startActivity(intent);
                    }
                }
            }
        });
        this.mContainer.setPadding(this.mContainer.getPaddingLeft(), this.mFragment.getTabAreaSize(), this.mContainer.getPaddingRight(), this.mContainer.getPaddingBottom());
        this.mEmptyHistoryIcon = (AutoReleasableImageView) findViewById(R.id.commerce_cash_empty_history_icon);
        this.mEmptyHistoryText = findViewById(R.id.commerce_cash_empty_history_text);
        this.mEmptyHistoryIcon.setVisibility(8);
        this.mEmptyHistoryText.setVisibility(8);
    }

    public void handleLoadingSuccess(WishCommerceCashHistory wishCommerceCashHistory) {
        if (wishCommerceCashHistory != null) {
            this.mHistory = wishCommerceCashHistory;
            this.mAdapter.setEvents(this.mHistory);
            this.mLoadedAllEvents = this.mHistory.getHasNoMoreItems();
            this.mOffset = this.mHistory.getNextOffset();
            int i = 0;
            boolean z = this.mAdapter.getCount() == 0 && this.mLoadedAllEvents;
            this.mHistoryOptions.setVisibility(z ? 8 : 0);
            this.mEmptyHistoryIcon.setVisibility(z ? 0 : 8);
            View view = this.mEmptyHistoryText;
            if (!z) {
                i = 8;
            }
            view.setVisibility(i);
            if (z) {
                Resources resources = getResources();
                this.mEmptyHistoryIcon.setImageDrawable(resources.getDrawable(R.drawable.empty_cash_history_icon));
                this.mEmptyHistoryIcon.setBackground(resources.getDrawable(R.drawable.commerce_cash_logo_circle_background));
                this.mContainer.setBackgroundColor(getResources().getColor(R.color.gray7));
            }
            if (this.mLoadedAllEvents) {
                markNoMoreItems();
            } else {
                this.mFragment.loadHistory(this.mOffset, 15);
            }
            markLoadingComplete();
        }
    }

    public void handleLoadingFailure() {
        markLoadingErrored();
    }

    public int getCurrentScrollY() {
        if (this.mHistoryOptions != null) {
            return this.mHistoryOptions.getScrollY();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setupScroller(View view) {
        this.mPagerHelper.setupScroller(view);
    }
}
