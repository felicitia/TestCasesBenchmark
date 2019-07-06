package com.etsy.android.ui.convos;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.etsy.android.R;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.convos.adapters.ConvoAdapter;
import com.etsy.android.lib.convos.adapters.a.C0064a;
import com.etsy.android.lib.convos.c;
import com.etsy.android.lib.convos.e;
import com.etsy.android.lib.convos.g;
import com.etsy.android.lib.convos.h;
import com.etsy.android.lib.convos.i;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.ConversationImage;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.u;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyLoadingListFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class ConvoListFragment extends EtsyLoadingListFragment implements LoaderCallbacks<Cursor> {
    public static final int CONVO_LIMIT = 20;
    private static final int LOADER_ID = 0;
    private static final String TAG = f.a(ConvoListFragment.class);
    private ConvoAdapter mAdapter;
    private g mCallbacks = c.b;
    private boolean mIsFirst = true;
    private boolean mIsFirstTime;
    private long mLastSeenConvoId = -1;
    private boolean mLoadedRequested;
    private int mMaxCount;
    BroadcastReceiver mNewMessageSentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equalsIgnoreCase("com.etsy.android.convos.MESSAGE_SENT")) {
                ConvoListFragment.this.reloadConvos();
            }
        }
    };
    private int mOffset = 0;
    private int mPreviousPosition = 0;
    private boolean mReloadOnly;
    private boolean mReloadRequested;
    private boolean mShowingData;

    @NonNull
    public String getTrackingName() {
        return "conversations";
    }

    public static ConvoListFragment getInstance() {
        return new ConvoListFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(false);
        setHasOptionsMenu(true);
        setPullToRefreshEnabled(true);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BOENavDrawerActivity) activity;
        if (!(activity instanceof g)) {
            throw new IllegalStateException("Parent activity must implement ConversationsCallbacks");
        }
        this.mCallbacks = (g) activity;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.etsy.android.convos.MESSAGE_SENT");
        LocalBroadcastManager.getInstance(this.mActivity).registerReceiver(this.mNewMessageSentReceiver, intentFilter);
    }

    public void onDetach() {
        super.onDetach();
        LocalBroadcastManager.getInstance(this.mActivity).unregisterReceiver(this.mNewMessageSentReceiver);
        this.mCallbacks = c.b;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setActivateOnItemClick(this.mCallbacks.isTwoPane());
        setupEmptyView();
        showLoadingView();
        initAndLoad();
    }

    private void initAndLoad() {
        if (getArguments() != null) {
            this.mLastSeenConvoId = getArguments().getLong("last_convo");
            this.mReloadOnly = getArguments().getBoolean("reload_only");
        }
        if (this.mAdapter == null) {
            this.mAdapter = new ConvoAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), new e() {
                public void a(int i, List<ConversationImage> list) {
                }

                public void a(ConversationMessage conversationMessage, C0064a aVar) {
                }

                public boolean a() {
                    return false;
                }

                public void a(EtsyId etsyId, boolean z) {
                    if (z) {
                        com.etsy.android.ui.nav.e.a(ConvoListFragment.this.getActivity()).a().f(a.a().d().b(b.aw));
                        return;
                    }
                    com.etsy.android.ui.nav.e.a(ConvoListFragment.this.getActivity()).a().c(etsyId);
                }
            });
            h.b(getActivity());
        } else {
            this.mAdapter.refreshListView(getResources());
        }
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setDivider(null);
        getLoaderManager().initLoader(0, null, this);
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.convo_message_action_bar, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_new_message) {
            return super.onOptionsItemSelected(menuItem);
        }
        h.b(this.mActivity);
        this.mLastSeenConvoId = -1;
        launchNewMessage();
        return true;
    }

    public void showEmptyView() {
        super.showEmptyView();
        this.mCallbacks.onShowEmpty();
    }

    public void showListView() {
        super.showListView();
        this.mCallbacks.onShowConvo();
    }

    public void showErrorView() {
        if (this.mCallbacks.isTwoPane()) {
            super.hideAllViews();
            this.mCallbacks.onShowErrorView(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ConvoListFragment.this.onRetryClicked();
                }
            });
            return;
        }
        super.showErrorView();
    }

    private void setupEmptyView() {
        if (this.mCallbacks.isTwoPane()) {
            ViewGroup viewGroup = (ViewGroup) getEmptyView();
            viewGroup.removeAllViewsInLayout();
            LinearLayout linearLayout = new LinearLayout(this.mActivity);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LayoutParams(-1, -1));
            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.empty_convos);
            int e = (new l(this.mActivity).e() / layerDrawable.getMinimumHeight()) + 1;
            for (int i = 0; i < e; i++) {
                View view = new View(this.mActivity);
                view.setBackground(layerDrawable);
                linearLayout.addView(view);
            }
            viewGroup.addView(linearLayout);
        } else {
            setEmptyImage(R.drawable.convos_empty);
            setEmptyButtonVisibility(false);
            setEmptyText((int) R.string.empty_convos);
            setEmptySubtext(R.string.convos_empty_subtext);
        }
        this.mListView.setEmptyView(getEmptyView());
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        int headerViewsCount = i - listView.getHeaderViewsCount();
        if (shouldShowConvo(headerViewsCount)) {
            Conversation a = i.a(this.mAdapter.getItem(headerViewsCount));
            goToConvo(a, true);
            h.a((Context) this.mActivity, a.getConversationId());
        }
        this.mReloadOnly = false;
        this.mPreviousPosition = headerViewsCount;
    }

    private boolean shouldShowConvo(int i) {
        return !this.mCallbacks.isTwoPane() || this.mPreviousPosition != i;
    }

    private void showMessage(boolean z) {
        int previousPosition = getPreviousPosition();
        boolean z2 = true;
        this.mListView.setItemChecked(this.mListView.getHeaderViewsCount() + previousPosition, true);
        Conversation a = i.a(this.mAdapter.getItem(previousPosition));
        if (a != null && ((!this.mIsFirst && a.getConversationId() != this.mLastSeenConvoId) || !this.mCallbacks.isShowingConvo())) {
            if (!this.mIsFirst && !z) {
                z2 = false;
            }
            goToConvo(a, z2);
        }
        this.mIsFirst = false;
    }

    private int getPreviousPosition() {
        int i = this.mPreviousPosition;
        int count = this.mAdapter.getCount();
        if (this.mLastSeenConvoId > 0 && count > 0) {
            i = this.mAdapter.getPositionForConvoId(this.mLastSeenConvoId);
        }
        return u.a(i, 0, count - 1);
    }

    private void goToConvo(Conversation conversation, boolean z) {
        if (this.mLastSeenConvoId != conversation.getConversationId()) {
            this.mLastSeenConvoId = conversation.getConversationId();
        }
        this.mCallbacks.onItemSelected(conversation, z);
    }

    private void getConversations() {
        this.mIsFirstTime = this.mOffset == 0;
        d.a a = d.a.a((EtsyApiV3Request) ((EtsyApiV3Request.a) ((EtsyApiV3Request.a) new EtsyApiV3Request.a(Conversation.class, "/member/conversations").a("limit", String.valueOf(20))).a("offset", String.valueOf(this.mOffset))).d());
        a.a((C0065a<ResultType>) new d.b<Conversation>() {
            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
            }

            public void a(@NonNull List<Conversation> list, int i, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
                ConvoListFragment.this.addConvosToDatabase(list);
            }
        });
        a.a((C0065a<ResultType>) new d.b<Conversation>() {
            public void a(@NonNull List<Conversation> list, int i, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
                ConvoListFragment.this.onConvosReturned(list, i);
                ConvoListFragment.this.stopPullToRefresh();
            }

            public void a(int i, String str, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
                ConvoListFragment.this.showErrorView();
                ConvoListFragment.this.stopPullToRefresh();
            }
        }, (Fragment) this);
        getRequestQueue().a((Object) getActivity(), a.c());
    }

    /* access modifiers changed from: private */
    public void reloadConvos() {
        if (!this.mIsFirst) {
            this.mReloadRequested = true;
        }
        this.mOffset = 0;
        getConversations();
    }

    private void launchNewMessage() {
        com.etsy.android.ui.nav.e.a(getActivity()).a().u();
    }

    private void setActivateOnItemClick(boolean z) {
        this.mListView.setChoiceMode(z ? 1 : 0);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return com.etsy.android.lib.convos.contentprovider.b.a((Context) this.mActivity);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mAdapter.swapCursor(cursor);
        if (cursor != null && cursor.getCount() > 0) {
            if (this.mCallbacks.isTwoPane() && (!this.mReloadOnly || this.mReloadRequested)) {
                showMessage(this.mReloadRequested);
            }
            this.mReloadRequested = false;
            showListView();
            if (this.mLoadedRequested) {
                if (endReached()) {
                    stopEndless();
                } else {
                    startEndless();
                }
            }
            this.mShowingData = true;
        } else if (this.mLoadedRequested) {
            showEmptyView();
        }
        if (!this.mLoadedRequested) {
            this.mLoadedRequested = true;
            startPullToRefresh();
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.mAdapter.swapCursor(null);
    }

    public void onLoadMoreItems() {
        getConversations();
    }

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
        stopEndless();
        reloadConvos();
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        stopEndless();
        showLoadingView();
        reloadConvos();
    }

    /* access modifiers changed from: private */
    public void addConvosToDatabase(@NonNull List<Conversation> list) {
        if (this.mIsFirstTime) {
            com.etsy.android.lib.convos.contentprovider.b.a((Context) getActivity(), list);
        } else {
            com.etsy.android.lib.convos.contentprovider.b.b((Context) getActivity(), list);
        }
    }

    /* access modifiers changed from: private */
    public void onConvosReturned(@NonNull List<Conversation> list, int i) {
        this.mOffset += list.size();
        this.mMaxCount = i;
        if ((this.mIsFirstTime && !this.mShowingData && list.size() == 0) || this.mMaxCount == 0) {
            showEmptyView();
        }
    }

    private boolean endReached() {
        return this.mMaxCount <= this.mOffset;
    }
}
