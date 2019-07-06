package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.viewholder.EmptyHolder;

public abstract class PaddedColumnRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    protected static final int VIEW_TYPE_EMPTY_COLUMN = 601;
    protected static final int VIEW_TYPE_TITLE_COLUMN = 600;
    protected final int mColumns;
    private boolean mPadOutToFillRows;
    protected final boolean mShowLeftTitleColumn;

    /* access modifiers changed from: protected */
    public abstract void bindCoreItemViewType(ViewHolder viewHolder, int i);

    /* access modifiers changed from: protected */
    public abstract void bindLeftTitleItemViewType(ViewHolder viewHolder, int i);

    /* access modifiers changed from: protected */
    public abstract int getCoreItemViewType(int i);

    /* access modifiers changed from: protected */
    public abstract ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i);

    /* access modifiers changed from: protected */
    public abstract ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i);

    protected PaddedColumnRecyclerViewAdapter(FragmentActivity fragmentActivity, c cVar, int i, boolean z, boolean z2) {
        super(fragmentActivity, cVar);
        f.a(z && i < 2, (RuntimeException) new IllegalArgumentException("If using LeftTitleColumnRecyclerViewAdapter with left column enabled, must have at least 2 columns. Using 1 column will fail to show any items."));
        this.mColumns = i;
        this.mShowLeftTitleColumn = z;
        this.mPadOutToFillRows = z2;
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        int headerCount = i - getHeaderCount();
        if (this.mShowLeftTitleColumn && headerCount == 0) {
            return VIEW_TYPE_TITLE_COLUMN;
        }
        if (this.mShowLeftTitleColumn && headerCount % this.mColumns == 0) {
            return VIEW_TYPE_EMPTY_COLUMN;
        }
        if (!this.mPadOutToFillRows || headerCount < this.mItems.size() + getExtraLeftColumnCountForItemsSize(this.mItems.size())) {
            return getCoreItemViewType(i);
        }
        return VIEW_TYPE_EMPTY_COLUMN;
    }

    public int getDataItemCount() {
        int size = this.mItems.size();
        if (this.mShowLeftTitleColumn) {
            size += getExtraLeftColumnCountForItemsSize(size);
        }
        return (!this.mPadOutToFillRows || size <= 0 || size % this.mColumns <= 0) ? size : (this.mColumns + size) - (size - (((int) Math.floor((double) (size / this.mColumns))) * this.mColumns));
    }

    private int getExtraLeftColumnCountForItemsSize(int i) {
        if (!this.mShowLeftTitleColumn) {
            return 0;
        }
        int i2 = this.mColumns - 1;
        int i3 = i / i2;
        if (i % i2 > 0) {
            i3++;
        }
        return i3;
    }

    private int getExtraLeftColumnCountForPosition(int i) {
        if (!this.mShowLeftTitleColumn) {
            return 0;
        }
        int headerCount = i - getHeaderCount();
        int i2 = headerCount / this.mColumns;
        if (headerCount % this.mColumns > 0) {
            i2++;
        }
        return i2;
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return i2 / this.mColumns;
            default:
                return super.getSpanSize(i, i2);
        }
    }

    public T getItem(int i) {
        if (getHeaderCount() > 0 && i < getHeaderCount()) {
            return null;
        }
        if (getFooterCount() > 0 && i > (getDataItemCount() + getHeaderCount()) - 1) {
            return null;
        }
        if (!this.mShowLeftTitleColumn && !this.mPadOutToFillRows) {
            return this.mItems.get(i - getHeaderCount());
        }
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
                return null;
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return null;
            default:
                return super.getItem(i - getExtraLeftColumnCountForPosition(i));
        }
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
                return onCreateLeftTitleItemViewHolder(viewGroup, i);
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return new EmptyHolder(this.mContext);
            default:
                return onCreateCoreItemViewHolder(viewGroup, i);
        }
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
                bindLeftTitleItemViewType(viewHolder, i);
                return;
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return;
            default:
                bindCoreItemViewType(viewHolder, i);
                return;
        }
    }
}
