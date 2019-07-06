package com.etsy.etsyapi.api.pub;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.pub.$AutoValue_BugHuntLeadersGetSpec reason: invalid class name */
abstract class C$AutoValue_BugHuntLeadersGetSpec extends C$$AutoValue_BugHuntLeadersGetSpec {
    C$AutoValue_BugHuntLeadersGetSpec() {
    }

    public static BugHuntLeadersGetSpec read(JsonParser jsonParser) throws IOException {
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
