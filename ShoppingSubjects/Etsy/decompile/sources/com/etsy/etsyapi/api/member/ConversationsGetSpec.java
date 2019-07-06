package com.etsy.etsyapi.api.member;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.Request;
import com.etsy.etsyapi.c;
import com.etsy.etsyapi.models.resource.member.Conversation2;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ConversationsGetSpec implements Request<Conversation2> {

    public static abstract class a {
    }

    @Nullable
    public abstract Boolean friendly_timestamp();

    @Nullable
    public abstract Boolean in_inbox();

    @Nullable
    public abstract Boolean include_read();

    @Nullable
    public abstract Boolean is_visible();

    @Nullable
    public abstract Integer limit();

    @Nullable
    public abstract Integer offset();

    @Nullable
    public abstract Integer since();

    @Nullable
    public abstract String tag_identifier();

    public static ConversationsGetSpec create(Integer num, Boolean bool, Boolean bool2, Integer num2, Integer num3, Boolean bool3, Boolean bool4, String str) {
        AutoValue_ConversationsGetSpec autoValue_ConversationsGetSpec = new AutoValue_ConversationsGetSpec(num, bool, bool2, num2, num3, bool3, bool4, str);
        return autoValue_ConversationsGetSpec;
    }

    public static ConversationsGetSpec read(JsonParser jsonParser) throws IOException {
        return AutoValue_ConversationsGetSpec.read(jsonParser);
    }

    public byte[] write(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(this);
    }

    public static byte[] write(ConversationsGetSpec conversationsGetSpec, ObjectMapper objectMapper) throws JsonProcessingException {
        return conversationsGetSpec.write(objectMapper);
    }

    public static a builder() {
        return new a();
    }

    public static a builder(ConversationsGetSpec conversationsGetSpec) {
        return new a(conversationsGetSpec);
    }

    private void buildOptions(@NonNull Map<String, Object> map) {
        if (!map.containsKey("since") && since() != null) {
            map.put("since", since());
        }
        if (!map.containsKey("is_visible") && is_visible() != null) {
            map.put("is_visible", is_visible());
        }
        if (!map.containsKey("in_inbox") && in_inbox() != null) {
            map.put("in_inbox", in_inbox());
        }
        if (!map.containsKey("limit") && limit() != null) {
            map.put("limit", limit());
        }
        if (!map.containsKey("offset") && offset() != null) {
            map.put("offset", offset());
        }
        if (!map.containsKey("include_read") && include_read() != null) {
            map.put("include_read", include_read());
        }
        if (!map.containsKey("friendly_timestamp") && friendly_timestamp() != null) {
            map.put("friendly_timestamp", friendly_timestamp());
        }
        if (!map.containsKey("tag_identifier") && tag_identifier() != null) {
            map.put("tag_identifier", tag_identifier());
        }
    }

    public c<Conversation2> request() {
        return request(new HashMap());
    }

    public c<Conversation2> request(@NonNull Map<String, Object> map) {
        buildOptions(map);
        c<Conversation2> cVar = new c<>();
        cVar.a = "/etsyapps/v3/member/conversations";
        cVar.b = Conversation2.class;
        cVar.c = BaseHttpRequest.GET;
        cVar.d = map;
        return cVar;
    }
}
