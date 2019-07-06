package com.etsy.etsyapi.api.member;

import com.etsy.auto.value.jackson.ModelParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.member.$AutoValue_ConversationsGetSpec reason: invalid class name */
abstract class C$AutoValue_ConversationsGetSpec extends C$$AutoValue_ConversationsGetSpec {
    C$AutoValue_ConversationsGetSpec(Integer num, Boolean bool, Boolean bool2, Integer num2, Integer num3, Boolean bool3, Boolean bool4, String str) {
        super(num, bool, bool2, num2, num3, bool3, bool4, str);
    }

    public static ConversationsGetSpec read(JsonParser jsonParser) throws IOException {
        a aVar = new a();
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            return null;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                char c = 65535;
                switch (currentName.hashCode()) {
                    case -1325048210:
                        if (currentName.equals("tag_identifier")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1204194686:
                        if (currentName.equals("friendly_timestamp")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -1019779949:
                        if (currentName.equals("offset")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -49715507:
                        if (currentName.equals("include_read")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -27237652:
                        if (currentName.equals("in_inbox")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 102976443:
                        if (currentName.equals("limit")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 109441850:
                        if (currentName.equals("since")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1967053405:
                        if (currentName.equals("is_visible")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        aVar.a(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 1:
                        aVar.a(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 2:
                        aVar.b(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 3:
                        aVar.b(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 4:
                        aVar.c(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 5:
                        aVar.c(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 6:
                        aVar.d(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 7:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
        return aVar.a();
    }
}
