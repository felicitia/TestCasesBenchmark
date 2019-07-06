package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.EtsyId;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsPage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MissionControlStatsTrafficHeroSpec implements Request<MissionControlStatsPage> {

    public static abstract class a {
    }

    @Nullable
    public abstract String channel();

    @Nullable
    public abstract String date_range();

    @Nullable
    public abstract Integer end_date();

    @Nullable
    public abstract String end_date_str();

    @Nullable
    public abstract String end_date_str_inclusive();

    @NonNull
    public abstract EtsyId shop_id();

    @Nullable
    public abstract Integer start_date();

    @Nullable
    public abstract String start_date_str();

    public static MissionControlStatsTrafficHeroSpec create(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5) {
        AutoValue_MissionControlStatsTrafficHeroSpec autoValue_MissionControlStatsTrafficHeroSpec = new AutoValue_MissionControlStatsTrafficHeroSpec(etsyId, num, str, str2, num2, str3, str4, str5);
        return autoValue_MissionControlStatsTrafficHeroSpec;
    }

    public static MissionControlStatsTrafficHeroSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_MissionControlStatsTrafficHeroSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(MissionControlStatsTrafficHeroSpec missionControlStatsTrafficHeroSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return missionControlStatsTrafficHeroSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(MissionControlStatsTrafficHeroSpec missionControlStatsTrafficHeroSpec) {
        return new a(missionControlStatsTrafficHeroSpec);
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
    }

    public c<MissionControlStatsPage> request() {
        return request(new HashMap());
    }

    public c<MissionControlStatsPage> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<MissionControlStatsPage> cVar = new c<>();
        cVar.a = "/etsyapps/v3/bespoke/shop/{shop_id}/stats/traffic-hero".replace("{shop_id}", shop_id().toString());
        cVar.b = MissionControlStatsPage.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
