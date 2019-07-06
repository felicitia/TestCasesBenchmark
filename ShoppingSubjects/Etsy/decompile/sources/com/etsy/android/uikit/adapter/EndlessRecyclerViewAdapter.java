package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.uikit.viewholder.EndlessLoadingViewHolder;
import com.etsy.android.vespa.e;

public abstract class EndlessRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    public static final int VIEW_TYPE_ENDLESS_ERROR = 502;
    public static final int VIEW_TYPE_ENDLESS_LOADING = 503;
    protected e mScrollLoadTriggerListener;

    protected EndlessRecyclerViewAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar);
    }

    /* access modifiers changed from: protected */
    public final void onPostBindViewHolder(int i) {
        if (this.mScrollLoadTriggerListener != null && i == getItemCount() - this.mScrollLoadTriggerListener.getLoadTriggerPosition()) {
            this.mScrollLoadTriggerListener.onScrolledToLoadTrigger();
        }
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder onCreateFooterViewHolder = super.onCreateFooterViewHolder(viewGroup, i);
        if (onCreateFooterViewHolder != null) {
            return onCreateFooterViewHolder;
        }
        switch (i) {
            case VIEW_TYPE_ENDLESS_ERROR /*502*/:
            case VIEW_TYPE_ENDLESS_LOADING /*503*/:
                return new EndlessLoadingViewHolder(this.mInflater.inflate(k.list_item_loading, viewGroup, false));
            default:
                return onCreateFooterViewHolder;
        }
    }

    public void setScrollLoadTriggerListener(e eVar) {
        this.mScrollLoadTriggerListener = eVar;
    }
}
