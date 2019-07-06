package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: ViewGroupHierarchyChildViewAddEvent */
public abstract class h extends g {
    @CheckResult
    @NonNull
    public static h create(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return new AutoValue_ViewGroupHierarchyChildViewAddEvent(viewGroup, view);
    }

    h() {
    }
}
