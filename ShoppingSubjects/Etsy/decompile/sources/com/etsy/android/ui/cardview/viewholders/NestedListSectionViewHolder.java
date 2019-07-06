package com.etsy.android.ui.cardview.viewholders;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.h;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class NestedListSectionViewHolder extends BaseViewHolder<h> {
    private BaseViewHolderFactoryRecyclerViewAdapter mAdapter;
    private final RecyclerView mRecyclerView = ((RecyclerView) findViewById(R.id.items));

    public NestedListSectionViewHolder(@NonNull FragmentActivity fragmentActivity, @NonNull ViewGroup viewGroup, @NonNull b bVar, @NonNull c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_nested_list_section, viewGroup, false));
        this.mAdapter = new BaseViewHolderFactoryRecyclerViewAdapter(fragmentActivity, bVar);
        this.mAdapter.setIsNestedSectionAdapter(true);
        this.mAdapter.getViewHolderFactory().a(cVar);
        this.mRecyclerView.setLayoutManager(createLayoutManager(fragmentActivity));
        this.mRecyclerView.setRecycledViewPool(cVar.e());
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    @NonNull
    public final LayoutManager createLayoutManager(FragmentActivity fragmentActivity) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentActivity, getAdapter().getViewHolderFactory().c());
        gridLayoutManager.setSpanSizeLookup(getAdapter().getSpanSizeLookup());
        return gridLayoutManager;
    }

    public BaseViewHolderFactoryRecyclerViewAdapter getAdapter() {
        return this.mAdapter;
    }

    public void bind(h hVar) {
        getAdapter().clear();
        getAdapter().addListSection(hVar);
    }
}
