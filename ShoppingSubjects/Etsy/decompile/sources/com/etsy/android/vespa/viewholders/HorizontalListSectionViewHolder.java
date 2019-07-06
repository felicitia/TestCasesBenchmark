package com.etsy.android.vespa.viewholders;

import android.app.Activity;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.util.b.a;
import com.etsy.android.uikit.view.SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.g;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

public class HorizontalListSectionViewHolder extends BaseViewHolder<g> {
    private BaseViewHolderFactoryRecyclerViewAdapter mAdapter;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView = getRootView();
    @Nullable
    private g mSection;
    /* access modifiers changed from: private */
    public SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout mSwipeToRefreshLayout;

    public HorizontalListSectionViewHolder(FragmentActivity fragmentActivity, ViewGroup viewGroup, b bVar, boolean z, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_card_view_horiz_scroll_section, viewGroup, false));
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), 0, false));
        this.mRecyclerView.setHorizontalScrollBarEnabled(false);
        this.mAdapter = initAdapter(fragmentActivity, bVar, cVar);
        this.mRecyclerView.setRecycledViewPool(cVar.f());
        new GravitySnapHelper(GravityCompat.START).attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mAdapter);
        if (!z) {
            this.mRecyclerView.setItemAnimator(null);
        }
        this.mAdapter.setIsNestedSectionAdapter(true);
        Activity activity = (Activity) this.mRecyclerView.getContext();
        if (activity != null) {
            this.mSwipeToRefreshLayout = (SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout) activity.findViewById(i.swipe_refresh_layout);
            if (this.mSwipeToRefreshLayout != null) {
                this.mRecyclerView.setOnScrollListener(new OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        if (i > ViewConfiguration.get(HorizontalListSectionViewHolder.this.mRecyclerView.getContext()).getScaledTouchSlop() && HorizontalListSectionViewHolder.this.mSwipeToRefreshLayout != null) {
                            HorizontalListSectionViewHolder.this.mSwipeToRefreshLayout.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                });
            }
        }
    }

    public void bind(g gVar) {
        if (gVar.getItems() != null && gVar.getItems().size() > 0) {
            this.mRecyclerView.getLayoutParams().height = this.mAdapter.getViewHolderFactory().a(((com.etsy.android.vespa.k) gVar.getItems().get(0)).getViewType());
        }
        this.mAdapter.clear();
        ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).setInitialPrefetchItemCount(gVar.getItems().size());
        this.mAdapter.addListSection(gVar);
        Parcelable layoutState = gVar.getLayoutState();
        if (layoutState != null) {
            this.mRecyclerView.getLayoutManager().onRestoreInstanceState(layoutState);
        }
        this.mSection = gVar;
    }

    public void recycle() {
        if (!(this.mSection == null || this.mAdapter == null || this.mRecyclerView == null)) {
            this.mSection.setLayoutState(this.mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
        this.mSection = null;
    }

    /* access modifiers changed from: protected */
    public BaseViewHolderFactoryRecyclerViewAdapter initAdapter(FragmentActivity fragmentActivity, b bVar, final c cVar) {
        return new BaseViewHolderFactoryRecyclerViewAdapter(fragmentActivity, bVar) {
            @NonNull
            public c createViewHolderFactory(@Nullable a aVar) {
                return cVar;
            }
        };
    }

    public BaseViewHolderFactoryRecyclerViewAdapter getAdapter() {
        return this.mAdapter;
    }

    public RecyclerView getRootView() {
        return (RecyclerView) super.getRootView();
    }
}
