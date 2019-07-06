package com.salesforce.marketingcloud.registration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends c {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final String i;
    private final boolean j;
    private final int k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;
    private final Set<String> q;
    private final Map<String, String> r;

    /* renamed from: com.salesforce.marketingcloud.registration.a$a reason: collision with other inner class name */
    static final class C0174a extends com.salesforce.marketingcloud.registration.c.a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private Boolean f;
        private Boolean g;
        private Boolean h;
        private String i;
        private Boolean j;
        private Integer k;
        private String l;
        private String m;
        private String n;
        private String o;
        private String p;
        private Set<String> q;
        private Map<String, String> r;

        C0174a() {
        }

        public com.salesforce.marketingcloud.registration.c.a a(int i2) {
            this.k = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a a(@Nullable String str) {
            this.a = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a a(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null attributeMap");
            }
            this.r = map;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a a(Set<String> set) {
            if (set == null) {
                throw new NullPointerException("Null tags");
            }
            this.q = set;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a a(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public c a() {
            String str = "";
            if (this.b == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" deviceId");
                str = sb.toString();
            }
            if (this.d == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" sdkVersion");
                str = sb2.toString();
            }
            if (this.e == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" appVersion");
                str = sb3.toString();
            }
            if (this.f == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" dst");
                str = sb4.toString();
            }
            if (this.g == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" locationEnabled");
                str = sb5.toString();
            }
            if (this.h == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" proximityEnabled");
                str = sb6.toString();
            }
            if (this.i == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" platformVersion");
                str = sb7.toString();
            }
            if (this.j == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(" pushEnabled");
                str = sb8.toString();
            }
            if (this.k == null) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str);
                sb9.append(" timeZone");
                str = sb9.toString();
            }
            if (this.m == null) {
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str);
                sb10.append(" platform");
                str = sb10.toString();
            }
            if (this.n == null) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(str);
                sb11.append(" hwid");
                str = sb11.toString();
            }
            if (this.o == null) {
                StringBuilder sb12 = new StringBuilder();
                sb12.append(str);
                sb12.append(" appId");
                str = sb12.toString();
            }
            if (this.p == null) {
                StringBuilder sb13 = new StringBuilder();
                sb13.append(str);
                sb13.append(" locale");
                str = sb13.toString();
            }
            if (this.q == null) {
                StringBuilder sb14 = new StringBuilder();
                sb14.append(str);
                sb14.append(" tags");
                str = sb14.toString();
            }
            if (this.r == null) {
                StringBuilder sb15 = new StringBuilder();
                sb15.append(str);
                sb15.append(" attributeMap");
                str = sb15.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb16 = new StringBuilder();
                sb16.append("Missing required properties:");
                sb16.append(str);
                throw new IllegalStateException(sb16.toString());
            }
            String str2 = this.a;
            String str3 = this.b;
            String str4 = this.c;
            String str5 = this.d;
            String str6 = this.e;
            boolean booleanValue = this.f.booleanValue();
            boolean booleanValue2 = this.g.booleanValue();
            boolean booleanValue3 = this.h.booleanValue();
            String str7 = this.i;
            boolean booleanValue4 = this.j.booleanValue();
            int intValue = this.k.intValue();
            String str8 = this.l;
            String str9 = this.m;
            String str10 = str9;
            String str11 = str10;
            f fVar = new f(str2, str3, str4, str5, str6, booleanValue, booleanValue2, booleanValue3, str7, booleanValue4, intValue, str8, str11, this.n, this.o, this.p, this.q, this.r);
            return fVar;
        }

        public com.salesforce.marketingcloud.registration.c.a b(String str) {
            if (str == null) {
                throw new NullPointerException("Null deviceId");
            }
            this.b = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a b(boolean z) {
            this.g = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a c(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a c(boolean z) {
            this.h = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a d(String str) {
            if (str == null) {
                throw new NullPointerException("Null sdkVersion");
            }
            this.d = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a d(boolean z) {
            this.j = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a e(String str) {
            if (str == null) {
                throw new NullPointerException("Null appVersion");
            }
            this.e = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a f(String str) {
            if (str == null) {
                throw new NullPointerException("Null platformVersion");
            }
            this.i = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a g(@Nullable String str) {
            this.l = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a h(String str) {
            if (str == null) {
                throw new NullPointerException("Null platform");
            }
            this.m = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a i(String str) {
            if (str == null) {
                throw new NullPointerException("Null hwid");
            }
            this.n = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a j(String str) {
            if (str == null) {
                throw new NullPointerException("Null appId");
            }
            this.o = str;
            return this;
        }

        public com.salesforce.marketingcloud.registration.c.a k(String str) {
            if (str == null) {
                throw new NullPointerException("Null locale");
            }
            this.p = str;
            return this;
        }
    }

    a(@Nullable String str, String str2, @Nullable String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, boolean z4, int i2, @Nullable String str7, String str8, String str9, String str10, String str11, Set<String> set, Map<String, String> map) {
        String str12 = str2;
        String str13 = str4;
        String str14 = str5;
        String str15 = str6;
        String str16 = str8;
        String str17 = str9;
        String str18 = str10;
        String str19 = str11;
        Set<String> set2 = set;
        Map<String, String> map2 = map;
        this.a = str;
        if (str12 == null) {
            throw new NullPointerException("Null deviceId");
        }
        this.b = str12;
        this.c = str3;
        if (str13 == null) {
            throw new NullPointerException("Null sdkVersion");
        }
        this.d = str13;
        if (str14 == null) {
            throw new NullPointerException("Null appVersion");
        }
        this.e = str14;
        this.f = z;
        this.g = z2;
        this.h = z3;
        if (str15 == null) {
            throw new NullPointerException("Null platformVersion");
        }
        this.i = str15;
        this.j = z4;
        this.k = i2;
        this.l = str7;
        if (str16 == null) {
            throw new NullPointerException("Null platform");
        }
        this.m = str16;
        if (str17 == null) {
            throw new NullPointerException("Null hwid");
        }
        this.n = str17;
        if (str18 == null) {
            throw new NullPointerException("Null appId");
        }
        this.o = str18;
        if (str19 == null) {
            throw new NullPointerException("Null locale");
        }
        this.p = str19;
        if (set2 == null) {
            throw new NullPointerException("Null tags");
        }
        this.q = set2;
        if (map2 == null) {
            throw new NullPointerException("Null attributeMap");
        }
        this.r = map2;
    }

    @Nullable
    public String a() {
        return this.a;
    }

    @NonNull
    public String b() {
        return this.b;
    }

    @Nullable
    public String c() {
        return this.c;
    }

    @NonNull
    public String d() {
        return this.d;
    }

    @NonNull
    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a != null ? this.a.equals(cVar.a()) : cVar.a() == null) {
            if (this.b.equals(cVar.b()) && (this.c != null ? this.c.equals(cVar.c()) : cVar.c() == null) && this.d.equals(cVar.d()) && this.e.equals(cVar.e()) && this.f == cVar.f() && this.g == cVar.g() && this.h == cVar.h() && this.i.equals(cVar.i()) && this.j == cVar.j() && this.k == cVar.k() && (this.l != null ? this.l.equals(cVar.l()) : cVar.l() == null) && this.m.equals(cVar.m()) && this.n.equals(cVar.n()) && this.o.equals(cVar.o()) && this.p.equals(cVar.p()) && this.q.equals(cVar.q()) && this.r.equals(cVar.r())) {
                return true;
            }
        }
        return false;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 1237;
        int hashCode = ((((((((((((((((((this.a == null ? 0 : this.a.hashCode()) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ (this.c == null ? 0 : this.c.hashCode())) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ (this.f ? 1231 : 1237)) * 1000003) ^ (this.g ? 1231 : 1237)) * 1000003) ^ (this.h ? 1231 : 1237)) * 1000003) ^ this.i.hashCode()) * 1000003;
        if (this.j) {
            i3 = 1231;
        }
        int i4 = (((hashCode ^ i3) * 1000003) ^ this.k) * 1000003;
        if (this.l != null) {
            i2 = this.l.hashCode();
        }
        return ((((((((((((i4 ^ i2) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode();
    }

    @NonNull
    public String i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    @Nullable
    public String l() {
        return this.l;
    }

    @NonNull
    public String m() {
        return this.m;
    }

    @NonNull
    public String n() {
        return this.n;
    }

    @NonNull
    public String o() {
        return this.o;
    }

    @NonNull
    public String p() {
        return this.p;
    }

    @NonNull
    public Set<String> q() {
        return this.q;
    }

    @NonNull
    public Map<String, String> r() {
        return this.r;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registration{signedString=");
        sb.append(this.a);
        sb.append(", deviceId=");
        sb.append(this.b);
        sb.append(", systemToken=");
        sb.append(this.c);
        sb.append(", sdkVersion=");
        sb.append(this.d);
        sb.append(", appVersion=");
        sb.append(this.e);
        sb.append(", dst=");
        sb.append(this.f);
        sb.append(", locationEnabled=");
        sb.append(this.g);
        sb.append(", proximityEnabled=");
        sb.append(this.h);
        sb.append(", platformVersion=");
        sb.append(this.i);
        sb.append(", pushEnabled=");
        sb.append(this.j);
        sb.append(", timeZone=");
        sb.append(this.k);
        sb.append(", contactKey=");
        sb.append(this.l);
        sb.append(", platform=");
        sb.append(this.m);
        sb.append(", hwid=");
        sb.append(this.n);
        sb.append(", appId=");
        sb.append(this.o);
        sb.append(", locale=");
        sb.append(this.p);
        sb.append(", tags=");
        sb.append(this.q);
        sb.append(", attributeMap=");
        sb.append(this.r);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
