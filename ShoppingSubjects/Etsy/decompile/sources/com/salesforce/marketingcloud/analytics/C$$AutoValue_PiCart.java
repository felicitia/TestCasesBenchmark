package com.salesforce.marketingcloud.analytics;

import android.support.annotation.NonNull;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.salesforce.marketingcloud.analytics.$$AutoValue_PiCart reason: invalid class name */
abstract class C$$AutoValue_PiCart extends PiCart {
    private final List<PiCartItem> a;

    C$$AutoValue_PiCart(List<PiCartItem> list) {
        if (list == null) {
            throw new NullPointerException("Null cartItems");
        }
        this.a = list;
    }

    @NonNull
    public List<PiCartItem> cartItems() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PiCart)) {
            return false;
        }
        return this.a.equals(((PiCart) obj).cartItems());
    }

    public int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PiCart{cartItems=");
        sb.append(this.a);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
