package com.salesforce.marketingcloud.c;

import android.support.annotation.Nullable;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends g {
    private final String a;
    private final String b;
    private final int c;
    private final long d;
    private final long e;
    private final Map<String, List<String>> f;

    /* renamed from: com.salesforce.marketingcloud.c.a$a reason: collision with other inner class name */
    static final class C0162a extends com.salesforce.marketingcloud.c.g.a {
        private String a;
        private String b;
        private Integer c;
        private Long d;
        private Long e;
        private Map<String, List<String>> f;

        C0162a() {
        }

        public com.salesforce.marketingcloud.c.g.a a(int i) {
            this.c = Integer.valueOf(i);
            return this;
        }

        public com.salesforce.marketingcloud.c.g.a a(long j) {
            this.d = Long.valueOf(j);
            return this;
        }

        public com.salesforce.marketingcloud.c.g.a a(@Nullable String str) {
            this.a = str;
            return this;
        }

        public com.salesforce.marketingcloud.c.g.a a(@Nullable Map<String, List<String>> map) {
            this.f = map;
            return this;
        }

        public g a() {
            String str = "";
            if (this.c == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" code");
                str = sb.toString();
            }
            if (this.d == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" startTimeMillis");
                str = sb2.toString();
            }
            if (this.e == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" endTimeMillis");
                str = sb3.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Missing required properties:");
                sb4.append(str);
                throw new IllegalStateException(sb4.toString());
            }
            c cVar = new c(this.a, this.b, this.c.intValue(), this.d.longValue(), this.e.longValue(), this.f);
            return cVar;
        }

        public com.salesforce.marketingcloud.c.g.a b(long j) {
            this.e = Long.valueOf(j);
            return this;
        }

        public com.salesforce.marketingcloud.c.g.a b(@Nullable String str) {
            this.b = str;
            return this;
        }
    }

    a(@Nullable String str, @Nullable String str2, int i, long j, long j2, @Nullable Map<String, List<String>> map) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = j;
        this.e = j2;
        this.f = map;
    }

    @Nullable
    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.a != null ? this.a.equals(gVar.a()) : gVar.a() == null) {
            if (this.b != null ? this.b.equals(gVar.b()) : gVar.b() == null) {
                if (this.c == gVar.c() && this.d == gVar.d() && this.e == gVar.e()) {
                    if (this.f == null) {
                        if (gVar.f() == null) {
                            return true;
                        }
                    } else if (this.f.equals(gVar.f())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public Map<String, List<String>> f() {
        return this.f;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((this.a == null ? 0 : this.a.hashCode()) ^ 1000003) * 1000003) ^ (this.b == null ? 0 : this.b.hashCode())) * 1000003) ^ this.c) * 1000003) ^ ((int) ((this.d >>> 32) ^ this.d))) * 1000003) ^ ((int) ((this.e >>> 32) ^ this.e))) * 1000003;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response{body=");
        sb.append(this.a);
        sb.append(", message=");
        sb.append(this.b);
        sb.append(", code=");
        sb.append(this.c);
        sb.append(", startTimeMillis=");
        sb.append(this.d);
        sb.append(", endTimeMillis=");
        sb.append(this.e);
        sb.append(", headers=");
        sb.append(this.f);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
