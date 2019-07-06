package com.contextlogic.wish.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.button.ThemedButton;

public class AddEditPaymentsFooterCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    public final ThemedButton paymentSaveButton;

    public AddEditPaymentsFooterCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        this.paymentSaveButton = (ThemedButton) mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds)[0];
        this.paymentSaveButton.setTag(null);
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

    public static AddEditPaymentsFooterCellBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    public static AddEditPaymentsFooterCellBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, DataBindingComponent dataBindingComponent) {
        return (AddEditPaymentsFooterCellBinding) DataBindingUtil.inflate(layoutInflater, R.layout.add_edit_payments_footer_cell, viewGroup, z, dataBindingComponent);
    }
}
