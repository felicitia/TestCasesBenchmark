package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ViewAttachDetachedEvent extends e {
    private final View view;

    AutoValue_ViewAttachDetachedEvent(View view2) {
        if (view2 == null) {
            throw new NullPointerException("Null view");
        }
        this.view = view2;
    }

    @NonNull
    public View view() {
        return this.view;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ViewAttachDetachedEvent{view=");
        sb.append(this.view);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        return this.view.equals(((e) obj).view());
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}
