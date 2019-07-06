package com.hannesdorfmann.adapterdelegates2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.List;

/* compiled from: AbsListItemAdapterDelegate */
public abstract class a<I extends T, T, VH extends ViewHolder> implements b<List<T>> {
    /* access modifiers changed from: protected */
    public abstract void a(@NonNull I i, @NonNull VH vh);

    /* access modifiers changed from: protected */
    public abstract boolean a(@NonNull T t, List<T> list, int i);

    public final boolean a(@NonNull List<T> list, int i) {
        return a((T) list.get(i), list, i);
    }

    public final void a(@NonNull List<T> list, int i, @NonNull ViewHolder viewHolder) {
        a((I) list.get(i), (VH) viewHolder);
    }
}
