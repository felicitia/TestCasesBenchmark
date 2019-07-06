package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.EtsyId;
import com.etsy.etsyapi.models.resource.shop.SOEDashboard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class SellerDashboardGetSpec implements Request<SOEDashboard> {

    public static abstract class a {
    }

    @Nullable
    public abstract Boolean includeShopManager();

    @NonNull
    public abstract EtsyId shop_id();

    @Nullable
    public abstract String statsChannel();

    @Nullable
    public abstract String statsRange();

    public static SellerDashboardGetSpec create(EtsyId etsyId, String str, String str2, Boolean bool) {
        return new AutoValue_SellerDashboardGetSpec(etsyId, str, str2, bool);
    }

    public static SellerDashboardGetSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_SellerDashboardGetSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(SellerDashboardGetSpec sellerDashboardGetSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return sellerDashboardGetSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(SellerDashboardGetSpec sellerDashboardGetSpec) {
        return new a(sellerDashboardGetSpec);
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
        if (!map.containsKey("statsRange") && statsRange() != null) {
            map.put("statsRange", statsRange());
        }
        if (!map.containsKey("statsChannel") && statsChannel() != null) {
            map.put("statsChannel", statsChannel());
        }
        if (!map.containsKey("includeShopManager") && includeShopManager() != null) {
            map.put("includeShopManager", includeShopManager());
        }
    }

    public c<SOEDashboard> request() {
        return request(new HashMap());
    }

    public c<SOEDashboard> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<SOEDashboard> cVar = new c<>();
        cVar.a = "/etsyapps/v3/bespoke/shop/{shop_id}/dashboard".replace("{shop_id}", shop_id().toString());
        cVar.b = SOEDashboard.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
