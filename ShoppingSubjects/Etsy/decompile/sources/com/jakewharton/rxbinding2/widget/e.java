package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AdapterView;

/* compiled from: AdapterViewNothingSelectionEvent */
public abstract class e extends f {
    @CheckResult
    @NonNull
    public static f create(@NonNull AdapterView<?> adapterView) {
        return new AutoValue_AdapterViewNothingSelectionEvent(adapterView);
    }

    e() {
    }
}
