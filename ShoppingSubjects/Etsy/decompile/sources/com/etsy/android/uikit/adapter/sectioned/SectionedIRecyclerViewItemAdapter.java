package com.etsy.android.uikit.adapter.sectioned;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.uikit.adapter.sectioned.b.a;
import com.hannesdorfmann.adapterdelegates2.c;
import java.util.List;

public class SectionedIRecyclerViewItemAdapter<ItemType> extends d<ItemType, c<ItemType>, ViewHolder> {
    protected final c<List<ItemType>> mDelegatesManager;

    public /* bridge */ /* synthetic */ int getItemCountForSection(int i) {
        return super.getItemCountForSection(i);
    }

    public /* bridge */ /* synthetic */ int getSectionCount() {
        return super.getSectionCount();
    }

    public SectionedIRecyclerViewItemAdapter() {
        this(new c());
    }

    public SectionedIRecyclerViewItemAdapter(@NonNull c<List<ItemType>> cVar) {
        this.mDelegatesManager = cVar;
    }

    public int getItemViewType(@NonNull a aVar, int i) {
        return this.mDelegatesManager.a(((c) this.mSections.get(aVar.a)).c(), aVar.b);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull a aVar, int i) {
        this.mDelegatesManager.a(((c) this.mSections.get(aVar.a)).c(), aVar.b, viewHolder);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mDelegatesManager.a(viewGroup, i);
    }
}
