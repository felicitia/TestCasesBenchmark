package com.etsy.android.uikit.util;

import android.text.Editable;
import android.text.TextWatcher;

public class EtsyTextWatcher implements TextWatcher {
    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged() {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        onTextChanged();
    }
}
