package com.etsy.android.uikit.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.vespa.e;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.hannesdorfmann.adapterdelegates2.c;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemAdapter extends BaseRecyclerViewAdapter<Object> {
    protected c<List<Object>> delegatesManager;
    protected e mScrollLoadTriggerListener;

    public RecyclerViewItemAdapter(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar) {
        this(fragmentActivity, cVar, new c());
    }

    public RecyclerViewItemAdapter(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar, @NonNull c<List<Object>> cVar2) {
        super(fragmentActivity, cVar);
        this.delegatesManager = cVar2;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(this.mItems, i, viewHolder);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(new ArrayList(this.mHeaders), i, viewHolder);
    }

    public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(new ArrayList(this.mFooters), i, viewHolder);
    }

    public int getListItemViewType(int i) {
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            return this.delegatesManager.a(new ArrayList(this.mHeaders), i);
        }
        if (this.mFooters.size() <= 0 || i <= (getDataItemCount() - 1) + this.mHeaders.size()) {
            return this.delegatesManager.a(this.mItems, i);
        }
        return this.delegatesManager.a(new ArrayList(this.mFooters), i);
    }

    public int getItemCount() {
        if (this.mItems == null) {
            return 0;
        }
        return this.mItems.size() + this.mFooters.size() + this.mHeaders.size();
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void onPostBindViewHolder(int i) {
        if (this.mScrollLoadTriggerListener != null && i == getItemCount() - this.mScrollLoadTriggerListener.getLoadTriggerPosition()) {
            this.mScrollLoadTriggerListener.onScrolledToLoadTrigger();
        }
    }

    public void setScrollLoadTriggerListener(e eVar) {
        this.mScrollLoadTriggerListener = eVar;
    }
}
