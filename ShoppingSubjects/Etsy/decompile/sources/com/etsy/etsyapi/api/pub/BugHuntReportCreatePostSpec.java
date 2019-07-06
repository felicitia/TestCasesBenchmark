package com.etsy.etsyapi.api.pub;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.EtsyId;
import com.etsy.etsyapi.models.resource.pub.BugHuntReport;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BugHuntReportCreatePostSpec implements Request<BugHuntReport> {

    public static abstract class a {
        public abstract a a(EtsyId etsyId);

        public abstract a a(File file);

        public abstract a a(String str);

        public abstract BugHuntReportCreatePostSpec a();

        public abstract a b(File file);

        public abstract a b(String str);

        public abstract a c(File file);

        public abstract a c(String str);

        public abstract a d(String str);

        public abstract a e(String str);
    }

    @Nullable
    public abstract String device_type();

    @NonNull
    public abstract String etsy_version();

    @Nullable
    public abstract File image01();

    @Nullable
    public abstract File image02();

    @Nullable
    public abstract File image03();

    @NonNull
    public abstract String message();

    @NonNull
    public abstract String platform();

    @NonNull
    public abstract String platform_version();

    @Nullable
    public abstract EtsyId user_id();

    public static BugHuntReportCreatePostSpec create(String str, String str2, String str3, String str4, String str5, EtsyId etsyId, File file, File file2, File file3) {
        AutoValue_BugHuntReportCreatePostSpec autoValue_BugHuntReportCreatePostSpec = new AutoValue_BugHuntReportCreatePostSpec(str, str2, str3, str4, str5, etsyId, file, file2, file3);
        return autoValue_BugHuntReportCreatePostSpec;
    }

    public static BugHuntReportCreatePostSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_BugHuntReportCreatePostSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(BugHuntReportCreatePostSpec bugHuntReportCreatePostSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return bugHuntReportCreatePostSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(BugHuntReportCreatePostSpec bugHuntReportCreatePostSpec) {
        return new a(bugHuntReportCreatePostSpec);
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
        if (!map.containsKey("message")) {
            map.put("message", message());
        }
        if (!map.containsKey(ResponseConstants.PLATFORM)) {
            map.put(ResponseConstants.PLATFORM, platform());
        }
        if (!map.containsKey(ResponseConstants.PLATFORM_VERSION)) {
            map.put(ResponseConstants.PLATFORM_VERSION, platform_version());
        }
        if (!map.containsKey(ResponseConstants.ETSY_VERSION)) {
            map.put(ResponseConstants.ETSY_VERSION, etsy_version());
        }
        if (!map.containsKey("device_type") && device_type() != null) {
            map.put("device_type", device_type());
        }
        if (!map.containsKey("user_id") && user_id() != null) {
            map.put("user_id", user_id());
        }
        if (!map.containsKey("image01") && image01() != null) {
            map.put("image01", image01());
        }
        if (!map.containsKey("image02") && image02() != null) {
            map.put("image02", image02());
        }
        if (!map.containsKey("image03") && image03() != null) {
            map.put("image03", image03());
        }
    }

    public c<BugHuntReport> request() {
        return request(new HashMap());
    }

    public c<BugHuntReport> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<BugHuntReport> cVar = new c<>();
        cVar.a = "/etsyapps/v3/public/bughunt/report";
        cVar.b = BugHuntReport.class;
        cVar.c = BaseHttpRequest.POST;
        cVar.d = map;
        return cVar;
    }
}
