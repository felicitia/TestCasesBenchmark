package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ViewScrollChangeEvent extends k {
    private final int oldScrollX;
    private final int oldScrollY;
    private final int scrollX;
    private final int scrollY;
    private final View view;

    AutoValue_ViewScrollChangeEvent(View view2, int i, int i2, int i3, int i4) {
        if (view2 == null) {
            throw new NullPointerException("Null view");
        }
        this.view = view2;
        this.scrollX = i;
        this.scrollY = i2;
        this.oldScrollX = i3;
        this.oldScrollY = i4;
    }

    @NonNull
    public View view() {
        return this.view;
    }

    public int scrollX() {
        return this.scrollX;
    }

    public int scrollY() {
        return this.scrollY;
    }

    public int oldScrollX() {
        return this.oldScrollX;
    }

    public int oldScrollY() {
        return this.oldScrollY;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ViewScrollChangeEvent{view=");
        sb.append(this.view);
        sb.append(", scrollX=");
        sb.append(this.scrollX);
        sb.append(", scrollY=");
        sb.append(this.scrollY);
        sb.append(", oldScrollX=");
        sb.append(this.oldScrollX);
        sb.append(", oldScrollY=");
        sb.append(this.oldScrollY);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (!(this.view.equals(kVar.view()) && this.scrollX == kVar.scrollX() && this.scrollY == kVar.scrollY() && this.oldScrollX == kVar.oldScrollX() && this.oldScrollY == kVar.oldScrollY())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.scrollX) * 1000003) ^ this.scrollY) * 1000003) ^ this.oldScrollX) * 1000003) ^ this.oldScrollY;
    }
}
