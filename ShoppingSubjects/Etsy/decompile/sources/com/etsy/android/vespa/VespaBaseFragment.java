package com.etsy.android.vespa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.JsonBody;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.core.http.request.d.b;
import com.etsy.android.lib.models.apiv3.vespa.ListSectionActionResult;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.util.aj;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.EmptyMessageView;
import com.etsy.android.vespa.b.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class VespaBaseFragment<T extends Page> extends BaseRecyclerViewListFragment<k> implements OnRefreshListener, e, f {
    /* access modifiers changed from: protected */
    public EmptyMessageView mEmptyMessageView = null;
    protected ArrayList<String> mRequestCachedKeysUrls = new ArrayList<>();

    @NonNull
    public abstract String getApiUrl();

    @NonNull
    public abstract a getPagination();

    /* access modifiers changed from: protected */
    public Map<String, String> getRequestParams() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean hasRecyclerViewPadding() {
        return true;
    }

    @Deprecated
    public final void showLoadingView() {
    }

    /* access modifiers changed from: protected */
    public boolean useConsistentRecyclerViewPadding() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initAdapter();
    }

    /* access modifiers changed from: protected */
    public void initAdapter() {
        this.mAdapter = new BaseViewHolderFactoryRecyclerViewAdapter(getActivity(), getAnalyticsContext(), this);
    }

    @NonNull
    public final LayoutManager createLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getAdapter().getViewHolderFactory().c());
        gridLayoutManager.setSpanSizeLookup(getAdapter().getSpanSizeLookup());
        return gridLayoutManager;
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        getAdapter().setScrollLoadTriggerListener(this);
        if (useConsistentRecyclerViewPadding()) {
            this.mRecyclerView.setPadding(this.mRecyclerView.getPaddingLeft(), this.mRecyclerView.getPaddingBottom(), this.mRecyclerView.getPaddingRight(), this.mRecyclerView.getPaddingBottom());
        } else if (!hasRecyclerViewPadding()) {
            this.mRecyclerView.setPadding(0, 0, 0, 0);
        }
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        return onCreateView;
    }

    /* access modifiers changed from: protected */
    public void onRetry() {
        onRefresh();
    }

    public void onSaveInstanceState(Bundle bundle) {
        saveAdapterState(bundle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void saveAdapterState(Bundle bundle) {
        getAdapter().onSaveInstanceState(bundle);
        getPagination().onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        restoreAdapterState(bundle);
    }

    /* access modifiers changed from: protected */
    public void restoreAdapterState(@Nullable Bundle bundle) {
        getAdapter().onRestoreInstanceState(bundle);
        getPagination().onViewStateRestored(bundle);
    }

    public void onResume() {
        super.onResume();
        if (isEmpty()) {
            onRefresh();
        } else {
            showListView();
        }
    }

    public void onRefresh() {
        this.mEmptyView.setVisibility(8);
        getPagination().reset();
        removeCachedResponses();
        this.mSwipeRefreshLayout.setRefreshing(true);
        loadContent();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public BaseViewHolderFactoryRecyclerViewAdapter getAdapter() {
        return (BaseViewHolderFactoryRecyclerViewAdapter) this.mAdapter;
    }

    @NonNull
    public c getViewHolderFactory() {
        return getAdapter().getViewHolderFactory();
    }

    public void addDelegateViewHolderFactory(c cVar) {
        getAdapter().getViewHolderFactory().a(cVar);
    }

    public int getLayoutId() {
        return k.fragment_vespa_recyclerview;
    }

    public void showErrorView() {
        if (this.mSwipeRefreshLayout != null && this.mEmptyMessageView != null) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            this.mEmptyMessageView.setTitle(o.loading_problem);
            this.mEmptyMessageView.setSubtitle(o.loading_no_internet);
            this.mEmptyMessageView.setImage(g.error_sorry_girl);
            this.mEmptyMessageView.setTryAgain();
            showEmptyView();
        }
    }

    public void showEmptyView() {
        if (this.mLoadingView != null && this.mRecyclerView != null && this.mEmptyView != null) {
            this.mLoadingView.setVisibility(8);
            this.mRecyclerView.setVisibility(8);
            this.mEmptyView.setVisibility(0);
        }
    }

    public void showLoadingOverlay() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
    }

    public void hideLoadingOverlay() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
    }

    public void showActionLoading(boolean z) {
        if (z) {
            showLoadingOverlay();
            if (this.mRecyclerView != null) {
                this.mRecyclerView.setAlpha(0.5f);
                return;
            }
            return;
        }
        hideLoadingOverlay();
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: protected */
    public String getEmptyMessage() {
        return getString(o.empty_default);
    }

    public void removeCachedResponses() {
        Iterator it = this.mRequestCachedKeysUrls.iterator();
        while (it.hasNext()) {
            getRequestQueue().a((String) it.next());
        }
    }

    public void startEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    VespaBaseFragment.this.getAdapter().addLoadingIndicator();
                }
            });
        }
    }

    public void stopEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    VespaBaseFragment.this.getAdapter().removeLoadingIndicator();
                }
            });
        }
    }

    public void showListView() {
        if (this.mLoadingView != null && this.mRecyclerView != null && this.mEmptyView != null) {
            this.mLoadingView.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            this.mEmptyView.setVisibility(8);
        }
    }

    public void setLoading(boolean z) {
        super.setLoading(z);
        if (!z) {
            stopEndless();
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccess(List list, int i) {
        throw new UnsupportedOperationException("Use the onLoadSuccess() methods defined in CardRecyclerViewBaseFragment instead of this base class method.");
    }

    /* access modifiers changed from: protected */
    public final void onLoadComplete(@NonNull j jVar) {
        boolean z = false;
        setLoading(false);
        if (this.mSwipeRefreshLayout != null && this.mSwipeRefreshLayout.isRefreshing()) {
            boolean isRefreshing = this.mSwipeRefreshLayout.isRefreshing();
            this.mSwipeRefreshLayout.setRefreshing(false);
            z = isRefreshing;
        }
        addPage(jVar, z);
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    /* access modifiers changed from: protected */
    public void resetAndLoadContent() {
        getPagination().reset();
        setLoading(false);
        loadContent();
    }

    public final String getContentUrl() {
        String apiNextLink = getPagination().getApiNextLink();
        return TextUtils.isEmpty(apiNextLink) ? getApiUrl() : apiNextLink;
    }

    /* access modifiers changed from: protected */
    public EtsyApiV3Request<T> getRequest() {
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(getPageClass(), getContentUrl());
        a.a((Map<String, String>) getPagination().getPaginationParams());
        a.a(getRequestParams());
        return (EtsyApiV3Request) a.d();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        getRequestQueue().a(((d.a) ((d.a) d.a.a(getRequest()).a(true)).a((C0065a<ResultType>) new b<T>() {
            WeakReference<VespaBaseFragment> a = new WeakReference<>(VespaBaseFragment.this);

            public void a(@NonNull List<T> list, int i, @NonNull com.etsy.android.lib.core.a.a<T> aVar) {
                VespaBaseFragment vespaBaseFragment = (VespaBaseFragment) this.a.get();
                if (vespaBaseFragment != null) {
                    vespaBaseFragment.onLoadSuccess(aVar);
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<T> aVar) {
                VespaBaseFragment vespaBaseFragment = (VespaBaseFragment) this.a.get();
                if (vespaBaseFragment != null) {
                    vespaBaseFragment.onLoadFailure();
                }
            }
        }, (Fragment) this)).c());
    }

    /* access modifiers changed from: protected */
    public void initEmptyStateViews(View view) {
        this.mEmptyMessageView = (EmptyMessageView) view.findViewById(i.empty_message_view);
        this.mLoadingView = view.findViewById(i.loading_view);
        this.mEmptyView = this.mEmptyMessageView;
        this.mErrorView = this.mEmptyMessageView;
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccess(@Nullable com.etsy.android.lib.core.a.a<? extends j> aVar) {
        this.mRequestCachedKeysUrls.add(getContentUrl());
        j page = new Page();
        if (aVar != null) {
            page = (j) aVar.h();
        }
        onLoadComplete(page);
        getPagination().onSuccess(aVar, getAdapter().getItemCount());
    }

    /* access modifiers changed from: protected */
    public void setServerMessage(final MessageCard messageCard) {
        if (messageCard != null) {
            this.mEmptyMessageView.bind(messageCard);
            if (messageCard.isTryAgain()) {
                this.mEmptyMessageView.setButtonClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        VespaBaseFragment.this.onRefresh();
                    }
                });
            } else if (!TextUtils.isEmpty(messageCard.getDeepLinkUrl())) {
                this.mEmptyMessageView.setButtonClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        Intent intent = new Intent(VespaBaseFragment.this.getActivity(), EtsyApplication.get().getDeepLinkRoutingActivity());
                        intent.setData(Uri.parse(messageCard.getDeepLinkUrl()));
                        VespaBaseFragment.this.getActivity().startActivity(intent);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addPage(j jVar) {
        addPage(jVar, false);
    }

    /* access modifiers changed from: protected */
    public void addPage(j jVar, boolean z) {
        if (jVar != null) {
            MessageCard messageCard = jVar.getMessageCard();
            if (messageCard != null) {
                setServerMessage(messageCard);
                return;
            }
            if (z) {
                getAdapter().clear();
            }
            getAdapter().addPage(jVar);
            return;
        }
        onLoadFailure();
    }

    public boolean canLoadContent() {
        return super.canLoadContent() && getPagination().canLoadContent();
    }

    public void onScrolledToLoadTrigger() {
        if (canLoadContent()) {
            startEndless();
        }
        loadContent();
    }

    public int getLoadTriggerPosition() {
        return getPagination().getLoadTriggerPosition();
    }

    /* access modifiers changed from: protected */
    public Class<T> getPageClass() {
        return Page.class;
    }

    public void performActionWithToast(PositionList positionList, ServerDrivenAction serverDrivenAction, int i) {
        aj.b(getContext(), i);
        performAction(positionList, serverDrivenAction);
    }

    public void performAction(final PositionList positionList, ServerDrivenAction serverDrivenAction) {
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(ListSectionActionResult.class, serverDrivenAction.getPath());
        a.a(serverDrivenAction.getRequestMethod());
        if (serverDrivenAction.getRequestMethod().equals(BaseHttpRequest.POST)) {
            a.a((BaseHttpBody) new JsonBody.a().a(serverDrivenAction.getParams()).f());
        } else {
            a.a(serverDrivenAction.getParams());
        }
        com.etsy.android.lib.core.http.request.a c = ((d.a) d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new b<ListSectionActionResult>() {
            WeakReference<VespaBaseFragment> a = new WeakReference<>(VespaBaseFragment.this);

            public void a(@NonNull List<ListSectionActionResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<ListSectionActionResult> aVar) {
                VespaBaseFragment vespaBaseFragment = (VespaBaseFragment) this.a.get();
                if (vespaBaseFragment != null) {
                    vespaBaseFragment.showActionLoading(false);
                    vespaBaseFragment.handleActionResults(positionList, list, aVar);
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<ListSectionActionResult> aVar) {
                VespaBaseFragment vespaBaseFragment = (VespaBaseFragment) this.a.get();
                if (vespaBaseFragment != null) {
                    vespaBaseFragment.showActionLoading(false);
                }
            }
        }, (Fragment) this)).c();
        if (serverDrivenAction.isImmediatelyRemovable()) {
            getAdapter().removeItem(positionList.getParentPosition());
        }
        if (serverDrivenAction.getRefreshNeeded()) {
            showActionLoading(true);
        }
        getRequestQueue().a(c);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        if (r0 >= getAdapter().getItemCount()) goto L_0x0008;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleActionResults(com.etsy.android.vespa.PositionList r5, java.util.List<com.etsy.android.lib.models.apiv3.vespa.ListSectionActionResult> r6, com.etsy.android.lib.core.a.a<com.etsy.android.lib.models.apiv3.vespa.ListSectionActionResult> r7) {
        /*
            r4 = this;
            int r5 = r5.getParentPosition()
            java.util.Iterator r6 = r6.iterator()
        L_0x0008:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x00de
            java.lang.Object r0 = r6.next()
            com.etsy.android.lib.models.apiv3.vespa.ListSectionActionResult r0 = (com.etsy.android.lib.models.apiv3.vespa.ListSectionActionResult) r0
            boolean r1 = r0.hasMessage()
            if (r1 == 0) goto L_0x0025
            android.view.View r1 = r4.getView()
            java.lang.String r2 = r0.getMessage()
            com.etsy.android.lib.util.aj.b(r1, r2)
        L_0x0025:
            java.lang.String r1 = r0.getType()
            r2 = -1
            int r3 = r1.hashCode()
            switch(r3) {
                case -1960928773: goto L_0x0064;
                case -1411068134: goto L_0x005a;
                case -934610812: goto L_0x0050;
                case 27439477: goto L_0x0046;
                case 1094496948: goto L_0x003c;
                case 1742801432: goto L_0x0032;
                default: goto L_0x0031;
            }
        L_0x0031:
            goto L_0x006d
        L_0x0032:
            java.lang.String r3 = "remove_all_below"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 4
            goto L_0x006d
        L_0x003c:
            java.lang.String r3 = "replace"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 2
            goto L_0x006d
        L_0x0046:
            java.lang.String r3 = "reload_page"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 3
            goto L_0x006d
        L_0x0050:
            java.lang.String r3 = "remove"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 0
            goto L_0x006d
        L_0x005a:
            java.lang.String r3 = "append"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 1
            goto L_0x006d
        L_0x0064:
            java.lang.String r3 = "replace_next_link"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            r2 = 5
        L_0x006d:
            switch(r2) {
                case 0: goto L_0x00d5;
                case 1: goto L_0x00a7;
                case 2: goto L_0x009a;
                case 3: goto L_0x0095;
                case 4: goto L_0x0081;
                case 5: goto L_0x0071;
                default: goto L_0x0070;
            }
        L_0x0070:
            goto L_0x0008
        L_0x0071:
            com.etsy.android.vespa.b.a r0 = r4.getPagination()
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            int r1 = r1.getItemCount()
            r0.onSuccess(r7, r1)
            goto L_0x0008
        L_0x0081:
            int r0 = r5 + 1
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            int r1 = r1.getItemCount()
            if (r0 >= r1) goto L_0x0008
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            r1.removeItem(r0)
            goto L_0x0081
        L_0x0095:
            r4.onRefresh()
            goto L_0x0008
        L_0x009a:
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            com.etsy.android.lib.models.cardviewelement.ListSection r0 = r0.getListSection()
            r1.replaceItem(r5, r0)
            goto L_0x0008
        L_0x00a7:
            com.etsy.android.lib.models.cardviewelement.ListSection r0 = r0.getListSection()
            com.etsy.android.vespa.k r1 = r0.getHeader()
            if (r1 == 0) goto L_0x00b9
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            r1.addListSection(r0)
            goto L_0x00c4
        L_0x00b9:
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            java.util.List r0 = r0.getItems()
            r1.addItems(r0)
        L_0x00c4:
            com.etsy.android.vespa.b.a r0 = r4.getPagination()
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r1 = r4.getAdapter()
            int r1 = r1.getItemCount()
            r0.recalculateLoadTriggerPosition(r1)
            goto L_0x0008
        L_0x00d5:
            com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter r0 = r4.getAdapter()
            r0.removeItem(r5)
            goto L_0x0008
        L_0x00de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.vespa.VespaBaseFragment.handleActionResults(com.etsy.android.vespa.PositionList, java.util.List, com.etsy.android.lib.core.a.a):void");
    }

    public void resetScrollPosition() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.scrollToPosition(0);
        }
    }
}
