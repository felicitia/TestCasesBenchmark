package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.contextlogic.wish.util.ViewUtil;

public class ThemedDropdownEditText extends ThemedAutoCompleteTextView {
    public boolean enoughToFilter() {
        return true;
    }

    public ThemedDropdownEditText(Context context) {
        super(context);
    }

    public ThemedDropdownEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ThemedDropdownEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && getFilter() != null && ViewUtil.extractEditTextValue(this) == null) {
            performFiltering("", 0);
            showDropDown();
        }
    }
}
