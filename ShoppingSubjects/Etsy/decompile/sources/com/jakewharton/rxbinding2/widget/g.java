package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;

/* compiled from: RatingBarChangeEvent */
public abstract class g {
    public abstract boolean fromUser();

    public abstract float rating();

    @NonNull
    public abstract RatingBar view();

    @CheckResult
    @NonNull
    public static g create(@NonNull RatingBar ratingBar, float f, boolean z) {
        return new AutoValue_RatingBarChangeEvent(ratingBar, f, z);
    }

    g() {
    }
}
