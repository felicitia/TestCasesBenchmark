package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.AdapterView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_AdapterViewNothingSelectionEvent extends e {
    private final AdapterView<?> view;

    AutoValue_AdapterViewNothingSelectionEvent(AdapterView<?> adapterView) {
        if (adapterView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = adapterView;
    }

    @NonNull
    public AdapterView<?> view() {
        return this.view;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdapterViewNothingSelectionEvent{view=");
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
