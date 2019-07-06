package com.contextlogic.wish.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public interface ViewHolderCreator<VH extends ViewHolder> {
    VH createViewHolder(View view);
}
