package com.etsy.etsyapi.api.shop.bespoke;

import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$AutoValue_MissionControlStatsTrafficHeroSpec reason: invalid class name */
abstract class C$AutoValue_MissionControlStatsTrafficHeroSpec extends C$$AutoValue_MissionControlStatsTrafficHeroSpec {
    C$AutoValue_MissionControlStatsTrafficHeroSpec(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5) {
        super(etsyId, num, str, str2, num2, str3, str4, str5);
    }

    public static MissionControlStatsTrafficHeroSpec read(JsonParser jsonParser) throws IOException {
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
                            c = 1;
                            break;
                        }
                        break;
                    case -531577596:
                        if (currentName.equals("end_date_str")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -474705571:
                        if (currentName.equals("start_date_str")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 738950403:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_CHANNEL)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 873238892:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_DATE_RANGE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1535116401:
                        if (currentName.equals("end_date_str_inclusive")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1725067410:
                        if (currentName.equals("end_date")) {
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
                }
                switch (c) {
                    case 0:
                        aVar.a((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 1:
                        aVar.a(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 2:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 3:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 4:
                        aVar.b(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 5:
                        aVar.c(ModelParser.a(jsonParser));
                        break;
                    case 6:
                        aVar.d(ModelParser.a(jsonParser));
                        break;
                    case 7:
                        aVar.e(ModelParser.a(jsonParser));
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
