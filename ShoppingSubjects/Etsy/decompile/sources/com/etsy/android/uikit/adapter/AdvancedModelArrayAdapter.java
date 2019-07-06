package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModel;

@Deprecated
public abstract class AdvancedModelArrayAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    private static final int VIEW_TYPE_COUNT = 6;
    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_FOOTER = 2;
    private static final int VIEW_TYPE_HEADER = 1;
    protected static final int VIEW_TYPE_LANDSCAPE = 3;
    protected static final int VIEW_TYPE_PORTRAIT = 0;
    private View mFooterView;
    protected boolean mHasFooter;
    protected boolean mHasHeader;
    private View mHeaderView;

    public View getViewDefault(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public int getViewTypeCount() {
        return 6;
    }

    public AdvancedModelArrayAdapter(FragmentActivity fragmentActivity, int i, c cVar) {
        super(fragmentActivity, i, cVar);
    }

    public void setHeaderView(View view) {
        setHasHeader(true);
        this.mHeaderView = view;
    }

    public void setFooterView(View view) {
        setHasFooter(true);
        this.mFooterView = view;
    }

    public void setHasHeader(boolean z) {
        this.mHasHeader = z;
    }

    public void setHasFooter(boolean z) {
        this.mHasFooter = z;
    }

    public int getRealCount() {
        return super.getCount();
    }

    public int getCount() {
        int realCount = getRealCount();
        if (this.mHasHeader) {
            realCount++;
        }
        return this.mHasFooter ? realCount + 1 : realCount;
    }

    public int getItemViewType(int i) {
        if (isHeaderPosition(i)) {
            return 1;
        }
        return isFooterPosition(i) ? 2 : 0;
    }

    private boolean isHeaderPosition(int i) {
        return this.mHasHeader && i == 0;
    }

    private boolean isFooterPosition(int i) {
        int realCount = getRealCount();
        if (this.mHasFooter) {
            if (this.mHasHeader && i == realCount + 1) {
                return true;
            }
            if (!this.mHasHeader && i == realCount) {
                return true;
            }
        }
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (isHeaderPosition(i)) {
            return getViewHeader(view, viewGroup);
        }
        if (isFooterPosition(i)) {
            return getViewFooter(view, viewGroup);
        }
        if (this.mHasHeader) {
            i--;
        }
        return getViewDefault(i, view, viewGroup);
    }

    public View getViewHeader(View view, ViewGroup viewGroup) {
        return this.mHeaderView;
    }

    public View getViewFooter(View view, ViewGroup viewGroup) {
        return this.mFooterView;
    }
}
