package com.etsy.android.ui.core.listingpanel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import kotlin.jvm.internal.p;

/* compiled from: PostalCodeTextWatcher.kt */
public final class PostalCodeTextWatcher implements TextWatcher {
    private final EditText mEditableZip;
    private int mOldCount;
    private x mPostalCodeFormatter;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        p.b(charSequence, "s");
    }

    public PostalCodeTextWatcher(EditText editText) {
        p.b(editText, "mEditableZip");
        this.mEditableZip = editText;
    }

    public final x getMPostalCodeFormatter() {
        return this.mPostalCodeFormatter;
    }

    public final void setMPostalCodeFormatter(x xVar) {
        this.mPostalCodeFormatter = xVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        p.b(charSequence, "s");
        this.mOldCount = this.mEditableZip.length();
    }

    public void afterTextChanged(Editable editable) {
        String valueOf = String.valueOf(editable);
        if (this.mPostalCodeFormatter != null) {
            x xVar = this.mPostalCodeFormatter;
            valueOf = String.valueOf(xVar != null ? xVar.a(valueOf, this.mOldCount) : null);
        }
        boolean z = true;
        if (!p.a((Object) valueOf, (Object) String.valueOf(editable))) {
            CharSequence charSequence = valueOf;
            this.mEditableZip.setText(charSequence);
            if (charSequence.length() <= 0) {
                z = false;
            }
            if (z) {
                this.mEditableZip.setSelection(valueOf.length());
            }
        }
    }
}
