package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* compiled from: SeekBarProgressChangeEvent */
public abstract class k extends j {
    public abstract boolean fromUser();

    public abstract int progress();

    @CheckResult
    @NonNull
    public static k create(@NonNull SeekBar seekBar, int i, boolean z) {
        return new AutoValue_SeekBarProgressChangeEvent(seekBar, i, z);
    }

    k() {
    }
}
