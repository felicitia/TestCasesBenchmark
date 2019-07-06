package com.etsy.etsyapi.api.pub;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.resource.pub.BugHuntLeader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BugHuntLeadersGetSpec implements Request<BugHuntLeader> {

    public static abstract class a {
        public abstract BugHuntLeadersGetSpec a();
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
    }

    public static BugHuntLeadersGetSpec create() {
        return new AutoValue_BugHuntLeadersGetSpec();
    }

    public static BugHuntLeadersGetSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_BugHuntLeadersGetSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(BugHuntLeadersGetSpec bugHuntLeadersGetSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return bugHuntLeadersGetSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(BugHuntLeadersGetSpec bugHuntLeadersGetSpec) {
        return new a(bugHuntLeadersGetSpec);
    }

    public c<BugHuntLeader> request() {
        return request(new HashMap());
    }

    public c<BugHuntLeader> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<BugHuntLeader> cVar = new c<>();
        cVar.a = "/etsyapps/v3/public/bughunt/leaders";
        cVar.b = BugHuntLeader.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
