package com.etsy.etsyapi.api.member;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.resource.member.ConversationsFolders;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ConversationsFoldersSpec implements Request<ConversationsFolders> {

    public static abstract class a {
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
    }

    public static ConversationsFoldersSpec create() {
        return new AutoValue_ConversationsFoldersSpec();
    }

    public static ConversationsFoldersSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_ConversationsFoldersSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(ConversationsFoldersSpec conversationsFoldersSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return conversationsFoldersSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(ConversationsFoldersSpec conversationsFoldersSpec) {
        return new a(conversationsFoldersSpec);
    }

    public c<ConversationsFolders> request() {
        return request(new HashMap());
    }

    public c<ConversationsFolders> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<ConversationsFolders> cVar = new c<>();
        cVar.a = "/etsyapps/v3/member/conversations/folders";
        cVar.b = ConversationsFolders.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
