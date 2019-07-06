package com.hannesdorfmann.adapterdelegates2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

/* compiled from: AdapterDelegate */
public interface b<T> {
    @NonNull
    ViewHolder a(ViewGroup viewGroup);

    void a(@NonNull T t, int i, @NonNull ViewHolder viewHolder);

    boolean a(@NonNull T t, int i);
}
