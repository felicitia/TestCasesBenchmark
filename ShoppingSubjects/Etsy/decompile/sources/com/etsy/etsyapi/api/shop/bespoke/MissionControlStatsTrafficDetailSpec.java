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

public abstract class MissionControlStatsTrafficDetailSpec implements Request<MissionControlStatsPageList> {

    public static abstract class a {
    }

    @Nullable
    public abstract String channel();

    @Nullable
    public abstract String date_range();

    @Nullable
    public abstract String domain();

    @Nullable
    public abstract Integer end_date();

    @Nullable
    public abstract String end_date_str();

    @Nullable
    public abstract String end_date_str_inclusive();

    @Nullable
    public abstract Boolean include_yoy_visits();

    @Nullable
    public abstract String internal_page();

    @Nullable
    public abstract String referrer();

    @NonNull
    public abstract String referrer_group();

    @NonNull
    public abstract EtsyId shop_id();

    @Nullable
    public abstract Integer start_date();

    @Nullable
    public abstract String start_date_str();

    public static MissionControlStatsTrafficDetailSpec create(EtsyId etsyId, String str, String str2, String str3, String str4, Integer num, String str5, String str6, Integer num2, String str7, String str8, String str9, Boolean bool) {
        AutoValue_MissionControlStatsTrafficDetailSpec autoValue_MissionControlStatsTrafficDetailSpec = new AutoValue_MissionControlStatsTrafficDetailSpec(etsyId, str, str2, str3, str4, num, str5, str6, num2, str7, str8, str9, bool);
        return autoValue_MissionControlStatsTrafficDetailSpec;
    }

    public static MissionControlStatsTrafficDetailSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_MissionControlStatsTrafficDetailSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(MissionControlStatsTrafficDetailSpec missionControlStatsTrafficDetailSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return missionControlStatsTrafficDetailSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(MissionControlStatsTrafficDetailSpec missionControlStatsTrafficDetailSpec) {
        return new a(missionControlStatsTrafficDetailSpec);
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
        if (!map.containsKey("referrer") && referrer() != null) {
            map.put("referrer", referrer());
        }
        if (!map.containsKey("domain") && domain() != null) {
            map.put("domain", domain());
        }
        if (!map.containsKey("internal_page") && internal_page() != null) {
            map.put("internal_page", internal_page());
        }
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
        if (!map.containsKey("include_yoy_visits") && include_yoy_visits() != null) {
            map.put("include_yoy_visits", include_yoy_visits());
        }
    }

    public c<MissionControlStatsPageList> request() {
        return request(new HashMap());
    }

    public c<MissionControlStatsPageList> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<MissionControlStatsPageList> cVar = new c<>();
        cVar.a = "/etsyapps/v3/bespoke/shop/{shop_id}/stats/traffic-detail/{referrer_group}".replace("{shop_id}", shop_id().toString()).replace("{referrer_group}", referrer_group().toString());
        cVar.b = MissionControlStatsPageList.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
