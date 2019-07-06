package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModel;

@Deprecated
public abstract class CollapsibleBaseArrayAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    static final int VIEW_COLLAPSED = 1;
    static final int VIEW_NORMAL = 0;
    private static final int VISIBLE_ITEMS_DEFAULT = 1;
    private boolean mIsCollapsed = false;
    private int mVisibleItems = 1;

    public abstract View getCollapsedView(int i, View view, ViewGroup viewGroup);

    public abstract View getNormalView(int i, View view, ViewGroup viewGroup);

    public int getViewTypeCount() {
        return 2;
    }

    public CollapsibleBaseArrayAdapter(FragmentActivity fragmentActivity, int i, c cVar) {
        super(fragmentActivity, i, cVar);
    }

    public void setVisibleItemsCount(int i) {
        this.mVisibleItems = i;
        notifyDataSetChanged();
    }

    public void setCollapsed(boolean z) {
        this.mIsCollapsed = z;
        notifyDataSetChanged();
    }

    public boolean isCollapsed() {
        return this.mIsCollapsed && isCollapsible();
    }

    public boolean isCollapsible() {
        return super.getCount() > this.mVisibleItems + 1;
    }

    private boolean isCollapsedView(int i) {
        boolean z = false;
        if (!isCollapsed()) {
            return false;
        }
        if (i == 0) {
            z = true;
        }
        return z;
    }

    public boolean isValidPosition(int i) {
        return super.isValidPosition(i);
    }

    public int getCount() {
        if (isCollapsed()) {
            return this.mVisibleItems + 1;
        }
        return super.getCount();
    }

    public int getTotalItemCount() {
        return super.getCount();
    }

    public int getCollapsedItemCount() {
        if (isCollapsed()) {
            return super.getCount() - this.mVisibleItems;
        }
        return 0;
    }

    public int getPosition(T t) {
        return super.getPosition(t);
    }

    public T getItem(int i) {
        if (!isCollapsed()) {
            return super.getItem(i);
        }
        if (i != 0) {
            return super.getItem((super.getCount() - 1) - (this.mVisibleItems - i));
        }
        return null;
    }

    public int getItemViewType(int i) {
        return isCollapsedView(i) ? 1 : 0;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) != 1) {
            return getNormalView(i, view, viewGroup);
        }
        return getCollapsedView(i, view, viewGroup);
    }
}
