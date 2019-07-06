package com.contextlogic.wish.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ManagePaymentHeaderCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    public final ThemedTextView managePaymentHeaderText;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.manage_payment_header_text, 1);
    }

    public ManagePaymentHeaderCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds);
        this.managePaymentHeaderText = (ThemedTextView) mapBindings[1];
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ManagePaymentHeaderCellBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    public static ManagePaymentHeaderCellBinding inflate(LayoutInflater layoutInflater, DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.manage_payment_header_cell, null, false), dataBindingComponent);
    }

    public static ManagePaymentHeaderCellBinding bind(View view, DataBindingComponent dataBindingComponent) {
        if ("layout/manage_payment_header_cell_0".equals(view.getTag())) {
            return new ManagePaymentHeaderCellBinding(dataBindingComponent, view);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("view tag isn't correct on view:");
        sb.append(view.getTag());
        throw new RuntimeException(sb.toString());
    }
}
