package com.salesforce.marketingcloud.registration;

import android.support.annotation.NonNull;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.salesforce.marketingcloud.registration.$AutoValue_Attribute reason: invalid class name */
abstract class C$AutoValue_Attribute extends Attribute {
    private final String a;
    private final String b;

    C$AutoValue_Attribute(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Null key");
        }
        this.a = str;
        if (str2 == null) {
            throw new NullPointerException("Null value");
        }
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        return this.a.equals(attribute.key()) && this.b.equals(attribute.value());
    }

    public int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    @NonNull
    public String key() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Attribute{key=");
        sb.append(this.a);
        sb.append(", value=");
        sb.append(this.b);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    @NonNull
    public String value() {
        return this.b;
    }
}
