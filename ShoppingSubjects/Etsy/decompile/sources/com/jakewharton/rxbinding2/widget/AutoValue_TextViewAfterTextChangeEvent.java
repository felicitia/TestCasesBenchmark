package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_TextViewAfterTextChangeEvent extends n {
    private final Editable editable;
    private final TextView view;

    AutoValue_TextViewAfterTextChangeEvent(TextView textView, @Nullable Editable editable2) {
        if (textView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = textView;
        this.editable = editable2;
    }

    @NonNull
    public TextView view() {
        return this.view;
    }

    @Nullable
    public Editable editable() {
        return this.editable;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TextViewAfterTextChangeEvent{view=");
        sb.append(this.view);
        sb.append(", editable=");
        sb.append(this.editable);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (!this.view.equals(nVar.view()) || (this.editable != null ? !this.editable.equals(nVar.editable()) : nVar.editable() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ (this.editable == null ? 0 : this.editable.hashCode());
    }
}
