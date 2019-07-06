package com.etsy.android.uikit.view;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;

public class ContactsAutoComplete extends InstantAutoCompleteTextView {
    public ContactsAutoComplete(Context context) {
        super(context);
    }

    public ContactsAutoComplete(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ContactsAutoComplete(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public CharSequence convertSelectionToString(Object obj) {
        if (obj instanceof Cursor) {
            return ((Cursor) obj).getString(1);
        }
        return super.convertSelectionToString(obj);
    }
}
