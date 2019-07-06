package com.contextlogic.wish.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormCreditCardField;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormCvvEditText;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormExpiryDateEditText;

public class AddEditPaymentsHeaderCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final CreditCardFormCreditCardField paymentCreditCardNumber;
    public final CreditCardFormExpiryDateEditText paymentExpiryDate;
    public final CreditCardFormCvvEditText paymentSecurityCode;

    static {
        sViewsWithIds.put(R.id.payment_credit_card_number, 1);
        sViewsWithIds.put(R.id.payment_expiry_date, 2);
        sViewsWithIds.put(R.id.payment_security_code, 3);
    }

    public AddEditPaymentsHeaderCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.paymentCreditCardNumber = (CreditCardFormCreditCardField) mapBindings[1];
        this.paymentExpiryDate = (CreditCardFormExpiryDateEditText) mapBindings[2];
        this.paymentSecurityCode = (CreditCardFormCvvEditText) mapBindings[3];
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

    public static AddEditPaymentsHeaderCellBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    public static AddEditPaymentsHeaderCellBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, DataBindingComponent dataBindingComponent) {
        return (AddEditPaymentsHeaderCellBinding) DataBindingUtil.inflate(layoutInflater, R.layout.add_edit_payments_header_cell, viewGroup, z, dataBindingComponent);
    }
}
