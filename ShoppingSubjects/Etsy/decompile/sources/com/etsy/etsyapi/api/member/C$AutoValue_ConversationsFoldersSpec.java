package com.etsy.etsyapi.api.member;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.member.$AutoValue_ConversationsFoldersSpec reason: invalid class name */
abstract class C$AutoValue_ConversationsFoldersSpec extends C$$AutoValue_ConversationsFoldersSpec {
    C$AutoValue_ConversationsFoldersSpec() {
    }

    public static ConversationsFoldersSpec read(JsonParser jsonParser) throws IOException {
        a aVar = new a();
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            return null;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                currentName.hashCode();
                jsonParser.skipChildren();
            }
        }
        return aVar.a();
    }
}
