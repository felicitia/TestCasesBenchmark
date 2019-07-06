package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractContextRecyclerViewAdapter<T, K extends Context> extends Adapter<ViewHolder> {
    public static final int VIEW_TYPE_FOOTER = 501;
    public static final int VIEW_TYPE_HEADER = 500;
    /* access modifiers changed from: protected */
    public K mContext;
    protected final ArrayList<com.etsy.android.uikit.b.a> mFooters = new ArrayList<>();
    protected final ArrayList<com.etsy.android.uikit.b.a> mHeaders = new ArrayList<>();
    /* access modifiers changed from: protected */
    public c mImageBatch;
    /* access modifiers changed from: protected */
    public LayoutInflater mInflater;
    protected a<T> mItemRemovedListener;
    /* access modifiers changed from: protected */
    public final ArrayList<T> mItems = new ArrayList<>();

    public interface a<T> {
        void a(T t);
    }

    /* access modifiers changed from: protected */
    public abstract int getListItemViewType(int i);

    public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
    }

    /* access modifiers changed from: protected */
    public abstract void onBindListItemViewHolder(ViewHolder viewHolder, int i);

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i);

    /* access modifiers changed from: protected */
    public void onPostBindViewHolder(int i) {
    }

    protected AbstractContextRecyclerViewAdapter(K k, c cVar) {
        this.mContext = k;
        this.mInflater = LayoutInflater.from(k);
        this.mImageBatch = cVar;
    }

    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        for (int i2 = 0; i2 < this.mHeaders.size(); i2++) {
            if (((com.etsy.android.uikit.b.a) this.mHeaders.get(i2)).a == i) {
                return onCreateHeaderViewHolder(viewGroup, i);
            }
        }
        for (int i3 = 0; i3 < this.mFooters.size(); i3++) {
            if (((com.etsy.android.uikit.b.a) this.mFooters.get(i3)).a == i) {
                return onCreateFooterViewHolder(viewGroup, i);
            }
        }
        return onCreateListItemViewHolder(viewGroup, i);
    }

    public final int getItemViewType(int i) {
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            return getHeaderItemViewType(i);
        }
        if (this.mFooters.size() <= 0 || i <= (getDataItemCount() - 1) + this.mHeaders.size()) {
            return getListItemViewType(i);
        }
        return getFooterItemViewType(i);
    }

    public final void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            onBindHeaderViewHolder(viewHolder, i);
        } else if (this.mFooters.size() <= 0 || i <= (getDataItemCount() - 1) + this.mHeaders.size()) {
            onBindListItemViewHolder(viewHolder, i);
        } else {
            onBindFooterViewHolder(viewHolder, i);
        }
        onPostBindViewHolder(i);
    }

    public int getItemCount() {
        return getDataItemCount() + this.mFooters.size() + this.mHeaders.size();
    }

    public void addItems(Collection<? extends T> collection) {
        int size = this.mHeaders.size() + getDataItemCount();
        this.mItems.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }

    public void addItem(T t) {
        int size = this.mHeaders.size() + getDataItemCount();
        this.mItems.add(t);
        notifyItemInserted(size);
    }

    public void addItemAtPosition(int i, T t) {
        int size = this.mHeaders.size() + i;
        this.mItems.add(i, t);
        notifyItemInserted(size);
    }

    public void replaceItem(int i, T t) {
        this.mItems.remove(i);
        this.mItems.add(i, t);
        notifyItemChanged(i);
    }

    public void replaceList(Collection<? extends T> collection) {
        this.mItems.clear();
        this.mItems.addAll(collection);
        notifyDataSetChanged();
    }

    public void clear() {
        int itemCount = getItemCount();
        this.mHeaders.clear();
        this.mItems.clear();
        this.mFooters.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public void clearData() {
        int dataItemCount = getDataItemCount();
        this.mItems.clear();
        notifyItemRangeRemoved(this.mHeaders.size(), dataItemCount);
    }

    public int getDataItemCount() {
        return this.mItems.size();
    }

    public int getFooterCount() {
        return this.mFooters.size();
    }

    public int getHeaderCount() {
        return this.mHeaders.size();
    }

    /* access modifiers changed from: protected */
    public final int getFooterItemViewType(int i) {
        int size = (this.mFooters.size() - getItemCount()) + i;
        if (this.mFooters.size() == 0 || size < 0) {
            return -1;
        }
        return ((com.etsy.android.uikit.b.a) this.mFooters.get(size)).a;
    }

    /* access modifiers changed from: protected */
    public final int getHeaderItemViewType(int i) {
        if (this.mHeaders.size() == 0 || i > this.mHeaders.size() - 1 || i < 0) {
            return -1;
        }
        return ((com.etsy.android.uikit.b.a) this.mHeaders.get(i)).a;
    }

    public T getItem(int i) {
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            return null;
        }
        if (this.mFooters.size() <= 0 || i <= (getDataItemCount() + this.mHeaders.size()) - 1) {
            return this.mItems.get(i - this.mHeaders.size());
        }
        return null;
    }

    public int getDataItemPositionForAdapterPosition(int i) {
        if (i == -1) {
            return -1;
        }
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            return -1;
        }
        if (this.mFooters.size() <= 0 || i <= (getDataItemCount() + this.mHeaders.size()) - 1) {
            return i - this.mHeaders.size();
        }
        return -1;
    }

    public List<T> getItems() {
        return this.mItems;
    }

    public void removeItem(int i) {
        Object remove = this.mItems.remove(i);
        if (this.mItemRemovedListener != null) {
            this.mItemRemovedListener.a(remove);
        }
        notifyItemRemoved(this.mHeaders.size() + i);
    }

    public void removeItem(T t) {
        int indexOf = this.mItems.indexOf(t);
        if (indexOf != -1) {
            removeItem(indexOf);
        }
    }

    public void setItemRemovedListener(a<T> aVar) {
        this.mItemRemovedListener = aVar;
    }

    public void addFooter(int i) {
        this.mFooters.add(new com.etsy.android.uikit.b.a(i));
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeFooter(int i) {
        if (this.mFooters.size() != 0) {
            int size = this.mFooters.size() - 1;
            while (true) {
                if (size < 0) {
                    size = -1;
                    break;
                } else if (((com.etsy.android.uikit.b.a) this.mFooters.get(size)).a == i) {
                    break;
                } else {
                    size--;
                }
            }
            if (size >= 0) {
                this.mFooters.remove(size);
                notifyItemRemoved(getDataItemCount() + this.mHeaders.size() + size);
            }
        }
    }

    public void addHeader(int i) {
        this.mHeaders.add(new com.etsy.android.uikit.b.a(i));
        notifyItemInserted(this.mHeaders.size() - 1);
    }

    public void removeHeader(int i) {
        if (this.mHeaders.size() != 0) {
            int size = this.mHeaders.size() - 1;
            while (true) {
                if (size < 0) {
                    size = -1;
                    break;
                } else if (((com.etsy.android.uikit.b.a) this.mHeaders.get(size)).a == i) {
                    break;
                } else {
                    size--;
                }
            }
            if (size >= 0) {
                this.mHeaders.remove(size);
                notifyItemRemoved(size);
            }
        }
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case VIEW_TYPE_HEADER /*500*/:
            case VIEW_TYPE_FOOTER /*501*/:
                return i2;
            default:
                return 1;
        }
    }
}
