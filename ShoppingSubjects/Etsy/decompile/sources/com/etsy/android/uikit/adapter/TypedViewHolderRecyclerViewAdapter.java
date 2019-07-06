package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;

public abstract class TypedViewHolderRecyclerViewAdapter<T, K extends ViewHolder> extends BaseRecyclerViewAdapter<T> {
    /* access modifiers changed from: protected */
    public abstract void onBindTypedViewHolder(K k, int i);

    /* access modifiers changed from: protected */
    public abstract K onCreateListItemViewHolder(ViewGroup viewGroup, int i);

    protected TypedViewHolderRecyclerViewAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar);
    }

    /* access modifiers changed from: protected */
    public final void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        onBindTypedViewHolder(viewHolder, i);
    }
}
