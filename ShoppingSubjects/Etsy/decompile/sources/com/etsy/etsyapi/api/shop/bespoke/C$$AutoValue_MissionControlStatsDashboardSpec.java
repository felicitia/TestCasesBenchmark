package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsDashboardSpec reason: invalid class name */
abstract class C$$AutoValue_MissionControlStatsDashboardSpec extends MissionControlStatsDashboardSpec {
    private final String channel;
    private final String currency_filter;
    private final String date_range;
    private final Integer end_date;
    private final String end_date_str;
    private final String end_date_str_inclusive;
    private final Boolean include_all_listings;
    private final Boolean include_yoy_visits;
    private final Integer inv_limit;
    private final Integer inv_offset;
    private final String inv_sort;
    private final String inv_sort_by;
    private final String listings_filter;
    private final String page_id;
    private final Boolean prod_dataset_override;
    private final Integer prod_shop_id;
    private final EtsyId shop_id;
    private final Boolean show_onboarding;
    private final Integer start_date;
    private final String start_date_str;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsDashboardSpec$a */
    /* compiled from: $$AutoValue_MissionControlStatsDashboardSpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a {
        private EtsyId a;
        private Integer b;
        private String c;
        private String d;
        private Integer e;
        private String f;
        private String g;
        private String h;
        private Integer i;
        private Boolean j;
        private Boolean k;
        private String l;
        private Boolean m;
        private String n;
        private Integer o;
        private Integer p;
        private String q;
        private String r;
        private String s;
        private Boolean t;

        a() {
        }

        a(MissionControlStatsDashboardSpec missionControlStatsDashboardSpec) {
            this.a = missionControlStatsDashboardSpec.shop_id();
            this.b = missionControlStatsDashboardSpec.start_date();
            this.c = missionControlStatsDashboardSpec.start_date_str();
            this.d = missionControlStatsDashboardSpec.date_range();
            this.e = missionControlStatsDashboardSpec.end_date();
            this.f = missionControlStatsDashboardSpec.end_date_str();
            this.g = missionControlStatsDashboardSpec.end_date_str_inclusive();
            this.h = missionControlStatsDashboardSpec.channel();
            this.i = missionControlStatsDashboardSpec.prod_shop_id();
            this.j = missionControlStatsDashboardSpec.prod_dataset_override();
            this.k = missionControlStatsDashboardSpec.include_yoy_visits();
            this.l = missionControlStatsDashboardSpec.page_id();
            this.m = missionControlStatsDashboardSpec.include_all_listings();
            this.n = missionControlStatsDashboardSpec.currency_filter();
            this.o = missionControlStatsDashboardSpec.inv_limit();
            this.p = missionControlStatsDashboardSpec.inv_offset();
            this.q = missionControlStatsDashboardSpec.inv_sort();
            this.r = missionControlStatsDashboardSpec.inv_sort_by();
            this.s = missionControlStatsDashboardSpec.listings_filter();
            this.t = missionControlStatsDashboardSpec.show_onboarding();
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a a(@Nullable Integer num) {
            this.b = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a a(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a b(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a b(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a c(@Nullable String str) {
            this.f = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a d(@Nullable String str) {
            this.g = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a e(@Nullable String str) {
            this.h = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a c(@Nullable Integer num) {
            this.i = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a a(@Nullable Boolean bool) {
            this.j = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a b(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a f(@Nullable String str) {
            this.l = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a c(@Nullable Boolean bool) {
            this.m = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a g(@Nullable String str) {
            this.n = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a d(@Nullable Integer num) {
            this.o = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a e(@Nullable Integer num) {
            this.p = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a h(@Nullable String str) {
            this.q = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a i(@Nullable String str) {
            this.r = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a j(@Nullable String str) {
            this.s = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec.a d(@Nullable Boolean bool) {
            this.t = bool;
            return this;
        }

        public MissionControlStatsDashboardSpec a() {
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
            EtsyId etsyId = this.a;
            Integer num = this.b;
            String str2 = this.c;
            String str3 = this.d;
            Integer num2 = this.e;
            String str4 = this.f;
            String str5 = this.g;
            String str6 = this.h;
            Integer num3 = this.i;
            Boolean bool = this.j;
            Boolean bool2 = this.k;
            String str7 = this.l;
            Boolean bool3 = this.m;
            Boolean bool4 = bool3;
            Boolean bool5 = bool4;
            AutoValue_MissionControlStatsDashboardSpec autoValue_MissionControlStatsDashboardSpec = new AutoValue_MissionControlStatsDashboardSpec(etsyId, num, str2, str3, num2, str4, str5, str6, num3, bool, bool2, str7, bool5, this.n, this.o, this.p, this.q, this.r, this.s, this.t);
            return autoValue_MissionControlStatsDashboardSpec;
        }
    }

    C$$AutoValue_MissionControlStatsDashboardSpec(EtsyId etsyId, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num3, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str6, @Nullable Boolean bool3, @Nullable String str7, @Nullable Integer num4, @Nullable Integer num5, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable Boolean bool4) {
        EtsyId etsyId2 = etsyId;
        if (etsyId2 == null) {
            throw new NullPointerException("Null shop_id");
        }
        this.shop_id = etsyId2;
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
        this.page_id = str6;
        this.include_all_listings = bool3;
        this.currency_filter = str7;
        this.inv_limit = num4;
        this.inv_offset = num5;
        this.inv_sort = str8;
        this.inv_sort_by = str9;
        this.listings_filter = str10;
        this.show_onboarding = bool4;
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
    public String page_id() {
        return this.page_id;
    }

    @Nullable
    public Boolean include_all_listings() {
        return this.include_all_listings;
    }

    @Nullable
    public String currency_filter() {
        return this.currency_filter;
    }

    @Nullable
    public Integer inv_limit() {
        return this.inv_limit;
    }

    @Nullable
    public Integer inv_offset() {
        return this.inv_offset;
    }

    @Nullable
    public String inv_sort() {
        return this.inv_sort;
    }

    @Nullable
    public String inv_sort_by() {
        return this.inv_sort_by;
    }

    @Nullable
    public String listings_filter() {
        return this.listings_filter;
    }

    @Nullable
    public Boolean show_onboarding() {
        return this.show_onboarding;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MissionControlStatsDashboardSpec{shop_id=");
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
        sb.append("page_id=");
        sb.append(this.page_id);
        sb.append(", ");
        sb.append("include_all_listings=");
        sb.append(this.include_all_listings);
        sb.append(", ");
        sb.append("currency_filter=");
        sb.append(this.currency_filter);
        sb.append(", ");
        sb.append("inv_limit=");
        sb.append(this.inv_limit);
        sb.append(", ");
        sb.append("inv_offset=");
        sb.append(this.inv_offset);
        sb.append(", ");
        sb.append("inv_sort=");
        sb.append(this.inv_sort);
        sb.append(", ");
        sb.append("inv_sort_by=");
        sb.append(this.inv_sort_by);
        sb.append(", ");
        sb.append("listings_filter=");
        sb.append(this.listings_filter);
        sb.append(", ");
        sb.append("show_onboarding=");
        sb.append(this.show_onboarding);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MissionControlStatsDashboardSpec)) {
            return false;
        }
        MissionControlStatsDashboardSpec missionControlStatsDashboardSpec = (MissionControlStatsDashboardSpec) obj;
        if (!this.shop_id.equals(missionControlStatsDashboardSpec.shop_id()) || (this.start_date != null ? !this.start_date.equals(missionControlStatsDashboardSpec.start_date()) : missionControlStatsDashboardSpec.start_date() != null) || (this.start_date_str != null ? !this.start_date_str.equals(missionControlStatsDashboardSpec.start_date_str()) : missionControlStatsDashboardSpec.start_date_str() != null) || (this.date_range != null ? !this.date_range.equals(missionControlStatsDashboardSpec.date_range()) : missionControlStatsDashboardSpec.date_range() != null) || (this.end_date != null ? !this.end_date.equals(missionControlStatsDashboardSpec.end_date()) : missionControlStatsDashboardSpec.end_date() != null) || (this.end_date_str != null ? !this.end_date_str.equals(missionControlStatsDashboardSpec.end_date_str()) : missionControlStatsDashboardSpec.end_date_str() != null) || (this.end_date_str_inclusive != null ? !this.end_date_str_inclusive.equals(missionControlStatsDashboardSpec.end_date_str_inclusive()) : missionControlStatsDashboardSpec.end_date_str_inclusive() != null) || (this.channel != null ? !this.channel.equals(missionControlStatsDashboardSpec.channel()) : missionControlStatsDashboardSpec.channel() != null) || (this.prod_shop_id != null ? !this.prod_shop_id.equals(missionControlStatsDashboardSpec.prod_shop_id()) : missionControlStatsDashboardSpec.prod_shop_id() != null) || (this.prod_dataset_override != null ? !this.prod_dataset_override.equals(missionControlStatsDashboardSpec.prod_dataset_override()) : missionControlStatsDashboardSpec.prod_dataset_override() != null) || (this.include_yoy_visits != null ? !this.include_yoy_visits.equals(missionControlStatsDashboardSpec.include_yoy_visits()) : missionControlStatsDashboardSpec.include_yoy_visits() != null) || (this.page_id != null ? !this.page_id.equals(missionControlStatsDashboardSpec.page_id()) : missionControlStatsDashboardSpec.page_id() != null) || (this.include_all_listings != null ? !this.include_all_listings.equals(missionControlStatsDashboardSpec.include_all_listings()) : missionControlStatsDashboardSpec.include_all_listings() != null) || (this.currency_filter != null ? !this.currency_filter.equals(missionControlStatsDashboardSpec.currency_filter()) : missionControlStatsDashboardSpec.currency_filter() != null) || (this.inv_limit != null ? !this.inv_limit.equals(missionControlStatsDashboardSpec.inv_limit()) : missionControlStatsDashboardSpec.inv_limit() != null) || (this.inv_offset != null ? !this.inv_offset.equals(missionControlStatsDashboardSpec.inv_offset()) : missionControlStatsDashboardSpec.inv_offset() != null) || (this.inv_sort != null ? !this.inv_sort.equals(missionControlStatsDashboardSpec.inv_sort()) : missionControlStatsDashboardSpec.inv_sort() != null) || (this.inv_sort_by != null ? !this.inv_sort_by.equals(missionControlStatsDashboardSpec.inv_sort_by()) : missionControlStatsDashboardSpec.inv_sort_by() != null) || (this.listings_filter != null ? !this.listings_filter.equals(missionControlStatsDashboardSpec.listings_filter()) : missionControlStatsDashboardSpec.listings_filter() != null) || (this.show_onboarding != null ? !this.show_onboarding.equals(missionControlStatsDashboardSpec.show_onboarding()) : missionControlStatsDashboardSpec.show_onboarding() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((((((((((((((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * 1000003) ^ (this.start_date_str == null ? 0 : this.start_date_str.hashCode())) * 1000003) ^ (this.date_range == null ? 0 : this.date_range.hashCode())) * 1000003) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * 1000003) ^ (this.end_date_str == null ? 0 : this.end_date_str.hashCode())) * 1000003) ^ (this.end_date_str_inclusive == null ? 0 : this.end_date_str_inclusive.hashCode())) * 1000003) ^ (this.channel == null ? 0 : this.channel.hashCode())) * 1000003) ^ (this.prod_shop_id == null ? 0 : this.prod_shop_id.hashCode())) * 1000003) ^ (this.prod_dataset_override == null ? 0 : this.prod_dataset_override.hashCode())) * 1000003) ^ (this.include_yoy_visits == null ? 0 : this.include_yoy_visits.hashCode())) * 1000003) ^ (this.page_id == null ? 0 : this.page_id.hashCode())) * 1000003) ^ (this.include_all_listings == null ? 0 : this.include_all_listings.hashCode())) * 1000003) ^ (this.currency_filter == null ? 0 : this.currency_filter.hashCode())) * 1000003) ^ (this.inv_limit == null ? 0 : this.inv_limit.hashCode())) * 1000003) ^ (this.inv_offset == null ? 0 : this.inv_offset.hashCode())) * 1000003) ^ (this.inv_sort == null ? 0 : this.inv_sort.hashCode())) * 1000003) ^ (this.inv_sort_by == null ? 0 : this.inv_sort_by.hashCode())) * 1000003) ^ (this.listings_filter == null ? 0 : this.listings_filter.hashCode())) * 1000003;
        if (this.show_onboarding != null) {
            i = this.show_onboarding.hashCode();
        }
        return hashCode ^ i;
    }
}
