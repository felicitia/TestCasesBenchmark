package com.etsy.android.vespa;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;

public class ArrayRecyclerViewAdapter<T> extends Adapter<BaseViewHolder<T>> {
    protected ArrayList<T> mItems = new ArrayList<>();
    protected c mViewHolderFactory;

    public ArrayRecyclerViewAdapter(c cVar) {
        this.mViewHolderFactory = cVar;
    }

    public BaseViewHolder<T> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mViewHolderFactory.a(viewGroup, i);
    }

    public void onBindViewHolder(BaseViewHolder<T> baseViewHolder, int i) {
        baseViewHolder.bind(this.mItems.get(i));
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void setData(@NonNull List<T> list) {
        this.mItems.clear();
        this.mItems.addAll(list);
        notifyDataSetChanged();
    }
}
