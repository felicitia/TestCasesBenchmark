package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsDashboardListingSpec reason: invalid class name */
abstract class C$$AutoValue_MissionControlStatsDashboardListingSpec extends MissionControlStatsDashboardListingSpec {
    private final String channel;
    private final String currency_filter;
    private final String date_range;
    private final Integer end_date;
    private final String end_date_str;
    private final String end_date_str_inclusive;
    private final Boolean include_yoy_visits;
    private final EtsyId listing_id;
    private final Boolean prod_dataset_override;
    private final Integer prod_shop_id;
    private final EtsyId shop_id;
    private final Integer start_date;
    private final String start_date_str;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsDashboardListingSpec$a */
    /* compiled from: $$AutoValue_MissionControlStatsDashboardListingSpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a {
        private EtsyId a;
        private EtsyId b;
        private Integer c;
        private String d;
        private String e;
        private Integer f;
        private String g;
        private String h;
        private String i;
        private Integer j;
        private Boolean k;
        private Boolean l;
        private String m;

        a() {
        }

        a(MissionControlStatsDashboardListingSpec missionControlStatsDashboardListingSpec) {
            this.a = missionControlStatsDashboardListingSpec.shop_id();
            this.b = missionControlStatsDashboardListingSpec.listing_id();
            this.c = missionControlStatsDashboardListingSpec.start_date();
            this.d = missionControlStatsDashboardListingSpec.start_date_str();
            this.e = missionControlStatsDashboardListingSpec.date_range();
            this.f = missionControlStatsDashboardListingSpec.end_date();
            this.g = missionControlStatsDashboardListingSpec.end_date_str();
            this.h = missionControlStatsDashboardListingSpec.end_date_str_inclusive();
            this.i = missionControlStatsDashboardListingSpec.channel();
            this.j = missionControlStatsDashboardListingSpec.prod_shop_id();
            this.k = missionControlStatsDashboardListingSpec.prod_dataset_override();
            this.l = missionControlStatsDashboardListingSpec.include_yoy_visits();
            this.m = missionControlStatsDashboardListingSpec.currency_filter();
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a b(EtsyId etsyId) {
            this.b = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a a(@Nullable Integer num) {
            this.c = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a a(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a b(@Nullable String str) {
            this.e = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a b(@Nullable Integer num) {
            this.f = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a c(@Nullable String str) {
            this.g = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a d(@Nullable String str) {
            this.h = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a e(@Nullable String str) {
            this.i = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a c(@Nullable Integer num) {
            this.j = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a a(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a b(@Nullable Boolean bool) {
            this.l = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec.a f(@Nullable String str) {
            this.m = str;
            return this;
        }

        public MissionControlStatsDashboardListingSpec a() {
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
                sb2.append(" listing_id");
                str = sb2.toString();
            }
            if (!str.isEmpty()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Missing required properties:");
                sb3.append(str);
                throw new IllegalStateException(sb3.toString());
            }
            AutoValue_MissionControlStatsDashboardListingSpec autoValue_MissionControlStatsDashboardListingSpec = new AutoValue_MissionControlStatsDashboardListingSpec(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            return autoValue_MissionControlStatsDashboardListingSpec;
        }
    }

    C$$AutoValue_MissionControlStatsDashboardListingSpec(EtsyId etsyId, EtsyId etsyId2, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num3, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str6) {
        if (etsyId == null) {
            throw new NullPointerException("Null shop_id");
        }
        this.shop_id = etsyId;
        if (etsyId2 == null) {
            throw new NullPointerException("Null listing_id");
        }
        this.listing_id = etsyId2;
        this.start_date = num;
        this.start_date_str = str;
        this.date_range = str2;
        this.end_date = num2;
        this.end_date_str = str3;
        this.end_date_str_inclusive = str4;
        this.channel = str5;
        this.prod_shop_id = num3;
        this.prod_dataset_override = bool;
        this.include_yoy_visits = bool2;
        this.currency_filter = str6;
    }

    @NonNull
    public EtsyId shop_id() {
        return this.shop_id;
    }

    @NonNull
    public EtsyId listing_id() {
        return this.listing_id;
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
    public Integer prod_shop_id() {
        return this.prod_shop_id;
    }

    @Nullable
    public Boolean prod_dataset_override() {
        return this.prod_dataset_override;
    }

    @Nullable
    public Boolean include_yoy_visits() {
        return this.include_yoy_visits;
    }

    @Nullable
    public String currency_filter() {
        return this.currency_filter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MissionControlStatsDashboardListingSpec{shop_id=");
        sb.append(this.shop_id);
        sb.append(", ");
        sb.append("listing_id=");
        sb.append(this.listing_id);
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
        sb.append("prod_shop_id=");
        sb.append(this.prod_shop_id);
        sb.append(", ");
        sb.append("prod_dataset_override=");
        sb.append(this.prod_dataset_override);
        sb.append(", ");
        sb.append("include_yoy_visits=");
        sb.append(this.include_yoy_visits);
        sb.append(", ");
        sb.append("currency_filter=");
        sb.append(this.currency_filter);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MissionControlStatsDashboardListingSpec)) {
            return false;
        }
        MissionControlStatsDashboardListingSpec missionControlStatsDashboardListingSpec = (MissionControlStatsDashboardListingSpec) obj;
        if (!this.shop_id.equals(missionControlStatsDashboardListingSpec.shop_id()) || !this.listing_id.equals(missionControlStatsDashboardListingSpec.listing_id()) || (this.start_date != null ? !this.start_date.equals(missionControlStatsDashboardListingSpec.start_date()) : missionControlStatsDashboardListingSpec.start_date() != null) || (this.start_date_str != null ? !this.start_date_str.equals(missionControlStatsDashboardListingSpec.start_date_str()) : missionControlStatsDashboardListingSpec.start_date_str() != null) || (this.date_range != null ? !this.date_range.equals(missionControlStatsDashboardListingSpec.date_range()) : missionControlStatsDashboardListingSpec.date_range() != null) || (this.end_date != null ? !this.end_date.equals(missionControlStatsDashboardListingSpec.end_date()) : missionControlStatsDashboardListingSpec.end_date() != null) || (this.end_date_str != null ? !this.end_date_str.equals(missionControlStatsDashboardListingSpec.end_date_str()) : missionControlStatsDashboardListingSpec.end_date_str() != null) || (this.end_date_str_inclusive != null ? !this.end_date_str_inclusive.equals(missionControlStatsDashboardListingSpec.end_date_str_inclusive()) : missionControlStatsDashboardListingSpec.end_date_str_inclusive() != null) || (this.channel != null ? !this.channel.equals(missionControlStatsDashboardListingSpec.channel()) : missionControlStatsDashboardListingSpec.channel() != null) || (this.prod_shop_id != null ? !this.prod_shop_id.equals(missionControlStatsDashboardListingSpec.prod_shop_id()) : missionControlStatsDashboardListingSpec.prod_shop_id() != null) || (this.prod_dataset_override != null ? !this.prod_dataset_override.equals(missionControlStatsDashboardListingSpec.prod_dataset_override()) : missionControlStatsDashboardListingSpec.prod_dataset_override() != null) || (this.include_yoy_visits != null ? !this.include_yoy_visits.equals(missionControlStatsDashboardListingSpec.include_yoy_visits()) : missionControlStatsDashboardListingSpec.include_yoy_visits() != null) || (this.currency_filter != null ? !this.currency_filter.equals(missionControlStatsDashboardListingSpec.currency_filter()) : missionControlStatsDashboardListingSpec.currency_filter() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ this.listing_id.hashCode()) * 1000003) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * 1000003) ^ (this.start_date_str == null ? 0 : this.start_date_str.hashCode())) * 1000003) ^ (this.date_range == null ? 0 : this.date_range.hashCode())) * 1000003) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * 1000003) ^ (this.end_date_str == null ? 0 : this.end_date_str.hashCode())) * 1000003) ^ (this.end_date_str_inclusive == null ? 0 : this.end_date_str_inclusive.hashCode())) * 1000003) ^ (this.channel == null ? 0 : this.channel.hashCode())) * 1000003) ^ (this.prod_shop_id == null ? 0 : this.prod_shop_id.hashCode())) * 1000003) ^ (this.prod_dataset_override == null ? 0 : this.prod_dataset_override.hashCode())) * 1000003) ^ (this.include_yoy_visits == null ? 0 : this.include_yoy_visits.hashCode())) * 1000003;
        if (this.currency_filter != null) {
            i = this.currency_filter.hashCode();
        }
        return hashCode ^ i;
    }
}
