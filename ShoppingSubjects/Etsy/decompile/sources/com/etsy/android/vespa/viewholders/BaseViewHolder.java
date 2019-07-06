package com.etsy.android.vespa.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.logger.b;
import com.etsy.android.uikit.util.a;

public class BaseViewHolder<T> extends ViewHolder {
    /* access modifiers changed from: protected */
    @NonNull
    public b mViewTracker;

    public void bind(T t) {
    }

    public void recycle() {
    }

    public BaseViewHolder(View view) {
        super(view);
        this.mViewTracker = a.b(view);
    }

    public View getRootView() {
        return this.itemView;
    }

    public View findViewById(int i) {
        return this.itemView.findViewById(i);
    }

    protected static LayoutInflater getInflater(@NonNull ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext());
    }
}
