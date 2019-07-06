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

public class ManagePaymentCellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final View paymentBottomBorder;
    public final AutoReleasableImageView paymentCreditCardIcon;
    public final ThemedTextView paymentCreditCardNumberText;
    public final ThemedTextView paymentDeleteText;
    public final ThemedTextView paymentEditText;
    public final ThemedTextView paymentPrimaryText;
    public final AppCompatRadioButton paymentRadioButton;

    static {
        sViewsWithIds.put(R.id.payment_radio_button, 3);
        sViewsWithIds.put(R.id.payment_credit_card_icon, 4);
        sViewsWithIds.put(R.id.payment_credit_card_number_text, 5);
        sViewsWithIds.put(R.id.payment_primary_text, 6);
        sViewsWithIds.put(R.id.payment_bottom_border, 7);
    }

    public ManagePaymentCellBinding(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.paymentBottomBorder = (View) mapBindings[7];
        this.paymentCreditCardIcon = (AutoReleasableImageView) mapBindings[4];
        this.paymentCreditCardNumberText = (ThemedTextView) mapBindings[5];
        this.paymentDeleteText = (ThemedTextView) mapBindings[2];
        this.paymentDeleteText.setTag(null);
        this.paymentEditText = (ThemedTextView) mapBindings[1];
        this.paymentEditText.setTag(null);
        this.paymentPrimaryText = (ThemedTextView) mapBindings[6];
        this.paymentRadioButton = (AppCompatRadioButton) mapBindings[3];
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
            BindingAdapters.tintCompoundDrawables(this.paymentDeleteText, getColorFromResource(this.paymentDeleteText, R.color.main_primary));
            BindingAdapters.tintCompoundDrawables(this.paymentEditText, getColorFromResource(this.paymentEditText, R.color.main_primary));
        }
    }
}
