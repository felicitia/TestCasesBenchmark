package com.etsy.android.uikit.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

public class InstantAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public boolean enoughToFilter() {
        return true;
    }

    public InstantAutoCompleteTextView(Context context) {
        super(context);
    }

    public InstantAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InstantAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && getAdapter() != null) {
            Context context = getContext();
            if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
                post(new Runnable() {
                    public void run() {
                        InstantAutoCompleteTextView.this.performFiltering(InstantAutoCompleteTextView.this.getText(), 0);
                        InstantAutoCompleteTextView.this.showDropDown();
                    }
                });
            }
        }
    }
}
