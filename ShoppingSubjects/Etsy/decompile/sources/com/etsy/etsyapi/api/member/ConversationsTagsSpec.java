package com.etsy.etsyapi.api.member;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.resource.member.ConversationsTags;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ConversationsTagsSpec implements Request<ConversationsTags> {

    public static abstract class a {
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
    }

    public static ConversationsTagsSpec create() {
        return new AutoValue_ConversationsTagsSpec();
    }

    public static ConversationsTagsSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_ConversationsTagsSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(ConversationsTagsSpec conversationsTagsSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return conversationsTagsSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(ConversationsTagsSpec conversationsTagsSpec) {
        return new a(conversationsTagsSpec);
    }

    public c<ConversationsTags> request() {
        return request(new HashMap());
    }

    public c<ConversationsTags> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<ConversationsTags> cVar = new c<>();
        cVar.a = "/etsyapps/v3/member/conversations/tags";
        cVar.b = ConversationsTags.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
