package com.salesforce.marketingcloud.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.salesforce.marketingcloud.analytics.$$AutoValue_PiCartItem reason: invalid class name */
abstract class C$$AutoValue_PiCartItem extends PiCartItem {
    private final String a;
    private final int b;
    private final double c;
    private final String d;

    C$$AutoValue_PiCartItem(String str, int i, double d2, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("Null item");
        }
        this.a = str;
        this.b = i;
        this.c = d2;
        this.d = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PiCartItem)) {
            return false;
        }
        PiCartItem piCartItem = (PiCartItem) obj;
        if (this.a.equals(piCartItem.item()) && this.b == piCartItem.quantity() && Double.doubleToLongBits(this.c) == Double.doubleToLongBits(piCartItem.price())) {
            if (this.d == null) {
                if (piCartItem.uniqueId() == null) {
                    return true;
                }
            } else if (this.d.equals(piCartItem.uniqueId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.c) >>> 32) ^ Double.doubleToLongBits(this.c)))) * 1000003) ^ (this.d == null ? 0 : this.d.hashCode());
    }

    @NonNull
    public String item() {
        return this.a;
    }

    @NonNull
    public double price() {
        return this.c;
    }

    @NonNull
    public int quantity() {
        return this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PiCartItem{item=");
        sb.append(this.a);
        sb.append(", quantity=");
        sb.append(this.b);
        sb.append(", price=");
        sb.append(this.c);
        sb.append(", uniqueId=");
        sb.append(this.d);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    @Nullable
    public String uniqueId() {
        return this.d;
    }
}
