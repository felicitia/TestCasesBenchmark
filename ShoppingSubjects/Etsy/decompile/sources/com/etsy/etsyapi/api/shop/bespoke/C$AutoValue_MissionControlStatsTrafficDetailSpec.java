package com.etsy.etsyapi.api.shop.bespoke;

import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$AutoValue_MissionControlStatsTrafficDetailSpec reason: invalid class name */
abstract class C$AutoValue_MissionControlStatsTrafficDetailSpec extends C$$AutoValue_MissionControlStatsTrafficDetailSpec {
    C$AutoValue_MissionControlStatsTrafficDetailSpec(EtsyId etsyId, String str, String str2, String str3, String str4, Integer num, String str5, String str6, Integer num2, String str7, String str8, String str9, Boolean bool) {
        super(etsyId, str, str2, str3, str4, num, str5, str6, num2, str7, str8, str9, bool);
    }

    public static MissionControlStatsTrafficDetailSpec read(JsonParser jsonParser) throws IOException {
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
                    case -1573629589:
                        if (currentName.equals("start_date")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1326197564:
                        if (currentName.equals("domain")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -722568161:
                        if (currentName.equals("referrer")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -531577596:
                        if (currentName.equals("end_date_str")) {
                            c = 9;
                            break;
                        }
                        break;
                    case -474705571:
                        if (currentName.equals("start_date_str")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 738950403:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_CHANNEL)) {
                            c = 11;
                            break;
                        }
                        break;
                    case 873238892:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_DATE_RANGE)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1535116401:
                        if (currentName.equals("end_date_str_inclusive")) {
                            c = 10;
                            break;
                        }
                        break;
                    case 1725067410:
                        if (currentName.equals("end_date")) {
                            c = 8;
                            break;
                        }
                        break;
                    case 1726427163:
                        if (currentName.equals("include_yoy_visits")) {
                            c = 12;
                            break;
                        }
                        break;
                    case 1826554577:
                        if (currentName.equals("internal_page")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 2067081988:
                        if (currentName.equals("shop_id")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 2130707039:
                        if (currentName.equals("referrer_group")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        aVar.a((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 1:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 2:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 3:
                        aVar.c(ModelParser.a(jsonParser));
                        break;
                    case 4:
                        aVar.d(ModelParser.a(jsonParser));
                        break;
                    case 5:
                        aVar.a(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 6:
                        aVar.e(ModelParser.a(jsonParser));
                        break;
                    case 7:
                        aVar.f(ModelParser.a(jsonParser));
                        break;
                    case 8:
                        aVar.b(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 9:
                        aVar.g(ModelParser.a(jsonParser));
                        break;
                    case 10:
                        aVar.h(ModelParser.a(jsonParser));
                        break;
                    case 11:
                        aVar.i(ModelParser.a(jsonParser));
                        break;
                    case 12:
                        aVar.a(Boolean.valueOf(jsonParser.getValueAsBoolean()));
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
