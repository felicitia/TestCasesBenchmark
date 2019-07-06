package com.contextlogic.wish.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class AddEditPaymentsAddressCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final View paymentBillingAddressBottomDivider;
    public final LinearLayout paymentBillingAddressLayout;
    public final AutoReleasableImageView paymentBillingAddressPin;
    public final AppCompatRadioButton paymentBillingAddressRadioButton;
    public final ThemedTextView paymentBillingAddressText;

    static {
        sViewsWithIds.put(R.id.payment_billing_address_layout, 1);
        sViewsWithIds.put(R.id.payment_billing_address_radio_button, 2);
        sViewsWithIds.put(R.id.payment_billing_address_pin, 3);
        sViewsWithIds.put(R.id.payment_billing_address_text, 4);
        sViewsWithIds.put(R.id.payment_billing_address_bottom_divider, 5);
    }

    public AddEditPaymentsAddressCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.paymentBillingAddressBottomDivider = (View) mapBindings[5];
        this.paymentBillingAddressLayout = (LinearLayout) mapBindings[1];
        this.paymentBillingAddressPin = (AutoReleasableImageView) mapBindings[3];
        this.paymentBillingAddressRadioButton = (AppCompatRadioButton) mapBindings[2];
        this.paymentBillingAddressText = (ThemedTextView) mapBindings[4];
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
}
