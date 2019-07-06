package com.etsy.android.localization.addresses;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout.LayoutParams;

public class AddressFieldView extends TextInputLayout {
    private TextInputEditText editTextView;

    public AddressFieldView(Context context) {
        super(context);
        init();
    }

    public AddressFieldView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public AddressFieldView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setLayoutParams(new LayoutParams(-2, -1, 1.0f));
        this.editTextView = new TextInputEditText(getContext());
        addView(this.editTextView);
    }

    public TextInputEditText getEditTextView() {
        return this.editTextView;
    }
}
