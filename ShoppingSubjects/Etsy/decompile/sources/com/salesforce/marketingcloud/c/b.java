package com.salesforce.marketingcloud.c;

import android.support.annotation.Nullable;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

final class b extends e {
    private final String a;
    private final String b;
    private final long c;
    private final String d;
    private final boolean e;
    private final String f;
    private final List<String> g;
    private final d h;

    static final class a extends com.salesforce.marketingcloud.c.e.a {
        private String a;
        private String b;
        private Long c;
        private String d;
        private Boolean e;
        private String f;
        private List<String> g;
        private d h;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.c.e.a a(long j) {
            this.c = Long.valueOf(j);
            return this;
        }

        public com.salesforce.marketingcloud.c.e.a a(d dVar) {
            if (dVar == null) {
                throw new NullPointerException("Null requestId");
            }
            this.h = dVar;
            return this;
        }

        public com.salesforce.marketingcloud.c.e.a a(String str) {
            if (str == null) {
                throw new NullPointerException("Null method");
            }
            this.a = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.c.e.a a(List<String> list) {
            if (list == null) {
                throw new NullPointerException("Null headers");
            }
            this.g = list;
            return this;
        }

        public com.salesforce.marketingcloud.c.e.a a(boolean z) {
            this.e = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public String a() {
            return this.b;
        }

        public com.salesforce.marketingcloud.c.e.a b(@Nullable String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public e b() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" method");
                str = sb.toString();
            }
            if (this.c == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" connectionTimeout");
                str = sb2.toString();
            }
            if (this.d == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" contentType");
                str = sb3.toString();
            }
            if (this.e == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" gzipRequest");
                str = sb4.toString();
            }
            if (this.f == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" url");
                str = sb5.toString();
            }
            if (this.g == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" headers");
                str = sb6.toString();
            }
            if (this.h == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" requestId");
                str = sb7.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Missing required properties:");
                sb8.append(str);
                throw new IllegalStateException(sb8.toString());
            }
            b bVar = new b(this.a, this.b, this.c.longValue(), this.d, this.e.booleanValue(), this.f, this.g, this.h);
            return bVar;
        }

        public com.salesforce.marketingcloud.c.e.a c(String str) {
            if (str == null) {
                throw new NullPointerException("Null contentType");
            }
            this.d = str;
            return this;
        }

        public com.salesforce.marketingcloud.c.e.a d(String str) {
            if (str == null) {
                throw new NullPointerException("Null url");
            }
            this.f = str;
            return this;
        }
    }

    private b(String str, @Nullable String str2, long j, String str3, boolean z, String str4, List<String> list, d dVar) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = str3;
        this.e = z;
        this.f = str4;
        this.g = list;
        this.h = dVar;
    }

    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.a.equals(eVar.a()) && (this.b != null ? this.b.equals(eVar.b()) : eVar.b() == null) && this.c == eVar.c() && this.d.equals(eVar.d()) && this.e == eVar.e() && this.f.equals(eVar.f()) && this.g.equals(eVar.g()) && this.h.equals(eVar.h());
    }

    public String f() {
        return this.f;
    }

    public List<String> g() {
        return this.g;
    }

    public d h() {
        return this.h;
    }

    public int hashCode() {
        return ((((((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ (this.b == null ? 0 : this.b.hashCode())) * 1000003) ^ ((int) ((this.c >>> 32) ^ this.c))) * 1000003) ^ this.d.hashCode()) * 1000003) ^ (this.e ? 1231 : 1237)) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.a);
        sb.append(", requestBody=");
        sb.append(this.b);
        sb.append(", connectionTimeout=");
        sb.append(this.c);
        sb.append(", contentType=");
        sb.append(this.d);
        sb.append(", gzipRequest=");
        sb.append(this.e);
        sb.append(", url=");
        sb.append(this.f);
        sb.append(", headers=");
        sb.append(this.g);
        sb.append(", requestId=");
        sb.append(this.h);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
