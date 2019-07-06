package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import java.util.Collection;
import java.util.Iterator;

public class SectionedRecyclerViewAdapter extends BaseRecyclerViewAdapter<BaseRecyclerViewAdapter> {
    private static final int TYPE_INDEX_MULTIPLIER = 1000;

    private static class a {
        private int a;
        private BaseRecyclerViewAdapter b;
        private int c;

        public a(BaseRecyclerViewAdapter baseRecyclerViewAdapter, int i, int i2) {
            this.a = i;
            this.b = baseRecyclerViewAdapter;
            this.c = i2;
        }

        public int a() {
            return this.a;
        }

        public BaseRecyclerViewAdapter b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }

    private static class b {
        private BaseRecyclerViewAdapter a;
        /* access modifiers changed from: private */
        public int b;

        public b(BaseRecyclerViewAdapter baseRecyclerViewAdapter, int i) {
            this.a = baseRecyclerViewAdapter;
            this.b = i;
        }

        public BaseRecyclerViewAdapter a() {
            return this.a;
        }
    }

    public SectionedRecyclerViewAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar);
    }

    public void addItem(BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        int headerCount = getHeaderCount() + getDataItemCount();
        this.mItems.add(baseRecyclerViewAdapter);
        notifyItemRangeInserted(headerCount, baseRecyclerViewAdapter.getItemCount());
        registerAdapterObserver(baseRecyclerViewAdapter);
    }

    public void addItems(Collection<? extends BaseRecyclerViewAdapter> collection) {
        int headerCount = getHeaderCount() + getDataItemCount();
        int i = 0;
        for (BaseRecyclerViewAdapter baseRecyclerViewAdapter : collection) {
            registerAdapterObserver(baseRecyclerViewAdapter);
            i += baseRecyclerViewAdapter.getItemCount();
        }
        this.mItems.addAll(collection);
        notifyItemRangeInserted(headerCount, i);
    }

    private void registerAdapterObserver(final BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        baseRecyclerViewAdapter.registerAdapterDataObserver(new AdapterDataObserver() {
            public void onChanged() {
                SectionedRecyclerViewAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int i, int i2) {
                SectionedRecyclerViewAdapter.this.notifyItemRangeChanged(SectionedRecyclerViewAdapter.this.getStartingIndexForAdapter(baseRecyclerViewAdapter) + i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                SectionedRecyclerViewAdapter.this.notifyItemRangeInserted(SectionedRecyclerViewAdapter.this.getStartingIndexForAdapter(baseRecyclerViewAdapter) + i, i2);
            }

            public void onItemRangeRemoved(int i, int i2) {
                SectionedRecyclerViewAdapter.this.notifyItemRangeRemoved(SectionedRecyclerViewAdapter.this.getStartingIndexForAdapter(baseRecyclerViewAdapter) + i, i2);
            }
        });
    }

    public int getDataItemCount() {
        Iterator it = this.mItems.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((BaseRecyclerViewAdapter) it.next()).getItemCount();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        b adapterWithViewTypeForSectionedViewType = getAdapterWithViewTypeForSectionedViewType(i);
        if (adapterWithViewTypeForSectionedViewType != null) {
            return adapterWithViewTypeForSectionedViewType.a().onCreateViewHolder(viewGroup, adapterWithViewTypeForSectionedViewType.b);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        a adapterWithPositionForSectionedPosition = getAdapterWithPositionForSectionedPosition(i);
        if (adapterWithPositionForSectionedPosition != null) {
            adapterWithPositionForSectionedPosition.b().onBindViewHolder(viewHolder, adapterWithPositionForSectionedPosition.c());
        }
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER /*500*/:
            case AbstractContextRecyclerViewAdapter.VIEW_TYPE_FOOTER /*501*/:
                return i2;
            default:
                a adapterWithPositionForSectionedPosition = getAdapterWithPositionForSectionedPosition(i);
                if (adapterWithPositionForSectionedPosition != null) {
                    return adapterWithPositionForSectionedPosition.b().getSpanSize(adapterWithPositionForSectionedPosition.c(), i2);
                }
                return 1;
        }
    }

    /* access modifiers changed from: private */
    public int getStartingIndexForAdapter(BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        int headerCount = getHeaderCount();
        Iterator it = this.mItems.iterator();
        while (it.hasNext()) {
            BaseRecyclerViewAdapter baseRecyclerViewAdapter2 = (BaseRecyclerViewAdapter) it.next();
            if (baseRecyclerViewAdapter2.equals(baseRecyclerViewAdapter)) {
                return headerCount;
            }
            headerCount += baseRecyclerViewAdapter2.getItemCount();
        }
        return headerCount;
    }

    private a getAdapterWithPositionForSectionedPosition(int i) {
        int headerCount = getHeaderCount();
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            if (i >= headerCount && i < ((BaseRecyclerViewAdapter) this.mItems.get(i2)).getItemCount() + headerCount) {
                return new a((BaseRecyclerViewAdapter) this.mItems.get(i2), i2, i - headerCount);
            }
            headerCount += ((BaseRecyclerViewAdapter) this.mItems.get(i2)).getItemCount();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        a adapterWithPositionForSectionedPosition = getAdapterWithPositionForSectionedPosition(i);
        if (adapterWithPositionForSectionedPosition == null) {
            return -1;
        }
        int itemViewType = adapterWithPositionForSectionedPosition.b().getItemViewType(adapterWithPositionForSectionedPosition.c());
        f.a(itemViewType >= 1000, (RuntimeException) new IllegalStateException(String.format("BaseRecyclerViewAdapters used in the SectionedRecyclerViewAdapter must not use view types with values >= %s", new Object[]{Integer.valueOf(1000)})));
        return ((adapterWithPositionForSectionedPosition.a() + 1) * 1000) + itemViewType;
    }

    private b getAdapterWithViewTypeForSectionedViewType(int i) {
        int i2 = (i / 1000) - 1;
        int i3 = i % 1000;
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return null;
        }
        return new b((BaseRecyclerViewAdapter) this.mItems.get(i2), i3);
    }
}
