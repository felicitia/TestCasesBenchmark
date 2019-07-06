package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class ManagedSelectionEditText extends AppCompatEditText {
    private a mOnSelectionChangedListener;

    public interface a {
        void a(int i, int i2);
    }

    public ManagedSelectionEditText(Context context) {
        super(context);
    }

    public ManagedSelectionEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ManagedSelectionEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSelectionChangedListener(a aVar) {
        this.mOnSelectionChangedListener = aVar;
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (this.mOnSelectionChangedListener != null) {
            this.mOnSelectionChangedListener.a(i, i2);
        }
    }
}
