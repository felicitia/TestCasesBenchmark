package com.etsy.etsyapi.api.pub;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import java.io.File;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.pub.$$AutoValue_BugHuntReportCreatePostSpec reason: invalid class name */
abstract class C$$AutoValue_BugHuntReportCreatePostSpec extends BugHuntReportCreatePostSpec {
    private final String device_type;
    private final String etsy_version;
    private final File image01;
    private final File image02;
    private final File image03;
    private final String message;
    private final String platform;
    private final String platform_version;
    private final EtsyId user_id;

    /* renamed from: com.etsy.etsyapi.api.pub.$$AutoValue_BugHuntReportCreatePostSpec$a */
    /* compiled from: $$AutoValue_BugHuntReportCreatePostSpec */
    static final class a extends com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private EtsyId f;
        private File g;
        private File h;
        private File i;

        a() {
        }

        a(BugHuntReportCreatePostSpec bugHuntReportCreatePostSpec) {
            this.a = bugHuntReportCreatePostSpec.message();
            this.b = bugHuntReportCreatePostSpec.platform();
            this.c = bugHuntReportCreatePostSpec.platform_version();
            this.d = bugHuntReportCreatePostSpec.etsy_version();
            this.e = bugHuntReportCreatePostSpec.device_type();
            this.f = bugHuntReportCreatePostSpec.user_id();
            this.g = bugHuntReportCreatePostSpec.image01();
            this.h = bugHuntReportCreatePostSpec.image02();
            this.i = bugHuntReportCreatePostSpec.image03();
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a a(String str) {
            this.a = str;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a b(String str) {
            this.b = str;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a c(String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a d(String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a e(@Nullable String str) {
            this.e = str;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a a(@Nullable EtsyId etsyId) {
            this.f = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a a(@Nullable File file) {
            this.g = file;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a b(@Nullable File file) {
            this.h = file;
            return this;
        }

        public com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a c(@Nullable File file) {
            this.i = file;
            return this;
        }

        public BugHuntReportCreatePostSpec a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" message");
                str = sb.toString();
            }
            if (this.b == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" platform");
                str = sb2.toString();
            }
            if (this.c == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" platform_version");
                str = sb3.toString();
            }
            if (this.d == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" etsy_version");
                str = sb4.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Missing required properties:");
                sb5.append(str);
                throw new IllegalStateException(sb5.toString());
            }
            AutoValue_BugHuntReportCreatePostSpec autoValue_BugHuntReportCreatePostSpec = new AutoValue_BugHuntReportCreatePostSpec(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
            return autoValue_BugHuntReportCreatePostSpec;
        }
    }

    C$$AutoValue_BugHuntReportCreatePostSpec(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable EtsyId etsyId, @Nullable File file, @Nullable File file2, @Nullable File file3) {
        if (str == null) {
            throw new NullPointerException("Null message");
        }
        this.message = str;
        if (str2 == null) {
            throw new NullPointerException("Null platform");
        }
        this.platform = str2;
        if (str3 == null) {
            throw new NullPointerException("Null platform_version");
        }
        this.platform_version = str3;
        if (str4 == null) {
            throw new NullPointerException("Null etsy_version");
        }
        this.etsy_version = str4;
        this.device_type = str5;
        this.user_id = etsyId;
        this.image01 = file;
        this.image02 = file2;
        this.image03 = file3;
    }

    @NonNull
    public String message() {
        return this.message;
    }

    @NonNull
    public String platform() {
        return this.platform;
    }

    @NonNull
    public String platform_version() {
        return this.platform_version;
    }

    @NonNull
    public String etsy_version() {
        return this.etsy_version;
    }

    @Nullable
    public String device_type() {
        return this.device_type;
    }

    @Nullable
    public EtsyId user_id() {
        return this.user_id;
    }

    @Nullable
    public File image01() {
        return this.image01;
    }

    @Nullable
    public File image02() {
        return this.image02;
    }

    @Nullable
    public File image03() {
        return this.image03;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BugHuntReportCreatePostSpec{message=");
        sb.append(this.message);
        sb.append(", ");
        sb.append("platform=");
        sb.append(this.platform);
        sb.append(", ");
        sb.append("platform_version=");
        sb.append(this.platform_version);
        sb.append(", ");
        sb.append("etsy_version=");
        sb.append(this.etsy_version);
        sb.append(", ");
        sb.append("device_type=");
        sb.append(this.device_type);
        sb.append(", ");
        sb.append("user_id=");
        sb.append(this.user_id);
        sb.append(", ");
        sb.append("image01=");
        sb.append(this.image01);
        sb.append(", ");
        sb.append("image02=");
        sb.append(this.image02);
        sb.append(", ");
        sb.append("image03=");
        sb.append(this.image03);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BugHuntReportCreatePostSpec)) {
            return false;
        }
        BugHuntReportCreatePostSpec bugHuntReportCreatePostSpec = (BugHuntReportCreatePostSpec) obj;
        if (!this.message.equals(bugHuntReportCreatePostSpec.message()) || !this.platform.equals(bugHuntReportCreatePostSpec.platform()) || !this.platform_version.equals(bugHuntReportCreatePostSpec.platform_version()) || !this.etsy_version.equals(bugHuntReportCreatePostSpec.etsy_version()) || (this.device_type != null ? !this.device_type.equals(bugHuntReportCreatePostSpec.device_type()) : bugHuntReportCreatePostSpec.device_type() != null) || (this.user_id != null ? !this.user_id.equals(bugHuntReportCreatePostSpec.user_id()) : bugHuntReportCreatePostSpec.user_id() != null) || (this.image01 != null ? !this.image01.equals(bugHuntReportCreatePostSpec.image01()) : bugHuntReportCreatePostSpec.image01() != null) || (this.image02 != null ? !this.image02.equals(bugHuntReportCreatePostSpec.image02()) : bugHuntReportCreatePostSpec.image02() != null) || (this.image03 != null ? !this.image03.equals(bugHuntReportCreatePostSpec.image03()) : bugHuntReportCreatePostSpec.image03() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((this.message.hashCode() ^ 1000003) * 1000003) ^ this.platform.hashCode()) * 1000003) ^ this.platform_version.hashCode()) * 1000003) ^ this.etsy_version.hashCode()) * 1000003) ^ (this.device_type == null ? 0 : this.device_type.hashCode())) * 1000003) ^ (this.user_id == null ? 0 : this.user_id.hashCode())) * 1000003) ^ (this.image01 == null ? 0 : this.image01.hashCode())) * 1000003) ^ (this.image02 == null ? 0 : this.image02.hashCode())) * 1000003;
        if (this.image03 != null) {
            i = this.image03.hashCode();
        }
        return hashCode ^ i;
    }
}
