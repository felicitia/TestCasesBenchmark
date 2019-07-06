package com.salesforce.marketingcloud.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.messages.c.b;
import java.util.Date;
import java.util.Map;
import org.apache.commons.math3.geometry.VectorFormat;

abstract class a extends c {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final com.salesforce.marketingcloud.messages.c.a e;
    private final Date f;
    private final Date g;
    private final int h;
    private final int i;
    private final String j;
    private final int k;
    private final int l;
    private final int m;
    private final boolean n;
    private final int o;
    private final int p;
    private final String q;
    private final Map<String, String> r;
    private final String s;

    /* renamed from: com.salesforce.marketingcloud.messages.a$a reason: collision with other inner class name */
    static final class C0166a extends b {
        private String a;
        private String b;
        private String c;
        private String d;
        private com.salesforce.marketingcloud.messages.c.a e;
        private Date f;
        private Date g;
        private Integer h;
        private Integer i;
        private String j;
        private Integer k;
        private Integer l;
        private Integer m;
        private Boolean n;
        private Integer o;
        private Integer p;
        private String q;
        private Map<String, String> r;
        private String s;

        C0166a() {
        }

        public b a(int i2) {
            this.h = Integer.valueOf(i2);
            return this;
        }

        public b a(@Nullable com.salesforce.marketingcloud.messages.c.a aVar) {
            this.e = aVar;
            return this;
        }

        public b a(String str) {
            if (str == null) {
                throw new NullPointerException("Null id");
            }
            this.a = str;
            return this;
        }

        public b a(@Nullable Date date) {
            this.f = date;
            return this;
        }

        public b a(@Nullable Map<String, String> map) {
            this.r = map;
            return this;
        }

        public b a(boolean z) {
            this.n = Boolean.valueOf(z);
            return this;
        }

        public c a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" id");
                str = sb.toString();
            }
            if (this.c == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" alert");
                str = sb2.toString();
            }
            if (this.h == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" messageType");
                str = sb3.toString();
            }
            if (this.i == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" contentType");
                str = sb4.toString();
            }
            if (this.k == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" messagesPerPeriod");
                str = sb5.toString();
            }
            if (this.l == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" numberOfPeriods");
                str = sb6.toString();
            }
            if (this.m == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" periodType");
                str = sb7.toString();
            }
            if (this.n == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(" isRollingPeriod");
                str = sb8.toString();
            }
            if (this.o == null) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str);
                sb9.append(" messageLimit");
                str = sb9.toString();
            }
            if (this.p == null) {
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str);
                sb10.append(" proximity");
                str = sb10.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append("Missing required properties:");
                sb11.append(str);
                throw new IllegalStateException(sb11.toString());
            }
            String str2 = this.a;
            String str3 = this.b;
            String str4 = this.c;
            String str5 = this.d;
            com.salesforce.marketingcloud.messages.c.a aVar = this.e;
            Date date = this.f;
            Date date2 = this.g;
            int intValue = this.h.intValue();
            int intValue2 = this.i.intValue();
            String str6 = this.j;
            int intValue3 = this.k.intValue();
            int intValue4 = this.l.intValue();
            int intValue5 = this.m.intValue();
            boolean booleanValue = this.n.booleanValue();
            int intValue6 = this.o.intValue();
            int intValue7 = this.p.intValue();
            String str7 = this.q;
            String str8 = str7;
            g gVar = new g(str2, str3, str4, str5, aVar, date, date2, intValue, intValue2, str6, intValue3, intValue4, intValue5, booleanValue, intValue6, intValue7, str8, this.r, this.s);
            return gVar;
        }

        public b b(int i2) {
            this.i = Integer.valueOf(i2);
            return this;
        }

        public b b(@Nullable String str) {
            this.b = str;
            return this;
        }

        public b b(@Nullable Date date) {
            this.g = date;
            return this;
        }

        public b c(int i2) {
            this.k = Integer.valueOf(i2);
            return this;
        }

        public b c(String str) {
            if (str == null) {
                throw new NullPointerException("Null alert");
            }
            this.c = str;
            return this;
        }

        public b d(int i2) {
            this.l = Integer.valueOf(i2);
            return this;
        }

        public b d(@Nullable String str) {
            this.d = str;
            return this;
        }

        public b e(int i2) {
            this.m = Integer.valueOf(i2);
            return this;
        }

        public b e(@Nullable String str) {
            this.j = str;
            return this;
        }

        public b f(int i2) {
            this.o = Integer.valueOf(i2);
            return this;
        }

        public b f(@Nullable String str) {
            this.q = str;
            return this;
        }

        public b g(int i2) {
            this.p = Integer.valueOf(i2);
            return this;
        }

        public b g(@Nullable String str) {
            this.s = str;
            return this;
        }
    }

    a(String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable com.salesforce.marketingcloud.messages.c.a aVar, @Nullable Date date, @Nullable Date date2, int i2, int i3, @Nullable String str5, int i4, int i5, int i6, boolean z, int i7, int i8, @Nullable String str6, @Nullable Map<String, String> map, @Nullable String str7) {
        String str8 = str;
        String str9 = str3;
        if (str8 == null) {
            throw new NullPointerException("Null id");
        }
        this.a = str8;
        this.b = str2;
        if (str9 == null) {
            throw new NullPointerException("Null alert");
        }
        this.c = str9;
        this.d = str4;
        this.e = aVar;
        this.f = date;
        this.g = date2;
        this.h = i2;
        this.i = i3;
        this.j = str5;
        this.k = i4;
        this.l = i5;
        this.m = i6;
        this.n = z;
        this.o = i7;
        this.p = i8;
        this.q = str6;
        this.r = map;
        this.s = str7;
    }

    @NonNull
    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    @NonNull
    public String c() {
        return this.c;
    }

    @Nullable
    public String d() {
        return this.d;
    }

    @Nullable
    public com.salesforce.marketingcloud.messages.c.a e() {
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
        if (this.a.equals(cVar.a()) && (this.b != null ? this.b.equals(cVar.b()) : cVar.b() == null) && this.c.equals(cVar.c()) && (this.d != null ? this.d.equals(cVar.d()) : cVar.d() == null) && (this.e != null ? this.e.equals(cVar.e()) : cVar.e() == null) && (this.f != null ? this.f.equals(cVar.f()) : cVar.f() == null) && (this.g != null ? this.g.equals(cVar.g()) : cVar.g() == null) && this.h == cVar.h() && this.i == cVar.i() && (this.j != null ? this.j.equals(cVar.j()) : cVar.j() == null) && this.k == cVar.k() && this.l == cVar.l() && this.m == cVar.m() && this.n == cVar.n() && this.o == cVar.o() && this.p == cVar.p() && (this.q != null ? this.q.equals(cVar.q()) : cVar.q() == null) && (this.r != null ? this.r.equals(cVar.r()) : cVar.r() == null)) {
            if (this.s == null) {
                if (cVar.s() == null) {
                    return true;
                }
            } else if (this.s.equals(cVar.s())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Date f() {
        return this.f;
    }

    @Nullable
    public Date g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((((((((((((((((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ (this.b == null ? 0 : this.b.hashCode())) * 1000003) ^ this.c.hashCode()) * 1000003) ^ (this.d == null ? 0 : this.d.hashCode())) * 1000003) ^ (this.e == null ? 0 : this.e.hashCode())) * 1000003) ^ (this.f == null ? 0 : this.f.hashCode())) * 1000003) ^ (this.g == null ? 0 : this.g.hashCode())) * 1000003) ^ this.h) * 1000003) ^ this.i) * 1000003) ^ (this.j == null ? 0 : this.j.hashCode())) * 1000003) ^ this.k) * 1000003) ^ this.l) * 1000003) ^ this.m) * 1000003) ^ (this.n ? 1231 : 1237)) * 1000003) ^ this.o) * 1000003) ^ this.p) * 1000003) ^ (this.q == null ? 0 : this.q.hashCode())) * 1000003) ^ (this.r == null ? 0 : this.r.hashCode())) * 1000003;
        if (this.s != null) {
            i2 = this.s.hashCode();
        }
        return hashCode ^ i2;
    }

    public int i() {
        return this.i;
    }

    @Nullable
    public String j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    public int m() {
        return this.m;
    }

    public boolean n() {
        return this.n;
    }

    public int o() {
        return this.o;
    }

    public int p() {
        return this.p;
    }

    @Nullable
    public String q() {
        return this.q;
    }

    @Nullable
    public Map<String, String> r() {
        return this.r;
    }

    @Nullable
    public String s() {
        return this.s;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message{id=");
        sb.append(this.a);
        sb.append(", title=");
        sb.append(this.b);
        sb.append(", alert=");
        sb.append(this.c);
        sb.append(", sound=");
        sb.append(this.d);
        sb.append(", media=");
        sb.append(this.e);
        sb.append(", startDateUtc=");
        sb.append(this.f);
        sb.append(", endDateUtc=");
        sb.append(this.g);
        sb.append(", messageType=");
        sb.append(this.h);
        sb.append(", contentType=");
        sb.append(this.i);
        sb.append(", url=");
        sb.append(this.j);
        sb.append(", messagesPerPeriod=");
        sb.append(this.k);
        sb.append(", numberOfPeriods=");
        sb.append(this.l);
        sb.append(", periodType=");
        sb.append(this.m);
        sb.append(", isRollingPeriod=");
        sb.append(this.n);
        sb.append(", messageLimit=");
        sb.append(this.o);
        sb.append(", proximity=");
        sb.append(this.p);
        sb.append(", openDirect=");
        sb.append(this.q);
        sb.append(", customKeys=");
        sb.append(this.r);
        sb.append(", custom=");
        sb.append(this.s);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
