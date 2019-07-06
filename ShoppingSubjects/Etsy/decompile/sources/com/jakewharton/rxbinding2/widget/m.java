package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* compiled from: SeekBarStopChangeEvent */
public abstract class m extends j {
    @CheckResult
    @NonNull
    public static m create(@NonNull SeekBar seekBar) {
        return new AutoValue_SeekBarStopChangeEvent(seekBar);
    }

    m() {
    }
}
