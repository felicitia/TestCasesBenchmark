package com.salesforce.marketingcloud.messages.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.messages.a.b.C0168b;
import java.util.Date;
import java.util.Map;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends b {
    private final String a;
    private final String b;
    private final String c;
    private final Map<String, String> d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final com.salesforce.marketingcloud.messages.a.b.a i;
    private final String j;
    private final Date k;
    private final Date l;
    private final int m;
    private final int n;
    private final String o;

    /* renamed from: com.salesforce.marketingcloud.messages.a.a$a reason: collision with other inner class name */
    static final class C0167a extends C0168b {
        private String a;
        private String b;
        private String c;
        private Map<String, String> d;
        private String e;
        private String f;
        private String g;
        private String h;
        private com.salesforce.marketingcloud.messages.a.b.a i;
        private String j;
        private Date k;
        private Date l;
        private Integer m;
        private Integer n;
        private String o;

        C0167a() {
        }

        public C0168b a(int i2) {
            this.m = Integer.valueOf(i2);
            return this;
        }

        public C0168b a(@Nullable com.salesforce.marketingcloud.messages.a.b.a aVar) {
            this.i = aVar;
            return this;
        }

        public C0168b a(@Nullable String str) {
            this.a = str;
            return this;
        }

        public C0168b a(@Nullable Date date) {
            this.k = date;
            return this;
        }

        public C0168b a(@Nullable Map<String, String> map) {
            this.d = map;
            return this;
        }

        public b a() {
            String str = "";
            if (this.j == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" id");
                str = sb.toString();
            }
            if (this.m == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" messageType");
                str = sb2.toString();
            }
            if (this.n == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" contentType");
                str = sb3.toString();
            }
            if (this.o == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" url");
                str = sb4.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Missing required properties:");
                sb5.append(str);
                throw new IllegalStateException(sb5.toString());
            }
            d dVar = new d(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m.intValue(), this.n.intValue(), this.o);
            return dVar;
        }

        public C0168b b(int i2) {
            this.n = Integer.valueOf(i2);
            return this;
        }

        public C0168b b(@Nullable String str) {
            this.b = str;
            return this;
        }

        public C0168b b(@Nullable Date date) {
            this.l = date;
            return this;
        }

        public C0168b c(@Nullable String str) {
            this.c = str;
            return this;
        }

        public C0168b d(@Nullable String str) {
            this.e = str;
            return this;
        }

        public C0168b e(@Nullable String str) {
            this.f = str;
            return this;
        }

        public C0168b f(@Nullable String str) {
            this.g = str;
            return this;
        }

        public C0168b g(@Nullable String str) {
            this.h = str;
            return this;
        }

        public C0168b h(String str) {
            if (str == null) {
                throw new NullPointerException("Null id");
            }
            this.j = str;
            return this;
        }

        public C0168b i(String str) {
            if (str == null) {
                throw new NullPointerException("Null url");
            }
            this.o = str;
            return this;
        }
    }

    a(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Map<String, String> map, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable com.salesforce.marketingcloud.messages.a.b.a aVar, String str8, @Nullable Date date, @Nullable Date date2, int i2, int i3, String str9) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = map;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = aVar;
        if (str8 == null) {
            throw new NullPointerException("Null id");
        }
        this.j = str8;
        this.k = date;
        this.l = date2;
        this.m = i2;
        this.n = i3;
        if (str9 == null) {
            throw new NullPointerException("Null url");
        }
        this.o = str9;
    }

    @Nullable
    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    @Nullable
    public String c() {
        return this.c;
    }

    @Nullable
    public Map<String, String> d() {
        return this.d;
    }

    @Nullable
    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != null ? this.a.equals(bVar.a()) : bVar.a() == null) {
            if (this.b != null ? this.b.equals(bVar.b()) : bVar.b() == null) {
                if (this.c != null ? this.c.equals(bVar.c()) : bVar.c() == null) {
                    if (this.d != null ? this.d.equals(bVar.d()) : bVar.d() == null) {
                        if (this.e != null ? this.e.equals(bVar.e()) : bVar.e() == null) {
                            if (this.f != null ? this.f.equals(bVar.f()) : bVar.f() == null) {
                                if (this.g != null ? this.g.equals(bVar.g()) : bVar.g() == null) {
                                    if (this.h != null ? this.h.equals(bVar.h()) : bVar.h() == null) {
                                        if (this.i != null ? this.i.equals(bVar.p()) : bVar.p() == null) {
                                            if (this.j.equals(bVar.j()) && (this.k != null ? this.k.equals(bVar.k()) : bVar.k() == null) && (this.l != null ? this.l.equals(bVar.l()) : bVar.l() == null) && this.m == bVar.m() && this.n == bVar.n() && this.o.equals(bVar.o())) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public String f() {
        return this.f;
    }

    @Nullable
    public String g() {
        return this.g;
    }

    @Nullable
    public String h() {
        return this.h;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = ((((((((((((((((((((((this.a == null ? 0 : this.a.hashCode()) ^ 1000003) * 1000003) ^ (this.b == null ? 0 : this.b.hashCode())) * 1000003) ^ (this.c == null ? 0 : this.c.hashCode())) * 1000003) ^ (this.d == null ? 0 : this.d.hashCode())) * 1000003) ^ (this.e == null ? 0 : this.e.hashCode())) * 1000003) ^ (this.f == null ? 0 : this.f.hashCode())) * 1000003) ^ (this.g == null ? 0 : this.g.hashCode())) * 1000003) ^ (this.h == null ? 0 : this.h.hashCode())) * 1000003) ^ (this.i == null ? 0 : this.i.hashCode())) * 1000003) ^ this.j.hashCode()) * 1000003) ^ (this.k == null ? 0 : this.k.hashCode())) * 1000003;
        if (this.l != null) {
            i2 = this.l.hashCode();
        }
        return ((((((hashCode ^ i2) * 1000003) ^ this.m) * 1000003) ^ this.n) * 1000003) ^ this.o.hashCode();
    }

    @Nullable
    /* renamed from: i */
    public com.salesforce.marketingcloud.messages.a.b.a p() {
        return this.i;
    }

    @NonNull
    public String j() {
        return this.j;
    }

    @Nullable
    public Date k() {
        return this.k;
    }

    @Nullable
    public Date l() {
        return this.l;
    }

    public int m() {
        return this.m;
    }

    public int n() {
        return this.n;
    }

    @NonNull
    public String o() {
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CloudPageMessage{requestId=");
        sb.append(this.a);
        sb.append(", messageHash=");
        sb.append(this.b);
        sb.append(", subject=");
        sb.append(this.c);
        sb.append(", customKeys=");
        sb.append(this.d);
        sb.append(", custom=");
        sb.append(this.e);
        sb.append(", title=");
        sb.append(this.f);
        sb.append(", alert=");
        sb.append(this.g);
        sb.append(", sound=");
        sb.append(this.h);
        sb.append(", media=");
        sb.append(this.i);
        sb.append(", id=");
        sb.append(this.j);
        sb.append(", startDateUtc=");
        sb.append(this.k);
        sb.append(", endDateUtc=");
        sb.append(this.l);
        sb.append(", messageType=");
        sb.append(this.m);
        sb.append(", contentType=");
        sb.append(this.n);
        sb.append(", url=");
        sb.append(this.o);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
