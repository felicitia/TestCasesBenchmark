package com.salesforce.marketingcloud.analytics.b;

import java.util.Date;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class b extends i {
    private final String a;
    private final String b;
    private final Date c;
    private final a d;

    b(String str, String str2, Date date, a aVar) {
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
        if (aVar == null) {
            throw new NullPointerException("Null details");
        }
        this.d = aVar;
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

    public a d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.a.equals(iVar.a()) && this.b.equals(iVar.b()) && this.c.equals(iVar.c()) && this.d.equals(iVar.d());
    }

    public int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PiOpenEvent{apiEndpoint=");
        sb.append(this.a);
        sb.append(", eventName=");
        sb.append(this.b);
        sb.append(", timestamp=");
        sb.append(this.c);
        sb.append(", details=");
        sb.append(this.d);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
