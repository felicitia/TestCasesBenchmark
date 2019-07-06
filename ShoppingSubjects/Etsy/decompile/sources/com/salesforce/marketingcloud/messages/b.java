package com.salesforce.marketingcloud.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class b extends e {
    private final String a;
    private final com.salesforce.marketingcloud.location.b b;
    private final int c;
    private final String d;
    private final int e;
    private final int f;
    private final int g;
    private final String h;
    private final String i;
    private final List<c> j;

    static final class a extends com.salesforce.marketingcloud.messages.e.a {
        private String a;
        private com.salesforce.marketingcloud.location.b b;
        private Integer c;
        private String d;
        private Integer e;
        private Integer f;
        private Integer g;
        private String h;
        private String i;
        private List<c> j;

        a() {
        }

        public com.salesforce.marketingcloud.messages.e.a a(int i2) {
            this.c = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a a(com.salesforce.marketingcloud.location.b bVar) {
            if (bVar == null) {
                throw new NullPointerException("Null center");
            }
            this.b = bVar;
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a a(String str) {
            if (str == null) {
                throw new NullPointerException("Null id");
            }
            this.a = str;
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a a(List<c> list) {
            if (list == null) {
                throw new NullPointerException("Null messages");
            }
            this.j = list;
            return this;
        }

        public e a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" id");
                str = sb.toString();
            }
            if (this.b == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" center");
                str = sb2.toString();
            }
            if (this.c == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" radius");
                str = sb3.toString();
            }
            if (this.e == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" major");
                str = sb4.toString();
            }
            if (this.f == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" minor");
                str = sb5.toString();
            }
            if (this.g == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" regionType");
                str = sb6.toString();
            }
            if (this.j == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" messages");
                str = sb7.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Missing required properties:");
                sb8.append(str);
                throw new IllegalStateException(sb8.toString());
            }
            i iVar = new i(this.a, this.b, this.c.intValue(), this.d, this.e.intValue(), this.f.intValue(), this.g.intValue(), this.h, this.i, this.j);
            return iVar;
        }

        public com.salesforce.marketingcloud.messages.e.a b(int i2) {
            this.e = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a b(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a c(int i2) {
            this.f = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a c(@Nullable String str) {
            this.h = str;
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a d(int i2) {
            this.g = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.messages.e.a d(@Nullable String str) {
            this.i = str;
            return this;
        }
    }

    b(String str, com.salesforce.marketingcloud.location.b bVar, int i2, @Nullable String str2, int i3, int i4, int i5, @Nullable String str3, @Nullable String str4, List<c> list) {
        if (str == null) {
            throw new NullPointerException("Null id");
        }
        this.a = str;
        if (bVar == null) {
            throw new NullPointerException("Null center");
        }
        this.b = bVar;
        this.c = i2;
        this.d = str2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = str3;
        this.i = str4;
        if (list == null) {
            throw new NullPointerException("Null messages");
        }
        this.j = list;
    }

    @NonNull
    public String a() {
        return this.a;
    }

    @NonNull
    public com.salesforce.marketingcloud.location.b b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    @Nullable
    public String d() {
        return this.d;
    }

    public int e() {
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
        return this.a.equals(eVar.a()) && this.b.equals(eVar.b()) && this.c == eVar.c() && (this.d != null ? this.d.equals(eVar.d()) : eVar.d() == null) && this.e == eVar.e() && this.f == eVar.f() && this.g == eVar.g() && (this.h != null ? this.h.equals(eVar.h()) : eVar.h() == null) && (this.i != null ? this.i.equals(eVar.i()) : eVar.i() == null) && this.j.equals(eVar.j());
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    @Nullable
    public String h() {
        return this.h;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c) * 1000003) ^ (this.d == null ? 0 : this.d.hashCode())) * 1000003) ^ this.e) * 1000003) ^ this.f) * 1000003) ^ this.g) * 1000003) ^ (this.h == null ? 0 : this.h.hashCode())) * 1000003;
        if (this.i != null) {
            i2 = this.i.hashCode();
        }
        return ((hashCode ^ i2) * 1000003) ^ this.j.hashCode();
    }

    @Nullable
    public String i() {
        return this.i;
    }

    @NonNull
    public List<c> j() {
        return this.j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Region{id=");
        sb.append(this.a);
        sb.append(", center=");
        sb.append(this.b);
        sb.append(", radius=");
        sb.append(this.c);
        sb.append(", proximityUuid=");
        sb.append(this.d);
        sb.append(", major=");
        sb.append(this.e);
        sb.append(", minor=");
        sb.append(this.f);
        sb.append(", regionType=");
        sb.append(this.g);
        sb.append(", name=");
        sb.append(this.h);
        sb.append(", description=");
        sb.append(this.i);
        sb.append(", messages=");
        sb.append(this.j);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
