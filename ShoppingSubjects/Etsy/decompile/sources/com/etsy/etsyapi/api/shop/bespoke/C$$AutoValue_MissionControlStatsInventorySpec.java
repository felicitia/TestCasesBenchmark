package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsInventorySpec reason: invalid class name */
abstract class C$$AutoValue_MissionControlStatsInventorySpec extends MissionControlStatsInventorySpec {
    private final String channel;
    private final String date_range;
    private final String domain;
    private final Integer end_date;
    private final String end_date_str;
    private final String end_date_str_inclusive;
    private final Boolean include_yoy_visits;
    private final String internal_page;
    private final Integer inv_limit;
    private final Integer inv_offset;
    private final String inv_sort;
    private final String inv_sort_by;
    private final String listings_filter;
    private final Boolean prod_dataset_override;
    private final Integer prod_shop_id;
    private final String referrer;
    private final String referrer_group;
    private final EtsyId shop_id;
    private final Integer start_date;
    private final String start_date_str;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_MissionControlStatsInventorySpec$a */
    /* compiled from: $$AutoValue_MissionControlStatsInventorySpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a {
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
        private Integer l;
        private Integer m;
        private String n;
        private String o;
        private String p;
        private String q;
        private String r;
        private String s;
        private String t;

        a() {
        }

        a(MissionControlStatsInventorySpec missionControlStatsInventorySpec) {
            this.a = missionControlStatsInventorySpec.shop_id();
            this.b = missionControlStatsInventorySpec.start_date();
            this.c = missionControlStatsInventorySpec.start_date_str();
            this.d = missionControlStatsInventorySpec.date_range();
            this.e = missionControlStatsInventorySpec.end_date();
            this.f = missionControlStatsInventorySpec.end_date_str();
            this.g = missionControlStatsInventorySpec.end_date_str_inclusive();
            this.h = missionControlStatsInventorySpec.channel();
            this.i = missionControlStatsInventorySpec.prod_shop_id();
            this.j = missionControlStatsInventorySpec.prod_dataset_override();
            this.k = missionControlStatsInventorySpec.include_yoy_visits();
            this.l = missionControlStatsInventorySpec.inv_limit();
            this.m = missionControlStatsInventorySpec.inv_offset();
            this.n = missionControlStatsInventorySpec.inv_sort();
            this.o = missionControlStatsInventorySpec.inv_sort_by();
            this.p = missionControlStatsInventorySpec.referrer_group();
            this.q = missionControlStatsInventorySpec.referrer();
            this.r = missionControlStatsInventorySpec.domain();
            this.s = missionControlStatsInventorySpec.internal_page();
            this.t = missionControlStatsInventorySpec.listings_filter();
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a a(@Nullable Integer num) {
            this.b = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a a(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a b(@Nullable String str) {
            this.d = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a b(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a c(@Nullable String str) {
            this.f = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a d(@Nullable String str) {
            this.g = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a e(@Nullable String str) {
            this.h = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a c(@Nullable Integer num) {
            this.i = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a a(@Nullable Boolean bool) {
            this.j = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a b(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a d(@Nullable Integer num) {
            this.l = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a e(@Nullable Integer num) {
            this.m = num;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a f(@Nullable String str) {
            this.n = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a g(@Nullable String str) {
            this.o = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a h(@Nullable String str) {
            this.p = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a i(@Nullable String str) {
            this.q = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a j(@Nullable String str) {
            this.r = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a k(@Nullable String str) {
            this.s = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec.a l(@Nullable String str) {
            this.t = str;
            return this;
        }

        public MissionControlStatsInventorySpec a() {
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
            Integer num4 = this.l;
            Integer num5 = this.m;
            Integer num6 = num5;
            Integer num7 = num6;
            AutoValue_MissionControlStatsInventorySpec autoValue_MissionControlStatsInventorySpec = new AutoValue_MissionControlStatsInventorySpec(etsyId, num, str2, str3, num2, str4, str5, str6, num3, bool, bool2, num4, num7, this.n, this.o, this.p, this.q, this.r, this.s, this.t);
            return autoValue_MissionControlStatsInventorySpec;
        }
    }

    C$$AutoValue_MissionControlStatsInventorySpec(EtsyId etsyId, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num3, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Integer num4, @Nullable Integer num5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12) {
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
        this.inv_limit = num4;
        this.inv_offset = num5;
        this.inv_sort = str6;
        this.inv_sort_by = str7;
        this.referrer_group = str8;
        this.referrer = str9;
        this.domain = str10;
        this.internal_page = str11;
        this.listings_filter = str12;
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
    public String listings_filter() {
        return this.listings_filter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MissionControlStatsInventorySpec{shop_id=");
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
        sb.append("listings_filter=");
        sb.append(this.listings_filter);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MissionControlStatsInventorySpec)) {
            return false;
        }
        MissionControlStatsInventorySpec missionControlStatsInventorySpec = (MissionControlStatsInventorySpec) obj;
        if (!this.shop_id.equals(missionControlStatsInventorySpec.shop_id()) || (this.start_date != null ? !this.start_date.equals(missionControlStatsInventorySpec.start_date()) : missionControlStatsInventorySpec.start_date() != null) || (this.start_date_str != null ? !this.start_date_str.equals(missionControlStatsInventorySpec.start_date_str()) : missionControlStatsInventorySpec.start_date_str() != null) || (this.date_range != null ? !this.date_range.equals(missionControlStatsInventorySpec.date_range()) : missionControlStatsInventorySpec.date_range() != null) || (this.end_date != null ? !this.end_date.equals(missionControlStatsInventorySpec.end_date()) : missionControlStatsInventorySpec.end_date() != null) || (this.end_date_str != null ? !this.end_date_str.equals(missionControlStatsInventorySpec.end_date_str()) : missionControlStatsInventorySpec.end_date_str() != null) || (this.end_date_str_inclusive != null ? !this.end_date_str_inclusive.equals(missionControlStatsInventorySpec.end_date_str_inclusive()) : missionControlStatsInventorySpec.end_date_str_inclusive() != null) || (this.channel != null ? !this.channel.equals(missionControlStatsInventorySpec.channel()) : missionControlStatsInventorySpec.channel() != null) || (this.prod_shop_id != null ? !this.prod_shop_id.equals(missionControlStatsInventorySpec.prod_shop_id()) : missionControlStatsInventorySpec.prod_shop_id() != null) || (this.prod_dataset_override != null ? !this.prod_dataset_override.equals(missionControlStatsInventorySpec.prod_dataset_override()) : missionControlStatsInventorySpec.prod_dataset_override() != null) || (this.include_yoy_visits != null ? !this.include_yoy_visits.equals(missionControlStatsInventorySpec.include_yoy_visits()) : missionControlStatsInventorySpec.include_yoy_visits() != null) || (this.inv_limit != null ? !this.inv_limit.equals(missionControlStatsInventorySpec.inv_limit()) : missionControlStatsInventorySpec.inv_limit() != null) || (this.inv_offset != null ? !this.inv_offset.equals(missionControlStatsInventorySpec.inv_offset()) : missionControlStatsInventorySpec.inv_offset() != null) || (this.inv_sort != null ? !this.inv_sort.equals(missionControlStatsInventorySpec.inv_sort()) : missionControlStatsInventorySpec.inv_sort() != null) || (this.inv_sort_by != null ? !this.inv_sort_by.equals(missionControlStatsInventorySpec.inv_sort_by()) : missionControlStatsInventorySpec.inv_sort_by() != null) || (this.referrer_group != null ? !this.referrer_group.equals(missionControlStatsInventorySpec.referrer_group()) : missionControlStatsInventorySpec.referrer_group() != null) || (this.referrer != null ? !this.referrer.equals(missionControlStatsInventorySpec.referrer()) : missionControlStatsInventorySpec.referrer() != null) || (this.domain != null ? !this.domain.equals(missionControlStatsInventorySpec.domain()) : missionControlStatsInventorySpec.domain() != null) || (this.internal_page != null ? !this.internal_page.equals(missionControlStatsInventorySpec.internal_page()) : missionControlStatsInventorySpec.internal_page() != null) || (this.listings_filter != null ? !this.listings_filter.equals(missionControlStatsInventorySpec.listings_filter()) : missionControlStatsInventorySpec.listings_filter() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((((((((((((((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * 1000003) ^ (this.start_date_str == null ? 0 : this.start_date_str.hashCode())) * 1000003) ^ (this.date_range == null ? 0 : this.date_range.hashCode())) * 1000003) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * 1000003) ^ (this.end_date_str == null ? 0 : this.end_date_str.hashCode())) * 1000003) ^ (this.end_date_str_inclusive == null ? 0 : this.end_date_str_inclusive.hashCode())) * 1000003) ^ (this.channel == null ? 0 : this.channel.hashCode())) * 1000003) ^ (this.prod_shop_id == null ? 0 : this.prod_shop_id.hashCode())) * 1000003) ^ (this.prod_dataset_override == null ? 0 : this.prod_dataset_override.hashCode())) * 1000003) ^ (this.include_yoy_visits == null ? 0 : this.include_yoy_visits.hashCode())) * 1000003) ^ (this.inv_limit == null ? 0 : this.inv_limit.hashCode())) * 1000003) ^ (this.inv_offset == null ? 0 : this.inv_offset.hashCode())) * 1000003) ^ (this.inv_sort == null ? 0 : this.inv_sort.hashCode())) * 1000003) ^ (this.inv_sort_by == null ? 0 : this.inv_sort_by.hashCode())) * 1000003) ^ (this.referrer_group == null ? 0 : this.referrer_group.hashCode())) * 1000003) ^ (this.referrer == null ? 0 : this.referrer.hashCode())) * 1000003) ^ (this.domain == null ? 0 : this.domain.hashCode())) * 1000003) ^ (this.internal_page == null ? 0 : this.internal_page.hashCode())) * 1000003;
        if (this.listings_filter != null) {
            i = this.listings_filter.hashCode();
        }
        return hashCode ^ i;
    }
}
