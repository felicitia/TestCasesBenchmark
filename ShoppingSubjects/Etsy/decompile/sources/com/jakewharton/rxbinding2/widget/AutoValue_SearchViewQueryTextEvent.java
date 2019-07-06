package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.SearchView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SearchViewQueryTextEvent extends i {
    private final boolean isSubmitted;
    private final CharSequence queryText;
    private final SearchView view;

    AutoValue_SearchViewQueryTextEvent(SearchView searchView, CharSequence charSequence, boolean z) {
        if (searchView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = searchView;
        if (charSequence == null) {
            throw new NullPointerException("Null queryText");
        }
        this.queryText = charSequence;
        this.isSubmitted = z;
    }

    @NonNull
    public SearchView view() {
        return this.view;
    }

    @NonNull
    public CharSequence queryText() {
        return this.queryText;
    }

    public boolean isSubmitted() {
        return this.isSubmitted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SearchViewQueryTextEvent{view=");
        sb.append(this.view);
        sb.append(", queryText=");
        sb.append(this.queryText);
        sb.append(", isSubmitted=");
        sb.append(this.isSubmitted);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (!this.view.equals(iVar.view()) || !this.queryText.equals(iVar.queryText()) || this.isSubmitted != iVar.isSubmitted()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.queryText.hashCode()) * 1000003) ^ (this.isSubmitted ? 1231 : 1237);
    }
}
