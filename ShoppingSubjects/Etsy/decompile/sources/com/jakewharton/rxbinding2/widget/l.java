package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* compiled from: SeekBarStartChangeEvent */
public abstract class l extends j {
    @CheckResult
    @NonNull
    public static l create(@NonNull SeekBar seekBar) {
        return new AutoValue_SeekBarStartChangeEvent(seekBar);
    }

    l() {
    }
}
