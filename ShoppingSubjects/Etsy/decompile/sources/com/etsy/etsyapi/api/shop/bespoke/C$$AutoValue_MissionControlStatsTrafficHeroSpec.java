package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsTrafficHeroSpec reason: invalid class name */
abstract class C$$AutoValue_MissionControlStatsTrafficHeroSpec extends MissionControlStatsTrafficHeroSpec {
    private final String channel;
    private final String date_range;
    private final Integer end_date;
    private final String end_date_str;
    private final String end_date_str_inclusive;
    private final EtsyId shop_id;
    private final Integer start_date;
    private final String start_date_str;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsTrafficHeroSpec$a */
    /* compiled from: $$AutoValue_MissionControlStatsTrafficHeroSpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a {
        private EtsyId a;
        private Integer b;
        private String c;
        private String d;
        private Integer e;
        private String f;
        private String g;
        private String h;

        a() {
        }

        a(MissionControlStatsTrafficHeroSpec missionControlStatsTrafficHeroSpec) {
            this.a = missionControlStatsTrafficHeroSpec.shop_id();
            this.b = missionControlStatsTrafficHeroSpec.start_date();
            this.c = missionControlStatsTrafficHeroSpec.start_date_str();
            this.d = missionControlStatsTrafficHeroSpec.date_range();
            this.e = missionControlStatsTrafficHeroSpec.end_date();
            this.f = missionControlStatsTrafficHeroSpec.end_date_str();
            this.g = missionControlStatsTrafficHeroSpec.end_date_str_inclusive();
            this.h = missionControlStatsTrafficHeroSpec.channel();
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a a(@Nullable Integer num) {
            this.b = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a a(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a b(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a b(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a c(@Nullable String str) {
            this.f = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a d(@Nullable String str) {
            this.g = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec.a e(@Nullable String str) {
            this.h = str;
            return this;
        }

        public MissionControlStatsTrafficHeroSpec a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" shop_id");
                str = sb.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Missing required properties:");
                sb2.append(str);
                throw new IllegalStateException(sb2.toString());
            }
            AutoValue_MissionControlStatsTrafficHeroSpec autoValue_MissionControlStatsTrafficHeroSpec = new AutoValue_MissionControlStatsTrafficHeroSpec(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            return autoValue_MissionControlStatsTrafficHeroSpec;
        }
    }

    C$$AutoValue_MissionControlStatsTrafficHeroSpec(EtsyId etsyId, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        if (etsyId == null) {
            throw new NullPointerException("Null shop_id");
        }
        this.shop_id = etsyId;
        this.start_date = num;
        this.start_date_str = str;
        this.date_range = str2;
        this.end_date = num2;
        this.end_date_str = str3;
        this.end_date_str_inclusive = str4;
        this.channel = str5;
    }

    @NonNull
    public EtsyId shop_id() {
        return this.shop_id;
    }

    @Nullable
    public Integer start_date() {
        return this.start_date;
    }

    @Nullable
    public String start_date_str() {
        return this.start_date_str;
    }

    @Nullable
    public String date_range() {
        return this.date_range;
    }

    @Nullable
    public Integer end_date() {
        return this.end_date;
    }

    @Nullable
    public String end_date_str() {
        return this.end_date_str;
    }

    @Nullable
    public String end_date_str_inclusive() {
        return this.end_date_str_inclusive;
    }

    @Nullable
    public String channel() {
        return this.channel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MissionControlStatsTrafficHeroSpec{shop_id=");
        sb.append(this.shop_id);
        sb.append(", ");
        sb.append("start_date=");
        sb.append(this.start_date);
        sb.append(", ");
        sb.append("start_date_str=");
        sb.append(this.start_date_str);
        sb.append(", ");
        sb.append("date_range=");
        sb.append(this.date_range);
        sb.append(", ");
        sb.append("end_date=");
        sb.append(this.end_date);
        sb.append(", ");
        sb.append("end_date_str=");
        sb.append(this.end_date_str);
        sb.append(", ");
        sb.append("end_date_str_inclusive=");
        sb.append(this.end_date_str_inclusive);
        sb.append(", ");
        sb.append("channel=");
        sb.append(this.channel);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MissionControlStatsTrafficHeroSpec)) {
            return false;
        }
        MissionControlStatsTrafficHeroSpec missionControlStatsTrafficHeroSpec = (MissionControlStatsTrafficHeroSpec) obj;
        if (!this.shop_id.equals(missionControlStatsTrafficHeroSpec.shop_id()) || (this.start_date != null ? !this.start_date.equals(missionControlStatsTrafficHeroSpec.start_date()) : missionControlStatsTrafficHeroSpec.start_date() != null) || (this.start_date_str != null ? !this.start_date_str.equals(missionControlStatsTrafficHeroSpec.start_date_str()) : missionControlStatsTrafficHeroSpec.start_date_str() != null) || (this.date_range != null ? !this.date_range.equals(missionControlStatsTrafficHeroSpec.date_range()) : missionControlStatsTrafficHeroSpec.date_range() != null) || (this.end_date != null ? !this.end_date.equals(missionControlStatsTrafficHeroSpec.end_date()) : missionControlStatsTrafficHeroSpec.end_date() != null) || (this.end_date_str != null ? !this.end_date_str.equals(missionControlStatsTrafficHeroSpec.end_date_str()) : missionControlStatsTrafficHeroSpec.end_date_str() != null) || (this.end_date_str_inclusive != null ? !this.end_date_str_inclusive.equals(missionControlStatsTrafficHeroSpec.end_date_str_inclusive()) : missionControlStatsTrafficHeroSpec.end_date_str_inclusive() != null) || (this.channel != null ? !this.channel.equals(missionControlStatsTrafficHeroSpec.channel()) : missionControlStatsTrafficHeroSpec.channel() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * 1000003) ^ (this.start_date_str == null ? 0 : this.start_date_str.hashCode())) * 1000003) ^ (this.date_range == null ? 0 : this.date_range.hashCode())) * 1000003) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * 1000003) ^ (this.end_date_str == null ? 0 : this.end_date_str.hashCode())) * 1000003) ^ (this.end_date_str_inclusive == null ? 0 : this.end_date_str_inclusive.hashCode())) * 1000003;
        if (this.channel != null) {
            i = this.channel.hashCode();
        }
        return hashCode ^ i;
    }
}
