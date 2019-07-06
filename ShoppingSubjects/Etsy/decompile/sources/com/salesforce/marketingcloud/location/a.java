package com.salesforce.marketingcloud.location;

import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends b {
    private final double a;
    private final double b;

    a(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public double a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Double.doubleToLongBits(this.a) == Double.doubleToLongBits(bVar.a()) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(bVar.b());
    }

    public int hashCode() {
        return ((((int) ((Double.doubleToLongBits(this.a) >>> 32) ^ Double.doubleToLongBits(this.a))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.b) >>> 32) ^ Double.doubleToLongBits(this.b)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LatLon{latitude=");
        sb.append(this.a);
        sb.append(", longitude=");
        sb.append(this.b);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
