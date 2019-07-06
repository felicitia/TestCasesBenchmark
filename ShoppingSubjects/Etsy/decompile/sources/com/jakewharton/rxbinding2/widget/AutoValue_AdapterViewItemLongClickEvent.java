package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_AdapterViewItemLongClickEvent extends c {
    private final View clickedView;
    private final long id;
    private final int position;
    private final AdapterView<?> view;

    AutoValue_AdapterViewItemLongClickEvent(AdapterView<?> adapterView, View view2, int i, long j) {
        if (adapterView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = adapterView;
        if (view2 == null) {
            throw new NullPointerException("Null clickedView");
        }
        this.clickedView = view2;
        this.position = i;
        this.id = j;
    }

    @NonNull
    public AdapterView<?> view() {
        return this.view;
    }

    @NonNull
    public View clickedView() {
        return this.clickedView;
    }

    public int position() {
        return this.position;
    }

    public long id() {
        return this.id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdapterViewItemLongClickEvent{view=");
        sb.append(this.view);
        sb.append(", clickedView=");
        sb.append(this.clickedView);
        sb.append(", position=");
        sb.append(this.position);
        sb.append(", id=");
        sb.append(this.id);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (!this.view.equals(cVar.view()) || !this.clickedView.equals(cVar.clickedView()) || this.position != cVar.position() || this.id != cVar.id()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (int) (((long) ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.clickedView.hashCode()) * 1000003) ^ this.position) * 1000003)) ^ ((this.id >>> 32) ^ this.id));
    }
}
