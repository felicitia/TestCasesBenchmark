package com.salesforce.marketingcloud;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.notifications.c.C0173c;
import com.salesforce.marketingcloud.notifications.c.b;
import org.apache.commons.math3.geometry.VectorFormat;

final class f extends b {
    private final String a;
    private final String b;
    private final String c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final int i;
    private final String j;
    private final Class<? extends Activity> k;
    private final Class<? extends Activity> l;
    private final Class<? extends Activity> m;
    private final com.salesforce.marketingcloud.notifications.c.a n;
    private final C0173c o;
    private final b p;
    private final String q;

    static final class a extends com.salesforce.marketingcloud.b.a {
        private String a;
        private String b;
        private String c;
        private Boolean d;
        private Boolean e;
        private Boolean f;
        private Boolean g;
        private Boolean h;
        private Integer i;
        private String j;
        private Class<? extends Activity> k;
        private Class<? extends Activity> l;
        private Class<? extends Activity> m;
        private com.salesforce.marketingcloud.notifications.c.a n;
        private C0173c o;
        private b p;
        private String q;

        a() {
        }

        public com.salesforce.marketingcloud.b.a a(int i2) {
            this.i = Integer.valueOf(i2);
            return this;
        }

        public com.salesforce.marketingcloud.b.a a(@Nullable com.salesforce.marketingcloud.notifications.c.a aVar) {
            this.n = aVar;
            return this;
        }

        public com.salesforce.marketingcloud.b.a a(@Nullable C0173c cVar) {
            this.o = cVar;
            return this;
        }

        public com.salesforce.marketingcloud.b.a a(String str) {
            if (str == null) {
                throw new NullPointerException("Null applicationId");
            }
            this.a = str;
            return this;
        }

        public com.salesforce.marketingcloud.b.a a(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" applicationId");
                str = sb.toString();
            }
            if (this.b == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" accessToken");
                str = sb2.toString();
            }
            if (this.d == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" analyticsEnabled");
                str = sb3.toString();
            }
            if (this.e == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" piAnalyticsEnabled");
                str = sb4.toString();
            }
            if (this.f == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" geofencingEnabled");
                str = sb5.toString();
            }
            if (this.g == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" proximityEnabled");
                str = sb6.toString();
            }
            if (this.h == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" inboxEnabled");
                str = sb7.toString();
            }
            if (this.i == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(" notificationSmallIconResId");
                str = sb8.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append("Missing required properties:");
                sb9.append(str);
                throw new IllegalStateException(sb9.toString());
            }
            String str2 = this.a;
            String str3 = this.b;
            String str4 = this.c;
            boolean booleanValue = this.d.booleanValue();
            boolean booleanValue2 = this.e.booleanValue();
            boolean booleanValue3 = this.f.booleanValue();
            boolean booleanValue4 = this.g.booleanValue();
            boolean booleanValue5 = this.h.booleanValue();
            int intValue = this.i.intValue();
            String str5 = this.j;
            Class<? extends Activity> cls = this.k;
            Class<? extends Activity> cls2 = this.l;
            Class<? extends Activity> cls3 = this.m;
            Class<? extends Activity> cls4 = cls3;
            Class<? extends Activity> cls5 = cls4;
            f fVar = new f(str2, str3, str4, booleanValue, booleanValue2, booleanValue3, booleanValue4, booleanValue5, intValue, str5, cls, cls2, cls5, this.n, this.o, this.p, this.q);
            return fVar;
        }

        public com.salesforce.marketingcloud.b.a b(String str) {
            if (str == null) {
                throw new NullPointerException("Null accessToken");
            }
            this.b = str;
            return this;
        }

        public com.salesforce.marketingcloud.b.a b(boolean z) {
            this.e = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.b.a c(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.salesforce.marketingcloud.b.a c(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.b.a d(@Nullable String str) {
            this.q = str;
            return this;
        }

        public com.salesforce.marketingcloud.b.a d(boolean z) {
            this.g = Boolean.valueOf(z);
            return this;
        }

        public com.salesforce.marketingcloud.b.a f(boolean z) {
            this.h = Boolean.valueOf(z);
            return this;
        }
    }

    private f(String str, String str2, @Nullable String str3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2, @Nullable String str4, @Nullable Class<? extends Activity> cls, @Nullable Class<? extends Activity> cls2, @Nullable Class<? extends Activity> cls3, @Nullable com.salesforce.marketingcloud.notifications.c.a aVar, @Nullable C0173c cVar, @Nullable b bVar, @Nullable String str5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = z4;
        this.h = z5;
        this.i = i2;
        this.j = str4;
        this.k = cls;
        this.l = cls2;
        this.m = cls3;
        this.n = aVar;
        this.o = cVar;
        this.p = bVar;
        this.q = str5;
    }

    @NonNull
    public String b() {
        return this.a;
    }

    @NonNull
    public String c() {
        return this.b;
    }

    @Nullable
    public String d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a.equals(bVar.b()) && this.b.equals(bVar.c()) && (this.c != null ? this.c.equals(bVar.d()) : bVar.d() == null) && this.d == bVar.e() && this.e == bVar.f() && this.f == bVar.g() && this.g == bVar.h() && this.h == bVar.i() && this.i == bVar.j() && (this.j != null ? this.j.equals(bVar.k()) : bVar.k() == null) && (this.k != null ? this.k.equals(bVar.l()) : bVar.l() == null) && (this.l != null ? this.l.equals(bVar.m()) : bVar.m() == null) && (this.m != null ? this.m.equals(bVar.n()) : bVar.n() == null) && (this.n != null ? this.n.equals(bVar.o()) : bVar.o() == null) && (this.o != null ? this.o.equals(bVar.p()) : bVar.p() == null) && (this.p != null ? this.p.equals(bVar.q()) : bVar.q() == null)) {
            if (this.q == null) {
                if (bVar.r() == null) {
                    return true;
                }
            } else if (this.q.equals(bVar.r())) {
                return true;
            }
        }
        return false;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public boolean h() {
        return this.g;
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 1237;
        int hashCode = (((((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ (this.c == null ? 0 : this.c.hashCode())) * 1000003) ^ (this.d ? 1231 : 1237)) * 1000003) ^ (this.e ? 1231 : 1237)) * 1000003) ^ (this.f ? 1231 : 1237)) * 1000003) ^ (this.g ? 1231 : 1237)) * 1000003;
        if (this.h) {
            i3 = 1231;
        }
        int hashCode2 = (((((((((((((((((hashCode ^ i3) * 1000003) ^ this.i) * 1000003) ^ (this.j == null ? 0 : this.j.hashCode())) * 1000003) ^ (this.k == null ? 0 : this.k.hashCode())) * 1000003) ^ (this.l == null ? 0 : this.l.hashCode())) * 1000003) ^ (this.m == null ? 0 : this.m.hashCode())) * 1000003) ^ (this.n == null ? 0 : this.n.hashCode())) * 1000003) ^ (this.o == null ? 0 : this.o.hashCode())) * 1000003) ^ (this.p == null ? 0 : this.p.hashCode())) * 1000003;
        if (this.q != null) {
            i2 = this.q.hashCode();
        }
        return hashCode2 ^ i2;
    }

    public boolean i() {
        return this.h;
    }

    @DrawableRes
    public int j() {
        return this.i;
    }

    @Nullable
    public String k() {
        return this.j;
    }

    @Nullable
    public Class<? extends Activity> l() {
        return this.k;
    }

    @Nullable
    public Class<? extends Activity> m() {
        return this.l;
    }

    @Nullable
    public Class<? extends Activity> n() {
        return this.m;
    }

    @Nullable
    public com.salesforce.marketingcloud.notifications.c.a o() {
        return this.n;
    }

    @Nullable
    public C0173c p() {
        return this.o;
    }

    @Nullable
    public b q() {
        return this.p;
    }

    @Nullable
    public String r() {
        return this.q;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MarketingCloudConfig{applicationId=");
        sb.append(this.a);
        sb.append(", accessToken=");
        sb.append(this.b);
        sb.append(", gcmSenderId=");
        sb.append(this.c);
        sb.append(", analyticsEnabled=");
        sb.append(this.d);
        sb.append(", piAnalyticsEnabled=");
        sb.append(this.e);
        sb.append(", geofencingEnabled=");
        sb.append(this.f);
        sb.append(", proximityEnabled=");
        sb.append(this.g);
        sb.append(", inboxEnabled=");
        sb.append(this.h);
        sb.append(", notificationSmallIconResId=");
        sb.append(this.i);
        sb.append(", notificationDefaultTitle=");
        sb.append(this.j);
        sb.append(", cloudPageRecipient=");
        sb.append(this.k);
        sb.append(", openDirectRecipient=");
        sb.append(this.l);
        sb.append(", notificationRecipient=");
        sb.append(this.m);
        sb.append(", notificationBuilder=");
        sb.append(this.n);
        sb.append(", notificationLaunchIntentProvider=");
        sb.append(this.o);
        sb.append(", notificationChannelIdProvider=");
        sb.append(this.p);
        sb.append(", notificationChannelName=");
        sb.append(this.q);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
