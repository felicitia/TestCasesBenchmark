package com.hannesdorfmann.adapterdelegates2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public abstract class AbsDelegationAdapter<T> extends Adapter {
    protected c<T> delegatesManager;
    protected T items;

    public AbsDelegationAdapter() {
        this(new c());
    }

    public AbsDelegationAdapter(@NonNull c<T> cVar) {
        if (cVar == null) {
            throw new NullPointerException("AdapterDelegatesManager is null");
        }
        this.delegatesManager = cVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(this.items, i, viewHolder);
    }

    public int getItemViewType(int i) {
        return this.delegatesManager.a(this.items, i);
    }

    public T getItems() {
        return this.items;
    }

    public void setItems(T t) {
        this.items = t;
    }
}
