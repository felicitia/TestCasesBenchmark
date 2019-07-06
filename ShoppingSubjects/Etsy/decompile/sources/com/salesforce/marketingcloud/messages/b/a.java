package com.salesforce.marketingcloud.messages.b;

import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.location.b;
import com.salesforce.marketingcloud.messages.e;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends b {
    private final b a;
    private final int b;
    private final List<e> c;

    a(b bVar, int i, List<e> list) {
        if (bVar == null) {
            throw new NullPointerException("Null refreshCenter");
        }
        this.a = bVar;
        this.b = i;
        if (list == null) {
            throw new NullPointerException("Null fences");
        }
        this.c = list;
    }

    public b a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    @NonNull
    public List<e> c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a.equals(bVar.a()) && this.b == bVar.b() && this.c.equals(bVar.c());
    }

    public int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GeofenceMessageResponse{refreshCenter=");
        sb.append(this.a);
        sb.append(", refreshRadius=");
        sb.append(this.b);
        sb.append(", fences=");
        sb.append(this.c);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
