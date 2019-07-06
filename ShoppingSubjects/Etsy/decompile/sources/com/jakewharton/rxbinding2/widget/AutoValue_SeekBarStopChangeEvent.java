package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.SeekBar;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SeekBarStopChangeEvent extends m {
    private final SeekBar view;

    AutoValue_SeekBarStopChangeEvent(SeekBar seekBar) {
        if (seekBar == null) {
            throw new NullPointerException("Null view");
        }
        this.view = seekBar;
    }

    @NonNull
    public SeekBar view() {
        return this.view;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SeekBarStopChangeEvent{view=");
        sb.append(this.view);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        return this.view.equals(((m) obj).view());
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}
