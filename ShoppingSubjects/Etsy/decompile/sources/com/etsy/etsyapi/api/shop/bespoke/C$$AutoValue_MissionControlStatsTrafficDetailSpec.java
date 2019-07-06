package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsTrafficDetailSpec reason: invalid class name */
abstract class C$$AutoValue_MissionControlStatsTrafficDetailSpec extends MissionControlStatsTrafficDetailSpec {
    private final String channel;
    private final String date_range;
    private final String domain;
    private final Integer end_date;
    private final String end_date_str;
    private final String end_date_str_inclusive;
    private final Boolean include_yoy_visits;
    private final String internal_page;
    private final String referrer;
    private final String referrer_group;
    private final EtsyId shop_id;
    private final Integer start_date;
    private final String start_date_str;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsTrafficDetailSpec$a */
    /* compiled from: $$AutoValue_MissionControlStatsTrafficDetailSpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a {
        private EtsyId a;
        private String b;
        private String c;
        private String d;
        private String e;
        private Integer f;
        private String g;
        private String h;
        private Integer i;
        private String j;
        private String k;
        private String l;
        private Boolean m;

        a() {
        }

        a(MissionControlStatsTrafficDetailSpec missionControlStatsTrafficDetailSpec) {
            this.a = missionControlStatsTrafficDetailSpec.shop_id();
            this.b = missionControlStatsTrafficDetailSpec.referrer_group();
            this.c = missionControlStatsTrafficDetailSpec.referrer();
            this.d = missionControlStatsTrafficDetailSpec.domain();
            this.e = missionControlStatsTrafficDetailSpec.internal_page();
            this.f = missionControlStatsTrafficDetailSpec.start_date();
            this.g = missionControlStatsTrafficDetailSpec.start_date_str();
            this.h = missionControlStatsTrafficDetailSpec.date_range();
            this.i = missionControlStatsTrafficDetailSpec.end_date();
            this.j = missionControlStatsTrafficDetailSpec.end_date_str();
            this.k = missionControlStatsTrafficDetailSpec.end_date_str_inclusive();
            this.l = missionControlStatsTrafficDetailSpec.channel();
            this.m = missionControlStatsTrafficDetailSpec.include_yoy_visits();
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a a(String str) {
            this.b = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a b(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a c(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a d(@Nullable String str) {
            this.e = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a a(@Nullable Integer num) {
            this.f = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a e(@Nullable String str) {
            this.g = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a f(@Nullable String str) {
            this.h = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a b(@Nullable Integer num) {
            this.i = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a g(@Nullable String str) {
            this.j = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a h(@Nullable String str) {
            this.k = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a i(@Nullable String str) {
            this.l = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec.a a(@Nullable Boolean bool) {
            this.m = bool;
            return this;
        }

        public MissionControlStatsTrafficDetailSpec a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" shop_id");
                str = sb.toString();
            }
            if (this.b == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" referrer_group");
                str = sb2.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Missing required properties:");
                sb3.append(str);
                throw new IllegalStateException(sb3.toString());
            }
            AutoValue_MissionControlStatsTrafficDetailSpec autoValue_MissionControlStatsTrafficDetailSpec = new AutoValue_MissionControlStatsTrafficDetailSpec(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            return autoValue_MissionControlStatsTrafficDetailSpec;
        }
    }

    C$$AutoValue_MissionControlStatsTrafficDetailSpec(EtsyId etsyId, String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Integer num, @Nullable String str5, @Nullable String str6, @Nullable Integer num2, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable Boolean bool) {
        if (etsyId == null) {
            throw new NullPointerException("Null shop_id");
        }
        this.shop_id = etsyId;
        if (str == null) {
            throw new NullPointerException("Null referrer_group");
        }
        this.referrer_group = str;
        this.referrer = str2;
        this.domain = str3;
        this.internal_page = str4;
        this.start_date = num;
        this.start_date_str = str5;
        this.date_range = str6;
        this.end_date = num2;
        this.end_date_str = str7;
        this.end_date_str_inclusive = str8;
        this.channel = str9;
        this.include_yoy_visits = bool;
    }

    @NonNull
    public EtsyId shop_id() {
        return this.shop_id;
    }

    @NonNull
    public String referrer_group() {
        return this.referrer_group;
    }

    @Nullable
    public String referrer() {
        return this.referrer;
    }

    @Nullable
    public String domain() {
        return this.domain;
    }

    @Nullable
    public String internal_page() {
        return this.internal_page;
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

    @Nullable
    public Boolean include_yoy_visits() {
        return this.include_yoy_visits;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MissionControlStatsTrafficDetailSpec{shop_id=");
        sb.append(this.shop_id);
        sb.append(", ");
        sb.append("referrer_group=");
        sb.append(this.referrer_group);
        sb.append(", ");
        sb.append("referrer=");
        sb.append(this.referrer);
        sb.append(", ");
        sb.append("domain=");
        sb.append(this.domain);
        sb.append(", ");
        sb.append("internal_page=");
        sb.append(this.internal_page);
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
        sb.append(", ");
        sb.append("include_yoy_visits=");
        sb.append(this.include_yoy_visits);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MissionControlStatsTrafficDetailSpec)) {
            return false;
        }
        MissionControlStatsTrafficDetailSpec missionControlStatsTrafficDetailSpec = (MissionControlStatsTrafficDetailSpec) obj;
        if (!this.shop_id.equals(missionControlStatsTrafficDetailSpec.shop_id()) || !this.referrer_group.equals(missionControlStatsTrafficDetailSpec.referrer_group()) || (this.referrer != null ? !this.referrer.equals(missionControlStatsTrafficDetailSpec.referrer()) : missionControlStatsTrafficDetailSpec.referrer() != null) || (this.domain != null ? !this.domain.equals(missionControlStatsTrafficDetailSpec.domain()) : missionControlStatsTrafficDetailSpec.domain() != null) || (this.internal_page != null ? !this.internal_page.equals(missionControlStatsTrafficDetailSpec.internal_page()) : missionControlStatsTrafficDetailSpec.internal_page() != null) || (this.start_date != null ? !this.start_date.equals(missionControlStatsTrafficDetailSpec.start_date()) : missionControlStatsTrafficDetailSpec.start_date() != null) || (this.start_date_str != null ? !this.start_date_str.equals(missionControlStatsTrafficDetailSpec.start_date_str()) : missionControlStatsTrafficDetailSpec.start_date_str() != null) || (this.date_range != null ? !this.date_range.equals(missionControlStatsTrafficDetailSpec.date_range()) : missionControlStatsTrafficDetailSpec.date_range() != null) || (this.end_date != null ? !this.end_date.equals(missionControlStatsTrafficDetailSpec.end_date()) : missionControlStatsTrafficDetailSpec.end_date() != null) || (this.end_date_str != null ? !this.end_date_str.equals(missionControlStatsTrafficDetailSpec.end_date_str()) : missionControlStatsTrafficDetailSpec.end_date_str() != null) || (this.end_date_str_inclusive != null ? !this.end_date_str_inclusive.equals(missionControlStatsTrafficDetailSpec.end_date_str_inclusive()) : missionControlStatsTrafficDetailSpec.end_date_str_inclusive() != null) || (this.channel != null ? !this.channel.equals(missionControlStatsTrafficDetailSpec.channel()) : missionControlStatsTrafficDetailSpec.channel() != null) || (this.include_yoy_visits != null ? !this.include_yoy_visits.equals(missionControlStatsTrafficDetailSpec.include_yoy_visits()) : missionControlStatsTrafficDetailSpec.include_yoy_visits() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ this.referrer_group.hashCode()) * 1000003) ^ (this.referrer == null ? 0 : this.referrer.hashCode())) * 1000003) ^ (this.domain == null ? 0 : this.domain.hashCode())) * 1000003) ^ (this.internal_page == null ? 0 : this.internal_page.hashCode())) * 1000003) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * 1000003) ^ (this.start_date_str == null ? 0 : this.start_date_str.hashCode())) * 1000003) ^ (this.date_range == null ? 0 : this.date_range.hashCode())) * 1000003) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * 1000003) ^ (this.end_date_str == null ? 0 : this.end_date_str.hashCode())) * 1000003) ^ (this.end_date_str_inclusive == null ? 0 : this.end_date_str_inclusive.hashCode())) * 1000003) ^ (this.channel == null ? 0 : this.channel.hashCode())) * 1000003;
        if (this.include_yoy_visits != null) {
            i = this.include_yoy_visits.hashCode();
        }
        return hashCode ^ i;
    }
}
