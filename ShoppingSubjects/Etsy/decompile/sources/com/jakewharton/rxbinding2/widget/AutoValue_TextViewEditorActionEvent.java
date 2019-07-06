package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_TextViewEditorActionEvent extends q {
    private final int actionId;
    private final KeyEvent keyEvent;
    private final TextView view;

    AutoValue_TextViewEditorActionEvent(TextView textView, int i, @Nullable KeyEvent keyEvent2) {
        if (textView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = textView;
        this.actionId = i;
        this.keyEvent = keyEvent2;
    }

    @NonNull
    public TextView view() {
        return this.view;
    }

    public int actionId() {
        return this.actionId;
    }

    @Nullable
    public KeyEvent keyEvent() {
        return this.keyEvent;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TextViewEditorActionEvent{view=");
        sb.append(this.view);
        sb.append(", actionId=");
        sb.append(this.actionId);
        sb.append(", keyEvent=");
        sb.append(this.keyEvent);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        if (!this.view.equals(qVar.view()) || this.actionId != qVar.actionId() || (this.keyEvent != null ? !this.keyEvent.equals(qVar.keyEvent()) : qVar.keyEvent() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.actionId) * 1000003) ^ (this.keyEvent == null ? 0 : this.keyEvent.hashCode());
    }
}
