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

public class ManagePaymentFooterCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final LinearLayout paymentAddNewSpacing;
    public final ThemedTextView paymentAddNewText;

    static {
        sViewsWithIds.put(R.id.payment_add_new_spacing, 2);
    }

    public ManagePaymentFooterCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.paymentAddNewSpacing = (LinearLayout) mapBindings[2];
        this.paymentAddNewText = (ThemedTextView) mapBindings[1];
        this.paymentAddNewText.setTag(null);
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
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if ((j & 1) != 0) {
            BindingAdapters.tintCompoundDrawables(this.paymentAddNewText, getColorFromResource(this.paymentAddNewText, R.color.main_primary));
        }
    }

    public static ManagePaymentFooterCellBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    public static ManagePaymentFooterCellBinding inflate(LayoutInflater layoutInflater, DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.manage_payment_footer_cell, null, false), dataBindingComponent);
    }

    public static ManagePaymentFooterCellBinding bind(View view, DataBindingComponent dataBindingComponent) {
        if ("layout/manage_payment_footer_cell_0".equals(view.getTag())) {
            return new ManagePaymentFooterCellBinding(dataBindingComponent, view);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("view tag isn't correct on view:");
        sb.append(view.getTag());
        throw new RuntimeException(sb.toString());
    }
}
