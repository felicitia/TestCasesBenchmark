package com.salesforce.marketingcloud.analytics;

import android.support.annotation.NonNull;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.salesforce.marketingcloud.analytics.$$AutoValue_PiOrder reason: invalid class name */
abstract class C$$AutoValue_PiOrder extends PiOrder {
    private final PiCart a;
    private final String b;
    private final double c;
    private final double d;

    C$$AutoValue_PiOrder(PiCart piCart, String str, double d2, double d3) {
        if (piCart == null) {
            throw new NullPointerException("Null cart");
        }
        this.a = piCart;
        if (str == null) {
            throw new NullPointerException("Null orderNumber");
        }
        this.b = str;
        this.c = d2;
        this.d = d3;
    }

    @NonNull
    public PiCart cart() {
        return this.a;
    }

    public double discount() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PiOrder)) {
            return false;
        }
        PiOrder piOrder = (PiOrder) obj;
        return this.a.equals(piOrder.cart()) && this.b.equals(piOrder.orderNumber()) && Double.doubleToLongBits(this.c) == Double.doubleToLongBits(piOrder.shipping()) && Double.doubleToLongBits(this.d) == Double.doubleToLongBits(piOrder.discount());
    }

    public int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.c) >>> 32) ^ Double.doubleToLongBits(this.c)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.d) >>> 32) ^ Double.doubleToLongBits(this.d)));
    }

    @NonNull
    public String orderNumber() {
        return this.b;
    }

    public double shipping() {
        return this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PiOrder{cart=");
        sb.append(this.a);
        sb.append(", orderNumber=");
        sb.append(this.b);
        sb.append(", shipping=");
        sb.append(this.c);
        sb.append(", discount=");
        sb.append(this.d);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
