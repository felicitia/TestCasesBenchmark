package com.salesforce.marketingcloud.messages;

import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.messages.c.a;
import org.apache.commons.math3.geometry.VectorFormat;

final class h extends a {
    private final String a;
    private final String b;

    h(@Nullable String str, @Nullable String str2) {
        this.a = str;
        this.b = str2;
    }

    @Nullable
    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
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
        if (this.a != null ? this.a.equals(aVar.a()) : aVar.a() == null) {
            if (this.b == null) {
                if (aVar.b() == null) {
                    return true;
                }
            } else if (this.b.equals(aVar.b())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.a == null ? 0 : this.a.hashCode()) ^ 1000003) * 1000003;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Media{url=");
        sb.append(this.a);
        sb.append(", altText=");
        sb.append(this.b);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
