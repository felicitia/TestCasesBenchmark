package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates2.a;

/* compiled from: BaseListItemAdapterDelegate */
public abstract class b<I extends T, T, VH extends ViewHolder> extends a<I, T, VH> {
    @NonNull
    private LayoutInflater a;

    @NonNull
    public abstract VH b(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup);

    public b(@NonNull Activity activity) {
        this.a = activity.getLayoutInflater();
    }

    @NonNull
    public final VH a(@NonNull ViewGroup viewGroup) {
        return b(this.a, viewGroup);
    }
}
