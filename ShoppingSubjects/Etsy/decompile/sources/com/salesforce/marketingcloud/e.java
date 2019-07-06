package com.salesforce.marketingcloud;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.InitializationStatus.Status;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

final class e extends InitializationStatus {
    private final Status a;
    private final Throwable b;
    private final boolean c;
    private final int d;
    private final String e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final List<String> k;

    static final class a extends com.salesforce.marketingcloud.InitializationStatus.a {
        private Status a;
        private Throwable b;
        private Boolean c;
        private Integer d;
        private String e;
        private Boolean f;
        private Boolean g;
        private Boolean h;
        private Boolean i;
        private Boolean j;
        private List<String> k;

        a() {
        }

        public com.salesforce.marketingcloud.InitializationStatus.a a(int i2) {
            this.d = Integer.valueOf(i2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.InitializationStatus.a a(Status status) {
            if (status == null) {
                throw new NullPointerException("Null status");
            }
            this.a = status;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.InitializationStatus.a a(@Nullable String str) {
            this.e = str;
            return this;
        }

        public com.salesforce.marketingcloud.InitializationStatus.a a(@Nullable Throwable th) {
            this.b = th;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.salesforce.marketingcloud.InitializationStatus.a a(List<String> list) {
            if (list == null) {
                throw new NullPointerException("Null initializedComponents");
            }
            this.k = list;
            return this;
        }

        public com.salesforce.marketingcloud.InitializationStatus.a a(boolean z) {
            this.c = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Throwable a() {
            return this.b;
        }

        public com.salesforce.marketingcloud.InitializationStatus.a b(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        public boolean b() {
            if (this.c != null) {
                return this.c.booleanValue();
            }
            throw new IllegalStateException("Property \"locationsError\" has not been set");
        }

        public com.salesforce.marketingcloud.InitializationStatus.a c(boolean z) {
            this.g = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public boolean c() {
            if (this.i != null) {
                return this.i.booleanValue();
            }
            throw new IllegalStateException("Property \"messagingPermissionError\" has not been set");
        }

        public com.salesforce.marketingcloud.InitializationStatus.a d(boolean z) {
            this.i = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public boolean d() {
            if (this.j != null) {
                return this.j.booleanValue();
            }
            throw new IllegalStateException("Property \"sslProviderEnablementError\" has not been set");
        }

        public com.salesforce.marketingcloud.InitializationStatus.a e(boolean z) {
            this.j = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public boolean e() {
            if (this.g != null) {
                return this.g.booleanValue();
            }
            throw new IllegalStateException("Property \"storageError\" has not been set");
        }

        public com.salesforce.marketingcloud.InitializationStatus.a f(boolean z) {
            this.h = Boolean.valueOf(z);
            return this;
        }

        public boolean f() {
            if (this.h != null) {
                return this.h.booleanValue();
            }
            throw new IllegalStateException("Property \"proximityError\" has not been set");
        }

        /* access modifiers changed from: 0000 */
        public InitializationStatus h() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" status");
                str = sb.toString();
            }
            if (this.c == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" locationsError");
                str = sb2.toString();
            }
            if (this.d == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" playServicesStatus");
                str = sb3.toString();
            }
            if (this.f == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" encryptionChanged");
                str = sb4.toString();
            }
            if (this.g == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" storageError");
                str = sb5.toString();
            }
            if (this.h == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(" proximityError");
                str = sb6.toString();
            }
            if (this.i == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str);
                sb7.append(" messagingPermissionError");
                str = sb7.toString();
            }
            if (this.j == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(" sslProviderEnablementError");
                str = sb8.toString();
            }
            if (this.k == null) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str);
                sb9.append(" initializedComponents");
                str = sb9.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb10 = new StringBuilder();
                sb10.append("Missing required properties:");
                sb10.append(str);
                throw new IllegalStateException(sb10.toString());
            }
            e eVar = new e(this.a, this.b, this.c.booleanValue(), this.d.intValue(), this.e, this.f.booleanValue(), this.g.booleanValue(), this.h.booleanValue(), this.i.booleanValue(), this.j.booleanValue(), this.k);
            return eVar;
        }
    }

    private e(Status status, @Nullable Throwable th, boolean z, int i2, @Nullable String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, List<String> list) {
        this.a = status;
        this.b = th;
        this.c = z;
        this.d = i2;
        this.e = str;
        this.f = z2;
        this.g = z3;
        this.h = z4;
        this.i = z5;
        this.j = z6;
        this.k = list;
    }

    @NonNull
    public Status c() {
        return this.a;
    }

    @Nullable
    public Throwable d() {
        return this.b;
    }

    public boolean e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InitializationStatus)) {
            return false;
        }
        InitializationStatus initializationStatus = (InitializationStatus) obj;
        return this.a.equals(initializationStatus.c()) && (this.b != null ? this.b.equals(initializationStatus.d()) : initializationStatus.d() == null) && this.c == initializationStatus.e() && this.d == initializationStatus.f() && (this.e != null ? this.e.equals(initializationStatus.g()) : initializationStatus.g() == null) && this.f == initializationStatus.h() && this.g == initializationStatus.i() && this.h == initializationStatus.j() && this.i == initializationStatus.k() && this.j == initializationStatus.l() && this.k.equals(initializationStatus.m());
    }

    public int f() {
        return this.d;
    }

    @Nullable
    public String g() {
        return this.e;
    }

    public boolean h() {
        return this.f;
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 1237;
        int hashCode = (((((((this.a.hashCode() ^ 1000003) * 1000003) ^ (this.b == null ? 0 : this.b.hashCode())) * 1000003) ^ (this.c ? 1231 : 1237)) * 1000003) ^ this.d) * 1000003;
        if (this.e != null) {
            i2 = this.e.hashCode();
        }
        int i4 = (((((((((hashCode ^ i2) * 1000003) ^ (this.f ? 1231 : 1237)) * 1000003) ^ (this.g ? 1231 : 1237)) * 1000003) ^ (this.h ? 1231 : 1237)) * 1000003) ^ (this.i ? 1231 : 1237)) * 1000003;
        if (this.j) {
            i3 = 1231;
        }
        return ((i4 ^ i3) * 1000003) ^ this.k.hashCode();
    }

    public boolean i() {
        return this.g;
    }

    public boolean j() {
        return this.h;
    }

    public boolean k() {
        return this.i;
    }

    public boolean l() {
        return this.j;
    }

    @NonNull
    public List<String> m() {
        return this.k;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InitializationStatus{status=");
        sb.append(this.a);
        sb.append(", unrecoverableException=");
        sb.append(this.b);
        sb.append(", locationsError=");
        sb.append(this.c);
        sb.append(", playServicesStatus=");
        sb.append(this.d);
        sb.append(", playServicesMessage=");
        sb.append(this.e);
        sb.append(", encryptionChanged=");
        sb.append(this.f);
        sb.append(", storageError=");
        sb.append(this.g);
        sb.append(", proximityError=");
        sb.append(this.h);
        sb.append(", messagingPermissionError=");
        sb.append(this.i);
        sb.append(", sslProviderEnablementError=");
        sb.append(this.j);
        sb.append(", initializedComponents=");
        sb.append(this.k);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
