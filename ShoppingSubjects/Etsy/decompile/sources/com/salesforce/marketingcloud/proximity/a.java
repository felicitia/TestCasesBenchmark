package com.salesforce.marketingcloud.proximity;

import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends e {
    private final String a;
    private final String b;
    private final int c;
    private final int d;

    a(String str, String str2, int i, int i2) {
        if (str == null) {
            throw new NullPointerException("Null id");
        }
        this.a = str;
        if (str2 == null) {
            throw new NullPointerException("Null guid");
        }
        this.b = str2;
        this.c = i;
        this.d = i2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.a.equals(eVar.a()) && this.b.equals(eVar.b()) && this.c == eVar.c() && this.d == eVar.d();
    }

    public int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c) * 1000003) ^ this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BeaconRegion{id=");
        sb.append(this.a);
        sb.append(", guid=");
        sb.append(this.b);
        sb.append(", major=");
        sb.append(this.c);
        sb.append(", minor=");
        sb.append(this.d);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
