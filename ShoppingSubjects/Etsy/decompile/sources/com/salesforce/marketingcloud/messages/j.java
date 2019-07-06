package com.salesforce.marketingcloud.messages;

import org.apache.commons.math3.geometry.VectorFormat;

final class j extends m {
    private final String a;
    private final String b;

    j(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Null regionId");
        }
        this.a = str;
        if (str2 == null) {
            throw new NullPointerException("Null messageId");
        }
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.a.equals(mVar.a()) && this.b.equals(mVar.b());
    }

    public int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegionMessage{regionId=");
        sb.append(this.a);
        sb.append(", messageId=");
        sb.append(this.b);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
