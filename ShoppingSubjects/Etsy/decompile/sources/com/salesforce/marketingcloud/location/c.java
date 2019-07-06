package com.salesforce.marketingcloud.location;

import org.apache.commons.math3.geometry.VectorFormat;

final class c extends f {
    private final String a;
    private final float b;
    private final double c;
    private final double d;
    private final int e;

    c(String str, float f, double d2, double d3, int i) {
        if (str == null) {
            throw new NullPointerException("Null id");
        }
        this.a = str;
        this.b = f;
        this.c = d2;
        this.d = d3;
        this.e = i;
    }

    public String a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    public double c() {
        return this.c;
    }

    public double d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.a.equals(fVar.a()) && Float.floatToIntBits(this.b) == Float.floatToIntBits(fVar.b()) && Double.doubleToLongBits(this.c) == Double.doubleToLongBits(fVar.c()) && Double.doubleToLongBits(this.d) == Double.doubleToLongBits(fVar.d()) && this.e == fVar.e();
    }

    public int hashCode() {
        return ((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.b)) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.c) >>> 32) ^ Double.doubleToLongBits(this.c)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.d) >>> 32) ^ Double.doubleToLongBits(this.d)))) * 1000003) ^ this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GeofenceRegion{id=");
        sb.append(this.a);
        sb.append(", radius=");
        sb.append(this.b);
        sb.append(", latitude=");
        sb.append(this.c);
        sb.append(", longitude=");
        sb.append(this.d);
        sb.append(", transitionTypes=");
        sb.append(this.e);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
