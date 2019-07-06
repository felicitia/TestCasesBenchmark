package com.etsy.etsyapi.api.shop.bespoke;

import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.etsyapi.models.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$AutoValue_MissionControlStatsDashboardListingSpec reason: invalid class name */
abstract class C$AutoValue_MissionControlStatsDashboardListingSpec extends C$$AutoValue_MissionControlStatsDashboardListingSpec {
    C$AutoValue_MissionControlStatsDashboardListingSpec(EtsyId etsyId, EtsyId etsyId2, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, String str6) {
        super(etsyId, etsyId2, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, str6);
    }

    public static MissionControlStatsDashboardListingSpec read(JsonParser jsonParser) throws IOException {
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
                    case -1981272548:
                        if (currentName.equals("prod_shop_id")) {
                            c = 9;
                            break;
                        }
                        break;
                    case -1573629589:
                        if (currentName.equals("start_date")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -531577596:
                        if (currentName.equals("end_date_str")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -474705571:
                        if (currentName.equals("start_date_str")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 416469862:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_CURRENCY)) {
                            c = 12;
                            break;
                        }
                        break;
                    case 738950403:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_CHANNEL)) {
                            c = 8;
                            break;
                        }
                        break;
                    case 873238892:
                        if (currentName.equals(Filter.FILTER_FIELD_NAME_DATE_RANGE)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 988969142:
                        if (currentName.equals("listing_id")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1535116401:
                        if (currentName.equals("end_date_str_inclusive")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1725067410:
                        if (currentName.equals("end_date")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1726427163:
                        if (currentName.equals("include_yoy_visits")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 2020677531:
                        if (currentName.equals("prod_dataset_override")) {
                            c = 10;
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
                        aVar.b((EtsyId) ModelParser.a(jsonParser, EtsyId.class));
                        break;
                    case 2:
                        aVar.a(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 3:
                        aVar.a(ModelParser.a(jsonParser));
                        break;
                    case 4:
                        aVar.b(ModelParser.a(jsonParser));
                        break;
                    case 5:
                        aVar.b(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 6:
                        aVar.c(ModelParser.a(jsonParser));
                        break;
                    case 7:
                        aVar.d(ModelParser.a(jsonParser));
                        break;
                    case 8:
                        aVar.e(ModelParser.a(jsonParser));
                        break;
                    case 9:
                        aVar.c(Integer.valueOf(jsonParser.getValueAsInt()));
                        break;
                    case 10:
                        aVar.a(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 11:
                        aVar.b(Boolean.valueOf(jsonParser.getValueAsBoolean()));
                        break;
                    case 12:
                        aVar.f(ModelParser.a(jsonParser));
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
