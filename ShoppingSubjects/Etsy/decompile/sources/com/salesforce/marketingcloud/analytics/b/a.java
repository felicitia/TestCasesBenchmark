package com.salesforce.marketingcloud.analytics.b;

import java.util.Date;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends g {
    private final String a;
    private final String b;
    private final Date c;

    a(String str, String str2, Date date) {
        if (str == null) {
            throw new NullPointerException("Null apiEndpoint");
        }
        this.a = str;
        if (str2 == null) {
            throw new NullPointerException("Null eventName");
        }
        this.b = str2;
        if (date == null) {
            throw new NullPointerException("Null timestamp");
        }
        this.c = date;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Date c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.a.equals(gVar.a()) && this.b.equals(gVar.b()) && this.c.equals(gVar.c());
    }

    public int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PiCloseEvent{apiEndpoint=");
        sb.append(this.a);
        sb.append(", eventName=");
        sb.append(this.b);
        sb.append(", timestamp=");
        sb.append(this.c);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
