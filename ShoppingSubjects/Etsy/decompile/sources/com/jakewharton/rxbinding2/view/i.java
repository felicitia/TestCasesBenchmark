package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: ViewGroupHierarchyChildViewRemoveEvent */
public abstract class i extends g {
    @CheckResult
    @NonNull
    public static i create(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return new AutoValue_ViewGroupHierarchyChildViewRemoveEvent(viewGroup, view);
    }

    i() {
    }
}
