package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.SeekBar;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SeekBarProgressChangeEvent extends k {
    private final boolean fromUser;
    private final int progress;
    private final SeekBar view;

    AutoValue_SeekBarProgressChangeEvent(SeekBar seekBar, int i, boolean z) {
        if (seekBar == null) {
            throw new NullPointerException("Null view");
        }
        this.view = seekBar;
        this.progress = i;
        this.fromUser = z;
    }

    @NonNull
    public SeekBar view() {
        return this.view;
    }

    public int progress() {
        return this.progress;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SeekBarProgressChangeEvent{view=");
        sb.append(this.view);
        sb.append(", progress=");
        sb.append(this.progress);
        sb.append(", fromUser=");
        sb.append(this.fromUser);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (!(this.view.equals(kVar.view()) && this.progress == kVar.progress() && this.fromUser == kVar.fromUser())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.progress) * 1000003) ^ (this.fromUser ? 1231 : 1237);
    }
}
