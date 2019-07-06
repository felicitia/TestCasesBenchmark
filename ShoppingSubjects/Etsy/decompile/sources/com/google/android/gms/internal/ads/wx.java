package com.google.android.gms.internal.ads;

import android.support.v4.internal.view.SupportMenu;

final class wx {
    private final Object a;
    private final int b;

    wx(Object obj, int i) {
        this.a = obj;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof wx)) {
            return false;
        }
        wx wxVar = (wx) obj;
        return this.a == wxVar.a && this.b == wxVar.b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.a) * SupportMenu.USER_MASK) + this.b;
    }
}
