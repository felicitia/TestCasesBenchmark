package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.EtsyId;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsPageList;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MissionControlStatsDashboardListingSpec implements Request<MissionControlStatsPageList> {

    public static abstract class a {
    }

    @Nullable
    public abstract String channel();

    @Nullable
    public abstract String currency_filter();

    @Nullable
    public abstract String date_range();

    @Nullable
    public abstract Integer end_date();

    @Nullable
    public abstract String end_date_str();

    @Nullable
    public abstract String end_date_str_inclusive();

    @Nullable
    public abstract Boolean include_yoy_visits();

    @NonNull
    public abstract EtsyId listing_id();

    @Nullable
    public abstract Boolean prod_dataset_override();

    @Nullable
    public abstract Integer prod_shop_id();

    @NonNull
    public abstract EtsyId shop_id();

    @Nullable
    public abstract Integer start_date();

    @Nullable
    public abstract String start_date_str();

    public static MissionControlStatsDashboardListingSpec create(EtsyId etsyId, EtsyId etsyId2, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, String str6) {
        AutoValue_MissionControlStatsDashboardListingSpec autoValue_MissionControlStatsDashboardListingSpec = new AutoValue_MissionControlStatsDashboardListingSpec(etsyId, etsyId2, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, str6);
        return autoValue_MissionControlStatsDashboardListingSpec;
    }

    public static MissionControlStatsDashboardListingSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_MissionControlStatsDashboardListingSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(MissionControlStatsDashboardListingSpec missionControlStatsDashboardListingSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return missionControlStatsDashboardListingSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(MissionControlStatsDashboardListingSpec missionControlStatsDashboardListingSpec) {
        return new a(missionControlStatsDashboardListingSpec);
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
        if (!map.containsKey("start_date") && start_date() != null) {
            map.put("start_date", start_date());
        }
        if (!map.containsKey("start_date_str") && start_date_str() != null) {
            map.put("start_date_str", start_date_str());
        }
        if (!map.containsKey(Filter.FILTER_FIELD_NAME_DATE_RANGE) && date_range() != null) {
            map.put(Filter.FILTER_FIELD_NAME_DATE_RANGE, date_range());
        }
        if (!map.containsKey("end_date") && end_date() != null) {
            map.put("end_date", end_date());
        }
        if (!map.containsKey("end_date_str") && end_date_str() != null) {
            map.put("end_date_str", end_date_str());
        }
        if (!map.containsKey("end_date_str_inclusive") && end_date_str_inclusive() != null) {
            map.put("end_date_str_inclusive", end_date_str_inclusive());
        }
        if (!map.containsKey(Filter.FILTER_FIELD_NAME_CHANNEL) && channel() != null) {
            map.put(Filter.FILTER_FIELD_NAME_CHANNEL, channel());
        }
        if (!map.containsKey("prod_shop_id") && prod_shop_id() != null) {
            map.put("prod_shop_id", prod_shop_id());
        }
        if (!map.containsKey("prod_dataset_override") && prod_dataset_override() != null) {
            map.put("prod_dataset_override", prod_dataset_override());
        }
        if (!map.containsKey("include_yoy_visits") && include_yoy_visits() != null) {
            map.put("include_yoy_visits", include_yoy_visits());
        }
        if (!map.containsKey(Filter.FILTER_FIELD_NAME_CURRENCY) && currency_filter() != null) {
            map.put(Filter.FILTER_FIELD_NAME_CURRENCY, currency_filter());
        }
    }

    public c<MissionControlStatsPageList> request() {
        return request(new HashMap());
    }

    public c<MissionControlStatsPageList> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<MissionControlStatsPageList> cVar = new c<>();
        cVar.a = "/etsyapps/v3/bespoke/shop/{shop_id}/stats/dashboard-listing/{listing_id}".replace("{shop_id}", shop_id().toString()).replace("{listing_id}", listing_id().toString());
        cVar.b = MissionControlStatsPageList.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
