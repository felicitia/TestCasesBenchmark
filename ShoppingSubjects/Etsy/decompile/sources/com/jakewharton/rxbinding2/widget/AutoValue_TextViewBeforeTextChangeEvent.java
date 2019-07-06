package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.TextView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_TextViewBeforeTextChangeEvent extends p {
    private final int after;
    private final int count;
    private final int start;
    private final CharSequence text;
    private final TextView view;

    AutoValue_TextViewBeforeTextChangeEvent(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        if (textView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = textView;
        if (charSequence == null) {
            throw new NullPointerException("Null text");
        }
        this.text = charSequence;
        this.start = i;
        this.count = i2;
        this.after = i3;
    }

    @NonNull
    public TextView view() {
        return this.view;
    }

    @NonNull
    public CharSequence text() {
        return this.text;
    }

    public int start() {
        return this.start;
    }

    public int count() {
        return this.count;
    }

    public int after() {
        return this.after;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TextViewBeforeTextChangeEvent{view=");
        sb.append(this.view);
        sb.append(", text=");
        sb.append(this.text);
        sb.append(", start=");
        sb.append(this.start);
        sb.append(", count=");
        sb.append(this.count);
        sb.append(", after=");
        sb.append(this.after);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        if (!(this.view.equals(pVar.view()) && this.text.equals(pVar.text()) && this.start == pVar.start() && this.count == pVar.count() && this.after == pVar.after())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.start) * 1000003) ^ this.count) * 1000003) ^ this.after;
    }
}
