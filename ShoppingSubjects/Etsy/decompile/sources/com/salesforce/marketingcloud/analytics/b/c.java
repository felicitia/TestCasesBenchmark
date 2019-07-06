package com.salesforce.marketingcloud.analytics.b;

import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class c extends a {
    private final boolean a;
    private final List<String> b;

    c(boolean z, List<String> list) {
        this.a = z;
        if (list == null) {
            throw new NullPointerException("Null objectIds");
        }
        this.b = list;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public List<String> b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.a == aVar.a() && this.b.equals(aVar.b());
    }

    public int hashCode() {
        return (((this.a ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventDetails{openFromPush=");
        sb.append(this.a);
        sb.append(", objectIds=");
        sb.append(this.b);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
