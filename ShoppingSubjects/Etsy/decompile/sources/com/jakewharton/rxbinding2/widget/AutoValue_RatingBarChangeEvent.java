package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.RatingBar;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_RatingBarChangeEvent extends g {
    private final boolean fromUser;
    private final float rating;
    private final RatingBar view;

    AutoValue_RatingBarChangeEvent(RatingBar ratingBar, float f, boolean z) {
        if (ratingBar == null) {
            throw new NullPointerException("Null view");
        }
        this.view = ratingBar;
        this.rating = f;
        this.fromUser = z;
    }

    @NonNull
    public RatingBar view() {
        return this.view;
    }

    public float rating() {
        return this.rating;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RatingBarChangeEvent{view=");
        sb.append(this.view);
        sb.append(", rating=");
        sb.append(this.rating);
        sb.append(", fromUser=");
        sb.append(this.fromUser);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (!(this.view.equals(gVar.view()) && Float.floatToIntBits(this.rating) == Float.floatToIntBits(gVar.rating()) && this.fromUser == gVar.fromUser())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.rating)) * 1000003) ^ (this.fromUser ? 1231 : 1237);
    }
}
