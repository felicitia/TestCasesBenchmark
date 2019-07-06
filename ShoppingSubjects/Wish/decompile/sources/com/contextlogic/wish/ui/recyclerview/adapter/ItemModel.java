package com.contextlogic.wish.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;

public interface ItemModel<VH extends ViewHolder> {
    int getLayoutResId();

    ViewHolderCreator<VH> getViewHolderCreator();

    void onBindViewHolder(VH vh);

    void onViewRecycled(VH vh);
}
