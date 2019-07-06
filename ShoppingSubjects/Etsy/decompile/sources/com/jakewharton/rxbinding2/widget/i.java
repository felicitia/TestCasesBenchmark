package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;

/* compiled from: SearchViewQueryTextEvent */
public abstract class i {
    public abstract boolean isSubmitted();

    @NonNull
    public abstract CharSequence queryText();

    @NonNull
    public abstract SearchView view();

    @CheckResult
    @NonNull
    public static i create(@NonNull SearchView searchView, @NonNull CharSequence charSequence, boolean z) {
        return new AutoValue_SearchViewQueryTextEvent(searchView, charSequence, z);
    }

    i() {
    }
}
