package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ViewGroupHierarchyChildViewAddEvent extends h {
    private final View child;
    private final ViewGroup view;

    AutoValue_ViewGroupHierarchyChildViewAddEvent(ViewGroup viewGroup, View view2) {
        if (viewGroup == null) {
            throw new NullPointerException("Null view");
        }
        this.view = viewGroup;
        if (view2 == null) {
            throw new NullPointerException("Null child");
        }
        this.child = view2;
    }

    @NonNull
    public ViewGroup view() {
        return this.view;
    }

    @NonNull
    public View child() {
        return this.child;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ViewGroupHierarchyChildViewAddEvent{view=");
        sb.append(this.view);
        sb.append(", child=");
        sb.append(this.child);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (!this.view.equals(hVar.view()) || !this.child.equals(hVar.child())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}
